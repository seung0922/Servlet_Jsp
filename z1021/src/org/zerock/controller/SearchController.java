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
		
		// 파라미터 수집
		// 파라미터 입력안했을 때 (null)일때도 고려하여 설정
		String latStr = request.getParameter("lat");
		latStr = latStr == null ? "37.570444" : latStr;
		
		String lngStr = request.getParameter("lng");
		lngStr = lngStr == null ? "126.985320" : lngStr;
		
		String pageStr = request.getParameter("page");
		pageStr = pageStr == null ? "1" : pageStr;
		
		// 필요한 DTO 생성
		// DAO의 findByPosition에 넣어줄 dto
		PositionSearchDTO dto = new PositionSearchDTO();
		
		// 파라미터는 String 타입이므로 가져온 파라미터를 원하는 타입으로 변형시켜줄 StringUtil 생성
		dto.setCurrLat(StringUtil.getDouble(latStr));
		dto.setCurrLng(StringUtil.getDouble(lngStr));
		dto.setPage(StringUtil.getInt(pageStr));
		
		// DAO 호출
		List<StoreVO> result = StoreDAO.INSTANCE.findByPosition(dto);
		
		log.info("test");
		
		// 결과를 request에 담기
		request.setAttribute("list", result);
		request.setAttribute("msg", "HelloWorld");
		
		// request, response를 view단으로 밀어줌
		request
			.getRequestDispatcher("/WEB-INF/jsp/search.jsp")
			.forward(request, response);
		
	}


}
