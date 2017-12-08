/**
 * 文 件 名:  PurchaseServiceImpl
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhengbinggui
 * 修改时间:  2017/12/7
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.service.impl;

import com.ice.sms.common.base.ResultInfo;
import com.ice.sms.common.constant.Constant;
import com.ice.sms.dao.PurchaseDao;
import com.ice.sms.dto.purchase.request.AddPurchaseRequest;
import com.ice.sms.dto.purchase.request.DelPurchaseRequest;
import com.ice.sms.dto.purchase.request.GetPurchaseRequest;
import com.ice.sms.dto.purchase.request.QueryPurchaseRequest;
import com.ice.sms.dto.purchase.response.GetPurchaseResponse;
import com.ice.sms.dto.purchase.response.QueryPurchaseResponse;
import com.ice.sms.dto.purchase.vo.PurchaseVo;
import com.ice.sms.entity.PurchaseDo;
import com.ice.sms.service.PurchaseService;
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
 * @author zhengbinggui
 * @version 2017/12/7
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service (value = "purchaseService")
public class PurchaseServiceImpl implements PurchaseService
{
	private static final Logger LOGGER = LogManager.getLogger (PurchaseServiceImpl.class);

	@Autowired
	private PurchaseDao purchaseDao;

	@Override
	public ResultInfo addPurchase (AddPurchaseRequest addPurchaseRequest)
	{
		LOGGER.debug (String.format ("SMS.PurchaseServiceImpl.addPurchase.request:%s", addPurchaseRequest));

		ResultInfo resultInfo = new ResultInfo ();

		if (null == addPurchaseRequest)
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("addPurchaseRequest can not be null");
			return resultInfo;
		}

		PurchaseVo purchaseVo = addPurchaseRequest.getPurchaseVo ();

		if (null == purchaseVo)
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("purchaseVo can not be null");
			return resultInfo;
		}
		if (StringUtils.isEmpty (purchaseVo.getProductId ()))
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("productId can not be null");
			return resultInfo;
		}
		if (StringUtils.isEmpty (purchaseVo.getEmployeeId ()))
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("employeeId can not be null");
			return resultInfo;
		}
		if (null == purchaseVo.getPrice ())
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("price can not be null");
			return resultInfo;
		}
		if (null == purchaseVo.getAmount ())
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("amount can not be null");
			return resultInfo;
		}

		PurchaseDo purchaseDo = change2Do (purchaseVo);

		try
		{
			purchaseDao.addPurchase (purchaseDo);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.PurchaseServiceImpl.addPurchase.exception", e);
			resultInfo.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			resultInfo.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return resultInfo;
		}

		LOGGER.debug (String.format ("SMS.PurchaseServiceImpl.addPurchase.response:%s", resultInfo));
		return resultInfo;
	}

	@Override
	public ResultInfo delPurchase (DelPurchaseRequest delPurchaseRequest)
	{
		LOGGER.debug (String.format ("SMS.PurchaseServiceImpl.delPurchase.request:%s", delPurchaseRequest));

		ResultInfo resultInfo = new ResultInfo ();

		if (null == delPurchaseRequest)
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("delPurchaseRequest can not be null");
			return resultInfo;
		}

		List<Integer> purchaseIds = delPurchaseRequest.getPurchaseIds ();

		if (! CollectionUtils.isEmpty (purchaseIds))
		{
			try
			{
				purchaseDao.delPurchase (purchaseIds);
			}
			catch (Exception e)
			{
				LOGGER.error ("SMS.PurchaseServiceImpl.delPurchase.exception", e);
				resultInfo.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
				resultInfo.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
				return resultInfo;
			}
		}

		LOGGER.debug (String.format ("SMS.PurchaseServiceImpl.delPurchase.response:%s", resultInfo));
		return resultInfo;
	}

	@Override
	public QueryPurchaseResponse queryPurchase (QueryPurchaseRequest queryPurchaseRequest)
	{
		LOGGER.debug (String.format ("SMS.PurchaseServiceImpl.queryPurchase.request:%s", queryPurchaseRequest));

		QueryPurchaseResponse response = new QueryPurchaseResponse ();

		if (null == queryPurchaseRequest)
		{
			response.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			response.setResultDesc ("queryPurchaseRequest can not be null");
			return response;
		}

		Map<String, Object> params = new HashMap<> ();
		if (! StringUtils.isEmpty (queryPurchaseRequest.getProductName ()))
		{
			params.put ("productName", queryPurchaseRequest.getProductName ());
		}
		if (! StringUtils.isEmpty (queryPurchaseRequest.getSupplier ()))
		{
			params.put ("supplier", queryPurchaseRequest.getSupplier ());
		}
		if (null != queryPurchaseRequest.getSupplierId ())
		{
			params.put ("supplier", queryPurchaseRequest.getSupplierId ());
		}
		if (null != queryPurchaseRequest.getProductTypeId ())
		{
			params.put ("productTypeId", queryPurchaseRequest.getProductTypeId ());
		}
		if (! StringUtils.isEmpty (queryPurchaseRequest.getProductTypeName ()))
		{
			params.put ("productTypeName", queryPurchaseRequest.getProductTypeName ());
		}
		if (! StringUtils.isEmpty (queryPurchaseRequest.getProductId ()))
		{
			params.put ("productId", queryPurchaseRequest.getProductId ());
		}
		if (! StringUtils.isEmpty (queryPurchaseRequest.getEmployeeId ()))
		{
			params.put ("employeeId", queryPurchaseRequest.getEmployeeId ());
		}
		if (! StringUtils.isEmpty (queryPurchaseRequest.getEmployeeName ()))
		{
			params.put ("employeeName", queryPurchaseRequest.getEmployeeName ());
		}
		if (! StringUtils.isEmpty (queryPurchaseRequest.getCreateTime ()))
		{
			params.put ("createTime", queryPurchaseRequest.getCreateTime ());
		}
		if (! StringUtils.isEmpty (queryPurchaseRequest.getMinCreateTime ()))
		{
			params.put ("minCreateTime", queryPurchaseRequest.getMinCreateTime ());
		}
		if (! StringUtils.isEmpty (queryPurchaseRequest.getMaxCreateTime ()))
		{
			params.put ("maxCreateTime", queryPurchaseRequest.getMaxCreateTime ());
		}
		if (null != queryPurchaseRequest.getPurchaseId ())
		{
			params.put ("purchaseId", queryPurchaseRequest.getPurchaseId ());
		}
		if (null != queryPurchaseRequest.getStorage ())
		{
			params.put ("storage", queryPurchaseRequest.getStorage ());
		}
		if (null != queryPurchaseRequest.getPageIndex () && null != queryPurchaseRequest.getPageSize ())
		{
			params.put ("start", (queryPurchaseRequest.getPageIndex () - 1) * queryPurchaseRequest.getPageSize ());
			params.put ("psize", (queryPurchaseRequest.getPageSize ()));
		}

		List<PurchaseDo> purchaseDos = null;
		int total = 0;
		try
		{
			purchaseDos = purchaseDao.queryPurchase (params);
			total = purchaseDao.countPurchase (params);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.PurchaseServiceImpl.queryPurchase.exception", e);
			response.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			response.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return response;
		}

		if (! CollectionUtils.isEmpty (purchaseDos))
		{
			List<PurchaseVo> purchaseVos = new ArrayList<> ();
			for (PurchaseDo purchaseDo : purchaseDos)
			{
				PurchaseVo purchaseVo = change2Vo (purchaseDo);
				purchaseVos.add (purchaseVo);
			}
			response.setPurchaseVos (purchaseVos);
		}
		response.setTotal (total);

		LOGGER.debug (String.format ("SMS.PurchaseServiceImpl.queryPurchase.response:%s", response));
		return response;
	}

	@Override
	public GetPurchaseResponse getPurchase (GetPurchaseRequest getPurchaseRequest)
	{
		LOGGER.debug (String.format ("SMS.PurchaseServiceImpl.getPurchase.request:%s", getPurchaseRequest));

		GetPurchaseResponse response = new GetPurchaseResponse ();

		if (null == getPurchaseRequest)
		{
			response.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			response.setResultDesc ("getPurchaseRequest can not be null");
			return response;
		}
		if (null == getPurchaseRequest.getPurchaseId ())
		{
			response.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			response.setResultDesc ("purchaseId can not be null");
			return response;
		}

		PurchaseDo purchaseDo = null;
		try
		{
			purchaseDo = purchaseDao.getPurchase (getPurchaseRequest.getPurchaseId ());
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.PurchaseServiceImpl.getPurchase.exception", e);
			response.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			response.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return response;
		}

		if (null != purchaseDo)
		{
			PurchaseVo purchaseVo = change2Vo (purchaseDo);
			response.setPurchaseVo (purchaseVo);
		}

		LOGGER.debug (String.format ("SMS.PurchaseServiceImpl.getPurchase.response:%s", response));
		return response;
	}

	private PurchaseDo change2Do (PurchaseVo purchaseVo)
	{
		PurchaseDo purchaseDo = new PurchaseDo ();
		purchaseDo.setProductId (purchaseVo.getProductId ());
		purchaseDo.setEmployeeId (purchaseVo.getEmployeeId ());
		purchaseDo.setPrice (purchaseVo.getPrice ());
		purchaseDo.setAmount (purchaseVo.getAmount ());
		purchaseDo.setMemo (purchaseVo.getMemo ());
		purchaseDo.setAddress (purchaseVo.getAddress ());
		purchaseDo.setPhone (purchaseVo.getPhone ());
		purchaseDo.setEmail (purchaseVo.getEmail ());

		return purchaseDo;
	}

	private PurchaseVo change2Vo (PurchaseDo purchaseDo)
	{
		PurchaseVo purchaseVo = new PurchaseVo ();
		purchaseVo.setPurchaseId (purchaseDo.getPurchaseId ());
		purchaseVo.setProductId (purchaseDo.getProductId ());
		purchaseVo.setEmployeeId (purchaseDo.getEmployeeId ());
		purchaseVo.setPrice (purchaseDo.getPrice ());
		purchaseVo.setAmount (purchaseDo.getAmount ());
		purchaseVo.setMemo (purchaseDo.getMemo ());
		purchaseVo.setCreateTime (purchaseDo.getCreateTime ());
		purchaseVo.setLastUpdateTime (purchaseDo.getLastUpdateTime ());
		purchaseVo.setProductName (purchaseDo.getProductName ());
		purchaseVo.setSupplier (purchaseDo.getSupplier ());
		purchaseVo.setEmployeeName (purchaseDo.getEmployeeName ());
		purchaseVo.setProductTypeId (purchaseDo.getProductTypeId ());
		purchaseVo.setProductTypeName (purchaseDo.getProductTypeName ());
		purchaseVo.setSupplierId (purchaseDo.getSupplierId ());
		purchaseVo.setStorage (purchaseDo.getStorage ());
		purchaseVo.setAddress (purchaseDo.getAddress ());
		purchaseVo.setPhone (purchaseDo.getPhone ());
		purchaseVo.setEmail (purchaseDo.getEmail ());

		return purchaseVo;
	}
}