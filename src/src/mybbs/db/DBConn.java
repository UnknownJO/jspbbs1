package src.mybbs.db;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConn {
	
	private  Connection   conn =  null;
	
	public   Connection   getConnection() {

		Properties   prop = new Properties();          //프로퍼티스 객체화
		 
		try { //DBConn으로 가져올 것이고,그파일정보와 경로를 가져와 변수에 담는다.
		    String  path    =  DBConn.class.getResource(
		    		"db.properties").getPath(); 
			Reader  reader  =  new FileReader(path);   //가져온 정보,경로를 읽고 담아.
			prop.load( reader );                         //읽어온 객체를 로딩
			
			String  driver = prop.getProperty("driver"); //getProperty로 정보 읽어와서 담음.
			String  dburl  = prop.getProperty("dburl");
			String  dbuid  = prop.getProperty("dbuid");
			String  dbpwd  = prop.getProperty("dbpwd");
			
			Class.forName(driver);                       //읽어온 정보로 다시 연결에 활용.
			conn  =  DriverManager.getConnection(dburl, dbuid, dbpwd);			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		 
		 return conn;
	}
	
}