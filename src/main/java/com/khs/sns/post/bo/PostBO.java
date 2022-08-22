package com.khs.sns.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.khs.sns.common.FileManagerService;
import com.khs.sns.post.comment.bo.CommentBO;
import com.khs.sns.post.dao.PostDAO;
import com.khs.sns.post.like.bo.LikeBO;
import com.khs.sns.post.model.CommentDetail;
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
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private CommentBO commentBO;
	
	public int addPost(String content, int userId,MultipartFile file) {
		
		// 파일을 저장하고, 파일 접근 경로를 DAO로 전달.
		String imagePath = FileManagerService.saveFile(userId, file);	
		
		// 파일 저장이 실패한 경우
		if(imagePath == null) {
			
			return -1;
		}
		
		return postDAO.insertPost(content,userId,imagePath);
	}
	
	public List<PostDetail> getPostList(int loginUserId){
		
		List<PostDetail> postDetailList = new ArrayList<>();
		
		List<Post> postList = postDAO.selectPostList();
				
		for(Post post : postList) {
			
			int userId = post.getUserId();
			int postId = post.getId();
			
			// user 테이블 조회
			// userBo를 통해서 userId 와 일치하는 사용자 정보 조회
			User user = snsBO.getUserById(userId);
			int likeCount = likeBO.countLike(postId);
	   		List<CommentDetail> commentList = commentBO.getCommentListById(postId);
		 	boolean isLike =  likeBO.isLike(postId,loginUserId );
	   		
			// 게시글과 사용자 정보를 합치는 과정
			PostDetail postDetail = new PostDetail();
			postDetail.setPost(post);
			postDetail.setUser(user);
			postDetail.setLikeCount(likeCount);
			postDetail.setCommentList(commentList);
			postDetail.setLike(isLike);
			
			postDetailList.add(postDetail);
			
		}
		
		return postDetailList;
	}
	
	public int deletePost(int postId,int userId) {
		
		Post post = postDAO.selectPost(postId);
		int count =  postDAO.deletePost(postId, userId);		
		// 게시글 삭제

		if(count == 1) {	
		// 파일 삭제
		// 파일 경로 알아 오기
				FileManagerService.removeFile(post.getImagePath());
		
	
		// 댓글 삭제
		commentBO.deleteComment(postId);
		// 좋아요 삭제
		likeBO.deleteLikeByPostId(postId);
			
		}
		
		return count;

	}
}
