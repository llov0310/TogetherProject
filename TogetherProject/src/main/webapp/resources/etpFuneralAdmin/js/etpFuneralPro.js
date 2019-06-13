/* Functions */
  
  function refresh(){
	  $(".total").load("/etpproduct");
  }

  function removeRow(button) {
    button.closest("tr").remove();
  }
  
  function popupOpen(){
		var url= "/etpFuneralProAddPage";    // 팝업창 페이지 URL
		var winWidth = 700;
	    var winHeight = 700;
	    var popupOption= "width="+winWidth+", height="+winHeight;    // 팝업창
																		// 옵션(optoin)
		window.open(url,"",popupOption);
	}
  /* Doc ready */
 
  $(".remove").on('click', function () {
	  var nm = $(this).parent().parent().eq(0).find('label.pd_nm').text();
	  console.log(nm);
	  $.ajax({
		 type : 'POST',
		 url : '/etpProductDel',
		 dataType : 'text',
		 data : {nm},
		 success : function(){
			 alert("왓음");
			 removeRow($(this));
			 $(".total").load("/etpFuneralPro");
			 window.location.reload();
		 }
		 
	  });
      
     
  });
  
  $(".add").on('click',function(){
			
			popupOpen();
	  });