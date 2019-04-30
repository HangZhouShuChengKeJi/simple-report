package com.orange.heart.service;

import com.orange.heart.entity.RUser;
import com.orange.heart.entity.RUserExample;

import java.util.List;

/**
 * 用户信息
 * @author zhoup
 *
 */

public interface RUserService {
	
	/**
	 *用户
	 * @param query
	 * @return
	 */
	List<RUser> queryRUser(RUserExample query);

	/**
	 * 根据登录账号查询用户
	 * @param loginAccount
	 * @return
	 */
	RUser getUserByMobile(String loginAccount);
	
	/**
	 * 根据登录账号查询用户
	 * @param loginAccount
	 * @return
	 */
	RUser getUserByEmail(String loginAccount);
	
	
	/**
	 * 根据用户ID查询用户
	 * @param userId
	 * @return
	 */
	RUser getUserByUserId(Integer userId);

	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	int addUser(RUser rcUser);
	
	/**
	 * 根据用户Id删除用户
	 * @param userId
	 * @return
	 */
	int deleteUserByUserId(Integer userId);
	
	/**
	 * 根据用户Id修改用户信息
	 * @param userId
	 * @return
	 */
	int updateUser(RUser rcUser);
	
	
}
