/**
 * 文 件 名:  MemberController
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhengbinggui
 * 修改时间:  2017/12/5
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.controller;

import com.ice.sms.common.base.ResultInfo;
import com.ice.sms.dto.member.request.*;
import com.ice.sms.dto.member.response.QueryMemberByIdResponse;
import com.ice.sms.dto.member.response.QueryMemberResponse;
import com.ice.sms.service.MemberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author zhengbinggui
 * @version 2017/12/5
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@RestController
@RequestMapping ("/member")
public class MemberController
{
	private static final Logger LOGGER = LogManager.getLogger (MemberController.class);

	@Autowired
	private MemberService memberService;

	@RequestMapping (value = "/addMember", method = RequestMethod.POST)
	public ResultInfo addMember (@RequestBody AddMemberRequest addMemberRequest)
	{
		ResultInfo resultInfo = memberService.addMember (addMemberRequest);
		return resultInfo;
	}

	@RequestMapping (value = "/updateMember", method = RequestMethod.POST)
	public ResultInfo updateMember (@RequestBody UpdateMemberRequest updateMemberRequest)
	{
		ResultInfo resultInfo = memberService.updateMember (updateMemberRequest);
		return resultInfo;
	}

	@RequestMapping (value = "/delMember", method = RequestMethod.POST)
	public ResultInfo delMember (@RequestBody DelMemberRequest delMemberRequest)
	{
		ResultInfo resultInfo = memberService.delMember (delMemberRequest);
		return resultInfo;
	}

	@RequestMapping (value = "/queryMember", method = RequestMethod.POST)
	public QueryMemberResponse queryMember (@RequestBody QueryMemberRequest queryMemberRequest)
	{
		QueryMemberResponse response = memberService.queryMember (queryMemberRequest);
		return response;
	}

	@RequestMapping (value = "/queryMemberById", method = RequestMethod.POST)
	public QueryMemberByIdResponse queryMemberById (@RequestBody QueryMemberByIdRequest queryMemberByIdRequest)
	{
		QueryMemberByIdResponse response = memberService.queryMemberById (queryMemberByIdRequest);
		return response;
	}
}