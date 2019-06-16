$('.tr_list').on('click',function(){
		var or_dt = $(this).find('label').eq(0).text(); // 이용 시간 : 시작  
		var or_dt2 = $(this).find('label').eq(1).text();  // 이용 시간 : 끝
		var etp_cd = $(this).find('td').eq(8).text(); // 업체 코드
		var check = $(this).find('td').eq(6).find('label').text(); // 확인유무
		
		// 팝업창
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

					$(".total_price").text(' : ' + total_price + '원');
					
				
				}else{
					
					$(".selectDetail").empty();

					for(var i = 0; i< data.length; i++ ){
				
						$(".selectDetail").append('<tr class="tr_pro">' 
								+ '<td>' + data[i].pd_nm + '</td><td>'
								+ data[i].s_or_dt1 + ' ~ '+data[i].s_or_dt2 + '</td><td>'
								+ data[i].s_th_dt + '</td><td>'
								+ data[i].pd_price + '</td></tr>');
						
					}
					
					
					var total_price = 0;
					for(var i = 0; i< data.length; i++ ){
						
						total_price = parseInt(total_price) + parseInt(data[i].pd_price);
					}

					$(".total_price").text(' : ' + total_price + '원');
					
					
				}
	
				//출력해야될부분
				jsonData = JSON.stringify(testList);
				console.log(jsonData);

				$('.success').click(function(){
					
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
									location.reload();
									return false;
									}
								}
							});
						
						
					}else if(check == "확인"){
						$('.bP').bPopup().close();
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