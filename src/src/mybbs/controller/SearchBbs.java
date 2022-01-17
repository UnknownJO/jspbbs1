package src.mybbs.controller;

import java.io.IOException;
import java.io.PrintWriter;
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


@WebServlet("/searchbbs")
public class SearchBbs extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String searchword_ = request.getParameter("searchword");  
		ContentsDao dao = new ContentsDao();
		
		String searchword = "";
		// 빈값이 전달될 경우 진행이 안되도록
		if(searchword_ != null && !searchword_.equals("")) {
			searchword = searchword_;
		
		List<ContentsVo> searchlist = dao.getSearch(searchword);
		
		JSONObject  jsObj  = new JSONObject();
		JSONArray jsArr = new JSONArray();
		
		for(ContentsVo ContentsVo : searchlist) {
			JSONObject obj = new JSONObject();
			obj.put("row_num", ContentsVo.getRow_num());
			obj.put("menu_name", ContentsVo.getMenu_name());
			obj.put("con_title", ContentsVo.getCon_title());
			obj.put("con_writer", ContentsVo.getCon_writer());
			obj.put("con_content", ContentsVo.getCon_content());
			obj.put("con_writingdate", ContentsVo.getCon_writingdate());
			obj.put("con_hitcount", ContentsVo.getCon_hitcount());
			jsArr.add(obj);
		}
		jsObj.put("contents", jsArr); //위에서 만든 json오브젝트에 배열을 담아서 제이슨오브젝트로 만들었다.
		String json = jsObj.toJSONString();
		PrintWriter out = response.getWriter();
		out.print(json);
		out.flush();
		out.close();
		}
	}
}
