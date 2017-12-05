/**
 * 文 件 名:  RetryLimitHashedCredentialsMatcher
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  huangsongbo
 * 修改时间:  2017/9/18
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.security;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author huangsongbo
 * @version 2017/9/18
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher
{

	private Cache<String, AtomicInteger> passwordRetryCache;

	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch (AuthenticationToken token, AuthenticationInfo info)
	{
		String username = (String) token.getPrincipal();
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		// 自定义一个验证过程：当用户连续输入密码错误5次以上禁止用户登录一段时间
		if (retryCount.incrementAndGet() > 5) {
			throw new ExcessiveAttemptsException ();
		}
		boolean match = super.doCredentialsMatch(token, info);
		if (match) {
			passwordRetryCache.remove(username);
		}
		return match;
	}
}
