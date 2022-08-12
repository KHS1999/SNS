<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
		<header id="header" class=" d-flex justify-content-center">
			<div class="d-flex">
				<h2 class="pt-3 pl-3 text-center">Stargram</h2>
					<div class="input-group ml-3 mt-3 mb-3">
				  	<input type="text" class="form-control" placeholder="친구 게시글 검색하기(id)" aria-label="Recipient's username" aria-describedby="basic-addon2">
				  <div class="input-group-append">
				    <button class="btn btn-outline-info" type="button">검색</button>
				  </div>
			</div>
			</div>
			<%-- session에 userId 가 값이 저장되어 있으면 로그아웃 링크 보여주기 --%>
			<c:if test="${not empty userId }">
				<div class="mr-3 mt-3 ml-3">${userloginId }님 <a href="/user/signout">로그아웃</a></div>
			</c:if>
		</header>