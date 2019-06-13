package com.together.service;
import java.util.ArrayList;

import com.together.domain.DogsVO;
import com.together.domain.MemberVO;
import com.together.domain.OrdersVO;
import com.together.domain.PostVO;
import com.together.domain.DogsAttachVO;
public interface OldMypageService {
	
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
	//작성글 리스트
	public ArrayList<PostVO> postlist(String user_id);
	//회원탈퇴
	public Integer memberdel(String user_id, String password);
	//취소요청
	public Integer delorder(String or_cd, String or_stat);
	//파일 업로드 ...?
	//public Integer addFile(String da_uuid, String da_path,da_name,d_cd);
	public Integer addFile(String da_uuid, String da_path, String da_name, String d_cd);
	
	//파일업로드할때 d_cd를 받아오기 위한 서비스 함수
	public ArrayList<DogsVO> getD_cd(String user_id, String d_nm, int d_gender, String d_kind, String d_age);
	
	public ArrayList<OrdersVO> searchdate(String user_id, int day);

}
