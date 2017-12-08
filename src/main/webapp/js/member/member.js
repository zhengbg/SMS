/**
 * Created by Administrator on 2017/12/7.
 */
var pageIndex = 1;
var flag = false;
var memberName = "";
$('input[name="selectall"]').click(function(){
    if($(this).is(':checked')){
        $('input[name="memberCheckBox"]').each(function(){
            $(this).prop("checked",true);
        });
    }else{
        $('input[name="memberCheckBox"]').each(function(){
            $(this).removeAttr("checked",false);
        });
    }

});
$("#search-btn").on('click',function () {
    memberName = $("#search-name").val();
    pageIndex = 1;
    query(pageIndex);
})
$("#member-plus").on('click',function () {
    location.href = "../../member-add.jsp";
})
$("#batch-delete").on('click',function () {
    var arr =  [];
    $('input[name="memberCheckBox"]:checked').each(function(){
        arr.push($(this).val());
    });
    if(arr.length < 1){
        alert("please chose one at least!");
        return;
    }
    var data ={
        "memberIds":arr
    }
    batchDel(data);
})
function delMember(memberId) {
    var data = {
        "memberIds":[memberId]
    }
    batchDel(data);
}
function batchDel(data) {
    ajaxUtil.doPostAjax('/member/delMember',data,
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
function editMember(memberId) {
    location.href = "../../member-edit.jsp?memberId="+memberId;
}
function paintData(data){
    var html = "";
    var memberVos = data.memberVos;
    if(!!!memberVos)
    {
        $("#member-list").html(html);
        return;
    }
    var member;
    for (var i = 0; i < memberVos.length; i++) {
        member = memberVos[i];
        html = html + "<tr>";
        html = html + "<td><input name='memberCheckBox' value=\""+member.memberId+"\" type=\"checkbox\" /></td>";
        html = html + "<td>"+(i + 1)+"</td>";
        html = html + "<td><a href=\"#\">" + member.memberId + "</a></td>";
        html = html + "<td>" + member.memberName + "</td>";
        html = html + "<td>" + member.idCard + "</td>";
        html = html + "<td>" + member.phone + "</td>";
        html = html + "<td>" + member.address + "</td>";
        html = html + "<td>" + member.balance + "</td>";
        html = html + "<td><div class=\"am-btn-group am-btn-group-xs\"><div class=\"am-btn-group am-btn-group-xs\">";
        html = html + "<span class=\"am-btn am-btn-default am-btn-xs am-text-secondary\" onclick=\"editMember('"+member.memberId+"')\"><span class=\"am-icon-pencil-square-o\"></span> 编辑</span>";
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
        "memberName":memberName,
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