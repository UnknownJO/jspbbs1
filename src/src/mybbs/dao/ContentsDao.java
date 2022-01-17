package src.mybbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.mybbs.db.DBConn;
import src.mybbs.vo.ContentsVo;
import src.mybbs.vo.MenuVo;


public class ContentsDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 각메뉴 내용출력
	public List<ContentsVo> getMenuConList(String whichmenu) throws SQLException{
		List<ContentsVo> list = new ArrayList<>();
		try {
			DBConn db = new DBConn();
			conn = db.getConnection();
			String sql ="SELECT NUM, M.*"
					+ " FROM (SELECT ROWNUM NUM, N.*"
					+ " FROM (SELECT ROWNUM R, CONTENTS.* FROM CONTENTS "
					+ " WHERE MENU_NAME=? ORDER BY CON_NUM DESC)N)M"
					+ " WHERE NUM BETWEEN 1 AND 10";
					
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, whichmenu);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int row_num = rs.getInt("NUM");
				int con_num = rs.getInt("CON_NUM");
				String menu_name = rs.getString("MENU_NAME");
				String con_title = rs.getString("CON_TITLE");
				String con_writer = rs.getString("CON_WRITER");
				String con_writingdate = rs.getString("CON_WRITINGDATE");
				int con_hitcount = rs.getInt("CON_HITCOUNT");
				
				
				ContentsVo result = new ContentsVo(row_num, con_num, menu_name, con_title, con_writer,
						con_writingdate, con_hitcount);
				list.add(result);
				
			};
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if( pstmt != null) pstmt.close();
			if( conn != null ) conn.close();
	}
		
		return list;
	}
	
	
	
	//검색하기
	public List<ContentsVo> getSearch(String searchword){
		List<ContentsVo> list = new ArrayList<ContentsVo>();
		String sql = "SELECT ROWNUM, CONTENTS.*"
				+ " FROM CONTENTS"
				+ " WHERE CON_TITLE LIKE ? OR CON_WRITER LIKE ? OR CON_CONTENT LIKE ?"
				+ " AND ROWNUM < 11"
				+ " ORDER BY CON_NUM ASC";
				
		try {
			DBConn db = new DBConn();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"%"+searchword+"%");
			pstmt.setString(2,"%"+searchword+"%");
			pstmt.setString(3,"%"+searchword+"%");
			 
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int row_num = rs.getInt("ROWNUM");
				int con_num = rs.getInt("CON_NUM");
				String menu_name = rs.getString("MENU_NAME");
				String con_title = rs.getString("CON_TITLE");
				String con_writer = rs.getString("CON_WRITER");
				String con_writingdate = rs.getString("CON_WRITINGDATE");
				int con_hitcount = rs.getInt("CON_HITCOUNT");
				
				ContentsVo result = new ContentsVo(row_num, con_num, menu_name, con_title, con_writer,
						con_writingdate, con_hitcount);
				list.add(result);
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	// 새글쓰기
	public String writebbs(String menu_name, String writer, String writetitle, String writecontent) {
		
		String sql = "INSERT INTO CONTENTS(CON_NUM, MENU_NAME, CON_TITLE, CON_WRITER, CON_CONTENT )"
				   + " VALUES(CON_SEQ.NEXTVAL, ?, ?, ?, ?)";
		int writeresult = 0;
		try {
			DBConn db = new DBConn();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menu_name);
			pstmt.setString(2, writer);
			pstmt.setString(3, writetitle);
			pstmt.setString(4, writecontent);
			writeresult = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(writeresult == 0) {
		return "글쓰기에 실패하였습니다.";
		} else {
			return "글이 저장되었습니다.";
		}
		
	}
	//글 정보보기
	public ContentsVo viewcon(int con_num) {
		ContentsVo result = new ContentsVo();
		
		String sql = "SELECT CON_NUM, MENU_NAME, CON_TITLE, CON_WRITER, CON_CONTENT FROM CONTENTS"
				   + " WHERE CON_NUM=?";
		
		try {
			DBConn db = new DBConn();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, con_num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){

			             con_num = rs.getInt("CON_NUM");
				String menu_name = rs.getString("MENU_NAME");
				String con_title = rs.getString("CON_TITLE");
				String con_writer =rs.getString("CON_WRITER");
				String con_content = rs.getString("CON_CONTENT");
				
				result = new ContentsVo(con_num, menu_name, con_title, con_writer, con_content);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}
	
	// 메뉴정보 가져오기
		public List<MenuVo> getMenu(){
			
			List<MenuVo> list = new ArrayList<MenuVo>();
			
			String sql = "SELECT MENU_NAME"
					+ " FROM MENULIST";
			
			try {
				DBConn db = new DBConn();
				conn  = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs    = pstmt.executeQuery();
				
				while(rs.next()) {
				
				//String menu_num  = rs.getString("MENU_NUM");
				String menu_name = rs.getString("MENU_NAME");
				MenuVo menu = new MenuVo();
				menu.setMenu_name(menu_name);
				list.add(menu);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return  list;
			
		}
	public List<ContentsVo> updateform(int con_num) {
		List<ContentsVo> list = new ArrayList<ContentsVo>();
		String sql = "SELECT * FROM CONTENTS WHERE=CON_NUM=?";
				
				try {
					DBConn db = new DBConn();
					conn = db.getConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, con_num);
					rs = pstmt.executeQuery();
					while(rs.next()) {
						con_num = rs.getInt("CON_NUM");
						String menu_name   = rs.getString("MENU_NAME");
						String con_writer  = rs.getString("CON_WRITER");
						String con_title   = rs.getString("CON_TITLE");
						String con_content = rs.getString("CON_CONTENT");
						
					ContentsVo result = new ContentsVo(con_num, menu_name, con_writer, con_title, con_content);
					list.add(result);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
						
	}
	//글내용 수정
	public String updateAction(String menu_name, String con_title, String con_writer, 
			String con_content, String con_num) {
		
		String sql = "UPDATE CONTENTS"
				+ " SET MENU_NAME=?, CON_TITLE=?, CON_WRITER=?, CON_CONTENT=?"
				+ " WHERE CON_NUM=?";
		
		int updateResult = 0;
		try {
			DBConn db = new DBConn();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menu_name);
			pstmt.setString(2, con_title);
			pstmt.setString(3, con_writer);
			pstmt.setString(4, con_content);
			pstmt.setString(5, con_num);
			updateResult = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(updateResult == 0) {
			return "수정에 실패하였습니다.";
			} else {
				return "글이 수정되었습니다.";
			}
		
	}
	
	//글삭제
	public String con_delete(int con_num) {
		String sql = "DELETE CONTENTS"
				+ " WHERE CON_NUM=?";
		
		int deleteResult = 0;
		try {
			DBConn db = new DBConn();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, con_num);
			deleteResult = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(deleteResult == 0) {
			return "글삭제가 실패하였습니다.";
			} else {
				return "성공적으로 삭제되었습니다.";
			}
	}
	//페이징 처리
	public List<ContentsVo> pageCon(String menu_name, int start, int end) {
		List<ContentsVo> list = new ArrayList<>();

		String sql = "SELECT NUM, M.*"
				+ "FROM (SELECT ROWNUM NUM, N.*"
				+ "FROM (SELECT ROWNUM R, CONTENTS.* FROM CONTENTS "
				+ " WHERE MENU_NAME=? ORDER BY CON_NUM DESC)N)M"
				+ " WHERE NUM BETWEEN ? AND ?";

		System.out.printf("%s %s", start, end);
		try {
			DBConn db = new DBConn();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, menu_name);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			while (rs.next()) {

				int row_num = rs.getInt("NUM");
				int con_num = rs.getInt("CON_NUM");
				menu_name = rs.getString("MENU_NAME");
				String con_title = rs.getString("CON_TITLE");
				String con_writer = rs.getString("CON_WRITER");
				String con_writingdate = rs.getString("CON_WRITINGDATE");
				int con_hitcount = rs.getInt("CON_HITCOUNT");

				ContentsVo result = new ContentsVo(row_num, con_num, menu_name, con_title, con_writer, con_writingdate,
						con_hitcount);
				list.add(result);
				System.out.println(row_num);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}
