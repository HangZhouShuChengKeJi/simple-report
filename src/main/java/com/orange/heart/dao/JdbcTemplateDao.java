package com.orange.heart.dao;

import com.orange.commons.utils.PageInfo;
import com.orange.commons.utils.RequestUtil;
import com.orange.heart.common.CTBException;
import com.orange.heart.constant.HeartConstant;
import com.orange.heart.entity.CrudQueryVo;
import com.orange.heart.entity.RCrudColumn;
import com.orange.heart.entity.RReportSql;
import com.orange.heart.entity.report.QueryParam;
import com.orange.heart.entity.report.QueryParamRequest;
import com.orange.heart.entity.report.QueryShow;
import com.orange.heart.entity.report.QueryShowVo;
import com.orange.heart.util.DateUtil;
import com.orange.heart.util.SqlUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplateDao {

    /**
     * 统计条数SQL
     */
    private static final String COUNT_SQL = "select count(*) from $TABLENAME$ $WHERE$";

    /**
     * 查询SQL
     */
    private static final String QUERY_SQL = "select $SELECT$ from $TABLENAME$ $WHERE$ $ORDERBY$ $LIMIT$ $OFFSET$";

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public JdbcTemplateDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public List<Map<String, Object>> queryByPaging(List<CrudQueryVo> crudQueryVoList, String tableName,
                                                   PageInfo pageInfo, List<RCrudColumn> resultList) {
        if (null == tableName) {
            return new ArrayList<Map<String, Object>>();
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String where = getWhere(crudQueryVoList, paramMap);
        String orderBy = " order by id desc ";
        String limit = "";
        String offset = "";
        String select = getSelectSql(resultList);
        if (null != pageInfo && pageInfo.getTotalCount() > 0) {
            limit = " limit " + pageInfo.getLimit();
            offset = " offset " + pageInfo.getOffset();
        }
        String sql = QUERY_SQL.replace("$SELECT$", select).replace("$TABLENAME$", tableName).replace("$WHERE$", where)
                              .replace("$ORDERBY$", orderBy).replace("$LIMIT$", limit).replace("$OFFSET$", offset);
        return namedParameterJdbcTemplate.queryForList(sql, paramMap);
    }

    public String getSelectSql(List<RCrudColumn> resultList) {
        if (CollectionUtils.isEmpty(resultList)) {
            return "*";
        }
        StringBuilder selBuilder = new StringBuilder("* ");
        for (RCrudColumn crudColumn : resultList) {
            if (crudColumn.getColumnType() == null) {
                continue;
            }
            switch (crudColumn.getColumnType()) {
                // 日期型
                case 4:
                    if (StringUtils.isNotBlank(crudColumn.getQueryFormat())) {
                        selBuilder.append(", DATE_FORMAT(").append(crudColumn.getColumnId()).append(",'")
                                  .append(crudColumn.getQueryFormat()).append("') as _date_format_")
                                  .append(crudColumn.getColumnId());
                    }
                    break;
                default:
                    break;
            }
        }
        return selBuilder.toString();
    }

    @SuppressWarnings("deprecation")
    public int count(List<CrudQueryVo> crudQueryVoList, String tableName) {
        if (StringUtils.isBlank(tableName)) {
            return 0;
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        String where = getWhere(crudQueryVoList, paramMap);
        String sql = COUNT_SQL.replace("$TABLENAME$", tableName).replace("$WHERE$", where);
        return queryForInt(sql, paramMap);
    }

    private String getWhere(List<CrudQueryVo> queryList, Map<String, Object> paramMap) {
        StringBuilder sbBuilder = new StringBuilder();
        if (null != queryList) {
            for (CrudQueryVo crudQueryVo : queryList) {
                if (StringUtils.isBlank(crudQueryVo.getInputValue()) && isEmpty(crudQueryVo.getInputValueList())) {
                    continue;
                }
                if (sbBuilder.length() == 0) {
                    sbBuilder.append(" where ");
                } else {
                    sbBuilder.append(" and ");
                }
                if (crudQueryVo.getColumnType() != null) {
                    switch (crudQueryVo.getColumnType()) {
                        case 2:
                            String startTime = crudQueryVo.getInputValueList().get(0);
                            String endTime = crudQueryVo.getInputValueList().get(1);
                            boolean hasStart = false;
                            if (StringUtils.isNotBlank(startTime)) {
                                paramMap.put("_qry_startTime" + crudQueryVo.getColumnId(), startTime);
                                sbBuilder.append(crudQueryVo.getColumnId() + " >= :_qry_startTime"
                                        + crudQueryVo.getColumnId());
                                hasStart = true;
                            }
                            if (StringUtils.isNotBlank(endTime)) {
                                if (hasStart) {
                                    sbBuilder.append(" and ");
                                }
                                paramMap.put("_qry_endTime" + crudQueryVo.getColumnId(), endTime);
                                sbBuilder
                                        .append(crudQueryVo.getColumnId() + " < :_qry_endTime" + crudQueryVo.getColumnId());
                            }

                            break;
                        default:
                            paramMap.put(crudQueryVo.getColumnId(), crudQueryVo.getInputValue());
                            sbBuilder.append(crudQueryVo.getColumnId() + " = :" + crudQueryVo.getColumnId());
                    }
                } else {
                    paramMap.put(crudQueryVo.getColumnId(), crudQueryVo.getInputValue());
                    sbBuilder.append(crudQueryVo.getColumnId() + " = :" + crudQueryVo.getColumnId());
                }
            }
        }
        return sbBuilder.toString();
    }

    private boolean isEmpty(List<String> inputValueList) {
        if (CollectionUtils.isEmpty(inputValueList)) {
            return true;
        }
        boolean allBlank = true;
        for (String string : inputValueList) {
            if (StringUtils.isNotBlank(string)) {
                allBlank = false;
            }
        }
        return allBlank;
    }

    public List<Map<String, Object>> queryByPagingBySql(String sql, Map<String, Object> paramMap) {
        if (StringUtils.isBlank(sql)) {
            return new ArrayList<Map<String, Object>>();
        }
        if (paramMap == null) {
            paramMap = new HashMap<String, Object>();
        }
        return namedParameterJdbcTemplate.queryForList(sql, paramMap);
    }

    @SuppressWarnings("deprecation")
    public int countBySql(String sql, Map<String, Object> paramMap) {
        if (StringUtils.isBlank(sql)) {
            return 0;
        }
        if (paramMap == null) {
            paramMap = new HashMap<String, Object>();
        }

        return queryForInt(sql, paramMap);
    }

    /**
     * 查询一页数据
     *
     * @param rReportSql
     * @param query
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public QueryShowVo queryByPaging(RReportSql rReportSql, QueryParamRequest query) {
        QueryShowVo vo = new QueryShowVo();
        if (rReportSql == null) {
            return vo;
        }
        String sql = rReportSql.getSql();
        if (StringUtils.isBlank(sql)) {
            return vo;
        }
        if (StringUtils.isBlank(rReportSql.getShowColumn())) {
            return vo;
        }
        List<QueryShow> queryShowList = SqlUtil.listShowColumn(rReportSql.getShowColumn());
        if (CollectionUtils.isEmpty(queryShowList)) {
            return vo;
        }
        Map<String, Object> paramMap = getParamMap(query);
        // 判断是否需要 // 通过VelocityEngine替换参数
        boolean isVelocity = false;
        if (StringUtils.startsWithIgnoreCase(sql.trim(), "##velocity")) {
            sql = StringUtils.replace(sql,"##velocity", "");
            // 整理参数
            sql = SqlUtil.getSql(sql, paramMap);
            isVelocity = true;
        }
        // 查询总数
        StringBuilder countBuilder = new StringBuilder();
        countBuilder.append("select count(1) from ( ").append(sql).append(" ) ctb_r");
        // 查询一页
        StringBuilder sbBuilder = new StringBuilder();
        sbBuilder.append("select * from ( ").append(sql).append(" ) ctb_r");
        // 增加查询条件
        if (!isVelocity) {
            String tempStr = addSqlParam(query);
            sbBuilder.append(tempStr);
            countBuilder.append(tempStr);
        }

        query.setTotalCount(queryForInt(countBuilder.toString(), paramMap));

        // 拼装 limit
        String limit = "";
        String offset = "";
        if (null != query && query.getTotalCount() > 0) {
            limit = " limit " + query.getLimit();
            offset = " offset " + query.getOffset();
            sbBuilder.append(limit).append(offset);
        }
        List<Map<String, Object>> maps = namedParameterJdbcTemplate.queryForList(sbBuilder.toString(), paramMap);

        // 展示数据
        List<List<QueryShow>> dataList = new ArrayList<List<QueryShow>>();
        if (CollectionUtils.isNotEmpty(maps)) {
            // 数据循环
            for (Map<String, Object> map : maps) {
                List<QueryShow> column = new ArrayList<QueryShow>();
                // 展示值
                for (QueryShow qs : queryShowList) {
                    QueryShow queryShow = new QueryShow();
                    queryShow.setCode(qs.getCode());
                    queryShow.setName(qs.getName());
                    queryShow.setType(qs.getType());
                    queryShow.setShowEnum(qs.getShowEnum());

                    if(StringUtils.equals("mobile", qs.getCode().toLowerCase())) {
                        String mobile = String.valueOf(map.get(qs.getCode()));
                        if(StringUtils.isNotBlank(mobile)) {
                            queryShow.setValue(mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
                        }
                    }else {
                        queryShow.setValue(map.get(qs.getCode()));
                    }
                    column.add(queryShow);
                }
                dataList.add(column);
            }
        }
        vo.setDataList(dataList);
        return vo;
    }

    /**
     * 组装sql查询条件
     *
     * @param query
     *
     * @return
     */
    private String addSqlParam(QueryParamRequest query) {
        if (query == null || query.getQueryParams() == null || query.getParamValueMap() == null) {
            return "";
        }
        Map<String, Object> paramValueMap = query.getParamValueMap();
        List<QueryParam> queryParams = query.getQueryParams();
        StringBuffer sqBf = new StringBuffer();
        for (QueryParam queryParam : queryParams) {
            if (StringUtils.equals(queryParam.getType(), HeartConstant.PARAM_TYPE.DATE_TIME_RANGE) || StringUtils.equals(queryParam.getType(), HeartConstant.PARAM_TYPE.NUMBER_RANGE)) {
                String tempStartKey = queryParam.getParamCode() + "_start";
                appendSqlStr(sqBf, paramValueMap.get(tempStartKey), queryParam, ">=");
                String tempEndKey = queryParam.getParamCode() + "_end";
                appendSqlStr(sqBf, paramValueMap.get(tempEndKey), queryParam, "<=");
            } else {
                appendSqlStr(sqBf, paramValueMap.get(queryParam.getParamCode()), queryParam, "=");
            }
        }
        return sqBf.toString();
    }

    private void appendSqlStr(StringBuffer sqBf, Object strObj, QueryParam param, String connectorStr) {
        if (strObj == null || param == null || sqBf == null) {
            return;
        }
        String str = String.valueOf(strObj);
        if (StringUtils.isBlank(str)) {
            return;
        }

        if (StringUtils.isBlank(sqBf.toString())) {
            sqBf.append(" where ");
        } else {
            sqBf.append(" and ");
        }
        sqBf.append("ctb_r.").append(param.getParamCode());
        if (StringUtils.isBlank(connectorStr)) {
            connectorStr = "=";
        }
        sqBf.append(" ").append(connectorStr).append(" ");
        if (StringUtils.equals(param.getType(), HeartConstant.PARAM_TYPE.DATE_TIME_RANGE) || StringUtils.equals(param.getType(), HeartConstant.PARAM_TYPE.STR)) {
            sqBf.append("'").append(str).append("'");
        } else {
            sqBf.append(str);
        }
    }

    /**
     * 执行查询 sql ， 并返回 int
     *
     * @param sql
     * @param paramMap
     *
     * @return
     */
    private int queryForInt(String sql, Map<String, ?> paramMap) {
        Integer res = namedParameterJdbcTemplate.queryForObject(sql, paramMap, new SingleColumnRowMapper<>(Integer.class));
        if (res == null) {
            return 0;
        }
        return res;
    }


    /**
     * 获取参数
     *
     * @param query
     *
     * @return
     */
    private Map<String, Object> getParamMap(QueryParamRequest query) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (query == null || CollectionUtils.isEmpty(query.getQueryParams())) {
            return paramMap;
        }
        Map<String, Object> paramValueMap = query.getParamValueMap();
        if (paramValueMap == null) {
            return paramMap;
        }
        return query.getParamValueMap();

        //		// 组装参数
        //		for (QueryParam queryParam : query.getQueryParams()) {
        //			String tempValue = (String) paramValueMap.get(queryParam.getParamCode());
        //			if (StringUtils.isBlank(tempValue)) {
        //				continue;
        //			}
        //			// 字符串
        //			if (StringUtils.equals(queryParam.getType(), HeartConstant.param_type.STR)) {
        //				paramMap.put(queryParam.getParamCode(), tempValue);
        //			} else if (StringUtils.equals(queryParam.getType(), HeartConstant.param_type.NUMBER)) {
        //				// 整型
        //				try {
        //					paramMap.put(queryParam.getParamCode(), Integer.parseInt(tempValue));
        //				} catch (Throwable t) {
        //
        //				}
        //			} else if (StringUtils.equals(queryParam.getType(), HeartConstant.param_type.DATE_TIME)) {
        //				// 日期
        //				try {
        //					paramMap.put(queryParam.getParamCode(), DateUtil.parseDateYmd(tempValue));
        //				} catch (Exception e) {
        //				}
        //			} else {
        //				//  默认
        //				paramMap.put(queryParam.getParamCode(), tempValue);
        //			}
        //		}
        //		return paramMap;
    }

    private String getPageSql(String sql, QueryParamRequest query) {
        if (StringUtils.isBlank(sql)) {
            return sql;
        }
        List<QueryParam> paramList = query.getQueryParams();
        if (CollectionUtils.isEmpty(paramList)) {
            return sql;
        }
        Map<String, Object> paramMap = query.getParamValueMap();
        if (paramMap == null) {
            paramMap = new HashMap<String, Object>();
        }

        for (QueryParam param : paramList) {
            // 截取参数 举例：  <#loginTime: u.update_time > :loginTime loginTime#>
            String paramStartStr = "<#" + param.getParamCode();
            String endStartStr = param.getParamCode() + "#>";
            int start = StringUtils.indexOf(sql, paramStartStr);
            int end = StringUtils.indexOf(sql, endStartStr);
            if (start == -1 || end == -1) {
                throw new CTBException("sql和参数名(" + param.getParamCode() + ")不匹配 sql:" + sql);
            }
            end = end + endStartStr.length();
            String paramStr = StringUtils.substring(sql, start, end);
            // 参数值
            Object o = paramMap.get(param.getParamCode());
            // 必填项
            if (StringUtils.equals(param.getIsMust(), String.valueOf(HeartConstant.YES_NO.YES))) {

                if (o == null) {
                    throw new CTBException("必填项没有写全:" + param.getParamName());
                }
                // 替换值
                sql = StringUtils.replace(sql, paramStartStr, "");
                sql = StringUtils.replace(sql, endStartStr, "");
            } else {
                // 选填项
                if (o == null) {
                    // 替换值
                    sql = StringUtils.replace(sql, paramStr, "");
                } else {
                    // 替换值
                    sql = StringUtils.replace(sql, paramStartStr, "");
                    sql = StringUtils.replace(sql, endStartStr, "");
                }
            }
        }
        return sql;
    }

    /**
     * 插入
     *
     * @param sql
     * @param paramMap
     *
     * @return
     */
    public int insertSql(String sql, Map<String, Object> paramMap) {
        if (StringUtils.isBlank(sql)) {
            return 0;
        }
        if (paramMap == null) {
            paramMap = new HashMap<String, Object>();
        }

        return namedParameterJdbcTemplate.update(sql, paramMap);
    }
}
