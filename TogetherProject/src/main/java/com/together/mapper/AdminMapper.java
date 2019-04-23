package com.together.mapper;


import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.together.domain.DogsVO;
import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.Paging;

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
   
//   // 페이징 관련 함수 추가▼▼▼▼▼▼▼▼
//   // 전체 자료(회원수) 개수 가져오기
//   public ArrayList<MemberVO> memberList(@Param("startNum") int startNum, @Param("endNum") int endNum);
//   // 멤버 전체 수 조회
//   public int memberCount();
//   // 페이징 관련 함수 추가▲▲▲▲▲▲▲▲
   
   // 진짜 페이징
   public ArrayList<MemberVO> memberList(Paging p);
   public int getPageNum();
   
}