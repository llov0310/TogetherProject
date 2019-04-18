package com.together.service;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.together.domain.DogsVO;
import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;

public interface AdminService {
	
   // 회원정보를 얻어올 함수 선언
   public ArrayList<MemberVO> getMemberList();

   // 업체 리스트를 가져올 함수 선언
   public ArrayList<EnterpriseVO> getEnterpriseList();
   
   // 반려견 리스트를 가져올 함수 선언
   public ArrayList<DogsVO> getDogsList();


   // 업체 신청 수락을 위한 함수 추가
   public int etpApplyManage_01(String user_id);

   // 업체 신청 거절을 위한 함수 추가
   public int etpApplyManage_02(String user_id);

}