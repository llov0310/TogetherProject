package com.together.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.together.domain.MemberVO;
import com.together.service.CustomerService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class SignController {
	
	private CustomerService customerService;

	// 회원가입
	@RequestMapping(value = "/sign", method = RequestMethod.POST)
	public String signup(Model model, MemberVO ins) throws IOException {
		String user_id = ins.getUser_id();
		
		
		//▼▼▼파이어베이스▼▼▼
		FileInputStream serviceAccount = new FileInputStream("C:\\firebase/blogapp-a9a56-firebase-adminsdk-v8z9o-c7af607772.json");
		//resources/firebase/blogapp-a9a56-firebase-adminsdk-v8z9o-c7af607772.json
		FirebaseOptions options = new FirebaseOptions.Builder()
		    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
		    .setDatabaseUrl("https://blogapp-a9a56.firebaseio.com")
		    .build();

		FirebaseApp.initializeApp(options);

		// As an admin, the app has access to read and write all data, regardless of Security Rules
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Member");

		System.out.println("ref에 뭐가 찍힐까? : "+ref);
		ref.addListenerForSingleValueEvent(new ValueEventListener() {
		  @Override
		  public void onDataChange(DataSnapshot dataSnapshot) {
		    Object document = dataSnapshot.getValue();
		    System.out.println(document);
		  }

		  @Override
		  public void onCancelled(DatabaseError error) {
		  }
		});
		//▲▲▲파이어베이스▲▲▲
		
		DatabaseReference useridRef = null;
		useridRef = ref.child(user_id);
		
		Map<String, Object> Map = new HashMap<>();
		Map.put("user_id", user_id);
		
		useridRef.setValueAsync(Map);
		
		//useridRef.child("user_id").setValueAsync(user_id);
//		useridRef.setValueAsync(user_id);
		
		System.out.println("파베 테스트 : "+ Map );
		
		
		int insert = customerService.signup(ins);

		if (insert != 0) {
			return "nav/login";
		} else {
			return "home";
		}

	}
}
