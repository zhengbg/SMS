<html>
<head>
    <script src="js/jquery.min.js"></script>
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
