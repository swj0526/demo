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
    <style>
        .layui-tab-item{
            height: 100%;
        }
    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <#include "header.ftl"/>
    <#include "left.ftl"/>
    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            <div class="layui-tab" lay-filter="demo" lay-allowclose="true">
                <ul class="layui-tab-title">
                </ul>
                <div class="layui-tab-content" >
                </div>
            </div>
        </div>
    </div>
    <#include "footer.ftl"/>
</div>
<script>
    layui.use(['element', 'jquery'], function () {
        var $ = layui.jquery,
            element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
        //点击左边的按钮,然后判断是否新增tab还是需要定位一个tab
        $(".add").click(function () {
            //获取被点击的元素id,来取得准确的元素
            var id = $(this).attr('id');
            //判断是否需要新增还是定位
            if ($("[lay-id=" + id + "]").length > 0) { //存在
                //存在的话,要给这个元素动态的追加属性data-type="tabChange"
               // $(this).attr('data-type', 'tabChange');
                //切换到指定Tab项
                element.tabChange('demo', id); //切换到：用户管理
            } else { //新增
                //$(this).attr('data-type', 'tabAdd');
                //根据id判断嵌入什么页面
                if (id == "1") {
                    element.tabAdd('demo', {
                        title: $(this).text(), //新增学生信息
                        content: '<iframe src="/addStudent" frameborder="0" height="550px" width="100%"></iframe>',
                        id: id //实际使用一般是规定好的id，这里以时间戳模拟下
                    });
                    //$(this).attr('data-type', 'tabChange');
                    element.tabChange('demo', id);
                } else if (id == "2") {
                    element.tabAdd('demo', {
                        title: $(this).text(), //新增成绩
                        content: '<iframe src="/addScore" frameborder="0" height="550px" width="100%"></iframe>',
                        id: id //实际使用一般是规定好的id，这里以时间戳模拟下
                    });
                   // $(this).attr('data-type', 'tabChange');
                    //切换到指定Tab项
                    element.tabChange('demo', id);
                }else if(id == "3"){
                    element.tabAdd('demo', {
                        title: $(this).text(), //学生信息查询
                        content: '<iframe src="/studentList" frameborder="0" height="700px" width="100%"></iframe>',
                        id: id //实际使用一般是规定好的id，这里以时间戳模拟下
                    });
                    //$(this).attr('data-type', 'tabChange');
                    //切换到指定Tab项
                    element.tabChange('demo', id);
                }
            }
        });
        $('.site-demo-active').on('click', function () {
            var othis = $(this),
                type = othis.data('type');
            //active[type] ? active[type].call(this, othis) : '';
        });
        //Hash地址的定位
        var layid = location.hash.replace(/^#test=/, '');
        element.tabChange('test', layid);
        element.on('tab(test)', function (elem) {
            location.hash = 'test=' + $(this).attr('lay-id');
        });
    });
</script>
</body>
</html>