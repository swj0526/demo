
    $("[name='name']").blur(function(){ //失去焦点事件
         if($("[name='name']").val()==""||$("[name='name']")==null){
             $('#error').css("display","");
             $('#error').text("学生姓名不可为空");
         }else{
             $('#error').css("display","none");
             $('#error').text("");

             $.post("/getStudentController",{
                 name:$("[name='name']").val()
             },function (result) {
                   if(result.success==true){
                       var  chinese = $("[name='chinese']").val();
                       var  maths = $("[name='maths']").val();
                       var  english = $("[name='english']").val();
                       if(chinese!=null&&maths!=null&&english!=null){
                           $.post("/SaveScoreController",{
                               chiese:chinese,
                               maths:maths,
                               english:english
                           },function (result) {
                           });
                       }
                   }else{
                       $('#error').css("display","");
                       $('#error').text("学生没有找到!");
                   }
               });


         }
    });
