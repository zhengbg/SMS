<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="/i/app-icon72x72@2x.png">
    <link rel="stylesheet" href="css/amazeui.min.css"/>
    <link rel="stylesheet" href="css/admin.css">
    <link rel="stylesheet" href="css/page.css">
</head>
<body>
<p class="browsehappy">你正在使用<strong>过时</strong>的浏览器，Amaze UI 暂不支持。 请 <a href="http://browsehappy.com/" target="_blank">升级浏览器</a>
    以获得更好的体验！</p>

<header class="am-topbar am-topbar-inverse admin-header">
    <div class="am-topbar-brand">
        <strong>SMS For Dev</strong>
        <small>小型超市后台管理系统</small>
    </div>

    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only"
            data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span
            class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span
                    class="am-badge am-badge-warning">5</span></a></li>
            <li class="am-dropdown" data-am-dropdown>
                <a class="am-dropdown-toggle" data-am-dropdown-toggle href="javascript:;">
                    <span class="am-icon-users"></span> 管理员 <span class="am-icon-caret-down"></span>
                </a>
                <ul class="am-dropdown-content">
                    <li><a href="#"><span class="am-icon-user"></span> 资料</a></li>
                    <li><a href="#"><span class="am-icon-cog"></span> 设置</a></li>
                    <li><a href="/logout"><span class="am-icon-power-off"></span> 退出</a></li>
                </ul>
            </li>
            <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span
                    class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
        </ul>
    </div>
