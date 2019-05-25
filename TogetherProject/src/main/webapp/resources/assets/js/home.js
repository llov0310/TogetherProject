//$(document).ready(function(){
//	
//	
//	$('#mainNav').addClass navbar-light
//	
//});

$(window).scroll(function() {    
    var scroll = $(window).scrollTop();
     //console.log(scroll);
    if (scroll >= 1000) {
        //console.log('a');
        $("#mainNav").addClass("navbar-light");
    } else {
        //console.log('a');
        $("#mainNav").removeClass("navbar-light");
    }
});