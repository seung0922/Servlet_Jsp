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
		
		// ����� ��ȯ�ϱ� ���� ����Ʈ ����
		List<StoreVO> result = null;
		
		// �����ϱ� ���� ������ ����
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			log.info(dto);
			
			// db�� ����
			con = CPUtil.INSTANCE.getDS().getConnection();
			pstmt = con.prepareStatement(FIND_POSITION);
			
			// �Ķ���ͷ� ���� ���� ���ε� ������ ����ִ´�.
			pstmt.setDouble(1, dto.getCurrLat());
			pstmt.setDouble(2, dto.getCurrLng());
			pstmt.setInt(3, dto.getPage());
			pstmt.setInt(4, dto.getPage());
			
			rs = pstmt.executeQuery();
			
			result = new ArrayList<>();
			
			// ���پ� ���� �޾ƿͼ� ����Ʈ�� �����Ѵ�.
			// sno, sname, lat, lng �� �� �ε����� �޾Ƽ� ����!
			while(rs.next()) {
				// svo ��ü�� ����� ������
				// �Ķ���Ϳ� ���� ���� �������ϰ� �ִ� �ͺ���
				// ���� ��Ƴ��� ��ü�� �ϳ� �ִ� ���� �� ����ϰ� ������ ���� �� �ֱ� ����
				
				// ����
//				StoreVO svo = new StoreVO();
//				svo.setSno(rs.getLong(1));
//				svo.setSname(rs.getString(2));
//				svo.setLat(rs.getDouble(3));
//				svo.setLng(rs.getDouble(4));
//				result.add(svo);
				
				// Builder ������
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
