package com.orange.heart.entity;

import com.orange.commons.utils.PageInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RCrudColumnExample extends PageInfo {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RCrudColumnExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andKeywordIsNull() {
            addCriterion("keyword is null");
            return (Criteria) this;
        }

        public Criteria andKeywordIsNotNull() {
            addCriterion("keyword is not null");
            return (Criteria) this;
        }

        public Criteria andKeywordEqualTo(String value) {
            addCriterion("keyword =", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordNotEqualTo(String value) {
            addCriterion("keyword <>", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordGreaterThan(String value) {
            addCriterion("keyword >", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordGreaterThanOrEqualTo(String value) {
            addCriterion("keyword >=", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordLessThan(String value) {
            addCriterion("keyword <", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordLessThanOrEqualTo(String value) {
            addCriterion("keyword <=", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordLike(String value) {
            addCriterion("keyword like", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordNotLike(String value) {
            addCriterion("keyword not like", value, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordIn(List<String> values) {
            addCriterion("keyword in", values, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordNotIn(List<String> values) {
            addCriterion("keyword not in", values, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordBetween(String value1, String value2) {
            addCriterion("keyword between", value1, value2, "keyword");
            return (Criteria) this;
        }

        public Criteria andKeywordNotBetween(String value1, String value2) {
            addCriterion("keyword not between", value1, value2, "keyword");
            return (Criteria) this;
        }

        public Criteria andColumnNameIsNull() {
            addCriterion("`column_name` is null");
            return (Criteria) this;
        }

        public Criteria andColumnNameIsNotNull() {
            addCriterion("`column_name` is not null");
            return (Criteria) this;
        }

        public Criteria andColumnNameEqualTo(String value) {
            addCriterion("`column_name` =", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotEqualTo(String value) {
            addCriterion("`column_name` <>", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameGreaterThan(String value) {
            addCriterion("`column_name` >", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameGreaterThanOrEqualTo(String value) {
            addCriterion("`column_name` >=", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLessThan(String value) {
            addCriterion("`column_name` <", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLessThanOrEqualTo(String value) {
            addCriterion("`column_name` <=", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLike(String value) {
            addCriterion("`column_name` like", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotLike(String value) {
            addCriterion("`column_name` not like", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameIn(List<String> values) {
            addCriterion("`column_name` in", values, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotIn(List<String> values) {
            addCriterion("`column_name` not in", values, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameBetween(String value1, String value2) {
            addCriterion("`column_name` between", value1, value2, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotBetween(String value1, String value2) {
            addCriterion("`column_name` not between", value1, value2, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnTypeIsNull() {
            addCriterion("column_type is null");
            return (Criteria) this;
        }

        public Criteria andColumnTypeIsNotNull() {
            addCriterion("column_type is not null");
            return (Criteria) this;
        }

        public Criteria andColumnTypeEqualTo(Integer value) {
            addCriterion("column_type =", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeNotEqualTo(Integer value) {
            addCriterion("column_type <>", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeGreaterThan(Integer value) {
            addCriterion("column_type >", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("column_type >=", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeLessThan(Integer value) {
            addCriterion("column_type <", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeLessThanOrEqualTo(Integer value) {
            addCriterion("column_type <=", value, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeIn(List<Integer> values) {
            addCriterion("column_type in", values, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeNotIn(List<Integer> values) {
            addCriterion("column_type not in", values, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeBetween(Integer value1, Integer value2) {
            addCriterion("column_type between", value1, value2, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("column_type not between", value1, value2, "columnType");
            return (Criteria) this;
        }

        public Criteria andColumnIdIsNull() {
            addCriterion("column_id is null");
            return (Criteria) this;
        }

        public Criteria andColumnIdIsNotNull() {
            addCriterion("column_id is not null");
            return (Criteria) this;
        }

        public Criteria andColumnIdEqualTo(String value) {
            addCriterion("column_id =", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotEqualTo(String value) {
            addCriterion("column_id <>", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdGreaterThan(String value) {
            addCriterion("column_id >", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdGreaterThanOrEqualTo(String value) {
            addCriterion("column_id >=", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdLessThan(String value) {
            addCriterion("column_id <", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdLessThanOrEqualTo(String value) {
            addCriterion("column_id <=", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdLike(String value) {
            addCriterion("column_id like", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotLike(String value) {
            addCriterion("column_id not like", value, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdIn(List<String> values) {
            addCriterion("column_id in", values, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotIn(List<String> values) {
            addCriterion("column_id not in", values, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdBetween(String value1, String value2) {
            addCriterion("column_id between", value1, value2, "columnId");
            return (Criteria) this;
        }

        public Criteria andColumnIdNotBetween(String value1, String value2) {
            addCriterion("column_id not between", value1, value2, "columnId");
            return (Criteria) this;
        }

        public Criteria andDictInfoIsNull() {
            addCriterion("dict_info is null");
            return (Criteria) this;
        }

        public Criteria andDictInfoIsNotNull() {
            addCriterion("dict_info is not null");
            return (Criteria) this;
        }

        public Criteria andDictInfoEqualTo(String value) {
            addCriterion("dict_info =", value, "dictInfo");
            return (Criteria) this;
        }

        public Criteria andDictInfoNotEqualTo(String value) {
            addCriterion("dict_info <>", value, "dictInfo");
            return (Criteria) this;
        }

        public Criteria andDictInfoGreaterThan(String value) {
            addCriterion("dict_info >", value, "dictInfo");
            return (Criteria) this;
        }

        public Criteria andDictInfoGreaterThanOrEqualTo(String value) {
            addCriterion("dict_info >=", value, "dictInfo");
            return (Criteria) this;
        }

        public Criteria andDictInfoLessThan(String value) {
            addCriterion("dict_info <", value, "dictInfo");
            return (Criteria) this;
        }

        public Criteria andDictInfoLessThanOrEqualTo(String value) {
            addCriterion("dict_info <=", value, "dictInfo");
            return (Criteria) this;
        }

        public Criteria andDictInfoLike(String value) {
            addCriterion("dict_info like", value, "dictInfo");
            return (Criteria) this;
        }

        public Criteria andDictInfoNotLike(String value) {
            addCriterion("dict_info not like", value, "dictInfo");
            return (Criteria) this;
        }

        public Criteria andDictInfoIn(List<String> values) {
            addCriterion("dict_info in", values, "dictInfo");
            return (Criteria) this;
        }

        public Criteria andDictInfoNotIn(List<String> values) {
            addCriterion("dict_info not in", values, "dictInfo");
            return (Criteria) this;
        }

        public Criteria andDictInfoBetween(String value1, String value2) {
            addCriterion("dict_info between", value1, value2, "dictInfo");
            return (Criteria) this;
        }

        public Criteria andDictInfoNotBetween(String value1, String value2) {
            addCriterion("dict_info not between", value1, value2, "dictInfo");
            return (Criteria) this;
        }

        public Criteria andRequiredIsNull() {
            addCriterion("required is null");
            return (Criteria) this;
        }

        public Criteria andRequiredIsNotNull() {
            addCriterion("required is not null");
            return (Criteria) this;
        }

        public Criteria andRequiredEqualTo(Integer value) {
            addCriterion("required =", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotEqualTo(Integer value) {
            addCriterion("required <>", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredGreaterThan(Integer value) {
            addCriterion("required >", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredGreaterThanOrEqualTo(Integer value) {
            addCriterion("required >=", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredLessThan(Integer value) {
            addCriterion("required <", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredLessThanOrEqualTo(Integer value) {
            addCriterion("required <=", value, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredIn(List<Integer> values) {
            addCriterion("required in", values, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotIn(List<Integer> values) {
            addCriterion("required not in", values, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredBetween(Integer value1, Integer value2) {
            addCriterion("required between", value1, value2, "required");
            return (Criteria) this;
        }

        public Criteria andRequiredNotBetween(Integer value1, Integer value2) {
            addCriterion("required not between", value1, value2, "required");
            return (Criteria) this;
        }

        public Criteria andDataFormatIsNull() {
            addCriterion("data_format is null");
            return (Criteria) this;
        }

        public Criteria andDataFormatIsNotNull() {
            addCriterion("data_format is not null");
            return (Criteria) this;
        }

        public Criteria andDataFormatEqualTo(String value) {
            addCriterion("data_format =", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatNotEqualTo(String value) {
            addCriterion("data_format <>", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatGreaterThan(String value) {
            addCriterion("data_format >", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatGreaterThanOrEqualTo(String value) {
            addCriterion("data_format >=", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatLessThan(String value) {
            addCriterion("data_format <", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatLessThanOrEqualTo(String value) {
            addCriterion("data_format <=", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatLike(String value) {
            addCriterion("data_format like", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatNotLike(String value) {
            addCriterion("data_format not like", value, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatIn(List<String> values) {
            addCriterion("data_format in", values, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatNotIn(List<String> values) {
            addCriterion("data_format not in", values, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatBetween(String value1, String value2) {
            addCriterion("data_format between", value1, value2, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andDataFormatNotBetween(String value1, String value2) {
            addCriterion("data_format not between", value1, value2, "dataFormat");
            return (Criteria) this;
        }

        public Criteria andRegularExpressIsNull() {
            addCriterion("regular_express is null");
            return (Criteria) this;
        }

        public Criteria andRegularExpressIsNotNull() {
            addCriterion("regular_express is not null");
            return (Criteria) this;
        }

        public Criteria andRegularExpressEqualTo(String value) {
            addCriterion("regular_express =", value, "regularExpress");
            return (Criteria) this;
        }

        public Criteria andRegularExpressNotEqualTo(String value) {
            addCriterion("regular_express <>", value, "regularExpress");
            return (Criteria) this;
        }

        public Criteria andRegularExpressGreaterThan(String value) {
            addCriterion("regular_express >", value, "regularExpress");
            return (Criteria) this;
        }

        public Criteria andRegularExpressGreaterThanOrEqualTo(String value) {
            addCriterion("regular_express >=", value, "regularExpress");
            return (Criteria) this;
        }

        public Criteria andRegularExpressLessThan(String value) {
            addCriterion("regular_express <", value, "regularExpress");
            return (Criteria) this;
        }

        public Criteria andRegularExpressLessThanOrEqualTo(String value) {
            addCriterion("regular_express <=", value, "regularExpress");
            return (Criteria) this;
        }

        public Criteria andRegularExpressLike(String value) {
            addCriterion("regular_express like", value, "regularExpress");
            return (Criteria) this;
        }

        public Criteria andRegularExpressNotLike(String value) {
            addCriterion("regular_express not like", value, "regularExpress");
            return (Criteria) this;
        }

        public Criteria andRegularExpressIn(List<String> values) {
            addCriterion("regular_express in", values, "regularExpress");
            return (Criteria) this;
        }

        public Criteria andRegularExpressNotIn(List<String> values) {
            addCriterion("regular_express not in", values, "regularExpress");
            return (Criteria) this;
        }

        public Criteria andRegularExpressBetween(String value1, String value2) {
            addCriterion("regular_express between", value1, value2, "regularExpress");
            return (Criteria) this;
        }

        public Criteria andRegularExpressNotBetween(String value1, String value2) {
            addCriterion("regular_express not between", value1, value2, "regularExpress");
            return (Criteria) this;
        }

        public Criteria andMaxlengthIsNull() {
            addCriterion("maxlength is null");
            return (Criteria) this;
        }

        public Criteria andMaxlengthIsNotNull() {
            addCriterion("maxlength is not null");
            return (Criteria) this;
        }

        public Criteria andMaxlengthEqualTo(Integer value) {
            addCriterion("maxlength =", value, "maxlength");
            return (Criteria) this;
        }

        public Criteria andMaxlengthNotEqualTo(Integer value) {
            addCriterion("maxlength <>", value, "maxlength");
            return (Criteria) this;
        }

        public Criteria andMaxlengthGreaterThan(Integer value) {
            addCriterion("maxlength >", value, "maxlength");
            return (Criteria) this;
        }

        public Criteria andMaxlengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("maxlength >=", value, "maxlength");
            return (Criteria) this;
        }

        public Criteria andMaxlengthLessThan(Integer value) {
            addCriterion("maxlength <", value, "maxlength");
            return (Criteria) this;
        }

        public Criteria andMaxlengthLessThanOrEqualTo(Integer value) {
            addCriterion("maxlength <=", value, "maxlength");
            return (Criteria) this;
        }

        public Criteria andMaxlengthIn(List<Integer> values) {
            addCriterion("maxlength in", values, "maxlength");
            return (Criteria) this;
        }

        public Criteria andMaxlengthNotIn(List<Integer> values) {
            addCriterion("maxlength not in", values, "maxlength");
            return (Criteria) this;
        }

        public Criteria andMaxlengthBetween(Integer value1, Integer value2) {
            addCriterion("maxlength between", value1, value2, "maxlength");
            return (Criteria) this;
        }

        public Criteria andMaxlengthNotBetween(Integer value1, Integer value2) {
            addCriterion("maxlength not between", value1, value2, "maxlength");
            return (Criteria) this;
        }

        public Criteria andStyleExpressIsNull() {
            addCriterion("style_express is null");
            return (Criteria) this;
        }

        public Criteria andStyleExpressIsNotNull() {
            addCriterion("style_express is not null");
            return (Criteria) this;
        }

        public Criteria andStyleExpressEqualTo(String value) {
            addCriterion("style_express =", value, "styleExpress");
            return (Criteria) this;
        }

        public Criteria andStyleExpressNotEqualTo(String value) {
            addCriterion("style_express <>", value, "styleExpress");
            return (Criteria) this;
        }

        public Criteria andStyleExpressGreaterThan(String value) {
            addCriterion("style_express >", value, "styleExpress");
            return (Criteria) this;
        }

        public Criteria andStyleExpressGreaterThanOrEqualTo(String value) {
            addCriterion("style_express >=", value, "styleExpress");
            return (Criteria) this;
        }

        public Criteria andStyleExpressLessThan(String value) {
            addCriterion("style_express <", value, "styleExpress");
            return (Criteria) this;
        }

        public Criteria andStyleExpressLessThanOrEqualTo(String value) {
            addCriterion("style_express <=", value, "styleExpress");
            return (Criteria) this;
        }

        public Criteria andStyleExpressLike(String value) {
            addCriterion("style_express like", value, "styleExpress");
            return (Criteria) this;
        }

        public Criteria andStyleExpressNotLike(String value) {
            addCriterion("style_express not like", value, "styleExpress");
            return (Criteria) this;
        }

        public Criteria andStyleExpressIn(List<String> values) {
            addCriterion("style_express in", values, "styleExpress");
            return (Criteria) this;
        }

        public Criteria andStyleExpressNotIn(List<String> values) {
            addCriterion("style_express not in", values, "styleExpress");
            return (Criteria) this;
        }

        public Criteria andStyleExpressBetween(String value1, String value2) {
            addCriterion("style_express between", value1, value2, "styleExpress");
            return (Criteria) this;
        }

        public Criteria andStyleExpressNotBetween(String value1, String value2) {
            addCriterion("style_express not between", value1, value2, "styleExpress");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andAddFlagIsNull() {
            addCriterion("add_flag is null");
            return (Criteria) this;
        }

        public Criteria andAddFlagIsNotNull() {
            addCriterion("add_flag is not null");
            return (Criteria) this;
        }

        public Criteria andAddFlagEqualTo(Integer value) {
            addCriterion("add_flag =", value, "addFlag");
            return (Criteria) this;
        }

        public Criteria andAddFlagNotEqualTo(Integer value) {
            addCriterion("add_flag <>", value, "addFlag");
            return (Criteria) this;
        }

        public Criteria andAddFlagGreaterThan(Integer value) {
            addCriterion("add_flag >", value, "addFlag");
            return (Criteria) this;
        }

        public Criteria andAddFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("add_flag >=", value, "addFlag");
            return (Criteria) this;
        }

        public Criteria andAddFlagLessThan(Integer value) {
            addCriterion("add_flag <", value, "addFlag");
            return (Criteria) this;
        }

        public Criteria andAddFlagLessThanOrEqualTo(Integer value) {
            addCriterion("add_flag <=", value, "addFlag");
            return (Criteria) this;
        }

        public Criteria andAddFlagIn(List<Integer> values) {
            addCriterion("add_flag in", values, "addFlag");
            return (Criteria) this;
        }

        public Criteria andAddFlagNotIn(List<Integer> values) {
            addCriterion("add_flag not in", values, "addFlag");
            return (Criteria) this;
        }

        public Criteria andAddFlagBetween(Integer value1, Integer value2) {
            addCriterion("add_flag between", value1, value2, "addFlag");
            return (Criteria) this;
        }

        public Criteria andAddFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("add_flag not between", value1, value2, "addFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagIsNull() {
            addCriterion("edit_flag is null");
            return (Criteria) this;
        }

        public Criteria andEditFlagIsNotNull() {
            addCriterion("edit_flag is not null");
            return (Criteria) this;
        }

        public Criteria andEditFlagEqualTo(Integer value) {
            addCriterion("edit_flag =", value, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagNotEqualTo(Integer value) {
            addCriterion("edit_flag <>", value, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagGreaterThan(Integer value) {
            addCriterion("edit_flag >", value, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("edit_flag >=", value, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagLessThan(Integer value) {
            addCriterion("edit_flag <", value, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagLessThanOrEqualTo(Integer value) {
            addCriterion("edit_flag <=", value, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagIn(List<Integer> values) {
            addCriterion("edit_flag in", values, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagNotIn(List<Integer> values) {
            addCriterion("edit_flag not in", values, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagBetween(Integer value1, Integer value2) {
            addCriterion("edit_flag between", value1, value2, "editFlag");
            return (Criteria) this;
        }

        public Criteria andEditFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("edit_flag not between", value1, value2, "editFlag");
            return (Criteria) this;
        }

        public Criteria andListFlagIsNull() {
            addCriterion("list_flag is null");
            return (Criteria) this;
        }

        public Criteria andListFlagIsNotNull() {
            addCriterion("list_flag is not null");
            return (Criteria) this;
        }

        public Criteria andListFlagEqualTo(Integer value) {
            addCriterion("list_flag =", value, "listFlag");
            return (Criteria) this;
        }

        public Criteria andListFlagNotEqualTo(Integer value) {
            addCriterion("list_flag <>", value, "listFlag");
            return (Criteria) this;
        }

        public Criteria andListFlagGreaterThan(Integer value) {
            addCriterion("list_flag >", value, "listFlag");
            return (Criteria) this;
        }

        public Criteria andListFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("list_flag >=", value, "listFlag");
            return (Criteria) this;
        }

        public Criteria andListFlagLessThan(Integer value) {
            addCriterion("list_flag <", value, "listFlag");
            return (Criteria) this;
        }

        public Criteria andListFlagLessThanOrEqualTo(Integer value) {
            addCriterion("list_flag <=", value, "listFlag");
            return (Criteria) this;
        }

        public Criteria andListFlagIn(List<Integer> values) {
            addCriterion("list_flag in", values, "listFlag");
            return (Criteria) this;
        }

        public Criteria andListFlagNotIn(List<Integer> values) {
            addCriterion("list_flag not in", values, "listFlag");
            return (Criteria) this;
        }

        public Criteria andListFlagBetween(Integer value1, Integer value2) {
            addCriterion("list_flag between", value1, value2, "listFlag");
            return (Criteria) this;
        }

        public Criteria andListFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("list_flag not between", value1, value2, "listFlag");
            return (Criteria) this;
        }

        public Criteria andQueryFlagIsNull() {
            addCriterion("query_flag is null");
            return (Criteria) this;
        }

        public Criteria andQueryFlagIsNotNull() {
            addCriterion("query_flag is not null");
            return (Criteria) this;
        }

        public Criteria andQueryFlagEqualTo(Integer value) {
            addCriterion("query_flag =", value, "queryFlag");
            return (Criteria) this;
        }

        public Criteria andQueryFlagNotEqualTo(Integer value) {
            addCriterion("query_flag <>", value, "queryFlag");
            return (Criteria) this;
        }

        public Criteria andQueryFlagGreaterThan(Integer value) {
            addCriterion("query_flag >", value, "queryFlag");
            return (Criteria) this;
        }

        public Criteria andQueryFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("query_flag >=", value, "queryFlag");
            return (Criteria) this;
        }

        public Criteria andQueryFlagLessThan(Integer value) {
            addCriterion("query_flag <", value, "queryFlag");
            return (Criteria) this;
        }

        public Criteria andQueryFlagLessThanOrEqualTo(Integer value) {
            addCriterion("query_flag <=", value, "queryFlag");
            return (Criteria) this;
        }

        public Criteria andQueryFlagIn(List<Integer> values) {
            addCriterion("query_flag in", values, "queryFlag");
            return (Criteria) this;
        }

        public Criteria andQueryFlagNotIn(List<Integer> values) {
            addCriterion("query_flag not in", values, "queryFlag");
            return (Criteria) this;
        }

        public Criteria andQueryFlagBetween(Integer value1, Integer value2) {
            addCriterion("query_flag between", value1, value2, "queryFlag");
            return (Criteria) this;
        }

        public Criteria andQueryFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("query_flag not between", value1, value2, "queryFlag");
            return (Criteria) this;
        }

        public Criteria andQueryFormatIsNull() {
            addCriterion("query_format is null");
            return (Criteria) this;
        }

        public Criteria andQueryFormatIsNotNull() {
            addCriterion("query_format is not null");
            return (Criteria) this;
        }

        public Criteria andQueryFormatEqualTo(String value) {
            addCriterion("query_format =", value, "queryFormat");
            return (Criteria) this;
        }

        public Criteria andQueryFormatNotEqualTo(String value) {
            addCriterion("query_format <>", value, "queryFormat");
            return (Criteria) this;
        }

        public Criteria andQueryFormatGreaterThan(String value) {
            addCriterion("query_format >", value, "queryFormat");
            return (Criteria) this;
        }

        public Criteria andQueryFormatGreaterThanOrEqualTo(String value) {
            addCriterion("query_format >=", value, "queryFormat");
            return (Criteria) this;
        }

        public Criteria andQueryFormatLessThan(String value) {
            addCriterion("query_format <", value, "queryFormat");
            return (Criteria) this;
        }

        public Criteria andQueryFormatLessThanOrEqualTo(String value) {
            addCriterion("query_format <=", value, "queryFormat");
            return (Criteria) this;
        }

        public Criteria andQueryFormatLike(String value) {
            addCriterion("query_format like", value, "queryFormat");
            return (Criteria) this;
        }

        public Criteria andQueryFormatNotLike(String value) {
            addCriterion("query_format not like", value, "queryFormat");
            return (Criteria) this;
        }

        public Criteria andQueryFormatIn(List<String> values) {
            addCriterion("query_format in", values, "queryFormat");
            return (Criteria) this;
        }

        public Criteria andQueryFormatNotIn(List<String> values) {
            addCriterion("query_format not in", values, "queryFormat");
            return (Criteria) this;
        }

        public Criteria andQueryFormatBetween(String value1, String value2) {
            addCriterion("query_format between", value1, value2, "queryFormat");
            return (Criteria) this;
        }

        public Criteria andQueryFormatNotBetween(String value1, String value2) {
            addCriterion("query_format not between", value1, value2, "queryFormat");
            return (Criteria) this;
        }

        public Criteria andHrefColumnIsNull() {
            addCriterion("href_column is null");
            return (Criteria) this;
        }

        public Criteria andHrefColumnIsNotNull() {
            addCriterion("href_column is not null");
            return (Criteria) this;
        }

        public Criteria andHrefColumnEqualTo(String value) {
            addCriterion("href_column =", value, "hrefColumn");
            return (Criteria) this;
        }

        public Criteria andHrefColumnNotEqualTo(String value) {
            addCriterion("href_column <>", value, "hrefColumn");
            return (Criteria) this;
        }

        public Criteria andHrefColumnGreaterThan(String value) {
            addCriterion("href_column >", value, "hrefColumn");
            return (Criteria) this;
        }

        public Criteria andHrefColumnGreaterThanOrEqualTo(String value) {
            addCriterion("href_column >=", value, "hrefColumn");
            return (Criteria) this;
        }

        public Criteria andHrefColumnLessThan(String value) {
            addCriterion("href_column <", value, "hrefColumn");
            return (Criteria) this;
        }

        public Criteria andHrefColumnLessThanOrEqualTo(String value) {
            addCriterion("href_column <=", value, "hrefColumn");
            return (Criteria) this;
        }

        public Criteria andHrefColumnLike(String value) {
            addCriterion("href_column like", value, "hrefColumn");
            return (Criteria) this;
        }

        public Criteria andHrefColumnNotLike(String value) {
            addCriterion("href_column not like", value, "hrefColumn");
            return (Criteria) this;
        }

        public Criteria andHrefColumnIn(List<String> values) {
            addCriterion("href_column in", values, "hrefColumn");
            return (Criteria) this;
        }

        public Criteria andHrefColumnNotIn(List<String> values) {
            addCriterion("href_column not in", values, "hrefColumn");
            return (Criteria) this;
        }

        public Criteria andHrefColumnBetween(String value1, String value2) {
            addCriterion("href_column between", value1, value2, "hrefColumn");
            return (Criteria) this;
        }

        public Criteria andHrefColumnNotBetween(String value1, String value2) {
            addCriterion("href_column not between", value1, value2, "hrefColumn");
            return (Criteria) this;
        }

        public Criteria andDetailFlagIsNull() {
            addCriterion("detail_flag is null");
            return (Criteria) this;
        }

        public Criteria andDetailFlagIsNotNull() {
            addCriterion("detail_flag is not null");
            return (Criteria) this;
        }

        public Criteria andDetailFlagEqualTo(Integer value) {
            addCriterion("detail_flag =", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagNotEqualTo(Integer value) {
            addCriterion("detail_flag <>", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagGreaterThan(Integer value) {
            addCriterion("detail_flag >", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("detail_flag >=", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagLessThan(Integer value) {
            addCriterion("detail_flag <", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagLessThanOrEqualTo(Integer value) {
            addCriterion("detail_flag <=", value, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagIn(List<Integer> values) {
            addCriterion("detail_flag in", values, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagNotIn(List<Integer> values) {
            addCriterion("detail_flag not in", values, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagBetween(Integer value1, Integer value2) {
            addCriterion("detail_flag between", value1, value2, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andDetailFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("detail_flag not between", value1, value2, "detailFlag");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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