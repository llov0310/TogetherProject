package com.together.service;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.together.domain.DogsVO;
import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.Paging;

public interface AdminService {
	
   // 회원정보를 얻어올 함수 선언
   public ArrayList<MemberVO> getMemberList();

//   // 페이징을 이용하여 회원정보를 얻어올 함수 선언
//   public ArrayList<MemberVO> memberList(@Param("startNum") int startNum, @Param("endNum") int endNum);
//   //public int memberCount();
   
   // 진짜 페이징 처리
   public ArrayList<MemberVO> memberList(Paging p);

   public int getPageNum();
   
   
   // 업체 리스트를 가져올 함수 선언
   public ArrayList<EnterpriseVO> getEnterpriseList();
   
   // 반려견 리스트를 가져올 함수 선언
   public ArrayList<DogsVO> getDogsList();


   // 업체 신청 수락을 위한 함수 추가
   public int etpApplyManage_01(String user_id);

   // 업체 신청 거절을 위한 함수 추가
   public int etpApplyManage_02(String user_id);
   
   

}