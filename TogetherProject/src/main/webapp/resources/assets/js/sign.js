$(document).ready(function(){

	  $('.input').focus(function(){
	    $(this).parent().find(".label-txt").addClass('label-active');
	  });

	  $(".input").focusout(function(){
	    if ($(this).val() == '') {
	      $(this).parent().find(".label-txt").removeClass('label-active');
	    };
	  });
	  
	  $('.input').focus(function(){
		    $(this).parent().find(".label-txt2").addClass('label-active');
		  });

		  $(".input").focusout(function(){
		    if ($(this).val() == '') {
		      $(this).parent().find(".label-txt2").removeClass('label-active');
		    };
		  });
		  //로그인 정보 가져오는 jquery문
		  
		  $("#signup").click(function(){
			 var id = $("#id").val();
			 var password = $("#password").val();
			 var name = $("#name").val();
			 var adress = $("#sample6_extraAddress").val();
			 var email = $("#email").val();
			 
			 var query = {	user_id : id, 
					 		password:password,
			 				name:name,
			 				adress:adress,
			 				email:email};
			 
			 $.ajax({
				
				 type : "POST",
		           dataType : 'text',
		           data : query,
		           url : "sign.do",
		           success : function(data) {
		        	   alert("회원가입이 완료되었습니다 로그인 페이지로 이동합니다.")
		        	   window.location.href = "/nav/login"
		        }

			 });
		  });

	});
	
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }
            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
//            document.getElementById('sample4_postcode').value = data.zonecode;
            document.getElementById("sample4_roadAddress").value = roadAddr;
//            document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
            
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("sample4_extraAddress").value = extraRoadAddr;
            } else {
                document.getElementById("sample4_extraAddress").value = '';
            }

//            var guideTextBox = document.getElementById("guide");
            // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
//            if(data.autoRoadAddress) {
//                var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
//                guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
//                guideTextBox.style.display = 'block';
//
//            } else if(data.autoJibunAddress) {
//                var expJibunAddr = data.autoJibunAddress;
//                guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
//                guideTextBox.style.display = 'block';
//            } else {
//                guideTextBox.innerHTML = '';
//                guideTextBox.style.display = 'none';
//            }
        }
    }).open();
}