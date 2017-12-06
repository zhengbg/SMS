/**
 * 文 件 名:  PasswordHelper
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  huangsongbo
 * 修改时间:  2017/9/18
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.security;

import com.ice.sms.entity.UserDo;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author huangsongbo
 * @version 2017/9/18
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PasswordHelper
{
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator ();
	private String algorithmName = "md5";
	private final int hashIterations = 2;

	public void encryptPassword (UserDo user)
	{
		// User对象包含最基本的字段account和Password
		user.setSalt (randomNumberGenerator.nextBytes ().toHex ());
		// 将用户的注册密码经过散列算法替换成一个不可逆的新密码保存进数据，散列过程使用了盐
		String newPassword = new SimpleHash (algorithmName, user.getPassword (),
				ByteSource.Util.bytes (user.getCredentialsSalt ()), hashIterations).toHex ();
		user.setPassword (newPassword);
	}
}
