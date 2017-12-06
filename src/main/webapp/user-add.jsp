<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
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
        <strong>SMS For Dev</strong> <small>小型超市后台管理系统</small>
    </div>

    <button class="am-topbar-btn am-topbar-toggle am-btn am-btn-sm am-btn-success am-show-sm-only" data-am-collapse="{target: '#topbar-collapse'}"><span class="am-sr-only">导航切换</span> <span class="am-icon-bars"></span></button>

    <div class="am-collapse am-topbar-collapse" id="topbar-collapse">

        <ul class="am-nav am-nav-pills am-topbar-nav am-topbar-right admin-header-list">
            <li><a href="javascript:;"><span class="am-icon-envelope-o"></span> 收件箱 <span class="am-badge am-badge-warning">5</span></a></li>
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
            <li class="am-hide-sm-only"><a href="javascript:;" id="admin-fullscreen"><span class="am-icon-arrows-alt"></span> <span class="admin-fullText">开启全屏</span></a></li>
        </ul>
    </div>
</header>
<div class="am-cf admin-main">
    <!-- sidebar start -->
    <div class="admin-sidebar am-offcanvas" id="admin-offcanvas" style="font-size: 14px;">
        <div class="am-offcanvas-bar admin-offcanvas-bar">
            <ul class="am-list admin-sidebar-list">
                <li class="admin-parent">
                    <a class="am-cf" data-am-collapse="{target: '#security-nav'}"><span class="am-icon-file"></span> 安全管理<span class="am-icon-angle-right am-fr am-margin-right"></span></a>
                    <ul class="am-list am-collapse admin-sidebar-sub am-in" id="security-nav">
                        <li><a href="user.jsp" class="am-cf"><span class="am-icon-check"></span> 用户管理<span class="am-icon-star am-fr am-margin-right admin-icon-yellow"></span></a></li>
                    </ul>
                </li>
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
                <div class="am-fl am-cf">
                    <strong class="am-text-primary am-text-lg">表单</strong> /
                    <small>form</small>
                </div>
            </div>

            <hr>

            <div class="am-tabs am-margin" data-am-tabs>
                <ul class="am-tabs-nav am-nav am-nav-tabs">
                    <li class="am-active"><a href="#tab1">基本信息</a></li>
                </ul>

                <div class="am-tabs-bd">
                    <div class="am-tab-panel am-fade am-in am-active" id="tab1">
                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                用户帐号
                            </div>
                            <div class="am-u-sm-8 am-u-md-4" style = "float:left;">
                                <input type="text" id="userId" class="am-input-sm" style = "width:200px;height:32px;"> *必填
                            </div>
                        </div>
                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                用户密码
                            </div>
                            <div class="am-u-sm-8 am-u-md-4" style = "float:left;">
                                <input type="password" id="password" placeholder="默认123456" class="am-input-sm" style = "width:200px;height:32px;">
                            </div>
                        </div>
                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">用户类型</div>
                            <div class="am-u-sm-8 am-u-md-10">
                                <select id = "type-list" data-am-selected="{maxHeight: 100}">
                                    <option value="0">管理员</option>
                                    <option value="1">收银员</option>
                                    <option value="2">采购员</option>
                                </select>
                            </div>
                        </div>

                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                姓名
                            </div>
                            <div class="am-u-sm-8 am-u-md-4" style = "float:left;">
                                <input type="text" id="username" class="am-input-sm" style = "width:200px;height:32px;">
                            </div>

                        </div>
                        <div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                联系号码
                            </div>
                            <div class="am-u-sm-8 am-u-md-4" style = "float:left;">
                                <input type="text" id="phone" class="am-input-sm" style = "width:200px;height:32px;">
                            </div>

                        </div>

                        <%--<div class="am-g am-margin-top">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                邮箱地址
                            </div>
                            <div class="am-u-sm-8 am-u-md-4" style = "float:left;">
                                <input type="text" id="email" class="am-input-sm" style = "width:200px;height:32px;"> *必填
                            </div>
                        </div>
--%>
                        <%--<div class="am-g am-margin-top-sm">
                            <div class="am-u-sm-4 am-u-md-2 am-text-right">
                                新增描述
                            </div>
                            <div class="am-u-sm-12 am-u-md-10">
                                <textarea rows="5" id="desc" cols="30" placeholder="请输入..."></textarea>
                            </div>
                        </div>--%>
                    </div>
                </div>
            </div>

            <div class="am-margin">
                <button type="button" id="user-save" class="am-btn am-btn-primary am-btn-xs">保存</button>
                <button type="button" id="user-cancel" class="am-btn am-btn-primary am-btn-xs">取消</button>
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
<script src="js/user/user-add.js"></script>
</body>
</html>
