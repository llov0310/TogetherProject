  function mapserch(){
	  location.href = "/hotelserch"
  }
  
$(document).ready(function() {
     
      $("#searchbar").click(function() {
    	  
         $("#searchlist").toggle(); //천천히 보이기

      });
   }); 



function place(text){

	var a = text;
	$("#searchbar").val(a);
	$("#searchlist").hide();
}