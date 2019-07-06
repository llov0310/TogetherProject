package com.together.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.together.domain.MemberVO;
import com.together.service.AdminService;
import com.together.service.CustomerService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class SignController {

	private CustomerService customerService;
	private AdminService adminService;

	// 회원가입
//	@RequestMapping(value = "/sign", method = RequestMethod.POST)
//	public String signup(Model model, MemberVO ins, HttpServletRequest request) throws IOException {
//		int insert = customerService.signup(ins);
//
//		if (insert != 0) {
//			String user_id = ins.getUser_id();
//			String password = ins.getPassword();
//			String ph_no = ins.getPh_no();
//
//			String user_nm = ins.getUser_nm();
//			int authority_no = ins.getAuthority_no();
//			String birth_dt = ins.getBirth_dt();
//
//			ArrayList<MemberVO> arr = adminService.register_dt(user_id);
//			String register_dt = arr.get(0).getRegister_dt().toString();
//
//			// ins.getRegister_dt();
//
//			String path = request.getSession().getServletContext().getRealPath("/");
//
//			// 서버 올릴 때 경로
//			String firebasePath = path + "resources" + File.separator + "firebase" + File.separator
//					+ "blogapp-a9a56-firebase-adminsdk-v8z9o-c7af607772.json";
//
//			// ▼▼▼파이어베이스▼▼▼
//			FileInputStream serviceAccount = new FileInputStream(firebasePath);
//			// 수정 코드
//			FirebaseApp firebaseApp = null;
//			List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
//			if (firebaseApps != null && !firebaseApps.isEmpty()) {
//				for (FirebaseApp app : firebaseApps) {
//					if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
//						firebaseApp = app;
//					}
//				}
//			} else {
//				FirebaseOptions options = new FirebaseOptions.Builder()
//						.setCredentials(GoogleCredentials.fromStream(serviceAccount))
//						.setDatabaseUrl("https://blogapp-a9a56.firebaseio.com").build();
//
//				FirebaseApp.initializeApp(options);
//			}
//
//			// As an admin, the app has access to read and write all data, regardless of
//			// Security Rules
//			DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Member");
//			ref.addListenerForSingleValueEvent(new ValueEventListener() {
//				@Override
//				public void onDataChange(DataSnapshot dataSnapshot) {
//					Object document = dataSnapshot.getValue();
//					System.out.println("오브젝트 도큐먼트 확인 : " + document);
//				}
//
//				@Override
//				public void onCancelled(DatabaseError error) {
//				}
//			});
//			// ▲▲▲파이어베이스▲▲▲/
//			DatabaseReference useridRef = null;
//			useridRef = ref.child(user_id);
//
//			Map<String, Object> Map = new HashMap<>(); // 맵 생성하고
//			Map.put("user_id", user_id);
//			Map.put("password", password);
//			Map.put("ph_no", ph_no);
//
//			Map.put("user_nm", user_nm);
//			Map.put("register_dt", register_dt);
//			Map.put("authority_no", authority_no);
//			Map.put("birth_dt", birth_dt);
//
//			useridRef.setValueAsync(Map);
//
//			System.out.println("파베 테스트 : " + Map);
//
//			return "nav/login";
//		} else {
//			return "home";
//		}
//
//	}

	@RequestMapping(value = "/signTest", method = RequestMethod.POST)
	@ResponseBody
	public String signTest(MemberVO ins, HttpServletRequest request) throws IOException, FirebaseAuthException {
		String user_id; //파이어베이스에 가입한 유저 아이디를 담을 변수 선언
		String birth_dt;
		String bio;
		String ph_no;
		String user_nm;
		String f_uid;
		String imageurl;
		
		user_id = ins.getUser_id(); // ajax에서 보낸 user_id를 가져와서 user_id변수에 담음
		birth_dt = ins.getBirth_dt();
		bio = "";
		ph_no = ins.getPh_no();
		user_nm = ins.getUser_nm();
		imageurl = "https://firebasestorage.googleapis.com/v0/b/blogapp-a9a56.appspot.com/o/users_photos%2Fprofile.png?alt=media&token=a112f73c-373f-41ba-bd0f-dfea8ac8d6a1";
		
		String path = request.getSession().getServletContext().getRealPath("/");

		// 서버 올릴 때 경로
		String firebasePath = path + "resources" + File.separator + "firebase" + File.separator
				+ "blogapp-a9a56-firebase-adminsdk-v8z9o-c7af607772.json";

		// ▼▼▼파이어베이스▼▼▼
		FileInputStream serviceAccount = new FileInputStream(firebasePath);
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

		// As an admin, the app has access to read and write all data, regardless of
		// Security Rules
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
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
		// ▲▲▲파이어베이스▲▲▲/
		
		UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(user_id);
		// See the UserRecord reference doc for the contents of userRecord.
		System.out.println("Successfully fetched user data: " + userRecord.getUid());
		
		f_uid = userRecord.getUid(); //파이어베이스에 저장된 아이디의 Uid를 가져와 f_uid 변수에 담는다
		ins.setF_uid(f_uid); // 오라클 DB에 넣기위해 set으로 MeberVO의 f_uid에 uid를 저장
		
		System.out.println(ins);
		System.out.println(ins.getUser_id());
		System.out.println(ins.getF_uid());
		
//		// 파이어베이스 DB에 인서트하는 부분 시작
		DatabaseReference useridRef = null;
		useridRef = ref.child(f_uid); //이름이 아닌 uid가 들어가야됨

		Map<String, Object> Map = new HashMap<>(); // 맵 생성하고

		Map.put("bio", bio);
		Map.put("birth_dt", birth_dt);
		Map.put("id", f_uid);
		Map.put("imageurl", imageurl);
		Map.put("ph_no", ph_no);
		Map.put("username", user_nm);
		
		useridRef.setValueAsync(Map);
		System.out.println("파베 테스트 : " + Map);
		// 파이어베이스 DB에 인서트하는 부분 끝
		
		int insert = customerService.signup(ins);

		if(insert != 0) {
			return "success";
		}else {
			return "fail";
		}
		
	}

}
