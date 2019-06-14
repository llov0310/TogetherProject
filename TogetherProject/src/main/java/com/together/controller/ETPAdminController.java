package com.together.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.together.domain.EnterpriseVO;
import com.together.domain.MemberVO;
import com.together.domain.OrdersVO;
import com.together.domain.ProductVO;
import com.together.service.ETPAdminService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ETPAdminController {
	private ETPAdminService etpAdminService;

	// 업체 관리자 홈 페이지 맵핑
	@RequestMapping(value = "/etpAdminHome", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request, HttpSession session) {
		String sess = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		String etpKindCheck = (String) request.getSession().getAttribute("etpKindCheck");
		
		//관리자 홈페이지가 미구현이라 나중에 개발할예정
//		if(etpKindCheck.equals("h")) {
//			return "etpAdmin/etpAdminHome";
//		}else if(etpKindCheck.equals("f")){
//			return "etpFuneralAdmin/etpFuneralAdminHome";
//		}else if(etpKindCheck.equals("d")){
//			return "etpHospitalAdmin/etpHospitalAdminHome";
//		}
		
//		if(etpKindCheck.equals("h")) {
//			return "etpAdmin/etpInfo";
//		}else if(etpKindCheck.equals("f")){
//			return "etpAdmin/etpInfo";
//		}else if(etpKindCheck.equals("d")){
//			return "etpAdmin/etpInfo";
//		}
		
		
		
		return "redirect: etpInfo";
		
	}

	// 업체 관리자 - 주문 현황 : 호텔
	@RequestMapping(value = "/etpOrderList", method = RequestMethod.GET)
	public String etpOrderList(Model model, HttpServletRequest request, HttpSession session) {

		String sess = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		ArrayList<EnterpriseVO> orderlist = new ArrayList<EnterpriseVO>();

		ArrayList<EnterpriseVO> ent_list = new ArrayList<EnterpriseVO>();

		ent_list = etpAdminService.textbox(sess);

		String code = ent_list.get(0).getEtp_cd();

		orderlist = etpAdminService.select_order_list(code);

		model.addAttribute("orderLists", orderlist);

		return "etpAdmin/etpOrderList";
	}

	// 업체 관리자 - 주문한 (예약)상품 팝업창에서 확인버튼 클릭 시 업데이트  : 호텔
	@RequestMapping(value = "/etpOrderListCheck", method = RequestMethod.POST)
	@ResponseBody
	public String orderCheck(Model model, @RequestParam String day1, @RequestParam String day2, @RequestParam String nm,
			@RequestParam String check_val, @RequestParam String day_th) {

		ArrayList<OrdersVO> updateCheck = etpAdminService.newinfo(nm);

		String member_id = updateCheck.get(0).getUser_id();

		int up = etpAdminService.updateChecked(day1, day2, check_val, member_id, day_th);

		return "success";
	}

	// 업체 관리자 - 상품 정보  : 호텔
	@RequestMapping(value = "/etpProduct", method = RequestMethod.GET)
	public String etpProduct(Model model, HttpServletRequest request) {
		String id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();

		ArrayList<EnterpriseVO> info = etpAdminService.info_select(id);
		ArrayList<ProductVO> product = new ArrayList<ProductVO>();

		String code = info.get(0).getEtp_cd();

		product = etpAdminService.product_select(code);

		model.addAttribute("product_info", product);

		return "etpAdmin/etpProduct";
	}

	// 업체 관리자 - 상품 정보 : 상품 추가 팝업창(Add버튼 클릭시 새창 띄움)  : 호텔
	@RequestMapping(value = "/etpProductAddPage", method = RequestMethod.GET)
	public String etpProductAddPage(Model model) {

		return "etpAdmin/etpProductAddPage";
	}

	// 업체 관리자 - 상품 정보 : 팝업창 상품 추가 버튼  : 호텔
	@RequestMapping(value = "/etpProductAddRegister", method = RequestMethod.GET)
	@ResponseBody
	public String etpProductAddRegister(HttpServletRequest request, Model model, @RequestParam String pd_nm,
			@RequestParam int pd_price, @RequestParam String pd_content, @RequestParam String pd_num,
			@RequestParam String pd_img_path) {

		String id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();

		ArrayList<EnterpriseVO> info = etpAdminService.info_select(id);

		String code = info.get(0).getEtp_cd();

		int product_insert = etpAdminService.insert_pro(code, pd_nm, pd_price, pd_content, pd_img_path);

		ArrayList<ProductVO> select_product = etpAdminService.st_insert_pro(code, pd_nm);

		String pro_code = select_product.get(0).getPd_cd();

		for (int i = 5; i < 13; i++) {

			for (int f = 1; f <= 30; f++) {

				if (i < 10 && f < 10) {
					String total_code = pro_code + "-" + "0" + i + "-" + "0" + f;
					int stock_insert = etpAdminService.stockint(total_code, pro_code, pd_num);
				} else if (f < 10) {
					String total_code = pro_code + "-" + i + "-" + "0" + f;
					int stock_insert = etpAdminService.stockint(total_code, pro_code, pd_num);
				} else if (i < 10) {
					String total_code = pro_code + "-" + "0" + i + "-" + f;
					int stock_insert = etpAdminService.stockint(total_code, pro_code, pd_num);
				} else {
					String total_code = pro_code + "-" + i + "-" + f;
					int stock_insert = etpAdminService.stockint(total_code, pro_code, pd_num);
				}

				if (f == 30) {
					if (i == 5 || i == 7 || i == 8) {
						String total_code = pro_code + "-" + "0" + i + "-" + "31";
						int stock_insert = etpAdminService.stockint(total_code, pro_code, pd_num);
					} else if (i == 10 || i == 12) {
						String total_code = pro_code + "-" + i + "-" + "31";
						int stock_insert = etpAdminService.stockint(total_code, pro_code, pd_num);
					}

				}

			}
			if (i == 12) {
				return "success";
			}
		}

		return "success";
	}

	// 업체 관리자 - 상품 정보 : 상품 제거  : 호텔
	@RequestMapping(value = "/etpProductDel", method = RequestMethod.POST)
	@ResponseBody
	public String etpProductDel(Model model, HttpServletRequest request, @RequestParam String nm) {
		String id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();

		ArrayList<EnterpriseVO> info = etpAdminService.info_select(id);

		String code = info.get(0).getEtp_cd();

		int order_del = etpAdminService.del(code, nm);
		System.out.println(order_del);
		System.out.println("넘어옵니까?");
		return "success";
	}

	// 업체 관리자 - 업체 정보 수정 페이지 이동  : 호텔
	@RequestMapping(value = "/etpInfo", method = RequestMethod.GET)
	public String etpInfo(Model model, HttpSession session, HttpServletRequest request) {

		String sess = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();

		ArrayList<EnterpriseVO> ent = new ArrayList<EnterpriseVO>();

		ent = etpAdminService.textbox(sess);

		model.addAttribute("list", ent);

		return "etpAdmin/etpInfo";
	}

	// 업체 관리자 - 업체 정보 수정 페이지 : 수정버튼 작동  : 호텔
	@RequestMapping(value = "/etpUpdate", method = RequestMethod.POST)
	@ResponseBody
	public String etpUpdate(Model model, @RequestParam String etp_nm, @RequestParam String etp_if_info,
			@RequestParam String etp_if_intro, @RequestParam String etp_addr, @RequestParam String etp_ph_no,
			@RequestParam String etp_license_no, @RequestParam String etp_email, @RequestParam String time1,
			@RequestParam String time2, @RequestParam String etp_cd, @RequestParam String etp_if_img_path) {

		Integer update = etpAdminService.update(etp_nm, etp_addr, etp_ph_no, etp_license_no, etp_email, etp_cd);
		Integer update2 = etpAdminService.update2(etp_if_info, etp_if_intro, time1, time2, etp_cd, etp_if_img_path);

		return "tq";

	}
	
	
	// 장례 업체 관리자 - 상품 정보
	@RequestMapping(value = "/etpFuneralPro", method = RequestMethod.GET)
	public String etpFuneralPro(Model model, HttpServletRequest request) {
		String id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();

		ArrayList<EnterpriseVO> info = etpAdminService.info_select(id);
		ArrayList<ProductVO> product = new ArrayList<ProductVO>();

		String code = info.get(0).getEtp_cd();

		product = etpAdminService.product_select(code);

		model.addAttribute("product_info", product);

		return "etpFuneralAdmin/etpFuneralPro";
	}
	
	// 장례 업체 관리자 - 상품 정보 : 상품 추가 팝업창(Add버튼 클릭시 새창 띄움)
	@RequestMapping(value = "/etpFuneralProAddPage", method = RequestMethod.GET)
	public String etpFuneralProAddPage(Model model) {

		return "etpFuneralAdmin/etpFuneralProAddPage";
	}
	
	// 업체 관리자 - 상품 정보 : 팝업창 상품 추가 버튼  : 호텔
	@RequestMapping(value = "/etpFuneralProAddRegister", method = RequestMethod.GET)
	@ResponseBody
	public String etpFuneralProAddRegister(HttpServletRequest request, Model model, @RequestParam String pd_nm,
			@RequestParam int pd_price, @RequestParam String pd_content, @RequestParam String pd_img_path, @RequestParam String ca_cd) {
		//1. 세션에서 아이디를 가져옴
		String id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		//2. 그 아이디에 대한 업체 코드를 가져옴
		ArrayList<EnterpriseVO> info = etpAdminService.info_select(id);
		//3. 업체 코드를 code 변수에 저장
		String code = info.get(0).getEtp_cd();
		//4. insert할 때 code에 저장 한 업체 코드와 함께 sql문 실행
		int funeralProInsert = etpAdminService.funeralProInsert(code, pd_nm, pd_price, pd_content, pd_img_path, ca_cd); 
		
		return "success";
	}
	
	// 업체 관리자 - 주문 목록 : 장례 업체
	@RequestMapping(value = "/etpFuneralOrderList", method=RequestMethod.GET)
	public String etpFuneralOrderList(Model model, HttpServletRequest request, HttpSession session) {
		
		
		return "etpFuneralAdmin/etpFuneralOrderList";
	}
}
