package com.khs.sns.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.khs.sns.post.bo.PostBO;
import com.khs.sns.post.model.PostDetail;

@Controller
public class PostController {

	// 게시글 이동
	// 게시글 조회
	@Autowired
	private PostBO postBO;

	@GetMapping("/post/timeline/view")
	public String timelineView(Model model
			,HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		
		
		// 데이터 조회해서 model 객체에 값을 저장한다.
		List<PostDetail> postDetailList = postBO.getPostList(userId);
		 
		model.addAttribute("postList",postDetailList);
		
		return "/post/timeline";
		
	}
	
	

	
}
