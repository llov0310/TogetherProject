	$('.tr_list').on('click',function(){
		
		var t_day = $(this).find('td').eq(1).text(); //구매일
		var product_nm = $(this).find('td').eq(2).text(); //상품명
		var order_nm = $(this).find('td').eq(3).text(); //구매자명
		var order_ph = $(this).find('td').eq(4).text(); // 연락처
		var first_day = $(this).find('label').eq(0).text(); // 예약첫날
		var last_day = $(this).find('label').eq(1).text(); // 예약마지막날
		var book_stat = $(this).find('td').eq(6).find('label').text(); // 예약상태
		var check = $(this).find('td').eq(7).find('label').text(); // 확인유무
		var price = $(this).find('td').eq(8).text(); // 가격

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
		
		
		$('.success').click(function(){
			
			var day1 = $(this).parent().parent().find('.fir').text(); // 첫날
			var day2 = $(this).parent().parent().find('.la').text(); // 마지막
			var day_th = $(this).parent().parent().find('.this_day').text(); // 마지막
			var nm = $(this).parent().parent().find('.nm').text(); // 주문자이름
			var check = $(this).parent().parent().find('.check').text(); //체크유무
			var check_val = '1';
			
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
							
								// 닫기 전에 컨펌 실행
								if(confirm("주문자에게 주문 확인 SMS를 보내시겠습니까?")){
									
									var alarm_content = 'Together' + '\n'
									+ order_nm + '님의 예약이 완료 되었습니다.' + '\n'
									+ '주문 일시 : ' + t_day + '\n'
									+ '상품 명 : ' + product_nm + '\n'
									+ '예약 날짜 : ' + first_day + ' ~ ' + last_day
									
									console.log(alarm_content);
									console.log(order_ph);
									
									var query = {
										alarm_content : alarm_content,
										order_ph : order_ph
									}
									
									var data = JSON.stringify(query);
									
									$.ajax({
										type: "POST",
										url : "SMS_Service",
										data : data,
										contentType: 'application/json; charset=utf-8',
										success : function(data){
											if(data == "success"){
												alert("완료 되었습니다.");
										        window.location.href="/etpOrderList";
											}else{
												alert("SMS 서비스 오류");
											}
										}
										
									}); // ajax END  
							    }
								else{
									$('.bP').bPopup().close();
									window.location.href="/etpOrderList";
									//취소를 누르면 바로 window.location.href="/etpOrderList" 로 보내 면 될듯
								}
							return false;						
							}
						}
					});
				
				
			}else if(check == "확인"){
				$('.bP').bPopup().close();
				
				
			}
			
			
		});
		
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