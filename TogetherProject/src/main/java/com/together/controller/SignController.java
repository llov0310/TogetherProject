package com.together.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	public String signup(Model model, MemberVO ins, HttpServletRequest request) throws IOException {
		String user_id = ins.getUser_id();
		String password = ins.getPassword();
		String user_nm = ins.getUser_nm();
		
//		private String ph_no;
//		private String email;
//		private String user_nm;
//		private Timestamp register_dt;
//		private int authority_no;
//		private String birth_dt;
//		private int sign_no;
//		private String addr_ji;
//		private String addr_dong;
		
		String path = request.getSession().getServletContext().getRealPath("/");
//		System.out.println(path + "resources\\firebase\\blogapp-a9a56-firebase-adminsdk-v8z9o-c7af607772.json");

		// 서버 올릴 때 경로
		String firebasePath = path + "resources"+ File.separator +"firebase" + File.separator + "blogapp-a9a56-firebase-adminsdk-v8z9o-c7af607772.json";
		
		//▼▼▼파이어베이스▼▼▼
		FileInputStream serviceAccount = new FileInputStream(firebasePath);
		
		
		
		// /resources/firebase/blogapp-a9a56-firebase-adminsdk-v8z9o-c7af607772.json
		// C:\\firebase/blogapp-a9a56-firebase-adminsdk-v8z9o-c7af607772.json
//		FirebaseOptions options = new FirebaseOptions.Builder()
//		    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
//		    .setDatabaseUrl("https://blogapp-a9a56.firebaseio.com")
//		    .build();
//
//		FirebaseApp.initializeApp(options);

		
		// 수정 코드
		FirebaseApp firebaseApp = null;
		List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
		if (firebaseApps != null && !firebaseApps.isEmpty()) {
			for (FirebaseApp app : firebaseApps) {
				if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
					firebaseApp = app;
				}
			}
		} else {
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://blogapp-a9a56.firebaseio.com").build();

			FirebaseApp.initializeApp(options);
		}
		
		
		
		// As an admin, the app has access to read and write all data, regardless of Security Rules
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Member");

		System.out.println("ref에 뭐가 찍힐까? : "+ref);
		ref.addListenerForSingleValueEvent(new ValueEventListener() {
		  @Override
		  public void onDataChange(DataSnapshot dataSnapshot) {
		    Object document = dataSnapshot.getValue();
		    System.out.println("오브젝트 도큐먼트 확인 : " + document);
		  }

		  @Override
		  public void onCancelled(DatabaseError error) {
		  }
		});
		//▲▲▲파이어베이스▲▲▲
		
		DatabaseReference useridRef = null;
		useridRef = ref.child(user_id);
		
		Map<String, Object> Map = new HashMap<>(); // 맵 생성하고
		Map.put("user_id", user_id);
		Map.put("password", password);
		Map.put("user_nm", user_nm);
		
		useridRef.setValueAsync(Map);

		
		System.out.println("파베 테스트 : "+ Map );
		
		
		int insert = customerService.signup(ins);

		if (insert != 0) {
			return "nav/login";
		} else {
			return "home";
		}

	}
}
