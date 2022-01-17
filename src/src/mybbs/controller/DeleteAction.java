package src.mybbs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.mybbs.dao.ContentsDao;

/**
 * Servlet implementation class DeleteAction
 */
@WebServlet("/DeleteAction")
public class DeleteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		int con_num = Integer.parseInt(request.getParameter("con_num"));
		String menu_name = request.getParameter("menu_name");
		
		System.out.println(con_num);
		
		ContentsDao dao = new ContentsDao();
		String result = dao.con_delete(con_num);
		
		String html = "";
			   html += "<html>";
			   html += "<h2>"+result+"</h2>";
			   html += "<a href='index.jsp'>"+"홈으로"+"</a>";
			   html += "</html>";
		PrintWriter out = response.getWriter();
		out.print(html);
		out.flush();
		out.close();
	}

}
