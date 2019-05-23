package com.together.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.together.domain.DogsVO;
import com.together.domain.EnterpriseVO;
import com.together.domain.EtpListVO;
import com.together.domain.MemberVO;
import com.together.domain.Paging;
import com.together.domain.Search;
import com.together.service.AdminService;

import lombok.AllArgsConstructor;
import net.sf.json.JSONArray;

@Controller
@AllArgsConstructor
public class AdminController {
	private AdminService adminService;

	// 관리자 홈 페이지 맵핑
	@RequestMapping(value = "/adminHome", method = RequestMethod.GET)
	public String adminHome(Model model) {

		int memberCnt = adminService.memberCnt(); // 총 회원수를 담기 위한 변수 선언
		int etpApplyCnt = adminService.etpApplyCnt(); // 총 업체 신청를 담기 위한 변수 선언
		int dogsCnt = adminService.dogsCnt();// 반려견 수를 담기 위한 변수 선언
		int etpCnt = adminService.etpCnt(); // 등록 된 업체 수를 담기 위한 변수 선언

//		System.out.println(memberCnt + "멤버수 : 어드민 컨트롤러");
//		System.out.println(etpApplyCnt + "업체 신청 수 : 어드민 컨트롤러");
//		System.out.println(dogsCnt + "반려견 수 : 어드민 컨트롤러");
//		System.out.println(etpCnt + "반려견 수 : 어드민 컨트롤러");

		model.addAttribute("etpApplyCnt", etpApplyCnt);
		model.addAttribute("memberCnt", memberCnt);
		model.addAttribute("dogsCnt", dogsCnt);
		model.addAttribute("etpCnt", etpCnt);

		return "admin/adminHome";
	}

	// 회원관리 페이징 맵핑 (페이징 적용)
	@RequestMapping(value = "/memberManage" + "/{num}", method = RequestMethod.GET)
	public String memberManage(@PathVariable String num, Model model, HttpSession session) {

		Paging page = new Paging();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		int pageNum = 0;
		int mapNum = 0;
		int sendPageNum = 0;
		int realNum = Integer.parseInt(num);

		page.setTotalNum(adminService.memberPageNum());
		// OnePageBorad => 한 페이지에 보여줄 멤버(글) 수
		if (page.getTotalNum() <= page.getOnePageBoard()) { // totalnum이 10보다 작으면 pageNum을 1로 설정
			pageNum = 1;
		} else { // totalnum이 더 클 경우
			pageNum = page.getTotalNum() / page.getOnePageBoard(); // totalnum / 10
																	// ex) 21/ 10 = 2가 pageNum에 들어감
			if (page.getTotalNum() % page.getOnePageBoard() > 0) { // 그리고 21 % 10 나머지 1이 0보다 크기때문에 pageNum에 2+1=3이 들어감
				pageNum = pageNum + 1;
			}
		}

		// 추가내용
		if (pageNum % 5 != 0) {
			mapNum = pageNum / 5 + 1;
		} else {
			mapNum = pageNum / 5;
		}

		for (int i = 0; i < mapNum; i++) {
			arr = new ArrayList<Integer>();
			for (int j = 0; j < 5; j++) {

				if ((i * 5) + j + 1 > pageNum) {
					break;
				}

				arr.add((i * 5) + j + 1);
			}
			map.put(i, arr);
		}

		sendPageNum = (realNum - 1) / 5;

		page.setEndNum((realNum * 10) + 1);
		page.setStartNum(page.getEndNum() - 10);

		// 추가내용
		model.addAttribute("pageNum", map.get(sendPageNum));
		model.addAttribute("memberList", adminService.memberList(page));

		// 추가내용

		if (realNum > pageNum) {
			System.out.println("pageNum : " + pageNum);
			return "redirect:/memberManage/" + pageNum;
		}

		return "admin/memberManage";
	}

