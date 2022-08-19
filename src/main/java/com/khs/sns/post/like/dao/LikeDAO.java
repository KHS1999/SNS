package com.khs.sns.post.like.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {

	public int insertLike(
			@Param("postId")int postId
			,@Param("userId") int userId);
	
	public int selectCountLike(@Param("postId") int postId);
	
	// postId 와 UserId 가 모두 일치하는 행의 개수
	
	public int selectCountLikeByPostId(
			@Param("postId") int postId
			,@Param("userId") int userId);
}
