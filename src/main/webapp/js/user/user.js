/**
 * Created by Administrator on 2017/12/6.
 */
var pageIndex = 1;
var userName = "";
var status = "";
var flag = "";
$("#user-plus").on('click',function () {
    location.href = "user-add.jsp";
})
$("#batch-delete").on('click',function () {
    var arr =  [];
    $('input[name="userCheckBox"]:checked').each(function(){
        arr.push($(this).val());
    });
    if(arr.length < 1){
        alert("please chose one at least!");
        return;
    }
    var data ={
        "userIdList":arr
    }
    batchDel(data);
})
function editUser(userId){
    location.href = "user-edit.jsp?userId="+userId;
}
$('input[name="selectall"]').click(function(){
    if($(this).is(':checked')){
        $('input[name="userCheckBox"]').each(function(){
            $(this).prop("checked",true);
        });
    }else{
        $('input[name="userCheckBox"]').each(function(){
            $(this).removeAttr("checked",false);
        });
    }

});
$("#search-btn").on('click',function () {
    userName = $("#search-account").val();
    pageIndex = 1;
    query(pageIndex);
})
$("#status-list").on('change',function () {
    status = $("#status-list :selected").val();
    if(status == -1){
        status = "";
    }
    pageIndex = 1;
    query(pageIndex);
})
function delUser(userId){
    var data = {
        "userIdList":[userId]
    }
    batchDel(data);
}
function batchDel(data) {
    ajaxUtil.doPostAjax('/user/del',data,
        function (data) {
            if(data.resultCode != '000000'){
                alert(data.resultDesc);
                return;
            }
            if(flag && pageIndex > 1)
            {
                pageIndex = pageIndex - 1;
                flag = false;
            }
            query(pageIndex);
        },
        function (e) {
            alert("network error");
        })
}
function paintData(data) {
    var html = "";
    var userList = data.userVoList;
    var user;
    for (var i = 0; i < userList.length; i++) {
        user = userList[i];
        html = html + "<tr>";
        html = html + "<td><input name='userCheckBox' value=\""+user.userId+"\" type=\"checkbox\" /></td>";
        html = html + "<td>"+(i + 1)+"</td>";
        html = html + "<td><a href=\"#\">" + user.userId + "</a></td>";
        html = html + "<td><a href=\"#\">" + user.userName + "</a></td>";
        html = html + "<td><a href=\"#\">" + user.phone + "</a></td>";
        html = html + "<td>" + (user.locked == '0' ? '正常' : '锁定') + "</td>";
        html = html + "<td><div class=\"am-btn-group am-btn-group-xs\"><div class=\"am-btn-group am-btn-group-xs\">";
        html = html + "<span class=\"am-btn am-btn-default am-btn-xs am-text-secondary\" onclick=\"editUser('"+user.userId+"')\"><span class=\"am-icon-pencil-square-vo\"></span> 编辑</span>";
        html = html + "<span class=\"am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only\" onclick=\"delUser('"+user.userId+"')\"><span class=\"am-icon-trash-vo\"></span> 删除</span>";
        html = html + "</div></div></td>";
        html = html + "</tr>";
    }
    $("#user-list").html(html);
    if($("#user-list tr").length == 1)
    {
        flag = true;
    }
}
function query() {
    var data = {
        "locked":status,
        "userName":userName,
        "pageIndex":pageIndex,
        "pageSize":pageSize
    }
    ajaxUtil.doPostAjax('/user/query',data,
    function (data) {
        paintData(data);
        pagination(data.total,pageIndex);
    },
    function (e) {
        alert("network error!");
    })
}
$(document).ready(function () {
    query();
})