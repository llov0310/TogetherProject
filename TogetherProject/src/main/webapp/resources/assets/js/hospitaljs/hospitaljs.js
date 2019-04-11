


$(document).ready(function() {
	 	
		 $("#searchbar").click(function() {
			 $("#searchlist").toggle(); //천천히 보이기

		 });
	 }); 
	
	$(document).ready(function() {

			$(".ok").click(function() {
			var a =$(this).parent().parent().parent();
			a.children().eq(3).toggle();
			map.relayout()
			 
		 });
	 }); 
	
	
	$(document).ready(function(){ 
	
		$(function() {
			var selectTarget = $('.selectbox select');

			// focus 가 되었을 때와 focus 를 잃었을 때
			selectTarget.on({
				'focus' : function() {
					$(this).parent().addClass('focus');
				},
				'blur' : function() {
					$(this).parent().removeClass('focus');
				}
			});

			selectTarget.change(function() {
				var select_name = $(this).children('option:selected').text();
				$(this).siblings('label').text(select_name);
				$(this).parent().removeClass('focus');
			});
		}); 
	});
