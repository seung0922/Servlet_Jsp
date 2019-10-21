package org.zerock.server;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Fakelet2 extends Thread {	// 여러 사람이 동시에 접속할수있게 쓰레드 상속
	// 멤버변수
	private Socket socket;		// 소켓
	private InputStream in;		// 읽기
	private Scanner scanner;	// 입력받기
	private OutputStream out;	// 쓰기
	
	// 생성자
	public Fakelet2(Socket socket) throws Exception {
		this.socket = socket;
		in = socket.getInputStream();
		scanner = new Scanner(in);
		out = socket.getOutputStream();
	}
	
	@Override
	public void run() {
		try {
			String line = scanner.nextLine();
			
			String[] arr = line.split(" "); 	// 공백으로 쪼갬
			String target = arr[1].substring(1);	// aaa.jpg 뺴옴
			String app = target.substring(target.lastIndexOf(".")+1);	// .뒤부터 잘라서 저장
			String mimeType = FakeServer2.getMimeType(app);	// 확장자만 빼서 저장한 변수로 해당 key의 value를 가져와서 저장
			
			// 내가 가져오고자하는 마임타입없으면 실행
			if(mimeType == null) {
				AbstractLet let = new HelloLet();
				let.serve(in, out);
				return;
			}
			
			System.out.println("----------------------");
			System.out.println(target);
			System.out.println("----------------------");
			
			// input에 담고
			FileInputStream fin = new FileInputStream("C:\\zzz\\" + target);
			
			out.write(new String("HTTP/1.1 200 OK\r\n").getBytes()); 
		    out.write(new String("Cache-Control: private\r\n").getBytes()); 
//		    out.write(new String("Content-Length: "+ msg.getBytes().length+"\r\n")
//		    		.getBytes()); 
		    out.write(new String("Content-Type:" + mimeType + "\r\n\r\n")
		    		.getBytes());
		    
		    // 출력을 해줘야함
		    byte[] buffer = new byte[1024*8];
		    
		    while(true) {
		    	int count = fin.read(buffer);
		    	
		    	if(count == -1) {
		    		break;
		    	}
		    	out.write(buffer, 0, count);
		    }
		    
		    out.flush();
			
		} catch(Exception e) {
			System.out.println(e);
		} finally {	// 다 닫아 줘야함
			try { in.close(); } catch(Exception e) {}
			try { out.close(); } catch(Exception e) {}
			try { socket.close(); } catch(Exception e) {}
		}
	}
}
