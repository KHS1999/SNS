<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<link rel="stylesheet" href="/static/css/style.css" type="text/css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
<body>
	<div id="wrap">		
		<c:import url="/WEB-INF/jsp/include/timelineheader.jsp"/>

		<section class=" d-flex mt-3 justify-content-center">
		<!-- 입력상자 -->
			<div class="sectionDiv col-6 ">
			<div class="userId bg-secondary my-3 py-3 pl-3 text-white border rounded">
				사용자id ...(메뉴기능)
			</div>
			<hr>
			<div>
				<textarea rows="10" id="contentInput" class="form-control border-0 rounded">게시글 내용 </textarea>
			</div>
			<div class="d-flex justify-content-between mt-3">
				<a href="#" id="imageIcon"><span class="image-fill"><i class="bi bi-image-fill"></i></span></a>
				<input type="file" class="d-none" id="fileInput">
				<button id="uploadBtn" class="btn bg-info text-white">업로드</button>
			</div> 
			<!--  /입력상자 -->
			<!--  피드들 -->
			<!--  타이틀 -->
			<c:forEach var="postDetail" items="${postList }">
			<div class="border rounded mt-3">
			<div class="d-flex justify-content-between p-2">
				<div class="d-flex align-items-center ml-2">				
					<div> ${postDetail.user.loginId } </div>
				</div>	
				<div><span class="three-dots"><i class="bi bi-three-dots"></i></span></div>
			</div>
			<!--  /타이틀 -->
			
			<div>
				<img class="w-100" src="${postDetail.post.imagePath }">
			</div>
			
			<!--  좋아요 -->
			<div class="p-2">
			<a href="#" class="like-btn" data-post-id="${postDetail.post.id }">
				<i class="bi bi-heart" id="heart" ></i>
			</a>	
				<i class="bi bi-heart-fill d-none" id="heart-fill"></i> 좋아요: n개
			</div>
			<!--  /좋아요 -->
			
			<!-- 게시글 -->
			<div class="p-2">
				<b>${postDetail.user.loginId }</b> ${postDetail.post.content }
			</div>
			<!-- 게시글 -->
			
			<!--  댓글 -->
			<div class="p-2">
				<div class="mb-2 border-bottom small">댓글</div>
				<!-- 댓글리스트 -->
				<div class="mt-2">
					<div><b>userId1</b>꽃이 정말 예뻐요!!</div> 
					<div><b>userId1</b>구매처좀 알려주세요</div>   
					<div class= "d-flex justify-content-end text-right">  
						<a href="#" class="text-secondary">더보기</a>
					</div>
				</div>
				<!-- /댓글리스트 -->
			</div>
			<!--  /댓글 -->
			<hr>
			<div class="d-flex justify-content-between">
				댓글 달기
				<input type="text" class="form-control border-0 col-8 mb-3">
				<button class="btn ml-2 bg-success text-white">게시</button>
			</div>
			</div>
			</c:forEach>
		</div>			
		</section>
		<c:import url="/WEB-INF/jsp/include/footer.jsp"/>
	</div>
	<script>
		$(document).ready(function(){
			
			$(".like-btn").on("click",function(e){
				e.preventDefault();
				
				// 현재 클릭된 태그 객체를 얻어 와서 postId를 얻어 온다
				alert();
			});
			
			$("#imageIcon").on("click", function(e){
				// fileInput을 클릭한 효과를 만들어야 한다.
				e.preventDefault();
				$("#fileInput").click();
			});
			
			$("#heart").on("click",function(){
				
				$("#heart").addClass("d-none");
				$("#heart-fill").removeClass("d-none");
			});
			
			$("#heart-fill").on("click",function(){
				
				$("#heart-fill").addClass("d-none");
				$("#heart").removeClass("d-none");
			});
			
			$("#uploadBtn").on("click",function(){
				
				let content = $("#contentInput").val().trim();
				
				if(content == ""){
					alert("내용을 입력해주세요!!");
					
					return;
				}
				
				// 파일 선택 유효성 검사
				// $("#fileInput")[0].files[0]
				if($("#fileInput")[0].files.length == 0){
					alert("이미지를 선택하세요!");
					return;
				}
				
				var formData = new FormData();
				formData.append("content", content);
				formData.append("file", $("#fileInput")[0].files[0]);
				
				
				// 사용자가 입력한 content 로 api를 호출해서 데이터를 입력한다.
				$.ajax({
					
					type:"post",
					url:"/post/create",
					data:formData,
					enctype:"multipart/form-data",
					processData:false,
					contentType:false,
					success:function(data){
						if(data.result == "success"){
							location.reload();
						}else{
							alert("업로드 실패!!");
							
						}
					},
					error:function(){
						alert("업로드 에러!");
					}
				});
			});
		});
	</script>
</body>
</html>