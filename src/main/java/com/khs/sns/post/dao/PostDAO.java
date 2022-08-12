package com.khs.sns.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.khs.sns.post.model.Post;

@Repository
public interface PostDAO {
	public int insertPost(@Param("content") String content
			,@Param("userId") int userId
			,@Param("imagePath") String imagePath);
	
	public List<Post> selectPostList();
}