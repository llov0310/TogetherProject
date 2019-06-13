var path = null;

$(document).ready(function(){
	  $(window).load(function() {     
	       $('#loading').hide();   
	      });  
	  
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
	  	
	  	var storageRef = firebase.storage().ref('productImgUpload/' + uuid); 	
	  	console.log(uuid);
	  	
	  	var storage = firebase.storage();
	  	var downloadRef = storage.ref();
		var starsRef = downloadRef.child('productImgUpload/' + uuid);
	  	
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
	  
	  
	 $("#register_Btn").on('click', function(){
		 var ca_cd = $("#ca_cd option:selected").val();// 카테고리 넘버
		 var pd_nm = $("#gdsName").val(); // 상품 이름
		 var pd_price = $("#gdsPrice").val(); // 상품 가격
		 var pd_content = $("#gdsDes").val(); // 상품 설명
//		 var pd_num = $("#gdsNum").val(); // 총갯수 수량
		 var pd_img_path = path; // 이미지 업로드를 위한 uuid 변수
		 
		 console.log(ca_cd);
	  if(ca_cd == "null"){
		  alert('카테고리를 선택해주세요!');
	  }else if(pd_nm == ""){
		  alert('상품 이름을 입력하세요!');
		  
	  }else if(pd_price == ""){
		  alert('상품 가격을 입력하세요!');
		 
	  }else if(pd_content == ""){
		  alert('상품 소개를 입력하세요!');
		  
	  }else if(pd_img_path == null){
		  alert('이미지를 등록해주세요!');		  
	  }else{
	  $("#loading").show();
	  $.ajax({
		 type : 'GET',
		 url : '/etpFuneralProAddRegister', // 컨트롤러 메소드 추가해야함 - sql문 수정해야 함
		 dataType : 'text',
		 data : {ca_cd, pd_nm, pd_price, pd_content, pd_img_path},
		 success : function(data){
			 $('#loading').hide();   
			 if(data == "success"){
				 
			 alert("상품 추가 완료");
			 window.opener.location.href = "/etpFuneralPro";	 
			 window.close();
		 }else{
			 alert("넘어오지않았음");
		 }
			 
			 
		 }
		 
		 
	  }); // ajax END
	  	
	  } 
	 }); 
  });





