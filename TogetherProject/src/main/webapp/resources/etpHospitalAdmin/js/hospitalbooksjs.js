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
					  var user_nm;
					  var or_dt1;
					  var etp_nm;
		
					  var check;
$('.tr_list').on('click',function(){
		or_dt1 = $(this).find('label').eq(0).text(); // 이용 시간 : 시작
		var or_dt2 = $(this).find('label').eq(1).text();  // 이용 시간 : 끝
		var etp_cd = $(this).find('td').eq(8).text(); // 업체 코드
		check = $(this).find('td').eq(6).find('label').text(); // 확인유무
		var hor_cd =  $(this).find('td').eq(0).text(); // 주문코드
		var user_id =  $(this).find('td').eq(1).text(); // 유저 이름
		var ph_no =  $(this).find('td').eq(3).text(); // 연락처
		var hor_pet_cd = $(this).find('td').eq(9).text();
		var f_uid = $(this).find('td').eq(10).text();
		user_nm = $(this).find('td').eq(2).text();
		etp_nm = $(this).find('td').eq(11).text();
		console.log(user_id);
		console.log(hor_cd);
		console.log(hor_pet_cd);
		console.log(f_uid);
		// 팝업창
		$('.bP').bPopup({follow : [false,false],
			opacity : 0.6,
					positionStyle : 'fixed'});

		
				
		$.ajax({
			url : "/etphospitalOrderListDetail",
			type : "POST",
			dataType : "json",
			data : {hor_pet_cd, hor_cd,user_id},
			success : function(data){
				console.log(data);
				
					  var database = firebase.database();
				
					  			
// var userId = firebase.auth().onAuthStateChanged();
					  var dbRef = firebase.database().ref().child('Pets').child(f_uid).child(hor_pet_cd);
					  
					  
					  dbRef.on("value", function(snapshot) {
						  var dog = "";
						  var dog_disease = "";
						  dog = snapshot.val();
						  console.log(snapshot.val());
						  console.log(dog.gender);
					
						
						  for(var i=0; i<data.length; i++){
								if(i == data.length-1){
							  dog_disease = dog_disease + data[i].hod_canser+ '<br>';
								}else{
									dog_disease = dog_disease + data[i].hod_canser+","+ '<br>';
								}
						  }
							
							  if($(".selectDetail").find('tr').attr('class') != 'tr_pro'){
								  $(".selectDetail").append('<tr class="tr_pro">'+ 
										  '<td>' + '<img class="dogimg" src="'+dog.petimageurl+'"/>'+ '</td><td class="doginfo">'
										 + "이름: "+'<span class="dogname">'+dog.petname+ '</span>'+'<br>'
										 + "출생일: "+ dog.birthday +	'<br>'
										 + "품종: " +dog.petbreed + '<br>'
										 + "성별: "+ dog.gender +	'<br>'
										 + "몸무게: "+dog.petweight +'<br>'
										 + '</td><td>'+'<span class="dogdi">'+ dog_disease +'</span>'+ '</td>'
										 + '</tr>'
										 );
							  }
						  
	
					  });
						 $(".user_nm").text(' : ' + user_nm);
						 $(".ph_no").text(' : '+ ph_no);					
						 $(".total_price").text(' : ' +  or_dt1 + '~'+or_dt2);	
					
			}
			
			
	});
		$(".selectDetail").empty();

		
		
		$('.success').click(function(){
			
			
			
			var dname = $(".dogname").text();
			var dogdi = $(".dogdi").text();
			var context = "[Together]\n"
		  		context += "예약 해주셔서 감사합니다.\n"
		  		context += "[" + user_nm + "] 고객님께서 예약이 접수처리가 완료되었습니다.\n"
		  		context += "■ 예약일시 : ["+or_dt1+"]\n"
		  		context += "■ 매장명 : ["+etp_nm+"]\n"
		  		context += "■ 예약견 : ["+dname+"]\n"
		  		context += "■ 접수내역 : ["+dogdi+"]\n"
		  		context += "\n "
		  		context += "방문시에 창구에서 확인후 대기해 주시면 감사하겠습니다\n"
		  		context += "진료시간은 표기된 시간보다 오차가 있을수 있는점 양해바랍니다.\n"
		  
					console.log(dname);
		  			console.log(user_nm);
		  			console.log(dogdi);	
		  			
				if(check == "미확인"){
					$.ajax({	
						type : "POST",
						url : "/etphospitalOrdercheck",
						dataType : 'text',
						data : {hor_cd},
					success : function(data){
						alert("예약 확인");
						console.log(context);
						$('.tr_pro').text('');
								$('.bP').bPopup().close();
							
								  var newChats = firebase.database().ref().child('Chats').push();
								  console.log(newChats.key);
								  newChats.set({
									  isseen: false,
									  message: context,
									  receiver: f_uid,
									  sender: "3PTCMHLT3wO0Z0BmVUAHDQt0KGs2"
								  });
								  
								  var TokenKey = firebase.database().ref().child('Tokens').child(f_uid).child('TokenUid');
								  
								  
								  TokenKey.on("value", function(snapshot) {
									  var TokenId = ""
										  TokenId = snapshot.val();
									  var Tokenkey = TokenId.token;
								  
								  
									$.ajax({	
										type : "POST",
										url : "/fcm",
										dataType : 'json',
										data : {token:Tokenkey},
										success : function(data){
										console.log("들어옴???");
										}
										
									});
									location.reload();
							
								  });
								  
									
//								
								return false;
								
								
					}
					});
				}
//						
//				
//				
				else if(check == "확인"){
					$('.bP').bPopup().close();
				}
			
			
		});
	
		
// 확인 닫기 버튼 클릭 시
		
		
		$('.exit').on('click',function(){
			$('.tr_pro').text('');
			 
			$('.bP').bPopup().close();		
		});
});		 
		
