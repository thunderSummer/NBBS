package com.jvmup.nbbs.po;

import org.apache.ibatis.type.Alias;

import java.util.Date;
import java.util.List;

/**帖子 基类
 * ProjectName: NBBS
 *
 * @author xxl
 * <p>
 * Created by xxl on - 2018-07-14 21:19
 **/
@Alias("article")
public class Post extends BasePo {
    private int id;
    private User user;
    private String title;
    private String content;
    private Date createTIme;
    private int view;
    private int like;
    private int boardId;
    private List<Comment> commentList;
}
