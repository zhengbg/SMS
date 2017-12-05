/**
 * Created by Administrator on 2017/11/21.
 */
var pageSize = 3;
var ajaxUtil =
{
    doPostAjax: function (url, data, callSuccess, callError) {
        $.ajax({
            type: 'POST',
            url: url,
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                callSuccess(data);
            },
            error: function (e) {
                callError(e);
            }
        });
    },
    doGetAjax: function (url, callSuccess, callError) {
        $.ajax({
            type: 'GET',
            url: url,
            success: function (data) {
                callSuccess(data);
            },
            error: function (e) {
                callError(e);
            }
        });
    }
    /*
    doAjax: function (type, url, data, callSuccess, callError) {
        $.ajax({
            type: type,
            url: url,
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                callSuccess(data);
            },
            error: function (e) {
                callError(e);
            }
        });
    },
    doAjax: function (type, url, data, callSuccess, callError, sync) {
        $.ajax({
            type: type,
            url: url,
            data: JSON.stringify(data),
            async: sync,
            contentType: "application/json",
            dataType: "json",
            success: function (data) {
                callSuccess(data);
            },
            error: function (e) {
                callError(e);
            }
        });
    }*/
}


function UrlParam(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}

function pagination(total,nowPage) {
    var totalPage = parseInt((total + pageSize - 1) / pageSize);
    $("#page").paging({
        pageNo:nowPage,
        totalPage: totalPage,
        totalSize: total,
        callback: function(num) {
            pageIndex = num;
            queryAll(pageIndex);
        }
    })
}
function removeByValue(arr, val) {
    for(var i=0; i<arr.length; i++) {
        if(arr[i] == val) {
            arr.splice(i, 1);
            break;
        }
    }
}
