package com.khs.sns.post.comment;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khs.sns.post.comment.bo.CommentBO;

@RestController
public class CommentRestControler {
	
	@Autowired
	private CommentBO commentBO;
	
	@PostMapping("/post/comment/create")
	public Map<String, String> createComment(			
			@RequestParam("postId") int postId
			,@RequestParam("content") String content
			,HttpServletRequest request){
		
		HttpSession session = request.getSession();		
		int userId = (Integer)session.getAttribute("userId");
		
		int count = commentBO.addComment(userId, postId, content);
		
		Map<String,String> map = new HashMap<>();
		if(count == 1) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		 return map;
	}
}
