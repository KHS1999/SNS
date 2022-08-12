package com.khs.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khs.sns.post.bo.PostBO;

@RestController
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	// 게시글 작성 api
	@PostMapping("/post/create")
	public Map<String,String> createPost(
			@RequestParam("content") String content

			,HttpServletRequest request) {
		// 글쓴 사람 정보를 같이 저장하기 위해서
		// 로그인된 사용자의 id(user 테이블 pk)를 세션을 통해서 얻어내고 이를 사용한다.
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		int count = postBO.addPost(content,userId);
		
		Map<String,String> result = new HashMap<>();
		if(count == 1) {
			result.put("result","success");
		}else {
			result.put("result", "fail");
		}
		return result;
	}
	
	
	
}