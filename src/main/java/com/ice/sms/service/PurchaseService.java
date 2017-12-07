/**
 * 文 件 名:  PurchaseService
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhengbinggui
 * 修改时间:  2017/12/7
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.service;

import com.ice.sms.common.base.ResultInfo;
import com.ice.sms.dto.purchase.request.AddPurchaseRequest;
import com.ice.sms.dto.purchase.request.DelPurchaseRequest;
import com.ice.sms.dto.purchase.request.GetPurchaseRequest;
import com.ice.sms.dto.purchase.request.QueryPurchaseRequest;
import com.ice.sms.dto.purchase.response.GetPurchaseResponse;
import com.ice.sms.dto.purchase.response.QueryPurchaseResponse;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author zhengbinggui
 * @version 2017/12/7
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface PurchaseService
{
	ResultInfo addPurchase (AddPurchaseRequest addPurchaseRequest);

	ResultInfo delPurchase (DelPurchaseRequest delPurchaseRequest);

	QueryPurchaseResponse queryPurchase (QueryPurchaseRequest queryPurchaseRequest);

	GetPurchaseResponse getPurchase (GetPurchaseRequest getPurchaseRequest);
}