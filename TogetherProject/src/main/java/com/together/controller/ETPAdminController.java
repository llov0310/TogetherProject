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
import com.together.domain.ProductVO;
import com.together.service.ETPAdminService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class ETPAdminController {
	private ETPAdminService etpAdminService;

	// 업체 관리자 홈 페이지 맵핑
	@RequestMapping(value = "/etpAdminHome", method = RequestMethod.GET)
	public String home(Model model) {

		return "etpAdmin/etpAdminHome";
	}

	// 업체 관리자 - 주문 현황
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

	// 업체 관리자 - 상품 정보
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

	// 업체 관리자 - 상품 정보 : 상품 추가 팝업창(Add버튼 클릭시 새창 띄움)
	@RequestMapping(value = "/etpProductAddPage", method = RequestMethod.GET)
	public String etpProductAddPage(Model model) {

		return "etpAdmin/etpProductAddPage";
	}

	// 업체 관리자 - 상품 정보 : 팝업창 상품 추가 버튼
	@RequestMapping(value = "/etpProductAddRegister", method = RequestMethod.GET)
	@ResponseBody
	public String etpProductAddRegister(HttpServletRequest request, Model model, @RequestParam String pd_nm,
			@RequestParam int pd_price, @RequestParam String pd_content, @RequestParam String pd_num, @RequestParam String pd_img_path) {

		String id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();

		ArrayList<EnterpriseVO> info = etpAdminService.info_select(id);

		String code = info.get(0).getEtp_cd();

		int product_insert = etpAdminService.insert_pro(code, pd_nm, pd_price, pd_content, pd_img_path); // 이미지 업로드가되면 추가로 넣을예정

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

	// 업체 관리자 - 상품 정보 : 상품 제거
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

	// 업체 관리자 - 업체 정보 수정 페이지 이동
	@RequestMapping(value = "/etpInfo", method = RequestMethod.GET)
	public String etpInfo(Model model, HttpSession session, HttpServletRequest request) {
		
		String sess = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		
		ArrayList<EnterpriseVO> ent = new ArrayList<EnterpriseVO>();
		
		ent = etpAdminService.textbox(sess);
		
		model.addAttribute("list", ent);
		
		return "etpAdmin/etpInfo";
	}
	
	 // 업체 관리자 - 업체 정보 수정 페이지 : 수정버튼 작동
	   @RequestMapping(value = "/etpUpdate", method=RequestMethod.POST)
	   @ResponseBody
	   public String etpUpdate(Model model,@RequestParam String etp_nm,
			   @RequestParam String etp_if_info,@RequestParam String etp_if_intro
			   ,@RequestParam String etp_addr,@RequestParam String etp_ph_no
			   ,@RequestParam String etp_license_no,@RequestParam String etp_email,
			   @RequestParam String time1,@RequestParam String time2,@RequestParam String etp_cd,
			   @RequestParam String etp_if_img_path) {
		   
		   Integer update = etpAdminService.update(etp_nm,etp_addr,etp_ph_no,etp_license_no,etp_email,etp_cd);
		   Integer update2 = etpAdminService.update2(etp_if_info,etp_if_intro,time1,time2,etp_cd,etp_if_img_path);
		   
		    
		   
		   return "tq";		
		   
	   }
	
}
