<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
		<header id="header" class="bg-secondary d-flex align-items center justify-content-between">
			<h2 class="pt-3 pl-3 text-center">Stargram</h2>
			<%-- session에 userId 가 값이 저장되어 있으면 로그아웃 링크 보여주기 --%>
			<c:if test="${not empty userId }">
				<div class="mr-3 mt-3 text-white">${userloginId }님 <a href="/user/signout">로그아웃</a></div>
			</c:if>
		</header>