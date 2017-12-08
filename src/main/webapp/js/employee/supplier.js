/**
 * Created by Administrator on 2017/12/8.
 */
var pageIndex = 1;
var supplier = "";
var flag = false;
function saveSuppliser() {
    var supplierId = $("#supplierId").val();
    var lastUpdateTime = $("#lastUpdateTime").val();
    var supplier = $("#supplier").val();
    var phone = $("#phone").val();
    var email = $("#email").val();
    var address = $("#address").val();
    var memo = $("#memo").val();
    var data = {
        "supplierId": supplierId,
        "lastUpdateTime": lastUpdateTime,
        "supplier": supplier,
        "phone": phone,
        "email": email,
        "address": address,
        "memo": memo
    }
    ajaxUtil.doPostAjax('/supplier/updateSupplier', data,
        function (data) {
            if (data.resultCode != '000000') {
                alert(data.resultDesc);
                return;
            }
            query(1);
        },
        function (e) {
            alert("network error!");
        })
}
$("#type-plus").on('click', function () {
    $('#my-prompt').modal({
        relatedTarget: this,
        onConfirm: function (e) {
            var supplier = $("#supplier").val();
            var phone = $("#phone").val();
            var email = $("#email").val();
            var address = $("#address").val();
            var memo = $("#memo").val();
            var data = {
                "supplierVo": {
                    "supplier": supplier,
                    "phone": phone,
                    "email": email,
                    "address": address,
                    "memo": memo
                }
            }
            ajaxUtil.doPostAjax('/supplier/addSupplier', data,
                function (data) {
                    if (data.resultCode != '000000') {
                        alert(data.resultDesc);
                        return;
                    }
                    query(1);
                },
                function (e) {
                    alert("network error!");
                })
        },
        onCancel: function (e) {
        }
    });
})
$("#search-btn").on('click', function () {
    supplier = $("#search-type").val();
    pageIndex = 1;
    query(pageIndex);
})
$("#batch-delete").on('click', function () {
    var arr = [];
    $('input[name="checkBox"]:checked').each(function () {
        arr.push($(this).val());
    });
    if (arr.length < 1) {
        alert("please chose one at least!");
        return;
    }
    var data = {
        "supplierIds": arr
    }
    batchDel(data);
})
function delSupplier(productTypeId) {
    var data = {
        "supplierIds": [productTypeId]
    }
    batchDel(data);
}
function batchDel(data) {
    ajaxUtil.doPostAjax('/supplier/delSupplier', data,
        function (data) {
            if (data.resultCode != '000000') {
                alert(data.resultDesc);
                return;
            }
            if (flag && pageIndex > 1) {
                pageIndex = pageIndex - 1;
                flag = false;
            }
            query(pageIndex);
        },
        function (e) {
            alert("network error");
        })
}
function editSupplier(supplierId) {
    find(supplierId);
}
function find(supplierId) {
    var data = {
        "supplierId": supplierId,
    }
    ajaxUtil.doPostAjax('/supplier/getSupplier', data,
        function (data) {
            if (data.resultCode != '000000') {
                alert(data.resultDesc);
                return;
            }
            var supplierVo = data.supplierVo;
            $("#supplierId").val(supplierVo.supplierId);
            $("#lastUpdateTime").val(supplierVo.lastUpdateTime);
            $("#supplier").val(supplierVo.supplier);
            $("#phone").val(supplierVo.phone);
            $("#email").val(supplierVo.email);
            $("#address").val(supplierVo.address);
            $("#memo").val(supplierVo.memo);
            $('#my-prompt').modal({
                relatedTarget: this,
                onConfirm: function (e) {
                    saveSuppliser();
                },
                onCancel: function (e) {
                }
            });
        },
        function (e) {
            alert("network error!");
        })
}
function paintData(data) {
    var html = "";
    var supplierVos = data.supplierVos;
    if (!!!supplierVos) {
        $("#supplier-list").html(html);
        return;
    }
    var supplier;
    for (var i = 0; i < supplierVos.length; i++) {
        supplier = supplierVos[i];
        html = html + "<tr>";
        html = html + "<td><input name='checkBox' value=\"" + supplier.supplierId + "\" type=\"checkbox\" /></td>";
        html = html + "<td>" + (i + 1) + "</td>";
        html = html + "<td>" + supplier.supplier + "</td>";
        html = html + "<td>" + supplier.phone + "</td>";
        html = html + "<td>" + supplier.email + "</td>";
        html = html + "<td>" + supplier.address + "</td>";
        html = html + "<td>" + supplier.memo + "</td>";
        html = html + "<td>" + supplier.createTime + "</td>";
        html = html + "<td><div class=\"am-btn-group am-btn-group-xs\"><div class=\"am-btn-group am-btn-group-xs\">";
        html = html + "<span class=\"am-btn am-btn-default am-btn-xs am-text-secondary\" onclick=\"editSupplier('" + supplier.supplierId + "')\"><span class=\"am-icon-pencil-square-o\"></span> 编辑</span>";
        html = html + "<span class=\"am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only\" onclick=\"delSupplier('" + supplier.supplierId + "')\"><span class=\"am-icon-trash-o\"></span> 删除</span>";
        html = html + "</div></div></td>";
        html = html + "</tr>";
    }
    $("#supplier-list").html(html);
    if ($("#supplier-list tr").length == 1) {
        flag = true;
    }
}
function query() {
    var data = {
        "supplier": supplier,
        "pageIndex": pageIndex,
        "pageSize": pageSize
    }
    ajaxUtil.doPostAjax('/supplier/querySupplier', data,
        function (data) {
            if (data.resultCode != '000000') {
                alert(data.resultDesc);
                return;
            }
            paintData(data);
            pagination(data.total, pageIndex);
        },
        function (e) {
            alert("network error!");
        })
}

$(document).ready(function () {
    query();
})