/**
 * 文 件 名:  SaleService
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  huangsongbo
 * 修改时间:  2017/12/6
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.service;

import com.ice.sms.common.base.ResultInfo;
import com.ice.sms.dto.sale.request.BatchDelSaleReq;
import com.ice.sms.dto.sale.request.QuerySaleByIdReq;
import com.ice.sms.dto.sale.request.QuerySaleReq;
import com.ice.sms.dto.sale.response.QuerySaleByIdResp;
import com.ice.sms.dto.sale.response.QuerySaleResp;
import com.ice.sms.entity.SaleDo;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author huangsongbo
 * @version 2017/12/6
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface SaleService
{
	QuerySaleResp querySaleRecord (QuerySaleReq req);

	QuerySaleByIdResp querySaleRecordById (QuerySaleByIdReq req);

	ResultInfo addSale (SaleDo saleDo);

	ResultInfo updateSale (SaleDo saleDo);

	ResultInfo batchDelSale (BatchDelSaleReq req);
}