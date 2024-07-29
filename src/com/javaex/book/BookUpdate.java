package com.javaex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class BookUpdate {

	System.out.println("book 수정");
	
	// 0. import java.sql.*;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	Scanner sc = new Scanner(System.in);
	
	
	try {
		// 1. JDBC 드라이버 (Oracle) 로딩
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		
		// 2. Connection 얻어오기
		String url = "jdbc:mysql://localhost:3306/book_db";
		conn = DriverManager.getConnection(url, "book", "book");
		
		
		// 3. SQL문 준비 / 바인딩 / 실행
		
		// - sql문 준비 
		String query = "";
		query += " update book ";
		query += " set title = ? , ";
		query += " pubs = ? , ";
		query += " pub_date = ? , ";
		query += " author_id = ? ";
		query += " where book_id = ? ";
	
		//바인딩 
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, " 삼국지2 ");
		pstmt.setString(2, " 중국회사 ");
		pstmt.setString(3, "2021/04/18");
		pstmt.setInt(4, 4); 
		pstmt.setInt(5, 2); 
		
		// 실행
		int count = pstmt.executeUpdate();
		
		
		// 4.결과처리
		System.out.println(count + "건 수정 되었습니다.");

}
}
