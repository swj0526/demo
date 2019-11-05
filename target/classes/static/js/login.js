$(function () {
    $('#login').click(function () {
        var userName = $(".userName").val();
        var password = $(".password").val();
        if (userName == null || userName == "" || password == null || password == "") {
            $('.error').css("display", "");
            $('.error').text("用户名或密码不能为空");
        } else {
            $('.error').css("display", "none");
            $('.error').text("");
            $.get('/loginCheck', {
                userName: userName,
                password: password
            }, function (result) {
                if (result.success == true) {
                    window.location.href = "/home";
                } else if (result.success == false) {
                    $('.error').css("display", "");
                    $('.error').text(result.msg);
                }
            });
        }
    });


});