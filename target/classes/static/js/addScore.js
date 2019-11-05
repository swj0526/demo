
    layui.use(['element', 'form'], function () {
        var element = layui.element;
        var form = layui.form;
        $("[name='name']").blur(function () { //失去焦点事件
            if ($("[name='name']").val() == "" || $("[name='name']") == null) {
                layer.msg('学生姓名不可为空!');
            } else {
                $.post("/getStudentController", {
                    name: $("[name='name']").val()
                }, function (result) {
                    if (result.success == false) {
                        layer.msg(result.msg); //没有找到学生
                    } else if (result.success == true) {
                        alert(result);
                        $('#mod').click(function () {
                            form.on('submit(mod)', function (scoreData) {
                                var id = result.data.scoreId;
                                $.post('/modifyScoreController', {
                                    id: id,
                                    chinese: $("[name='chinese']").val(),
                                    english: $("[name='english']").val(),
                                    maths: $("[name='maths']").val()
                                }, function (resultScore) {
                                    if (resultScore.success == true) {
                                        layer.msg('修改成功!');
                                        $("#reset").trigger("click");
                                    } else if (resultScore.success == false) {
                                        layer.msg("修改失败!");
                                    }
                                });
                                return false;
                            });

                        });
                    }
                });
            }
        });
        //监听提交

    });

/*$.post("/getStudentController", {
                                name: $("[name='name']").val()
                            }, function (result) {
                                if (result.success == false) {
                                    layer.msg(result.msg); //没有找到学生
                                } else if (result.success == true) {
                                    var id = result.data.scoreId;
                                    $('#mod').click(function () {
                                        alert("234");
                                        $.post('/addScoreController', {
                                            id: id,
                                            chinese: $("[chinese='chinese']").val(),
                                            english: $("[english='english']").val(),
                                            maths: $("[maths='maths']").val()
                                        }, function (resultScore) {
                                            if (resultScore.success == true) {
                                                layer.msg('修改成功!');
                                                $("#reset").trigger("click");
                                            } else if (resultScore.success == false) {
                                                layer.msg(resultScore.msg);
                                            }
                                        });
                                    });
                                }
                            });*/