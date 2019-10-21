package org.zerock.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.stream.IntStream;

import javax.sql.DataSource;

public class TestCP { // Ver. pool

	public static void main(String[] args) { //pooling 없이 코딩 ㄴㄴ

		DataSource ds = CPUtil.INSTANCE.getDS();
		
		long start = System.currentTimeMillis();
		IntStream.range(1, 101).forEach(i -> {
			try (Connection con = ds.getConnection();
					PreparedStatement pstmt = con.prepareStatement("select sysdate from dual");
					ResultSet rs = pstmt.executeQuery();
					) {
				System.out.println(con);

				rs.next();

				System.out.println(rs.getString(1));
				
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		});
		long end = System.currentTimeMillis();

		System.out.println(end - start);
	}
}
