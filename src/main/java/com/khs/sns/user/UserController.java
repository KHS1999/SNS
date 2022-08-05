package com.khs.sns.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class UserController {
	
	// 회원가입 페이지
	@GetMapping("/user/signup/view")
	public String signUp() {
		return "/user/signup";
	}

}
