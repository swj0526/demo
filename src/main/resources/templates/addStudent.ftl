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
    <script type="text/javascript" src="js/addStudent.js">
    </script>

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "header.ftl"/>
    <#include "left.ftl"/>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div>
                <form class="layui-form"   >
                    <div class="layui-form-item">
                        <label class="layui-form-label">学生姓名</label>
                        <div class="layui-input-block">
                            <input type="text" name="name" required lay-verify="required"
                                   autocomplete="off" class="layui-input" placeholder="请输入">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">学生住址</label>
                        <div class="layui-input-block">
                            <input type="text" name="address" required lay-verify="required"
                                   autocomplete="off" class="layui-input" placeholder="请输入">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">学生年级</label>
                        <div class="layui-input-block">
                            <select name="gradeId" lay-verify="required">
                                <option value=""></option>
                                <option value="1">一年级</option>
                                <option value="2">二年级</option>
                                <option value="3">三年级</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="add" id="add">立即提交</button>
                            <button type="reset" class="layui-btn layui-btn-primary" id="reset">重置</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © swj.com - 学生信息管理系统
    </div>
</div>
</body>
</html>