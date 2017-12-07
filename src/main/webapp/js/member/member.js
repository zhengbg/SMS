/**
 * Created by Administrator on 2017/12/7.
 */
var pageIndex = 1;
var flag = "";
$("#member-plus").on('click',function () {
    location.href = "../../member-add.jsp";
})
function delMember() {
    
}
function paintData(data){
    var html = "";
    var memberVos = data.memberVos;
    var member;
    for (var i = 0; i < memberVos.length; i++) {
        member = memberVos[i];
        html = html + "<tr>";
        html = html + "<td><input name='userCheckBox' value=\""+member.memberId+"\" type=\"checkbox\" /></td>";
        html = html + "<td>"+(i + 1)+"</td>";
        html = html + "<td><a href=\"#\">" + member.memberId + "</a></td>";
        html = html + "<td>" + member.memberName + "</td>";
        html = html + "<td>" + member.idCard + "</td>";
        html = html + "<td>" + member.phone + "</td>";
        html = html + "<td>" + member.address + "</td>";
        html = html + "<td>" + member.balance + "</td>";
        html = html + "<td><div class=\"am-btn-group am-btn-group-xs\"><div class=\"am-btn-group am-btn-group-xs\">";
        html = html + "<span class=\"am-btn am-btn-default am-btn-xs am-text-secondary\" onclick=\"editUser('"+member.memberId+"')\"><span class=\"am-icon-pencil-square-vo\"></span> 编辑</span>";
        html = html + "<span class=\"am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only\" onclick=\"delMember('"+member.memberId+"')\"><span class=\"am-icon-trash-o\"></span> 删除</span>";
        html = html + "</div></div></td>";
        html = html + "</tr>";
    }
    $("#member-list").html(html);
    if($("#member-list tr").length == 1)
    {
        flag = true;
    }
}
function query() {
    var data = {
        "pageIndex":pageIndex,
        "pageSize":pageSize
    }
    ajaxUtil.doPostAjax('/member/queryMember',data,
    function (data) {
        if(data.resultCode != '000000'){
            alert(data.resultDesc);
            return;
        }
        paintData(data);
        pagination(data.total,pageIndex);
    },
    function (e) {
        alert("network error!")
    })
}

$(document).ready(function () {
    query();
})