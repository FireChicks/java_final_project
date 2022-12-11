/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author LeeJanyun
 */
public class UserDAO {
        private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhost:3306/FP";
			String dbID = "root";
			String dbPassword = "root";
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
        
                public int login(String userID, String userPassword) {
		String SQL = "SELECT  CONVERT(AES_DECRYPT(unhex(userPassword), SHA2('dldi1021', 256)) USING utf8) FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if(rs.getString(1).equals(userPassword)) {
					return 1; //로그인 성공
				} else {
					return 0; //비밀번호 불일치
				}
			}
			return -1; //아이디가 없는경우
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; //데이터 베이스 오류
	}
        
        
        public int join(User user) {
		String SQL = "INSERT INTO USER VALUES (?, HEX(AES_ENCRYPT(?, SHA2('dldi1021', 256))))";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			return pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
        
          public int checkID(String userID) {
		String SQL = "select userID from user where userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
                        if (rs.next()) {
					return 1; 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
}
