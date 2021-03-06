/**
 * 文 件 名:  UserDo
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  huangsongbo
 * 修改时间:  2017/12/5
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author huangsongbo
 * @version 2017/12/5
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Getter
@Setter
@ToString
public class UserDo implements Serializable
{
	private String userId;
	private String userName;
	private String password;
	private String type;
	private String phone;
	private String salt;
	private String locked;
	private String createTime;
	private String lastUpdateTime;
	//加盐
	public String getCredentialsSalt ()
	{
		return this.userId + this.salt;
	}
}
