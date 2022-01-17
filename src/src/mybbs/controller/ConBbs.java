package src.mybbs.controller;

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

import src.mybbs.dao.ContentsDao;
import src.mybbs.vo.ContentsVo;


@WebServlet("/conbbs")
public class ConBbs extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		String whichmenu = request.getParameter("whichmenu");  
		System.out.println(whichmenu);
		// -----------------------------------------------------------------
		
		// -----------------------------------------------------------------
		ContentsDao dao = new ContentsDao();
		
		 List<ContentsVo> conlist = null; 
		 
			try {
				conlist = dao.getMenuConList(whichmenu);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			JSONObject jsObj = new JSONObject();
			JSONArray jsArr = new JSONArray();

			for (ContentsVo ContentsVo : conlist) {
				JSONObject obj = new JSONObject();
				obj.put("row_num", ContentsVo.getRow_num());
				obj.put("con_num", ContentsVo.getCon_num());
				obj.put("menu_name", ContentsVo.getMenu_name());
				obj.put("con_title", ContentsVo.getCon_title());
				obj.put("con_writer", ContentsVo.getCon_writer());
				obj.put("con_writingdate", ContentsVo.getCon_writingdate());
				obj.put("con_hitcount", ContentsVo.getCon_hitcount());
				jsArr.add(obj);

			}
			jsObj.put("contents", jsArr); // 위에서 만든 json오브젝트에 배열을 담아서 제이슨오브젝트로 만들었다.
			String json = jsObj.toJSONString();
			PrintWriter out = response.getWriter();
			out.print(json);
			out.flush();
			out.close();
		 
	}

	
}
