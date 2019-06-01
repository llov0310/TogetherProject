package com.together.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.together.domain.AttachFileDTO;
import com.together.domain.DogsVO;
import com.together.domain.MemberVO;
import com.together.domain.OrdersVO;
import com.together.domain.Paging;
import com.together.domain.PostVO;
import com.together.service.BoardService;
import com.together.service.MypageService;
import lombok.extern.java.Log;
import lombok.AllArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;



@Log
@Controller
@AllArgsConstructor
public class MyPageController {
	
	
	
	//파일 업로드 하는 거
	
	/*여기다가 변수 만들거야*  필요한거 : (UUID)uuid, (File)uploadPath, uploadFileName*/
	/* 왜... 먼ㅇ;ㅣ럼ㄴ일
	UUID uuid=null;  //uuid 전역변수
	File uploadPath=null; //uploadPath 전역변수*/
	public static String uploadFileName=null; //uploadFileName 전역변수
	public static String uid=null; //uuid toString으로 한거 넣을 변수
	public static String path=null; //uploadPath toString으로 한거 넣을 변수

	
	//어...
	@GetMapping("board/register")
	public void register() {
		log.info("register 띄움");
	}
	//어...
	//mapper추가
	private BoardService boardservice;
	//mapper추가 

	// 년/월/일 폴더 생성
	private String getFoloder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
//	// 년/월/일 폴더 생성
	
