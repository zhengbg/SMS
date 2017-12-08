/**
 * Created by Administrator on 2017/12/7.
 */
$("#pro-save").on('click',function () {
    addProduct();
})
$("#pro-cancel").on('click',function () {
    location.href = "../../product.jsp";
})

function addProduct() {
    var productId = $("#productId").val();
    var productName = $("#productName").val();
    var productTypeId = $("#type-list :selected").val();
    var scale = $("#scale").val();
    var price = $("#price").val();
    var stock = $("#stock").val();
    var supplierId = $("#supplier-list :selected").val();
    var memo = $("#memo").val();
    if(!!!productId){
        alert("商品编号不能为空");
        return;
    }
    if(!!!productName){
        alert("商品名称不能为空");
        return;
    }
    if(productTypeId == -1){
        alert("请选择商品类型");
        return;
    }
    if(!!!scale){
        alert("商品规格不能为空");
        return;
    }
    if(!!!price){
        alert("商品价格不能为空");
        return;
    }
    if(!!!stock){
        alert("商品数量不能为空");
        return;
    }
    if(supplierId == -1){
        alert("请选择供应商");
        return;
    }
    var data = {
        "productVo":{
            "productId":productId,
            "productName":productName,
            "productTypeId":productTypeId,
            "productScale":scale,
            "price":price,
            "supplierId":supplierId,
            "stock":stock,
            "memo":memo
        }
    }
    ajaxUtil.doPostAjax('/product/addProduct',data,
    function (data) {
        if(data.resultCode != '000000'){
            alert(data.resultDesc);
            return;
        }
        location.href = "../../product.jsp";
    },
    function (e) {
        alert("network error!");
    })
}
function paintTypeList(data){
    var productTypeVos = data.productTypeVos;
    var html = "<option value=\"-1\">选择类型</option>";
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
function initSupplierList(){
    ajaxUtil.doPostAjax('/supplier/querySupplier',{},
        function (data) {
            if(data.resultCode != '000000'){
                alert(data.resultDesc);
                return;
            }
            var supplierVos = data.supplierVos;
            var html = "<option value=\"-1\">所有供应商</option>";
            if(null == supplierVos || supplierVos.length < 1)
            {
                $("#supplier-list").html(html);
                return;
            }
            var supplierVo;
            for (var i = 0; i < supplierVos.length; i++){
                supplierVo = supplierVos[i];
                html = html + "<option value=\""+supplierVo.supplierId+"\">"+supplierVo.supplier+"</option>";
            }
            $("#supplier-list").html(html);
        },
        function (e) {
            alert("network error!");
        })
}

$(document).ready(function () {
    initTypeList();
    initSupplierList();
})