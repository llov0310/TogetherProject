$(document).ready(function(){
	/*pagination*/
	   var num = 0;

	   var newURL =  window.location.pathname;

	   var url = newURL.split('/');

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
});