<%-- <%@ page language="java" contentType="text/html; --%>
<%-- charset=UTF-8" --%>
<%-- 	pageEncoding="UTF-8"%> --%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<%-- <%@ page session="false"%> --%>
<!-- <html> -->
<!-- <head> -->
<!-- <title>Home</title> -->
<!-- </head> -->
<!-- <body> -->
<!-- 	<h1>Hello world!</h1> -->
<%-- 	<c:forEach var="customer" items="${list}"> --%>
<%-- 		<P>${customer.name}</P> --%>
<%-- 		<p>${customer.cid}</p> --%>
<%-- 	</c:forEach> --%>
<!-- </body> -->
<!-- </html> -->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<html>

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <meta name="description" content="Coral - Onepage portfolio Template">
    <meta name="author" content="esrat">

    <!-- Fontawseom Icon CSS -->
    <link rel="stylesheet" href="assets/css/all.css">

    <!-- Theme CSS -->
    <link rel="stylesheet" href="assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="stylesheet" href="assets/css/responsive.css">

    <title>반려견 커뮤니티 투개더에 오신것을 환영합니다!</title>
</head>

<body id="page-top">

    <!-- NAVBAR
    ================================================= -->
    <nav class="navbar navbar-expand-lg navbar-dark navbar-togglable  fixed-top " id="mainNav">
        <div class="container">

            <!-- Brand -->
            <a class="navbar-brand js-scroll-trigger" href="#page-top">
                <img src="assets/img/Together.png">
            </a>

            <!-- Toggler -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon-bar">
                    <i class="fa fa-bars"></i>
                </span>
            </button>
            
            

            <!-- Collapse -->
            <div class="collapse navbar-collapse" id="navbarCollapse">
                <!-- Links -->
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="#page-top">
                            Home
                        </a>
                    </li>
                   
                    <li class="nav-item">
                        <a class="nav-link js-scroll-trigger" href="#about">
                            Services
                        </a>
                    </li>
                    <li class="nav-item ">
                        <a class="nav-link js-scroll-trigger" href="page">
                            Page
                        </a>
                    </li>
                </ul>
                
            <button class="button" id="login" style="vertical-align:middle;" onclick="login()">Sign in</button>
            <button class="button" id="signup" style="vertical-align:middle;" onclick="signup()">Sign up</button>
            


            </div>
            
            
            <!-- / .navbar-collapse -->
        </div>
       
            
        <!-- / .container -->
    </nav>
    

    <!-- HERO
    ================================================== -->
     <section class="section section-top section-full">

        <!-- Cover -->
        <div class="bg-cover" style="background-image: url(assets/img/dogpage1.png); width:100%; height:100%;"></div>

        <!-- Overlay -->
        <div class="bg-overlay"></div>
        <!-- Content -->
        <div class="container">
            <div class="row justify-content-center align-items-center">
                <div class="col-md-10 col-lg-7 ">
                    <div class="banner-content">
                        <!-- Preheading -->
                        <p class="text-white text-uppercase text-center text-xs">
                            <span>Dog Love</span>
                        </p>

                        <!-- Heading -->
                        <h1 class="text-white text-center mb-4 display-4 font-weight-bold">
                         	  반려견 종합 서비스  <br> Together에 오신것을 <br> 환영합니다.
                        </h1>

                        <!-- Subheading -->
                        <p class="lead text-white text-center mb-5">
                            반려견에 대한 통합서비스가 주인 투개더에서는 사용자의 편의성과 함께  <br><br>새로운 서비스를 제공하고 있습니다 분양/짝찾기/병원/장례 등 <br><br>정보를 확인할수 있으며 커뮤니티를 통해 <br><br>쉽게 알고자하는 정보를 획득할수 있습니다.
                            <br><br>반려견과 처음부터 끝까지 책임지는 서비스 플랫폼 입니다.
                        </p>

                        <!-- Button -->
                        <div class="text-center mb-0" >
                            <a href="https://play.google.com/store/apps?utm_source=apac_med&utm_medium=hasem&utm_content=Jan0219&utm_campaign=Evergreen&pcampaignid=MKT-DR-apac-kr-1003227-med-hasem-ap-Evergreen-Jan0219-Text_Search_BKWS-BKWS%7cONSEM_kwid_43700009359644034_creativeid_96242360585_device_c&gclid=EAIaIQobChMIos6S79qA4QIVhKmWCh3c8wTKEAAYASAAEgJzU_D_BwE&gclsrc=aw.ds" target="_blank" class="btn btn-primary">
                                Google play Download
                            </a>
                            <a href="https://itunes.apple.com/kr/app/apple-store/id375380948?mt=8" target="_blank" class="btn btn-primary" style="width:247px;">
                                ios Download
                            </a>
                     </div>
                    </div>
                </div>
            </div>
            <!-- / .row -->
        </div>
        <!-- / .container -->
    </section>

    <!-- SECTIONS
    ================================================== -->
    <!-- PAGES
    ================================================== -->

    <!-- FEATURES
    ================================================== -->
    <section class="section" id="feature">
        <div class="container">
            <div class="row justify-content-center mb-4">
                <div class="col-md-8 col-lg-6 text-center">

                    <!-- Heading -->
                    <h2 class="lg-title mb-2">
                        반려견을 위한 곳
                    </h2>

                    <!-- Subheading -->
                    <p class="mb-5 ">
                        1000만 반려동물시대에 맞춘 최적화 플랫폼
                        <c:forEach var="user" items="${id}">
    <p>${user.userid}
    <p>${user.password}
