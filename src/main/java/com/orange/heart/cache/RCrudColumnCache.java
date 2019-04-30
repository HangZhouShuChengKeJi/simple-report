package com.orange.heart.cache;

import com.orange.heart.dao.RCrudColumnDAO;
import com.orange.heart.entity.RCrudColumn;
import com.orange.heart.entity.RCrudColumnExample;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author allen
 * 
 */
@Service("rCrudColumnCache")
public class RCrudColumnCache implements InitializingBean {

	@Resource
	private RCrudColumnDAO rCrudColumnDAO;

	private static Map<String, List<RCrudColumn>> columnInfoMap = new HashMap<String, List<RCrudColumn>>();

	private static Map<String, String>				allKeywordMap	= new HashMap<String, String>();

	private Object									lock			= new Object();

	@Override
	public void afterPropertiesSet() throws Exception {
		refresh();
	}

	public void refresh() {
		RCrudColumnExample example = new RCrudColumnExample();
		example.setOrderByClause("keyword, sort, id");
		List<RCrudColumn> columnInfoList = rCrudColumnDAO.selectByExample(example);
		Map<String, List<RCrudColumn>> tempMap = new HashMap<String, List<RCrudColumn>>();
		if (CollectionUtils.isNotEmpty(columnInfoList)) {
			for (RCrudColumn columnInfo : columnInfoList) {
				List<RCrudColumn> subList = tempMap.get(columnInfo.getKeyword());
				if (CollectionUtils.isEmpty(subList)) {
					subList = new ArrayList<RCrudColumn>();
					tempMap.put(columnInfo.getKeyword(), subList);
					allKeywordMap.put(columnInfo.getKeyword(), columnInfo.getKeyword());
				}
				subList.add(columnInfo);
				
			}
		}
		synchronized (lock) {
			columnInfoMap = tempMap;
		}
	}

	/**
	 * 
	 * @param keyword
	 * @return
	 */
	public Map<String, String> getAllKeyword() {
		return allKeywordMap;
	}

	/**
	 * 
	 * @param keyword
	 * @return
	 */
	public List<RCrudColumn> getAllListByKeyword(String keyword) {
		return getListByKeyword(keyword, 99);
	}

	/**
	 * 
	 * @param keyword
	 * @return
	 */
	public List<RCrudColumn> getAddListByKeyword(String keyword) {
		return getListByKeyword(keyword, 1);
	}

	/**
	 * 
	 * @param keyword
	 * @return
	 */
	public List<RCrudColumn> getEditListByKeyword(String keyword) {
		return getListByKeyword(keyword, 2);
	}

	/**
	 * 
	 * @param keyword
	 * @return
	 */
	public List<RCrudColumn> getShowListByKeyword(String keyword) {
		return getListByKeyword(keyword, 3);
	}

	/**
	 * 
	 * @param keyword
	 * @return
	 */
	public List<RCrudColumn> getDetailListByKeyword(String keyword) {
		return getListByKeyword(keyword, 4);
	}

	/**
	 * 
	 * @param keyword
	 * @return
	 */
	public List<RCrudColumn> getQueryListByKeyword(String keyword) {
		return getListByKeyword(keyword, 5);
	}

	private List<RCrudColumn> getListByKeyword(String keyword, int type) {
		List<RCrudColumn> columnList = columnInfoMap.get(keyword);
		List<RCrudColumn> resultList = new ArrayList<RCrudColumn>();
		if (CollectionUtils.isNotEmpty(columnList)) {
			for (RCrudColumn crudColumn : columnList) {
				if ((type == 1 && crudColumn.getAddFlag() != null && crudColumn.getAddFlag() == 1)
						|| (type == 2 && crudColumn.getEditFlag() != null && crudColumn.getEditFlag() == 1)
						|| (type == 3 && crudColumn.getListFlag() != null && crudColumn.getListFlag() == 1)
						|| (type == 4 && crudColumn.getDetailFlag() != null && crudColumn.getDetailFlag() == 1)
						|| (type == 5 && crudColumn.getQueryFlag() != null && crudColumn.getQueryFlag() == 1)
						|| (type == 99)) {
					RCrudColumn newColumn = new RCrudColumn();
					try {
						BeanUtils.copyProperties(newColumn, crudColumn);
					} catch (Exception e) {
						continue;
					}
					resultList.add(newColumn);
				}
			}
		}
		return resultList;
	}
}
