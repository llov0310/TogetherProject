package com.together.mapper;


import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.together.domain.DogsVO;
import com.together.domain.EnterpriseVO;
import com.together.domain.EtpListVO;
import com.together.domain.MemberVO;
import com.together.domain.Paging;
import com.together.domain.Search;

public interface AdminMapper {

   // 업체 신청 수락을 위한 함수 맵핑
   public int etpApplyManage_01(@Param("user_id") String user_id);
   
   // 업체 신청 거절을 위한 함수 맵핑
   public int etpApplyManage_02(@Param("user_id") String user_id);
   
   // 업체 신청 수락 시 수락 일시 추가 함수 맵핑
   public int etpApplyManage_03(@Param("user_id") String user_id);
   
   // 업체 삭제를 위한 함수 맵핑
   public int etpListDelete(String user_id);
   
   // 회원관리 페이지 넘버를 계산하기 위해 사용하는 함수 맵핑
   public int memberPageNum();
   
   // 반려견 관리 페이지 넘버를 계산하기 위해 사용하는  함수 맵핑
   public int dogsPageNum();
   
   // 업체 신청 관리 페이지 넘버를 계산하기 위해 사용하는 함수 맵핑
   public int etpPageNum();
   
   // 업체 리스트 페이지 넘버를 계산하기 위해 사용하는 함수 맵핑
   public int etpListPageNum();

   // 회원 정보를 가져오는 함수 맵핑 (페이징)
   public ArrayList<MemberVO> memberList(Paging p);
   
   // 반려견 정보를 가져오는 함수 맵핑 (페이징)
   public ArrayList<DogsVO> dogsList(Paging p);
   
   // 업체 정보를 가져오는 함수 맵핑 (페이징)
   public ArrayList<EnterpriseVO> enterpriseManage(Paging p);
   
   // 업체 리스트를 가져오는 함수 맵핑 (페이징)
   public ArrayList<EtpListVO> enterpriseList(Paging p);
   
   // 관리자 홈페이지 : 회원수를 가져오는 함수 맵핑
   public int memberCnt();
   
   // 관리자 홈페이지 : 업체 신청 수를 가져오는 함수 맵핑
   public int etpApplyCnt();
   
   // 관리자 홈페이지 : 반려견 수를 가져오는 함수 맵핑
   public int dogsCnt();
   
   // 관리자 홈페이지 : 등록 된 업체 수를 가져오는 함수 맵핑
   public int etpCnt();
   
   // 관리자 홈페이지 : 월 별 가입자 수를 가져오는 함수 맵핑 
   public ArrayList<MemberVO> monthMemberCnt(String year);
   
   // 관리자 홈페이지 : 도넛 차트를 그리기 위해 회원 생년월일 + 숫자를 가져오는 함수 맵핑
   public ArrayList<MemberVO> memberAge();
   
   // 회원정보 검색을 위한 맵핑
   public ArrayList<MemberVO> memberSearch(Search s);

   // 회원정보 검색 결과 맵핑
   public ArrayList<MemberVO> memberSearchResult(Map<Object, Object> parm);
   
   // 반려견 정보 검색을 위한 맵핑
   public ArrayList<DogsVO> dogsSearch(Search s);
   
   // 반려견 정보 검색 결과 맵핑
   public ArrayList<DogsVO> dogsSearchResult(Map<Object, Object> parm);

   // 업체 신청 검색을 위한 맵핑
   public ArrayList<EnterpriseVO> etpApplySearch(Search s);
	
   // 업체 신청 검색 결과 맵핑
   public ArrayList<EnterpriseVO> etpApplySearchResult(Map<Object, Object> parm);
   
   // 업체 리스트 검색을 위한 맵핑
   public ArrayList<EtpListVO> etpListSearch(Search s);
   
   // 업체 리스트 검색 결과 맵핑
   public ArrayList<EtpListVO> etpListSearchResult(Map<Object, Object> parm);
   
}