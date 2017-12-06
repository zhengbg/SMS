/**
 * 文 件 名:  Constant
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  huangsongbo
 * 修改时间:  2017/12/5
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.common.constant;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author huangsongbo
 * @version 2017/12/5
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface Constant
{
	interface Common
	{
		//统一成功返回
		String SUCCESS_CODE = "000000";
		String SUCCESS_DESC = "SUCCESS";

		//执行sql异常
		String SQL_EXCEPTION_CODE = "200001";
		String SQL_EXCEPTION_DESC = "Execute Sql Exception";

		String UNKNOWN_ERROR_CODE = "999999";
		String UNKNOWN_ERROR_DESC = "System inner error!";

		//参数缺失
		String MISSING_PARAMETERS_CODE = "100001";
	}
}