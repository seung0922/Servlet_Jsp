package org.zerock.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.stream.IntStream;

public class TestOld { 
	public static void main(String[] args) throws Exception { //connection 연결 많으면 위험 => 뻗음
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		long start = System.currentTimeMillis();
		String url="jdbc:oracle:thin:@10.10.10.135:1521:XE";
		String userName = "student";
		String password = "1234";
		IntStream.range(1, 101).forEach(i ->{ //100개만에 문제
			try(Connection con = DriverManager.getConnection(url,userName,password);
					PreparedStatement pstmt = con.prepareStatement("select sysdate from dual");
					ResultSet rs = pstmt.executeQuery();
					){ 
//				System.out.println(con);
				
				rs.next();
				
				System.out.println(rs.getString(1));
				
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		});
		long end = System.currentTimeMillis();
		
		System.out.println(end-start);
	}
}
