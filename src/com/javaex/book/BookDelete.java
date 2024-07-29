package com.javaex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BookDelete {

	public static void main(String[] args) {

		System.out.println("book 삭제");

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
			query += " delete from book ";
			query += " where book_id = ? ";

			// - 바인딩,(1,5)의 의미는 1번째에서 5번째에 해당하는 데이터. 1은 자동으로 증가하는 book_id 칸을 의미하기 때문에
			// 고정.(ㄹㅇ? 이거 맞나?)
			// 바뀌는 부분은 5에 해당하는 영역. 즉, book_id의 n번째 부분을 삭제하기 위해선 (1,n)을 입력하면 된다.
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, 5);

			// - 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + "건 삭제 되었습니다.");

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

		sc.close();
	}
}
