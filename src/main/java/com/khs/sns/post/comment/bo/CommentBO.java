package com.khs.sns.post.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khs.sns.post.comment.dao.CommentDAO;
import com.khs.sns.post.model.Comment;
import com.khs.sns.post.model.CommentDetail;
import com.khs.sns.user.bo.SNSBO;
import com.khs.sns.user.model.User;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	@Autowired
	private SNSBO userBO;
	
	public int addComment(int userId,int postId, String content) {
		return commentDAO.insertComment(userId, postId, content);
	}
	
	// postId 를 대상으로 해당하는 댓글 들을 조회 기능
	public List<CommentDetail> getCommentListById(int postId){
		
		List<Comment> commentList = commentDAO.selectCommentListByPostId(postId);
		
		List<CommentDetail> commentDetailList = new ArrayList<>();
		
		for(Comment comment : commentList) {
			
			int userId = comment.getUserId();
			User user = userBO.getUserById(userId);
			
			CommentDetail commentDetail = new CommentDetail();
			commentDetail.setComment(comment);
			commentDetail.setUser(user);
			
			commentDetailList.add(commentDetail);
		}
		
		return commentDetailList;
	}
	
	// 해당하는 postId 의 모든 댓글 삭제
	public int deleteComment(int postId) {
		return commentDAO.deleteComment(postId);
	}
}
