package com.khs.sns.post.like;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khs.sns.post.like.bo.LikeBO;

@RestController
public class LikeRestController {
	
	@Autowired
	private LikeBO likeBO;
	
	@GetMapping("/post/like")
	public Map<String, String> like(
			@RequestParam("postId") int postId
			, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> map = new HashMap<>();
		
		int count = likeBO.addLike(postId, userId);
		
		if(count == 1) {
			
			map.put("result", "success");
		}else {
			map.put("result","fail");
		}
		return map;
	}

	// 좋아요 취소 api
	@GetMapping("/post/unlike")
	public Map<String, String> unlike(
			@RequestParam("postId") int postId
			,HttpServletRequest request){
		
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("userId");
		
		int count = likeBO.unlike(postId, userId);
		
		Map<String, String> map = new HashMap<>();
		
		if(count == 1) {
			map.put("result", "success");
		}else {
			map.put("result", "fail");
		}
		return map;
	}
		
}
