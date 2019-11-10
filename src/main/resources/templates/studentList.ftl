<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>学生信息管理系统</title>
    <link rel="stylesheet" type="text/css" href="layui/css/layui.css"/>
    <#-- <script type="text/javascript" src="js/jquery.js">
     </script>-->
    <script type="text/javascript" src="layui/layui.js">
    </script>
</head>
<body class="layui-layout-body">

<#--//修改删除弹出层-->
<div style="display: none" id="updateOrDelete">
    <form class="layui-form" lay-filter="dataForm" id="dataFor" style="margin-right: 30px;">
        <div class="layui-form-item" style="display: none;">
            <label class="layui-form-label">id</label>
            <div class="layui-input-block">
                <input type="text" name="id" required lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" style="display: none;">
            <label class="layui-form-label">scoreId</label>
            <div class="layui-input-block">
                <input type="text" name="scoreId" required lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">学生姓名</label>
            <div class="layui-input-inline">
                <input type="text" name="name" required lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">学生地址</label>
            <div class="layui-input-inline">
                <input type="text" name="address" required lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">语文成绩</label>
            <div class="layui-input-inline">
                <input type="number" name="chinese" required lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">数学成绩</label>
            <div class="layui-input-inline">
                <input type="number" name="maths" required lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">英语成绩</label>
            <div class="layui-input-inline">
                <input type="number" name="english" required lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" style="width: 50%">
            <label class="layui-form-label">所在年级</label>
            <div class="layui-input-block">
                <select name="gradeId" lay-verify="required">
                    <option value="1">一年级</option>
                    <option value="2">二年级</option>
                    <option value="3">三年级</option>
                </select>
            </div>
        </div>


    </form>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-filter="formDemo" id="btn">修改</button>
        </div>
    </div>
</div>


<!-- 内容主体区域 -->

<div style="padding: 15px;">
    <div class="demoTable">
        <#--查询条件-->
        <form class="layui-form  layui-form-pane" id="formStudent" method="post">
            <div class="layui-form-item" pane>
                <label class="layui-form-label">学生姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="name" placeholder="学生姓名"
                           autocomplete="off" class="layui-input" id="name">
                </div>

            </div>
            <div class="layui-form-item" pane>
                <label class="layui-form-label">学生班级</label>
                <div class="layui-input-block">
                    <select name="gradeId" id="gradeId">
                        <option value="0">全部</option>
                        <option value="1">一年级</option>
                        <option value="2">二年级</option>
                        <option value="3">三年级</option>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formD" data-type="reload" id="subStudent">
                        立即提交
                    </button>
                    <button class="layui-btn" id="excel">
                        导出表格
                    </button>
                </div>
            </div>
        </form>
    </div>
    <#--数据表格-->
    <table class="layui-hide" id="test" lay-filter="test"></table>
    <#--自定义分页-->
    <div id="demo0"></div>
    <#--头部工具栏-->
    <script type="text/html" id="toolbarDemo">

            </script>
    <#--//行工具栏-->
    <script type="text/html" id="barDemo">
        <a class="layui-btn  layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </script>
    <#--监听事件-->
    <script>
        layui.use(['table', 'layer', 'jquery', 'form', 'element', 'laypage'], function () {

            var currPage = 1;
            var count1 = 0;
            var table = layui.table;
            var layer = layui.layer;
            var $ = layui.jquery;
            var form = layui.form;
            var element = layui.element;
            var laypage = layui.laypage;
            var name = $("#name");
            var gradeId = $("#gradeId");
            //渲染数据表格
            var tableIns = table.render({
                elem: '#test'//渲染目标
                , url: '/listConditionController'//数据接口
                , id: 'userTableReload'
                , page: true
                , done: function (rest, curr, count) {
                    console.log(rest);//后台返回的json字符串
                    console.log(curr);//当前页
                    console.log(count);//数据总条数
                    currPage = curr;
                    count1 = count;

                }
                /* , toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
                 , defaultToolbar: ['exports']
                 , title: '用户数据表'//excel导出*/
                , cols: [[
                    {field: 'name', title: '学生姓名'},
                    {field: 'address', title: '学生地址'},
                    /* {field: 'time', title: '入学时间'},*/
                    {field: 'gradeName', title: '学生班级'}
                    , {field: 'chinese', title: '语文成绩'}
                    , {field: 'maths', title: '数学成绩'}
                    , {field: 'english', title: '英语成绩'}
                    , {fixed: 'right', title: '操作', toolbar: '#barDemo'}
                ]]
            });

            //请求路径
            var url;
            //标记弹出层
            var mainIndex;


            //修改弹窗
            function modify(data) {
                mainIndex = layer.open({
                    type: 1,
                    title: "修改用户",
                    skin: 'layui-layer-rim', //加上边框
                    area: ['500px', '500px'], //设置宽高
                    content: $("#updateOrDelete"),
                    success: function (index) {
                        form.val("dataForm", data);
                        url = "/modifyStudentScoreController"
                    }
                });
            }


            /* //监听提交事件
             form.on("submit(formDemo)", function (obj) {

             });*/
            $('#btn').click(function () {
                //序列化表单提交数据
                var serialize = $("#dataFor").serialize();
                //发送ajasx请求
                $.post(url, serialize, function (result) {
                    layer.close(mainIndex);
                    tableIns.reload();
                });
            });
            //监听行工具事件
            table.on('tool(test)', function (obj) {
                var data = obj.data;//获得当前行数据
                //console.log(obj)  obj.event获取lay-event 对应的值
                if (obj.event === 'del') {
                    layer.confirm('真的删除行么', function (index) {
                        obj.del();
                        layer.close(index);//向服务端发送删除指令
                        /*    alert(data.score);*/
                        //删除事件
                        $.post("/deleteStudentScoreController", {id: data.id, scoreId: data.scoreId}, function () {
                            layer.msg("删除成功!");
                        });
                    });
                } else if (obj.event === 'edit') {//编辑
                    modify(data)
                }
            });
            var $ = layui.$, active = {
                reload: function () {
                    //执行重载
                    table.reload('userTableReload', {
                        page: {
                            curr: currPage
                        },
                        where: {
                            'name': name.val(),
                            'gradeId': gradeId.val()
                        }
                    }, 'data');
                }
            }
            $('.demoTable .layui-btn').on('click', function () {
                var type = $(this).data('type');
                active[type] ? active[type].call(this) : '';
                return false;
            });
            $('#excel').click(function () {
                $.post("/doExcel", function (result) {
                    window.open(result);
                });
            });


        });
    </script>
</div>

</body>
</html>