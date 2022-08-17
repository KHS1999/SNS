package com.khs.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	public static final String FILE_UPLOAD_PATH = "D:\\김효석\\springproject\\sns\\upload/";
	
	// 파일을 저장하고 접근 가능한 경로 리턴하는 기능
	public static String saveFile(int userId,MultipartFile file) {
		// 파일을 어디에 저장할지
	//	D:\\김효석\\springproject\\upload/3_486551321/asdf.jpg	
		// 디렉토리를 새로 만든다.
		// 디렉토리 이름 규칙
		// 사용자 (userId)
		// 시간 정보 - UNIX 타임 : 1970년 1월 1일을 기준으로 몇 millisecond(1/1000) 가 지났는지를 표현
		// /3_486551321/asdf.jpg
		
		String directoryName = userId + "_" + System.currentTimeMillis() + "/";
		
		// 디렉토리 생성
		String filePath = FILE_UPLOAD_PATH + directoryName;
		File directory = new File(filePath);
		if(directory.mkdir() == false) {
			// 디렉토리 생성 실패
			return null;
		}
			// 파일 저장
			
			try{
				
				byte[] bytes = file.getBytes();
					
		//	D:\\김효석\\springproject\\upload/3_486551321/asdf.jpg	
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path,bytes);
			
			}catch(IOException e){
				e.printStackTrace();
			return null;
			}
			
			// 외부에서 접근 가능한 경로 리턴
			// 규칙 정하기
			// /images/3_486551321/asdf.jpg
			// <img src="/images/3_486551321/asdf.jpg >
			
			return "/images/" + directoryName + file.getOriginalFilename();
			
			
		}
}