</c:forEach>
                    </p>

                </div>
            </div>
            <!-- / .row -->

            <div class="row justy-content-center">
                <div class="col-lg-3 col-md-6">
                    <div class="text-center feature-block">
                            <div class="img petching">
                            	<img src="assets/img/family.png">
                            </div>
                        <h4 class="mb-3" style="padding-top:10px;">PetChing</h4>
                        <p>반려견의 짝 찾기/나에게 맞는 반려견을 찾을수 있는 서비스 </p>
                    </div>
                </div>

                <div class="col-lg-3 col-md-6">
                    <div class="text-center feature-block">
                        <div class="img pet hospital">
                            	<img src="assets/img/first-aid-kit.png">
                            </div>
                        <h4 class="mb-3" style="padding-top:10px;">Pet hospital</h4>
                        <p>반려견의 건강이나빠지면 조취를 빠르게 취할수 있습니다</p>
                    </div>
                </div>

                <div class="col-lg-3 col-md-6">
                    <div class="text-center feature-block">
                        <div class="img pet community">
                            	<img src="assets/img/communication.png">
                            </div>
                        <h4 class="mb-3" style="padding-top:10px;">pet community</h4>
                        <p>SNS의 커뮤니티 서비스 / 모임 서비스 지원</p>
                    </div>
                </div>

                <div class="col-lg-3 col-md-6">
                    <div class="text-center feature-block">
                        <div class="img pet goodbye">
                            	<img src="assets/img/invitation.png">
                            </div>
                        <h4 class="mb-3" style="padding-top:10px;">GoodByePet</h4>
                        <p>반려견의 사후처리를 책임집니다.</p>
                    </div>
                </div>
                
                
            </div>
        </div>
        <!-- / .container -->
    </section>


    <!-- ABOUT
    ================================================== -->
    <section class="section bg-light" id="about">
        <div class="container">
            <div class="row ">
                <div class="col-lg-6">
                    <div class="about-img">
                        <img src="assets/img/about/twopet.jpg" alt="" class="img-fluid">
                    </div>
                </div>

                <div class="col-lg-6 ">
                    <div class="about-content">
                        <h2 class="display-5">
                             펫칭(PetChing)
                        </h2>
                        <p>펫칭은 신개념 매칭 서비스 입니다. 반려견의 정보를 한눈에 알아보고 자신이 원하는 반려견을 찾아내어 매칭이 가능하도록 만들어줍니다
                         	사용자는 편의성과 함께 시간을 단축 할수 있습니다.</p>


                        <ul class="list-unstyled skill-list ">
                            <li>반려견 짝 찾기 서비스</li>
                            <li>분양/입양 센터 서비스</li>
                            <li>인기펫 서비스</li>
                            <li>펫 이상형 월드컵</li>
                         
                        </ul>

                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row mt-5 align-items-center ">
                <div class="col-lg-6 col-md-6">
                    <div class="about-content-2 ">
                        <h2 class="display-5">
                            펫 병원
                        </h2>
                        <p>펫 병원은 사용자들의 예약을 간편하게 도와줄수 있습니다. 이 서비스는 실시간 예약 서비스로 좀더 견주분들께 즉각적으로 반응하여 큰일이 나지
                        않도록 해주는 서비스입니다.</p>

                       <ul class="list-unstyled skill-list ">
                            <li>실시간 예약 서비스</li>
                            <li>위치 기반 서치</li>
                            <li>편리한 UI</li>
                            <li>믿을수 있는 업체 정보</li>
                         
                        </ul> 
                    </div>
                </div>

                <div class="col-lg-6 col-md-6">
                    <div class="about-img">
                        <img src="assets/img/about/pethos.jpg" alt="" class="img-fluid">
                    </div>
                </div>
            </div>
        </div>
        <p>
        
        <pre>
        
        </pre>
        
        <div class="container">
            <div class="row ">
                <div class="col-lg-6">
                    <div class="about-img">
                        <img src="assets/img/about/dogcom.JPG" alt="" class="img-fluid">
                    </div>
                </div>

                <div class="col-lg-6 ">
                    <div class="about-content">
                        <h2 class="display-5">
                             펫 커뮤니티
                        </h2>
                        <p>펫 커뮤니티는 SNS 형태의 사진 기반 커뮤니티 서비스 입니다. 이러한 서비스를 통해 사용자들은 정보를 공유하고 일상을 공유하며 공감대를 느낄수잇는 서비스입니다
                        
                        또한 SNS에 게시된 게시글의 정보를 빠르게 Together내에서 검색이 가능합니다. 그리고 모임기능을 통해 자신과 성향이 비슷한 사람들과 함께 그룹활동을 하여 좀더 반려견과 가까워질수 있습니다.</p>


                        <ul class="list-unstyled skill-list ">
                            <li>통합 서비스 연계</li>
                            <li>사진 커뮤니티 서비스</li>
                            <li>모임 구현</li>
                            <li>메세지 기능 구현</li>
                         
                        </ul>

                    </div>
                </div>
            </div>
        </div>
        
        <pre>
        
        </pre>
        <div class="container">
            <div class="row mt-5 align-items-center ">
                <div class="col-lg-6 col-md-6">
                    <div class="about-content-2 ">
                        <h2 class="display-5">
                            펫 호텔
                        </h2>
                        <p>펫 호텔은 반려견의 최상의 휴식장소를 제공합니다. 업체를 통해 비교할수 있는 정보를 제공하여 사용자의 편의성을 높이고 조건에 맞는 호텔을 검색할수 있는 서비스입니다.
                        펫 호텔은 실시간으로 예약이 가능하며 비어있다면 방위치를 자신이 선택할수 있습니다. </p>

                       <ul class="list-unstyled skill-list ">
                            <li>실시간 예약 서비스</li>
                            <li>위치 기반 서치</li>
                            <li>편리한 UI</li>
                            <li>믿을수 있는 업체 정보</li>
                         
                        </ul> 
                    </div>
                </div>

                <div class="col-lg-6 col-md-6">
                    <div class="about-img">
                        <img src="assets/img/about/pethotal.png" alt="" class="img-fluid">
                    </div>
                </div>
            </div>
        </div>
        <pre>
        
        </pre>
        
        <div class="container">
            <div class="row ">
                <div class="col-lg-6">
                    <div class="about-img">
                        <img src="assets/img/about/petfuneal.jpg" alt="" class="img-fluid">
                    </div>
                </div>

                <div class="col-lg-6 ">
                    <div class="about-content">
                        <h2 class="display-5">
                             굿바이펫
                        </h2>
                        <p>굿바이펫은 반려견의 사후처리를 책임집니다. 믿고 내 가족처럼 내일처럼 맡아서 도와주고 견주의 슬픔을 함께 나눕니다.
                        또한 온라인 추모소를 동해 자신의 반려견에 하고싶은말을 적어 보낼수 있고 원한다면 다른 견주들의 위로를 받을수 있습니다. </p>


                        <ul class="list-unstyled skill-list ">
                            <li>장례 업체 정보 제공</li>
                            <li>온라인 분향소 서비스 제공</li>
                            <li>너와 함께 한 추억 다이어리 서비스</li>
                            
                         
                        </ul>

                    </div>
                </div>
            </div>
        </div>
        
    </section>



    <!-- SERVICES
    ================================================== -->
  

    <!-- ABOUT
    ================================================== -->
    <section class="section bg-light" id="portfolio">
        <div class="container">
            <div class="row justify-content-center mb-4">
                <div class="col-md-8 col-lg-7 text-center">

                    <!-- Heading -->
                    <h2 class="lg-title mb-2">
                        앱 소개 화면
                    </h2>

                    <!-- Subheading -->
                    <p class="mb-5">
                        Together 앱 실제  화면 미리보기
                    </p>

                </div>
            </div>
            <!-- / .row -->

            <div class="row justify-content-center">
                <div class="col-lg-4 col-md-6 col-sm-6 mb-5">
                    <div class="portfolio-block">
                        <img src="assets/img/portfolio/43.png" alt="portfolio">

                        <div class="portfolio-content">
                            <h4>Probiz-portfolio web template</h4>
                            <span class="work-cat">Web Design</span>
                        </div>
                        <div class="overlay-content">
                            <a href="single-portfolio.html"><i class="fa fa-link"></i></a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6 mb-5">
                    <div class="portfolio-block">
                        <img src="assets/img/portfolio/44.png" alt="portfolio">

                        <div class="portfolio-content">
                            <h4>Probiz-portfolio web template</h4>
                            <span class="work-cat">Web Design</span>
                        </div>
                        <div class="overlay-content">
                            <a href="single-portfolio.html"><i class="fa fa-link"></i></a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6 mb-5">
                    <div class="portfolio-block">
                        <img src="assets/img/portfolio/43.png" alt="portfolio">

                        <div class="portfolio-content">
                            <h4>Probiz-portfolio web template</h4>
                            <span class="work-cat">Web Design</span>
                        </div>
                        <div class="overlay-content">
                            <a href="single-portfolio.html"><i class="fa fa-link"></i></a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6">
                    <div class="portfolio-block">
                        <img src="assets/img/portfolio/45.png" alt="portfolio">

                        <div class="portfolio-content">
                            <h4>Probiz-portfolio web template</h4>
                            <span class="work-cat">Web Design</span>
                        </div>
                        <div class="overlay-content">
                            <a href="single-portfolio.html"><i class="fa fa-link"></i></a>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6 col-sm-6">
                    <div class="portfolio-block">
                        <img src="assets/img/portfolio/46.png" alt="portfolio">

                        <div class="portfolio-content">
                            <h4>Probiz-portfolio web template</h4>
                            <span class="work-cat">Web Design</span>
                        </div>
                        <div class="overlay-content">
                            <a href="single-portfolio.html"><i class="fa fa-link"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>


    <!-- Pricing
    ================================================== -->
    
    <!-- Testimonial
    ================================================== -->
   
    <!-- Contact
    ================================================== -->
    
    <!-- FOOTER
    ================================================== -->
    <footer class="top-padding bg-dark">
        <!--Content -->
        <div class="container">
            <div class="row align-self-center">
                <div class="col-lg-4 col-md-6">
                    <div class="footer-widget">
                        <!-- Brand -->
                        <a href="#" class="footer-brand text-white">
                            <img src="assets/img/Together.png">
                        </a>
                        <br>
                        <p> New Next Generation Integration Dog Service Together</p>
                    </div>
                </div>

                <div class="col-lg-2 ml-lg-auto col-md-2">

                    <!-- Links -->
                    <ul class="footer-link list-unstyled ml-0 justify-content-end">
                        <li>
                            <a href="#" class="text-white">
                               	 Phone
                            </a>
                        </li>
                        <li>
                            <a href="#" class="text-white">
                                Location
                            </a>
                        </li>
                        <li>
                            <a href="#" class="text-white">
                                Question
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="col-lg-3 col-md-4">

                    <!-- Links -->
                    <ul class="footer-link list-unstyled ml-0 justify-content-end">
                        <li>
                            <i class="fa fa-mobile"></i> 010-5505-9374
                        </li>
                        <li>
                            <i class="fa fa-location-arrow"></i> 복현동 영진전문대학 300호 40번자리 , 대구광역시
                        </li>
                        <li>
                            <i class="fa fa-globe"></i> llov0310@gmail.com
                        </li>
                    </ul>
                </div>
            </div>
            <!-- / .row -->

            <div class="row justify-content-md-center footer-copy">
                <div class="col-lg-8 col-md-6 col-sm-6 text-center">
                    <p class="lead text-white-50">&copy; Copyright Reserved to Themeturn | Design and Developed by Esrat </p>
                </div>
            </div>
        </div>
        <!-- / .container -->
    </footer>

    <!-- JAVASCRIPT
    ================================================== -->
    <!-- Global JS -->
    <script src="assets/libs/jquery/jquery.min.js"></script>
    <script src="assets/libs/bootstrap/bootstrap.bundle.min.js"></script>

    <!-- Plugin JavaScript -->
    <script src="assets/js/jquery.easing.min.js"></script>
    <script src="assets/js/ajax-contact.js"></script>

    <!-- Theme JS -->
    <script src="assets/js/theme.js"></script>
    
    <!--  login JS -->
    <script src="assets/js/login.js"></script>

</body>

</html>