</header>
<div class="am-cf admin-main">
    <!-- sidebar start -->
    <div class="admin-sidebar am-offcanvas" id="admin-offcanvas" style="font-size: 14px;">
        <div class="am-offcanvas-bar admin-offcanvas-bar">
            <ul class="am-list admin-sidebar-list">
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#person-nav'}"><span class="am-icon-users am-icon-fw"></span> 人事管理<span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="person-nav">
                        <li><a href="employee.jsp" class="am-cf"><span class="am-icon-male am-icon-fw"></span> 员工管理</a></li>
                        <li><a href="member.jsp" class="am-cf"><span class="am-icon-flag am-icon-fw"></span> 会员管理</a></li>
                        <li><a href="supplier.jsp" class="am-cf"><span class="am-icon-user am-icon-fw"></span> 供应商管理</a></li>
                    </ul>
                </li>
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#product-nav'}"><span class="am-icon-university am-icon-fw"></span> 商品管理<span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="product-nav">
                        <li><a href="product.jsp" class="am-cf"><span class="am-icon-tasks am-icon-fw"></span> 库存管理</a></li>
                        <li><a href="productType.jsp" class="am-cf"><span class="am-icon-sort am-icon-fw"></span> 分类管理</a></li>
                    </ul>
                </li>
                <li><a href="purchase.jsp"><span class="am-icon-shopping-basket am-icon-fw"></span>采购管理</a></li>
                <li><a href="#"><span class="am-icon-sellsy am-icon-fw"></span>销售管理</a></li>
            </ul>

            <div class="am-panel am-panel-default admin-sidebar-panel">
                <div class="am-panel-bd">
                    <p><span class="am-icon-bookmark"></span> 致语</p>
                    <p>时光静好，与君语；细水流年，与君同。—— Admin</p>
                </div>
            </div>

        </div>
    </div>
    <!-- sidebar end

    <!-- content start -->
    <div class="admin-content">
        <div class="admin-content-body">
            <div class="am-cf am-padding am-padding-bottom-0">
                <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">采购管理</strong>
                    <small></small>
                </div>
            </div>

            <hr>

            <div class="am-g">
                <div class="am-u-sm-12 am-u-md-3" style="width: 30%;height: 50px;">
                    <div class="am-form-group">
                        <label for="type-list" class="am-form-label search-label">商品类型:&nbsp;&nbsp; </label>
                        <select id="type-list" data-am-selected="{btnSize: 'sm'}">
                        </select>
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-3" style="width: 30%;height: 50px;">
                    <div class="am-form-group">
                        <label for="employee-list" class="am-form-label search-label">采购员:&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; </label>
                        <select id="employee-list" data-am-selected="{btnSize: 'sm'}">
                        </select>
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-3" style="width: 30%;">
                    <div class="am-form-group">
                        <label for="productId" class="am-form-label search-label" style="float: left;">商品编号: &nbsp;&nbsp;</label>
                        <input type="text" id="productId" class="am-form-field search-input">
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-3" style="width: 30%;">
                    <div class="am-form-group">
                        <label for="productName" class="am-form-label search-label" style="float: left;">商品名称:&nbsp;&nbsp;&nbsp;  </label>
                        <input type="text" id="productName" class="am-form-field search-input">
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-3" style="width: 30%;">
                    <div class="am-form-group">
                        <label for="supplier" class="am-form-label search-label" style="float: left;">供应商:&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;</label>
                        <input type="text" id="supplier" class="am-form-field search-input">
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-3" style="width: 30%;">
                    <div class="am-form-group">
                        <label for="startTime" class="am-form-label search-label" style="float: left;">开始日期: &nbsp;&nbsp;</label>
                        <form action="" class="am-form am-form-inline">
                            <div class="am-form-group am-form-icon" style="width: 200px;">
                                <i class="am-icon-calendar"></i>
                                <input type="date" id="startTime" class="am-form-field am-input-sm search-input"
                                       placeholder="日期">
                            </div>
                        </form>
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-3" style="width: 30%;">
                    <div class="am-form-group">
                        <label for="endTime" class="am-form-label search-label" style="float: left;">结束日期:&nbsp;&nbsp;&nbsp; </label>
                        <form action="" class="am-form am-form-inline">
                            <div class="am-form-group am-form-icon" style="width: 200px;">
                                <i class="am-icon-calendar"></i>
                                <input type="date" id="endTime" class="am-form-field am-input-sm search-input"
                                       placeholder="日期">
                            </div>
                        </form>
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-3" style="width: 70%;">
                    <div class="am-form-group">
                        <button class="am-btn am-btn-default am-btn-sm" id="search-btn" type="button"><span class="am-icon-search"></span>搜索
                        </button>
                    </div>
                </div>




            </div>

            <div class="am-g">
                <div class="am-u-sm-12 am-u-md-6">
                    <div class="am-btn-toolbar">
                        <div class="am-btn-group am-btn-group-xs">
                            <button type="button" id="purchase-plus" class="am-btn am-btn-default"><span
                                    class="am-icon-plus"></span> 新增
                            </button>
                            <button type="button" id="batch-delete" class="am-btn am-btn-dan"><span
                                    class="am-icon-trash-o"></span> 删除
                            </button>
                        </div>
                    </div>
                </div>
                <div class="am-u-sm-12">
                    <form class="am-form">
                        <table class="am-table am-table-striped am-table-hover table-main">
                            <thead>
                            <tr>
                                <th class="table-check"><input name="selectall" type="checkbox"/></th>
                                <th>ID</th>
                                <th>商品编号</th>
                                <th>商品名称</th>
                                <th>商品类型</th>
                                <th>采购员</th>
                                <th>单价</th>
                                <th>数量</th>
                                <th>创建时间</th>
                                <th class="table-set">操作</th>
                            </tr>
                            </thead>
                            <tbody id="employee-data">

                            </tbody>
                        </table>
                        <div class="am-cf">
                            <div class="am-fr">
                                <div value="1 0"></div>
                                <div id="page" class="page_div"></div>
                            </div>
                        </div>
                        <hr/>
                    </form>
                </div>

            </div>
        </div>

        <footer class="admin-content-footer">
            <hr>
            <p class="am-padding-left">© 2017 AMS For Development.Licensed under QT license.</p>
        </footer>

    </div>
    <!-- content end -->
</div>
<script src="js/jquery.min.js"></script>
<script src="js/amazeui.min.js"></script>
<script src="js/app.js"></script>
<script src="js/page.js"></script>
<script src="js/common.js"></script>
<script src="js/purchase/purchase.js"></script>
</body>
</html>
