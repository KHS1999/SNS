package com.khs.sns.post.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.khs.sns.post.model.Comment;

@Repository
public interface CommentDAO {

	public int insertComment(
			@Param("userId") int userId
			,@Param("postId")int postId
			,@Param("content") String content);
	
	public List<Comment> selectCommentListByPostId(@Param("postId") int postId);
	
	public int deleteComment(@Param("postId") int postId);
}
