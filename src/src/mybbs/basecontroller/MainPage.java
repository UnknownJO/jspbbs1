package src.mybbs.basecontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import src.mybbs.dao.BbsDao;
import src.mybbs.vo.UserVo;

/**
 * Servlet implementation class MainPage
 */
@WebServlet("/Main")
public class MainPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		BbsDao dao = new BbsDao();
		List<UserVo> list = null;
		try {
			list = dao.getMainList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		JSONObject  jsObj  = new JSONObject();
		JSONArray jsArr = new JSONArray();
		for(UserVo userList : list) {           // 구해온 list를 UserVo객체에 담고
			JSONObject obj = new JSONObject();      
			obj.put("id", userList.getId());         // UserVo객체 안의 내용들을 꺼내서 오브젝트에 담고
			obj.put("name", userList.getName());
			obj.put("email", userList.getEmail());
			obj.put("tel", userList.getTel());
			jsArr.add(obj);
		}
		jsObj.put("UserList", jsArr); //위에서 만든 json오브젝트에 배열을 담아서 제이슨오브젝트로 만들었다.
		String json = jsObj.toJSONString();
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
	}

}
