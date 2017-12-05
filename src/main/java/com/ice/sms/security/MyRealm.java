package com.ice.sms.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author huangsongbo
 * @version 2017/9/15
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MyRealm extends AuthorizingRealm {
/*	@Autowired
	private UserService userService;*/

	/* 权限认证 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

	/* 登录认证 */
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		/*String username = (String) token.getPrincipal();

		QryUserReq req = new QryUserReq();

		req.setUsername(username);

		QryUserResp resp = userService.queryUser(req);

		User user = null;

		if (null != resp && !resp.getUserList().isEmpty()) {
			user = resp.getUserList().get(0);
		}

		if (user == null) {
			// 用户名不存在抛出异常
			throw new UnknownAccountException();
		}

		if (user.getLocked() == 0) {
			// 用户被管理员锁定抛出异常
			throw new LockedAccountException();
		}

		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),
				user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), getName());

		return authenticationInfo;*/
		return null;
	}
}
