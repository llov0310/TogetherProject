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

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import net.coobird.thumbnailator.Thumbnailator;
@Controller
@Log
@AllArgsConstructor
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
	@RequestMapping(value = "/uploadAjax", method = RequestMethod.GET)
	public String adminHome(Model model) {
		return "upload/uploadAjax";
	}
	
	
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
	
	
	
	
}
