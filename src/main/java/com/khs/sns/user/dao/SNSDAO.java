package com.khs.sns.user.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.khs.sns.user.model.User;

@Repository
public interface SNSDAO {
	
	public int insertUser(
			@Param("loginId") String loginId
			,@Param("password") String password
			,@Param("name") String name
			,@Param("email") String email
			);
	
	// 전달된 loginId가 몇개인지 리턴하는 메소드
	public int selectCountLoginId(@Param("loginId") String loginId);
	
	public User selectUser(
			@Param("loginId") String loginId
			,@Param("password") String password
			);
	
	public User selectUserById(@Param("id") int id);
}
