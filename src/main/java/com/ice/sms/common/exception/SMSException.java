/**
 * 文 件 名:  SMSException
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  huangsongbo
 * 修改时间:  2017/11/17
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.common.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author huangsongbo
 * @version 2017/11/17
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Getter
@Setter
@ToString
public class SMSException extends RuntimeException
{
	private String resultCode;

	private String resultDesc;

	public SMSException (String code,String msg)
	{
		this.resultCode = code;
		this.resultDesc = msg;
	}
}
