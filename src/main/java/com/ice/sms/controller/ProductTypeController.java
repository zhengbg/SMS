/**
 * 文 件 名:  ProductTypeController
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhengbinggui
 * 修改时间:  2017/12/6
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.controller;

import com.ice.sms.common.base.ResultInfo;
import com.ice.sms.dto.producttype.request.*;
import com.ice.sms.dto.producttype.response.GetProductTypeResponse;
import com.ice.sms.dto.producttype.response.QueryProductTypeResponse;
import com.ice.sms.service.ProductTypeService;
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
 * @version 2017/12/6
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping ("/member")
public class ProductTypeController
{
	private static final Logger LOGGER = LogManager.getLogger (ProductTypeController.class);

	@Autowired
	private ProductTypeService productTypeService;

	@RequestMapping (value = "/addProductType", method = RequestMethod.POST)
	public ResultInfo addProductType (@RequestBody AddProductTypeRequest addProductTypeRequest)
	{
		ResultInfo resultInfo = productTypeService.addProductType (addProductTypeRequest);
		return resultInfo;
	}

	@RequestMapping (value = "/delProductType", method = RequestMethod.POST)
	public ResultInfo delProductType (@RequestBody DelProductTypeRequest delProductTypeRequest)
	{
		ResultInfo resultInfo = productTypeService.delProductType (delProductTypeRequest);
		return resultInfo;
	}

	@RequestMapping (value = "/updateProductType", method = RequestMethod.POST)
	public ResultInfo updateProductType (@RequestBody UpdateProductTypeRequest updateProductTypeRequest)
	{
		ResultInfo resultInfo = productTypeService.updateProductType (updateProductTypeRequest);
		return resultInfo;
	}

	@RequestMapping (value = "/queryProductType", method = RequestMethod.POST)
	public QueryProductTypeResponse queryProductType (@RequestBody QueryProductTypeRequest queryProductTypeRequest)
	{
		QueryProductTypeResponse response = productTypeService.queryProductType (queryProductTypeRequest);
		return response;
	}

	@RequestMapping (value = "/getProductType", method = RequestMethod.POST)
	public GetProductTypeResponse getProductType (@RequestBody GetProductTypeRequest getProductTypeRequest)
	{
		GetProductTypeResponse response = productTypeService.getProductType (getProductTypeRequest);
		return response;
	}
}