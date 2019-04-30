/**
 * 
 */
package com.orange.heart.service;

import com.orange.commons.utils.PageInfo;
import com.orange.heart.entity.CrudQueryVo;
import com.orange.heart.entity.RCrudColumn;

import java.util.List;
import java.util.Map;

/**
 * 
 */
public interface ReportCommonService {

	List<Map<String, Object>> queryList(String tableName, List<CrudQueryVo> crudQueryVoList,
                                        List<RCrudColumn> resultList, PageInfo pageInfo);

}
