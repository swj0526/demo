$(function () {
    $('#register').click(function () {
        var userName = $(".userName").val();
        var password = $(".password").val();
        if(userName==null||userName==""||password==null||password==""){
            $('.error').css("display","");
        $('.error').text("用户名或密码不能为空");
        }else {
            $('.error').css("display","none");
            $('.error').text("");
            $.get('/registerCheck', {
                userName: userName,
                password: password
            }, function (result) {
                if(result.success==true){
                  window.location.href="/";
                }else if(result.success==false){
                    $('.error').css("display","");
                    $('.error').text(result.msg);
                }
            });
        }

    });
});