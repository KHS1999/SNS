<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>로그인 페이지</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

 

<link rel="stylesheet" href="/static/css/style.css" type="text/css">

</head>
<body>
	<div id="wrap">
		<form id="loginForm">
		<section  class="section  d-flex justify-content-center py-5">
			<div class= "col-4 text-center pt-5 sectionDiv">
				<h1>Stargram</h1>								
				<input type="text" id="loginIdinput"placeholder="아이디" class="form-control mt-3">
				<input type="password" id="passwordInput"placeholder="비밀번호" class="form-control mt-3">								
				<button class="btn bg-primary text-white mt-3 form-control">로그인</button>
				<div class="sectionDiv my-3 text-center">
				    <br>계정이 없으신가요?<a href="/user/signup/view">가입하기</a><br><br>
				</div>
			</div>
		</section>
		</form>
		<c:import url="/WEB-INF/jsp/include/footer.jsp"/>
	</div>
	<script>
		$(document).ready(function(){
			$("#loginForm").on("submit",function(e){
				// 해당 이벤트가 가지고  있는 기능을 비활성화
				e.preventDefault();
				
				// loginId 와 password 를 통해서 로그인 진행
				// 로그인 api 호출
				let loginId = $("#loginIdinput").val();
				let password = $("#passwordInput").val();
				
				if(loginId == ""){
					
					alert("아이디를 입력하세요");
					return;
				}
				if(password == ""){
					alert("비밀번호를 입력하세요");
					return;
				}
				
				$.ajax({
					type:"post",
					url:"/user/signin",
					data:{"loginId":loginId, "password":password},
					success:function(data){
						
						if(data.result == "success"){
							location.href = "/post/timeline/view";
						}else{
							alert("아이디/비밀번호를 확인하세요!!");
						}
					},
					error:function(){
						alert("로그인 에러");
					}
				});
					
			});
		});
	</script>
</body>
</html>