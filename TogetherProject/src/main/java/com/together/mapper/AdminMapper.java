package com.together.mapper;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.together.domain.DogsVO;
import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;

public interface AdminMapper {
   // 회원 리스트를 가져오기 위한 함수 추가
   public ArrayList<MemberVO> getMemberList();
   
   // 업체 리스트를 가져오기 위한 함수 추가
   public ArrayList<EnterpriseVO> getEnterpriseList();
   

   // 업체 신청 수락을 위한 함수 추가
   public int etpApplyManage_01(@Param("user_id") String user_id);
   
   // 업체 신청 거절을 위한 함수 추가
   public int etpApplyManage_02(@Param("user_id") String user_id);
   
   // 반려견 리스트를 가져오기 위한 함수 추가
   public ArrayList<DogsVO> getDogsList();
}