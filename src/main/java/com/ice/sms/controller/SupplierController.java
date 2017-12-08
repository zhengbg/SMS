/**
 * 文 件 名:  SupplierController
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhengbinggui
 * 修改时间:  2017/12/8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.controller;

import com.ice.sms.common.base.ResultInfo;
import com.ice.sms.dto.supplier.request.*;
import com.ice.sms.dto.supplier.response.GetSupplierResponse;
import com.ice.sms.dto.supplier.response.QuerySupplierResponse;
import com.ice.sms.service.SupplierService;
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
 * @version 2017/12/8
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping ("/supplier")
public class SupplierController
{
	private static final Logger LOGGER = LogManager.getLogger (SupplierController.class);

	@Autowired
	private SupplierService supplierService;

	@RequestMapping (value = "/addSupplier", method = RequestMethod.POST)
	ResultInfo addSupplier (@RequestBody AddSupplierRequest addSupplierRequest)
	{
		ResultInfo resultInfo = supplierService.addSupplier (addSupplierRequest);
		return resultInfo;
	}

	@RequestMapping (value = "/delSupplier", method = RequestMethod.POST)
	ResultInfo delSupplier (@RequestBody DelSupplierRequest delSupplierRequest)
	{
		ResultInfo resultInfo = supplierService.delSupplier (delSupplierRequest);
		return resultInfo;
	}

	@RequestMapping (value = "/updateSupplier", method = RequestMethod.POST)
	ResultInfo updateSupplier (@RequestBody UpdateSupplierRequest updateSupplierRequest)
	{
		ResultInfo resultInfo = supplierService.updateSupplier (updateSupplierRequest);
		return resultInfo;
	}

	@RequestMapping (value = "/querySupplier", method = RequestMethod.POST)
	QuerySupplierResponse querySupplier (@RequestBody QuerySupplierRequest querySupplierRequest)
	{
		QuerySupplierResponse response = supplierService.querySupplier (querySupplierRequest);
		return response;
	}

	@RequestMapping (value = "/getSupplier", method = RequestMethod.POST)
	GetSupplierResponse getSupplier (@RequestBody GetSupplierRequest getSupplierRequest)
	{
		GetSupplierResponse response = supplierService.getSupplier (getSupplierRequest);
		return response;
	}
}