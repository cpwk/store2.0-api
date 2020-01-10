package com.maidao.edu.store.api.user.model;

import javax.persistence.*;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-09 10:30
 * Version 1.8.0_211
 * 项目名称：homework
 * 类名称:UserSession
 * 类描述:用户会话实体类
 **/

@Entity
@Table(name = "user_session")
public class UserSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column
    private String token;
    @Column(name = "signin_at")
    private Long signInAt;
    @Column(name = "expire_at")
    private Long expireAt;
    @Column
    private String ip;
    @Column
    private String location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getSignInAt() {
        return signInAt;
    }

    public void setSignInAt(Long signInAt) {
        this.signInAt = signInAt;
    }

    public Long getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Long expireAt) {
        this.expireAt = expireAt;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
