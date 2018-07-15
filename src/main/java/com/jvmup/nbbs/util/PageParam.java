package com.jvmup.nbbs.util;

/**
 * 分页插件的基类
 * ProjectName: NBBS
 * @author xxl
 *
 * Created by xxl on 2018-07-14 13:23
 **/
public class PageParam {
    private Integer defaultPage;
    //    默认每页显示条数
    private Integer defaultPageSize = 10;
    //    是否启用分页功能
    private Boolean defaultUseFlag = true;
    //    是否检测当前页码的合法性（大于最大页码或小于最小页码都不合法）
    private Boolean defaultCheckFlag;
    //当前sql查询的总记录数，回填
    private Integer total;
    //    当前sql查询实现分页后的总页数，回填
    private Integer totalPage;

    public Integer getDefaultPage() {
        return defaultPage;
    }

    public void setDefaultPage(Integer defaultPage) {
        this.defaultPage = defaultPage;
    }

    public Integer getDefaultPageSize() {
        return defaultPageSize;
    }

    public void setDefaultPageSize(Integer defaultPageSize) {
        this.defaultPageSize = defaultPageSize;
    }

    public Boolean isDefaultUseFlag() {
        return defaultUseFlag;
    }

    public void setDefaultUseFlag(Boolean defaultUseFlag) {
        this.defaultUseFlag = defaultUseFlag;
    }

    public Boolean isDefaultCheckFlag() {
        return defaultCheckFlag;
    }

    public void setDefaultCheckFlag(Boolean defaultCheckFlag) {
        this.defaultCheckFlag = defaultCheckFlag;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
