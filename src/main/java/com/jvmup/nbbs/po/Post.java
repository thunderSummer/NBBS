package com.jvmup.nbbs.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Alias("post")
public class Post extends BasePo {
    private int id;
    private User user;
    private String title;
    private String content;
    private int type;
    private int topping;
    @JsonFormat(pattern = "yyyy-MM-dd-hh-mm")
    private Date createTime;
    private Date lastModify;
    private int view;
    private Section section;
    private String brief;
    private List<Comment> commentList;
    private List<User> likeList;
    private List<User> unlikeList;
    private List<User> reportList;
    private List<User> collectionList;
    private int hot;
    private int commentNum;


    public void setSection(Section section) {
        this.section = section;
    }

    public Section getSection() {
        return section;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
    @JsonFormat(pattern = "yyyy-MM-dd-hh-mm")
    public Date getCreateTime() {
        return createTime;
    }
    @JsonFormat(pattern = "yyyy-MM-dd-hh-mm")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBrief() {
        return brief;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

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

    @JsonFormat(pattern = "yyyy-MM-dd-hh-mm")
    public Date getLastModify() {
        return lastModify;
    }
    @JsonFormat(pattern = "yyyy-MM-dd-hh-mm")
    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }
}
