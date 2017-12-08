/**
 * 文 件 名:  QuerySupplierRequest
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhengbinggui
 * 修改时间:  2017/12/8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.dto.supplier.request;

import com.ice.sms.common.base.RequestInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author zhengbinggui
 * @version 2017/12/8
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Getter
@Setter
@ToString
public class QuerySupplierRequest extends RequestInfo
{
	private Integer supplierId;
	private String supplier;
	private String createTime;
	private String lastUpdateTime;
	private String minCreateTime;
	private String maxCreateTime;
	private String minLastUpdateTime;
	private String maxLastUpdateTime;
}