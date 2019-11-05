<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>学生信息管理系统</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>
    <script type="text/javascript" src="js/jquery.js">
    </script>
    <script type="text/javascript" src="layui/layui.js">
    </script>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "header.ftl"/>
    <#include "left.ftl"/>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © swj.com - 学生信息管理系统
    </div>
</div>

<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });
</script>
</body>
</html>