<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>회원가입 페이지</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

 

<link rel="stylesheet" href="/static/css/style.css" type="text/css">

</head>

<body>

	<div id="wrap">

		<section  class="section  d-flex justify-content-center py-5">
			<div class= "col-4 text-center pt-5 sectionDiv">
				<h1>Stargram</h1>
				<span class="text-secondary"><b>친구들의 사진과 동영상을 보려면 가입하세요.</b></span>
				<div class="d-flex">
					<input type="text" placeholder="아이디"  id= "idInput" class="form-control col-9 mt-3">
					<button class="btn bg-primary text-white mt-3 ml-1 form-control"  id="isDuplicateBtn">중복확인</button>
				</div>
				<div id="duplicateText" class="d-none"><span class="small text-danger">중복된 ID 입니다.</span></div>
				<div id="possibleText" class="d-none"><span class="small text-success">사용가능한 ID 입니다.</span></div>
				<input type="password" placeholder="패스워드" id="passwordInput" class="form-control mt-3">
				<input type="password" placeholder="패스워드확인" id="checkpasswordInput" class="form-control mt-3">
				<input type="text" placeholder="이름" id="nameInput" class="form-control mt-3">
				<input type="text" placeholder="이메일" id="emailInput" class="form-control mt-3">
				<button id="signupBtn" class="btn bg-primary text-white mt-3 form-control" >회원가입</button>
				<div class="sectionDiv my-3 text-center">
				    <br>계정이 있으신가요?<a href="/user/signin/view">로그인</a><br><br>
				</div>
			</div>	
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp"/>
	</div>
	<script>
		$(document).ready(function(){
			
			var isDuplicateCheck = false;
			var isDuplicateId = true;
			
			$("#idInput").on("input",function() {
				 isDuplicateCheck = false;
				 isDuplicateId = true;		
				$("#possibleText").addClass("d-none");
				$("#duplicateText").addClass("d-none");
			});	
			
		
			
			$("#isDuplicateBtn").on("click",function(){
				let id = $("#idInput").val();
				
				if(id == ""){
					alert("아이디를 입력해주세요!");
					return ;
				}	
				
				$.ajax({
					type:"get",
					url:"/user/duplicate_id",
					data:{"loginId":id},
					success:function(data){
						// {"is_duplicate" : "true"}
						// {"is_duplicate" : "false"}
						// 중복체크 여부 판단
						 isDuplicateCheck = true;
						
						if(data.is_duplicate){ // 중복된 경우
							$("#duplicateText").removeClass("d-none");
							$("#possibleText").addClass("d-none");
							isDuplicateId = true;
						}else{ // 중복되지 않은 경우
							$("#possibleText").removeClass("d-none");	
							$("#duplicateText").addClass("d-none");
							isDuplicateId = false;
						}
					},
					error:function(){
						alert("중복확인 에러");
					}
				});
			});
			
			$("#signupBtn").on("click",function(){
				
				let id = $("#idInput").val();
				let password = $("#passwordInput").val();
				let checkpassword = $("#checkpasswordInput").val();
				let name = $("#nameInput").val();
				let email = $("#emailInput").val();
				
				if(id == ""){
					alert("아이디를 입력해주세요!");
					return ;
				}
				
				// 중복체크 여부 유효성 검사
				//if(isDuplicateCheck == false){
				if(!isDuplicateCheck){
					alert("중복여부 체크를 진행해주세요");
					return;
				}
				
				// 아이디 중복여부 유효성 검사
				//if(isDuplicateId == true){
				if(isDuplicateId){	
					alert("중복된 아이디입니다");
					return ;
				}
				
				if(password == ""){
					alert("비밀번호를 입력해주세요!");
					return;
				}
				
				if(checkpassword == ""){
					alert("비밀번호확인을 입력해주세요!");
					return;
				}
				
				if(password != checkpassword){
					alert("비밀번호가 일치하지 않습니다!");
					return;
				}
				if(name == ""){
					alert("이름을 입력해주세요!")
					return;
				}
				if(email == ""){
					alert("이메일을 입력해주세요!");
					return;
				}
				$.ajax({
					type:"post",
					url:"/user/signup",
					data:{"loginId":id, "password":password, "name":name, "email":email},
					success:function(data){
						if(data.result == "success"){
							location.href = "/user/signin/view";
						}else{
							alert("회원가입 실패");
						}
					},
					error:function(){
						alert("회원가입 에러!!");
					}
				});
			});
			
		});
	</script>
</body>
</html>