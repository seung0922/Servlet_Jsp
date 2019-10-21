package org.zerock.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.zerock.domain.StoreVO;
import org.zerock.dto.PositionSearchDTO;
import org.zerock.util.CPUtil;

import lombok.extern.log4j.Log4j;

@Log4j
public enum StoreDAO {
	
	INSTANCE;
	
	final String FIND_POSITION = "select sno, sname, lat, lng\r\n" + 
			"from \r\n" + 
			"  (\r\n" + 
			"      select \r\n" + 
			"      rownum rn, sno, sname, lat, lng,\r\n" + 
			"      distance\r\n" + 
			"        from \r\n" + 
			"          (\r\n" + 
			"            select \r\n" + 
			"              sno, sname,lat, lng,\r\n" + 
			"              sqrt( power(lat - ?,2) + \r\n" + 
			"                power(lng - ?,2) ) distance\r\n" + 
			"            from tbl_store\r\n" + 
			"            order by 5 ) store \r\n" + 
			"        where rownum <= (? * 5)\r\n" + 
			"  )\r\n" + 
			"where rn > (? - 1) * 5";
	
	public List<StoreVO> findByPosition(PositionSearchDTO dto) {
		
		// 결과로 반환하기 위한 리스트 선언
		List<StoreVO> result = null;
		
		// 연결하기 위한 변수들 선언
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			log.info(dto);
			
			// db와 연결
			con = CPUtil.INSTANCE.getDS().getConnection();
			pstmt = con.prepareStatement(FIND_POSITION);
			
			// 파라미터로 받은 값을 바인드 변수에 집어넣는다.
			pstmt.setDouble(1, dto.getCurrLat());
			pstmt.setDouble(2, dto.getCurrLng());
			pstmt.setInt(3, dto.getPage());
			pstmt.setInt(4, dto.getPage());
			
			rs = pstmt.executeQuery();
			
			result = new ArrayList<>();
			
			// 한줄씩 값을 받아와서 리스트에 저장한다.
			// sno, sname, lat, lng 을 한 인덱스로 받아서 저장!
			while(rs.next()) {
				// svo 객체를 만드는 이유는
				// 파라미터에 여러 변수 지저분하게 넣는 것보다
				// 변수 모아놓은 객체를 하나 넣는 것이 더 깔끔하고 오류도 줄일 수 있기 때문
				
				// 원래
//				StoreVO svo = new StoreVO();
//				svo.setSno(rs.getLong(1));
//				svo.setSname(rs.getString(2));
//				svo.setLat(rs.getDouble(3));
//				svo.setLng(rs.getDouble(4));
//				result.add(svo);
				
				// Builder 썼을때
				result.add(StoreVO.builder()
						.sno(rs.getLong(1))
						.sname(rs.getString(2))
						.lat(rs.getDouble(3))
						.lng(rs.getDouble(4))
						.build());
				
			}
			
		} catch(Exception e) {
			log.error(e.getMessage());
		} finally {
			if(rs != null) try { rs.close(); } catch(Exception e) {}
			if(pstmt != null) try { pstmt.close(); } catch(Exception e) {}
			if(con != null) try { con.close(); } catch(Exception e) {}
		}
		
		return result;
	}
}
