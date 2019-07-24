	  const messaging = firebase.messaging();
	  messaging.requestPermission()
	  .then(function(){
			  console.log("오우케이");
			  return messaging.getToken();
		  }).then(function(token) {
			  console.log(token);
		})
	  .catch(function(err) {
		  console.log("댓다마");
	  })