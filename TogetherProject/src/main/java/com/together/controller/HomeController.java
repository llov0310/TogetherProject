package com.together.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.service.CustomerService;

import lombok.AllArgsConstructor;



/**
 * Handles requests for the application home page.
 */
@Controller
@AllArgsConstructor
public class HomeController {
	
	private CustomerService customerservice;
	
	
   //홈 페이지 맵핑
   @RequestMapping(value = "/", method=RequestMethod.GET)
   public String home(Model model, HttpServletRequest request) throws IOException, FirebaseAuthException {
		//ins.getRegister_dt();

		String path = request.getSession().getServletContext().getRealPath("/");

		// 서버 올릴 때 경로
		String firebasePath = path + "resources"+ File.separator +"firebase" + File.separator + "blogapp-a9a56-firebase-adminsdk-v8z9o-c7af607772.json";
		
		//▼▼▼파이어베이스▼▼▼
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

		// As an admin, the app has access to read and write all data, regardless of Security Rules
		DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Member");
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
		//▲▲▲파이어베이스▲▲▲/
		UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail("t@gmail.com");

		System.out.println("Successfully fetched user data: " + userRecord.getUid());
		System.out.println("가져와지나? : " + userRecord.getUid());
	   
		//petcode를 통해 uid값을 가져옴
       
		
	   return "home";
   }
   
   //서비스 페이지 맵핑
   @RequestMapping(value = "/services", method=RequestMethod.GET)
   public String services(Model model) {
	   return "nav/services";
   }
   
   //로그인 페이지 맵핑
   @RequestMapping(value = "/login", method=RequestMethod.GET)
   public String login(Model model) {
	   
	   return "nav/login";
   }
   
   //회원가입 페이지 맵핑
   @RequestMapping(value = "/signup", method=RequestMethod.GET)
   public String signup(Model model) {
	   
	   return "nav/signup";
   }
   
   //호텔, 병원, 장례, 추모게시판 같이 있는 페이지 맵핑
   @RequestMapping(value = "/page", method=RequestMethod.GET)
   public String menuPage(Model model) {
	   
	   return "service/page";
   }
   
   //호텔 페이지 맵핑
   @RequestMapping(value = "/hotel", method=RequestMethod.GET)
   public String hotel(Model model) {
	   
	   return "service/hotel";
   }
   
   //호텔 페이지 관련 이동 맵핑 정보
   @RequestMapping(value = "/hotelserch", method=RequestMethod.GET)
   public String hotelserch(Model model ,HttpServletRequest request,HttpSession session ,@RequestParam String toAddress
		   		,@RequestParam String checkin, @RequestParam String checkout) {
	   
	   
	   ArrayList<EnterpriseVO> result = new ArrayList<EnterpriseVO>();
	   String in = checkin.substring(5);
	   String out = checkout.substring(5);
	   

		String[] AddressList = {"서울","인천","여수","대구","강릉",
		"전주","대전","통영","남해","거제","광주","울산","수원"
		,"평창","춘천","가명","태안","제주도"};

			for(int i=0; i<AddressList.length; i++) {
	
			   if(toAddress.equals(AddressList[i])) {
				   String Address = AddressList[i];
				   result = customerservice.ser(Address,in,out);   
			   }
			   
			 }
			
			if(result.size() == 0) {
				String Serch = toAddress;
				result = customerservice.ser2(Serch,in,out);
			}
		
	   model.addAttribute("etp_list", result);
	   
	  return  "service/hotel/hotelserch";
   }
   
   //펫 장례 페이지 맵핑
   @RequestMapping(value = "/goodbye1", method=RequestMethod.GET)
   public String goodbye1(Model model) {
	   
	   return "service/goodbye1/goodbye1_home";
   }
   
   //추모 게시판 페이지 맵핑
   @RequestMapping(value = "/memorialGallery", method=RequestMethod.GET)
   public String memorialGallery(Model model) {
	   
	   return "service/memorial/memorialGallery";
   }
   
   //마이 페이지 맵핑 (예전거)
   @RequestMapping(value = "/mypage", method=RequestMethod.GET)
   public String mypage(Model model) {
	   
	   return "nav/mypage";
   }
   
   // 마이 페이지 맵핑
   @RequestMapping(value = "/myPageHome", method=RequestMethod.GET)
   public String myPageHome(Model model) {
	   
	   return "myPage/myPageHome";
   }
   
   //업체 신청 페이지 맵핑
   @RequestMapping(value = "/etpApply", method=RequestMethod.GET)
   public String etpApply(Model model, HttpSession session, HttpServletRequest request) {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		System.out.println(user_id);
	 
	   return "nav/etpApply";
   
   }
}



