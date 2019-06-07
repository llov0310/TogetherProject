var path = null; // 파일 업로드를 위한 변수 선언

$(document).ready(function(){
	// 파이어베이스를 이용한 파일 업로드 부분
	// Your web app's Firebase configuration
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

	  // DOM요소 가져오기 - 개별적 변수 선언
	  // Get elements
	  var uploader = document.getElementById('uploader');
	  var fileButton = document.getElementById('fileButton');
	  
	  var uuid;
	  // Listen for file selection - 파일이 선택되었을 때 알 수 있도록 해야함
	  fileButton.addEventListener('change', function(e){
	  	//
	  	function guid() {
	    		function s4(){
	      	return Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
	    		}
	    			return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
	  	}
	  	uuid = guid();
	  	
	  	
	  	// 파일이 업로드될때 이 함수 안에서 호출 할 것임
	  	
	  	// Get file - 파일을 가져오기 위해 타깃 요소에 있는 파일 객체에서 입력할 것
	  	var file = e.target.files[0];
	  	
	  	// Create a storage ref - 스토리지 참조 생성
	  	// firebase.storage().ref('folder_name/file_name');
	  	
	  	var storageRef = firebase.storage().ref('etpRepresentativeImg/' + uuid); 	
	  	console.log(uuid);
	  	
	  	var storage = firebase.storage();
	  	var downloadRef = storage.ref();
		var starsRef = downloadRef.child('etpRepresentativeImg/' + uuid);
	  	
	  	// Upload file
	  	var task = storageRef.put(file);
	  	
	  	// Update progress bar
	  	task.on('state_changed',
	  		function progress(snapshot){
	  			var percentage = (snapshot.bytesTransferred / snapshot.totalBytes) * 100;
	  			uploader.value = percentage;
	  		},
	  		
	  		function error(err){
	  			
	  		},
	  		
	  		function complete(){
	  			// 해당 이미지의 파이어베이스의 download url을 가져옴
	  			starsRef.getDownloadURL().then(function(url) {
	  				// `url` is the download URL for 'images/stars.jpg'
	  				document.getElementById('imgsrccode').innerHTML = url;
	  				path = document.getElementById('imgsrccode').innerHTML;
	  				console.log(path);
	  				
	  				// 업로드 이미지 미리보기
	  				var productImg = document.getElementById('productImg');
	  				productImg.src = url;
	  			});
	  		}
	  	);
	  });	
	  	// 파일 업로드 끝

		$('#update').on('click' , function(){
			alert("클릭은되나;");
		var etp_cd = $("#code").text();
		var etp_nm = $("#etp_nm").val(); // 업체이름
		var etp_if_info = $("#etp_if_info").val(); // 간단 설명
		var etp_if_intro = $("#etp_if_intro").val(); // 업체 상세 설명
		var etp_addr = $("#etp_addr").val(); // 주소
		var etp_ph_no = $("#etp_ph_no").val(); // 전화번호
		var etp_license_no = $("#etp_license_no").val(); // 사업자 번호
		var etp_email = $("#etp_email").val(); // 이메일
		var time1 = $("#s1 option:selected").val().concat(":").concat($("#m1 option:selected").val());
		var time2 = $("#s2 option:selected").val().concat(":").concat($("#m2 option:selected").val());
		var etp_if_img_path = path;// 업체 대표 이미지 업로드 경로를 담는 변수		
		
				$.ajax({
					type : "POST",
					url : "/etpUpdate",
					data : {etp_nm,etp_cd,
							etp_if_info,
							etp_if_intro,
							etp_addr,
							etp_ph_no,
							etp_license_no,
							etp_email,
							time1,
							time2,
							etp_if_img_path},
					dataType : "text",
					success : function(){
						alert("수정이 완료 되었습니다.");
						window.location.href="/etpInfo";
						
					}
					
				});
	
		});
	
});