<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="format-detection" content="telephone=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="alternate icon" type="image/png" href="i/favicon.png">
    <link rel="stylesheet" href="css/amazeui.min.css"/>
    <link rel="stylesheet" href="css/admin.css"/>
</head>
<body>
<div class="header">
    <div class="am-g">
        <h1>Web Side</h1>
    </div>
    <hr />
</div>
<div class="am-g">
    <div class="am-u-lg-6 am-u-md-8 am-u-sm-centered">
        <div style="font-size: 200%;">LOGIN</div>
        <hr>
        <br>
        <br>

        <div class="am-form">
            <label for="username">用户名:</label>
            <input type="text" name="" id="username" value="">
            <br>
            <label for="password">密码:</label>
            <input type="password" name="" id="password" value="">
            <br>
            <label for="remember-me">
                <input id="remember-me" type="checkbox">
                记住密码
            </label>
            <br />
            <div class="am-cf">
                <button type="button" name="" class="am-btn am-btn-primary am-btn-sm am-fl login">登录</button>
            </div>
        </div>
        <hr>
        <p>© 2017 AMS For Development.Licensed under QT license.</p>
    </div>
</div>
<script src="js/jquery.min.js"></script>
<script src="js/amazeui.min.js"></script>
<script src="js/common.js"></script>
</body>
</html>