	// 회원관리 페이지 : 회원 검색
	@RequestMapping(value = "/memberManage" + "/search" + "/{page}" + "/{searchType}"
			+ "/{keyword}", method = RequestMethod.GET)
	public String memberSearch(@PathVariable int page, @PathVariable String searchType, @PathVariable String keyword,
			Model model) {
		Paging p = new Paging();
		Search s = new Search();
		ArrayList<MemberVO> searchArr = new ArrayList<MemberVO>();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		Map<Object, Object> parm = new HashMap<Object, Object>();
		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();

		s.setPage(page);
		s.setKeyword(keyword);
		s.setSearchType(searchType);

		System.out.println(page); // 현재 페이지 번호
		System.out.println(searchType); // 검색 옵션
		System.out.println(keyword); // 검색 키워드

		searchArr = adminService.memberSearch(s);

		System.out.println("사용자 관리 검색 :" + searchArr);

		int pageNum = 0;
		int mapNum = 0;
		int sendPageNum = 0;
		int realNum = page;
		p.setTotalNum(searchArr.size());

		//

		if (p.getTotalNum() <= p.getOnePageBoard()) {
			pageNum = 1;
		} else {
			pageNum = p.getTotalNum() / p.getOnePageBoard();
			if (p.getTotalNum() % p.getOnePageBoard() > 0) {
				pageNum = pageNum + 1;
			}
		}

		if (pageNum % 5 != 0) {
			mapNum = pageNum / 5 + 1;
		} else {
			mapNum = pageNum / 5;
		}

		for (int i = 0; i < mapNum; i++) {
			arr = new ArrayList<Integer>();
			for (int j = 0; j < 5; j++) {

				if ((i * 5) + j + 1 > pageNum) {
					break;
				}

				arr.add((i * 5) + j + 1);
			}
			map.put(i, arr);
		}

		p.setEndNum((realNum * 10) + 1);
		p.setStartNum(p.getEndNum() - 10);

		parm.put("Paging", p);
		parm.put("Search", s);

		model.addAttribute("pageNum", map.get(sendPageNum));
		model.addAttribute("memberSearch", adminService.memberSearchResult(parm));
		System.out.println("사용자 관리검색 결과 :" + adminService.memberSearchResult(parm));

		for (int i = 0; i < adminService.memberSearchResult(parm).size(); i++) {
			System.out.println(adminService.memberSearchResult(parm).get(i).getUser_id());
		}

		if (realNum > pageNum) {
			System.out.println("pageNum : " + pageNum);
			System.out.println("keyword : " + keyword);
			try {
				keyword = URLEncoder.encode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:/memberManage/search/" + pageNum + "/" + searchType + "/" + keyword + "";
		}

		return "admin/memberManage";
	}

	// 업체 관리 페이지 (페이징)
	@RequestMapping(value = "/enterpriseManage" + "/{num}", method = RequestMethod.GET)
	public String enterpriseManage(@PathVariable String num, Model model, HttpSession session) {
		Paging page = new Paging();
		int pageNum = 0;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int realNum = Integer.parseInt(num);
		page.setTotalNum(adminService.etpPageNum());
		// 추가내용
		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		int mapNum = 0;
		int sendPageNum = 0;

		// OnePageBorad => 한 페이지에 보여줄 멤버(글) 수
		if (page.getTotalNum() <= page.getOnePageBoard()) { // totalnum이 10보다 작으면 pageNum을 1로 설정
			pageNum = 1;
		} else { // totalnum이 더 클 경우
			pageNum = page.getTotalNum() / page.getOnePageBoard(); // totalnum / 10
																	// ex) 21/ 10 = 2가 pageNum에 들어감
			if (page.getTotalNum() % page.getOnePageBoard() > 0) { // 그리고 21 % 10 나머지 1이 0보다 크기때문에 pageNum에 2+1=3이 들어감
				pageNum = pageNum + 1;
			}
		}

		// 추가내용
		if (pageNum % 5 != 0) {
			mapNum = pageNum / 5 + 1;
		} else {
			mapNum = pageNum / 5;
		}

		for (int i = 0; i < mapNum; i++) {
			arr = new ArrayList<Integer>();
			for (int j = 0; j < 5; j++) {

				if ((i * 5) + j + 1 > pageNum) {
					break;
				}

				arr.add((i * 5) + j + 1);
			}
			map.put(i, arr);
		}

		sendPageNum = (realNum - 1) / 5;

		page.setEndNum((realNum * 10) + 1);
		page.setStartNum(page.getEndNum() - 10);

		// model.addAttribute("pageNum", map.get(sendPageNum));

		model.addAttribute("pageNum", map.get(sendPageNum));
		model.addAttribute("enterpriseManage", adminService.enterpriseManage(page));

		// 추가내용

		if (realNum > pageNum) {
			System.out.println("pageNum : " + pageNum);
			return "redirect:/enterpriseManage/" + pageNum;
		}

		return "admin/enterpriseManage";
	}
	
//	// 업체신청 관리 페이지 : 상세정보(새창 띄움)
//	@RequestMapping()
//	public String etpDetail() {
//		return null;
//	}
	
	// 업체신청 관리 페이지 : 업체신청 검색
	@RequestMapping(value = "/enterpriseManage" + "/search" + "/{page}" + "/{searchType}"
			+ "/{keyword}", method = RequestMethod.GET)
	public String etpApplySearch(@PathVariable int page, @PathVariable String searchType, @PathVariable String keyword,
			Model model) {
		Paging p = new Paging();
		Search s = new Search();
		ArrayList<EnterpriseVO> searchArr = new ArrayList<EnterpriseVO>();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		Map<Object, Object> parm = new HashMap<Object, Object>();
		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();

		s.setPage(page);
		s.setKeyword(keyword);
		s.setSearchType(searchType);

		System.out.println(page); // 현재 페이지 번호
		System.out.println(searchType); // 검색 옵션
		System.out.println(keyword); // 검색 키워드

		searchArr = adminService.etpApplySearch(s);

		System.out.println("사용자 관리 검색 :" + searchArr);

		int pageNum = 0;
		int mapNum = 0;
		int sendPageNum = 0;
		int realNum = page;
		p.setTotalNum(searchArr.size());

		//

		if (p.getTotalNum() <= p.getOnePageBoard()) {
			pageNum = 1;
		} else {
			pageNum = p.getTotalNum() / p.getOnePageBoard();
			if (p.getTotalNum() % p.getOnePageBoard() > 0) {
				pageNum = pageNum + 1;
			}
		}

		if (pageNum % 5 != 0) {
			mapNum = pageNum / 5 + 1;
		} else {
			mapNum = pageNum / 5;
		}

		for (int i = 0; i < mapNum; i++) {
			arr = new ArrayList<Integer>();
			for (int j = 0; j < 5; j++) {

				if ((i * 5) + j + 1 > pageNum) {
					break;
				}

				arr.add((i * 5) + j + 1);
			}
			map.put(i, arr);
		}

		p.setEndNum((realNum * 10) + 1);
		p.setStartNum(p.getEndNum() - 10);

		parm.put("Paging", p);
		parm.put("Search", s);

		model.addAttribute("pageNum", map.get(sendPageNum));
		model.addAttribute("etpApplySearch", adminService.etpApplySearchResult(parm));
		System.out.println("사용자 관리검색 결과 :" + adminService.etpApplySearchResult(parm));

		for (int i = 0; i < adminService.etpApplySearchResult(parm).size(); i++) {
			System.out.println(adminService.etpApplySearchResult(parm).get(i).getUser_id());
		}

		if (realNum > pageNum) {
			System.out.println("pageNum : " + pageNum);
			System.out.println("keyword : " + keyword);
			try {
				keyword = URLEncoder.encode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:/enterpriseManage/search/" + pageNum + "/" + searchType + "/" + keyword + "";
		}

		System.out.println("서치타입확인" + s.getSearchType());
		return "admin/enterpriseManage";
	}

	// 업체 신청 수락 및 거절 맵핑
	@RequestMapping(value = "/etpApplyManage", method = RequestMethod.POST)
	@ResponseBody
	public String etpApplyManage(Model model, HttpSession session, @RequestBody String param) {
		List<Map<String, Object>> etpApplyMap = new ArrayList<Map<String, Object>>();
		etpApplyMap = JSONArray.fromObject(param);
		String user_id = null;
		int update = 0;
		int delete = 0;
		int accept_dt_update = 0;
		
		
		System.out.println(etpApplyMap);
		System.out.println("수락 및 거절 여부 : " + etpApplyMap.get(0).get("etpCk"));
		System.out.println(etpApplyMap.get(0).get("user_id"));
		System.out.println(etpApplyMap.size());

		if (etpApplyMap.get(0).get("etpCk").equals("수락")) {
			for (int i = 0; i < etpApplyMap.size(); i++) {
				user_id = (String) etpApplyMap.get(i).get("user_id");
				update = adminService.etpApplyManage_01(user_id);
				accept_dt_update = adminService.etpApplyManage_03(user_id);
			}
			if (update != 0) {
				System.out.println("넘어는 오시는건가요?");
				return "success1";
			} else {
				return "fail1";
			}

		} else if (etpApplyMap.get(0).get("etpCk").equals("거절")) {
			for (int i = 0; i < etpApplyMap.size(); i++) {
				user_id = (String) etpApplyMap.get(i).get("user_id");
				delete = adminService.etpApplyManage_02(user_id);
			}
			if (delete != 0) {
				System.out.println("넘어는 오시는건가요?" + "혹시이거니?");
				return "success2";
			} else {
				return "fail2";
			}
		}

		return "all_fail";
	}

	// 업체 목록 (페이징 적용)
	@RequestMapping(value = "/enterpriseList" + "/{num}", method = RequestMethod.GET)
	public String enterpriseList(@PathVariable String num, Model model, HttpSession session) {
		Paging page = new Paging();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		int pageNum = 0;
		int mapNum = 0;
		int sendPageNum = 0;
		int realNum = Integer.parseInt(num);

		page.setTotalNum(adminService.etpListPageNum());
		if (page.getTotalNum() <= page.getOnePageBoard()) {
			pageNum = 1;
		} else {
			pageNum = page.getTotalNum() / page.getOnePageBoard();
			if (page.getTotalNum() % page.getOnePageBoard() > 0) {
				pageNum = pageNum + 1;
			}
		}
		
		// 추가내용
		if (pageNum % 5 != 0) {
			mapNum = pageNum / 5 + 1;
		} else {
			mapNum = pageNum / 5;
		}

		for (int i = 0; i < mapNum; i++) {
			arr = new ArrayList<Integer>();
			for (int j = 0; j < 5; j++) {

				if ((i * 5) + j + 1 > pageNum) {
					break;
				}

				arr.add((i * 5) + j + 1);
			}
			map.put(i, arr);
		}

		sendPageNum = (realNum - 1) / 5;

		page.setEndNum((realNum * 10) + 1);
		page.setStartNum(page.getEndNum() - 10);

		model.addAttribute("pageNum", map.get(sendPageNum));
		model.addAttribute("enterpriseList", adminService.enterpriseList(page));

		if (realNum > pageNum) {
			System.out.println("pageNum : " + pageNum);
			return "redirect:/enterpriseList/" + pageNum;
		}

		return "admin/enterpriseList";
	}

	// 업체 목록 페이지 : 업체 정보 검색
	@RequestMapping(value = "/enterpriseList" + "/search" + "/{page}" + "/{searchType}"
			+ "/{keyword}", method = RequestMethod.GET)
	public String etpListSearch(@PathVariable int page, @PathVariable String searchType, @PathVariable String keyword,
			Model model) {
		Paging p = new Paging();
		Search s = new Search();
		ArrayList<EtpListVO> searchArr = new ArrayList<EtpListVO>();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		Map<Object, Object> parm = new HashMap<Object, Object>();
		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();

		s.setPage(page);
		s.setKeyword(keyword);
		s.setSearchType(searchType);

		System.out.println(page); // 현재 페이지 번호
		System.out.println(searchType); // 검색 옵션
		System.out.println(keyword); // 검색 키워드

		searchArr = adminService.etpListSearch(s);

		System.out.println("사용자 관리 검색 :" + searchArr);

		int pageNum = 0;
		int mapNum = 0;
		int sendPageNum = 0;
		int realNum = page;
		p.setTotalNum(searchArr.size());

		//

		if (p.getTotalNum() <= p.getOnePageBoard()) {
			pageNum = 1;
		} else {
			pageNum = p.getTotalNum() / p.getOnePageBoard();
			if (p.getTotalNum() % p.getOnePageBoard() > 0) {
				pageNum = pageNum + 1;
			}
		}

		if (pageNum % 5 != 0) {
			mapNum = pageNum / 5 + 1;
		} else {
			mapNum = pageNum / 5;
		}

		for (int i = 0; i < mapNum; i++) {
			arr = new ArrayList<Integer>();
			for (int j = 0; j < 5; j++) {

				if ((i * 5) + j + 1 > pageNum) {
					break;
				}

				arr.add((i * 5) + j + 1);
			}
			map.put(i, arr);
		}

		p.setEndNum((realNum * 10) + 1);
		p.setStartNum(p.getEndNum() - 10);

		parm.put("Paging", p);
		parm.put("Search", s);

		model.addAttribute("pageNum", map.get(sendPageNum));
		model.addAttribute("etpListSearch", adminService.etpListSearchResult(parm));
		System.out.println("사용자 관리검색 결과 :" + adminService.etpListSearchResult(parm));

		for (int i = 0; i < adminService.etpListSearchResult(parm).size(); i++) {
			System.out.println(adminService.etpListSearchResult(parm).get(i).getUser_id());
		}

		if (realNum > pageNum) {
			System.out.println("pageNum : " + pageNum);
			System.out.println("keyword : " + keyword);
			try {
				keyword = URLEncoder.encode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:/enterpriseList/search/" + pageNum + "/" + searchType + "/" + keyword + "";
		}

		System.out.println("서치타입확인" + s.getSearchType());
		return "admin/enterpriseList";
	}

	// 업체 리스트 : 업체 삭제
	@RequestMapping(value = "/etpListDelete", method = RequestMethod.POST)
	@ResponseBody
	public String etpListDelete(Model model, HttpSession session, @RequestBody String param) {
		List<Map<String, Object>> etpListDeleteMap = new ArrayList<Map<String, Object>>();
		etpListDeleteMap = JSONArray.fromObject(param);
		String user_id = null;
		int update = 0;
		int delete = 0;

		System.out.println(etpListDeleteMap);
		System.out.println("수락 및 거절 여부 : " + etpListDeleteMap.get(0).get("etpCk"));
		System.out.println(etpListDeleteMap.get(0).get("user_id"));
		System.out.println(etpListDeleteMap.size());

		if (etpListDeleteMap.get(0).get("etpCk").equals("삭제")) {
			for (int i = 0; i < etpListDeleteMap.size(); i++) {
				user_id = (String) etpListDeleteMap.get(i).get("user_id");
				update = adminService.etpListDelete(user_id);
			}
			if (update != 0) {
				System.out.println("넘어는 오시는건가요?");
				return "success1";
			} else {
				return "fail1";
			}

		}
		return "all_fail";
	}
	
	
	
	
	
	
	
	
	// 반려견 관리 (페이징 적용)
	@RequestMapping(value = "/dogsManage" + "/{num}", method = RequestMethod.GET)
	public String dogsManage(@PathVariable String num, Model model, HttpSession session) {

		Paging page = new Paging();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
		int pageNum = 0;
		int mapNum = 0;
		int sendPageNum = 0;
		int realNum = Integer.parseInt(num);

		page.setTotalNum(adminService.dogsPageNum());
		if (page.getTotalNum() <= page.getOnePageBoard()) {
			pageNum = 1;
		} else {
			pageNum = page.getTotalNum() / page.getOnePageBoard();
			if (page.getTotalNum() % page.getOnePageBoard() > 0) {
				pageNum = pageNum + 1;
			}
		}

		// 추가내용
		if (pageNum % 5 != 0) {
			mapNum = pageNum / 5 + 1;
		} else {
			mapNum = pageNum / 5;
		}

		for (int i = 0; i < mapNum; i++) {
			arr = new ArrayList<Integer>();
			for (int j = 0; j < 5; j++) {

				if ((i * 5) + j + 1 > pageNum) {
					break;
				}

				arr.add((i * 5) + j + 1);
			}
			map.put(i, arr);
		}

		sendPageNum = (realNum - 1) / 5;

		page.setEndNum((realNum * 10) + 1);
		page.setStartNum(page.getEndNum() - 10);

		model.addAttribute("pageNum", map.get(sendPageNum));
		// sql 쿼리문
		model.addAttribute("dogsList", adminService.dogsList(page));

		// 추가내용

		if (realNum > pageNum) {
			System.out.println("pageNum : " + pageNum);
			return "redirect:/dogsManage/" + pageNum;
		}

		return "admin/dogsManage";
	}

	// 반려견 관리 페이지 : 반려견 정보 검색
	@RequestMapping(value = "/dogsManage" + "/search" + "/{page}" + "/{searchType}"
			+ "/{keyword}", method = RequestMethod.GET)
	public String dogsSearch(@PathVariable int page, @PathVariable String searchType, @PathVariable String keyword,
			Model model) {
		Paging p = new Paging();
		Search s = new Search();
		ArrayList<DogsVO> searchArr = new ArrayList<DogsVO>();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		Map<Object, Object> parm = new HashMap<Object, Object>();
		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();

		s.setPage(page);
		s.setKeyword(keyword);
		s.setSearchType(searchType);

		System.out.println(page); // 현재 페이지 번호
		System.out.println(searchType); // 검색 옵션
		System.out.println(keyword); // 검색 키워드

		searchArr = adminService.dogsSearch(s);

		System.out.println("사용자 관리 검색 :" + searchArr);

		int pageNum = 0;
		int mapNum = 0;
		int sendPageNum = 0;
		int realNum = page;
		p.setTotalNum(searchArr.size());

		//

		if (p.getTotalNum() <= p.getOnePageBoard()) {
			pageNum = 1;
		} else {
			pageNum = p.getTotalNum() / p.getOnePageBoard();
			if (p.getTotalNum() % p.getOnePageBoard() > 0) {
				pageNum = pageNum + 1;
			}
		}

		if (pageNum % 5 != 0) {
			mapNum = pageNum / 5 + 1;
		} else {
			mapNum = pageNum / 5;
		}

		for (int i = 0; i < mapNum; i++) {
			arr = new ArrayList<Integer>();
			for (int j = 0; j < 5; j++) {

				if ((i * 5) + j + 1 > pageNum) {
					break;
				}

				arr.add((i * 5) + j + 1);
			}
			map.put(i, arr);
		}

		p.setEndNum((realNum * 10) + 1);
		p.setStartNum(p.getEndNum() - 10);

		parm.put("Paging", p);
		parm.put("Search", s);

		model.addAttribute("pageNum", map.get(sendPageNum));
		model.addAttribute("dogsSearch", adminService.dogsSearchResult(parm));
		System.out.println("사용자 관리검색 결과 :" + adminService.dogsSearchResult(parm));

		for (int i = 0; i < adminService.dogsSearchResult(parm).size(); i++) {
			System.out.println(adminService.dogsSearchResult(parm).get(i).getUser_id());
		}

		if (realNum > pageNum) {
			System.out.println("pageNum : " + pageNum);
			System.out.println("keyword : " + keyword);
			try {
				keyword = URLEncoder.encode(keyword, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "redirect:/dogsManage/search/" + pageNum + "/" + searchType + "/" + keyword + "";
		}

		System.out.println("서치타입확인" + s.getSearchType());
		return "admin/dogsManage";
	}

	// 라인 차트 : 연도별 가입자 수를 나타냄 - Morrisjs 차트 사용
	@ResponseBody
	@RequestMapping(value = "/lineChart_01", method = RequestMethod.POST)
	public ArrayList<MemberVO> monthMemberCnt() {
		Date today = new Date(); // 현재 일시를 받아옴
		SimpleDateFormat date = new SimpleDateFormat("yyyy"); // 날짜 포맷을 yyyy로 변경 함
		String subStrYear = date.format(today); // yyyy 포맷으로 저장된 today를 year 변수에 담는다
		subStrYear = subStrYear.substring(2);
		String year = subStrYear;
//			System.out.println("현재 연도 : " + year);

//			System.out.println("로그인 Json 컨트롤러");
		return adminService.monthMemberCnt(year);
	}

	// 라인 차트 : 원하는 연도의 가입자 수를 보기 위해 선언
	@ResponseBody
	@RequestMapping(value = "/yearSelectChart", method = RequestMethod.POST)
	public ArrayList<MemberVO> monthMemberCnt(@RequestParam String year) {

		System.out.println(year);

		return adminService.monthMemberCnt(year);
	}

	// 도넛 차트 : 연령대별 가입자 수를 나타냄
	@ResponseBody
	@RequestMapping(value = "/donutChart_01", method = RequestMethod.POST)
	public ArrayList<MemberVO> memberAge() {

		return adminService.memberAge();
	}

}