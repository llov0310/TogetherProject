package com.together.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.service.MyPageService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MyPageController {
	private MyPageService myPageService;

	// 패스워드 값을 비교하고 회원 정보를 수정하는 페이지로 가는 구문
	@RequestMapping(value = "/myPagePassCk", method = RequestMethod.POST)
	@ResponseBody
	public String myPagePassCk(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam String password, @RequestParam String user_id) {
		ArrayList<MemberVO> passWordCk = new ArrayList<MemberVO>();

		passWordCk = myPageService.passWordCk(user_id, password);

		//배열 사이즈가 0이 아닐 때
		if(passWordCk.size() != 0) {
			String userId_ck = passWordCk.get(0).getUser_id(); // ArrayList에서 아이디를 가져옴
			String passWord_ck = passWordCk.get(0).getPassword(); // ArrayList에서 비밀번호를 가져옴
			
			// 아이디와 비밀번호가 일치하면 성공
			if (userId_ck.equals(user_id) && passWord_ck.equals(password)) {
				return "success";
			} else {
				return "fail";

			}
		}else { //배열에 담긴것이 아무것도 없으면 실패
			return "fail";
		}


	}

	// 홈 페이지 맵핑
	@RequestMapping(value = "/myPageInfoEdit", method = RequestMethod.GET)
	public String myPageInfoEdit(Model model) {
		
		return "myPage/myPageInfoEdit";
	}
	
	@RequestMapping(value = "/editBtn", method = RequestMethod.GET)
	@ResponseBody
	public String etpProductAddRegister(HttpServletRequest request, Model model, @RequestParam String userImg_path) {
		
		//세션에서 user_id를 가져옴
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		
		// 업데이트 구문 : 해당 아이디의 이메일, 휴대폰번호, 주소, 프로필 이미지 경로를 수정 함
		int memberUpdate = myPageService.memberUpdate(user_id, userImg_path);
		
		return "success";
	}

}
