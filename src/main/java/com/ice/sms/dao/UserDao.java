/**
 * 文 件 名:  UserDao
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  huangsongbo
 * 修改时间:  2017/12/5
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.dao;

import com.ice.sms.entity.UserDo;

import java.util.List;
import java.util.Map;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author huangsongbo
 * @version 2017/12/5
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface UserDao
{
	/**
	 * 根据用户帐号查询
	 * @param userId
	 * @return
	 */
	UserDo queryUserById (String userId);

	/**
	 * 新增用户信息
	 * @param userDo
	 */
	void addUser (UserDo userDo);

	/**
	 * 修改用户信息
	 * @param userDo
	 */
	void updateUser (UserDo userDo);

	/**
	 * 批量删除用户信息
	 * @param userIdList
	 */
	void batchDeleteUser (List<String> userIdList);

	/**
	 * 分页查询统计个数
	 * @param params
	 * @return
	 */
	int count (Map<String,Object> params);

	/**
	 * 查询用户列表
	 * @param params
	 * @return
	 */
	List<UserDo> queryUserList(Map<String,Object> params);
}