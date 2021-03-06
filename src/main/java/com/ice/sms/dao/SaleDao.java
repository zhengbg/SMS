/**
 * 文 件 名:  SaleDao
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  huangsongbo
 * 修改时间:  2017/12/6
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.dao;

import com.ice.sms.entity.SaleDo;

import java.util.List;
import java.util.Map;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author huangsongbo
 * @version 2017/12/6
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface SaleDao
{
	/**
	 * 查询销售列表
	 * @param params
	 * @return
	 */
	List<SaleDo> querySaleRecord (Map<String, Object> params);

	/**
	 * 分页总数
	 * @param params
	 * @return
	 */
	int count (Map<String, Object> params);

	/**
	 * 通过ID 查询销售记录
	 * @param saleId
	 * @return
	 */
	SaleDo querySaleRecordById (String saleId);

	/**
	 * 出售商品
	 * @param saleDo
	 */
	void addSaleRecord (SaleDo saleDo);

	/**
	 * 修改销售记录
	 * @param saleDo
	 */
	void updateSaleRecord (SaleDo saleDo);

	/**
	 * 批量删除销售记录
	 * @param saleIdList
	 */
	void batchDelSaleRecord (List<String> saleIdList);
}