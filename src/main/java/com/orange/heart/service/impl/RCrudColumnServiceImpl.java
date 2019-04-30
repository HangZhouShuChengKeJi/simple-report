package com.orange.heart.service.impl;

import com.orange.heart.dao.ColumnsDAO;
import com.orange.heart.dao.RCrudColumnDAO;
import com.orange.heart.entity.Columns;
import com.orange.heart.entity.ColumnsExample;
import com.orange.heart.entity.RCrudColumn;
import com.orange.heart.entity.RCrudColumnExample;
import com.orange.heart.service.RCrudColumnService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class RCrudColumnServiceImpl implements RCrudColumnService {

	@Resource
	private RCrudColumnDAO rCrudColumnDAO;
	
	@Resource
	private ColumnsDAO columnsDAO;
	

	@Override
	public int save(List<RCrudColumn> insertList, List<RCrudColumn> updateList, List<RCrudColumn> deleteList) {
		int rs = 0;
		if (CollectionUtils.isNotEmpty(insertList)) {
			for (RCrudColumn rCrudColumn : insertList) {
				rs = rs + rCrudColumnDAO.insert(rCrudColumn);
			}
		}
		if (CollectionUtils.isNotEmpty(updateList)) {
			for (RCrudColumn rCrudColumn : updateList) {
				rs = rs + rCrudColumnDAO.updateByPrimaryKeySelective(rCrudColumn);
			}
		}
		if (CollectionUtils.isNotEmpty(deleteList)) {
			List<Integer> idList = new ArrayList<Integer>();
			for (RCrudColumn rCrudColumn : updateList) {
				idList.add(rCrudColumn.getId());
			}
			RCrudColumnExample example = new RCrudColumnExample();
			example.createCriteria().andIdIn(idList);
			rs = rs + rCrudColumnDAO.deleteByExample(example);
		}
		return rs;
	}
	
	
	public List<Columns> selectByExampleWithoutBLOBs(ColumnsExample example){
		return columnsDAO.selectByExample(example);
	}
}
