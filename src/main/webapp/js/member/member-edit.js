/**
 * Created by Administrator on 2017/12/7.
 */
$("#member-save").on('click', function () {
    saveMember();
})
$("#member-cancel").on('click', function () {
    location.href = "../../member.jsp";
})

function saveMember() {
    var lastUpdateTime = $("#lastUpdateTime").val();
    var memberId = $("#memberId").val();
    var memberName = $("#memberName").val();
    /*var idCard = $("#idCard").val();*/
    var phone = $("#phone").val();
    var address = $("#address").val();
    var balance = $("#balance").val();
    var data = {
        "memberId": memberId,
        "memberName": memberName,
        /*"idCard":idCard,*/
        "phone": phone,
        "balance": balance,
        "lastUpdateTime": lastUpdateTime,
        "address": address

    }
    ajaxUtil.doPostAjax('/member/updateMember', data,
        function (data) {
            if (data.resultCode != '000000') {
                alert(data.resultDesc);
                return;
            }
            location.href = "../../member.jsp";
        },
        function (e) {
            alert("network error!");
        })
}

function find() {
    var memberId = UrlParam("memberId");
    var data = {
        "memberId": memberId
    }
    ajaxUtil.doPostAjax('/member/queryMemberById', data,
        function (data) {
            if (data.resultCode != '000000') {
                alert(data.resultDesc);
                return;
            }
            var member = data.memberVo;
            $("#memberId").val(member.memberId);
            $("#memberName").val(member.memberName);
            $("#idCard").val(member.idCard);
            $("#phone").val(member.phone);
            $("#address").val(member.address);
            $("#balance").val(member.balance);
            $("#lastUpdateTime").val(member.lastUpdateTime);
        },
        function (e) {
            alert("network error!");
        })
}
$(document).ready(function () {
    find();
})