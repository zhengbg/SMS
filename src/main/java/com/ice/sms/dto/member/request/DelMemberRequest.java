/**
 * 文 件 名:  DelMemberRequest
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhengbinggui
 * 修改时间:  2017/12/6
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.dto.member.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author zhengbinggui
 * @version 2017/12/6
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Getter
@Setter
@ToString
public class DelMemberRequest implements Serializable
{
	private List<String> memberIds;
}