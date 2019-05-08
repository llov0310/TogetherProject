$(function(){
	var monthMemberCnt = [];
	var json;
	
		$.ajax({
			url : "loginJson",
			type : "POST",
			dataType : "json",
			success : function(data) {
				alert("하이 ajax");
				console.log(data);
				console.log(data[0].count);
				console.log(data[0].month);
				
				for(i=0; i<data.length; i++){
					monthMemberCnt[i] = {
						month : data[i].month,
						count : data[i].count
					}
				}
				//파싱 해줘야함
				var obj = JSON.stringify(monthMemberCnt);
				json = JSON.parse(obj);

				new Morris.Line({
			        element: 'morrisChart',
				    data: json,
			        xkey: 'month',
			        ykeys: ['count'],
			        labels: ['value']			
				}); // morris차트
			},
			error : function() {
				alert("연결실패");
			}
		}); // ajax		
}); // function