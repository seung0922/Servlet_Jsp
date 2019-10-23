package org.naruto.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.naruto.domain.BoardVO;
import org.naruto.dto.PagingDTO;
import org.naruto.util.MyBatisLoader;

public class BoardDAOImple implements BoardDAO {
	private SqlSessionFactory factory = MyBatisLoader.INSTANCE.getFactory();
	@Override
	public BoardVO select(Long bno) {
		BoardVO result = null;
		try {
		
			SqlSession session = factory.openSession();
			 result = session.selectOne("org.naruto.dao.BoardMapper.select",bno);
			 System.out.println(result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}


	public static void main(String[] args) {
		BoardDAO dao = new BoardDAOImple();
		BoardVO vo = new BoardVO();
		PagingDTO dto = new PagingDTO();
//		dao.getCount();
//		dto.setPage(5);
//		dto.setAmount(10);
//		dao.getList(dto);
//		dao.delete(15L);
//		vo.setBno(3l);
//		vo.setTitle("UPDATE");
//		vo.setContent("UPDATE TEST");
//		vo.setWriter("업데이트");
//		dao.update(vo);
//		dao.select(5L);
	}


	@Override
	public boolean insert(BoardVO vo) {
		// TODO Auto-generated method stub
		boolean result = false;
		int a = 0;
		try {
		
			SqlSession session = factory.openSession();
			 a = session.insert("org.naruto.dao.BoardMapper.insert",vo);
			 session.commit();
			 System.out.println(result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		result=a==1?true:false;
		return result;
	}

	@Override
	public boolean update(BoardVO vo) {
		boolean result = false;
		try {
		
			SqlSession session = factory.openSession();
			 int upd = session.update("org.naruto.dao.BoardMapper.update",vo);
			 session.commit();
			 result=upd==1?true:false;
			 System.out.println(result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean delete(Long key) {
		boolean result = false;
		try {
		
			SqlSession session = factory.openSession();
			int del = session.update("org.naruto.dao.BoardMapper.delete",key);
			 session.commit();
			 result=del==1?true:false;
			 System.out.println(result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<BoardVO> getList(PagingDTO dto) {
		List<BoardVO> result = null;

		try (SqlSession session = factory.openSession()) {
			
			result = session.selectList("org.naruto.dao.BoardMapper.selectList",dto);
			System.out.println(result);
			  
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int getCount() {
		int result = 0;
		try {
			
			SqlSession session = factory.openSession();
			 result = session.selectOne("org.naruto.dao.BoardMapper.getCount");
			 System.out.println(result);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
