// ajax 두번 호출 방지를 위해 변수 선언
// 설명 : 3. <head> 바깥에 사용할 경우에는 isloaded 변수를 정의해서 ready() 함수가 끝나는 부분에 true로 하고 강제적으로 1번만 호출될 수 있도록 한다.
var isloaded = false;
$(document).ready(function(){
	// ajax 두번 호출 방지를 위해 if문 선언
	if (isloaded) {

	return;

	}
	// 월 별 가입자 수를 담을 변수선언, json을 담을 변수 선언
	var monthMemberCnt = [];
	var json;
	
		$.ajax({
			url : "/lineChart_01",
			type : "POST",
			dataType : "json",
			success : function(data) {
//				alert("하이 ajax");
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
		
		// ajax 두번 호출 방지를 위해 true로 변경
		isloaded = true;
}); // function