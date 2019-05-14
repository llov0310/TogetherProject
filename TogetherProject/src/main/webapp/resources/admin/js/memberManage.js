$(document).ready(function(){
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
			      window.location.href = "/memberManage/"+(num+1);
			   });

			   $('.pagination-newer').click(function(){
			      if(1 > parseInt(url[2])-1 )
			         window.location.href = "/memberManage/"+(parseInt(url[2]));
			      else
			         window.location.href = "/memberManage/"+(parseInt(url[2])-1);
			   });

			   $('.pagination-older').click(function(){
			         window.location.href = "/memberManage/"+(parseInt(url[2])+1);
			   });
		   
	   }else{ //검색 일 때
		      if(url[3] != null){
		          $("#"+url[3]+"").addClass('pagination-active');
		       }

		       $('.pagination-inner a').on('click', function() {
		          var a = $(".pagination-inner a").index(this);
		          var b = $(".pagination-inner a:eq("+a+")").attr("id");
		          num = b;
		          window.location.href = "/memberManage/search/"+num+"/"+url[4]+"/"+url[5];
		       });

		       $('.pagination-newer').click(function(){
		          if(1 > parseInt(url[3])-1 )
		             window.location.href = "/memberManage/search/"+(parseInt(url[3]))+"/"+url[4]+"/"+url[5];
		          else
		             window.location.href = "/memberManage/search/"+(parseInt(url[3])-1)+"/"+url[4]+"/"+url[5];
		       });

		       $('.pagination-older').click(function(){
		          window.location.href = "/memberManage/search/"+(parseInt(url[3])+1)+"/"+url[4]+"/"+url[5];
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
		         }
		         window.location.href = "/memberManage/search/"+page+"/"+searchType+"/"+keyword;
		         
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
		         window.location.href = "/memberManage/search/"+page+"/"+searchType+"/"+keyword;
		      }
		   }); // 검색 이벤트 종료
	   
	   // 엔터치면 검색 되게 하는 함수
		$('#keyword').keypress(function(event) {
			if (event.which == 13) {
				$('#search').click();

			}
		});
		
		
}); //document ready