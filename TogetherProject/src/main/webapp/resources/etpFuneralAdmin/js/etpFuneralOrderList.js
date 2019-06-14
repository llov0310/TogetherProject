	
	$('.tr_list').on('click',function(){
		
//		var t_day = $(this).find('td').eq(1).text(); //구매일
//		var product_nm = $(this).find('td').eq(2).text(); //상품명
//		var order_nm = $(this).find('td').eq(3).text(); //구매자명
//		var order_ph = $(this).find('td').eq(4).text(); // 연락처
//		var first_day = $(this).find('label').eq(0).text(); // 예약첫날
//		var last_day = $(this).find('label').eq(1).text(); // 예약마지막날
//		var book_stat = $(this).find('td').eq(6).find('label').text(); // 예약상태
//		var check = $(this).find('td').eq(7).find('label').text(); // 확인유무
//		var price = $(this).find('td').eq(8).text(); // 가격
		
		var or_dt = $(this).find('label').eq(0).text(); // 이용 시간 : 시작  
		var or_dt2 = $(this).find('label').eq(1).text();  // 이용 시간 : 끝 
		var etp_cd = $(this).find('td').eq(8).text(); // 업체 코드
		
		console.log(or_dt);
		console.log(or_dt2);
		console.log(etp_cd);
		
//		$.ajax({
//			url : "/etpFuneralOrderListDetail",
//			type : "POST",
//			dataType : "text",
//			data : {day1,day2,nm,check_val,day_th},
//			success : function(data){
//				if(data == "success"){
//					$('.this_day').text('');
//					$('.pro_nm').text('');
//					$('.nm').text('');
//					$('.ph').text('');
//					$('.fir').text('');
//					$('.la').text('');
//					$('.stat').text('');
//					$('.check').text('');
//					$('.to_price').text('');
//				$('.bP').bPopup().close();
//				location.reload();
//				return false;	
//				}
//			}
//		});
//		
//		
//		
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
		
		
		
		
		
		
		
		
//확인 닫기 버튼 클릭 시		
		$('.success').click(function(){
			
			var day1 = $(this).parent().parent().find('.fir').text(); // 첫날
			var day2 = $(this).parent().parent().find('.la').text(); // 마지막
			var day_th = $(this).parent().parent().find('.this_day').text(); // 마지막
			var nm = $(this).parent().parent().find('.nm').text(); // 주문자이름
			var check = $(this).parent().parent().find('.check').text(); //체크유무
			var check_val = '1';
			
			if(check == "미확인"){
					$.ajax({
						url : "/etpFuneralOrderListCheck",
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
							$('.bP').bPopup().close();
							location.reload();
							return false;
//							$('#bP').css('display', 'none');
							
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