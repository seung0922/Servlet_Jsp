//package com.codechobo;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@WebServlet("/TodayTestServlet")
//public class FortuneServlet extends HttpServlet {
//	static HashMap result = new HashMap();
//	static String[] arr = { "운세1", "운세2", "운세3", "운세4", "운세5", "운세6", "운세7", "운세8", "운세9", "운세10", "운세11", "운세12",
//			"운세13", "운세14", "운세15", "운세16", "운세17", "운세18", "운세19", "운세20", "운세21", "운세22", "운세23", "운세24", "운세25",
//			"운세26", "운세27", "운세28", "운세29", "운세30", "운세31", "운세32", "운세33", "운세34", "운세35", "운세36", "운세37", "운세38",
//			"운세39", "운세40", };
//
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// 1. 사용자에게 입력받은 값 갖고 온다.
//		String name = request.getParameter("name");
//		String age = request.getParameter("age");
//		String date = request.getParameter("date");
//
//		// 2. 작업 한다.
//		// 1. 사용자 입력받은 데이터 합쳐서 문자열로 저장한다.
//		String userData = name + age + date;
//
//		// 2. Map에 해당 사용자 데이터 있는지 확인한다. 
//		if (!result.containsKey(userData)) { // 2-1. 해당 사용자 데이터 없으면
//			// 운세 랜덤으로 값 받아와서 
//			int r = (int) (Math.random() * arr.length);
////			String unsaeRandom = arr[r];
//
//			// key에는 유저데이터, value에는 운세 랜덤값 넣는다.
//			result.put(userData, unsaeRandom);
//
//		}
//		
//		
//		// 텍스트파일 읽어오는거
//		ArrayList wordList = new ArrayList();
//		try {
//			FileReader fr = new FileReader("fortune.txt");
//			BufferedReader br = new BufferedReader(fr);
//			
//			String line = "";
//			int i = 0;
//			while((line=br.readLine())!=null) {
//				// 2. 읽어온 라인을 wordList에 저장한다.
//				wordList.add(line);
////				System.out.println(i + ":" + line);
//				i++;
//			}
//			
//			br.close();
//			
//		} catch(IOException e) {
//			// 3. 만일 예외가 발생하면 프로그램을 종료한다.
//			System.out.println("예외");
//			System.exit(0);
//		}
//
//		// 3. 화면에 출력
//		// 한글파일 안깨지게 해주는 설정
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html; charset=UTF-8");
//
//		PrintWriter out = response.getWriter();
//
//		out.println("<!DOCTYPE html>");
//		out.println("<html lang='en'>");
//		out.println("<head>");
//		out.println("    <meta charset='UTF-8'>");
//		out.println("    <title>Document</title>");
//		out.println("<body>");
//		out.println("    <h1>오늘의 운세</h1>");
//		out.println("");
//		out.println("    <div>");
//		out.println("        <form>");
//		out.println("            <label for='name'>이름</label>");
//		out.println("            <input type='text' name='name' ><br>");
//		out.println("            <label for='age'>나이</label>");
//		out.println("            <input type='text' name='age'><br>");
//		out.println("            <label for='date'>날짜</label>");
//		out.println("            <input type='text' name='date' maxlength='8' placeholder='8글자이상 ex)19950922'>");
//		out.println("            <input type='submit' id='btn'></input><br>");
//		out.println("        </form>");
//		out.println("            <textarea id='memo' cols='30' rows='10'></textarea>");
//		out.println("            <p id='txt'></p>");
//		out.println("    </div>");
//		out.println("    <script src='https://code.jquery.com/jquery-1.11.3.js'></script>");
//		out.println("    <script>");
//		out.println("        $(document).ready(function() {");
//		out.println("            $('#btn').click(function() {");
////		out.println("                alert('dd')");
//		out.println("                $('#memo').val('" + result.get(userData) + "');");
////		out.println("                $('#txt').html('" + result.get(userData) + "');");
//		out.println("            });");
//		out.println("        });");
//		out.println("    </script>");
//		out.println("</body>");
//		out.println("</html>");
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		doGet(request, response);
//	}
//
//}
