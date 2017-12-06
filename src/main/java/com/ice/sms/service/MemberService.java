/**
 * 文 件 名:  MemberService
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhengbinggui
 * 修改时间:  2017/12/5
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.service;

import com.ice.sms.common.base.ResultInfo;
import com.ice.sms.dto.member.request.*;
import com.ice.sms.dto.member.response.QueryMemberByIdResponse;
import com.ice.sms.dto.member.response.QueryMemberResponse;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author zhengbinggui
 * @version 2017/12/5
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface MemberService
{
	ResultInfo addMember (AddMemberRequest addMemberRequest);

	ResultInfo updateMember (UpdateMemberRequest updateMemberRequest);

	ResultInfo delMember (DelMemberRequest delMemberRequest);

	QueryMemberResponse queryMember (QueryMemberRequest queryMemberRequest);

	QueryMemberByIdResponse queryMemberById (QueryMemberByIdRequest queryMemberByIdRequest);
}