package com.khs.sns.post.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khs.sns.post.like.dao.LikeDAO;

@Service
public class LikeBO {
	
	@Autowired
	private LikeDAO likeDAO;
	
	public int addLike(int postId, int userId) {
		
		return likeDAO.insertLike(postId, userId);
	}
	// postId로 좋아요 개수 조회
	public int countLike(int postId) {
		return likeDAO.selectCountLike(postId);
	}
	
	// 특정 postId 에 로그인한 사용자의 userId로 좋아요 했는지 여부
	public boolean isLike(int postId, int userId) {
		// postId 와 UserId 가 모두 일치하는 행의 개수
		int count = likeDAO.selectCountLikeByPostId(postId, userId);
		
		if(count == 0 ) {
			return false;
		}else {
			return true;
		}
	}
	
}
