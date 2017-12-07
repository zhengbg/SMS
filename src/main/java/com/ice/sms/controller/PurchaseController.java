/**
 * 文 件 名:  PurchaseController
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhengbinggui
 * 修改时间:  2017/12/7
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.controller;

import com.ice.sms.common.base.ResultInfo;
import com.ice.sms.dto.purchase.request.AddPurchaseRequest;
import com.ice.sms.dto.purchase.request.DelPurchaseRequest;
import com.ice.sms.dto.purchase.request.GetPurchaseRequest;
import com.ice.sms.dto.purchase.request.QueryPurchaseRequest;
import com.ice.sms.dto.purchase.response.GetPurchaseResponse;
import com.ice.sms.dto.purchase.response.QueryPurchaseResponse;
import com.ice.sms.service.PurchaseService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author zhengbinggui
 * @version 2017/12/7
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping ("/purchase")
public class PurchaseController
{
	private static final Logger LOGGER = LogManager.getLogger (PurchaseController.class);

	@Autowired
	private PurchaseService PurchaseService;

	@RequestMapping (value = "/addPurchase", method = RequestMethod.POST)
	ResultInfo addPurchase (@RequestBody AddPurchaseRequest addPurchaseRequest)
	{
		ResultInfo resultInfo = PurchaseService.addPurchase (addPurchaseRequest);
		return resultInfo;
	}

	@RequestMapping (value = "/delPurchase", method = RequestMethod.POST)
	ResultInfo delPurchase (@RequestBody DelPurchaseRequest delPurchaseRequest)
	{
		ResultInfo resultInfo = PurchaseService.delPurchase (delPurchaseRequest);
		return resultInfo;
	}

	@RequestMapping (value = "/queryPurchase", method = RequestMethod.POST)
	QueryPurchaseResponse queryPurchase (@RequestBody QueryPurchaseRequest queryPurchaseRequest)
	{
		QueryPurchaseResponse response = PurchaseService.queryPurchase (queryPurchaseRequest);
		return response;
	}

	@RequestMapping (value = "/getPurchase", method = RequestMethod.POST)
	GetPurchaseResponse getPurchase (@RequestBody GetPurchaseRequest getPurchaseRequest)
	{
		GetPurchaseResponse response = PurchaseService.getPurchase (getPurchaseRequest);
		return response;
	}
}