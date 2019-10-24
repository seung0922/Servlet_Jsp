package org.zerock.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

/**
 * Servlet implementation class FileViewController
 */
@WebServlet("/fileView")
public class FileViewController extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 이 컨트롤러의 목적이 뭘까...?
		// 웹 화면에 파라미터(파일의 이름)로 지정해준 파일 보여준다.
		
		// 파라미터로 지정해준 값 가져와야겠지? fname으로 갖고와!
		String fname = request.getParameter("fname");
		
		// 파일 객체 생성해! ( 원래 그 파일이 들어있는 경로로 )
		File file = new File("C:\\zzz\\upload\\" + fname);
		
		Path path = new File(fname).toPath();
		String mineType = Files.probeContentType(path);
		
		// 파일이름 영어로 변환해! 한글은 깨지니까....
		fname = new String(fname.getBytes("UTF-8"), "8859_1");
		
		// 근데 이미지면 그냥 보여주고 아니면 다운로드 해주고싶어
		if(!mineType.equals("image/jpeg")) {
			
			response.setContentType("application/octet-stream");	// 브라우저에 내려받을 수 있게 헤더에 심어준다.
			response.setHeader("Content-Disposition", "attachment; filename=" + fname + ";");
			
		}
		
		// 출력하려면 outputStream 필요해
		OutputStream os = response.getOutputStream();
		
		// 그 경로에 있는 파일 화면에 보여줌
		FileUtils.copyFile(file, os);
		
	}

}
