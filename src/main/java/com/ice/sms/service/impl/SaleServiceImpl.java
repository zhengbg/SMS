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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
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
@Service
public class SaleServiceImpl implements SaleService
{
	private static final Logger LOGGER = LogManager.getLogger (SaleServiceImpl.class);
	@Autowired
	private SaleDao saleDao;

	@Override
	public QuerySaleResp querySaleRecord (QuerySaleReq req)
	{
		LOGGER.debug (String.format ("SMS.SaleServiceImpl.querySaleRecord.request:%s", req));
		QuerySaleResp resp = new QuerySaleResp ();
		Map<String,Object> params = new HashMap<> (10);
		if(! StringUtils.isEmpty (req.getUserId ()))
		{
			params.put ("userId",req.getUserId ());
		}
		if(! StringUtils.isEmpty (req.getMemberId ()))
		{
			params.put ("memberId",req.getMemberId ());
		}
		if(! StringUtils.isEmpty (req.getProductId ()))
		{
			params.put ("productId",req.getProductId ());
		}
		if (! StringUtils.isEmpty (req.getPageIndex ()))
		{
			params.put ("start", (req.getPageIndex () - 1) / req.getPageSize ());
		}
		if (! StringUtils.isEmpty (req.getPageSize ()))
		{
			params.put ("size", req.getPageSize ());
		}
		List<SaleDo> saleDoList = null;
		int total;
		try
		{
			saleDoList = saleDao.querySaleRecord (params);
			total = saleDao.count (params);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.SaleServiceImpl.querySaleRecord.Exception", e);
			throw new SMSException (Constant.Common.SQL_EXCEPTION_CODE, Constant.Common.SQL_EXCEPTION_DESC);
		}
		List<SaleVo> saleVos = new ArrayList<> (10);
		if (! CollectionUtils.isEmpty (saleDoList))
		{
			SaleVo saleVo = null;
			for (SaleDo saleDo : saleDoList)
			{
				saleVo = new SaleVo ();
				do2Vo (saleDo,saleVo);
				saleVos.add (saleVo);
			}
		}
		resp.setSaleVos (saleVos);
		resp.setTotal (total);
		LOGGER.debug (String.format ("SMS.SaleServiceImpl.querySaleRecord.response:%s", resp));
		return resp;
	}

	@Override
	public QuerySaleByIdResp querySaleRecordById (QuerySaleByIdReq req)
	{
		LOGGER.debug (String.format ("SMS.SaleServiceImpl.querySaleRecordById.request:%s", req));
		QuerySaleByIdResp resp = new QuerySaleByIdResp ();
		SaleDo saleDo = null;
		try
		{
			saleDo = saleDao.querySaleRecordById (req.getSaleId ());
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.SaleServiceImpl.querySaleRecordById.Exception", e);
			throw new SMSException (Constant.Common.SQL_EXCEPTION_CODE, Constant.Common.SQL_EXCEPTION_DESC);
		}
		SaleVo saleVo = new SaleVo ();
		do2Vo (saleDo,saleVo);
		resp.setSaleVo (saleVo);
		LOGGER.debug (String.format ("SMS.SaleServiceImpl.querySaleRecordById.response:%s", resp));
		return resp;
	}

	@Override
	public ResultInfo addSale (SaleVo saleVo)
	{
		LOGGER.debug (String.format ("SMS.SaleServiceImpl.addSale.request:%s", saleVo));
		ResultInfo resp = new ResultInfo ();
		SaleDo saleDo = new SaleDo ();
		vo2Do (saleDo,saleVo);
		try
		{
			saleDao.addSaleRecord (saleDo);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.SaleServiceImpl.addSale.Exception", e);
			throw new SMSException (Constant.Common.SQL_EXCEPTION_CODE, Constant.Common.SQL_EXCEPTION_DESC);
		}
		LOGGER.debug (String.format ("SMS.SaleServiceImpl.addSale.response:%s", resp));
		return resp;
	}

	@Override
	public ResultInfo updateSale (SaleVo saleVo)
	{
		LOGGER.debug (String.format ("SMS.SaleServiceImpl.updateSale.request:%s", saleVo));
		ResultInfo resp = new ResultInfo ();
		SaleDo saleDo = new SaleDo ();
		vo2Do (saleDo,saleVo);
		try
		{
			saleDao.updateSaleRecord (saleDo);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.SaleServiceImpl.updateSale.Exception", e);
			throw new SMSException (Constant.Common.SQL_EXCEPTION_CODE, Constant.Common.SQL_EXCEPTION_DESC);
		}
		LOGGER.debug (String.format ("SMS.SaleServiceImpl.updateSale.response:%s", resp));
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
		LOGGER.debug (String.format ("SMS.SaleServiceImpl.batchDelSale.response:%s", resp));
		return resp;
	}

	private void do2Vo(SaleDo saleDo,SaleVo saleVo)
	{
		saleVo.setSaleId (saleDo.getSaleId ());
		saleVo.setUserId (saleDo.getUserId ());
		saleVo.setProductId (saleDo.getProductId ());
		saleVo.setProductName (saleDo.getProductName ());
		saleVo.setMemberId (saleDo.getMemberId ());
		saleVo.setPrice (saleDo.getPrice ());
		saleVo.setAmount (saleDo.getAmount ());
		saleVo.setCreateTime (saleVo.getCreateTime ());
		saleVo.setLastUpdateTime (saleVo.getLastUpdateTime ());
		saleVo.setMemo (saleVo.getMemo ());
	}
	private void vo2Do (SaleDo saleDo,SaleVo saleVo)
	{
		saleDo.setSaleId (saleVo.getSaleId ());
		saleDo.setUserId (saleVo.getUserId ());
		saleDo.setProductId (saleVo.getProductId ());
		saleDo.setMemberId (saleVo.getMemberId ());
		saleDo.setPrice (saleVo.getPrice ());
		saleDo.setAmount (saleVo.getAmount ());
		saleDo.setMemo (saleVo.getMemo ());
	}
}
