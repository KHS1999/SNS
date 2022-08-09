package com.khs.sns.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {
	
	// 회원가입 페이지
	@GetMapping("/user/signup/view")
	public String signUp() {
		return "/user/signup";
	}
	
	// 로그인 페이지
	@GetMapping("/user/signin/view")
	public String signIn() {
		return "/user/signin";
	}
	
	// 로그아웃
	@GetMapping("/user/signout")
	public String signOut(HttpServletRequest request) {
		// 로그아웃 과정
		// 세션에 로그인 정보 제거
		
		HttpSession session = request.getSession();
		session.removeAttribute("userId");
		session.removeAttribute("userLoginId");
		
		return "redirect:/user/signin/view";
		
	}
}
