package com.together.service;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.together.domain.DogsVO;
import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.Paging;

public interface AdminService {
   // 페이징 처리에 필요한 함수
   public int getPageNum();
   
   // 회원정보를 가져오는 memberList 페이징 처리 함수 선언
   public ArrayList<MemberVO> memberList(Paging p);
   
   // 반려견 정보를 가져오는 dogsList 페이징 처리 함수 선언
   public ArrayList<DogsVO> dogsList(Paging p);
   
   
   
   // 업체 리스트를 가져올 함수 선언
   public ArrayList<EnterpriseVO> getEnterpriseList();
   
   // 반려견 리스트를 가져올 함수 선언
   public ArrayList<DogsVO> getDogsList();


   // 업체 신청 수락을 위한 함수 추가
   public int etpApplyManage_01(String user_id);

   // 업체 신청 거절을 위한 함수 추가
   public int etpApplyManage_02(String user_id);
   
   

}