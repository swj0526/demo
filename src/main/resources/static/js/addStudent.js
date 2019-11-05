

    layui.use(['element', 'form'], function () {
        var element = layui.element;
        var form = layui.form;
        //监听提交
        $('#add').click(function () {
            var name = $("[name='name']").val();
            var address = $("[name='address']").val();
            var gradeId = $("[name='gradeId']").val();
            if (name == null || name == "" || address == null || address == "" || gradeId == "") {
                layer.msg('提交信息不能为空!');
            } else {
                form.on('submit(add)', function (data) {
                    var student = data.field;
                    $.get('/addStudentController', student, function (result) {
                        if (result.success == true) {
                            layer.msg('注册成功!');
                            $("#reset").trigger("click");
                        } else if (result.success == false) {
                            layer.msg(result.msg);
                        }
                    });
                    return false;
                });
            }
        });
    });
