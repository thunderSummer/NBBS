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
@Alias("post")
public class Post extends BasePo {
    private int id;
    private User user;
    private String title;
    private String content;
    private int type;
    private int topping;
    private Date createTIme;
    private int view;
    private int sectionId;
    private List<Comment> commentList;
    private List<User> likeList;
    private List<User> unlikeList;
    private List<User> reportList;
    private List<User> collectionList;

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTIme() {
        return createTIme;
    }

    public void setCreateTIme(Date createTIme) {
        this.createTIme = createTIme;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTopping() {
        return topping;
    }

    public void setTopping(int topping) {
        this.topping = topping;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<User> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<User> likeList) {
        this.likeList = likeList;
    }

    public List<User> getUnlikeList() {
        return unlikeList;
    }

    public void setUnlikeList(List<User> unlikeList) {
        this.unlikeList = unlikeList;
    }

    public List<User> getReportList() {
        return reportList;
    }

    public void setReportList(List<User> reportList) {
        this.reportList = reportList;
    }

    public List<User> getCollectionList() {
        return collectionList;
    }

    public void setCollectionList(List<User> collectionList) {
        this.collectionList = collectionList;
    }

}
