package com.ljf.blog.pojo;

import java.util.ArrayList;
import java.util.List;

public class ContentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ContentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andCidIsNull() {
            addCriterion("cid is null");
            return (Criteria) this;
        }

        public Criteria andCidIsNotNull() {
            addCriterion("cid is not null");
            return (Criteria) this;
        }

        public Criteria andCidEqualTo(Integer value) {
            addCriterion("cid =", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotEqualTo(Integer value) {
            addCriterion("cid <>", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThan(Integer value) {
            addCriterion("cid >", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThanOrEqualTo(Integer value) {
            addCriterion("cid >=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThan(Integer value) {
            addCriterion("cid <", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThanOrEqualTo(Integer value) {
            addCriterion("cid <=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidIn(List<Integer> values) {
            addCriterion("cid in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotIn(List<Integer> values) {
            addCriterion("cid not in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidBetween(Integer value1, Integer value2) {
            addCriterion("cid between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotBetween(Integer value1, Integer value2) {
            addCriterion("cid not between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andSlugIsNull() {
            addCriterion("slug is null");
            return (Criteria) this;
        }

        public Criteria andSlugIsNotNull() {
            addCriterion("slug is not null");
            return (Criteria) this;
        }

        public Criteria andSlugEqualTo(String value) {
            addCriterion("slug =", value, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugNotEqualTo(String value) {
            addCriterion("slug <>", value, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugGreaterThan(String value) {
            addCriterion("slug >", value, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugGreaterThanOrEqualTo(String value) {
            addCriterion("slug >=", value, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugLessThan(String value) {
            addCriterion("slug <", value, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugLessThanOrEqualTo(String value) {
            addCriterion("slug <=", value, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugLike(String value) {
            addCriterion("slug like", value, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugNotLike(String value) {
            addCriterion("slug not like", value, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugIn(List<String> values) {
            addCriterion("slug in", values, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugNotIn(List<String> values) {
            addCriterion("slug not in", values, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugBetween(String value1, String value2) {
            addCriterion("slug between", value1, value2, "slug");
            return (Criteria) this;
        }

        public Criteria andSlugNotBetween(String value1, String value2) {
            addCriterion("slug not between", value1, value2, "slug");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNull() {
            addCriterion("created is null");
            return (Criteria) this;
        }

        public Criteria andCreatedIsNotNull() {
            addCriterion("created is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedEqualTo(Integer value) {
            addCriterion("created =", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotEqualTo(Integer value) {
            addCriterion("created <>", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThan(Integer value) {
            addCriterion("created >", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedGreaterThanOrEqualTo(Integer value) {
            addCriterion("created >=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThan(Integer value) {
            addCriterion("created <", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedLessThanOrEqualTo(Integer value) {
            addCriterion("created <=", value, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedIn(List<Integer> values) {
            addCriterion("created in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotIn(List<Integer> values) {
            addCriterion("created not in", values, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedBetween(Integer value1, Integer value2) {
            addCriterion("created between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andCreatedNotBetween(Integer value1, Integer value2) {
            addCriterion("created not between", value1, value2, "created");
            return (Criteria) this;
        }

        public Criteria andModifiedIsNull() {
            addCriterion("modified is null");
            return (Criteria) this;
        }

        public Criteria andModifiedIsNotNull() {
            addCriterion("modified is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedEqualTo(Integer value) {
            addCriterion("modified =", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotEqualTo(Integer value) {
            addCriterion("modified <>", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedGreaterThan(Integer value) {
            addCriterion("modified >", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedGreaterThanOrEqualTo(Integer value) {
            addCriterion("modified >=", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedLessThan(Integer value) {
            addCriterion("modified <", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedLessThanOrEqualTo(Integer value) {
            addCriterion("modified <=", value, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedIn(List<Integer> values) {
            addCriterion("modified in", values, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotIn(List<Integer> values) {
            addCriterion("modified not in", values, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedBetween(Integer value1, Integer value2) {
            addCriterion("modified between", value1, value2, "modified");
            return (Criteria) this;
        }

        public Criteria andModifiedNotBetween(Integer value1, Integer value2) {
            addCriterion("modified not between", value1, value2, "modified");
            return (Criteria) this;
        }

        public Criteria andAuthor_idIsNull() {
            addCriterion("author_id is null");
            return (Criteria) this;
        }

        public Criteria andAuthor_idIsNotNull() {
            addCriterion("author_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuthor_idEqualTo(Integer value) {
            addCriterion("author_id =", value, "author_id");
            return (Criteria) this;
        }

        public Criteria andAuthor_idNotEqualTo(Integer value) {
            addCriterion("author_id <>", value, "author_id");
            return (Criteria) this;
        }

        public Criteria andAuthor_idGreaterThan(Integer value) {
            addCriterion("author_id >", value, "author_id");
            return (Criteria) this;
        }

        public Criteria andAuthor_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("author_id >=", value, "author_id");
            return (Criteria) this;
        }

        public Criteria andAuthor_idLessThan(Integer value) {
            addCriterion("author_id <", value, "author_id");
            return (Criteria) this;
        }

        public Criteria andAuthor_idLessThanOrEqualTo(Integer value) {
            addCriterion("author_id <=", value, "author_id");
            return (Criteria) this;
        }

        public Criteria andAuthor_idIn(List<Integer> values) {
            addCriterion("author_id in", values, "author_id");
            return (Criteria) this;
        }

        public Criteria andAuthor_idNotIn(List<Integer> values) {
            addCriterion("author_id not in", values, "author_id");
            return (Criteria) this;
        }

        public Criteria andAuthor_idBetween(Integer value1, Integer value2) {
            addCriterion("author_id between", value1, value2, "author_id");
            return (Criteria) this;
        }

        public Criteria andAuthor_idNotBetween(Integer value1, Integer value2) {
            addCriterion("author_id not between", value1, value2, "author_id");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTagsIsNull() {
            addCriterion("tags is null");
            return (Criteria) this;
        }

        public Criteria andTagsIsNotNull() {
            addCriterion("tags is not null");
            return (Criteria) this;
        }

        public Criteria andTagsEqualTo(String value) {
            addCriterion("tags =", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotEqualTo(String value) {
            addCriterion("tags <>", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThan(String value) {
            addCriterion("tags >", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsGreaterThanOrEqualTo(String value) {
            addCriterion("tags >=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThan(String value) {
            addCriterion("tags <", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLessThanOrEqualTo(String value) {
            addCriterion("tags <=", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsLike(String value) {
            addCriterion("tags like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotLike(String value) {
            addCriterion("tags not like", value, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsIn(List<String> values) {
            addCriterion("tags in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotIn(List<String> values) {
            addCriterion("tags not in", values, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsBetween(String value1, String value2) {
            addCriterion("tags between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andTagsNotBetween(String value1, String value2) {
            addCriterion("tags not between", value1, value2, "tags");
            return (Criteria) this;
        }

        public Criteria andCategoriesIsNull() {
            addCriterion("categories is null");
            return (Criteria) this;
        }

        public Criteria andCategoriesIsNotNull() {
            addCriterion("categories is not null");
            return (Criteria) this;
        }

        public Criteria andCategoriesEqualTo(String value) {
            addCriterion("categories =", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesNotEqualTo(String value) {
            addCriterion("categories <>", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesGreaterThan(String value) {
            addCriterion("categories >", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesGreaterThanOrEqualTo(String value) {
            addCriterion("categories >=", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesLessThan(String value) {
            addCriterion("categories <", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesLessThanOrEqualTo(String value) {
            addCriterion("categories <=", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesLike(String value) {
            addCriterion("categories like", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesNotLike(String value) {
            addCriterion("categories not like", value, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesIn(List<String> values) {
            addCriterion("categories in", values, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesNotIn(List<String> values) {
            addCriterion("categories not in", values, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesBetween(String value1, String value2) {
            addCriterion("categories between", value1, value2, "categories");
            return (Criteria) this;
        }

        public Criteria andCategoriesNotBetween(String value1, String value2) {
            addCriterion("categories not between", value1, value2, "categories");
            return (Criteria) this;
        }

        public Criteria andThumbImgIsNull() {
            addCriterion("thumbImg is null");
            return (Criteria) this;
        }

        public Criteria andThumbImgIsNotNull() {
            addCriterion("thumbImg is not null");
            return (Criteria) this;
        }

        public Criteria andThumbImgEqualTo(String value) {
            addCriterion("thumbImg =", value, "thumbImg");
            return (Criteria) this;
        }

        public Criteria andThumbImgNotEqualTo(String value) {
            addCriterion("thumbImg <>", value, "thumbImg");
            return (Criteria) this;
        }

        public Criteria andThumbImgGreaterThan(String value) {
            addCriterion("thumbImg >", value, "thumbImg");
            return (Criteria) this;
        }

        public Criteria andThumbImgGreaterThanOrEqualTo(String value) {
            addCriterion("thumbImg >=", value, "thumbImg");
            return (Criteria) this;
        }

        public Criteria andThumbImgLessThan(String value) {
            addCriterion("thumbImg <", value, "thumbImg");
            return (Criteria) this;
        }

        public Criteria andThumbImgLessThanOrEqualTo(String value) {
            addCriterion("thumbImg <=", value, "thumbImg");
            return (Criteria) this;
        }

        public Criteria andThumbImgLike(String value) {
            addCriterion("thumbImg like", value, "thumbImg");
            return (Criteria) this;
        }

        public Criteria andThumbImgNotLike(String value) {
            addCriterion("thumbImg not like", value, "thumbImg");
            return (Criteria) this;
        }

        public Criteria andThumbImgIn(List<String> values) {
            addCriterion("thumbImg in", values, "thumbImg");
            return (Criteria) this;
        }

        public Criteria andThumbImgNotIn(List<String> values) {
            addCriterion("thumbImg not in", values, "thumbImg");
            return (Criteria) this;
        }

        public Criteria andThumbImgBetween(String value1, String value2) {
            addCriterion("thumbImg between", value1, value2, "thumbImg");
            return (Criteria) this;
        }

        public Criteria andThumbImgNotBetween(String value1, String value2) {
            addCriterion("thumbImg not between", value1, value2, "thumbImg");
            return (Criteria) this;
        }

        public Criteria andHitsIsNull() {
            addCriterion("hits is null");
            return (Criteria) this;
        }

        public Criteria andHitsIsNotNull() {
            addCriterion("hits is not null");
            return (Criteria) this;
        }

        public Criteria andHitsEqualTo(Integer value) {
            addCriterion("hits =", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsNotEqualTo(Integer value) {
            addCriterion("hits <>", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsGreaterThan(Integer value) {
            addCriterion("hits >", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsGreaterThanOrEqualTo(Integer value) {
            addCriterion("hits >=", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsLessThan(Integer value) {
            addCriterion("hits <", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsLessThanOrEqualTo(Integer value) {
            addCriterion("hits <=", value, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsIn(List<Integer> values) {
            addCriterion("hits in", values, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsNotIn(List<Integer> values) {
            addCriterion("hits not in", values, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsBetween(Integer value1, Integer value2) {
            addCriterion("hits between", value1, value2, "hits");
            return (Criteria) this;
        }

        public Criteria andHitsNotBetween(Integer value1, Integer value2) {
            addCriterion("hits not between", value1, value2, "hits");
            return (Criteria) this;
        }

        public Criteria andComments_numIsNull() {
            addCriterion("comments_num is null");
            return (Criteria) this;
        }

        public Criteria andComments_numIsNotNull() {
            addCriterion("comments_num is not null");
            return (Criteria) this;
        }

        public Criteria andComments_numEqualTo(Integer value) {
            addCriterion("comments_num =", value, "comments_num");
            return (Criteria) this;
        }

        public Criteria andComments_numNotEqualTo(Integer value) {
            addCriterion("comments_num <>", value, "comments_num");
            return (Criteria) this;
        }

        public Criteria andComments_numGreaterThan(Integer value) {
            addCriterion("comments_num >", value, "comments_num");
            return (Criteria) this;
        }

        public Criteria andComments_numGreaterThanOrEqualTo(Integer value) {
            addCriterion("comments_num >=", value, "comments_num");
            return (Criteria) this;
        }

        public Criteria andComments_numLessThan(Integer value) {
            addCriterion("comments_num <", value, "comments_num");
            return (Criteria) this;
        }

        public Criteria andComments_numLessThanOrEqualTo(Integer value) {
            addCriterion("comments_num <=", value, "comments_num");
            return (Criteria) this;
        }

        public Criteria andComments_numIn(List<Integer> values) {
            addCriterion("comments_num in", values, "comments_num");
            return (Criteria) this;
        }

        public Criteria andComments_numNotIn(List<Integer> values) {
            addCriterion("comments_num not in", values, "comments_num");
            return (Criteria) this;
        }

        public Criteria andComments_numBetween(Integer value1, Integer value2) {
            addCriterion("comments_num between", value1, value2, "comments_num");
            return (Criteria) this;
        }

        public Criteria andComments_numNotBetween(Integer value1, Integer value2) {
            addCriterion("comments_num not between", value1, value2, "comments_num");
            return (Criteria) this;
        }

        public Criteria andAllow_commentIsNull() {
            addCriterion("allow_comment is null");
            return (Criteria) this;
        }

        public Criteria andAllow_commentIsNotNull() {
            addCriterion("allow_comment is not null");
            return (Criteria) this;
        }

        public Criteria andAllow_commentEqualTo(Boolean value) {
            addCriterion("allow_comment =", value, "allow_comment");
            return (Criteria) this;
        }

        public Criteria andAllow_commentNotEqualTo(Boolean value) {
            addCriterion("allow_comment <>", value, "allow_comment");
            return (Criteria) this;
        }

        public Criteria andAllow_commentGreaterThan(Boolean value) {
            addCriterion("allow_comment >", value, "allow_comment");
            return (Criteria) this;
        }

        public Criteria andAllow_commentGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allow_comment >=", value, "allow_comment");
            return (Criteria) this;
        }

        public Criteria andAllow_commentLessThan(Boolean value) {
            addCriterion("allow_comment <", value, "allow_comment");
            return (Criteria) this;
        }

        public Criteria andAllow_commentLessThanOrEqualTo(Boolean value) {
            addCriterion("allow_comment <=", value, "allow_comment");
            return (Criteria) this;
        }

        public Criteria andAllow_commentIn(List<Boolean> values) {
            addCriterion("allow_comment in", values, "allow_comment");
            return (Criteria) this;
        }

        public Criteria andAllow_commentNotIn(List<Boolean> values) {
            addCriterion("allow_comment not in", values, "allow_comment");
            return (Criteria) this;
        }

        public Criteria andAllow_commentBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_comment between", value1, value2, "allow_comment");
            return (Criteria) this;
        }

        public Criteria andAllow_commentNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_comment not between", value1, value2, "allow_comment");
            return (Criteria) this;
        }

        public Criteria andAllow_pingIsNull() {
            addCriterion("allow_ping is null");
            return (Criteria) this;
        }

        public Criteria andAllow_pingIsNotNull() {
            addCriterion("allow_ping is not null");
            return (Criteria) this;
        }

        public Criteria andAllow_pingEqualTo(Boolean value) {
            addCriterion("allow_ping =", value, "allow_ping");
            return (Criteria) this;
        }

        public Criteria andAllow_pingNotEqualTo(Boolean value) {
            addCriterion("allow_ping <>", value, "allow_ping");
            return (Criteria) this;
        }

        public Criteria andAllow_pingGreaterThan(Boolean value) {
            addCriterion("allow_ping >", value, "allow_ping");
            return (Criteria) this;
        }

        public Criteria andAllow_pingGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allow_ping >=", value, "allow_ping");
            return (Criteria) this;
        }

        public Criteria andAllow_pingLessThan(Boolean value) {
            addCriterion("allow_ping <", value, "allow_ping");
            return (Criteria) this;
        }

        public Criteria andAllow_pingLessThanOrEqualTo(Boolean value) {
            addCriterion("allow_ping <=", value, "allow_ping");
            return (Criteria) this;
        }

        public Criteria andAllow_pingIn(List<Boolean> values) {
            addCriterion("allow_ping in", values, "allow_ping");
            return (Criteria) this;
        }

        public Criteria andAllow_pingNotIn(List<Boolean> values) {
            addCriterion("allow_ping not in", values, "allow_ping");
            return (Criteria) this;
        }

        public Criteria andAllow_pingBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_ping between", value1, value2, "allow_ping");
            return (Criteria) this;
        }

        public Criteria andAllow_pingNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_ping not between", value1, value2, "allow_ping");
            return (Criteria) this;
        }

        public Criteria andAllow_feedIsNull() {
            addCriterion("allow_feed is null");
            return (Criteria) this;
        }

        public Criteria andAllow_feedIsNotNull() {
            addCriterion("allow_feed is not null");
            return (Criteria) this;
        }

        public Criteria andAllow_feedEqualTo(Boolean value) {
            addCriterion("allow_feed =", value, "allow_feed");
            return (Criteria) this;
        }

        public Criteria andAllow_feedNotEqualTo(Boolean value) {
            addCriterion("allow_feed <>", value, "allow_feed");
            return (Criteria) this;
        }

        public Criteria andAllow_feedGreaterThan(Boolean value) {
            addCriterion("allow_feed >", value, "allow_feed");
            return (Criteria) this;
        }

        public Criteria andAllow_feedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("allow_feed >=", value, "allow_feed");
            return (Criteria) this;
        }

        public Criteria andAllow_feedLessThan(Boolean value) {
            addCriterion("allow_feed <", value, "allow_feed");
            return (Criteria) this;
        }

        public Criteria andAllow_feedLessThanOrEqualTo(Boolean value) {
            addCriterion("allow_feed <=", value, "allow_feed");
            return (Criteria) this;
        }

        public Criteria andAllow_feedIn(List<Boolean> values) {
            addCriterion("allow_feed in", values, "allow_feed");
            return (Criteria) this;
        }

        public Criteria andAllow_feedNotIn(List<Boolean> values) {
            addCriterion("allow_feed not in", values, "allow_feed");
            return (Criteria) this;
        }

        public Criteria andAllow_feedBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_feed between", value1, value2, "allow_feed");
            return (Criteria) this;
        }

        public Criteria andAllow_feedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("allow_feed not between", value1, value2, "allow_feed");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}