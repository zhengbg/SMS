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

import com.ice.sms.common.constant.Constant;
import com.ice.sms.dao.UserDao;
import com.ice.sms.dto.user.request.QueryByIdReq;
import com.ice.sms.dto.user.response.QueryByIdResp;
import com.ice.sms.dto.user.vo.UserVo;
import com.ice.sms.entity.UserDo;
import com.ice.sms.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			resp.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			resp.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return resp;
		}

		UserVo userVo = new UserVo ();
		userVo.setUserId (userDo.getUserId ());
		userVo.setUserName (userDo.getUserName ());
		userVo.setType (userDo.getType ());
		userVo.setPhone (userDo.getPhone ());
		userVo.setLastUpdateTime (userDo.getLastUpdateTime ());
		resp.setUserVo (userVo);

		LOGGER.debug (String.format ("SMS.UserServiceImpl.queryUserById.resp:%s", resp));
		return resp;
	}
}
