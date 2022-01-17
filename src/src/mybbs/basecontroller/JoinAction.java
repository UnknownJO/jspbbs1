package src.mybbs.basecontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.mybbs.dao.BbsDao;


/**
 * Servlet implementation class Join
 */
@WebServlet("/joinAction")
public class JoinAction extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		String id  = request.getParameter("uid");
		String pwd1 = request.getParameter("pwd1");
		String pwd2 = request.getParameter("pwd2");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String tel = request.getParameter("tel");
		System.out.println(id);
		String pwd = "";
		if( pwd1.equals(pwd2)) {
			pwd = pwd1;
		}else {
			String path="join.jsp";
			request.setAttribute("result", "비밀번호가 다릅니다.");
			request.getRequestDispatcher(path).forward(request, response);
		}
		String result = "";
		BbsDao dao = new BbsDao();
		try {
			result = dao.joinPeople(id, pwd, name, email, tel);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String html = "";
		   html = "<html>";
		   html += "<h2>" + result + "</h2>";
		   html += "<a href='login.jsp'>"+"로그인 이동"+"</a>";
		   html += "</html>";
		PrintWriter out = response.getWriter();
		out.print(html);
		out.flush();
		out.close();
		
	}
}
