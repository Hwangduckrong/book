package com.javaex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookSelect {

		public static void main(String[] args) {
			
			List<Book2Vo>bookList = new ArrayList<Book2Vo>();

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
						query += " select 	book_id, ";
						query += "          title ";
						query += " 			pubs, ";
						query += " 			pub_date, ";
						query += " 			author_id ";
						query += " 			name ";
						query += " 			book_desc ";
						query += " from book ";
						query += " where name = ? ";
						
						// - 바인딩
						pstmt = conn.prepareStatement(query);
						pstmt.setString(1,"이문열");
						
						// - 실행 
						rs = pstmt.executeQuery(); 


						// 4.결과처리
						
						while (rs.next()) {
							
							int book_id = rs.getInt("book_id");
							String title = rs.getNString("title");
							String pubs = rs.getNString("pubs");
							String pub_date = rs.getNString("pub_date");
							int author_id = rs.getInt("author_id");
							String name = rs.getString("name");
							String book_desc = rs.getString("book_desc");
							Book2Vo book2Vo = new Book2Vo(book_id,title,pubs,pub_date,author_id,name,book_desc);
							bookList.add(book2Vo);
							
							
						}
									
						System.out.println("리스트 모두 출력 되었습니다.");
									
					
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

}
