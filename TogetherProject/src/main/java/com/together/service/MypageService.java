package com.together.service;
import java.util.ArrayList;

import com.together.domain.DogsVO;
import com.together.domain.MemberVO;
import com.together.domain.OrdersVO;
public interface MypageService {
	
	//비밀번호체크
	public ArrayList<MemberVO> passCheak(String user_id);
	//회원 정보보기
	public ArrayList<MemberVO> memberinfo(String user_id);
	//비밀번호 변경
	public Integer passNew(String user_id, String password);
	//회원정보수정
	public Integer infoNew(String user_id, String email, String addr_ji, String addr_dong, String phon);
	//개 리스트
	public ArrayList<DogsVO> petlist(String user_id);
	// 개추가
	public Integer addDog(String user_id, String d_nm, int d_gender, String d_kind, String d_content, String d_age);
	//개 삭제
	public Integer petdelete(String user_id, String d_nm);
	//개 정보 수정
	public Integer petup(String user_id, String d_nm, int d_gender, String d_kind, String d_content, String d_age);
	//주문리스트
	public ArrayList<OrdersVO> orderlist(String user_id);



}
