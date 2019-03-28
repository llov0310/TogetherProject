<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<script src="assets/js/login.js"></script>

<link rel="stylesheet" href="assets/css/login.css">
<div class="wrapper fadeInDown">
  <div id="formContent">
    <!-- Tabs Titles -->

    <!-- Icon -->
    <div class="fadeIn first">
      <img src="assets/img/login/test.png" id="icon" alt="User Icon" />
    </div>

    <!-- Login Form -->
    
      <input type="text" id="id" class="fadeIn second" name="login" placeholder="login">
      <input type="password" id="password" class="fadeIn third" name="login" placeholder="password">
      <button id ="login" class="fadeIn fourth" value="로그인" style=margin-bottom:20px;>로그인</button>
      <button class="fadeIn fourth" value="회원가입" style="margin-bottom:10px; width:200px; text-align: center;" >회원가입</button>
    

    <!-- Remind Passowrd -->
    <div id="formFooter">
      <a class="underlineHover" href="#">비밀번호를 잊으셨나요?</a>
    </div>

  </div>
</div>