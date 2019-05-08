package com.together.mapper;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.together.domain.DogsVO;
import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.Paging;

public interface AdminMapper {

   // 업체 신청 수락을 위한 함수 추가
   public int etpApplyManage_01(@Param("user_id") String user_id);
   
   // 업체 신청 거절을 위한 함수 추가
   public int etpApplyManage_02(@Param("user_id") String user_id);
   
   // 페이징에 필요한 함수 맵핑
   public int getPageNum();
   
   // 회원 정보를 가져오는 함수 맵핑 (페이징)
   public ArrayList<MemberVO> memberList(Paging p);
   
   // 반려견 정보를 가져오는 함수 맵핑 (페이징)
   public ArrayList<DogsVO> dogsList(Paging p);
   
   // 업체 정보를 가져오는 함수 맵핑 (페이징)
   public ArrayList<EnterpriseVO> enterpriseList(Paging p);
   
   // 관리자 홈페이지 : 회원수를 가져오는 함수 맵핑
   public int memberCnt();
   
   // 관리자 홈페이지 : 업체 신청 수를 가져오는 함수 맵핑
   public int etpApplyCnt();
   
   // 관리자 홈페이지 : 반려견 수를 가져오는 함수 맵핑
   public int dogsCnt();
   
   // 관리자 홈페이지 : 월 별 가입자 수를 가져오는 함수 맵핑 
   public ArrayList<MemberVO> monthMemberCnt(String year);
   
}