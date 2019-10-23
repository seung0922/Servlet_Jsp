package org.naruto.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.naruto.dao.BoardDAO;
import org.naruto.dao.BoardDAOImple;
import org.naruto.domain.BoardVO;
import org.naruto.dto.PageMaker;
import org.naruto.dto.PagingDTO;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("/board/*")	// board로 시작하는 모든 애들 다받음
public class BoardController extends BasicController {
	private static final long serialVersionUID = 1L;
       
	private BoardDAO dao;
	
	public BoardController() {
		dao = new BoardDAOImple();
	}

	
	@RequestMapping(value = "/board/register", type = "GET")
	public String add(HttpServletRequest req, HttpServletResponse res) throws Exception {
		

		System.out.println("add...............");
		return "/board/register" ;
	}
	
	@RequestMapping(value = "/board/list", type = "GET")
	public String list(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//파라미터 수집 -> pageDTO 변환
		PagingDTO dto = new PagingDTO(req.getParameter("page"), req.getParameter("amount"));
		//Model 완성
		req.setAttribute("list", dao.getList(dto));
		req.setAttribute("pm", new PageMaker(dao.getCount(),dto));
		System.out.println("list...............");
		return "/board/list";
	}
	@RequestMapping(value = "/board/register", type = "POST")
	public String addPost(HttpServletRequest req,
			HttpServletResponse res)throws Exception {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String writer = req.getParameter("writer");
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(writer);
		BoardDAO dao = new BoardDAOImple();
		boolean insert = dao.insert(vo);
		System.out.println(insert);
		System.out.println("add.post.................");
		System.out.println(req.getParameter("title"));
		
		return "redirect:/list";
	}
	@RequestMapping(value="/board/delete", type="get")
	public String deleteGet(HttpServletRequest req, HttpServletResponse res) {
		String bno = req.getParameter("bno");
		BoardVO vo = new BoardVO();
		Long bnos = Long.parseLong(bno);
		vo.setBno(bnos);
		BoardDAO dao = new BoardDAOImple();
		boolean delete = dao.delete(bnos);
		System.out.println(delete);
		return "redirect:/list";
	}
	@RequestMapping(value="/board/read", type="GET")
	public String Read(HttpServletRequest req, HttpServletResponse res) {
		long bno = Long.parseLong(req.getParameter("bno"));
		req.setAttribute("board", dao.select(bno));
		
		return "/board/read";
	}

	

}
