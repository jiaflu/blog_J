package com.ljf.blog.pojo;

public class Content {
    private Integer cid;

    private String title;

    private String slug;

    private Integer created;

    private Integer modified;

    private Integer author_id;

    private String type;

    private String status;

    private String tags;

    private String categories;

    private String thumbImg;

    private Integer hits;

    private Integer comments_num;

    private Boolean allow_comment;

    private Boolean allow_ping;

    private Boolean allow_feed;

    private String content;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug == null ? null : slug.trim();
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getModified() {
        return modified;
    }

    public void setModified(Integer modified) {
        this.modified = modified;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags == null ? null : tags.trim();
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories == null ? null : categories.trim();
    }

    public String getThumbImg() {
        return thumbImg;
    }

    public void setThumbImg(String thumbImg) {
        this.thumbImg = thumbImg == null ? null : thumbImg.trim();
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public Integer getComments_num() {
        return comments_num;
    }

    public void setComments_num(Integer comments_num) {
        this.comments_num = comments_num;
    }

    public Boolean getAllow_comment() {
        return allow_comment;
    }

    public void setAllow_comment(Boolean allow_comment) {
        this.allow_comment = allow_comment;
    }

    public Boolean getAllow_ping() {
        return allow_ping;
    }

    public void setAllow_ping(Boolean allow_ping) {
        this.allow_ping = allow_ping;
    }

    public Boolean getAllow_feed() {
        return allow_feed;
    }

    public void setAllow_feed(Boolean allow_feed) {
        this.allow_feed = allow_feed;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}