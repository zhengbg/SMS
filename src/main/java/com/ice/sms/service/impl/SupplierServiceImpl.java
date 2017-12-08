/**
 * 文 件 名:  SupplierServiceImpl
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhengbinggui
 * 修改时间:  2017/12/8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.service.impl;

import com.ice.sms.common.base.ResultInfo;
import com.ice.sms.common.constant.Constant;
import com.ice.sms.dao.SupplierDao;
import com.ice.sms.dto.supplier.request.*;
import com.ice.sms.dto.supplier.response.GetSupplierResponse;
import com.ice.sms.dto.supplier.response.QuerySupplierResponse;
import com.ice.sms.dto.supplier.vo.SupplierVo;
import com.ice.sms.entity.SupplierDo;
import com.ice.sms.service.SupplierService;
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
 * @version 2017/12/8
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service (value = "supplierService")
public class SupplierServiceImpl implements SupplierService
{
	private static final Logger LOGGER = LogManager.getLogger (SupplierServiceImpl.class);

	@Autowired
	private SupplierDao supplierDao;

	@Override
	public ResultInfo addSupplier (AddSupplierRequest addSupplierRequest)
	{
		LOGGER.debug (String.format ("SMS.SupplierServiceImpl.addSupplier.request:%s", addSupplierRequest));

		ResultInfo resultInfo = new ResultInfo ();

		if (null == addSupplierRequest)
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("addSupplierRequest can not be null");
			return resultInfo;
		}

		SupplierVo supplierVo = addSupplierRequest.getSupplierVo ();
		if (null == supplierVo)
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("supplierVo can not be null");
			return resultInfo;
		}
		if (StringUtils.isEmpty (supplierVo.getSupplier ()))
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("supplier can not be null");
			return resultInfo;
		}

		SupplierDo supplierDo = change2Do (supplierVo);

		try
		{
			supplierDao.addSupplier (supplierDo);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.SupplierServiceImpl.addSupplier.exception", e);
			resultInfo.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			resultInfo.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return resultInfo;
		}

		LOGGER.debug (String.format ("SMS.SupplierServiceImpl.addSupplier.response:%s", resultInfo));
		return resultInfo;
	}

	@Override
	public ResultInfo delSupplier (DelSupplierRequest delSupplierRequest)
	{
		LOGGER.debug (String.format ("SMS.SupplierServiceImpl.delSupplier.request:%s", delSupplierRequest));

		ResultInfo resultInfo = new ResultInfo ();

		if (null == delSupplierRequest)
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("delSupplierRequest can not be null");
			return resultInfo;
		}

		List<Integer> supplierIds = delSupplierRequest.getSupplierIds ();
		if (! CollectionUtils.isEmpty (supplierIds))
		{
			try
			{
				supplierDao.delSupplier (supplierIds);
			}
			catch (Exception e)
			{
				LOGGER.error ("SMS.SupplierServiceImpl.delSupplier.exception", e);
				resultInfo.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
				resultInfo.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
				return resultInfo;
			}
		}

		LOGGER.debug (String.format ("SMS.SupplierServiceImpl.delSupplier.response:%s", resultInfo));
		return resultInfo;
	}

	@Override
	public ResultInfo updateSupplier (UpdateSupplierRequest updateSupplierRequest)
	{
		LOGGER.debug (String.format ("SMS.SupplierServiceImpl.updateSupplier.request:%s", updateSupplierRequest));

		ResultInfo resultInfo = new ResultInfo ();

		if (null == updateSupplierRequest)
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("updateSupplierRequest can not be null");
			return resultInfo;
		}
		if (null == updateSupplierRequest.getSupplierId ())
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("supplierId can not be null");
			return resultInfo;
		}
		if (StringUtils.isEmpty (updateSupplierRequest.getLastUpdateTime ()))
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("supplier can not be null");
			return resultInfo;
		}

		Map<String, Object> params = new HashMap<> ();
		if (! StringUtils.isEmpty (updateSupplierRequest.getSupplier ()))
		{
			params.put ("supplier", updateSupplierRequest.getSupplier ());
		}
		params.put ("supplierId", updateSupplierRequest.getSupplierId ());
		params.put ("lastUpdateTime", updateSupplierRequest.getLastUpdateTime ());

		try
		{
			supplierDao.updateSupplier (params);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.SupplierServiceImpl.updateSupplier.exception", e);
			resultInfo.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			resultInfo.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return resultInfo;
		}

		LOGGER.debug (String.format ("SMS.SupplierServiceImpl.updateSupplier.response:%s", resultInfo));
		return resultInfo;
	}

	@Override
	public QuerySupplierResponse querySupplier (QuerySupplierRequest querySupplierRequest)
	{
		LOGGER.debug (String.format ("SMS.SupplierServiceImpl.querySupplier.request:%s", querySupplierRequest));

		QuerySupplierResponse response = new QuerySupplierResponse ();

		if (null == querySupplierRequest)
		{
			response.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			response.setResultDesc ("querySupplierRequest can not be null");
			return response;
		}

		Map<String, Object> params = new HashMap<> ();
		if (null != querySupplierRequest.getSupplierId ())
		{
			params.put ("supplierId", querySupplierRequest.getSupplierId ());
		}
		if (! StringUtils.isEmpty (querySupplierRequest.getSupplier ()))
		{
			params.put ("supplier", querySupplierRequest.getSupplier ());
		}
		if (! StringUtils.isEmpty (querySupplierRequest.getCreateTime ()))
		{
			params.put ("createTime", querySupplierRequest.getCreateTime ());
		}
		if (! StringUtils.isEmpty (querySupplierRequest.getLastUpdateTime ()))
		{
			params.put ("lastUpdateTime", querySupplierRequest.getLastUpdateTime ());
		}
		if (! StringUtils.isEmpty (querySupplierRequest.getMinCreateTime ()))
		{
			params.put ("minCreateTime", querySupplierRequest.getMinCreateTime ());
		}
		if (! StringUtils.isEmpty (querySupplierRequest.getMaxCreateTime ()))
		{
			params.put ("maxCreateTime", querySupplierRequest.getMaxCreateTime ());
		}
		if (! StringUtils.isEmpty (querySupplierRequest.getMinLastUpdateTime ()))
		{
			params.put ("minLastUpdateTime", querySupplierRequest.getMinLastUpdateTime ());
		}
		if (! StringUtils.isEmpty (querySupplierRequest.getMaxLastUpdateTime ()))
		{
			params.put ("maxLastUpdateTime", querySupplierRequest.getMaxLastUpdateTime ());
		}
		if (null != querySupplierRequest.getPageIndex () && null != querySupplierRequest.getPageSize ())
		{
			params.put ("start", (querySupplierRequest.getPageIndex () - 1) * querySupplierRequest.getPageSize ());
			params.put ("psize", querySupplierRequest.getPageSize ());
		}

		List<SupplierDo> supplierDos = null;
		int total = 0;
		try
		{
			supplierDos = supplierDao.querySupplier (params);
			total = supplierDao.countSupplier (params);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.SupplierServiceImpl.querySupplier.exception", e);
			response.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			response.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return response;
		}

		if (! CollectionUtils.isEmpty (supplierDos))
		{
			List<SupplierVo> supplierVos = new ArrayList<> ();
			for (SupplierDo supplierDo : supplierDos)
			{
				SupplierVo supplierVo = change2Vo (supplierDo);
				supplierVos.add (supplierVo);
			}
			response.setSupplierVos (supplierVos);
		}
		response.setTotal (total);

		LOGGER.debug (String.format ("SMS.SupplierServiceImpl.querySupplier.response:%s", response));
		return response;
	}

	@Override
	public GetSupplierResponse getSupplier (GetSupplierRequest getSupplierRequest)
	{
		LOGGER.debug (String.format ("SMS.SupplierServiceImpl.getSupplier.request:%s", getSupplierRequest));

		GetSupplierResponse response = new GetSupplierResponse ();

		if (null == getSupplierRequest)
		{
			response.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			response.setResultDesc ("getSupplierRequest can not be null");
			return response;
		}
		if (null == getSupplierRequest.getSupplierId ())
		{
			response.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			response.setResultDesc ("supplierId can not be null");
			return response;
		}

		SupplierDo supplierDo = null;
		try
		{
			supplierDo = supplierDao.getSupplier (getSupplierRequest.getSupplierId ());
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.SupplierServiceImpl.getSupplier.exception", e);
			response.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			response.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return response;
		}

		if (null != supplierDo)
		{
			SupplierVo supplierVo = change2Vo (supplierDo);
			response.setSupplierVo (supplierVo);
		}

		LOGGER.debug (String.format ("SMS.SupplierServiceImpl.getSupplier.response:%s", response));
		return response;
	}

	private SupplierDo change2Do (SupplierVo supplierVo)
	{
		SupplierDo supplierDo = new SupplierDo ();
		supplierDo.setSupplier (supplierVo.getSupplier ());

		return supplierDo;
	}

	private SupplierVo change2Vo (SupplierDo supplierDo)
	{
		SupplierVo supplierVo = new SupplierVo ();
		supplierVo.setSupplierId (supplierDo.getSupplierId ());
		supplierVo.setSupplier (supplierDo.getSupplier ());
		supplierVo.setCreateTime (supplierDo.getCreateTime ());
		supplierVo.setLastUpdateTime (supplierDo.getLastUpdateTime ());

		return supplierVo;
	}
}