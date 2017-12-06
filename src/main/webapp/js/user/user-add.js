/**
 * Created by Administrator on 2017/12/6.
 */
$("#user-save").on('click',function () {
    addUser();
})
$("#user-cancel").on('click',function () {
    location.href = "user.jsp";
})
function addUser() {
    var account = $("#userId").val();
    var userName = $("#username").val();
    var password = $("#password").val();
    var type = $("#type-list :selected").val();
    var phone = $("#phone").val();
    var data = {
        "userId":account,
        "userName":userName,
        "password":password,
        "type":type,
        "phone":phone
    }
    ajaxUtil.doPostAjax('/user/add',data,
    function (data) {
        if(data.resultCode != '000000'){
            alert(data.resultDesc);
            return;
        }
        location.href = "user.jsp";
    },
    function (e) {
        alert("network error!")
    })
}