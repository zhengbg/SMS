/**
 * 文 件 名:  MemberServiceImpl
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  zhengbinggui
 * 修改时间:  2017/12/5
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.service.impl;

import com.ice.sms.common.base.ResultInfo;
import com.ice.sms.common.constant.Constant;
import com.ice.sms.dao.MemberDao;
import com.ice.sms.dto.member.request.*;
import com.ice.sms.dto.member.response.QueryMemberByIdResponse;
import com.ice.sms.dto.member.response.QueryMemberResponse;
import com.ice.sms.dto.member.vo.MemberVo;
import com.ice.sms.entity.MemberDo;
import com.ice.sms.service.MemberService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author zhengbinggui
 * @version 2017/12/5
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service (value = "memberService")
public class MemberServiceImpl implements MemberService
{
	private static final Logger LOGGER = LogManager.getLogger (MemberServiceImpl.class);

	@Autowired
	private MemberDao memberDao;

	@Override
	public ResultInfo addMember (AddMemberRequest addMemberRequest)
	{
		LOGGER.debug (String.format ("SMS.MemberServiceImpl.addMember.request:%s", addMemberRequest));

		ResultInfo resultInfo = new ResultInfo ();

		if (null == addMemberRequest)
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("addMemberRequest can not be null");
			return resultInfo;
		}

		MemberVo memberVo = addMemberRequest.getMemberVo ();

		if (StringUtils.isEmpty (memberVo.getMemberId ()))
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("memberId can not be null");
			return resultInfo;
		}
		if (StringUtils.isEmpty (memberVo.getMemberName ()))
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("memberName can not be null");
			return resultInfo;
		}
		if (StringUtils.isEmpty (memberVo.getPhone ()))
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("phone can not be null");
			return resultInfo;
		}
		if (StringUtils.isEmpty (memberVo.getIdCard ()))
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("ID card can not be null");
			return resultInfo;
		}

		MemberDo memberDo = change2Do (memberVo);

		if (null == memberDo.getBalance ())
		{
			memberDo.setBalance (new BigDecimal (0));
		}

		try
		{
			memberDao.addMember (memberDo);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.MemberServiceImpl.addMember.exception", e);
			resultInfo.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			resultInfo.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return resultInfo;
		}

		LOGGER.debug (String.format ("SMS.MemberServiceImpl.addMember.response:%s", resultInfo));
		return resultInfo;
	}

	@Override
	public ResultInfo updateMember (UpdateMemberRequest updateMemberRequest)
	{
		LOGGER.debug (String.format ("SMS.MemberServiceImpl.updateMember.request:%s", updateMemberRequest));

		ResultInfo resultInfo = new ResultInfo ();

		if (null == updateMemberRequest)
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("updateMemberRequest can not be null");
			return resultInfo;
		}
		if (StringUtils.isEmpty (updateMemberRequest.getMemberId ()))
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("memberId can not be null");
			return resultInfo;
		}
		if (StringUtils.isEmpty (updateMemberRequest.getLastUpdateTime ()))
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("last update time can not be null");
			return resultInfo;
		}

		Map<String, Object> params = new HashMap<String, Object> ();
		if (! StringUtils.isEmpty (updateMemberRequest.getMemberName ()))
		{
			params.put ("memberName", updateMemberRequest.getMemberName ());
		}
		if (! StringUtils.isEmpty (updateMemberRequest.getAddress ()))
		{
			params.put ("address", updateMemberRequest.getAddress ());
		}
		if (! StringUtils.isEmpty (updateMemberRequest.getPhone ()))
		{
			params.put ("phone", updateMemberRequest.getPhone ());
		}
		if (null != updateMemberRequest.getBalance ())
		{
			params.put ("balance", updateMemberRequest.getBalance ());
		}
		params.put ("memberId", updateMemberRequest.getMemberId ());
		params.put ("lastUpdateTime", updateMemberRequest.getLastUpdateTime ());

		try
		{
			memberDao.updateMember (params);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.MemberServiceImpl.updateMember.exception", e);
			resultInfo.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			resultInfo.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return resultInfo;
		}

		LOGGER.debug (String.format ("SMS.MemberServiceImpl.updateMember.response:%s", resultInfo));
		return resultInfo;
	}

	@Override
	public ResultInfo delMember (DelMemberRequest delMemberRequest)
	{
		LOGGER.debug (String.format ("SMS.MemberServiceImpl.delMember.request:%s", delMemberRequest));

		ResultInfo resultInfo = new ResultInfo ();

		if (null == delMemberRequest)
		{
			resultInfo.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			resultInfo.setResultDesc ("delMemberRequest can not be null");
			return resultInfo;
		}

		List<String> memberIds = delMemberRequest.getMemberIds ();
		if (! CollectionUtils.isEmpty (memberIds))
		{
			try
			{
				memberDao.delMember (memberIds);
			}
			catch (Exception e)
			{
				LOGGER.error ("SMS.MemberServiceImpl.delMember.exception", e);
				resultInfo.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
				resultInfo.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
				return resultInfo;
			}
		}

		LOGGER.debug (String.format ("SMS.MemberServiceImpl.delMember.response:%s", resultInfo));
		return resultInfo;
	}

	@Override
	public QueryMemberResponse queryMember (QueryMemberRequest queryMemberRequest)
	{
		LOGGER.debug (String.format ("SMS.MemberServiceImpl.queryMember.request:%s", queryMemberRequest));

		QueryMemberResponse response = new QueryMemberResponse ();

		if (null == queryMemberRequest)
		{
			response.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			response.setResultDesc ("queryMemberRequest can not be null");
			return response;
		}

		Map<String, Object> params = new HashMap<String, Object> ();

		if (! StringUtils.isEmpty (queryMemberRequest.getMemberName ()))
		{
			params.put ("memberName", queryMemberRequest.getMemberName ());
		}
		if (! StringUtils.isEmpty (queryMemberRequest.getAddress ()))
		{
			params.put ("address", queryMemberRequest.getAddress ());
		}
		if (! StringUtils.isEmpty (queryMemberRequest.getPhone ()))
		{
			params.put ("phone", queryMemberRequest.getPhone ());
		}
		if (! StringUtils.isEmpty (queryMemberRequest.getIdCard ()))
		{
			params.put ("idCard", queryMemberRequest.getIdCard ());
		}
		if (! StringUtils.isEmpty (queryMemberRequest.getCreateTime ()))
		{
			params.put ("createTime", queryMemberRequest.getCreateTime ());
		}
		if (! StringUtils.isEmpty (queryMemberRequest.getLastUpdateTime ()))
		{
			params.put ("lastUpdateTime", queryMemberRequest.getLastUpdateTime ());
		}
		if (! StringUtils.isEmpty (queryMemberRequest.getMinCreateTime ()))
		{
			params.put ("minCreateTime", queryMemberRequest.getMinCreateTime ());
		}
		if (! StringUtils.isEmpty (queryMemberRequest.getMaxCreateTime ()))
		{
			params.put ("maxCreateTime", queryMemberRequest.getMaxCreateTime ());
		}
		if (! StringUtils.isEmpty (queryMemberRequest.getMinLastUpdateTime ()))
		{
			params.put ("minLastUpdateTime", queryMemberRequest.getMinLastUpdateTime ());
		}
		if (! StringUtils.isEmpty (queryMemberRequest.getMaxLastUpdateTime ()))
		{
			params.put ("maxLastUpdateTime", queryMemberRequest.getMaxLastUpdateTime ());
		}
		if (null != queryMemberRequest.getPageIndex () && null != queryMemberRequest.getPageSize ())
		{
			params.put ("start", (queryMemberRequest.getPageIndex () - 1) * queryMemberRequest.getPageSize ());
			params.put ("psize", queryMemberRequest.getPageSize ());
		}

		List<MemberDo> memberDos = null;
		int total = 0;
		try
		{
			memberDos = memberDao.queryMember (params);
			total = memberDao.countMember (params);
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.MemberServiceImpl.queryMember.exception", e);
			response.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			response.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return response;
		}

		if (! CollectionUtils.isEmpty (memberDos))
		{
			List<MemberVo> memberVos = new ArrayList<MemberVo> ();
			for (MemberDo memberDo : memberDos)
			{
				MemberVo memberVo = change2Vo (memberDo);
				memberVos.add (memberVo);
			}
			response.setMemberVos (memberVos);
		}
		response.setTotal (total);

		LOGGER.debug (String.format ("SMS.MemberServiceImpl.queryMember.response:%s", response));
		return response;
	}

	@Override
	public QueryMemberByIdResponse queryMemberById (QueryMemberByIdRequest queryMemberByIdRequest)
	{
		LOGGER.debug (String.format ("SMS.MemberServiceImpl.queryMemberById.request:%s", queryMemberByIdRequest));

		QueryMemberByIdResponse response = new QueryMemberByIdResponse ();

		if (null == queryMemberByIdRequest)
		{
			response.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			response.setResultDesc ("queryMemberByIdRequest can not be null");
			return response;
		}
		if (StringUtils.isEmpty (queryMemberByIdRequest.getMemberId ()))
		{
			response.setResultCode (Constant.Common.MISSING_PARAMETERS_CODE);
			response.setResultDesc ("memberId can not be null");
			return response;
		}

		MemberDo memberDo = null;
		try
		{
			memberDo = memberDao.queryMemberById (queryMemberByIdRequest.getMemberId ());
		}
		catch (Exception e)
		{
			LOGGER.error ("SMS.MemberServiceImpl.queryMemberById.exception", e);
			response.setResultCode (Constant.Common.SQL_EXCEPTION_CODE);
			response.setResultDesc (Constant.Common.SQL_EXCEPTION_DESC);
			return response;
		}

		MemberVo memberVo = change2Vo (memberDo);
		response.setMemberVo (memberVo);

		LOGGER.debug (String.format ("SMS.MemberServiceImpl.queryMemberById.response:%s", response));
		return response;
	}

	private MemberDo change2Do (MemberVo memberVo)
	{
		MemberDo memberDo = new MemberDo ();
		memberDo.setMemberId (memberVo.getMemberId ());
		memberDo.setMemberName (memberVo.getMemberName ());
		memberDo.setAddress (memberVo.getAddress ());
		memberDo.setPhone (memberVo.getPhone ());
		memberDo.setIdCard (memberVo.getIdCard ());
		memberDo.setBalance (memberVo.getBalance ());
		memberDo.setCreateTime (memberVo.getCreateTime ());
		memberDo.setLastUpdateTime (memberVo.getLastUpdateTime ());
		return memberDo;
	}

	private MemberVo change2Vo (MemberDo memberDo)
	{
		MemberVo memberVo = new MemberVo ();
		memberVo.setMemberId (memberDo.getMemberId ());
		memberVo.setMemberName (memberDo.getMemberName ());
		memberVo.setAddress (memberDo.getAddress ());
		memberVo.setPhone (memberDo.getPhone ());
		memberVo.setIdCard (memberDo.getIdCard ());
		memberVo.setBalance (memberDo.getBalance ());
		memberVo.setCreateTime (memberDo.getCreateTime ());
		memberVo.setLastUpdateTime (memberDo.getLastUpdateTime ());
		return memberVo;
	}
}