/**
 * 文 件 名:  SaleServiceImpl
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  huangsongbo
 * 修改时间:  2017/12/6
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.service.impl;

import com.ice.sms.common.base.ResultInfo;
import com.ice.sms.common.constant.Constant;
import com.ice.sms.common.exception.SMSException;
import com.ice.sms.dao.SaleDao;
import com.ice.sms.dto.sale.request.BatchDelSaleReq;
import com.ice.sms.dto.sale.request.QuerySaleByIdReq;
import com.ice.sms.dto.sale.request.QuerySaleReq;
import com.ice.sms.dto.sale.response.QuerySaleByIdResp;
import com.ice.sms.dto.sale.response.QuerySaleResp;
import com.ice.sms.dto.sale.vo.SaleVo;
import com.ice.sms.entity.SaleDo;
import com.ice.sms.service.SaleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author huangsongbo
 * @version 2017/12/6
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
public class SaleServiceImpl implements SaleService
{
	private static final Logger LOGGER = LogManager.getLogger (SaleServiceImpl.class);
	@Autowired
	private SaleDao saleDao;

	@Override
	public QuerySaleResp querySaleRecord (QuerySaleReq req)
	{
		return null;
	}

	@Override
	public QuerySaleByIdResp querySaleRecordById (QuerySaleByIdReq req)
	{
		return null;
	}

	@Override
	public ResultInfo addSale (SaleVo saleVo)
	{
		LOGGER.debug (String.format ("SMS.SaleServiceImpl.addSale.request:%s", saleVo));
		ResultInfo resp = new ResultInfo ();
		SaleDo saleDo = new SaleDo ();
		saleDo.setUserId (saleVo.getUserId ());
		saleDo.setProductId (saleVo.getProductId ());
		saleDo.setMemberId (saleVo.getMemberId ());
		saleDo.setPrice (saleVo.getPrice ());
		saleDo.setAmount (saleVo.getAmount ());
		saleDo.setMemo (saleVo.getMemo ());

		return resp;
	}

	@Override
	public ResultInfo updateSale (SaleVo saleVo)
	{
		LOGGER.debug (String.format ("SMS.SaleServiceImpl.updateSale.request:%s", saleVo));
		ResultInfo resp = new ResultInfo ();

		return resp;
	}

	@Override
	public ResultInfo batchDelSale (BatchDelSaleReq req)
	{
		LOGGER.debug (String.format ("SMS.SaleServiceImpl.batchDelSale.request:%s", req));
		ResultInfo resp = new ResultInfo ();
		try
		{
			saleDao.batchDelSaleRecord (req.getSaleIdList ());
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.SaleServiceImpl.batchDelUser.Exception", e);
			throw new SMSException (Constant.Common.SQL_EXCEPTION_CODE, Constant.Common.SQL_EXCEPTION_DESC);
		}
		LOGGER.debug (String.format ("SMS.SaleServiceImpl.batchDelSale.response:%s", req));
		return resp;
	}
}
