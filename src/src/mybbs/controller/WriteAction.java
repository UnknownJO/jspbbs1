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
 * Servlet implementation class WriteAction
 */
@WebServlet("/WriteAction")
public class WriteAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String menu_name = request.getParameter("menu_name");
		String writer = request.getParameter("writer");
		String writetitle = request.getParameter("title");
		String writecontent = request.getParameter("content");
		
		ContentsDao dao = new ContentsDao();
		String result = dao.writebbs(menu_name, writer, writetitle, writecontent);
		
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
