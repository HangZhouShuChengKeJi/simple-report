package com.orange.heart.entity.report;

import java.io.Serializable;
import java.util.List;

public class QueryShowVo implements Serializable {
    /**
     * 展示数据
     */
    private List<List<QueryShow>> dataList;

    public List<List<QueryShow>> getDataList() {
        return dataList;
    }

    public void setDataList(List<List<QueryShow>> dataList) {
        this.dataList = dataList;
    }
}