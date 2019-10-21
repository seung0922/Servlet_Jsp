package com.codechobo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DiceServlet")
public class DiceServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		// 1. 랜덤으로 값을 두개 가져온다.
		// (주사위 1~6인데 파일명을 주사위 숫자대로 만들음(1.png ~ 6.png) -> 랜덤값.png 로 주사위 이미지 출력)
		int x = (int)(Math.random() * 6) + 1;
		int y = (int)(Math.random() * 6) + 1;
		
		out.println("<!DOCTYPE html>");
		out.println("<html lang='en'>");
		out.println("<head>");
		out.println("    <meta charset='UTF-8'>");
		out.println("    <title>Document</title>");
		out.println("    <style>");
		out.println("        img { width: 200px; height: 200px;}");
		out.println("    </style>");
		out.println("</head>");
		out.println("<body>");
		out.println("    <img src='" + x + ".png'>");
		out.println("    <img src='" + y + ".png'>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
