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
 * Servlet implementation class UpdateAction
 */
@WebServlet("/UpdateAction")
public class UpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String menu_name = request.getParameter("menu_name");
		String con_title = request.getParameter("title");
		String con_writer  = request.getParameter("writer");
		String con_content = request.getParameter("content");
		String con_num  = request.getParameter("con_num");
		
		ContentsDao dao = new ContentsDao();
		String result = dao.updateAction(menu_name, con_title, con_writer, con_content, con_num);
		String html = "";
		       html += "<html>";
		       html += "<h2>"+result+"</h2>";
		       html += "<a href='javascript:window.history.go(-2)'>"+"돌아가기"+"</a>";
		       html += "</html>";
		PrintWriter out = response.getWriter();
		out.print(html);
		out.flush();
		out.close();
	}

}
