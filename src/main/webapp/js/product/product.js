/**
 * Created by Administrator on 2017/12/7.
 */
var pageIndex = 1;
var startTime = "";
var endTime = "";
var productName ="";
var productId = "";
var productTypeId = "";
var flag = false;
$("#pro-plus").on('click',function () {
    location.href = "../../product-add.jsp";
})

$("#search-btn").on('click',function () {
    productTypeId = $("#type-list :selected").val();
    if(productTypeId == -1){
        productTypeId = "";
    }
    productId = $("#productId").val();
    productName = $("#productName").val();
    startTime = $("#startTime").val().replace(/-/g,"");
    endTime = $("#endTime").val().replace(/-/g,"");
    pageIndex = 1;
    query(pageIndex);
})
$('input[name="selectall"]').click(function(){
    if($(this).is(':checked')){
        $('input[name="productCheckBox"]').each(function(){
            $(this).prop("checked",true);
        });
    }else{
        $('input[name="productCheckBox"]').each(function(){
            $(this).removeAttr("checked",false);
        });
    }

});
$("#batch-delete").on('click',function () {
    var arr =  [];
    $('input[name="productCheckBox"]:checked').each(function(){
        arr.push($(this).val());
    });
    if(arr.length < 1){
        alert("please chose one at least!");
        return;
    }
    var data ={
        "productIds":arr
    }
    batchDel(data);
})
function delProduct(productId) {
    var data = {
        "productIds":[productId]
    }
    batchDel(data);
}
function batchDel(data) {
    ajaxUtil.doPostAjax('/product/delProduct',data,
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
function editProduct(productId) {
    location.href = "product-edit.jsp?productId="+productId;
}
function paintData(data) {
    var html = "";
    var productVos = data.productVos;
    if(!!!productVos){
        $("#product-list").html(html);
        return;
    }
    var product;
    for (var i = 0; i < productVos.length; i++) {
        product = productVos[i];
        html = html + "<tr>";
        html = html + "<td><input name='productCheckBox' value=\""+product.productId+"\" type=\"checkbox\" /></td>";
        html = html + "<td>"+(i + 1)+"</td>";
        html = html + "<td>" + product.productId + "</td>";
        html = html + "<td><a href=\"#\">" + product.productName + "</a></td>";
        html = html + "<td>" + product.productTypeName + "</td>";
        html = html + "<td>" + product.productScale + "</td>";
        html = html + "<td>" + product.price + "</td>";
        html = html + "<td>" + product.stock + "</td>";
        html = html + "<td>" + product.supplier + "</td>";
        html = html + "<td>" + product.createTime + "</td>";
        html = html + "<td><div class=\"am-btn-group am-btn-group-xs\"><div class=\"am-btn-group am-btn-group-xs\">";
        html = html + "<span class=\"am-btn am-btn-default am-btn-xs am-text-secondary\" onclick=\"editProduct('"+product.productId+"')\"><span class=\"am-icon-pencil-square-vo\"></span> 编辑</span>";
        html = html + "<span class=\"am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only\" onclick=\"delProduct('"+product.productId+"')\"><span class=\"am-icon-trash-vo\"></span> 删除</span>";
        html = html + "</div></div></td>";
        html = html + "</tr>";
    }
    $("#product-list").html(html);
    if($("#product-list tr").length == 1)
    {
        flag = true;
    }
}
function query() {
    var data = {
        "productTypeId":productTypeId,
        "minCreateTime":startTime,
        "maxCreateTime":endTime,
        "productName":productName,
        "productId":productId,
        "pageIndex":pageIndex,
        "pageSize":pageSize
    }
    ajaxUtil.doPostAjax('/product/queryProduct',data,
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
$(document).ready(function () {
    initTypeList();
    query();
})