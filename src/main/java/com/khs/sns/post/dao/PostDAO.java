package com.khs.sns.post.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDAO {
	public int insertPost(@Param("content") String content
			,@Param("userId") int userId);
}
