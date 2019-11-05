<html>
<head>
    <title>学生信息管理</title>
    <meta charset="utf-8">
    <link href="css/login.css" rel="stylesheet" type="text/css" charset="utf-8">
    <script type="text/javascript" src="js/jquery.js">
    </script>
    <script type="text/javascript" src="js/login.js"></script>
</head>
<body>
<div class="formFrame">
        <div class="title_div">学生信息管理系统登录</div>
        <div class="info_div">
            <div class="right">
                <p><input type="text" name="title" required lay-verify="required" placeholder="请输入标题" autocomplete="off" class="layui-input userName">  </p>
                <p><input type="password" class="password" placeholder="请输入密码"></p>
            </div>
        </div>
        <div class="bottom">
            <div>
                   <span class="error"></span>
                <a href="/register">注册</a></div>
            <div>
                <button id="login">登录</button>
            </div>
        </div>
</div>
</body>
</html>
