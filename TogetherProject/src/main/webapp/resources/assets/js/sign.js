$(document).ready(function() {
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
});


// 로그인 정보 가져오는 jquery문
$("#signup").click(function() {
var user_id = $("#id_01").val() + "@" + $("#id_02").val();
var user_nm = $("#user_nm").val(); // 이름
var password = $("#password").val(); // 비밀번호
var ph_no = $("#ph_no").val(); // 휴대폰 번호
var birth_dt = $("#birth_dt").val(); // 생년월일 + 1

firebase.auth().createUserWithEmailAndPassword(user_id, password).catch(function(error) {
    // Handle Errors here.
    var errorCode = error.code;
    var errorMessage = error.message;
    // ...
    console.log(user_id);
    console.log(password);
    console.log("계정 생성 완료");
    alert("하이");
});

console.log("아이디 : " + user_id);
console.log("이름 : " + user_nm);
console.log("비밀번호 : " + password);
console.log("휴대폰 번호 : " + ph_no);
console.log("생년월일 : " + birth_dt);

var query = {
		user_id : user_id,
		user_nm : user_nm,
		password : password,
		ph_no : ph_no,
		birth_dt : birth_dt
};

console.log(query);

$.ajax({
	type : "POST",
	dataType : 'text',
	data : query,
	url : "/signTest",
	success : function(data) {
		console.log(data);
		alert("넘어옴");
	}
}); // ajax END


});