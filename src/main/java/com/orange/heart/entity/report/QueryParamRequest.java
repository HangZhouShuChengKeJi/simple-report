package com.orange.heart.entity.report;

import com.orange.commons.utils.PageInfo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class QueryParamRequest extends PageInfo implements Serializable {

    private List<QueryParam> queryParams;

    private Map<String, Object> paramValueMap;

    public List<QueryParam> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(List<QueryParam> queryParams) {
        this.queryParams = queryParams;
    }

    public Map<String, Object> getParamValueMap() {
        return paramValueMap;
    }

    public void setParamValueMap(Map<String, Object> paramValueMap) {
        this.paramValueMap = paramValueMap;
    }
}