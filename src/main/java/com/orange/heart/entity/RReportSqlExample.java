package com.orange.heart.entity;

import com.orange.commons.utils.PageInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RReportSqlExample extends PageInfo {
    /**
     * r_report_sql
     */
    protected String orderByClause;

    /**
     * r_report_sql
     */
    protected boolean distinct;

    /**
     * r_report_sql
     */
    protected List<Criteria> oredCriteria;

    public RReportSqlExample() {
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

    /**
     * r_report_sql 2017-09-11
     */
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

        public Criteria andSqlCodeIsNull() {
            addCriterion("sql_code is null");
            return (Criteria) this;
        }

        public Criteria andSqlCodeIsNotNull() {
            addCriterion("sql_code is not null");
            return (Criteria) this;
        }

        public Criteria andSqlCodeEqualTo(String value) {
            addCriterion("sql_code =", value, "sqlCode");
            return (Criteria) this;
        }

        public Criteria andSqlCodeNotEqualTo(String value) {
            addCriterion("sql_code <>", value, "sqlCode");
            return (Criteria) this;
        }

        public Criteria andSqlCodeGreaterThan(String value) {
            addCriterion("sql_code >", value, "sqlCode");
            return (Criteria) this;
        }

        public Criteria andSqlCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sql_code >=", value, "sqlCode");
            return (Criteria) this;
        }

        public Criteria andSqlCodeLessThan(String value) {
            addCriterion("sql_code <", value, "sqlCode");
            return (Criteria) this;
        }

        public Criteria andSqlCodeLessThanOrEqualTo(String value) {
            addCriterion("sql_code <=", value, "sqlCode");
            return (Criteria) this;
        }

        public Criteria andSqlCodeLike(String value) {
            addCriterion("sql_code like", value, "sqlCode");
            return (Criteria) this;
        }

        public Criteria andSqlCodeNotLike(String value) {
            addCriterion("sql_code not like", value, "sqlCode");
            return (Criteria) this;
        }

        public Criteria andSqlCodeIn(List<String> values) {
            addCriterion("sql_code in", values, "sqlCode");
            return (Criteria) this;
        }

        public Criteria andSqlCodeNotIn(List<String> values) {
            addCriterion("sql_code not in", values, "sqlCode");
            return (Criteria) this;
        }

        public Criteria andSqlCodeBetween(String value1, String value2) {
            addCriterion("sql_code between", value1, value2, "sqlCode");
            return (Criteria) this;
        }

        public Criteria andSqlCodeNotBetween(String value1, String value2) {
            addCriterion("sql_code not between", value1, value2, "sqlCode");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("`name` is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("`name` is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("`name` =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("`name` <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("`name` >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("`name` >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("`name` <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("`name` <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("`name` like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("`name` not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("`name` in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("`name` not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("`name` between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("`name` not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andSqlIsNull() {
            addCriterion("`sql` is null");
            return (Criteria) this;
        }

        public Criteria andSqlIsNotNull() {
            addCriterion("`sql` is not null");
            return (Criteria) this;
        }

        public Criteria andSqlEqualTo(String value) {
            addCriterion("`sql` =", value, "sql");
            return (Criteria) this;
        }

        public Criteria andSqlNotEqualTo(String value) {
            addCriterion("`sql` <>", value, "sql");
            return (Criteria) this;
        }

        public Criteria andSqlGreaterThan(String value) {
            addCriterion("`sql` >", value, "sql");
            return (Criteria) this;
        }

        public Criteria andSqlGreaterThanOrEqualTo(String value) {
            addCriterion("`sql` >=", value, "sql");
            return (Criteria) this;
        }

        public Criteria andSqlLessThan(String value) {
            addCriterion("`sql` <", value, "sql");
            return (Criteria) this;
        }

        public Criteria andSqlLessThanOrEqualTo(String value) {
            addCriterion("`sql` <=", value, "sql");
            return (Criteria) this;
        }

        public Criteria andSqlLike(String value) {
            addCriterion("`sql` like", value, "sql");
            return (Criteria) this;
        }

        public Criteria andSqlNotLike(String value) {
            addCriterion("`sql` not like", value, "sql");
            return (Criteria) this;
        }

        public Criteria andSqlIn(List<String> values) {
            addCriterion("`sql` in", values, "sql");
            return (Criteria) this;
        }

        public Criteria andSqlNotIn(List<String> values) {
            addCriterion("`sql` not in", values, "sql");
            return (Criteria) this;
        }

        public Criteria andSqlBetween(String value1, String value2) {
            addCriterion("`sql` between", value1, value2, "sql");
            return (Criteria) this;
        }

        public Criteria andSqlNotBetween(String value1, String value2) {
            addCriterion("`sql` not between", value1, value2, "sql");
            return (Criteria) this;
        }

        public Criteria andDataSourceIsNull() {
            addCriterion("data_source is null");
            return (Criteria) this;
        }

        public Criteria andDataSourceIsNotNull() {
            addCriterion("data_source is not null");
            return (Criteria) this;
        }

        public Criteria andDataSourceEqualTo(String value) {
            addCriterion("data_source =", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotEqualTo(String value) {
            addCriterion("data_source <>", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceGreaterThan(String value) {
            addCriterion("data_source >", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceGreaterThanOrEqualTo(String value) {
            addCriterion("data_source >=", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceLessThan(String value) {
            addCriterion("data_source <", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceLessThanOrEqualTo(String value) {
            addCriterion("data_source <=", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceLike(String value) {
            addCriterion("data_source like", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotLike(String value) {
            addCriterion("data_source not like", value, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceIn(List<String> values) {
            addCriterion("data_source in", values, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotIn(List<String> values) {
            addCriterion("data_source not in", values, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceBetween(String value1, String value2) {
            addCriterion("data_source between", value1, value2, "dataSource");
            return (Criteria) this;
        }

        public Criteria andDataSourceNotBetween(String value1, String value2) {
            addCriterion("data_source not between", value1, value2, "dataSource");
            return (Criteria) this;
        }

        public Criteria andShowColumnIsNull() {
            addCriterion("show_column is null");
            return (Criteria) this;
        }

        public Criteria andShowColumnIsNotNull() {
            addCriterion("show_column is not null");
            return (Criteria) this;
        }

        public Criteria andShowColumnEqualTo(String value) {
            addCriterion("show_column =", value, "showColumn");
            return (Criteria) this;
        }

        public Criteria andShowColumnNotEqualTo(String value) {
            addCriterion("show_column <>", value, "showColumn");
            return (Criteria) this;
        }

        public Criteria andShowColumnGreaterThan(String value) {
            addCriterion("show_column >", value, "showColumn");
            return (Criteria) this;
        }

        public Criteria andShowColumnGreaterThanOrEqualTo(String value) {
            addCriterion("show_column >=", value, "showColumn");
            return (Criteria) this;
        }

        public Criteria andShowColumnLessThan(String value) {
            addCriterion("show_column <", value, "showColumn");
            return (Criteria) this;
        }

        public Criteria andShowColumnLessThanOrEqualTo(String value) {
            addCriterion("show_column <=", value, "showColumn");
            return (Criteria) this;
        }

        public Criteria andShowColumnLike(String value) {
            addCriterion("show_column like", value, "showColumn");
            return (Criteria) this;
        }

        public Criteria andShowColumnNotLike(String value) {
            addCriterion("show_column not like", value, "showColumn");
            return (Criteria) this;
        }

        public Criteria andShowColumnIn(List<String> values) {
            addCriterion("show_column in", values, "showColumn");
            return (Criteria) this;
        }

        public Criteria andShowColumnNotIn(List<String> values) {
            addCriterion("show_column not in", values, "showColumn");
            return (Criteria) this;
        }

        public Criteria andShowColumnBetween(String value1, String value2) {
            addCriterion("show_column between", value1, value2, "showColumn");
            return (Criteria) this;
        }

        public Criteria andShowColumnNotBetween(String value1, String value2) {
            addCriterion("show_column not between", value1, value2, "showColumn");
            return (Criteria) this;
        }

        public Criteria andParamIsNull() {
            addCriterion("param is null");
            return (Criteria) this;
        }

        public Criteria andParamIsNotNull() {
            addCriterion("param is not null");
            return (Criteria) this;
        }

        public Criteria andParamEqualTo(String value) {
            addCriterion("param =", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamNotEqualTo(String value) {
            addCriterion("param <>", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamGreaterThan(String value) {
            addCriterion("param >", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamGreaterThanOrEqualTo(String value) {
            addCriterion("param >=", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamLessThan(String value) {
            addCriterion("param <", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamLessThanOrEqualTo(String value) {
            addCriterion("param <=", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamLike(String value) {
            addCriterion("param like", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamNotLike(String value) {
            addCriterion("param not like", value, "param");
            return (Criteria) this;
        }

        public Criteria andParamIn(List<String> values) {
            addCriterion("param in", values, "param");
            return (Criteria) this;
        }

        public Criteria andParamNotIn(List<String> values) {
            addCriterion("param not in", values, "param");
            return (Criteria) this;
        }

        public Criteria andParamBetween(String value1, String value2) {
            addCriterion("param between", value1, value2, "param");
            return (Criteria) this;
        }

        public Criteria andParamNotBetween(String value1, String value2) {
            addCriterion("param not between", value1, value2, "param");
            return (Criteria) this;
        }

        public Criteria andPageSizeIsNull() {
            addCriterion("page_size is null");
            return (Criteria) this;
        }

        public Criteria andPageSizeIsNotNull() {
            addCriterion("page_size is not null");
            return (Criteria) this;
        }

        public Criteria andPageSizeEqualTo(Integer value) {
            addCriterion("page_size =", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeNotEqualTo(Integer value) {
            addCriterion("page_size <>", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeGreaterThan(Integer value) {
            addCriterion("page_size >", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("page_size >=", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeLessThan(Integer value) {
            addCriterion("page_size <", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeLessThanOrEqualTo(Integer value) {
            addCriterion("page_size <=", value, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeIn(List<Integer> values) {
            addCriterion("page_size in", values, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeNotIn(List<Integer> values) {
            addCriterion("page_size not in", values, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeBetween(Integer value1, Integer value2) {
            addCriterion("page_size between", value1, value2, "pageSize");
            return (Criteria) this;
        }

        public Criteria andPageSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("page_size not between", value1, value2, "pageSize");
            return (Criteria) this;
        }

        public Criteria andResultTypeIsNull() {
            addCriterion("result_type is null");
            return (Criteria) this;
        }

        public Criteria andResultTypeIsNotNull() {
            addCriterion("result_type is not null");
            return (Criteria) this;
        }

        public Criteria andResultTypeEqualTo(Integer value) {
            addCriterion("result_type =", value, "resultType");
            return (Criteria) this;
        }

        public Criteria andResultTypeNotEqualTo(Integer value) {
            addCriterion("result_type <>", value, "resultType");
            return (Criteria) this;
        }

        public Criteria andResultTypeGreaterThan(Integer value) {
            addCriterion("result_type >", value, "resultType");
            return (Criteria) this;
        }

        public Criteria andResultTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("result_type >=", value, "resultType");
            return (Criteria) this;
        }

        public Criteria andResultTypeLessThan(Integer value) {
            addCriterion("result_type <", value, "resultType");
            return (Criteria) this;
        }

        public Criteria andResultTypeLessThanOrEqualTo(Integer value) {
            addCriterion("result_type <=", value, "resultType");
            return (Criteria) this;
        }

        public Criteria andResultTypeIn(List<Integer> values) {
            addCriterion("result_type in", values, "resultType");
            return (Criteria) this;
        }

        public Criteria andResultTypeNotIn(List<Integer> values) {
            addCriterion("result_type not in", values, "resultType");
            return (Criteria) this;
        }

        public Criteria andResultTypeBetween(Integer value1, Integer value2) {
            addCriterion("result_type between", value1, value2, "resultType");
            return (Criteria) this;
        }

        public Criteria andResultTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("result_type not between", value1, value2, "resultType");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("`state` is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("`state` is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("`state` =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("`state` <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("`state` >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("`state` >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("`state` <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("`state` <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("`state` in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("`state` not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("`state` between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("`state` not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andNoticeEmailIsNull() {
            addCriterion("notice_email is null");
            return (Criteria) this;
        }

        public Criteria andNoticeEmailIsNotNull() {
            addCriterion("notice_email is not null");
            return (Criteria) this;
        }

        public Criteria andNoticeEmailEqualTo(String value) {
            addCriterion("notice_email =", value, "noticeEmail");
            return (Criteria) this;
        }

        public Criteria andNoticeEmailNotEqualTo(String value) {
            addCriterion("notice_email <>", value, "noticeEmail");
            return (Criteria) this;
        }

        public Criteria andNoticeEmailGreaterThan(String value) {
            addCriterion("notice_email >", value, "noticeEmail");
            return (Criteria) this;
        }

        public Criteria andNoticeEmailGreaterThanOrEqualTo(String value) {
            addCriterion("notice_email >=", value, "noticeEmail");
            return (Criteria) this;
        }

        public Criteria andNoticeEmailLessThan(String value) {
            addCriterion("notice_email <", value, "noticeEmail");
            return (Criteria) this;
        }

        public Criteria andNoticeEmailLessThanOrEqualTo(String value) {
            addCriterion("notice_email <=", value, "noticeEmail");
            return (Criteria) this;
        }

        public Criteria andNoticeEmailLike(String value) {
            addCriterion("notice_email like", value, "noticeEmail");
            return (Criteria) this;
        }

        public Criteria andNoticeEmailNotLike(String value) {
            addCriterion("notice_email not like", value, "noticeEmail");
            return (Criteria) this;
        }

        public Criteria andNoticeEmailIn(List<String> values) {
            addCriterion("notice_email in", values, "noticeEmail");
            return (Criteria) this;
        }

        public Criteria andNoticeEmailNotIn(List<String> values) {
            addCriterion("notice_email not in", values, "noticeEmail");
            return (Criteria) this;
        }

        public Criteria andNoticeEmailBetween(String value1, String value2) {
            addCriterion("notice_email between", value1, value2, "noticeEmail");
            return (Criteria) this;
        }

        public Criteria andNoticeEmailNotBetween(String value1, String value2) {
            addCriterion("notice_email not between", value1, value2, "noticeEmail");
            return (Criteria) this;
        }

        public Criteria andSchedulePlanIsNull() {
            addCriterion("schedule_plan is null");
            return (Criteria) this;
        }

        public Criteria andSchedulePlanIsNotNull() {
            addCriterion("schedule_plan is not null");
            return (Criteria) this;
        }

        public Criteria andSchedulePlanEqualTo(String value) {
            addCriterion("schedule_plan =", value, "schedulePlan");
            return (Criteria) this;
        }

        public Criteria andSchedulePlanNotEqualTo(String value) {
            addCriterion("schedule_plan <>", value, "schedulePlan");
            return (Criteria) this;
        }

        public Criteria andSchedulePlanGreaterThan(String value) {
            addCriterion("schedule_plan >", value, "schedulePlan");
            return (Criteria) this;
        }

        public Criteria andSchedulePlanGreaterThanOrEqualTo(String value) {
            addCriterion("schedule_plan >=", value, "schedulePlan");
            return (Criteria) this;
        }

        public Criteria andSchedulePlanLessThan(String value) {
            addCriterion("schedule_plan <", value, "schedulePlan");
            return (Criteria) this;
        }

        public Criteria andSchedulePlanLessThanOrEqualTo(String value) {
            addCriterion("schedule_plan <=", value, "schedulePlan");
            return (Criteria) this;
        }

        public Criteria andSchedulePlanLike(String value) {
            addCriterion("schedule_plan like", value, "schedulePlan");
            return (Criteria) this;
        }

        public Criteria andSchedulePlanNotLike(String value) {
            addCriterion("schedule_plan not like", value, "schedulePlan");
            return (Criteria) this;
        }

        public Criteria andSchedulePlanIn(List<String> values) {
            addCriterion("schedule_plan in", values, "schedulePlan");
            return (Criteria) this;
        }

        public Criteria andSchedulePlanNotIn(List<String> values) {
            addCriterion("schedule_plan not in", values, "schedulePlan");
            return (Criteria) this;
        }

        public Criteria andSchedulePlanBetween(String value1, String value2) {
            addCriterion("schedule_plan between", value1, value2, "schedulePlan");
            return (Criteria) this;
        }

        public Criteria andSchedulePlanNotBetween(String value1, String value2) {
            addCriterion("schedule_plan not between", value1, value2, "schedulePlan");
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

    /**
     * r_report_sql
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * r_report_sql 2017-09-11
     */
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