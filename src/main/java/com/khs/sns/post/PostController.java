package com.khs.sns.post;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

	// 게시글 이동
	// 게시글 조회
	@GetMapping("/post/timeline/view")
	public String timelineView(Model model) {
		
		
		
		return "/post/timeline";
	}
	
	

	
}
