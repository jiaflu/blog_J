package com.ljf.blog.pojo;

public class User {
    private Integer uid;

    private String username;

    private String password;

    private String email;

    private String home_url;

    private String screen_name;

    private Integer created;

    private Integer activated;

    private Integer logged;

    private String group_name;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getHome_url() {
        return home_url;
    }

    public void setHome_url(String home_url) {
        this.home_url = home_url == null ? null : home_url.trim();
    }

    public String getScreen_name() {
        return screen_name;
    }

    public void setScreen_name(String screen_name) {
        this.screen_name = screen_name == null ? null : screen_name.trim();
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getActivated() {
        return activated;
    }

    public void setActivated(Integer activated) {
        this.activated = activated;
    }

    public Integer getLogged() {
        return logged;
    }

    public void setLogged(Integer logged) {
        this.logged = logged;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name == null ? null : group_name.trim();
    }
}