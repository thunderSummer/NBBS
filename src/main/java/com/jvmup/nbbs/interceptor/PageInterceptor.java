package com.jvmup.nbbs.interceptor;

import com.jvmup.nbbs.util.PageParam;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;
/**
 * ProjectName: NBBS
 *
 * @author xxl
 *
 * Created by xxl on 2018-07-14 13:42
 **/
@Intercepts(@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class,Integer.class}))
public class PageInterceptor implements Interceptor {
    private Logger log = LogManager.getLogger(PageInterceptor.class);
    private int defaultPageSize;
    private int defaultPage;
    private boolean defaultUseFlag;
    private boolean defaultCheckFlag;
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaStatementHandler = getActualMetaStatementHandler(statementHandler);

        String sql = statementHandler.getBoundSql().getSql();

//        检测未通过，不是select语句
        if (!checkIsSelectFlag(sql)) {
            return invocation.proceed();
        }

        BoundSql boundSql = statementHandler.getBoundSql();

        Object paramObject = boundSql.getParameterObject();

        PageParam pageParam = getPageParam(paramObject);

        if (pageParam == null)
            return invocation.proceed();

        Integer pageNum = pageParam.getDefaultPage() == null ? defaultPage : pageParam.getDefaultPage();
        Integer pageSize = pageParam.getDefaultPageSize() == null ? defaultPageSize : pageParam.getDefaultPageSize();

        Boolean useFlag = pageParam.isDefaultUseFlag() == null ? defaultUseFlag : pageParam.isDefaultUseFlag();
        Boolean checkFlag = pageParam.isDefaultCheckFlag() == null ? defaultCheckFlag : pageParam.isDefaultCheckFlag();

        //不使用分页功能
        if (!useFlag) {
            return invocation.proceed();
        }

        int total = getTotal(invocation, metaStatementHandler, boundSql);

        //将动态获取到的分页参数回填到pageParam中
        setTotalToParam(pageParam, total, pageSize);

        //检查当前页码的有效性
        checkPage(checkFlag, pageNum, pageParam.getTotalPage());

        //修改sql
        return updateSql2Limit(invocation, metaStatementHandler, boundSql, pageNum, pageSize);
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        String strDefaultPage = properties.getProperty("default.page");
        String strDefaultPageSize = properties.getProperty("default.pageSize");
        String strDefaultUseFlag = properties.getProperty("default.useFlag");
        String strDefaultCheckFlag = properties.getProperty("default.checkFlag");
        defaultPage = Integer.valueOf(strDefaultPage);
        defaultPageSize = Integer.valueOf(strDefaultPageSize);
        defaultUseFlag = Boolean.valueOf(strDefaultUseFlag);
        defaultCheckFlag = Boolean.valueOf(strDefaultCheckFlag);
    }

    private MetaObject getActualMetaStatementHandler(StatementHandler statementHandler){
        MetaObject metaStatementHandler = SystemMetaObject.forObject(statementHandler);
        Object object = null;
//        分离代理对象链，目标可能被多个拦截器拦截，分离出最原始的目标类
        while (metaStatementHandler.hasGetter("h")) {
            object = metaStatementHandler.getValue("h");
            metaStatementHandler = SystemMetaObject.forObject(object);
        }
        while(metaStatementHandler.hasGetter("target")){
            object = metaStatementHandler.getValue("target");
            metaStatementHandler=SystemMetaObject.forObject(object);
        }
        return metaStatementHandler;
    }

    /**
     * 判断是否是select语句，只有select语句，才会用到分页
     */

    private boolean checkIsSelectFlag(String sql) {
        String trimSql = sql.trim();
        int index = trimSql.toLowerCase().indexOf("select");
        return index == 0;
    }
    /**
    获取分页的参数

    参数可以通过map，@param注解进行参数传递。或者请求pojo继承自PageParam  将PageParam中的分页数据放进去
     */
    private PageParam getPageParam(Object parameterObject) {
        if (parameterObject == null) {
            return null;
        }

        PageParam pageParam = null;
        //通过map和@param注解将PageParam参数传递进来，pojo继承自PageParam不推荐使用  这里从参数中提取出传递进来的pojo继承自PageParam

//        首先处理传递进来的是map对象和通过注解方式传值的情况，从中提取出PageParam,循环获取map中的键值对，取出PageParam对象
        if (parameterObject instanceof Map) {
            Map<String, Object> params = (Map<String, Object>) parameterObject;
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() instanceof PageParam) {
                    return (PageParam) entry.getValue();
                }
            }
        } else if (parameterObject instanceof PageParam) {
//            继承方式 pojo继承自PageParam 只取出我们希望得到的分页参数
            pageParam = (PageParam) parameterObject;

        }
        return pageParam;
    }

    //    获取当前sql查询的记录总数
    private int getTotal(Invocation invocation, MetaObject metaStatementHandler, BoundSql boundSql) {
//        获取mapper文件中当前查询语句的配置信息
        MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");

        //获取所有配置Configuration
        org.apache.ibatis.session.Configuration configuration = mappedStatement.getConfiguration();

//        获取当前查询语句的sql
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");

//        将sql改写成统计记录数的sql语句,这里是mysql的改写语句,将第一次查询结果作为第二次查询的表
        String countSql = "select count(*) as total from (" + sql + ") $_paging";

//        获取connection连接对象，用于执行countSql语句
        Connection conn = (Connection) invocation.getArgs()[0];

        PreparedStatement ps = null;

        int total = 0;

        try {
//            预编译统计总记录数的sql
            ps = conn.prepareStatement(countSql);
            //构建统计总记录数的BoundSql
            BoundSql countBoundSql = new BoundSql(configuration, countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            //构建ParameterHandler，用于设置统计sql的参数
            ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, boundSql.getParameterObject(), countBoundSql);
            //设置总数sql的参数
            parameterHandler.setParameters(ps);
            //执行查询语句
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//                与countSql中设置的别名对应
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null)
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return total;
    }

    //    设置条数参数到pageParam对象
    private void setTotalToParam(PageParam param, int total, int pageSize) {
        param.setTotal(total);
        param.setTotalPage(total % pageSize == 0 ? total / pageSize : (total / pageSize) + 1);
    }

    //    修改原始sql语句为分页sql语句
    private Object updateSql2Limit(Invocation invocation, MetaObject metaStatementHandler, BoundSql boundSql, int page, int pageSize) throws InvocationTargetException, IllegalAccessException, SQLException, InvocationTargetException {
        String sql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        //构建新的分页sql语句
        String limitSql = "select * from (" + sql + ") $_paging_table limit ?,?";
        //修改当前要执行的sql语句
        metaStatementHandler.setValue("delegate.boundSql.sql", limitSql);
        //相当于调用prepare方法，预编译sql并且加入参数，但是少了分页的两个参数，它返回一个PreparedStatement对象
        PreparedStatement ps = (PreparedStatement) invocation.proceed();
        //获取sql总的参数总数
        int count = ps.getParameterMetaData().getParameterCount();
        //设置与分页相关的两个参数
        ps.setInt(count - 1, (page - 1) * pageSize);
        ps.setInt(count, pageSize);
        return ps;
    }

    //    验证当前页码的有效性
    private void checkPage(boolean checkFlag, Integer pageNumber, Integer pageTotal) throws Exception {
        if (checkFlag) {
            if (pageNumber > pageTotal) {
                throw new Exception("查询失败，查询页码" + pageNumber + "大于总页数" + pageTotal);
            }
        }
    }
}
