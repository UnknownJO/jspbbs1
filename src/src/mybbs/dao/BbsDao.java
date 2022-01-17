package src.mybbs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import src.mybbs.db.DBConn;
import src.mybbs.vo.UserVo;


public class BbsDao {
	private  Connection         conn;
	private  PreparedStatement  pstmt;
	ResultSet rs;
	
	// 회원가입 관련
	public String joinPeople(String id, String pwd, String name, String email, String tel) throws SQLException{
		
		String sql = "INSERT INTO JOINMEMBER(ID,PWD,NAME,EMAIL,TEL)"
				+ "    VALUES(?,?,?,?,?)";
		try {
			DBConn db = new DBConn();
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.setString(5, tel);
			
			pstmt.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
				if( pstmt != null) pstmt.close();
				if( conn != null ) conn.close();
		}
		return "가입이 완료되었습니다.";
		
	}
	
	// 로그인 체크
	public String logincheck(String uid, String pwd) throws SQLException {
		
		String sql = "SELECT PWD, NAME  FROM JOINMEMBER"
				+ " WHERE ID=?";
		
		DBConn db = new DBConn();
		Connection conn = db.getConnection();
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, uid);
		rs = pstmt.executeQuery();
		if(rs.next()) {                          // uid가 존재하면 쿼리문 실행되면서 당연히 true로 if문 실행됨.
			String dbpwd = rs.getString("PWD");
			if(dbpwd.equals(pwd)) {                // dbpwd와 pwd가 같으면 로그인 성공
				String username = rs.getString("NAME");
				return username;
			}else {
				return "x";                       // 다르다면 실패 
			}
		}else {
			return "error";                     // rs.next();가 false라면 uid가 존재하지 않으므로 쿼리문 실행 안됐음.
		}
		
	}
	
	public List<UserVo> getList(String uid) throws SQLException{
		List<UserVo> list = new ArrayList<UserVo>();
		
		DBConn db = new DBConn();
		Connection conn = db.getConnection();
		String sql = "SELECT ID, NAME, EMAIL, TEL"
				+ "FROM JOINMEMBER"
				+ " WHERE ID=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			String id = rs.getString("ID");
			String name = rs.getString("NAME");
			String email = rs.getString("EMAIL");
			String tel = rs.getString("TEL");
			UserVo info = new UserVo(id,name,email,tel);
			list.add(info);
		}
		return list;
	}
	
	public List<UserVo> getMainList() throws SQLException{
		List<UserVo> list = new ArrayList<UserVo>();
		
		PreparedStatement pstmt = null;
		DBConn db;
		String sql = "SELECT ID, NAME, EMAIL, TEL"
				+ " FROM JOINMEMBER";
		try {
			db = new DBConn();
			Connection conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String id    = rs.getString("ID");
				String name  = rs.getString("NAME");
				String email = rs.getString("EMAIL");
				String tel   = rs.getString("TEL");
				
				UserVo result = new UserVo(id, name, email, tel);
				list.add(result);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if( pstmt != null) pstmt.close();
			if( conn != null ) conn.close();
		}
		return list;
	}
}
