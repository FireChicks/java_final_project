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
			String dbURL = "jdbc:mysql://125.180.23.159:3306/FP";
			String dbID = "dldi1021";
			String dbPassword = "1234";
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
		String SQL = "INSERT INTO USER VALUES (?, HEX(AES_ENCRYPT(?, SHA2('dldi1021', 256))),?,?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
                        pstmt.setString(3, user.getUserGender());
                        pstmt.setInt(4, user.getUserCalorie());
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
          public User getUser(String userID){
              String SQL = "select * from user where userID = ?";
              User user = new User();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
                        if (rs.next()) {
                        user.setUserID(rs.getString(1));
                        user.setUserGender(rs.getString(2));
                        rs.getString(3);
                        user.setUserCalorie(rs.getInt(4));
                        
			return user; 
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
          }
}
