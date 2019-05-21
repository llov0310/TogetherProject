package com.together.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.together.domain.DogsVO;
import com.together.domain.MemberVO;
import com.together.domain.OrdersVO;
import com.together.domain.Paging;
import com.together.domain.PostVO;
import com.together.service.MypageService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MyPageController {

	private MypageService mypage;

	@RequestMapping(value = "/member_info", method = RequestMethod.GET)
	public String member_info(Model model, HttpSession session, HttpServletRequest request) {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		ArrayList<MemberVO> member_info = new ArrayList<MemberVO>();
		 member_info =  mypage.memberinfo(user_id);
		/* System.out.println("회원정보" + member_info); */
		 
		 model.addAttribute("memberinfo", member_info);
		 
		return "nav/mypage/member_info";

	}

	// 패스워드 값을 비교하는 구문
	@RequestMapping(value = "/member_pass_cheak", method = RequestMethod.POST)
	@ResponseBody
	public String member_pass_cheak(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam String password) {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		ArrayList<MemberVO> pass_cheak = new ArrayList<MemberVO>();
		System.out.println(password +"비밀번호1");
		System.out.println(user_id + "아이디");
		pass_cheak = mypage.passCheak(user_id);

		String a = pass_cheak.get(0).getPassword();

		if (a.equals(password)) {
			return "success";
		} else {
			return "fail";

		}

	}

	//강아지 리스트
	  @RequestMapping(value = "/mypet_info", method=RequestMethod.GET) 
	  public String mypet_info(Model model,HttpSession session,HttpServletRequest request){ 
	  String user_id = ((MemberVO)request.getSession().getAttribute("user")).getUser_id();
	  System.out.println(user_id+"개리스트"); 
	  ArrayList<DogsVO> pet_list = new ArrayList<DogsVO>();
	  pet_list =  mypage.petlist(user_id);
	 
	 
	  model.addAttribute("pet", pet_list);
	 
	 	  return "nav/mypage/mypet_info"; 
	 }
	  
		@RequestMapping(value = "/pet_delete", method = RequestMethod.POST)
		@ResponseBody
		public String pet_delete(Model model, HttpSession session, HttpServletRequest request,@RequestParam String d_nm) {
			
			String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
			System.out.println(user_id + "아이디");
			System.out.println(d_nm+"개이름");	
			Integer petdelete = mypage.petdelete(user_id, d_nm);
				return "success";
		}

		
	  @RequestMapping(value = "/mypet_add_info", method = RequestMethod.POST)
		@ResponseBody
		public String mypet_add_info(Model model, HttpSession session, HttpServletRequest request,
				@RequestParam String d_nm,@RequestParam int d_gender,@RequestParam String d_kind,@RequestParam String d_content,@RequestParam String d_age) {
		  String user_id = ((MemberVO)request.getSession().getAttribute("user")).getUser_id();
		  System.out.println(user_id+"개주인");
		  System.out.println(d_nm+"개이름");
		  System.out.println(d_gender+"개성별");
		  System.out.println(d_kind+"품종");
		  System.out.println(d_age+"나이");
		  System.out.println(d_content+"설명");
		  Integer adddog = mypage.addDog(user_id, d_nm, d_gender, d_kind, d_content,d_age);
		  System.out.println("====================");

			return "success";
		}



	@RequestMapping(value = "/mypet_add", method = RequestMethod.GET)
	public String mypet_add(Model model) {
		return "nav/mypage/mypet_add";
	}

	@RequestMapping(value = "/petinfo_up", method = RequestMethod.GET)
	public String petinfo_up(Model model){
		
		/* model.addAttribute("d_nm", d_nm); */
		return "nav/mypage/petinfo_up";
	}
	
	@RequestMapping(value = "/pet_info_up", method = RequestMethod.POST)
	@ResponseBody
	public String pet_info_up(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam String d_nm,@RequestParam int d_gender,@RequestParam String d_kind,@RequestParam String d_content,@RequestParam String d_age) {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		
		  System.out.println(user_id+"개주인");
		  System.out.println(d_nm+"개이름");
		  System.out.println(d_gender+"개성별");
		  System.out.println(d_kind+"품종");
		  System.out.println(d_age+"나이");
		  System.out.println(d_content+"설명");
		  System.out.println("개정보 변경");
	  Integer petup = mypage.petup(user_id, d_nm, d_gender, d_kind, d_content,d_age);
		return "success";
	}
	
	
	@RequestMapping(value = "/myreservation", method = RequestMethod.GET)
	public String myreservation(Model model, HttpSession session, HttpServletRequest request) {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		ArrayList<OrdersVO> order_list = new ArrayList<OrdersVO>();
		 order_list = mypage.orderlist(user_id);
		  model.addAttribute("order_list", order_list);
		  	System.out.println(user_id);
		return "nav/mypage/myreservation";

	}
	
//	@RequestMapping(value = "/myreservation", method = RequestMethod.GET)
//	public String myreservation(Model model) {
//		
//
//		Paging page = new Paging();
//		ArrayList<Integer> arr = new ArrayList<Integer>();
//		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
//		int pageNum = 0;
//		int mapNum = 0;
//		int sendPageNum = 0;
//		int realNum = Integer.parseInt(num);
//
//		page.setTotalNum(adminService.memberPageNum());
//		// OnePageBorad => 한 페이지에 보여줄 멤버(글) 수
//		if (page.getTotalNum() <= page.getOnePageBoard()) { // totalnum이 10보다 작으면 pageNum을 1로 설정
//			pageNum = 1;
//		} else { // totalnum이 더 클 경우
//			pageNum = page.getTotalNum() / page.getOnePageBoard(); // totalnum / 10
//																	// ex) 21/ 10 = 2가 pageNum에 들어감
//			if (page.getTotalNum() % page.getOnePageBoard() > 0) { // 그리고 21 % 10 나머지 1이 0보다 크기때문에 pageNum에 2+1=3이 들어감
//				pageNum = pageNum + 1;
//			}
//		}
//
//		// 추가내용
//		if (pageNum % 5 != 0) {
//			mapNum = pageNum / 5 + 1;
//		} else {
//			mapNum = pageNum / 5;
//		}
//
//		for (int i = 0; i < mapNum; i++) {
//			arr = new ArrayList<Integer>();
//			for (int j = 0; j < 5; j++) {
//
//				if ((i * 5) + j + 1 > pageNum) {
//					break;
//				}
//
//				arr.add((i * 5) + j + 1);
//			}
//			map.put(i, arr);
//		}
//
//		sendPageNum = (realNum - 1) / 5;
//
//		page.setEndNum((realNum * 10) + 1);
//		page.setStartNum(page.getEndNum() - 10);
//
//		// 추가내용
//		model.addAttribute("pageNum", map.get(sendPageNum));
//		model.addAttribute("memberList", adminService.memberList(page));
//
//		// 추가내용
//
//		if (realNum > pageNum) {
//			System.out.println("pageNum : " + pageNum);
//			return "redirect:/memberManage/" + pageNum;
//		}
//
//		return "nav/mypage/myreservation";
//	
//	}
	
	@RequestMapping(value = "/mypost", method = RequestMethod.GET)
	public String mypost(Model model, HttpSession session, HttpServletRequest request) {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		 ArrayList<PostVO> post_list = new ArrayList<PostVO>(); 
		 post_list = mypage.postlist(user_id);

		
		  model.addAttribute("post_list", post_list);
		return "nav/mypage/mypost";

	}
	
//	@RequestMapping(value = "/mypost", method = RequestMethod.GET)
//	public String mypost(Model model) {
//		return "nav/mypage/mypost";
//
//	}

	@RequestMapping(value = "/deleteaccount", method = RequestMethod.GET)
	public String DeleteAccount(Model model) {
		return "nav/mypage/DeleteAccount";

	}
	@RequestMapping(value = "/deletid", method = RequestMethod.POST)
	@ResponseBody
	public String Deleteid(Model model, HttpSession session, HttpServletRequest request,@RequestParam String user_id,@RequestParam String password) {
		ArrayList<MemberVO> pass_cheak = new ArrayList<MemberVO>();
		System.out.println(password +"비밀번호1");
		System.out.println(user_id + "아이디");
		pass_cheak = mypage.passCheak(user_id);
		String a = pass_cheak.get(0).getPassword();
		
		if (a.equals(password)) {
		Integer delacc =mypage.memberdel(user_id, password);
		System.out.println("끝");
		session.invalidate();
		return "success";
		
		} else {
		return "fail";
		}
	}
	
	
	@RequestMapping(value = "/newpwpopup", method = RequestMethod.GET)
	public String newpwpopup(Model model) {
		return "nav/mypage/newpwpopup";

	}

	// 비빌번호 변경

	@RequestMapping(value = "/member_pass_new", method = RequestMethod.POST)
	@ResponseBody
	public String member_pass_new(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam String password) {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		System.out.println(user_id+"변경확인아이디");
		System.out.println(password+"변경확인비번");

		Integer update = mypage.passNew(user_id, password);
		System.out.println(update);

		
		return "success";
	}

	// 회원 정보수정

	@RequestMapping(value = "/member_info_new", method = RequestMethod.POST)
	@ResponseBody
	public String member_info_new(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam String email, @RequestParam String addr_ji, @RequestParam String addr_dong,
			@RequestParam String phon) {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		System.out.println(user_id + "다시넘겨온 아이디");
		System.out.println(email + "이메일");
		System.out.println(phon + "폰");
		System.out.println(addr_ji + "주소1");
		System.out.println(addr_dong + "주소2");
		System.out.println("====================");
		Integer update = mypage.infoNew(user_id, email, addr_ji, addr_dong, phon);

		System.out.println(update);
		return "success";
	}

}
