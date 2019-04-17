function login(){
	location.href = "login";
}

function signup(){
	location.href = "signup";
}


$(document).ready(function () {
//    $('#login').click(function() {
//       
//       var id  = $("#id").val();
//       var password = $("#password").val();
//       
//        if(id == ""){
//                 alert("아이디를 입력하세요.");
//                 $("#id").focus();
//                 return;  
//             }
//             if(password == ""){
//                 alert("비밀번호를 입력하세요.");
//                 $("#password").focus();
//                 return;
//             }   
//             
//        var query = {id:$("#id").val(), password:$("#password").val()};
//        
//        $.ajax({
//           type : "POST",
//           dataType : 'text',
//           data : query,
//           url : "login.do",
//           success : function(data) {
//            if(data == "success")
//               window.location.href = "/"
//            else{
//               document.getElementById("loginfail").innerHTML="아이디 또는 비밀번호가 틀렸습니다.";
//               console.log("error");
//            }
//           }
//       });
//    });
 });