/**
 * Created by Administrator on 2017/12/6.
 */
$("#user-save").on('click',function () {
    saveUser();
})
$("#user-cancel").on('click',function () {
    location.href = "user.jsp";
})
function find() {
    var userId = UrlParam("userId");
    var data = {
        "userId":userId
    }
    ajaxUtil.doPostAjax('/user/find',data,
    function (data) {
        if(data.resultCode != '000000'){
            alert(data.resultDesc);
            return;
        }
        var user = data.userVo;
        $("#userId").val(user.userId);
        $("#phone").val(user.phone);
        $("#username").val(user.userName);
        $("#type-list").val(user.type);
        $("#type-list").trigger('changed.selected.amui');
        $("#lastUpdateTime").val(user.lastUpdateTime);
        if(user.locked == '0')
        {
            $("#normal-label").addClass('am-active');
            $("#option1").attr('checked',true);
        }
        else
        {
            $("#lock-label").addClass('am-active');
            $("#option2").attr('checked',true);
        }

    },
    function (e) {
        alert("network error!")
    })
}
function saveUser() {
    var locked;
    var check = $("input[name='options']:checked").attr('id');
    if('option1' == check){
        locked = "0";
    }else{
        locked = "1";
    }
    var account = $("#userId").val();
    var userName = $("#username").val();
    var password = $("#password").val();
    var type = $("#type-list :selected").val();
    var phone = $("#phone").val();
    var lastUpdateTime = $("#lastUpdateTime").val();
    var data = {
        "userId":account,
        "userName":userName,
        "password":password,
        "type":type,
        "locked":locked,
        "phone":phone,
        "lastUpdateTime":lastUpdateTime
    }
    ajaxUtil.doPostAjax('/user/modify',data,
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
$(document).ready(function () {
    find();
})