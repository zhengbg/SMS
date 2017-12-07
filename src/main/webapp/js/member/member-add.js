/**
 * Created by Administrator on 2017/12/7.
 */
$("#member-save").on('click',function () {
    addMember();
})
$("#member-cancel").on('click',function () {
    location.href = "../../member.jsp";
})

function addMember() {
    var memberId = $("#memberId").val();
    var memberName = $("#memberName").val();
    var idCard = $("#idCard").val();
    var phone = $("#phone").val();
    var address = $("#address").val();
    var balance = $("#balance").val();
    var data = {
        "memberVo":{
            "memberId":memberId,
            "memberName":memberName,
            "idCard":idCard,
            "phone":phone,
            "balance":balance,
            "address":address
        }
    }
    ajaxUtil.doPostAjax('/member/addMember',data,
    function (data) {
        if(data.resultCode != '000000'){
            alert(data.resultDesc);
            return;
        }
        location.href = "../../member.jsp";
    },
    function (e) {
        alert("network error!");
    })
}