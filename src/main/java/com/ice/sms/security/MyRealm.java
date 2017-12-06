package com.ice.sms.security;

import com.ice.sms.common.constant.Constant;
import com.ice.sms.common.exception.SMSException;
import com.ice.sms.dao.UserDao;
import com.ice.sms.entity.UserDo;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author huangsongbo
 * @version 2017/9/15
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MyRealm extends AuthorizingRealm
{
	@Autowired
	private UserDao userDao;

	/* 权限认证 */
	protected AuthorizationInfo doGetAuthorizationInfo (PrincipalCollection principals)
	{
		return null;
	}

	/* 登录认证 */
	protected AuthenticationInfo doGetAuthenticationInfo (AuthenticationToken token) throws AuthenticationException
	{

		String userId = (String) token.getPrincipal ();
		UserDo user = null;
		try
		{
			user = userDao.queryUserById (userId);
		}
		catch (Exception e)
		{
			throw new SMSException (Constant.Common.UNKNOWN_ERROR_CODE, Constant.Common.UNKNOWN_ERROR_DESC);
		}
		if (user == null)
		{
			// 用户名不存在抛出异常
			throw new UnknownAccountException ();
		}
		if (! "0".equals (user.getLocked ()))
		{
			// 用户被管理员锁定抛出异常
			throw new LockedAccountException ();
		}
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo (user.getUserId (),
				user.getPassword (), ByteSource.Util.bytes (user.getCredentialsSalt ()), getName ());
		return authenticationInfo;
	}
}
