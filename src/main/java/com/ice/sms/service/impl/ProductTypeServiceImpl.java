/**
 * 文 件 名:  ProductTypeServiceImpl
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhengbinggui
 * 修改时间:  2017/12/6
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.service.impl;

import com.ice.sms.common.base.ResultInfo;
import com.ice.sms.common.constant.Constant;
import com.ice.sms.dao.ProductTypeDao;
import com.ice.sms.dto.producttype.request.*;
import com.ice.sms.dto.producttype.response.GetProductTypeResponse;
import com.ice.sms.dto.producttype.response.QueryProductTypeResponse;
import com.ice.sms.dto.producttype.vo.ProductTypeVo;
import com.ice.sms.entity.ProductTypeDo;
import com.ice.sms.service.ProductTypeService;
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
 * @version 2017/12/6
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service (value = "productTypeService")
public class ProductTypeServiceImpl implements ProductTypeService
{
	private static final Logger LOGGER = LogManager.getLogger (ProductTypeServiceImpl.class);

	@Autowired
	private ProductTypeDao productTypeDao;

	@Override
	public ResultInfo addProductType (AddProductTypeRequest addProductTypeRequest)
	{
		LOGGER.debug (String.format ("SMS.ProductTypeServiceImpl.addProductType.request:%s", addProductTypeRequest));

		ResultInfo resultInfo = new ResultInfo ();

		if (null == addProductTypeRequest)
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("addProductTypeRequest can not be null");
			return resultInfo;
		}

		ProductTypeVo productTypeVo = addProductTypeRequest.getProductTypeVo ();
		if (StringUtils.isEmpty (productTypeVo.getProductTypeName ()))
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("productTypeName can not be null");
			return resultInfo;
		}

		ProductTypeDo productTypeDo = change2Do (productTypeVo);

		try
		{
			productTypeDao.addProductType (productTypeDo);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.ProductTypeServiceImpl.addProductType.exception", e);
			resultInfo.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			resultInfo.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return resultInfo;
		}

		LOGGER.debug (String.format ("SMS.ProductTypeServiceImpl.addProductType.response:%s", resultInfo));
		return resultInfo;
	}

	@Override
	public ResultInfo delProductType (DelProductTypeRequest delProductTypeRequest)
	{
		LOGGER.debug (String.format ("SMS.ProductTypeServiceImpl.delProductType.request:%s", delProductTypeRequest));

		ResultInfo resultInfo = new ResultInfo ();

		if (null == delProductTypeRequest)
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("delProductTypeRequest can not be null");
			return resultInfo;
		}

		List<Integer> productTypeIds = delProductTypeRequest.getProductTypeIds ();
		if (! CollectionUtils.isEmpty (productTypeIds))
		{
			try
			{
				productTypeDao.delProductType (productTypeIds);
			}
			catch (Exception e)
			{
				LOGGER.error ("SMS.ProductTypeServiceImpl.delProductType.exception", e);
				resultInfo.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
				resultInfo.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
				return resultInfo;
			}
		}

		LOGGER.debug (String.format ("SMS.ProductTypeServiceImpl.delProductType.response:%s", resultInfo));
		return resultInfo;
	}

	@Override
	public ResultInfo updateProductType (UpdateProductTypeRequest updateProductTypeRequest)
	{
		LOGGER.debug (
				String.format ("SMS.ProductTypeServiceImpl.updateProductType.request:%s", updateProductTypeRequest));

		ResultInfo resultInfo = new ResultInfo ();

		if (null == updateProductTypeRequest)
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("updateProductTypeRequest can not be null");
			return resultInfo;
		}
		if (null == updateProductTypeRequest.getProductTypeId ())
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("productTypeId can not be null");
			return resultInfo;
		}
		if (StringUtils.isEmpty (updateProductTypeRequest.getLastUpdateTime ()))
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("lastUpdateTime can not be null");
			return resultInfo;
		}

		Map<String, Object> params = new HashMap<> ();
		if (! StringUtils.isEmpty (updateProductTypeRequest.getProductTypeName ()))
		{
			params.put ("productTypeName", updateProductTypeRequest.getProductTypeName ());
		}
		if (! StringUtils.isEmpty (updateProductTypeRequest.getMemo ()))
		{
			params.put ("memo", updateProductTypeRequest.getMemo ());
		}
		params.put ("productTypeId", updateProductTypeRequest.getProductTypeId ());
		params.put ("lastUpdateTime", updateProductTypeRequest.getLastUpdateTime ());

		try
		{
			productTypeDao.updateProductType (params);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.ProductTypeServiceImpl.updateProductType.exception", e);
			resultInfo.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			resultInfo.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return resultInfo;
		}

		LOGGER.debug (String.format ("SMS.ProductTypeServiceImpl.updateProductType.response:%s", resultInfo));
		return resultInfo;
	}

	@Override
	public QueryProductTypeResponse queryProductType (QueryProductTypeRequest queryProductTypeRequest)
	{
		LOGGER.debug (
				String.format ("SMS.ProductTypeServiceImpl.queryProductType.request:%s", queryProductTypeRequest));

		QueryProductTypeResponse response = new QueryProductTypeResponse ();

		if (null == queryProductTypeRequest)
		{
			response.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			response.setResultDesc ("queryProductTypeRequest can not be null");
			return response;
		}

		Map<String, Object> params = new HashMap<> ();
		if (! StringUtils.isEmpty (queryProductTypeRequest.getProductTypeName ()))
		{
			params.put ("productTypeName", queryProductTypeRequest.getProductTypeName ());
		}
		if (! StringUtils.isEmpty (queryProductTypeRequest.getCreateTime ()))
		{
			params.put ("createTime", queryProductTypeRequest.getCreateTime ());
		}
		if (! StringUtils.isEmpty (queryProductTypeRequest.getLastUpdateTime ()))
		{
			params.put ("lastUpdateTime", queryProductTypeRequest.getLastUpdateTime ());
		}
		if (! StringUtils.isEmpty (queryProductTypeRequest.getMinCreateTime ()))
		{
			params.put ("minCreateTime", queryProductTypeRequest.getMinCreateTime ());
		}
		if (! StringUtils.isEmpty (queryProductTypeRequest.getMaxCreateTime ()))
		{
			params.put ("maxCreateTime", queryProductTypeRequest.getMaxCreateTime ());
		}
		if (! StringUtils.isEmpty (queryProductTypeRequest.getMinLastUpdateTime ()))
		{
			params.put ("minLastUpdateTime", queryProductTypeRequest.getMinLastUpdateTime ());
		}
		if (! StringUtils.isEmpty (queryProductTypeRequest.getMaxLastUpdateTime ()))
		{
			params.put ("maxLastUpdateTime", queryProductTypeRequest.getMaxLastUpdateTime ());
		}
		if (null != queryProductTypeRequest.getPageIndex () && null != queryProductTypeRequest.getPageSize ())
		{
			params.put ("start",
					(queryProductTypeRequest.getPageIndex () - 1) * queryProductTypeRequest.getPageSize ());
			params.put ("psize", queryProductTypeRequest.getPageSize ());
		}

		List<ProductTypeDo> productTypeDos = null;
		int total = 0;
		try
		{
			productTypeDos = productTypeDao.queryProductType (params);
			total = productTypeDao.countProductType (params);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.ProductTypeServiceImpl.queryProductType.exception", e);
			response.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			response.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return response;
		}

		if (! CollectionUtils.isEmpty (productTypeDos))
		{
			List<ProductTypeVo> productTypeVos = new ArrayList<> ();

			//productTypeDos.forEach (productTypeDo -> productTypeVos.add (change2Do (productTypeDo)));
			for (ProductTypeDo productTypeDo : productTypeDos)
			{
				ProductTypeVo productTypeVo = change2Vo (productTypeDo);
				productTypeVos.add (productTypeVo);
			}
			response.setProductTypeVos (productTypeVos);
		}
		response.setTotal (total);

		LOGGER.debug (String.format ("SMS.ProductTypeServiceImpl.queryProductType.response:%s", response));
		return response;
	}

	@Override
	public GetProductTypeResponse getProductType (GetProductTypeRequest getProductTypeRequest)
	{
		LOGGER.debug (String.format ("SMS.ProductTypeServiceImpl.getProductType.request:%s", getProductTypeRequest));

		GetProductTypeResponse response = new GetProductTypeResponse ();

		if (null == getProductTypeRequest)
		{
			response.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			response.setResultDesc ("getProductTypeRequest can not be null");
			return response;
		}
		if (StringUtils.isEmpty (getProductTypeRequest.getProductTypeId ()))
		{
			response.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			response.setResultDesc ("productTypeId can not be null");
			return response;
		}

		ProductTypeDo productTypeDo = null;
		try
		{
			productTypeDo = productTypeDao.getProductType (getProductTypeRequest.getProductTypeId ());
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.ProductTypeServiceImpl.getProductType.exception", e);
			response.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			response.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return response;
		}

		if (null != productTypeDo)
		{
			response.setProductTypeVo (change2Vo (productTypeDo));
		}

		LOGGER.debug (String.format ("SMS.ProductTypeServiceImpl.getProductType.response:%s", response));
		return response;
	}

	private ProductTypeDo change2Do (ProductTypeVo productTypeVo)
	{
		ProductTypeDo productTypeDo = new ProductTypeDo ();
		productTypeDo.setProductTypeName (productTypeVo.getProductTypeName ());
		productTypeDo.setMemo (productTypeVo.getMemo ());
		return productTypeDo;
	}

	private ProductTypeVo change2Vo (ProductTypeDo productTypeDo)
	{
		ProductTypeVo productTypeVo = new ProductTypeVo ();
		productTypeVo.setProductTypeId (productTypeDo.getProductTypeId ());
		productTypeVo.setProductTypeName (productTypeDo.getProductTypeName ());
		productTypeVo.setMemo (productTypeDo.getMemo ());
		productTypeVo.setCreateTime (productTypeDo.getCreateTime ());
		productTypeVo.setLastUpdateTime (productTypeDo.getLastUpdateTime ());
		return productTypeVo;
	}
}