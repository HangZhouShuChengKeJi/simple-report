/**
 * 
 */
package com.orange.heart.service;

import com.orange.heart.entity.Columns;
import com.orange.heart.entity.ColumnsExample;
import com.orange.heart.entity.RCrudColumn;

import java.util.List;

public interface RCrudColumnService {

	int save(List<RCrudColumn> insertList, List<RCrudColumn> updateList, List<RCrudColumn> deleteList);
	
	
	List<Columns> selectByExampleWithoutBLOBs(ColumnsExample example);
}
