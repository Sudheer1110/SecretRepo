$(document).ready(function(){
    $("#cpassword").on("input",function(){
        let pass=$("#password").val();
        let cpass=$("#cpassword").val();
        if(pass===cpass){
            $("#messages").text("");
            $("#resetpass").prop("disabled",false);
        }else{
            $("#messages").text("Password and Confirm password fields must be same");
            $("#messages").css("color","red");
            $("#resetpass").prop("disabled",true);
        }
    });

    $("#resetpass").on("click",function(){
        $.ajax({
            url:"http://localhost:8081/exam/resetPass",
            method:"POST",
            data:{
                "username":$("#username").val(),
                "password":$("#password").val()
            },
            success:function(result){
                $("#messages").text(result["user_message"]);
            }
        })
    })
})