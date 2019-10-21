package org.zerock.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class FakeServer2 {
	
	// 확장자마다 content-type 다르게해주기위해 Map생성
	private static Map<String, String> mimeMap;
	
	static {
		mimeMap = new HashMap<>();
		mimeMap.put("jpg", "image/jpeg");
		mimeMap.put("mp4", "video/mp4");
		mimeMap.put("html", "text/html");
	}
	
	public static String getMimeType(String type) {
		return mimeMap.get(type);	// 문자열을 주면 map의 key에 해당하는 value 반환
	}
	
	public void runServer() throws Exception {
		ServerSocket ss = new ServerSocket(7777);
		
		System.out.println("Server Ready....2");
		
		for(int i=0; i<100; i++) { // 쓰레드 여러개 만들어주려고 for문 씀
			Socket socket = ss.accept();
			System.out.println(socket);
			
			Fakelet2 let = new Fakelet2(socket);
			let.start();	// 쓰레드니까 start해줌
		}
	}
	
	public static void main(String[] args) throws Exception {
		new FakeServer2().runServer();
	}
}
