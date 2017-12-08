/**
 * 文 件 名:  SupplierService
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhengbinggui
 * 修改时间:  2017/12/8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.service;

import com.ice.sms.common.base.ResultInfo;
import com.ice.sms.dto.supplier.request.*;
import com.ice.sms.dto.supplier.response.GetSupplierResponse;
import com.ice.sms.dto.supplier.response.QuerySupplierResponse;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author zhengbinggui
 * @version 2017/12/8
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface SupplierService
{
	ResultInfo addSupplier (AddSupplierRequest addSupplierRequest);

	ResultInfo delSupplier (DelSupplierRequest delSupplierRequest);

	ResultInfo updateSupplier (UpdateSupplierRequest updateSupplierRequest);

	QuerySupplierResponse querySupplier (QuerySupplierRequest querySupplierRequest);

	GetSupplierResponse getSupplier (GetSupplierRequest getSupplierRequest);
}