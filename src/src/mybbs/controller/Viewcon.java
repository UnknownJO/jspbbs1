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
import src.mybbs.vo.MenuVo;


/**
 * Servlet implementation class viewcon
 */
@WebServlet("/viewcon")
public class Viewcon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		int con_num = Integer.parseInt(request.getParameter("con_num"));
		ContentsDao dao = new ContentsDao();
		
		// 객체 상태로 그냥 받았고 request에 저장해서 응답으로 보냈다.
		ContentsVo viewlist = dao.viewcon(con_num);
		
		List<MenuVo> menulist = dao.getMenu();
		System.out.println("서블릿에서"+menulist);
		
		
		request.setAttribute("menuList", menulist);
		request.setAttribute("viewList", viewlist);
		//System.out.println("서블릿에서menulist:"+menulist);
		//System.out.println("서블릿에서viewList:"+viewlist);
		String path = "WEB-INF/view.jsp";
		request.getRequestDispatcher(path).forward(request, response);
		
		// response.sendRedirect("view.html");
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
