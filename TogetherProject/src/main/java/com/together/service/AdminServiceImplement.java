package com.together.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.together.domain.DogsVO;
import com.together.domain.EnterpriseVO;
import com.together.domain.EtpListVO;
import com.together.domain.MemberVO;
import com.together.domain.Paging;
import com.together.domain.Search;
import com.together.mapper.AdminMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImplement implements AdminService {
	private AdminMapper mapper;

	//업체 신청 수락
	@Override
	public int etpApplyManage_01(String user_id) {
		// TODO Auto-generated method stub
		return mapper.etpApplyManage_01(user_id);
	}

	//업체 신청 거절
	@Override
	public int etpApplyManage_02(String user_id) {
		// TODO Auto-generated method stub
		return mapper.etpApplyManage_02(user_id);
	}
	
	//업체 신청 수락시 수락일시 추가
	@Override
	public int etpApplyManage_03(String user_id) {
		// TODO Auto-generated method stub
		return mapper.etpApplyManage_03(user_id);
	}
	

	//업체 삭제
	@Override
	public int etpListDelete(String user_id) {
		// TODO Auto-generated method stub
		return mapper.etpListDelete(user_id);
	}	
	
	
	// 회원관리 페이지 넘버를 계산하기 위해 사용하는 함수 선언 (페이징)
	@Override
	public int memberPageNum() {
		// TODO Auto-generated method stub
		return mapper.memberPageNum();
	}
	
	// 반려견 관리 페이지 넘버를 계산하기 위해 사용하는  함수 선언 (페이징)
	@Override
	public int dogsPageNum() {
		// TODO Auto-generated method stub
		return mapper.dogsPageNum();
	}
	
	// 업체 신청 관리 페이지 넘버를 계산하기 위해 사용하는 함수 선언 (페이징)
	@Override
	public int etpPageNum() {
		// TODO Auto-generated method stub
		return mapper.etpPageNum();
	}	
	
	// 업체 리스트 페이지 넘버를 계산하기 위해 사용하는 함수 선언 (페이징)
	@Override
	public int etpListPageNum() {
		// TODO Auto-generated method stub
		return mapper.etpListPageNum();
	}
	
	// 회원 정보를 가져오는 함수 (페이징 처리)
	@Override
	public ArrayList<MemberVO> memberList(Paging p) {
		// TODO Auto-generated method stub
		return mapper.memberList(p);
	}
	
	// 반려견 정보를 가져오는 함수 (페이징 처리)
	@Override
	public ArrayList<DogsVO> dogsList(Paging p) {
		// TODO Auto-generated method stub
		return mapper.dogsList(p);
	}

	// 업체 정보를 가져오는 함수 (페이징 처리)
	@Override
	public ArrayList<EnterpriseVO> enterpriseManage(Paging p) {
		// TODO Auto-generated method stub
		return mapper.enterpriseManage(p);
	}
	
	// 업체 리스트를 가져오는 함수 (페이징 처리)
	@Override
	public ArrayList<EtpListVO> enterpriseList(Paging p) {
		// TODO Auto-generated method stub
		return mapper.enterpriseList(p);
	}
	
	// 관리자 홈페이지 : 회원수를 가져오는 함수 추가
	@Override
	public int memberCnt() {
		// TODO Auto-generated method stub
		return mapper.memberCnt();
	}
	
	// 관리자 홈페이지 : 업체 신청 수를 가져오는 함수 추가
	@Override
	public int etpApplyCnt() {
		// TODO Auto-generated method stub
		return mapper.etpApplyCnt();
	}
	
	// 관리자 홈페이지 : 반려견 수를 가져오는 함수 추가
	@Override
	public int dogsCnt() {
		// TODO Auto-generated method stub
		return mapper.dogsCnt();
	}
	
	// 관리자 홈페이지 : 등록 된 업체 수를 가져오는 함수 추가
	@Override
	public int etpCnt() {
		// TODO Auto-generated method stub
		return mapper.etpCnt();
	}

	// 관리자 홈페이지 : 차트 사용을 위한 함수 추가
	@Override
	public ArrayList<MemberVO> monthMemberCnt(String year) {
		// TODO Auto-generated method stub
		return mapper.monthMemberCnt(year);
	}

	// 관리자 홈페이지 : 도넛 차트를 그리기 위해 회원 생년월일 + 숫자를 가져오는 함수 추가
	@Override
	public ArrayList<MemberVO> memberAge() {
		// TODO Auto-generated method stub
		return mapper.memberAge();
	}
	
	// 회원정보 검색을 위한 함수 추가
	@Override
	public ArrayList<MemberVO> memberSearch(Search s) {
		// TODO Auto-generated method stub
		return mapper.memberSearch(s);
	}
	
	// 회원정보 검색 결과
	@Override
	public ArrayList<MemberVO> memberSearchResult(Map<Object, Object> parm) {
		// TODO Auto-generated method stub
		return mapper.memberSearchResult(parm);
	}
	
	// 반려견 정보 검색을 위한 함수 추가
	@Override
	public ArrayList<DogsVO> dogsSearch(Search s) {
		// TODO Auto-generated method stub
		return mapper.dogsSearch(s);
	}
	
	// 반려견 정보 검색 결과
	@Override
	public ArrayList<DogsVO> dogsSearchResult(Map<Object, Object> parm) {
		// TODO Auto-generated method stub
		return mapper.dogsSearchResult(parm);
	}
	
	// 업체 신청 검색을 위한 함수 추가
	@Override
	public ArrayList<EnterpriseVO> etpApplySearch(Search s) {
		// TODO Auto-generated method stub
		return mapper.etpApplySearch(s);
	}
	
	// 업체 신청 검색 결과
	@Override
	public ArrayList<EnterpriseVO> etpApplySearchResult(Map<Object, Object> parm) {
		// TODO Auto-generated method stub
		return mapper.etpApplySearchResult(parm);
	}
	
	// 업체 리스트 검색을 위한 함수 추가
	@Override
	public ArrayList<EtpListVO> etpListSearch(Search s) {
		// TODO Auto-generated method stub
		return mapper.etpListSearch(s);
	}

	// 업체 리스트 검색 결과
	@Override
	public ArrayList<EtpListVO> etpListSearchResult(Map<Object, Object> parm) {
		// TODO Auto-generated method stub
		return mapper.etpListSearchResult(parm);
	}

	// 업체 신청 관리 : 클릭 시 상세 정보
	@Override
	public ArrayList<EnterpriseVO> etpDetail(String user_id) {
		
		return mapper.etpDetail(user_id);
	}

	// 관리자 홈페이지 : 회원 정지 시키기
	@Override
	public int memManage_01(String user_id) {
		// TODO Auto-generated method stub
		return mapper.memManage_01(user_id);
	}

	// 관리자 홈페이지 : 회원 정지 해제 시키기
	@Override
	public int memManage_02(String user_id) {
		// TODO Auto-generated method stub
		return mapper.memManage_02(user_id);
	}
	
	// 관리자 홈페이지 : 회원 탈퇴 시키기
	@Override
	public int memManage_03(String user_id) {
		// TODO Auto-generated method stub
		return mapper.memManage_03(user_id);
	}
	
	// 관리자 홈페이지 : 관리자 회원으로 변경
	@Override
	public int memManage_04(String user_id) {
		// TODO Auto-generated method stub
		return mapper.memManage_04(user_id);
	}
	
	// 가입 일시를 가져오는 함수 추가
	@Override
	public ArrayList<MemberVO> register_dt(String user_id) {
		// TODO Auto-generated method stub
		return mapper.register_dt(user_id);
	}

	@Override
	public ArrayList<EnterpriseVO> dimg(String etpcd) {
		// TODO Auto-generated method stub
		return mapper.dimg(etpcd);
	}


}