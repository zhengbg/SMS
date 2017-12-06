/**
 * 文 件 名:  ProductTypeService
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhengbinggui
 * 修改时间:  2017/12/6
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.service;

import com.ice.sms.common.base.ResultInfo;
import com.ice.sms.dto.producttype.request.*;
import com.ice.sms.dto.producttype.response.GetProductTypeResponse;
import com.ice.sms.dto.producttype.response.QueryProductTypeResponse;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author zhengbinggui
 * @version 2017/12/6
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface ProductTypeService
{
	ResultInfo addProductType (AddProductTypeRequest addProductTypeRequest);

	ResultInfo delProductType (DelProductTypeRequest delProductTypeRequest);

	ResultInfo updateProductType (UpdateProductTypeRequest updateProductTypeRequest);

	QueryProductTypeResponse queryProductType (QueryProductTypeRequest queryProductTypeRequest);

	GetProductTypeResponse getProductType (GetProductTypeRequest getProductTypeRequest);
}