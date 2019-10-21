package com.codechobo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FortuneAction")
public class FortuneAction extends HttpServlet {
	// 유저데이터와 운세데이터 저장할 collector 생성해놓은 클래스 생성한다.
	UserFortune cl = new UserFortune();
	// 유저데이터와 랜덤값 저장할 userMap, 운세배열 저장된 fortuneList 가져온다.
	HashMap<String, Integer> userMap = cl.userMap;
	ArrayList<String> fortuneList = cl.fortuneList();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자에게 입력받은 값 갖고 온다.
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String date = request.getParameter("date");
		
		// 2. 작업 한다.
		// 2-1. 사용자 입력받은 데이터 합쳐서 문자열로 저장한다.
		String userData = name + age + date;
		
		// 2-2 Map에 해당 사용자 데이터 있는지 확인한다. 
		if (!userMap.containsKey(userData)) { // 1) 해당 사용자 데이터 없으면
			// 2) 랜덤한 값 변수에 저장
			int r = (int) (Math.random() * fortuneList.size());

			// 3) key에는 유저데이터, value에는 랜덤값 넣는다.
			userMap.put(userData, r);
		}
		
		// 2-3. result의 key에 해당 유저 데이터값이 있으면 value(위에서 저장한 랜덤한 숫자)반환 
		int userIdx = userMap.get(userData);
		
		// 2-4. fortuneList에서 위에서 얻은 랜덤한 숫자의 위치 값 반환
		String result = fortuneList.get(userIdx);
		
		// 3. 화면에 출력
		// 한글파일 안깨지게 해주는 설정
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
	
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html lang='en'>");
		out.println("<head>");
		out.println("    <meta charset='UTF-8'>");
		out.println("    <title>Document</title>");
		out.println("<body>");
		out.println("    <h1>오늘의 운세</h1>");
		out.println("");
		out.println("    <div>");
		out.println("        <form>");
		out.println("            <label for='name'>이름</label>");
		out.println("            <input type='text' name='name' required><br>");
		out.println("            <label for='age'>나이</label>");
		out.println("            <input type='text' name='age' required><br>");
		out.println("            <label for='date'>날짜</label>");
		out.println("            <input type='date' name='date' value='운세보기'>");
		out.println("            <input type='submit' id='btn'></input><br>");
		out.println("        </form>");
		out.println("            <textarea id='memo' cols='30' rows='10'>" + result + "</textarea>");
		out.println("            <p id='txt'></p>");
		out.println("    </div>");
		out.println("</body>");
		out.println("</html>");
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
