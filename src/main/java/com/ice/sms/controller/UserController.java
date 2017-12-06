/**
 * 文 件 名:  UserController
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  huangsongbo
 * 修改时间:  2017/12/6
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.controller;

import com.ice.sms.common.base.ResultInfo;
import com.ice.sms.common.constant.Constant;
import com.ice.sms.dto.user.request.*;
import com.ice.sms.dto.user.response.CountUserResp;
import com.ice.sms.dto.user.response.QueryByIdResp;
import com.ice.sms.dto.user.response.QueryUserListResp;
import com.ice.sms.dto.user.vo.UserVo;
import com.ice.sms.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author huangsongbo
 * @version 2017/12/6
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping ("/user")
public class UserController
{
	private static final Logger LOGGER = LogManager.getLogger (UserController.class);
	@Autowired
	private UserService userService;

	@RequestMapping (value = "/query", method = RequestMethod.POST)
	public QueryUserListResp queryUserList (@RequestBody QueryUserListReq req)
	{
		return userService.queryUserList (req);
	}

	@RequestMapping (value = "/find", method = RequestMethod.POST)
	public QueryByIdResp queryUserById (@RequestBody QueryByIdReq req)
	{
		QueryByIdResp resp = new QueryByIdResp ();
		if (StringUtils.isEmpty (req.getUserId ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "userId");
			return resp;
		}
		resp = userService.queryUserById (req);
		return resp;
	}

	@RequestMapping (value = "/del", method = RequestMethod.POST)
	public ResultInfo batchDelUser (@RequestBody BatchDelUserReq req)
	{
		ResultInfo resp = new ResultInfo ();
		if (CollectionUtils.isEmpty (req.getUserIdList ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "userIdList");
			return resp;
		}
		resp = userService.batchDelUser (req);
		return resp;
	}

	@RequestMapping (value = "/modify", method = RequestMethod.POST)
	public ResultInfo updateUser (@RequestBody UserVo userVo)
	{
		ResultInfo resp = new ResultInfo ();
		if (StringUtils.isEmpty (userVo.getUserId ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "userId");
			return resp;
		}
		if (StringUtils.isEmpty (userVo.getUserName ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "userName");
			return resp;
		}
		if (StringUtils.isEmpty (userVo.getLocked ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "locked");
			return resp;
		}
		if (StringUtils.isEmpty (userVo.getLastUpdateTime ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "lastUpdateTime");
			return resp;
		}
		resp = userService.updateUser (userVo);
		return resp;
	}

	@RequestMapping (value = "/add", method = RequestMethod.POST)
	public ResultInfo addUser (@RequestBody UserVo userVo)
	{
		ResultInfo resp = new ResultInfo ();
		if (StringUtils.isEmpty (userVo.getUserId ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "userId");
			return resp;
		}
		if (StringUtils.isEmpty (userVo.getType ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "type");
			return resp;
		}
		if (StringUtils.isEmpty (userVo.getUserName ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "userName");
			return resp;
		}
		resp = userService.addUser (userVo);
		return resp;
	}

	@RequestMapping (value = "/count", method = RequestMethod.POST)
	public CountUserResp countUser (@RequestBody QueryByIdReq req)
	{
		CountUserResp resp = new CountUserResp ();
		if (StringUtils.isEmpty (req.getUserId ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "userId");
			return resp;
		}
		resp = userService.countUser (req);
		return resp;
	}

	@RequestMapping (value = "/login", method = RequestMethod.POST)
	public ResultInfo login (@RequestBody UserLoginReq loginReq)
	{
		ResultInfo resp = new ResultInfo ();

		/*用户是否已经登录*/
		boolean flag = false;

		if (StringUtils.isEmpty (loginReq.getAccount ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "userId");
			return resp;
		}
		if (StringUtils.isEmpty (loginReq.getPassword ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "password");
			return resp;
		}
		Subject subject = SecurityUtils.getSubject ();

		if (subject.isAuthenticated () && loginReq.getAccount ().equals (subject.getPrincipal ()))
		{
			/*用户已经登录*/
			resp.setResultCode (Constant.User.USER_IN_LOGIN);
			resp.setResultDesc (Constant.User.USER_IN_LOGIN_DESC);
			return resp;
		}
		/*用户未登录*/
		else
		{
			/*用户名、密码存入token*/
			UsernamePasswordToken token = new UsernamePasswordToken (loginReq.getAccount (), loginReq.getPassword ());
			try
			{
				/*shiro 登录校验*/
				subject.login (token);
			}
			catch (UnknownAccountException e)
			{
				LOGGER.error (String.format ("UserController.Login.UnknownAccountException:%s", e));
				resp.setResultCode (Constant.User.USER_NOT_EXIST);
				resp.setResultDesc (Constant.User.USER_NOT_EXIST_DESC);
				return resp;
			}
			/* 密码错误 */
			catch (IncorrectCredentialsException e)
			{
				LOGGER.error (String.format ("UserController.Login.IncorrectCredentialsException:%s", e));
				resp.setResultCode (Constant.User.PASSWORD_ERROR);
				resp.setResultDesc (Constant.User.PASSWORD_ERROR_DESC);
				return resp;
			}
			/* 用户因多次输入错误次数超过五次被锁定 */
			catch (LockedAccountException e)
			{
				LOGGER.error (String.format ("UserController.Login.LockedAccountException:%s", e));
				resp.setResultCode (Constant.User.USER_IN_LOCK);
				resp.setResultDesc (Constant.User.USER_IN_LOCK_DESC);
				return resp;
			}
			/* 登录失败次数过多 */
			catch (ExcessiveAttemptsException e)
			{
				/* 这里可以设定将用户帐号锁定，即修改数据库用户信息locked字段为0 */
				LOGGER.error (String.format ("UserController.Login.ExcessiveAttemptsException:%s", e));
				resp.setResultCode (Constant.User.USER_IN_LOCK);
				resp.setResultDesc (Constant.User.USER_IN_LOCK_DESC);
				return resp;
			}
			/* 其他异常 */
			catch (Exception e)
			{
				LOGGER.error (String.format ("UserController.Login.Exception:%s", e));
				resp.setResultCode (Constant.Common.UNKNOWN_ERROR_CODE);
				resp.setResultDesc (Constant.Common.UNKNOWN_ERROR_DESC);
				return resp;
			}
		}

		return resp;
	}
}
