package com.khs.sns.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khs.sns.user.bo.SNSBO;
import com.khs.sns.user.model.User;




@RestController
public class UesrRestController {
	
	@Autowired
	private SNSBO snsBO;
	// 회원가입 API
	@PostMapping("/user/signup")
	public Map<String,String> signUp(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,@RequestParam("name") String name
			,@RequestParam("email") String email
			) {
		int count = snsBO.addUser(loginId, password,name, email);
		
		Map<String,String> result = new HashMap<>();
		if(count == 1) {
			result.put("result", "success");
		}else {
			result.put("result", "fail");
		}
		return result;
	}
	
	// 아이디 중복확인 API
	@GetMapping("/user/duplicate_id")
	public Map<String,Boolean> isDuplicate(String loginId){
//		{"is_duplicate" : "true"}
//		{"is_duplicate" : "false"}
		Map<String,Boolean> result = new HashMap<>();
		
		if(snsBO.isDuplicate(loginId)) { // 중복된 경우
			result.put("is_duplicate" , true);
		}else { // 중복되지 않은 경우 
			result.put("is_duplicate" , false);
		}
		return result;
	}
	
	// 로그인 API
	@PostMapping("/user/signin")
	public Map<String, String> signIn(
			@RequestParam("loginId") String loginId
			,@RequestParam("password") String password
			,HttpServletRequest request){
		
		User user = snsBO.getUser(loginId, password);
		
		Map<String, String> result = new HashMap<>();
		
		if(user != null) { // 로그인 성공
			
			result.put("result","success");
			
			HttpSession session = request.getSession();
			// user id, user loginId
			session.setAttribute("userId", user.getId());
			session.setAttribute("userloginId", user.getLoginId());
			
			}else{ // 로그인 실패
				result.put("result","fail");
		}
		
		return result;
	}
}
