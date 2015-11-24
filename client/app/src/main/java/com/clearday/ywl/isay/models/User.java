package com.clearday.ywl.isay.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by clearday on 15/11/25.
 */

public class User implements Serializable {
    private int id;                     // 用户UID
    private String idStr;                  // 字符串型的用户UID
    private String nickName;            // 用户昵称
    private int province;               // 用户所在省级ID
    private int city;                   // 用户所在城市ID
    private String location;               // 用户所在地
    private String signature;            // 用户个人描述
    private String avatarUrl;      // 用户头像地址（中图），50×50像素
    private String domain;                 // 用户的个性化域名
    private String gender;                 // 性别，m：男、f：女、n：未知
    private int followedCount;        // 粉丝数
    private int followingCount;          // 关注数
    private int isayCount;         // 微博数
    private int favouriteCount;       // 收藏数
    private String createdAt;             // 用户创建（注册）时间
    // private     Status      status;                 // 用户的最近一条微博信息字段
    private boolean allowComment;      // 是否允许所有人对我的微博进行评论，true：是，false：否
    private String avatarLargeUrl;           // 用户头像地址（大图），180×180像素
    private String avatarHdUrl;              // 用户头像地址（高清），高清头像原图
    private int onlineStatus;          // 用户的在线状态，0：不在线、1：在线
    private int biFollowerCount;     // 用户的互粉数
    private String lang;                   // 用户当前的语言版本，zh-cn：简体中文，zh-tw：繁体中文，en：英语

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }


    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getFollowedCount() {
        return followedCount;
    }

    public void setFollowedCount(int followedCount) {
        this.followedCount = followedCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }

    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }

    public int getIsayCount() {
        return isayCount;
    }

    public void setIsayCount(int isayCount) {
        this.isayCount = isayCount;
    }

    public int getFavouriteCount() {
        return favouriteCount;
    }

    public void setFavouriteCount(int favouriteCount) {
        this.favouriteCount = favouriteCount;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }


//    public Status getStatus() {
//        return status;
//    }
//
//    public void setStatus(Status status) {
//        this.status = status;
//    }

    public boolean isAllowComment() {
        return allowComment;
    }

    public void setAllowComment(boolean allowComment) {
        this.allowComment = allowComment;
    }

    public String getAvatarLargeUrl() {
        return avatarLargeUrl;
    }

    public void setAvatarLargeUrl(String avatarLargeUrl) {
        this.avatarLargeUrl = avatarLargeUrl;
    }

    public String getAvatarHdUrl() {
        return avatarHdUrl;
    }

    public void setAvatarHdUrl(String avatarHdUrl) {
        this.avatarHdUrl = avatarHdUrl;
    }

    public int getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public int getBiFollowerCount() {
        return biFollowerCount;
    }

    public void setBiFollowerCount(int biFollowerCount) {
        this.biFollowerCount = biFollowerCount;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public User parseJson(JSONObject jsonObject) throws JSONException {
        if (jsonObject != null) {
            User user = new User();
            user.setId(jsonObject.optInt("id"));
            user.setIdStr(jsonObject.optString("idStr"));
            user.setNickName(jsonObject.optString("nickName"));
            user.setProvince(jsonObject.optInt("province"));
            user.setCity(jsonObject.optInt("city"));
            user.setLocation(jsonObject.optString("location"));
            user.setSignature(jsonObject.optString("signature"));
            user.setAvatarUrl(jsonObject.optString("avatarUrl"));
            user.setDomain(jsonObject.optString("domain"));
            user.setGender(jsonObject.optString("gender"));
            user.setFollowedCount(jsonObject.optInt("followedCount"));
            user.setFollowingCount(jsonObject.optInt("followingCount"));
            user.setIsayCount(jsonObject.optInt("isayCount"));
            user.setFavouriteCount(jsonObject.optInt("favouriteCount"));
            user.setCreatedAt(jsonObject.optString("createdAt"));
            //  user.setStatus(new Status().parseJson((JSONObject) jsonObject.opt("status")));
            user.setAllowComment(jsonObject.optBoolean("allowComment"));
            user.setAvatarLargeUrl(jsonObject.optString("avatarLargeUrl"));
            user.setAvatarHdUrl(jsonObject.optString("avatarHdUrl"));
            user.setOnlineStatus(jsonObject.optInt("onlineStatus"));
            user.setBiFollowerCount(jsonObject.optInt("biFollowerCount"));
            user.setLang(jsonObject.optString("lang"));

            return user;
        }
        return null;
    }
}
