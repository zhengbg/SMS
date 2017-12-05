<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>欢迎来到主页</title>
</head>
<body>
<h2>Hello World!</h2>
<button type="button" onclick="func();">弹弹弹</button>
<script>
    function func() {
        $.ajax({
            type: "GET",
            url: "/test1",
            success: function (data) {
                alert(data.resultDesc);
            },
            error: function (e) {
                alert("unknown error !");
            }
        });
    }
</script>
</body>
</html>
