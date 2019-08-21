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
		var or_dt = $(this).find('label').eq(0).text(); // 이용 시간 : 시작  
		var or_dt2 = $(this).find('label').eq(1).text();  // 이용 시간 : 끝
		var etp_cd = $(this).find('td').eq(8).text(); // 업체 코드
		var check = $(this).find('td').eq(6).find('label').text(); // 확인유무
		var etp_nm = $(this).find('td').eq(9).text(); // 업체 코드
		var book_stat = $(this).find('td').eq(5).find('label').text(); // 예약상태
		var user_nm =  $(this).find('td').eq(2).text(); //유저 이름 
		var ph_no =  $(this).find('td').eq(3).text(); //연락처
		var f_uid =  $(this).find('td').eq(10).text(); //연락처
		
		
	     var TokenKey = firebase.database().ref().child('Tokens').child(f_uid).child('TokenUid');
		  
		  console.log(TokenKey);
		console.log(user_nm);
		console.log(ph_no);
		console.log(book_stat);
		
		// 팝업창
		
		if(book_stat == "취소 대기중"){
			$('.success').text("취소");
		}else{
			$('.success').text("확인");
		}
		
		$('.bP').bPopup({follow : [false,false],
			opacity : 0.6,
					positionStyle : 'fixed'});

		
		
		var testList = new Array();
		var jsonData = null;
		
		$.ajax({
			url : "/etpFuneralOrderListDetail",
			type : "POST",
			dataType : "json",
			data : {or_dt, or_dt2, etp_cd},
			success : function(data){
				var pr = "";
				for(var i = 0; i< data.length; i++ ){
					if(i == data.length-1){
						pr = pr+data[i].pd_nm;
					}else{
						pr = pr+data[i].pd_nm+", ";
					}
				}

				if($(".selectDetail").find('tr').attr('class') != 'tr_pro'){
					for(var i = 0; i< data.length; i++ ){
						$(".selectDetail").append('<tr class="tr_pro">' 
								+ '<td>' + data[i].pd_nm + '</td><td>'
								+ data[i].s_or_dt1 + ' ~ '+data[i].s_or_dt2 + '</td><td>'
								+ data[i].s_th_dt + '</td><td>'
								+ data[i].pd_price + '</td></tr>');

						var test = new Object();
						
						test.user_id = data[i].user_id;
						test.s_or_dt1 = data[i].s_or_dt1;
						test.s_or_dt2 = data[i].s_or_dt2;
						test.s_th_dt = data[i].s_th_dt;
						
						testList.push(test);
					}

					var total_price = 0;
					for(var i = 0; i< data.length; i++ ){
						
						total_price = parseInt(total_price) + parseInt(data[i].pd_price);
					}
					
					$(".user_nm").text(' : ' + user_nm);
					$(".ph_no").text(' : '+ ph_no);
					
					$(".total_price").text(' : ' + total_price + '원');
					
				
				}else{
					
					$(".selectDetail").empty();

					for(var i = 0; i< data.length; i++ ){
				
						$(".selectDetail").append('<tr class="tr_pro">' 
								+ '<td>' + data[i].pd_nm + '</td><td>'
								+ data[i].s_or_dt1 + ' ~ '+data[i].s_or_dt2 + '</td><td>'
								+ data[i].s_th_dt + '</td><td>'
								+ data[i].pd_price + '</td></tr>');
						
						var test = new Object();
						
						test.user_id = data[i].user_id;
						test.s_or_dt1 = data[i].s_or_dt1;
						test.s_or_dt2 = data[i].s_or_dt2;
						test.s_th_dt = data[i].s_th_dt;
						
						testList.push(test);
						
						
					}
					
					
					var total_price = 0;
					for(var i = 0; i< data.length; i++ ){
						
						total_price = parseInt(total_price) + parseInt(data[i].pd_price);
					}

					$(".user_nm").text(' : ' + user_nm);
					$(".ph_no").text(' : '+ ph_no);
					
					$(".total_price").text(' : ' + total_price + '원');
					
				
				}
			
				//출력해야될부분
				jsonData = JSON.stringify(testList);
				console.log(jsonData);

				$('.success').click(function(){
					var context = "[Together]\n"
				  		context += "예약 해주셔서 감사합니다.\n"
				  		context += "[" + user_nm + "] 고객님께서 예약이 접수처리가 완료되었습니다.\n"
				  		context += "■ 예약일시 : ["+or_dt+"]\n"
				  		context += "■ 매장명 : ["+etp_nm+"]\n"
				  		context += "■ 접수내역 : ["+pr+"]\n"
				  		context += "■ 가격: ["+total_price + "원]\n"
				  		context += "\n "
				  			
				  	
				  		console.log(context);
				if(book_stat == "예약 대기중"){			
					if(check == "미확인"){
							$.ajax({
								type : "POST",
								url : "/etpFuneralOrderListCheck",
								data : JSON.stringify(testList),
								contentType: 'application/json; charset=utf-8',
								success : function(data){
									if(data == "success"){
								
										
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
									}
								}
							});
						
					
					}else if(check == "확인"){
						$('.bP').bPopup().close();
					}
				}if(book_stat == "취소 대기중"){
					var context = "[Together]\n"
				  		context += "예약 해주셔서 감사합니다.\n"
				  		context += "[" + user_nm + "] 고객님께서 예약이 접수처리가 취소되었습니다.\n"
				  		context += "■ 예약일시 : ["+or_dt+"]\n"
				  		context += "■ 매장명 : ["+etp_nm+"]\n"
				  		context += "■ 접수내역 : ["+pr+"]\n"
				  		context += "■ 가격: ["+total_price + "원]\n"
				  		context += "감사합니다. "
				  			
				  			$.ajax({
								type : "POST",
								url : "/etpFuneralOrderListcancle",
								data : JSON.stringify(testList),
								contentType: 'application/json; charset=utf-8',
								success : function(data){
									if(data == "success"){
								
										
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
									}
								}
							});		
				}
					
					
				});
				
				
				
			} // success END
		});// ajax END

		
//확인 닫기 버튼 클릭 시		
		
		
		$('.exit').on('click',function(){
			$('.tr_pro').text('');

			$('.bP').bPopup().close();
			
		});
		
		
		
	});