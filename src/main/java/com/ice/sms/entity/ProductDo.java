/**
 * 文 件 名:  ProductDo
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  huangsongbo
 * 修改时间:  2017/12/5
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author huangsongbo
 * @version 2017/12/5
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Getter
@Setter
@ToString
public class ProductDo implements Serializable
{
	private String productId;
	private Integer productTypeId;
	private String productName;
	private String productScale;
	private BigDecimal price;
	private String supplier;
	private Integer stock;
	private String memo;
	private String createTime;
	private String lastUpdateTime;
	private String productTypeName;
	private Integer supplierId;
}
