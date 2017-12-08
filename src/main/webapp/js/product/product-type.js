/**
 * Created by Administrator on 2017/12/8.
 */
var pageIndex = 1;
var productTypeName = "";
var flag = false;
$("#type-plus").on('click',function () {
    $('#my-prompt').modal({
        relatedTarget: this,
        onConfirm: function(e) {
            var productTypeName = $("#productTypeName").val();
            var memo = $("#memo").val();
            var data = {
                "productTypeVo":{
                    "productTypeName":productTypeName,
                    "memo":memo
                }
            }
            ajaxUtil.doPostAjax('/productType/addProductType',data,
            function (data) {
                if(data.resultCode != '000000'){
                    alert(data.resultDesc);
                    return;
                }
                query(1);   
            },
            function (e) {
                alert("network error!");
            })
        },
        onCancel: function(e) {
        }
    });
})
$("#search-btn").on('click',function () {
    productTypeName = $("#search-type").val();
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
        "productTypeIds":arr
    }
    batchDel(data);
})
function delProductType(productTypeId) {
    var data = {
        "productTypeIds":[productTypeId]
    }
    batchDel(data);
}
function batchDel(data) {
    ajaxUtil.doPostAjax('/productType/delProductType',data,
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
function paintData(data){
    var html = "";
    var productTypeVos = data.productTypeVos;
    if(!!!productTypeVos){
        $("#product-type-list").html(html);
        return;
    }
    var productType;
    for (var i = 0; i < productTypeVos.length; i++) {
        productType = productTypeVos[i];
        html = html + "<tr>";
        html = html + "<td><input name='checkBox' value=\""+productType.productTypeId+"\" type=\"checkbox\" /></td>";
        html = html + "<td>"+(i + 1)+"</td>";
        html = html + "<td>" + productType.productTypeName + "</td>";
        html = html + "<td>" + (productType.memo==null?"无": productType.memo)+ "</td>";
        html = html + "<td>" + productType.createTime + "</td>";
        html = html + "<td><div class=\"am-btn-group am-btn-group-xs\"><div class=\"am-btn-group am-btn-group-xs\">";
        html = html + "<a style='color:#5c5c5c' onclick=\"delProductType('"+productType.productTypeId+"')\"><i class=\"am-icon-close\"></i></a>";
        html = html + "</div></div></td>";
        html = html + "</tr>";
    }
    $("#product-type-list").html(html);
    if($("#product-type-list tr").length == 1)
    {
        flag = true;
    }
}
function query() {
    var data = {
        "productTypeName":productTypeName,
        "pageIndex":pageIndex,
        "pageSize":pageSize
    }
    ajaxUtil.doPostAjax('/productType/queryProductType',data,
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
    query();
})