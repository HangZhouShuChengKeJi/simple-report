package com.orange.heart.util;

import com.orange.heart.entity.report.QueryParam;
import com.orange.heart.entity.report.QueryShow;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * sql工具类
 *
 * @author allen
 */

public class SqlUtil {


    /**
     * 把展示信息转换成集合
     *
     * @param showColumn
     *
     * @return
     */
    public static List<QueryShow> listShowColumn(String showColumn) {
        List<QueryShow> list = new ArrayList<QueryShow>();
        if (StringUtils.isBlank(showColumn)) {
            return list;
        }
        String[] showColumnArr = StringUtils.split(showColumn, ",");
        if (showColumnArr == null || showColumnArr.length <= 0) {
            return list;
        }
        for (int i = 0; i < showColumnArr.length; i++) {
            String[] arr = StringUtils.splitByWholeSeparatorPreserveAllTokens(showColumnArr[i], "|");
            if (arr == null || arr.length != 4) {
                continue;
            }
            QueryShow queryShow = new QueryShow();
            queryShow.setCode(arr[0]);
            queryShow.setName(arr[1]);
            queryShow.setShowEnum(arr[2]);
            queryShow.setType(arr[3]);
            list.add(queryShow);
        }
        return list;
    }

    /**
     * 把查询条件转换成集合
     *
     * @param paramSql
     *
     * @return
     */
    public static List<QueryParam> listQueryParam(String paramSql) {
        List<QueryParam> list = new ArrayList<QueryParam>();
        if (StringUtils.isBlank(paramSql)) {
            return list;
        }
        String[] paramArr = StringUtils.split(paramSql, ",");
        if (paramArr == null || paramArr.length <= 0) {
            return list;
        }
        for (int i = 0; i < paramArr.length; i++) {
            String[] arr = StringUtils.splitByWholeSeparatorPreserveAllTokens(paramArr[i], "|");
            if (arr == null || arr.length != 5) {
                continue;
            }
            QueryParam param = new QueryParam();
            param.setParamCode(arr[0]);
            param.setParamName(arr[1]);
            param.setType(arr[2]);
            param.setParamEnum(arr[3]);
            param.setIsMust(arr[4]);
            list.add(param);
        }
        return list;
    }

    /**
     * 把查询条件集合转换成字符串
     *
     * @param queryParamList
     *
     * @return
     */
    public static String convertQueryParamListToString(List<QueryParam> queryParamList) {
        if (CollectionUtils.isEmpty(queryParamList)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (QueryParam queryParam : queryParamList) {
            if (StringUtils.isNotBlank(sb.toString())) {
                sb.append(",");
            }
            sb.append(queryParam.getParamCode()).append("|");
            sb.append(queryParam.getParamName()).append("|");
            sb.append(queryParam.getType()).append("|");
            sb.append(queryParam.getParamEnum()).append("|");
            sb.append(queryParam.getIsMust());
        }
        return sb.toString();
    }

    /**
     * 把展示集合转换成字符串
     *
     * @param showColumnList
     *
     * @return
     */
    public static String convertShowColumnListToString(List<QueryShow> showColumnList) {
        if (CollectionUtils.isEmpty(showColumnList)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (QueryShow show : showColumnList) {
            if (StringUtils.isNotBlank(sb.toString())) {
                sb.append(",");
            }
            sb.append(show.getCode()).append("|");
            sb.append(show.getName()).append("|");
            sb.append(show.getShowEnum()).append("|");
            sb.append(show.getType());
        }
        return sb.toString();
    }

    /**
     * 组装sql
     *
     * @param sqlTemplate
     * @param paramMap
     *
     * @return
     */
    public static  String getSql(String sqlTemplate, Map<String, Object> paramMap) {
        if (StringUtils.isBlank(sqlTemplate)) {
            return sqlTemplate;
        }
        if (paramMap == null) {
            return sqlTemplate;
        }

        VelocityEngine velocityEngine = new VelocityEngine();
        StringWriter velocityWriter = new StringWriter();
        // 取得velocity的上下文context
        VelocityContext context = new VelocityContext();
        // 把数据填入上下文
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            context.put(entry.getKey(), entry.getValue());
        }
        velocityEngine.evaluate(context, velocityWriter, "", sqlTemplate);
        return velocityWriter.toString();
    }

}