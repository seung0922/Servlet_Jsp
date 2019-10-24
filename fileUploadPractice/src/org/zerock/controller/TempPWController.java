package org.zerock.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;

/**
 * Servlet implementation class TempPWController
 */
@WebServlet("/temp")
public class TempPWController extends HttpServlet {
	
	protected void doGet(
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// 이 컨트롤러를 만드는 목적?
		// "/temp" 경로 입력하면 화면에 내가 그린거 띄우게
		
		// 화면에 요청을 해서 화면에 출력시켜야하니까 response랑 outputStream 쓴다.
		
		// 화면에 나타낼 거에 타입지정
//		response.setContentType("image/gif");
		
		// 화면에 도화지....그린다.
		BufferedImage img = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
		
		// graphics...
		Graphics g = img.getGraphics();
		
		// 붓 색 설정
		g.setColor(Color.YELLOW);
		
		// 네모칸 핑크로 그림
		g.fillRect(0, 0, 300, 300);

		// 쓰고 싶은 문자열 생성
		String str = "" + (int)(Math.random() * 1000000);
		
		// 다시 붓 색 설정
		g.setColor(Color.BLACK);
		
		// 네모칸 안에 문자열 써
		g.drawString(str, 100, 100);
		
		// 폰트 지정해
		g.setFont(new Font("Serif", Font.BOLD, 50));
		
		// 담아
		OutputStream os = response.getOutputStream();
		
		// 화면에 출력시킴
		ImageIO.write(img, "GIF", os);
		
	}

}
