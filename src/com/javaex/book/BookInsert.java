package com.javaex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookInsert {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");

			// 3. SQL문 준비 / 바인딩 / 실행
			// *sql문 준비 (insert 문을 자바의 문자열로 만든다
			// String query ="insert into author values(null, '기안84', '웹툰작가')" ;
			String query = "";
			query += " insert into book ";
			query += " values(null, ?, ? ,? ,? ,? ,?) ";

			// *바인딩
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, " 패션왕 ");
			pstmt.setString(2, " 중앙북스(books) ");
			pstmt.setString(3, " 2012-02-22 ");
			pstmt.setString(4, " 4 ");
			pstmt.setString(5, " 기안84 ");
			pstmt.setString(6, " 기안동에서 산 84년생 ");

			// *실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 등록되었습니다.");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}

		}

	}

}
