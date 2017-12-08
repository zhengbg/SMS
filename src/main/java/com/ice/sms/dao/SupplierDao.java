/**
 * 文 件 名:  SupplierDao
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhengbinggui
 * 修改时间:  2017/12/8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.dao;

import com.ice.sms.entity.SupplierDo;

import java.util.List;
import java.util.Map;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author zhengbinggui
 * @version 2017/12/8
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface SupplierDao
{
	void addSupplier (SupplierDo supplierDo);

	void delSupplier (List<Integer> supplierIds);

	void updateSupplier (Map<String, Object> params);

	List<SupplierDo> querySupplier (Map<String, Object> params);

	int countSupplier (Map<String, Object> params);

	SupplierDo getSupplier (Integer supplierId);
}