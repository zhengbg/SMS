/**
 * 文 件 名:  ProductServiceImpl
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
import com.ice.sms.dao.ProductDao;
import com.ice.sms.dto.product.request.*;
import com.ice.sms.dto.product.response.GetProductResponse;
import com.ice.sms.dto.product.response.QueryProductResponse;
import com.ice.sms.dto.product.vo.ProductVo;
import com.ice.sms.entity.ProductDo;
import com.ice.sms.service.ProductService;
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
@Service (value = "productService")
public class ProductServiceImpl implements ProductService
{
	private static final Logger LOGGER = LogManager.getLogger (ProductServiceImpl.class);

	@Autowired
	private ProductDao productDao;

	@Override
	public ResultInfo addProduct (AddProductRequest addProductRequest)
	{
		LOGGER.debug (String.format ("SMS.ProductServiceImpl.addProduct.request:%s", addProductRequest));

		ResultInfo resultInfo = new ResultInfo ();

		if (null == addProductRequest)
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("addProductRequest can not be null");
			return resultInfo;
		}

		ProductVo productVo = addProductRequest.getProductVo ();
		if (null == productVo)
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("productVo can not be null");
			return resultInfo;
		}
		if (StringUtils.isEmpty (productVo.getProductId ()))
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("productId can not be null");
			return resultInfo;
		}
		if (StringUtils.isEmpty (productVo.getProductName ()))
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("productName can not be null");
			return resultInfo;
		}
		if (StringUtils.isEmpty (productVo.getProductScale ()))
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("productScale can not be null");
			return resultInfo;
		}
		if (null == productVo.getProductTypeId ())
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("productTypeId can not be null");
			return resultInfo;
		}
		if (null == productVo.getPrice ())
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("price can not be null");
			return resultInfo;
		}
		if (StringUtils.isEmpty (productVo.getSupplier ()))
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("supplier can not be null");
			return resultInfo;
		}
		if (null == productVo.getStock ())
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("stock can not be null");
			return resultInfo;
		}

		ProductDo productDo = change2Do (productVo);

		try
		{
			productDao.addProduct (productDo);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.ProductServiceImpl.addProduct.exception", e);
			resultInfo.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			resultInfo.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return resultInfo;
		}

		LOGGER.debug (String.format ("SMS.ProductServiceImpl.addProduct.response:%s", resultInfo));
		return resultInfo;
	}

	@Override
	public ResultInfo delProduct (DelProductRequest delProductRequest)
	{
		LOGGER.debug (String.format ("SMS.ProductServiceImpl.delProduct.request:%s", delProductRequest));

		ResultInfo resultInfo = new ResultInfo ();

		if (null == delProductRequest)
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("delProductRequest can not be null");
			return resultInfo;
		}

		List<String> productIds = delProductRequest.getProductIds ();

		if (! CollectionUtils.isEmpty (productIds))
		{

			try
			{
				productDao.delProduct (productIds);
			}
			catch (Exception e)
			{
				LOGGER.error ("SMS.ProductServiceImpl.delProduct.exception", e);
				resultInfo.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
				resultInfo.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
				return resultInfo;
			}

		}

		LOGGER.debug (String.format ("SMS.ProductServiceImpl.delProduct.response:%s", resultInfo));
		return resultInfo;
	}

	@Override
	public ResultInfo updateProduct (UpdateProductRequest updateProductRequest)
	{
		LOGGER.debug (String.format ("SMS.ProductServiceImpl.updateProduct.request:%s", updateProductRequest));

		ResultInfo resultInfo = new ResultInfo ();

		if (null == updateProductRequest)
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("updateProductRequest can not be null");
			return resultInfo;
		}
		if (StringUtils.isEmpty (updateProductRequest.getProductId ()))
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("productId can not be null");
			return resultInfo;
		}
		if (StringUtils.isEmpty (updateProductRequest.getLastUpdateTime ()))
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("lastUpdateTime can not be null");
			return resultInfo;
		}

		Map<String, Object> params = new HashMap<> ();
		if (! StringUtils.isEmpty (updateProductRequest.getProductName ()))
		{
			params.put ("productName", updateProductRequest.getProductName ());
		}
		if (! StringUtils.isEmpty (updateProductRequest.getProductScale ()))
		{
			params.put ("productScale", updateProductRequest.getProductScale ());
		}
		if (null != updateProductRequest.getPrice ())
		{
			params.put ("price", updateProductRequest.getPrice ());
		}
		if (! StringUtils.isEmpty (updateProductRequest.getSupplier ()))
		{
			params.put ("supplier", updateProductRequest.getSupplier ());
		}
		if (null != updateProductRequest.getProductTypeId ())
		{
			params.put ("productTypeId", updateProductRequest.getProductTypeId ());
		}
		if (! StringUtils.isEmpty (updateProductRequest.getMemo ()))
		{
			params.put ("memo", updateProductRequest.getMemo ());
		}
		params.put ("productId", updateProductRequest.getProductId ());
		params.put ("lastUpdateTime", updateProductRequest.getLastUpdateTime ());

		try
		{
			productDao.updateProduct (params);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.ProductServiceImpl.updateProduct.exception", e);
			resultInfo.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			resultInfo.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return resultInfo;
		}

		LOGGER.debug (String.format ("SMS.ProductServiceImpl.updateProduct.response:%s", resultInfo));
		return resultInfo;
	}

	@Override
	public QueryProductResponse queryProduct (QueryProductRequest queryProductRequest)
	{
		LOGGER.debug (String.format ("SMS.ProductServiceImpl.queryProduct.request:%s", queryProductRequest));

		QueryProductResponse response = new QueryProductResponse ();

		if (null == queryProductRequest)
		{
			response.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			response.setResultDesc ("queryProductRequest can not be null");
			return response;
		}

		Map<String, Object> params = new HashMap<> ();
		if (! StringUtils.isEmpty (queryProductRequest.getProductId ()))
		{
			params.put ("productId", queryProductRequest.getProductId ());
		}
		if (! StringUtils.isEmpty (queryProductRequest.getProductName ()))
		{
			params.put ("productName", queryProductRequest.getProductName ());
		}
		if (! StringUtils.isEmpty (queryProductRequest.getSupplier ()))
		{
			params.put ("supplier", queryProductRequest.getSupplier ());
		}
		if (null != queryProductRequest.getProductTypeId ())
		{
			params.put ("productTypeId", queryProductRequest.getProductTypeId ());
		}
		if (! StringUtils.isEmpty (queryProductRequest.getProductTypeName ()))
		{
			params.put ("productTypeName", queryProductRequest.getProductTypeName ());
		}
		if (null != queryProductRequest.getStock ())
		{
			params.put ("stock", queryProductRequest.getStock ());
		}
		if (! StringUtils.isEmpty (queryProductRequest.getCreateTime ()))
		{
			params.put ("createTime", queryProductRequest.getCreateTime ());
		}
		if (! StringUtils.isEmpty (queryProductRequest.getLastUpdateTime ()))
		{
			params.put ("lastUpdateTime", queryProductRequest.getLastUpdateTime ());
		}
		if (null != queryProductRequest.getMinStock ())
		{
			params.put ("minStock", queryProductRequest.getMinStock ());
		}
		if (null != queryProductRequest.getMaxStock ())
		{
			params.put ("maxStock", queryProductRequest.getMaxStock ());
		}
		if (! StringUtils.isEmpty (queryProductRequest.getMinCreateTime ()))
		{
			params.put ("minCreateTime", queryProductRequest.getMinCreateTime ());
		}
		if (! StringUtils.isEmpty (queryProductRequest.getMaxCreateTime ()))
		{
			params.put ("maxCreateTime", queryProductRequest.getMaxCreateTime ());
		}
		if (! StringUtils.isEmpty (queryProductRequest.getMinLastUpdateTime ()))
		{
			params.put ("minLastUpdateTime", queryProductRequest.getMinLastUpdateTime ());
		}
		if (! StringUtils.isEmpty (queryProductRequest.getMaxLastUpdateTime ()))
		{
			params.put ("maxLastUpdateTime", queryProductRequest.getMaxLastUpdateTime ());
		}
		if (null != queryProductRequest.getPageIndex () && null != queryProductRequest.getPageSize ())
		{
			params.put ("start", (queryProductRequest.getPageIndex () - 1) * queryProductRequest.getPageSize ());
			params.put ("psize", queryProductRequest.getPageSize ());
		}

		List<ProductDo> productDos = null;
		int total = 0;
		try
		{
			productDos = productDao.queryProduct (params);
			total = productDao.countProduct (params);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.ProductServiceImpl.queryProduct.exception", e);
			response.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			response.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return response;
		}

		if (! CollectionUtils.isEmpty (productDos))
		{
			List<ProductVo> productVos = new ArrayList<> ();
			for (ProductDo productDo : productDos)
			{
				ProductVo productVo = change2Vo (productDo);
				productVos.add (productVo);
			}
			response.setProductVos (productVos);
		}
		response.setTotal (total);

		LOGGER.debug (String.format ("SMS.ProductServiceImpl.queryProduct.response:%s", response));
		return response;
	}

	@Override
	public GetProductResponse getProduct (GetProductRequest getProductRequest)
	{
		LOGGER.debug (String.format ("SMS.ProductServiceImpl.getProduct.request:%s", getProductRequest));

		GetProductResponse response = new GetProductResponse ();

		if (null == getProductRequest)
		{
			response.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			response.setResultDesc ("getProductRequest can not be null");
			return response;
		}
		if (StringUtils.isEmpty (getProductRequest.getProductId ()))
		{
			response.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			response.setResultDesc ("productId can not be null");
			return response;
		}

		ProductDo productDo = null;
		try
		{
			productDo = productDao.getProduct (getProductRequest.getProductId ());
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.ProductServiceImpl.getProduct.exception", e);
			response.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			response.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return response;
		}

		if (null != productDo)
		{
			ProductVo productVo = change2Vo (productDo);
			response.setProductVo (productVo);
		}

		LOGGER.debug (String.format ("SMS.ProductServiceImpl.getProduct.response:%s", response));
		return response;
	}

	private ProductDo change2Do (ProductVo productVo)
	{
		ProductDo productDo = new ProductDo ();
		productDo.setProductId (productVo.getProductId ());
		productDo.setProductName (productVo.getProductName ());
		productDo.setProductScale (productVo.getProductScale ());
		productDo.setPrice (productVo.getPrice ());
		productDo.setSupplier (productVo.getSupplier ());
		productDo.setStock (productVo.getStock ());
		productDo.setProductTypeId (productVo.getProductTypeId ());
		productDo.setMemo (productVo.getMemo ());
		return productDo;
	}

	private ProductVo change2Vo (ProductDo productDo)
	{
		ProductVo productVo = new ProductVo ();
		productVo.setProductId (productDo.getProductId ());
		productVo.setProductName (productDo.getProductName ());
		productVo.setProductScale (productDo.getProductScale ());
		productVo.setPrice (productDo.getPrice ());
		productVo.setSupplier (productDo.getSupplier ());
		productVo.setStock (productDo.getStock ());
		productVo.setProductTypeId (productDo.getProductTypeId ());
		productVo.setMemo (productDo.getMemo ());
		productVo.setCreateTime (productDo.getCreateTime ());
		productVo.setLastUpdateTime (productDo.getCreateTime ());
		productVo.setProductTypeName (productDo.getProductTypeName ());
		return productVo;
	}

}