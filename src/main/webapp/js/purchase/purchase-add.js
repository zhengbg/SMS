/**
 * Created by Administrator on 2017/12/8.
 */

$("#purchase-save").on('click',function () {
    addPurchase();
})
$("#purchase-cancel").on('click',function () {
    location.href = "../../purchase.jsp";
})

function addPurchase() {
    var productId = $("#productId").val();
    var employeeId = $("#employee-list :selected").val();
    var price = $("#price").val();
    var amount = $("#amount").val();
    var memo = $("#memo").val();
    if(!!!productId){
        alert("商品编号不能为空");
        return;
    }
    if(employeeId == -1){
        alert("请选择采购员");
        return;
    }
    if(!!!price){
        alert("商品价格不能为空");
        return;
    }
    if(!!!amount){
        alert("商品数量不能为空");
        return;
    }
    var data = {
        "purchaseVo":{
            "productId":productId,
            "employeeId":employeeId,
            "price":price,
            "amount":amount,
            "memo":memo
        }
    }
    ajaxUtil.doPostAjax('/purchase/addPurchase',data,
        function (data) {
            if(data.resultCode != '000000'){
                alert(data.resultDesc);
                return;
            }
            location.href = "../../purchase.jsp";
        },
        function (e) {
            alert("network error!");
        })
}
function initEmployList() {
    var data = {
        "type":"2"
    }
    ajaxUtil.doPostAjax('/user/query',data,
        function (data) {
            if(data.resultCode != '000000'){
                alert(data.resultDesc);
                return;
            }
            var userVoList = data.userVoList;
            var html = "<option value=\"-1\">所有员工</option>";
            if(null == userVoList || userVoList.length < 1)
            {
                $("#employee-list").html(html);
                return;
            }
            var user;
            for (var i = 0; i < userVoList.length; i++){
                user = userVoList[i];
                html = html + "<option value=\""+user.userId+"\">"+user.userName+"</option>";
            }
            $("#employee-list").html(html);
        },
        function (e) {
            alert("network error!");
        })
}
$(document).ready(function () {
    initEmployList();
})