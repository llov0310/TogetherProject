$(document).ready(function(){
	//체크박스 전체 선택
	$("#allCheck").click(function(){
		//클릭 되었으면
		if($("#allCheck").prop("checked")){
			//input태그의 name이 user_id인 태그들을 찾아서 checked옵션을 true로 정의
			$("input[name=user_id]").prop("checked", true);
		}else{
			//input태그의 name이 user_id인 태그들을 찾아서 checked옵션을 false로 정의
			$("input[name=user_id]").prop("checked", false);
		}
	});
	//보낼때
	$(".etpCk").click(function(){
		// name이 같은 체크박스의 값들을 배열에 담는다
		var user_id = [];
		var etpCk = $(this).val();
		$("input[name='user_id']:checked").each(function(i){
			 user_id.push($(this).val());
		});

		console.log(etpCk);
		console.log(user_id);
		

		//jQuery.ajaxSettings.traditional = true; // arr전송하기
		
		$.ajax({
			url : "/etpApplyManage",
			type : "POST",
			dataType : "text",
			data : {"user_id" : user_id, "etpCk" : etpCk},
			traditional: true,
			success : function(data){
				alert("완료");
				console.log(data + "넘어오는지 테스트");
				window.location.href="enterpriseManage";
			},
			error:function(jqXHR, textStatus, errorThrown){
				alert('에러 발생 \n' + textStatus + ' : ' + errorThrown);
			}
			
			
		});
	});// click
	
	
}); 