	// 이미지 파일 판단
	private boolean checkImageType(File file) {
		try {
			String contentType = Files.probeContentType(file.toPath());

			return contentType.startsWith("image");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//이미지 파일 판단
	

	// AttachFileDTO의 리스트를 반환하는 구조임
	@PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile) {
		List<AttachFileDTO> list = new ArrayList<>();
		String uploadFolder = "C:\\upload";
		String uploadFolderPath = getFoloder();
		int count = 0;
		
		// make folder---
		File uploadPath = new File(uploadFolder, uploadFolderPath);

		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		} // end of if

		// make yyyy/MM/dd
		for (MultipartFile multipartFile : uploadFile) {
			AttachFileDTO attachDTO = new AttachFileDTO();
			uploadFileName = multipartFile.getOriginalFilename();

			// IE has file path
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("only file name : " + uploadFileName);
			attachDTO.setFileName(uploadFileName);

			UUID uuid = UUID.randomUUID();

			uploadFileName = uuid.toString() + "_" + uploadFileName;
			
			/*
			System.out.println(uuid + " ★★★★");
			System.out.println(uploadPath + " ★★★★");
			System.out.println(uploadFileName + " ★★★★");  //테스트-> 다 나옴
			*/
			
			
			try {
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);

				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				
				// check image type file
				if (checkImageType(saveFile)) {
					attachDTO.setImage(true);

					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));

					Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 100, 100);
					thumbnail.close();
				} // end of if

				uid = uuid.toString();
				path = uploadPath.toString();
				
				System.out.println("전역변수 테스트 : "+uid);
				System.out.println("전역변수 테스트 : "+path);
				System.out.println("전역변수 테스트 : "+uploadFileName);  //넘어옴
				
				if(count == 0) {
					//int uploadtest = boardservice.insert(uid, path, uploadFileName);  /*ㅂㄷㅂㄷ...*/
					count = count+1;
					System.out.println("count 체크" + count);
				}
				
				// 리스트에 더함
				list.add(attachDTO);			
			} catch (Exception e) {
				e.printStackTrace();
			} // end of catch
			
			
		} // end of for
		count = 0;
		System.out.println("여기는 0 초기화 후에 count" + count);
		
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	
	// AttachFileDTO의 리스트를 반환하는 구조임

	// 서버에서 섬네일을 get방식으로 가져옴 p526
	// 특정한 uri뒤에 파일 이름 추가하면 이미지 파일 데이터 가져와서 img태그를 작성하는 과정을 통해 처리
	@GetMapping("/display")
	@ResponseBody
	public ResponseEntity<byte[]> getFile(String fileName) {
		log.info("fileName : " + fileName);

		File file = new File("C:\\upload\\" + fileName);

		log.info("file : " + file);

		ResponseEntity<byte[]> result = null;
		try {
			HttpHeaders header = new HttpHeaders();

			header.add("Content-Type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		} // end of catch

		return result;
	}// end of response -getFile	
	
	//파일 업로드 하는 거
	
	
	
	private MypageService mypage;

	@RequestMapping(value = "/mainpage", method = RequestMethod.GET)
	public String mainpage(Model model, HttpSession session, HttpServletRequest request) {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();

	
		 
		return "nav/mypage/mainpage";

	}
	
	@RequestMapping(value = "/member_info", method = RequestMethod.GET)
	public String member_info(Model model, HttpSession session, HttpServletRequest request) {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		ArrayList<MemberVO> member_info = new ArrayList<MemberVO>();
		 member_info =  mypage.memberinfo(user_id);
		/* System.out.println("회원정보" + member_info); */
		 
		 model.addAttribute("memberinfo", member_info);
		 
		return "nav/mypage/member_info";

	}

	// 패스워드 값을 비교하는 구문
	@RequestMapping(value = "/member_pass_cheak", method = RequestMethod.POST)
	@ResponseBody
	public String member_pass_cheak(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam String password) {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		ArrayList<MemberVO> pass_cheak = new ArrayList<MemberVO>();
		System.out.println(password +"비밀번호1");
		System.out.println(user_id + "아이디");
		pass_cheak = mypage.passCheak(user_id);

		String a = pass_cheak.get(0).getPassword();

		if (a.equals(password)) {
			return "success";
		} else {
			return "fail";

		}

	}

	//강아지 리스트
	  @RequestMapping(value = "/mypet_info", method=RequestMethod.GET) 
	  public String mypet_info(Model model,HttpSession session,HttpServletRequest request){ 
	  String user_id = ((MemberVO)request.getSession().getAttribute("user")).getUser_id();
	  System.out.println(user_id+"개리스트"); 
	  ArrayList<DogsVO> pet_list = new ArrayList<DogsVO>();
	  pet_list =  mypage.petlist(user_id);
	 
	 
	  model.addAttribute("pet", pet_list);
	 
	 	  return "nav/mypage/mypet_info"; 
	 }
	  
		@RequestMapping(value = "/pet_delete", method = RequestMethod.POST)
		@ResponseBody
		public String pet_delete(Model model, HttpSession session, HttpServletRequest request,@RequestParam String d_nm) {
			
			String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
			System.out.println(user_id + "아이디");
			System.out.println(d_nm+"개이름");	
			Integer petdelete = mypage.petdelete(user_id, d_nm);
				return "success";
		}

		
	  @RequestMapping(value = "/mypet_add_info", method = RequestMethod.POST)
		@ResponseBody
		public String mypet_add_info(Model model, HttpSession session, HttpServletRequest request,
				@RequestParam String d_nm,@RequestParam int d_gender,@RequestParam String d_kind,@RequestParam String d_content,@RequestParam String d_age) {
		  String user_id = ((MemberVO)request.getSession().getAttribute("user")).getUser_id();
		  
		  //파일 업로드를 위한 변수 선언
		  String da_uuid = null;
		  String da_path = null;
		  String da_name = null;
		  String d_cd = null;
		  ArrayList<DogsVO> dcd = new ArrayList<DogsVO>();
		  //▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲▲
		  
		  System.out.println(user_id+"개주인");
		  System.out.println(d_nm+"개이름");
		  System.out.println(d_gender+"개성별");
		  System.out.println(d_kind+"품종");
		  System.out.println(d_age+"나이");
		  System.out.println(d_content+"설명");
		  Integer adddog = mypage.addDog(user_id, d_nm, d_gender, d_kind, d_content,d_age); // dogs 테이블에 인서트
		  
		  
		  dcd = mypage.getD_cd(user_id, d_nm, d_gender, d_kind, d_age);
		  
		  
		  
		  String code = dcd.get(0).getD_cd(); // 해당 user_id에 대한 d_cd를 가져오는 코드
		  System.out.println("d_cd 확인 : " + code);
		  
		  da_uuid = uid;
		  da_path = path;
		  da_name = uploadFileName;
		  d_cd = code;
		  
		
/*
		  System.out.println("파일 업로드 테스트: "+uploadFileName);
		  System.out.println("파일 업로드 테스트 : "+uid);
		  System.out.println("파일 업로드 테스트 : "+path);
*/
		  System.out.println("1 : "+da_uuid);
		  System.out.println("2 : "+da_path);
		  System.out.println("3 : "+da_name);
		  System.out.println("4 : "+d_cd);
		  
		  
		  Integer addFile = mypage.addFile(da_uuid, da_path, da_name, d_cd);
		  
		  
		  System.out.println("====================");

			return "success";
		}



	@RequestMapping(value = "/mypet_add", method = RequestMethod.GET)
	public String mypet_add(Model model) {
		return "nav/mypage/mypet_add";
	}

	@RequestMapping(value = "/petinfo_up", method = RequestMethod.GET)
	public String petinfo_up(Model model){
		
		/* model.addAttribute("d_nm", d_nm); */
		return "nav/mypage/petinfo_up";
	}
	
	@RequestMapping(value = "/pet_info_up", method = RequestMethod.POST)
	@ResponseBody
	public String pet_info_up(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam String d_nm,@RequestParam int d_gender,@RequestParam String d_kind,@RequestParam String d_content,@RequestParam String d_age) {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		
		  System.out.println(user_id+"개주인");
		  System.out.println(d_nm+"개이름");
		  System.out.println(d_gender+"개성별");
		  System.out.println(d_kind+"품종");
		  System.out.println(d_age+"나이");
		  System.out.println(d_content+"설명");
		  System.out.println("개정보 변경");
	  Integer petup = mypage.petup(user_id, d_nm, d_gender, d_kind, d_content,d_age);
		return "success";
	}
	
	
	@RequestMapping(value = "/myreservation", method = RequestMethod.GET)
	public String myreservation(Model model, HttpSession session, HttpServletRequest request) {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		ArrayList<OrdersVO> order_list = new ArrayList<OrdersVO>();

		 order_list = mypage.orderlist(user_id);
		 System.out.println(order_list); 
		 model.addAttribute("order_list", order_list);
		  
		  	System.out.println(user_id);
		return "nav/mypage/myreservation";

	}
	
	@RequestMapping(value = "/searchdate"+"/{day}", method = RequestMethod.GET)
	public String searchdate(Model model, HttpSession session, HttpServletRequest request,@PathVariable int day) {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		System.out.println(day);
		ArrayList<OrdersVO> searchdate = new ArrayList<OrdersVO>();
		searchdate = mypage.searchdate(user_id,day);
		model.addAttribute("searchdate", searchdate);
		System.out.println(searchdate);
		System.out.println("후우");
			return "nav/mypage/searchdate";

	}
	
	
//	@RequestMapping(value = "/searchdate", method = RequestMethod.GET)
//	public String searchdate(Model model, HttpSession session, HttpServletRequest request,@RequestParam int day) {
//		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
//		ArrayList<OrdersVO> searchdate = new ArrayList<OrdersVO>();
//
//		System.out.println(day);
//		searchdate = mypage.searchdate(user_id,day);
//		model.addAttribute("searchdate", searchdate);
//		System.out.println(searchdate);
//		return "nav/mypage/searchdate";
//
//	}
//	@RequestMapping(value = "/searchdate"+ "/search" + "/{day}", method = RequestMethod.GET)
//	public String searchdate(Model model, HttpSession session, HttpServletRequest request , @PathVariable int day) {
//		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
//		ArrayList<OrdersVO> order_list = new ArrayList<OrdersVO>();
// 
//		  
//		  	System.out.println(user_id);
//		return "nav/mypage/myreservation";
//
//	}
//	@RequestMapping(value = "/searchdate"+ "/search" + "/{day}", method = RequestMethod.GET)
//	public String searchdate(Model model, HttpSession session, HttpServletRequest request ,@PathVariable int day) {
//		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
//		ArrayList<OrdersVO> searchdate = new ArrayList<OrdersVO>();
//		searchdate = mypage.searchdate(user_id,day);
//		model.addAttribute("searchdate", searchdate);
//		System.out.println(searchdate);
//		System.out.println("앙앙");
//			return "nav/mypage/searchdate";
//
//	}
//	@RequestMapping(value = "/searchdate", method = RequestMethod.POST)
//	@ResponseBody
//	public String searchdate(Model model, HttpSession session, HttpServletRequest request,@RequestParam int day) {
//		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
//		
//		ArrayList<OrdersVO> searchdate = new ArrayList<OrdersVO>();
//		System.out.println("기간검색");
//		System.out.println(day);
//		searchdate = mypage.searchdate(user_id,day);
//		
//		model.addAttribute("searchdate",searchdate);
//		System.out.println(searchdate);
//		return "success";
//	}
	
	
//	@RequestMapping(value = "/myreservation", method = RequestMethod.GET)
//	public String myreservation(Model model) {
//		
//
//		Paging page = new Paging();
//		ArrayList<Integer> arr = new ArrayList<Integer>();
//		Map<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
//		int pageNum = 0;
//		int mapNum = 0;
//		int sendPageNum = 0;
//		int realNum = Integer.parseInt(num);
//
//		page.setTotalNum(adminService.memberPageNum());
//		// OnePageBorad => 한 페이지에 보여줄 멤버(글) 수
//		if (page.getTotalNum() <= page.getOnePageBoard()) { // totalnum이 10보다 작으면 pageNum을 1로 설정
//			pageNum = 1;
//		} else { // totalnum이 더 클 경우
//			pageNum = page.getTotalNum() / page.getOnePageBoard(); // totalnum / 10
//																	// ex) 21/ 10 = 2가 pageNum에 들어감
//			if (page.getTotalNum() % page.getOnePageBoard() > 0) { // 그리고 21 % 10 나머지 1이 0보다 크기때문에 pageNum에 2+1=3이 들어감
//				pageNum = pageNum + 1;
//			}
//		}
//
//		// 추가내용
//		if (pageNum % 5 != 0) {
//			mapNum = pageNum / 5 + 1;
//		} else {
//			mapNum = pageNum / 5;
//		}
//
//		for (int i = 0; i < mapNum; i++) {
//			arr = new ArrayList<Integer>();
//			for (int j = 0; j < 5; j++) {
//
//				if ((i * 5) + j + 1 > pageNum) {
//					break;
//				}
//
//				arr.add((i * 5) + j + 1);
//			}
//			map.put(i, arr);
//		}
//
//		sendPageNum = (realNum - 1) / 5;
//
//		page.setEndNum((realNum * 10) + 1);
//		page.setStartNum(page.getEndNum() - 10);
//
//		// 추가내용
//		model.addAttribute("pageNum", map.get(sendPageNum));
//		model.addAttribute("memberList", adminService.memberList(page));
//
//		// 추가내용
//
//		if (realNum > pageNum) {
//			System.out.println("pageNum : " + pageNum);
//			return "redirect:/memberManage/" + pageNum;
//		}
//
//		return "nav/mypage/myreservation";
//	
//	}
	
	//예약취소	
	@RequestMapping(value = "/orderdel", method = RequestMethod.POST)
	@ResponseBody
	public String orderdel(Model model, HttpSession session, HttpServletRequest request,@RequestParam String or_cd,@RequestParam String or_stat) {
	System.out.println(or_cd);
	System.out.println(or_stat);
	
	Integer delorder = mypage.delorder(or_cd, or_stat);
		return "success";
	}
		
	@RequestMapping(value = "/mypost", method = RequestMethod.GET)
	public String mypost(Model model, HttpSession session, HttpServletRequest request) {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		 ArrayList<PostVO> post_list = new ArrayList<PostVO>(); 
		 post_list = mypage.postlist(user_id);

		
		  model.addAttribute("post_list", post_list);
		return "nav/mypage/mypost";

	}
	


	@RequestMapping(value = "/deleteaccount", method = RequestMethod.GET)
	public String DeleteAccount(Model model) {
		return "nav/mypage/DeleteAccount";

	}
	//회원탈퇴
	@RequestMapping(value = "/deletid", method = RequestMethod.POST)
	@ResponseBody
	public String Deleteid(Model model, HttpSession session, HttpServletRequest request,@RequestParam String user_id,@RequestParam String password) {
		ArrayList<MemberVO> pass_cheak = new ArrayList<MemberVO>();
		System.out.println(password +"비밀번호1");
		System.out.println(user_id + "아이디");
		pass_cheak = mypage.passCheak(user_id);
		String a = pass_cheak.get(0).getPassword();
		
		if (a.equals(password)) {
		Integer delacc =mypage.memberdel(user_id, password);
		System.out.println("끝");
		session.invalidate();
		return "success";
		
		} else {
		return "fail";
		}
	}
	
	
	@RequestMapping(value = "/newpwpopup", method = RequestMethod.GET)
	public String newpwpopup(Model model) {
		return "nav/mypage/newpwpopup";

	}

	// 비빌번호 변경

	@RequestMapping(value = "/member_pass_new", method = RequestMethod.POST)
	@ResponseBody
	public String member_pass_new(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam String password) {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		System.out.println(user_id+"변경확인아이디");
		System.out.println(password+"변경확인비번");

		Integer update = mypage.passNew(user_id, password);
		System.out.println(update);

		
		return "success";
	}

	// 회원 정보수정

	@RequestMapping(value = "/member_info_new", method = RequestMethod.POST)
	@ResponseBody
	public String member_info_new(Model model, HttpSession session, HttpServletRequest request,
			@RequestParam String email, @RequestParam String addr_ji, @RequestParam String addr_dong,
			@RequestParam String phon) {
		String user_id = ((MemberVO) request.getSession().getAttribute("user")).getUser_id();
		System.out.println(user_id + "다시넘겨온 아이디");
		System.out.println(email + "이메일");
		System.out.println(phon + "폰");
		System.out.println(addr_ji + "주소1");
		System.out.println(addr_dong + "주소2");
		System.out.println("====================");
		Integer update = mypage.infoNew(user_id, email, addr_ji, addr_dong, phon);

		System.out.println(update);
		return "success";
	}

}
