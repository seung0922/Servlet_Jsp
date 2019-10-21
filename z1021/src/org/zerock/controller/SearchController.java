package org.zerock.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.zerock.dao.StoreDAO;
import org.zerock.domain.StoreVO;
import org.zerock.dto.PositionSearchDTO;
import org.zerock.util.StringUtil;

import lombok.extern.log4j.Log4j;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/search")
@Log4j
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// �Ķ���� ����
		// �Ķ���� �Է¾����� �� (null)�϶��� ����Ͽ� ����
		String latStr = request.getParameter("lat");
		latStr = latStr == null ? "37.570444" : latStr;
		
		String lngStr = request.getParameter("lng");
		lngStr = lngStr == null ? "126.985320" : lngStr;
		
		String pageStr = request.getParameter("page");
		pageStr = pageStr == null ? "1" : pageStr;
		
		// �ʿ��� DTO ����
		// DAO�� findByPosition�� �־��� dto
		PositionSearchDTO dto = new PositionSearchDTO();
		
		// �Ķ���ʹ� String Ÿ���̹Ƿ� ������ �Ķ���͸� ���ϴ� Ÿ������ ���������� StringUtil ����
		dto.setCurrLat(StringUtil.getDouble(latStr));
		dto.setCurrLng(StringUtil.getDouble(lngStr));
		dto.setPage(StringUtil.getInt(pageStr));
		
		// DAO ȣ��
		List<StoreVO> result = StoreDAO.INSTANCE.findByPosition(dto);
		
		log.info("test");
		
		// ����� request�� ���
		request.setAttribute("list", result);
		request.setAttribute("msg", "HelloWorld");
		
		// request, response�� view������ �о���
		request
			.getRequestDispatcher("/WEB-INF/jsp/search.jsp")
			.forward(request, response);
		
	}


}
