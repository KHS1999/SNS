package com.khs.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khs.sns.common.EncryptUtils;
import com.khs.sns.user.dao.SNSDAO;
import com.khs.sns.user.model.User;


@Service
public class SNSBO {
	@Autowired
	private SNSDAO snsDAO;
	
	public int addUser(String loginId, String password,String name, String email) {
		
		String encryptPassword = EncryptUtils.md5(password);
		return snsDAO.insertUser(loginId, encryptPassword,name, email);
	}
	
	// 아이디를 전달 받고 중복 여부를 알려주는 메소드
	public boolean isDuplicate(String loginId) {
		// 일치하는 loginId 개수 전달 받고,
		// 0일 경우 불일치
		// 그게 아닐경우 중복
		int count = snsDAO.selectCountLoginId(loginId);
		if(count == 0) {
			return false;
		}else {
			return true;
		}
	}
	
	public User getUser(String loginId, String password) {
		String encryptPassword = EncryptUtils.md5(password);
		return snsDAO.selectUser(loginId, encryptPassword);
	}
}
	
