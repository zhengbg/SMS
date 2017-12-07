/**
 * 文 件 名:  ProductController
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
import com.ice.sms.dto.product.request.*;
import com.ice.sms.dto.product.response.GetProductResponse;
import com.ice.sms.dto.product.response.QueryProductResponse;
import com.ice.sms.service.ProductService;
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
@RequestMapping ("/product")
public class ProductController
{
	private static final Logger LOGGER = LogManager.getLogger (ProductController.class);

	@Autowired
	private ProductService productService;

	@RequestMapping (value = "/addProduct", method = RequestMethod.POST)
	public ResultInfo addProduct (@RequestBody AddProductRequest addProductRequest)
	{
		ResultInfo resultInfo = productService.addProduct (addProductRequest);
		return resultInfo;
	}

	@RequestMapping (value = "/delProduct", method = RequestMethod.POST)
	public ResultInfo delProduct (@RequestBody DelProductRequest delProductRequest)
	{
		ResultInfo resultInfo = productService.delProduct (delProductRequest);
		return resultInfo;
	}

	@RequestMapping (value = "/updateProduct", method = RequestMethod.POST)
	public ResultInfo updateProduct (@RequestBody UpdateProductRequest updateProductRequest)
	{
		ResultInfo resultInfo = productService.updateProduct (updateProductRequest);
		return resultInfo;
	}

	@RequestMapping (value = "/queryProduct", method = RequestMethod.POST)
	public QueryProductResponse queryProduct (@RequestBody QueryProductRequest queryProductRequest)
	{
		QueryProductResponse response = productService.queryProduct (queryProductRequest);
		return response;
	}

	@RequestMapping (value = "/getProduct", method = RequestMethod.POST)
	public GetProductResponse getProduct (@RequestBody GetProductRequest getProductRequest)
	{
		GetProductResponse response = productService.getProduct (getProductRequest);
		return response;
	}
}