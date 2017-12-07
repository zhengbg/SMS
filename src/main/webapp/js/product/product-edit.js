/**
 * Created by Administrator on 2017/12/7.
 */
$("#pro-save").on('click', function () {
    saveProduct();
})
$("#pro-cancel").on('click', function () {
    location.href = "../../product.jsp";
})
function saveProduct() {
    var productId = $("#productId").val();
    var productName = $("#productName").val();
    var productTypeId = $("#type-list :selected").val();
    var scale = $("#scale").val();
    var price = $("#price").val();
  /*  var stock = $("#stock").val();*/
    var supplier = $("#supplier").val();
    var memo = $("#memo").val();
    var lastUpdateTime = $("#lastUpdateTime").val();
    if (!!!productId) {
        alert("商品编号不能为空");
        return;
    }
    if (!!!productName) {
        alert("商品名称不能为空");
        return;
    }
    if (productTypeId == -1) {
        alert("请选择商品类型");
        return;
    }
    if (!!!scale) {
        alert("商品规格不能为空");
        return;
    }
    if (!!!price) {
        alert("商品价格不能为空");
        return;
    }
   /* if (!!!stock) {
        alert("商品数量不能为空");
        return;
    }*/
    if (!!!supplier) {
        alert("商品所属供应商不能为空");
        return;
    }
    var data = {
        "productId": productId,
        "productName": productName,
        "productTypeId": productTypeId,
        "productScale": scale,
        "price": price,
        "supplier": supplier,
       /* "stock": stock,*/
        "lastUpdateTime": lastUpdateTime,
        "memo": memo
    }
    ajaxUtil.doPostAjax('/product/updateProduct', data,
        function (data) {
            if (data.resultCode != '000000') {
                alert(data.resultDesc);
                return;
            }
            location.href = "../../product.jsp";
        },
        function (e) {
            alert("network error!");
        })
}
function paintTypeList(data) {
    var productTypeVos = data.productTypeVos;
    var html = "<option value=\"-1\">选择类型</option>";
    if (null == productTypeVos || productTypeVos.length < 1) {
        $("#type-list").html(html);
        return;
    }
    var productType;
    for (var i = 0; i < productTypeVos.length; i++) {
        productType = productTypeVos[i];
        html = html + "<option value=\"" + productType.productTypeId + "\">" + productType.productTypeName + "</option>";
    }
    $("#type-list").html(html);
    find();
}
function initTypeList() {
    ajaxUtil.doPostAjax('/productType/queryProductType', {},
        function (data) {
            if (data.resultCode != '000000') {
                alert(data.resultDesc);
                return;
            }
            paintTypeList(data);
        },
        function (e) {
            alert("network error!");
        })
}
function find() {
    var productId = UrlParam("productId");
    var data = {
        "productId": productId
    }
    ajaxUtil.doPostAjax('/product/getProduct', data,
        function (data) {
            if (data.resultCode != '000000') {
                alert(data.resultDesc);
                return;
            }
            var productVo = data.productVo;
            $("#productId").val(productVo.productId);
            $("#productName").val(productVo.productName);
            $("#type-list").val(productVo.productTypeId);
            $("#type-list").trigger('changed.selected.amui');
            $("#scale").val(productVo.productScale);
            $("#price").val(productVo.price);
            $("#stock").val(productVo.stock);
            $("#supplier").val(productVo.supplier);
            $("#lastUpdateTime").val(productVo.lastUpdateTime)
            $("#memo").val(productVo.memo);
        },
        function (e) {
            alert("network error!");
        })
}

$(document).ready(function () {
    initTypeList();
})