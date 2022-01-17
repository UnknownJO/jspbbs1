package src.mybbs.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.mybbs.dao.ContentsDao;
import src.mybbs.vo.ContentsVo;

@WebServlet("/PagingCon")
public class PagingCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		String menu_name = request.getParameter("menu_name");
		System.out.println(pageNum);
		
		int start = 1 + (pageNum - 1) * 10;
		int end = pageNum * 10;

		ContentsDao dao = new ContentsDao();
		//ContentsVo list = new ContentsVo();
		
		List<ContentsVo> list = dao.pageCon(menu_name, start, end);
		System.out.println(list);
		 String path = "pagingindex.jsp";
		 
		 request.setAttribute("menu_name", menu_name);
		 request.setAttribute("pageCon", list);
		 request.getRequestDispatcher(path).forward(request, response);
		 
		//response.sendRedirect("pagingindex.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
