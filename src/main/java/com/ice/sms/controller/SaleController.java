/**
 * 文 件 名:  SaleController
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  huangsongbo
 * 修改时间:  2017/12/7
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.controller;

import com.ice.sms.common.base.ResultInfo;
import com.ice.sms.common.constant.Constant;
import com.ice.sms.dto.sale.request.BatchDelSaleReq;
import com.ice.sms.dto.sale.request.QuerySaleByIdReq;
import com.ice.sms.dto.sale.request.QuerySaleReq;
import com.ice.sms.dto.sale.response.QuerySaleByIdResp;
import com.ice.sms.dto.sale.response.QuerySaleResp;
import com.ice.sms.dto.sale.vo.SaleVo;
import com.ice.sms.service.SaleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author huangsongbo
 * @version 2017/12/7
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping ("/sale")
public class SaleController
{
	private static final Logger LOGGER = LogManager.getLogger (SaleController.class);

	@Autowired
	private SaleService saleService;

	@RequestMapping (value = "/query", method = RequestMethod.POST)
	public QuerySaleResp querySaleRecordList (@RequestBody QuerySaleReq req)
	{
		return saleService.querySaleRecord (req);
	}

	@RequestMapping (value = "/find", method = RequestMethod.POST)
	public QuerySaleByIdResp querySaleById (@RequestBody QuerySaleByIdReq req)
	{
		QuerySaleByIdResp resp = new QuerySaleByIdResp ();
		if (StringUtils.isEmpty (req.getSaleId ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "saleId");
			return resp;
		}
		return saleService.querySaleRecordById (req);
	}

	@RequestMapping (value = "/add", method = RequestMethod.POST)
	public ResultInfo addSale (@RequestBody SaleVo saleVo)
	{
		ResultInfo resp = new ResultInfo ();
		if (StringUtils.isEmpty (saleVo.getProductId ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "productId");
			return resp;
		}
		if (StringUtils.isEmpty (saleVo.getUserId ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "userId");
			return resp;
		}
		if (StringUtils.isEmpty (saleVo.getPrice ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "price");
			return resp;
		}
		if (StringUtils.isEmpty (saleVo.getAmount ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "amount");
			return resp;
		}
		saleService.addSale (saleVo);
		return resp;
	}

	@RequestMapping (value = "/modify", method = RequestMethod.POST)
	public ResultInfo updateSale (@RequestBody SaleVo saleVo)
	{
		ResultInfo resp = new ResultInfo ();
		if (StringUtils.isEmpty (saleVo.getSaleId ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "saleId");
			return resp;
		}
		if (StringUtils.isEmpty (saleVo.getProductId ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "productId");
			return resp;
		}
		if (StringUtils.isEmpty (saleVo.getUserId ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "userId");
			return resp;
		}
		if (StringUtils.isEmpty (saleVo.getLastUpdateTime ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "lastUpdateTime");
			return resp;
		}
		if (StringUtils.isEmpty (saleVo.getPrice ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "price");
			return resp;
		}
		if (StringUtils.isEmpty (saleVo.getAmount ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "amount");
			return resp;
		}
		resp = saleService.updateSale (saleVo);
		return resp;
	}

	@RequestMapping(value = "/batchDel",method = RequestMethod.POST)
	public ResultInfo batchDel (@RequestBody BatchDelSaleReq req)
	{
		ResultInfo resp = new ResultInfo ();
		if(CollectionUtils.isEmpty (req.getSaleIdList ()))
		{
			resp.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resp.setResultDesc (Constant.Common.MISSING_PARAMETERS_DESC + "saleIdList");
			return resp;
		}
		resp = saleService.batchDelSale (req);
		return resp;
	}
}
