/**
 * 文 件 名:  ProductService
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
import com.ice.sms.dto.product.request.*;
import com.ice.sms.dto.product.response.GetProductResponse;
import com.ice.sms.dto.product.response.QueryProductResponse;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author zhengbinggui
 * @version 2017/12/7
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ProductService
{
	ResultInfo addProduct (AddProductRequest addProductRequest);

	ResultInfo delProduct (DelProductRequest delProductRequest);

	ResultInfo updateProduct (UpdateProductRequest updateProductRequest);

	QueryProductResponse queryProduct (QueryProductRequest queryProductRequest);

	GetProductResponse getProduct (GetProductRequest getProductRequest);
}