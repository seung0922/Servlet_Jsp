package org.zerock.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.websocket.SendResult;

import org.apache.commons.io.FileUtils;

/**
 * Servlet implementation class UploadController
 */
@WebServlet("/upload")
@MultipartConfig(
		maxFileSize = 1024 * 1024 * 10,
		maxRequestSize = 1024 * 1024 * 50,
		fileSizeThreshold = 1024 * 1024,
		location = "C:\\zzz"
)
public class UploadController extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 우리가 이 컨트롤러를 만든 목적이 뭘까?
		// 파일선택해서 업로드를 누르면 우리가 지정한 폴더에 파일이 저장되게 하는 컨트롤러
		
		// 요청받은 파일들을 바운더리 별로 나누어서 컬렉션으로 저장
		Collection<Part> parts =  request.getParts();
		
		// parts를 반복문으로 돌려서 하나씩 작업한다.
		parts.forEach(part -> {
			
			// part의 fname 가져온다.
			String fname = part.getSubmittedFileName();
			
			System.out.println(fname);
			
			try {
			
				// part의 정보 출력
				System.out.printf("이름: %s, 타입: %s, 크기: %d", part.getSubmittedFileName(), part.getContentType(), part.getSize());
				
				// inputStream 생성
				InputStream in = part.getInputStream();
				System.out.println(in);
				
				// 선택한 파일을 저장하고 싶은 경로 + 이름 지정해준다.
				File file = new File("C:\\zzz\\upload\\" + System.currentTimeMillis() + fname);
				
				// 지정 경로에 파일 복사
				FileUtils.copyInputStreamToFile(in, file);
				

			} catch(Exception e) {
				e.printStackTrace();
			}
			
		});
		
	}

}
