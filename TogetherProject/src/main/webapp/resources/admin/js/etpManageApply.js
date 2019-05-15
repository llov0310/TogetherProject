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
		$(".etpCk").off().on('click',function(){
			// name이 같은 체크박스의 값들을 배열에 담는다
			// off() : 이벤트 제거하기
			var user_id = [];
			var etpCk = $(this).val();
			
			
			console.log(etpCk);
			console.log("첫 번째 테스트 : " + user_id);
			
			
			$("input[name='user_id']:checked").each(function(i){
				 user_id.push($(this).val());
			});
			
			console.log("두 번째 테스트 : " + user_id);
			//jQuery.ajaxSettings.traditional = true; // arr전송하기
			
			$.ajax({
				url : "/etpApplyManage",
				type : "POST",
				dataType : "text",
				data : {user_id : user_id, etpCk : etpCk},
				traditional: true, //배열 때문에 쓰는거
				
				success : function(data){
					alert("완료");
					console.log(data + "넘어오는지 테스트");
					window.location.href= "/enterpriseManage/"  + (num+1);
					
				},
				error:function(jqXHR, textStatus, errorThrown){
					alert('에러 발생 \n' + textStatus + ' : ' + errorThrown);
				}		
			}); // ajax END
		});// click END
		
		
		//★뭐지 이건★
		   $('.pagination-inner a').on('click', function() {
		      $(this).siblings().removeClass('pagination-active');
		      $(this).addClass('pagination-active');
		   });
		
		/*pagination*/	   
		   var num = 0;

		   var newURL =  window.location.pathname;
		   
		   
		   
		   var url = newURL.split('/');
		   
		   if(url[2] != 'search'){ //검색이 아닐 때
			   if(url[2] != null){
				      $("#"+url[2]+"").addClass('pagination-active');
				   }

				   $('.pagination-inner a').on('click', function() {
				      var a = $(".pagination-inner a").index(this);
				      num = a;
				      window.location.href = "/enterpriseManage/"+(num+1);
				   });

				   $('.pagination-newer').click(function(){
				      if(1 > parseInt(url[2])-1 )
				         window.location.href = "/enterpriseManage/"+(parseInt(url[2]));
				      else
				         window.location.href = "/enterpriseManage/"+(parseInt(url[2])-1);
				   });

				   $('.pagination-older').click(function(){
				         window.location.href = "/enterpriseManage/"+(parseInt(url[2])+1);
				   });
			   
		   }else{ //검색 일 때
			      if(url[3] != null){
			          $("#"+url[3]+"").addClass('pagination-active');
			       }

			       $('.pagination-inner a').on('click', function() {
			          var a = $(".pagination-inner a").index(this);
			          var b = $(".pagination-inner a:eq("+a+")").attr("id");
			          num = b;
			          window.location.href = "/enterpriseManage/search/"+num+"/"+url[4]+"/"+url[5];
			       });

			       $('.pagination-newer').click(function(){
			          if(1 > parseInt(url[3])-1 )
			             window.location.href = "/enterpriseManage/search/"+(parseInt(url[3]))+"/"+url[4]+"/"+url[5];
			          else
			             window.location.href = "/enterpriseManage/search/"+(parseInt(url[3])-1)+"/"+url[4]+"/"+url[5];
			       });

			       $('.pagination-older').click(function(){
			          window.location.href = "/enterpriseManage/search/"+(parseInt(url[3])+1)+"/"+url[4]+"/"+url[5];
			       });
		   } // else 끝
		   
		   // 검색 이벤트
		   $("#search").click(function(){   
			      if(url[2] != 'search'){
			         var page = 1; // 현재 페이지 번호
			         var searchType = $("#search-select option:selected").val();
			         var keyword = $("#keyword").val();

			         if(keyword.trim() == ""){
			            alert("검색내용을 입력하세요");
			            $("#keyword").val("");
			            $("#keyword").focus();
			            return false;
			         }
			         window.location.href = "/enterpriseManage/search/"+page+"/"+searchType+"/"+keyword;
			      } else{
			         var page = 1; // 현재 페이지 번호
			         var searchType = $("#search-select option:selected").val();
			         var keyword = $("#keyword").val();

			         if(keyword.trim() == ""){
			            alert("검색내용을 입력하세요");
			            $("#keyword").val("");
			            $("#keyword").focus();
			            return false;
			         }
			         window.location.href = "/enterpriseManage/search/"+page+"/"+searchType+"/"+keyword;
			      }
			   }); // 검색 이벤트 종료
		   
		   // 엔터치면 검색 되게 하는 함수
			$('#keyword').keypress(function(event) {
				if (event.which == 13) {
					$('#search').click();

				}
			});
		
}); //ready function END