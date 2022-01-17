package src.mybbs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.mybbs.dao.ContentsDao;
import src.mybbs.vo.ContentsVo;

/**
 * Servlet implementation class PagingCon2
 */
@WebServlet("/PagingCon2")
public class PagingCon2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String menu_name = request.getParameter("menu_name");
		
		ContentsDao dao = new ContentsDao();
		//ContentsVo list = new ContentsVo();
		
		List<ContentsVo> list = null;
		try {
			list = dao.getMenuConList(menu_name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 String path = "pagingindex.jsp";
		 
		 request.setAttribute("menu_name", menu_name);
		 request.setAttribute("pageCon", list);
		 request.getRequestDispatcher(path).forward(request, response);
	}

}
