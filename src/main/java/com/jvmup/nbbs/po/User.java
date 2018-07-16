package com.jvmup.nbbs.po;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.ibatis.type.Alias;


import java.util.Date;
import java.util.List;

/**
 * ProjectName: NBBS
 *
 * @author xxl
 *
 * Created by xxl on 2018-07-14 13:51
 **/
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Alias("user")
public class User extends BasePo {
    private int id;
    private String nickname;
    private String email;
    private String password;
    private String signature;
    private int ex;
    private Sex sex;
    private Date createTime;
    private String phone;
    private int status;
    private String avatar;
    @JsonProperty("fans")
    private List<User> fanList;
    private List<Post> likeList;
    private List<User> blackList;
    private List<Post> reportList;
    private List<Post> collectionList;
    private List<Post> unlikeList;
    private List<String> maskWordList;
    private List<String> postList;
    private List<User> followList;

    public List<User> getFollowList() {
        return followList;
    }

    public void setFollowList(List<User> followList) {
        this.followList = followList;
    }

    @JsonProperty("fans")
    public List<User> getFanList() {
        return fanList;
    }
    @JsonProperty("fans")
    public void setFanList(List<User> fanList) {
        this.fanList = fanList;
    }
    public List<Post> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<Post> likeList) {
        this.likeList = likeList;
    }

    public List<User> getBlackList() {
        return blackList;
    }

    public void setBlackList(List<User> blackList) {
        this.blackList = blackList;
    }

    public List<Post> getReportList() {
        return reportList;
    }

    public void setReportList(List<Post> reportList) {
        this.reportList = reportList;
    }

    public List<Post> getCollectionList() {
        return collectionList;
    }

    public void setCollectionList(List<Post> collectionList) {
        this.collectionList = collectionList;
    }

    public List<Post> getUnlikeList() {
        return unlikeList;
    }

    public void setUnlikeList(List<Post> unlikeList) {
        this.unlikeList = unlikeList;
    }

    public List<String> getMaskWordList() {
        return maskWordList;
    }

    public void setMaskWordList(List<String> maskWordList) {
        this.maskWordList = maskWordList;
    }

    public List<String> getPostList() {
        return postList;
    }

    public void setPostList(List<String> postList) {
        this.postList = postList;
    }

    public User(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getEx() {
        return ex;
    }

    public void setEx(int ex) {
        this.ex = ex;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
