$(document).ready(function(){
	  $(window).load(function() {     
	       $('#loading').hide();   
	      }); 
 
	 $("#register_Btn").on('click', function(){
	  var pd_nm = $("#gdsName").val(); // 상품 이름
	  var pd_price = $("#gdsPrice").val(); // 상품 가격
	  var pd_content = $("#gdsDes").val(); // 상품 설명
	  var pd_num = $("#gdsNum").val(); // 총갯수 수량
	  // 추후 업로드 // var pd_img = $("").text();
	  $('#loading').show();   
	  $.ajax({
		 type : 'GET',
		 url : '/etpProductAddRegister',
		 dataType : 'text',
		 data : {pd_nm,pd_price,pd_content,pd_num},
		 success : function(data){
			 $('#loading').hide();   
			 if(data == "success"){
			 alert("상품 추가 완료");
			 window.opener.location.href = "/etpProduct";	 
			 window.close();
		 }else{
			 alert("넘어오지않았음");
		 }
			 
			 
		 }
		 
		 
	  });
	  	
	  
	 }); 
  });
