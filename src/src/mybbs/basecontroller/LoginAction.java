package src.mybbs.basecontroller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.mybbs.dao.BbsDao;

@WebServlet("/LoginAction")
public class LoginAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		
		String result = null;
		BbsDao dao = new BbsDao();
		
		if(uid != null && uid != "") {
		try {
			result = dao.logincheck(uid, pwd);
			if(result.equals("x")) {
				String path = "login.jsp";
				request.setAttribute("result", "비밀번호가 틀렸습니다.");
				request.getRequestDispatcher(path).forward(request, response);
			}else if(result.equals("error")) {
				String path = "login.jsp";
				request.setAttribute("result", "아이디가 존재하지 않습니다.");
				request.getRequestDispatcher(path).forward(request, response);
			}else {
				HttpSession session = request.getSession();
				session.setAttribute("username", result);
				response.sendRedirect("index.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		}
	}
}
