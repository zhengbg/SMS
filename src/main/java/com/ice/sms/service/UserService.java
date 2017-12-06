/**
 * 文 件 名:  UserService
 * 版    权:  Quanten Teams. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  huangsongbo
 * 修改时间:  2017/12/5
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.ice.sms.service;

import com.ice.sms.common.base.ResultInfo;
import com.ice.sms.dto.user.request.BatchDelUserReq;
import com.ice.sms.dto.user.response.CountUserResp;
import com.ice.sms.dto.user.request.QueryByIdReq;
import com.ice.sms.dto.user.request.QueryUserListReq;
import com.ice.sms.dto.user.response.QueryByIdResp;
import com.ice.sms.dto.user.response.QueryUserListResp;
import com.ice.sms.dto.user.vo.UserVo;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author huangsongbo
 * @version 2017/12/5
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface UserService
{
	QueryByIdResp queryUserById (QueryByIdReq req);

	QueryUserListResp queryUserList (QueryUserListReq req);

	ResultInfo batchDelUser(BatchDelUserReq req);

	ResultInfo updateUser (UserVo userVo);

	ResultInfo addUser (UserVo userVo);

	CountUserResp countUser (QueryByIdReq req);
}