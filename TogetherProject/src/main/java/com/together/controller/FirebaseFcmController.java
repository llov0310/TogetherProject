package com.together.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.io.BufferedReader;

import java.io.InputStreamReader;


@Controller
@AllArgsConstructor
public class FirebaseFcmController {
	
	@ResponseBody
	@RequestMapping(value = "/fcm")
	public String member_info(Model model, HttpSession session, HttpServletRequest request) throws Exception {
	System.out.println("여긴오냐 시발럼아  엉 엉어ㅓ어엉");
	
		/*
		 * List<MobileTokenVO> tokenList = fcmService.loadFCMInfoList(vo);
		 * 
		 * String token = tokenList.get(count).getDEVICE_ID();
		 */
        
        final String apiKey = "AAAAjMWWtVI:APA91bFVgt1CuhihHU_ErDkpq24MiC7SN4ERW5UurfLLHoa938CRaWzl9Y6zNWGWtSVwuWXosQ-oMTbuMGiD66sn5r0JYgU033VT_Es8MbxPOItpt6BwjiKrU14bfkX6XbiS4Hfb33iH";
        URL url = new URL("https://fcm.googleapis.com/fcm/send");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Authorization", "key=" + apiKey);

        conn.setDoOutput(true);
        
//        String userId =(String) request.getSession().getAttribute("ssUserId");

        // 이렇게 보내면 주제를 ALL로 지정해놓은 모든 사람들한테 알림을 날려준다.
        String input = "{\"notification\" : {\"title\" : \"보내지냐? \", \"body\" : \"이게머냐고 도데체에에엥\"}, \"to\":\"/topics/ALL\"}";
        
        // 이걸로 보내면 특정 토큰을 가지고있는 어플에만 알림을 날려준다  위에 둘중에 한개 골라서 날려주자
//        String input = "{\"notification\" : {\"title\" : \" 여기다 제목넣기 \", \"body\" : \"여기다 내용 넣기\"}, \"to\":\" 여기가 받을 사람 토큰  \"}";

        OutputStream os = conn.getOutputStream();
        
        // 서버에서 날려서 한글 깨지는 사람은 아래처럼  UTF-8로 인코딩해서 날려주자
        os.write(input.getBytes("UTF-8"));
        os.flush();
        os.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + input);
        System.out.println("Response Code : " + responseCode);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        // print result
        System.out.println(response.toString());
		
		 
		return "jsonView";

	}
}
