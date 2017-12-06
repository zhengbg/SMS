/**
 * 文 件 名:  HandlerException
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  huangsongbo
 * 修改时间:  2017/11/17
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.common.exception;

import com.ice.sms.common.base.ResultInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author huangsongbo
 * @version 2017/11/17
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@ControllerAdvice
@ResponseBody
public class HandlerException
{
	@ExceptionHandler
	public ResultInfo toResponse (Exception e)
	{
		ResultInfo resultInfo = new ResultInfo ();

		if (e instanceof SMSException)
		{
			resultInfo.setResultCode (((SMSException) e).getResultCode ());

			resultInfo.setResultDesc (((SMSException) e).getResultDesc ());
		}
		else
		{
			resultInfo.setResultCode ("999999");
			resultInfo.setResultDesc (e.getClass ().getName ());
		}
		return resultInfo;
	}
}
