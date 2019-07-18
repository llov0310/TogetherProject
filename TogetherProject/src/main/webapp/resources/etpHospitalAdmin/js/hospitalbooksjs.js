var firebaseConfig = {
					  	apiKey : "AIzaSyDcJDqLBjqwDetOcCt5LcYjr8k8EL7mMnk",
					  	authDomain : "blogapp-a9a56.firebaseapp.com",
					  	databaseURL : "https://blogapp-a9a56.firebaseio.com",
					  	projectId : "blogapp-a9a56",
					  	storageBucket : "blogapp-a9a56.appspot.com",
					  	messagingSenderId : "604610409810",
					  	appId : "1:604610409810:web:76decd7c19ac5f6b"
					  };
					  // Initialize Firebase
					  firebase.initializeApp(firebaseConfig);

$('.tr_list').on('click',function(){
		var or_dt1 = $(this).find('label').eq(0).text(); // 이용 시간 : 시작
		var or_dt2 = $(this).find('label').eq(1).text();  // 이용 시간 : 끝
		var etp_cd = $(this).find('td').eq(8).text(); // 업체 코드
		var check = $(this).find('td').eq(6).find('label').text(); // 확인유무
		var hor_cd =  $(this).find('td').eq(0).text(); // 주문코드
		var user_id =  $(this).find('td').eq(1).text(); // 유저 이름
		var ph_no =  $(this).find('td').eq(3).text(); // 연락처
		var hor_pet_cd = $(this).find('td').eq(9).text();
		console.log(user_id);
		console.log(hor_cd);
		console.log(hor_pet_cd);
		// 팝업창
		$('.bP').bPopup({follow : [false,false],
			opacity : 0.6,
					positionStyle : 'fixed'});

		
		
		var testList = new Array();
		var jsonData = null;
		
		$.ajax({
			url : "/etphospitalOrderListDetail",
			type : "POST",
			dataType : "json",
			data : {hor_pet_cd, hor_cd,user_id},
			success : function(data){

				
				

		
					  
					  
					  var database = firebase.database();
				
//					  var userId = firebase.auth().onAuthStateChanged();
					  var dbRef = firebase.database().ref().child('Pets').child("6YZdQrf5RycKO1kwvze0vCXL8V83").child(hor_pet_cd);
					  dbRef.on("value", function(snapshot) {
						  var dog = "";
						  dog = snapshot.val();
						  console.log(snapshot.val());
						  console.log(dog.gender);
							console.log(data);
						
							 
						  
						  
					  });
				
					////시이작
					  if($(".selectDetail").find('tr').attr('class') != 'tr_pro'){
						  $(".selectDetail").append('<tr class="tr_pro">'
								 + '<td>' + '<img src="'+dog.petimageurl+'"/>'+ '</td><td>'
								 + dog.petname+ '</td><td>'
								 + dog.birthday + '</td><td>'
								 + dog.petbreed+ '</td></tr>'
								
								 );
					  }
			}
	});
			  
		
// if($(".selectDetail").find('tr').attr('class') != 'tr_pro'){
// for(var i = 0; i< data.length; i++ ){
// $(".selectDetail").append('<tr class="tr_pro">'
// + '<td>' + data[i].pd_nm + '</td><td>'
// + data[i].s_or_dt1 + ' ~ '+data[i].s_or_dt2 + '</td><td>'
// + data[i].s_th_dt + '</td><td>'
// + data[i].pd_price + '</td></tr>');
//
// var test = new Object();
//						
// test.user_id = data[i].user_id;
// test.s_or_dt1 = data[i].s_or_dt1;
// test.s_or_dt2 = data[i].s_or_dt2;
// test.s_th_dt = data[i].s_th_dt;
//						
// testList.push(test);
// }
//
// var total_price = 0;
// for(var i = 0; i< data.length; i++ ){
//						
// total_price = parseInt(total_price) + parseInt(data[i].pd_price);
// }
//					
// $(".user_nm").text(' : ' + user_nm);
// $(".ph_no").text(' : '+ ph_no);
//					
// $(".total_price").text(' : ' + total_price + '원');
//					
//				
// }else{
//					
// $(".selectDetail").empty();
//
// for(var i = 0; i< data.length; i++ ){
//				
// $(".selectDetail").append('<tr class="tr_pro">'
// + '<td>' + data[i].pd_nm + '</td><td>'
// + data[i].s_or_dt1 + ' ~ '+data[i].s_or_dt2 + '</td><td>'
// + data[i].s_th_dt + '</td><td>'
// + data[i].pd_price + '</td></tr>');
//						
// var test = new Object();
//						
// test.user_id = data[i].user_id;
// test.s_or_dt1 = data[i].s_or_dt1;
// test.s_or_dt2 = data[i].s_or_dt2;
// test.s_th_dt = data[i].s_th_dt;
//						
// testList.push(test);
//						
//						
// }
//					
//					
// var total_price = 0;
// for(var i = 0; i< data.length; i++ ){
//						
// total_price = parseInt(total_price) + parseInt(data[i].pd_price);
// }
//
// $(".user_nm").text(' : ' + user_nm);
// $(".ph_no").text(' : '+ ph_no);
//					
// $(".total_price").text(' : ' + total_price + '원');
//					
					
// }
	
// //출력해야될부분
// jsonData = JSON.stringify(testList);
// console.log(jsonData);
//
// $('.success').click(function(){
//					
// if(check == "미확인"){
// $.ajax({
// type : "POST",
// url : "/etpFuneralOrderListCheck",
// data : JSON.stringify(testList),
// contentType: 'application/json; charset=utf-8',
// success : function(data){
// if(data == "success"){
//										
//										
// $('.tr_pro').text('');
// $('.bP').bPopup().close();
// location.reload();
// return false;
// }
// }
// });
//						
//						
// }else if(check == "확인"){
// $('.bP').bPopup().close();
// }
					
					
// });
				
				
				
			 // success END
//		});// ajax END

		
// 확인 닫기 버튼 클릭 시
		
		
		$('.exit').on('click',function(){
			$('.tr_pro').text('');
			 
			$('.bP').bPopup().close();
			dog=null;

			
		});
		
		 
		
	});