package com.orange.heart.service.impl;

import com.orange.commons.utils.PageInfo;
import com.orange.heart.dao.JdbcTemplateDao;
import com.orange.heart.entity.CrudQueryVo;
import com.orange.heart.entity.RCrudColumn;
import com.orange.heart.service.ReportCommonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class ReportCommonServiceImpl implements ReportCommonService {

	@Resource
	private JdbcTemplateDao jdbcTemplateDao;

	public List<Map<String, Object>> queryList(String tableName, List<CrudQueryVo> crudQueryVoList,
                                               List<RCrudColumn> resultList, PageInfo pageInfo) {
		int rs = jdbcTemplateDao.count(crudQueryVoList, tableName);
		if (rs == 0) {
			return null;
		}
		pageInfo.setTotalCount(rs);
		// limit为空
		pageInfo.setPageNo(pageInfo.getPageNo());
		return jdbcTemplateDao.queryByPaging(crudQueryVoList, tableName, pageInfo, resultList);
	}

}
