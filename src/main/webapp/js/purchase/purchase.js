/**
 * Created by Administrator on 2017/12/8.
 */
var pageIndex = 1;
var startTime = "";
var endTime = "";
var productName ="";
var productId = "";
var employeeId = "";
var productTypeId = "";
var flag = false;

$("#purchase-plus").on('click',function () {
    location.href = "../../purchase-add.jsp";
})
$("#search-btn").on('click',function () {
    productTypeId = $("#type-list :selected").val();
    if(productTypeId == -1){
        productTypeId = "";
    }
    employeeId =  $("#employee-list :selected").val();
    if(employeeId == -1){
        employeeId = "";
    }
    productId = $("#productId").val();
    productName = $("#productName").val();
    startTime = $("#startTime").val().replace(/-/g,"");
    endTime = $("#endTime").val().replace(/-/g,"");
    pageIndex = 1;
    query(pageIndex);
})
$("#batch-delete").on('click',function () {
    var arr =  [];
    $('input[name="checkBox"]:checked').each(function(){
        arr.push($(this).val());
    });
    if(arr.length < 1){
        alert("please chose one at least!");
        return;
    }
    var data ={
        "purchaseIds":arr
    }
    batchDel(data);
})
function delPurchase(purchaseId) {
    var data = {
        "purchaseIds":[purchaseId]
    }
    batchDel(data);
}
function batchDel(data) {
    ajaxUtil.doPostAjax('/purchase/delPurchase',data,
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
function editPurchase(purchaseId) {
    location.href = "purchase-edit.jsp?purchaseId="+purchaseId;
}
function paintTypeList(data){
    var productTypeVos = data.productTypeVos;
    var html = "<option value=\"-1\">所有类型</option>";
    if(null == productTypeVos || productTypeVos.length < 1)
    {
        $("#type-list").html(html);
        return;
    }
    var productType;
    for (var i = 0; i < productTypeVos.length; i++){
        productType = productTypeVos[i];
        html = html + "<option value=\""+productType.productTypeId+"\">"+productType.productTypeName+"</option>";
    }
    $("#type-list").html(html);
}
function initTypeList() {
    ajaxUtil.doPostAjax('/productType/queryProductType',{},
        function (data) {
            if(data.resultCode != '000000'){
                alert(data.resultDesc);
                return;
            }
            paintTypeList(data);
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
function paintData(data) {
    var html = "";
    var purchaseVos = data.purchaseVos;
    if(!!!purchaseVos){
        $("#employee-data").html(html);
        return;
    }
    var purchase;
    for (var i = 0; i < purchaseVos.length; i++) {
        purchase = purchaseVos[i];
        html = html + "<tr>";
        html = html + "<td><input name='checkBox' value=\""+purchase.purchaseId+"\" type=\"checkbox\" /></td>";
        html = html + "<td>"+(i + 1)+"</td>";
        html = html + "<td>" + purchase.productId + "</td>";
        html = html + "<td><a href=\"#\">" + (purchase.productName==null?"":purchase.productName) + "</a></td>";
        html = html + "<td>" + (purchase.productTypeName==null?"":purchase.productTypeName)+ "</td>";
        html = html + "<td>" + purchase.employeeName + "</td>";
        html = html + "<td>" + purchase.price + "</td>";
        html = html + "<td>" + purchase.amount + "</td>";
        html = html + "<td>" + purchase.createTime + "</td>";
        html = html + "<td><div class=\"am-btn-group am-btn-group-xs\"><div class=\"am-btn-group am-btn-group-xs\">";
        html = html + "<span class=\"am-btn am-btn-default am-btn-xs am-text-secondary\" onclick=\"editPurchase('"+purchase.purchaseId+"')\"><span class=\"am-icon-pencil-square-o\"></span> 编辑</span>";
        html = html + "<span class=\"am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only\" onclick=\"delPurchase('"+purchase.purchaseId+"')\"><span class=\"am-icon-trash-o\"></span> 删除</span>";
        html = html + "</div></div></td>";
        html = html + "</tr>";
    }
    $("#employee-data").html(html);
    if($("#employee-data tr").length == 1)
    {
        flag = true;
    }
}
function query() {
    var data = {
        "employeeId":employeeId,
        "productTypeId":productTypeId,
        "minCreateTime":startTime,
        "maxCreateTime":endTime,
        "productName":productName,
        "productId":productId,
        "pageIndex":pageIndex,
        "pageSize":pageSize
    }
    ajaxUtil.doPostAjax('/purchase/queryPurchase',data,
        function (data) {
            if(data.resultCode != '000000'){
                alert(data.resultDesc);
                return;
            }
            paintData(data);
            pagination(data.total,pageIndex);
        },
        function (e) {
            alert("network error!");
        })
}

$(document).ready(function () {
    initTypeList();
    initEmployList();
    query();
})