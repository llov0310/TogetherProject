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
					  var database = firebase.database();	

					  
					  
$('.tr_list').on('click',function(){
		
		var t_day = $(this).find('td').eq(1).text(); //구매일
		var product_nm = $(this).find('td').eq(2).text(); //상품명
		var order_nm = $(this).find('td').eq(3).text(); //구매자명
		var order_ph = $(this).find('td').eq(4).text(); // 연락처
		var first_day = $(this).find('label').eq(0).text(); // 예약첫날
		var last_day = $(this).find('label').eq(1).text(); // 예약마지막날
		var book_stat = $(this).find('td').eq(6).find('label').text(); // 예약상태
		var check = $(this).find('td').eq(7).find('label').text(); // 확인유무
		var price = $(this).find('td').eq(8).text().trim(); // 가격
		var etp_nm = $(this).find('td').eq(9).text(); // 가격
		var f_uid = $(this).find('td').eq(10).text(); // 가격
		
		console.log(etp_nm);
		console.log(f_uid);
	     var TokenKey = firebase.database().ref().child('Tokens').child(f_uid).child('TokenUid');
		  
		  console.log(TokenKey);
		
		
		$('.bP').bPopup({follow : [false,false],
			opacity : 0.6,
					positionStyle : 'fixed'});
		
		
		$('.this_day').text(t_day);
		$('.pro_nm').text(product_nm);
		$('.nm').text(order_nm);
		$('.ph').text(order_ph);
		$('.fir').text(first_day);
		$('.la').text(last_day);
		$('.stat').text(book_stat);
		$('.check').text(check);
		$('.to_price').text(price);
		
		if(book_stat == "취소 대기중"){
			$('.success').text("취소");
		}else{
			$('.success').text("확인");
		}
		$('.success').click(function(){
			
			var day1 = $(this).parent().parent().find('.fir').text(); // 첫날
			var day2 = $(this).parent().parent().find('.la').text(); // 마지막
			var day_th = $(this).parent().parent().find('.this_day').text(); // 마지막
			var nm = $(this).parent().parent().find('.nm').text(); // 주문자이름
			var check = $(this).parent().parent().find('.check').text(); //체크유무
			var check_val = '1';
			
			var context = "[Together]\n"
		  		context += "예약 해주셔서 감사합니다.\n"
		  		context += "[" + order_nm + "] 고객님께서 예약이 접수처리가 완료되었습니다.\n"
		  		context += "■ 예약일시 : ["+first_day +"~"+last_day+"]\n"
		  		context += "■ 매장명 : ["+etp_nm+"]\n"
		  		context += "■ 주문상품 : ["+product_nm+"]\n"
		  		context += "■ 가격:["+price+"]\n"
		  		context += "\n "
		  		console.log(context);	
			
			console.log(day1);
			console.log(day2);
			console.log(day_th);
			
			if(book_stat == "예약 대기중"){
				
			if(check == "미확인"){
				
					$.ajax({
						url : "/etpOrderListCheck",
						type : "POST",
						dataType : "text",
						data : {day1,day2,nm,check_val,day_th},
						success : function(data){
							if(data == "success"){
								$('.this_day').text('');
								$('.pro_nm').text('');
								$('.nm').text('');
								$('.ph').text('');
								$('.fir').text('');
								$('.la').text('');
								$('.stat').text('');
								$('.check').text('');
								$('.to_price').text('');
								
								var newChats = firebase.database().ref().child('Chats').push();
								  console.log(newChats.key);
								  newChats.set({
									  isseen: false,
									  message: context,
									  receiver: f_uid,
									  sender: "3PTCMHLT3wO0Z0BmVUAHDQt0KGs2"
								  });
									alert("메세지 전송");
								
								alert("완료 되었습니다.");
								
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
							return false;						
							}
						}
					});
				
				
			}else if(check == "확인"){
				$('.this_day').text('');
				$('.pro_nm').text('');
				$('.nm').text('');
				$('.ph').text('');
				$('.fir').text('');
				$('.la').text('');
				$('.stat').text('');
				$('.check').text('');
				$('.to_price').text('');
				
				$('.bP').bPopup().close();
			}//======예약 확정 ======
			
			}if(book_stat == "취소 대기중"){
				
				var context = "[Together]\n"
			  		context += "[" + order_nm + "] 고객님 예약이 취소처리가 완료되었습니다.\n"
			  		context += "■ 예약일시 : ["+first_day +"~"+last_day+"]\n"
			  		context += "■ 매장명 : ["+etp_nm+"]\n"
			  		context += "■ 주문상품 : ["+product_nm+"]\n"
			  		context += "■ 가격:["+price+"]\n"
			  		context += "\n "
				  	context += "감사합니다."
			  		console.log(context);	
				
				$.ajax({
					url : "/etpOrderListcancle",
					type : "POST",
					dataType : "text",
					data : {day1,day2,nm,check_val,day_th},
					success : function(data){
						if(data == "success"){
							$('.this_day').text('');
							$('.pro_nm').text('');
							$('.nm').text('');
							$('.ph').text('');
							$('.fir').text('');
							$('.la').text('');
							$('.stat').text('');
							$('.check').text('');
							$('.to_price').text('');
							
							var newChats = firebase.database().ref().child('Chats').push();
							  console.log(newChats.key);
							  newChats.set({
								  isseen: false,
								  message: context,
								  receiver: f_uid,
								  sender: "3PTCMHLT3wO0Z0BmVUAHDQt0KGs2"
							  });
								alert("메세지 전송");
							
							alert("완료 되었습니다.");
							
							 TokenKey.on("value", function(snapshot) {
								  var TokenId = ""
									  TokenId = snapshot.val();
								  var Tokenkey = TokenId.token;
								  var cancle = "1";
							  
								$.ajax({	
									type : "POST",
									url : "/fcm",
									dataType : 'json',
									data : {token:Tokenkey,
											cancle:cancle},
									success : function(data){
									console.log("들어옴???");
									}
									
								});
								location.reload();
						
							  });
						return false;						
						}
					}
				});
		}else if(check == "확인"){
			$('.this_day').text('');
			$('.pro_nm').text('');
			$('.nm').text('');
			$('.ph').text('');
			$('.fir').text('');
			$('.la').text('');
			$('.stat').text('');
			$('.check').text('');
			$('.to_price').text('');
			
			$('.bP').bPopup().close();
		}
			
		});
		//=====취소확정
		$('.exit').on('click',function(){
			
			$('.this_day').text('');
			$('.pro_nm').text('');
			$('.nm').text('');
			$('.ph').text('');
			$('.fir').text('');
			$('.la').text('');
			$('.stat').text('');
			$('.check').text('');
			$('.to_price').text('');
			$('.bP').bPopup().close();
			
		});
	
	});