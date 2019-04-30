package com.orange.heart.entity;

import com.orange.commons.utils.PageInfo;

import java.util.ArrayList;
import java.util.List;

public class ColumnsExample extends PageInfo {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ColumnsExample() {
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

        public Criteria andTableCatalogIsNull() {
            addCriterion("TABLE_CATALOG is null");
            return (Criteria) this;
        }

        public Criteria andTableCatalogIsNotNull() {
            addCriterion("TABLE_CATALOG is not null");
            return (Criteria) this;
        }

        public Criteria andTableCatalogEqualTo(String value) {
            addCriterion("TABLE_CATALOG =", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogNotEqualTo(String value) {
            addCriterion("TABLE_CATALOG <>", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogGreaterThan(String value) {
            addCriterion("TABLE_CATALOG >", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogGreaterThanOrEqualTo(String value) {
            addCriterion("TABLE_CATALOG >=", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogLessThan(String value) {
            addCriterion("TABLE_CATALOG <", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogLessThanOrEqualTo(String value) {
            addCriterion("TABLE_CATALOG <=", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogLike(String value) {
            addCriterion("TABLE_CATALOG like", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogNotLike(String value) {
            addCriterion("TABLE_CATALOG not like", value, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogIn(List<String> values) {
            addCriterion("TABLE_CATALOG in", values, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogNotIn(List<String> values) {
            addCriterion("TABLE_CATALOG not in", values, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogBetween(String value1, String value2) {
            addCriterion("TABLE_CATALOG between", value1, value2, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableCatalogNotBetween(String value1, String value2) {
            addCriterion("TABLE_CATALOG not between", value1, value2, "tableCatalog");
            return (Criteria) this;
        }

        public Criteria andTableSchemaIsNull() {
            addCriterion("TABLE_SCHEMA is null");
            return (Criteria) this;
        }

        public Criteria andTableSchemaIsNotNull() {
            addCriterion("TABLE_SCHEMA is not null");
            return (Criteria) this;
        }

        public Criteria andTableSchemaEqualTo(String value) {
            addCriterion("TABLE_SCHEMA =", value, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaNotEqualTo(String value) {
            addCriterion("TABLE_SCHEMA <>", value, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaGreaterThan(String value) {
            addCriterion("TABLE_SCHEMA >", value, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaGreaterThanOrEqualTo(String value) {
            addCriterion("TABLE_SCHEMA >=", value, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaLessThan(String value) {
            addCriterion("TABLE_SCHEMA <", value, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaLessThanOrEqualTo(String value) {
            addCriterion("TABLE_SCHEMA <=", value, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaLike(String value) {
            addCriterion("TABLE_SCHEMA like", value, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaNotLike(String value) {
            addCriterion("TABLE_SCHEMA not like", value, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaIn(List<String> values) {
            addCriterion("TABLE_SCHEMA in", values, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaNotIn(List<String> values) {
            addCriterion("TABLE_SCHEMA not in", values, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaBetween(String value1, String value2) {
            addCriterion("TABLE_SCHEMA between", value1, value2, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableSchemaNotBetween(String value1, String value2) {
            addCriterion("TABLE_SCHEMA not between", value1, value2, "tableSchema");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNull() {
            addCriterion("`TABLE_NAME` is null");
            return (Criteria) this;
        }

        public Criteria andTableNameIsNotNull() {
            addCriterion("`TABLE_NAME` is not null");
            return (Criteria) this;
        }

        public Criteria andTableNameEqualTo(String value) {
            addCriterion("`TABLE_NAME` =", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotEqualTo(String value) {
            addCriterion("`TABLE_NAME` <>", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThan(String value) {
            addCriterion("`TABLE_NAME` >", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameGreaterThanOrEqualTo(String value) {
            addCriterion("`TABLE_NAME` >=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThan(String value) {
            addCriterion("`TABLE_NAME` <", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLessThanOrEqualTo(String value) {
            addCriterion("`TABLE_NAME` <=", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameLike(String value) {
            addCriterion("`TABLE_NAME` like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotLike(String value) {
            addCriterion("`TABLE_NAME` not like", value, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameIn(List<String> values) {
            addCriterion("`TABLE_NAME` in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotIn(List<String> values) {
            addCriterion("`TABLE_NAME` not in", values, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameBetween(String value1, String value2) {
            addCriterion("`TABLE_NAME` between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andTableNameNotBetween(String value1, String value2) {
            addCriterion("`TABLE_NAME` not between", value1, value2, "tableName");
            return (Criteria) this;
        }

        public Criteria andColumnNameIsNull() {
            addCriterion("`COLUMN_NAME` is null");
            return (Criteria) this;
        }

        public Criteria andColumnNameIsNotNull() {
            addCriterion("`COLUMN_NAME` is not null");
            return (Criteria) this;
        }

        public Criteria andColumnNameEqualTo(String value) {
            addCriterion("`COLUMN_NAME` =", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotEqualTo(String value) {
            addCriterion("`COLUMN_NAME` <>", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameGreaterThan(String value) {
            addCriterion("`COLUMN_NAME` >", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameGreaterThanOrEqualTo(String value) {
            addCriterion("`COLUMN_NAME` >=", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLessThan(String value) {
            addCriterion("`COLUMN_NAME` <", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLessThanOrEqualTo(String value) {
            addCriterion("`COLUMN_NAME` <=", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameLike(String value) {
            addCriterion("`COLUMN_NAME` like", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotLike(String value) {
            addCriterion("`COLUMN_NAME` not like", value, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameIn(List<String> values) {
            addCriterion("`COLUMN_NAME` in", values, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotIn(List<String> values) {
            addCriterion("`COLUMN_NAME` not in", values, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameBetween(String value1, String value2) {
            addCriterion("`COLUMN_NAME` between", value1, value2, "columnName");
            return (Criteria) this;
        }

        public Criteria andColumnNameNotBetween(String value1, String value2) {
            addCriterion("`COLUMN_NAME` not between", value1, value2, "columnName");
            return (Criteria) this;
        }

        public Criteria andOrdinalPositionIsNull() {
            addCriterion("ORDINAL_POSITION is null");
            return (Criteria) this;
        }

        public Criteria andOrdinalPositionIsNotNull() {
            addCriterion("ORDINAL_POSITION is not null");
            return (Criteria) this;
        }

        public Criteria andOrdinalPositionEqualTo(Long value) {
            addCriterion("ORDINAL_POSITION =", value, "ordinalPosition");
            return (Criteria) this;
        }

        public Criteria andOrdinalPositionNotEqualTo(Long value) {
            addCriterion("ORDINAL_POSITION <>", value, "ordinalPosition");
            return (Criteria) this;
        }

        public Criteria andOrdinalPositionGreaterThan(Long value) {
            addCriterion("ORDINAL_POSITION >", value, "ordinalPosition");
            return (Criteria) this;
        }

        public Criteria andOrdinalPositionGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDINAL_POSITION >=", value, "ordinalPosition");
            return (Criteria) this;
        }

        public Criteria andOrdinalPositionLessThan(Long value) {
            addCriterion("ORDINAL_POSITION <", value, "ordinalPosition");
            return (Criteria) this;
        }

        public Criteria andOrdinalPositionLessThanOrEqualTo(Long value) {
            addCriterion("ORDINAL_POSITION <=", value, "ordinalPosition");
            return (Criteria) this;
        }

        public Criteria andOrdinalPositionIn(List<Long> values) {
            addCriterion("ORDINAL_POSITION in", values, "ordinalPosition");
            return (Criteria) this;
        }

        public Criteria andOrdinalPositionNotIn(List<Long> values) {
            addCriterion("ORDINAL_POSITION not in", values, "ordinalPosition");
            return (Criteria) this;
        }

        public Criteria andOrdinalPositionBetween(Long value1, Long value2) {
            addCriterion("ORDINAL_POSITION between", value1, value2, "ordinalPosition");
            return (Criteria) this;
        }

        public Criteria andOrdinalPositionNotBetween(Long value1, Long value2) {
            addCriterion("ORDINAL_POSITION not between", value1, value2, "ordinalPosition");
            return (Criteria) this;
        }

        public Criteria andIsNullableIsNull() {
            addCriterion("IS_NULLABLE is null");
            return (Criteria) this;
        }

        public Criteria andIsNullableIsNotNull() {
            addCriterion("IS_NULLABLE is not null");
            return (Criteria) this;
        }

        public Criteria andIsNullableEqualTo(String value) {
            addCriterion("IS_NULLABLE =", value, "isNullable");
            return (Criteria) this;
        }

        public Criteria andIsNullableNotEqualTo(String value) {
            addCriterion("IS_NULLABLE <>", value, "isNullable");
            return (Criteria) this;
        }

        public Criteria andIsNullableGreaterThan(String value) {
            addCriterion("IS_NULLABLE >", value, "isNullable");
            return (Criteria) this;
        }

        public Criteria andIsNullableGreaterThanOrEqualTo(String value) {
            addCriterion("IS_NULLABLE >=", value, "isNullable");
            return (Criteria) this;
        }

        public Criteria andIsNullableLessThan(String value) {
            addCriterion("IS_NULLABLE <", value, "isNullable");
            return (Criteria) this;
        }

        public Criteria andIsNullableLessThanOrEqualTo(String value) {
            addCriterion("IS_NULLABLE <=", value, "isNullable");
            return (Criteria) this;
        }

        public Criteria andIsNullableLike(String value) {
            addCriterion("IS_NULLABLE like", value, "isNullable");
            return (Criteria) this;
        }

        public Criteria andIsNullableNotLike(String value) {
            addCriterion("IS_NULLABLE not like", value, "isNullable");
            return (Criteria) this;
        }

        public Criteria andIsNullableIn(List<String> values) {
            addCriterion("IS_NULLABLE in", values, "isNullable");
            return (Criteria) this;
        }

        public Criteria andIsNullableNotIn(List<String> values) {
            addCriterion("IS_NULLABLE not in", values, "isNullable");
            return (Criteria) this;
        }

        public Criteria andIsNullableBetween(String value1, String value2) {
            addCriterion("IS_NULLABLE between", value1, value2, "isNullable");
            return (Criteria) this;
        }

        public Criteria andIsNullableNotBetween(String value1, String value2) {
            addCriterion("IS_NULLABLE not between", value1, value2, "isNullable");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNull() {
            addCriterion("DATA_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNotNull() {
            addCriterion("DATA_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andDataTypeEqualTo(String value) {
            addCriterion("DATA_TYPE =", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotEqualTo(String value) {
            addCriterion("DATA_TYPE <>", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThan(String value) {
            addCriterion("DATA_TYPE >", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DATA_TYPE >=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThan(String value) {
            addCriterion("DATA_TYPE <", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThanOrEqualTo(String value) {
            addCriterion("DATA_TYPE <=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLike(String value) {
            addCriterion("DATA_TYPE like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotLike(String value) {
            addCriterion("DATA_TYPE not like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeIn(List<String> values) {
            addCriterion("DATA_TYPE in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotIn(List<String> values) {
            addCriterion("DATA_TYPE not in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeBetween(String value1, String value2) {
            addCriterion("DATA_TYPE between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotBetween(String value1, String value2) {
            addCriterion("DATA_TYPE not between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andCharacterMaximumLengthIsNull() {
            addCriterion("CHARACTER_MAXIMUM_LENGTH is null");
            return (Criteria) this;
        }

        public Criteria andCharacterMaximumLengthIsNotNull() {
            addCriterion("CHARACTER_MAXIMUM_LENGTH is not null");
            return (Criteria) this;
        }

        public Criteria andCharacterMaximumLengthEqualTo(Long value) {
            addCriterion("CHARACTER_MAXIMUM_LENGTH =", value, "characterMaximumLength");
            return (Criteria) this;
        }

        public Criteria andCharacterMaximumLengthNotEqualTo(Long value) {
            addCriterion("CHARACTER_MAXIMUM_LENGTH <>", value, "characterMaximumLength");
            return (Criteria) this;
        }

        public Criteria andCharacterMaximumLengthGreaterThan(Long value) {
            addCriterion("CHARACTER_MAXIMUM_LENGTH >", value, "characterMaximumLength");
            return (Criteria) this;
        }

        public Criteria andCharacterMaximumLengthGreaterThanOrEqualTo(Long value) {
            addCriterion("CHARACTER_MAXIMUM_LENGTH >=", value, "characterMaximumLength");
            return (Criteria) this;
        }

        public Criteria andCharacterMaximumLengthLessThan(Long value) {
            addCriterion("CHARACTER_MAXIMUM_LENGTH <", value, "characterMaximumLength");
            return (Criteria) this;
        }

        public Criteria andCharacterMaximumLengthLessThanOrEqualTo(Long value) {
            addCriterion("CHARACTER_MAXIMUM_LENGTH <=", value, "characterMaximumLength");
            return (Criteria) this;
        }

        public Criteria andCharacterMaximumLengthIn(List<Long> values) {
            addCriterion("CHARACTER_MAXIMUM_LENGTH in", values, "characterMaximumLength");
            return (Criteria) this;
        }

        public Criteria andCharacterMaximumLengthNotIn(List<Long> values) {
            addCriterion("CHARACTER_MAXIMUM_LENGTH not in", values, "characterMaximumLength");
            return (Criteria) this;
        }

        public Criteria andCharacterMaximumLengthBetween(Long value1, Long value2) {
            addCriterion("CHARACTER_MAXIMUM_LENGTH between", value1, value2, "characterMaximumLength");
            return (Criteria) this;
        }

        public Criteria andCharacterMaximumLengthNotBetween(Long value1, Long value2) {
            addCriterion("CHARACTER_MAXIMUM_LENGTH not between", value1, value2, "characterMaximumLength");
            return (Criteria) this;
        }

        public Criteria andCharacterOctetLengthIsNull() {
            addCriterion("CHARACTER_OCTET_LENGTH is null");
            return (Criteria) this;
        }

        public Criteria andCharacterOctetLengthIsNotNull() {
            addCriterion("CHARACTER_OCTET_LENGTH is not null");
            return (Criteria) this;
        }

        public Criteria andCharacterOctetLengthEqualTo(Long value) {
            addCriterion("CHARACTER_OCTET_LENGTH =", value, "characterOctetLength");
            return (Criteria) this;
        }

        public Criteria andCharacterOctetLengthNotEqualTo(Long value) {
            addCriterion("CHARACTER_OCTET_LENGTH <>", value, "characterOctetLength");
            return (Criteria) this;
        }

        public Criteria andCharacterOctetLengthGreaterThan(Long value) {
            addCriterion("CHARACTER_OCTET_LENGTH >", value, "characterOctetLength");
            return (Criteria) this;
        }

        public Criteria andCharacterOctetLengthGreaterThanOrEqualTo(Long value) {
            addCriterion("CHARACTER_OCTET_LENGTH >=", value, "characterOctetLength");
            return (Criteria) this;
        }

        public Criteria andCharacterOctetLengthLessThan(Long value) {
            addCriterion("CHARACTER_OCTET_LENGTH <", value, "characterOctetLength");
            return (Criteria) this;
        }

        public Criteria andCharacterOctetLengthLessThanOrEqualTo(Long value) {
            addCriterion("CHARACTER_OCTET_LENGTH <=", value, "characterOctetLength");
            return (Criteria) this;
        }

        public Criteria andCharacterOctetLengthIn(List<Long> values) {
            addCriterion("CHARACTER_OCTET_LENGTH in", values, "characterOctetLength");
            return (Criteria) this;
        }

        public Criteria andCharacterOctetLengthNotIn(List<Long> values) {
            addCriterion("CHARACTER_OCTET_LENGTH not in", values, "characterOctetLength");
            return (Criteria) this;
        }

        public Criteria andCharacterOctetLengthBetween(Long value1, Long value2) {
            addCriterion("CHARACTER_OCTET_LENGTH between", value1, value2, "characterOctetLength");
            return (Criteria) this;
        }

        public Criteria andCharacterOctetLengthNotBetween(Long value1, Long value2) {
            addCriterion("CHARACTER_OCTET_LENGTH not between", value1, value2, "characterOctetLength");
            return (Criteria) this;
        }

        public Criteria andNumericPrecisionIsNull() {
            addCriterion("NUMERIC_PRECISION is null");
            return (Criteria) this;
        }

        public Criteria andNumericPrecisionIsNotNull() {
            addCriterion("NUMERIC_PRECISION is not null");
            return (Criteria) this;
        }

        public Criteria andNumericPrecisionEqualTo(Long value) {
            addCriterion("NUMERIC_PRECISION =", value, "numericPrecision");
            return (Criteria) this;
        }

        public Criteria andNumericPrecisionNotEqualTo(Long value) {
            addCriterion("NUMERIC_PRECISION <>", value, "numericPrecision");
            return (Criteria) this;
        }

        public Criteria andNumericPrecisionGreaterThan(Long value) {
            addCriterion("NUMERIC_PRECISION >", value, "numericPrecision");
            return (Criteria) this;
        }

        public Criteria andNumericPrecisionGreaterThanOrEqualTo(Long value) {
            addCriterion("NUMERIC_PRECISION >=", value, "numericPrecision");
            return (Criteria) this;
        }

        public Criteria andNumericPrecisionLessThan(Long value) {
            addCriterion("NUMERIC_PRECISION <", value, "numericPrecision");
            return (Criteria) this;
        }

        public Criteria andNumericPrecisionLessThanOrEqualTo(Long value) {
            addCriterion("NUMERIC_PRECISION <=", value, "numericPrecision");
            return (Criteria) this;
        }

        public Criteria andNumericPrecisionIn(List<Long> values) {
            addCriterion("NUMERIC_PRECISION in", values, "numericPrecision");
            return (Criteria) this;
        }

        public Criteria andNumericPrecisionNotIn(List<Long> values) {
            addCriterion("NUMERIC_PRECISION not in", values, "numericPrecision");
            return (Criteria) this;
        }

        public Criteria andNumericPrecisionBetween(Long value1, Long value2) {
            addCriterion("NUMERIC_PRECISION between", value1, value2, "numericPrecision");
            return (Criteria) this;
        }

        public Criteria andNumericPrecisionNotBetween(Long value1, Long value2) {
            addCriterion("NUMERIC_PRECISION not between", value1, value2, "numericPrecision");
            return (Criteria) this;
        }

        public Criteria andNumericScaleIsNull() {
            addCriterion("NUMERIC_SCALE is null");
            return (Criteria) this;
        }

        public Criteria andNumericScaleIsNotNull() {
            addCriterion("NUMERIC_SCALE is not null");
            return (Criteria) this;
        }

        public Criteria andNumericScaleEqualTo(Long value) {
            addCriterion("NUMERIC_SCALE =", value, "numericScale");
            return (Criteria) this;
        }

        public Criteria andNumericScaleNotEqualTo(Long value) {
            addCriterion("NUMERIC_SCALE <>", value, "numericScale");
            return (Criteria) this;
        }

        public Criteria andNumericScaleGreaterThan(Long value) {
            addCriterion("NUMERIC_SCALE >", value, "numericScale");
            return (Criteria) this;
        }

        public Criteria andNumericScaleGreaterThanOrEqualTo(Long value) {
            addCriterion("NUMERIC_SCALE >=", value, "numericScale");
            return (Criteria) this;
        }

        public Criteria andNumericScaleLessThan(Long value) {
            addCriterion("NUMERIC_SCALE <", value, "numericScale");
            return (Criteria) this;
        }

        public Criteria andNumericScaleLessThanOrEqualTo(Long value) {
            addCriterion("NUMERIC_SCALE <=", value, "numericScale");
            return (Criteria) this;
        }

        public Criteria andNumericScaleIn(List<Long> values) {
            addCriterion("NUMERIC_SCALE in", values, "numericScale");
            return (Criteria) this;
        }

        public Criteria andNumericScaleNotIn(List<Long> values) {
            addCriterion("NUMERIC_SCALE not in", values, "numericScale");
            return (Criteria) this;
        }

        public Criteria andNumericScaleBetween(Long value1, Long value2) {
            addCriterion("NUMERIC_SCALE between", value1, value2, "numericScale");
            return (Criteria) this;
        }

        public Criteria andNumericScaleNotBetween(Long value1, Long value2) {
            addCriterion("NUMERIC_SCALE not between", value1, value2, "numericScale");
            return (Criteria) this;
        }

        public Criteria andDatetimePrecisionIsNull() {
            addCriterion("DATETIME_PRECISION is null");
            return (Criteria) this;
        }

        public Criteria andDatetimePrecisionIsNotNull() {
            addCriterion("DATETIME_PRECISION is not null");
            return (Criteria) this;
        }

        public Criteria andDatetimePrecisionEqualTo(Long value) {
            addCriterion("DATETIME_PRECISION =", value, "datetimePrecision");
            return (Criteria) this;
        }

        public Criteria andDatetimePrecisionNotEqualTo(Long value) {
            addCriterion("DATETIME_PRECISION <>", value, "datetimePrecision");
            return (Criteria) this;
        }

        public Criteria andDatetimePrecisionGreaterThan(Long value) {
            addCriterion("DATETIME_PRECISION >", value, "datetimePrecision");
            return (Criteria) this;
        }

        public Criteria andDatetimePrecisionGreaterThanOrEqualTo(Long value) {
            addCriterion("DATETIME_PRECISION >=", value, "datetimePrecision");
            return (Criteria) this;
        }

        public Criteria andDatetimePrecisionLessThan(Long value) {
            addCriterion("DATETIME_PRECISION <", value, "datetimePrecision");
            return (Criteria) this;
        }

        public Criteria andDatetimePrecisionLessThanOrEqualTo(Long value) {
            addCriterion("DATETIME_PRECISION <=", value, "datetimePrecision");
            return (Criteria) this;
        }

        public Criteria andDatetimePrecisionIn(List<Long> values) {
            addCriterion("DATETIME_PRECISION in", values, "datetimePrecision");
            return (Criteria) this;
        }

        public Criteria andDatetimePrecisionNotIn(List<Long> values) {
            addCriterion("DATETIME_PRECISION not in", values, "datetimePrecision");
            return (Criteria) this;
        }

        public Criteria andDatetimePrecisionBetween(Long value1, Long value2) {
            addCriterion("DATETIME_PRECISION between", value1, value2, "datetimePrecision");
            return (Criteria) this;
        }

        public Criteria andDatetimePrecisionNotBetween(Long value1, Long value2) {
            addCriterion("DATETIME_PRECISION not between", value1, value2, "datetimePrecision");
            return (Criteria) this;
        }

        public Criteria andCharacterSetNameIsNull() {
            addCriterion("`CHARACTER_SET_NAME` is null");
            return (Criteria) this;
        }

        public Criteria andCharacterSetNameIsNotNull() {
            addCriterion("`CHARACTER_SET_NAME` is not null");
            return (Criteria) this;
        }

        public Criteria andCharacterSetNameEqualTo(String value) {
            addCriterion("`CHARACTER_SET_NAME` =", value, "characterSetName");
            return (Criteria) this;
        }

        public Criteria andCharacterSetNameNotEqualTo(String value) {
            addCriterion("`CHARACTER_SET_NAME` <>", value, "characterSetName");
            return (Criteria) this;
        }

        public Criteria andCharacterSetNameGreaterThan(String value) {
            addCriterion("`CHARACTER_SET_NAME` >", value, "characterSetName");
            return (Criteria) this;
        }

        public Criteria andCharacterSetNameGreaterThanOrEqualTo(String value) {
            addCriterion("`CHARACTER_SET_NAME` >=", value, "characterSetName");
            return (Criteria) this;
        }

        public Criteria andCharacterSetNameLessThan(String value) {
            addCriterion("`CHARACTER_SET_NAME` <", value, "characterSetName");
            return (Criteria) this;
        }

        public Criteria andCharacterSetNameLessThanOrEqualTo(String value) {
            addCriterion("`CHARACTER_SET_NAME` <=", value, "characterSetName");
            return (Criteria) this;
        }

        public Criteria andCharacterSetNameLike(String value) {
            addCriterion("`CHARACTER_SET_NAME` like", value, "characterSetName");
            return (Criteria) this;
        }

        public Criteria andCharacterSetNameNotLike(String value) {
            addCriterion("`CHARACTER_SET_NAME` not like", value, "characterSetName");
            return (Criteria) this;
        }

        public Criteria andCharacterSetNameIn(List<String> values) {
            addCriterion("`CHARACTER_SET_NAME` in", values, "characterSetName");
            return (Criteria) this;
        }

        public Criteria andCharacterSetNameNotIn(List<String> values) {
            addCriterion("`CHARACTER_SET_NAME` not in", values, "characterSetName");
            return (Criteria) this;
        }

        public Criteria andCharacterSetNameBetween(String value1, String value2) {
            addCriterion("`CHARACTER_SET_NAME` between", value1, value2, "characterSetName");
            return (Criteria) this;
        }

        public Criteria andCharacterSetNameNotBetween(String value1, String value2) {
            addCriterion("`CHARACTER_SET_NAME` not between", value1, value2, "characterSetName");
            return (Criteria) this;
        }

        public Criteria andCollationNameIsNull() {
            addCriterion("`COLLATION_NAME` is null");
            return (Criteria) this;
        }

        public Criteria andCollationNameIsNotNull() {
            addCriterion("`COLLATION_NAME` is not null");
            return (Criteria) this;
        }

        public Criteria andCollationNameEqualTo(String value) {
            addCriterion("`COLLATION_NAME` =", value, "collationName");
            return (Criteria) this;
        }

        public Criteria andCollationNameNotEqualTo(String value) {
            addCriterion("`COLLATION_NAME` <>", value, "collationName");
            return (Criteria) this;
        }

        public Criteria andCollationNameGreaterThan(String value) {
            addCriterion("`COLLATION_NAME` >", value, "collationName");
            return (Criteria) this;
        }

        public Criteria andCollationNameGreaterThanOrEqualTo(String value) {
            addCriterion("`COLLATION_NAME` >=", value, "collationName");
            return (Criteria) this;
        }

        public Criteria andCollationNameLessThan(String value) {
            addCriterion("`COLLATION_NAME` <", value, "collationName");
            return (Criteria) this;
        }

        public Criteria andCollationNameLessThanOrEqualTo(String value) {
            addCriterion("`COLLATION_NAME` <=", value, "collationName");
            return (Criteria) this;
        }

        public Criteria andCollationNameLike(String value) {
            addCriterion("`COLLATION_NAME` like", value, "collationName");
            return (Criteria) this;
        }

        public Criteria andCollationNameNotLike(String value) {
            addCriterion("`COLLATION_NAME` not like", value, "collationName");
            return (Criteria) this;
        }

        public Criteria andCollationNameIn(List<String> values) {
            addCriterion("`COLLATION_NAME` in", values, "collationName");
            return (Criteria) this;
        }

        public Criteria andCollationNameNotIn(List<String> values) {
            addCriterion("`COLLATION_NAME` not in", values, "collationName");
            return (Criteria) this;
        }

        public Criteria andCollationNameBetween(String value1, String value2) {
            addCriterion("`COLLATION_NAME` between", value1, value2, "collationName");
            return (Criteria) this;
        }

        public Criteria andCollationNameNotBetween(String value1, String value2) {
            addCriterion("`COLLATION_NAME` not between", value1, value2, "collationName");
            return (Criteria) this;
        }

        public Criteria andColumnKeyIsNull() {
            addCriterion("COLUMN_KEY is null");
            return (Criteria) this;
        }

        public Criteria andColumnKeyIsNotNull() {
            addCriterion("COLUMN_KEY is not null");
            return (Criteria) this;
        }

        public Criteria andColumnKeyEqualTo(String value) {
            addCriterion("COLUMN_KEY =", value, "columnKey");
            return (Criteria) this;
        }

        public Criteria andColumnKeyNotEqualTo(String value) {
            addCriterion("COLUMN_KEY <>", value, "columnKey");
            return (Criteria) this;
        }

        public Criteria andColumnKeyGreaterThan(String value) {
            addCriterion("COLUMN_KEY >", value, "columnKey");
            return (Criteria) this;
        }

        public Criteria andColumnKeyGreaterThanOrEqualTo(String value) {
            addCriterion("COLUMN_KEY >=", value, "columnKey");
            return (Criteria) this;
        }

        public Criteria andColumnKeyLessThan(String value) {
            addCriterion("COLUMN_KEY <", value, "columnKey");
            return (Criteria) this;
        }

        public Criteria andColumnKeyLessThanOrEqualTo(String value) {
            addCriterion("COLUMN_KEY <=", value, "columnKey");
            return (Criteria) this;
        }

        public Criteria andColumnKeyLike(String value) {
            addCriterion("COLUMN_KEY like", value, "columnKey");
            return (Criteria) this;
        }

        public Criteria andColumnKeyNotLike(String value) {
            addCriterion("COLUMN_KEY not like", value, "columnKey");
            return (Criteria) this;
        }

        public Criteria andColumnKeyIn(List<String> values) {
            addCriterion("COLUMN_KEY in", values, "columnKey");
            return (Criteria) this;
        }

        public Criteria andColumnKeyNotIn(List<String> values) {
            addCriterion("COLUMN_KEY not in", values, "columnKey");
            return (Criteria) this;
        }

        public Criteria andColumnKeyBetween(String value1, String value2) {
            addCriterion("COLUMN_KEY between", value1, value2, "columnKey");
            return (Criteria) this;
        }

        public Criteria andColumnKeyNotBetween(String value1, String value2) {
            addCriterion("COLUMN_KEY not between", value1, value2, "columnKey");
            return (Criteria) this;
        }

        public Criteria andExtraIsNull() {
            addCriterion("EXTRA is null");
            return (Criteria) this;
        }

        public Criteria andExtraIsNotNull() {
            addCriterion("EXTRA is not null");
            return (Criteria) this;
        }

        public Criteria andExtraEqualTo(String value) {
            addCriterion("EXTRA =", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotEqualTo(String value) {
            addCriterion("EXTRA <>", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraGreaterThan(String value) {
            addCriterion("EXTRA >", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraGreaterThanOrEqualTo(String value) {
            addCriterion("EXTRA >=", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLessThan(String value) {
            addCriterion("EXTRA <", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLessThanOrEqualTo(String value) {
            addCriterion("EXTRA <=", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraLike(String value) {
            addCriterion("EXTRA like", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotLike(String value) {
            addCriterion("EXTRA not like", value, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraIn(List<String> values) {
            addCriterion("EXTRA in", values, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotIn(List<String> values) {
            addCriterion("EXTRA not in", values, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraBetween(String value1, String value2) {
            addCriterion("EXTRA between", value1, value2, "extra");
            return (Criteria) this;
        }

        public Criteria andExtraNotBetween(String value1, String value2) {
            addCriterion("EXTRA not between", value1, value2, "extra");
            return (Criteria) this;
        }

        public Criteria andPrivilegesIsNull() {
            addCriterion("`PRIVILEGES` is null");
            return (Criteria) this;
        }

        public Criteria andPrivilegesIsNotNull() {
            addCriterion("`PRIVILEGES` is not null");
            return (Criteria) this;
        }

        public Criteria andPrivilegesEqualTo(String value) {
            addCriterion("`PRIVILEGES` =", value, "privileges");
            return (Criteria) this;
        }

        public Criteria andPrivilegesNotEqualTo(String value) {
            addCriterion("`PRIVILEGES` <>", value, "privileges");
            return (Criteria) this;
        }

        public Criteria andPrivilegesGreaterThan(String value) {
            addCriterion("`PRIVILEGES` >", value, "privileges");
            return (Criteria) this;
        }

        public Criteria andPrivilegesGreaterThanOrEqualTo(String value) {
            addCriterion("`PRIVILEGES` >=", value, "privileges");
            return (Criteria) this;
        }

        public Criteria andPrivilegesLessThan(String value) {
            addCriterion("`PRIVILEGES` <", value, "privileges");
            return (Criteria) this;
        }

        public Criteria andPrivilegesLessThanOrEqualTo(String value) {
            addCriterion("`PRIVILEGES` <=", value, "privileges");
            return (Criteria) this;
        }

        public Criteria andPrivilegesLike(String value) {
            addCriterion("`PRIVILEGES` like", value, "privileges");
            return (Criteria) this;
        }

        public Criteria andPrivilegesNotLike(String value) {
            addCriterion("`PRIVILEGES` not like", value, "privileges");
            return (Criteria) this;
        }

        public Criteria andPrivilegesIn(List<String> values) {
            addCriterion("`PRIVILEGES` in", values, "privileges");
            return (Criteria) this;
        }

        public Criteria andPrivilegesNotIn(List<String> values) {
            addCriterion("`PRIVILEGES` not in", values, "privileges");
            return (Criteria) this;
        }

        public Criteria andPrivilegesBetween(String value1, String value2) {
            addCriterion("`PRIVILEGES` between", value1, value2, "privileges");
            return (Criteria) this;
        }

        public Criteria andPrivilegesNotBetween(String value1, String value2) {
            addCriterion("`PRIVILEGES` not between", value1, value2, "privileges");
            return (Criteria) this;
        }

        public Criteria andColumnCommentIsNull() {
            addCriterion("COLUMN_COMMENT is null");
            return (Criteria) this;
        }

        public Criteria andColumnCommentIsNotNull() {
            addCriterion("COLUMN_COMMENT is not null");
            return (Criteria) this;
        }

        public Criteria andColumnCommentEqualTo(String value) {
            addCriterion("COLUMN_COMMENT =", value, "columnComment");
            return (Criteria) this;
        }

        public Criteria andColumnCommentNotEqualTo(String value) {
            addCriterion("COLUMN_COMMENT <>", value, "columnComment");
            return (Criteria) this;
        }

        public Criteria andColumnCommentGreaterThan(String value) {
            addCriterion("COLUMN_COMMENT >", value, "columnComment");
            return (Criteria) this;
        }

        public Criteria andColumnCommentGreaterThanOrEqualTo(String value) {
            addCriterion("COLUMN_COMMENT >=", value, "columnComment");
            return (Criteria) this;
        }

        public Criteria andColumnCommentLessThan(String value) {
            addCriterion("COLUMN_COMMENT <", value, "columnComment");
            return (Criteria) this;
        }

        public Criteria andColumnCommentLessThanOrEqualTo(String value) {
            addCriterion("COLUMN_COMMENT <=", value, "columnComment");
            return (Criteria) this;
        }

        public Criteria andColumnCommentLike(String value) {
            addCriterion("COLUMN_COMMENT like", value, "columnComment");
            return (Criteria) this;
        }

        public Criteria andColumnCommentNotLike(String value) {
            addCriterion("COLUMN_COMMENT not like", value, "columnComment");
            return (Criteria) this;
        }

        public Criteria andColumnCommentIn(List<String> values) {
            addCriterion("COLUMN_COMMENT in", values, "columnComment");
            return (Criteria) this;
        }

        public Criteria andColumnCommentNotIn(List<String> values) {
            addCriterion("COLUMN_COMMENT not in", values, "columnComment");
            return (Criteria) this;
        }

        public Criteria andColumnCommentBetween(String value1, String value2) {
            addCriterion("COLUMN_COMMENT between", value1, value2, "columnComment");
            return (Criteria) this;
        }

        public Criteria andColumnCommentNotBetween(String value1, String value2) {
            addCriterion("COLUMN_COMMENT not between", value1, value2, "columnComment");
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