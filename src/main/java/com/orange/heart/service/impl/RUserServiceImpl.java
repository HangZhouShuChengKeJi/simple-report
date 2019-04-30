package com.orange.heart.service.impl;

import com.orange.heart.dao.RUserDAO;
import com.orange.heart.entity.RUser;
import com.orange.heart.entity.RUserExample;
import com.orange.heart.service.RUserService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("rUserService")
public class RUserServiceImpl implements RUserService {

	@Resource
	private RUserDAO rUserDAO;

	/**
	 * 根据登录账号查询用户
	 * 
	 * @param loginAccount
	 * @return
	 */
	@Override
	public RUser getUserByMobile(String mobile) {
		if (StringUtils.isBlank(mobile)) {
			return null;
		}
		RUserExample example = new RUserExample();
		example.createCriteria().andMobileEqualTo(mobile);
		List<RUser> userList = rUserDAO.selectByExample(example);
		if (CollectionUtils.isNotEmpty(userList)) {
			return userList.get(0);
		}
		return null;
	}
	
	/**
	 * 根据登录账号查询用户
	 * 
	 * @param loginAccount
	 * @return
	 */
	@Override
	public RUser getUserByEmail(String eamil) {
		if (StringUtils.isBlank(eamil)) {
			return null;
		}
		RUserExample example = new RUserExample();
		example.createCriteria().andEmailEqualTo(eamil);
		List<RUser> userList = rUserDAO.selectByExample(example);
		if (CollectionUtils.isNotEmpty(userList)) {
			return userList.get(0);
		}
		return null;
	}	


	/**
	 * 根据用户ID查询用户
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public RUser getUserByUserId(Integer userId) {
		if (userId == null) {
			return null;
		}
		return rUserDAO.selectByPrimaryKey(userId);
	}

	/**
	 * 查询用户信息
	 * 
	 * @param query
	 * @return
	 */
	public List<RUser> queryRUser(RUserExample query) {
		if (query == null) {
			return null;
		}
		query.setTotalCount(rUserDAO.countByExample(query));
		return rUserDAO.selectByExample(query);
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	@Override
	public int addUser(RUser user) {
		if (user == null) {
			return 0;
		}
		return rUserDAO.insertSelective(user);
	}

	/**
	 * 
	 * 删除用户
	 * 
	 * @param userId
	 * @return
	 */
	@Override
	public int deleteUserByUserId(Integer userId) {
		if (userId == 0) {
			return 0;
		}
		return rUserDAO.deleteByPrimaryKey(userId);
	}

	@Override
	public int updateUser(RUser rcUser) {
		int userId = rcUser.getId();
		RUserExample example = new RUserExample();
		example.createCriteria().andIdEqualTo(userId);
		return rUserDAO.updateByExampleSelective(rcUser, example);
	}


}
