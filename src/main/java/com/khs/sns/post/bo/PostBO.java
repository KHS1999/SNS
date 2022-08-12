package com.khs.sns.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khs.sns.post.dao.PostDAO;
import com.khs.sns.post.model.Post;
import com.khs.sns.post.model.PostDetail;
import com.khs.sns.user.bo.SNSBO;
import com.khs.sns.user.model.User;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private SNSBO snsBO;
	
	public int addPost(String content, int userId) {
		return postDAO.insertPost(content,userId, "");
	}
	
	public List<PostDetail> getPostList(){
		
		List<PostDetail> postDetailList = new ArrayList<>();
		
		List<Post> postList = postDAO.selectPostList();
		
		for(Post post : postList) {
			
			int userId = post.getUserId();
			// user 테이블 조회
			// userBo를 통해서 userId 와 일치하는 사용자 정보 조회
			User user = snsBO.getUserById(userId);
			
			// 게시글과 사용자 정보를 합치는 과정
			PostDetail postDetail = new PostDetail();
			postDetail.setPost(post);
			postDetail.setUser(user);
			
			postDetailList.add(postDetail);
			
		}
		
		return postDetailList;
	}
}
