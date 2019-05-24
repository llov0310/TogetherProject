package com.together.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.together.service.BoardService;

import com.together.domain.AttachFileDTO;  //import

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import net.coobird.thumbnailator.Thumbnailator;
@Controller
@Log
@AllArgsConstructor
//투개더 프로젝트 업로드 컨트롤러
/*
 * Multipart 타입 -> 파일 여러개 처리하는거

String getName() ->파라미터의 이름 <input>태그의 이름
String getOriginalFileName() -> 업로드되는 파일의 이름
boolean isEmpty() -> 파일이 존재하지 않는 경우 true
long getSize() -> 업로드 되는 파일의 크기
byte[] getBytes() -> byte[]로 파일 데이터 반환
inputStream getInputStream() -> 파일데이터와 연결된 inputStream을 반환
transferTo(File file) -> 파일의 저장
 * */

public class UploadController {
	/*
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
	public String adminHome(Model model) {
		return "upload/uploadAjax";
	}
	*/
	
	/*
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String adminHome(Model model) {
		return "board/register";
	}
	*/
	
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
			String uploadFileName = multipartFile.getOriginalFilename();

			// IE has file path
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			log.info("only file name : " + uploadFileName);
			attachDTO.setFileName(uploadFileName);

			UUID uuid = UUID.randomUUID();

			uploadFileName = uuid.toString() + "_" + uploadFileName;

			System.out.println(uuid + " ★★★★");
			System.out.println(uploadPath + " ★★★★");
			System.out.println(uploadFileName + " ★★★★");  //테스트-> 다 나옴
			
			
			
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

				String uid = uuid.toString();
				String path = uploadPath.toString();
				
				if(count == 0) {
					int uploadtest = boardservice.insert(uid, path, uploadFileName);  /*ㅂㄷㅂㄷ...*/
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
	
	
}
