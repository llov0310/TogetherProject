<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<link rel="stylesheet" href="assets/css/signup.css">
 <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
 
 <script src="assets/js/sign.js"></script>

<form>
<h2>회원가입</h2>

  <label>
    <p class="label-txt">아이디를 입력해 주세요</p>
    <input type="text" class="input" id="id">
    <div class="line-box">
      <div class="line"></div>
    </div>
  </label>
  <label>
    <p class="label-txt">이름을 입력해 주세요</p>
    <input type="text" class="input" id="name">
    <div class="line-box">
      <div class="line"></div>
    </div>
  </label>
  <label>
    <p class="label-txt">비밀번호를 입력해주세요</p>
    <input type="password" class="input" id="password">
    <div class="line-box">
      <div class="line"></div>
    </div>
  </label>
  <label>
    <p class="label-txt">이메일을 입력해주세요</p>
    <input type="text" class="input" id="email" placeholder="ex)xxxx@naver.com">
    <div class="line-box">
      <div class="line"></div>
    </div>
  </label>
  
  <label style="width:50%">
    <p class="label-txt2">주소를 검색 해 주세요</p>
    <input type="text" class="input" id="sample6_postcode" placeholder="주소">
     <input type="text" class="input" id="sample6_address" placeholder="지번">   
    <input type="text" class="input" id="sample6_extraAddress" placeholder="요약">
    <input type="submit" class="button2" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
     
  </label>
  </form>
<br>

<button id="signup">회원가입</button>