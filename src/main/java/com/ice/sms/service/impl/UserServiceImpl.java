/**
 * 文 件 名:  UserServiceImpl
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  huangsongbo
 * 修改时间:  2017/12/5
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.service.impl;

import com.ice.sms.common.base.ResultInfo;
import com.ice.sms.common.constant.Constant;
import com.ice.sms.common.exception.SMSException;
import com.ice.sms.dao.UserDao;
import com.ice.sms.dto.user.request.BatchDelUserReq;
import com.ice.sms.dto.user.response.CountUserResp;
import com.ice.sms.dto.user.request.QueryByIdReq;
import com.ice.sms.dto.user.request.QueryUserListReq;
import com.ice.sms.dto.user.response.QueryByIdResp;
import com.ice.sms.dto.user.response.QueryUserListResp;
import com.ice.sms.dto.user.vo.UserVo;
import com.ice.sms.entity.UserDo;
import com.ice.sms.security.PasswordHelper;
import com.ice.sms.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
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
@Service
public class UserServiceImpl implements UserService
{
	private static final Logger LOGGER = LogManager.getLogger (UserServiceImpl.class);
	@Autowired
	private UserDao userDao;

	@Override
	public QueryByIdResp queryUserById (QueryByIdReq req)
	{
		LOGGER.debug (String.format ("SMS.UserServiceImpl.queryUserById.request:%s", req));
		QueryByIdResp resp = new QueryByIdResp ();
		UserDo userDo = null;
		try
		{
			userDo = userDao.queryUserById (req.getUserId ());
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.UserServiceImpl.queryUserById.Exception", e);
			throw new SMSException (Constant.Common.SQL_EXCEPTION_CODE, Constant.Common.SQL_EXCEPTION_DESC);
		}

		UserVo userVo = new UserVo ();
		userVo.setUserId (userDo.getUserId ());
		userVo.setUserName (userDo.getUserName ());
		userVo.setType (userDo.getType ());
		userVo.setLocked (userDo.getLocked ());
		userVo.setPhone (userDo.getPhone ());
		userVo.setLastUpdateTime (userDo.getLastUpdateTime ());
		resp.setUserVo (userVo);

		LOGGER.debug (String.format ("SMS.UserServiceImpl.queryUserById.resp:%s", resp));
		return resp;
	}

	@Override
	public QueryUserListResp queryUserList (QueryUserListReq req)
	{
		LOGGER.debug (String.format ("SMS.UserServiceImpl.queryUserList.request:%s", req));
		QueryUserListResp resp = new QueryUserListResp ();
		Map<String, Object> params = new HashMap<> ();
		if (! StringUtils.isEmpty (req.getUserId ()))
		{
			params.put ("userId", req.getUserId ());
		}
		if (! StringUtils.isEmpty (req.getLocked ()))
		{
			params.put ("locked", req.getLocked ());
		}
		if (! StringUtils.isEmpty (req.getUserName ()))
		{
			params.put ("userName", req.getUserName ());
		}
		if (! StringUtils.isEmpty (req.getType ()))
		{
			params.put ("type", req.getUserName ());
		}
		if (! StringUtils.isEmpty (req.getPageIndex ()))
		{
			params.put ("start", (req.getPageIndex () - 1) / req.getPageSize ());
		}
		if (! StringUtils.isEmpty (req.getPageSize ()))
		{
			params.put ("size", req.getPageSize ());
		}
		List<UserDo> userDoList = null;
		int total;
		try
		{
			userDoList = userDao.queryUserList (params);
			total = userDao.count (params);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.UserServiceImpl.queryUserList.Exception", e);
			throw new SMSException (Constant.Common.SQL_EXCEPTION_CODE, Constant.Common.SQL_EXCEPTION_DESC);
		}
		List<UserVo> userVoList = new ArrayList<> (10);
		if (! CollectionUtils.isEmpty (userDoList))
		{
			UserVo userVo = null;
			for (UserDo userDo : userDoList)
			{
				userVo = new UserVo ();
				userVo.setUserId (userDo.getUserId ());
				userVo.setUserName (userDo.getUserName ());
				userVo.setType (userDo.getType ());
				userVo.setLocked (userDo.getLocked ());
				userVo.setPhone (userDo.getPhone ());
				userVo.setLastUpdateTime (userDo.getLastUpdateTime ());
				userVoList.add (userVo);
			}
		}
		resp.setUserVoList (userVoList);
		resp.setTotal (total);
		LOGGER.debug (String.format ("SMS.UserServiceImpl.queryUserList.resp:%s", resp));
		return resp;
	}

	@Override
	public ResultInfo batchDelUser (BatchDelUserReq req)
	{
		LOGGER.debug (String.format ("SMS.UserServiceImpl.batchDelUser.request:%s", req));
		ResultInfo resp = new ResultInfo ();
		try
		{
			userDao.batchDeleteUser (req.getUserIdList ());
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.UserServiceImpl.batchDelUser.Exception", e);
			throw new SMSException (Constant.Common.SQL_EXCEPTION_CODE, Constant.Common.SQL_EXCEPTION_DESC);
		}
		LOGGER.debug (String.format ("SMS.UserServiceImpl.batchDelUser.resp:%s", resp));
		return resp;
	}

	@Override
	public ResultInfo updateUser (UserVo userVo)
	{
		LOGGER.debug (String.format ("SMS.UserServiceImpl.updateUser.request:%s", userVo));
		ResultInfo resp = new ResultInfo ();
		UserDo userDo = new UserDo ();
		userDo.setUserId (userVo.getUserId ());
		userDo.setUserName (userVo.getUserName ());
		userDo.setPassword (userVo.getPassword ());
		userDo.setPhone (userVo.getPhone ());
		userDo.setLocked (userVo.getLocked ());
		userDo.setType (userVo.getType ());
		userDo.setLastUpdateTime (userVo.getLastUpdateTime ());
		/*密码不为空，则为修改密码，需要对密码再次加密*/
		if (! StringUtils.isEmpty (userDo.getPassword ()))
		{
			new PasswordHelper ().encryptPassword (userDo);
		}
		try
		{
			userDao.updateUser (userDo);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.UserServiceImpl.updateUser.Exception", e);
			throw new SMSException (Constant.Common.SQL_EXCEPTION_CODE, Constant.Common.SQL_EXCEPTION_DESC);
		}
		LOGGER.debug (String.format ("SMS.UserServiceImpl.updateUser.resp:%s", resp));
		return resp;
	}

	@Override
	public ResultInfo addUser (UserVo userVo)
	{
		LOGGER.debug (String.format ("SMS.UserServiceImpl.addUser.request:%s", userVo));
		ResultInfo resp = new ResultInfo ();
		UserDo userDo = new UserDo ();
		userDo.setUserId (userVo.getUserId ());
		userDo.setUserName (userVo.getUserName ());
		userDo.setPassword (userVo.getPassword ());
		userDo.setPhone (userVo.getPhone ());
		userDo.setType (userVo.getType ());
		/*默认状态正常*/
		userDo.setLocked ("0");
		/*如果没有设置用户密码，默认初始化密码为123456*/
		if(StringUtils.isEmpty (userDo.getPassword ()))
		{
			userDo.setPassword ("123456");
		}
		//设置盐值，加密
		new PasswordHelper ().encryptPassword (userDo);
		try
		{
			userDao.addUser (userDo);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.UserServiceImpl.addUser.Exception", e);
			throw new SMSException (Constant.Common.SQL_EXCEPTION_CODE, Constant.Common.SQL_EXCEPTION_DESC);
		}
		LOGGER.debug (String.format ("SMS.UserServiceImpl.updateUser.resp:%s", resp));
		return resp;
	}

	@Override
	public CountUserResp countUser (QueryByIdReq req)
	{
		LOGGER.debug (String.format ("SMS.UserServiceImpl.countUser.request:%s", req));
		CountUserResp resp = new CountUserResp ();
		Map<String,Object> params = new HashMap<> (1);
		params.put ("userId",req.getUserId ());
		int count;
		try
		{
			count = userDao.count (params);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.UserServiceImpl.countUser.Exception", e);
			throw new SMSException (Constant.Common.SQL_EXCEPTION_CODE, Constant.Common.SQL_EXCEPTION_DESC);
		}
		resp.setCount (count);
		LOGGER.debug (String.format ("SMS.UserServiceImpl.countUser.resp:%s", resp));
		return resp;
	}
}
