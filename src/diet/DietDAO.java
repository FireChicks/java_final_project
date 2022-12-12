/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author LeeJanyun
 */
public class DietDAO {
     private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public DietDAO() {
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
        
        public int getNext() {
		String SQL = "SELECT dietID  FROM diet ORDER BY dietID DESC";
		try {
			PreparedStatement pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1) + 1;
			}
			return 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
	}
        
        public int insertDiet(Diet diet) {
            String SQL = "Insert Into diet values(?,?,?,now(),?)";
                ArrayList<Diet> dietList = new ArrayList<Diet>();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, getNext());
                        pstmt.setString(2, diet.getUserID());
                        pstmt.setString(3, diet.getDietmenu());
                        pstmt.setInt(4, diet.getMenuCount());
                        pstmt.setInt(5, diet.getMenuCalorie());                        
			return pstmt.executeUpdate();                     
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1;
        }
        
         public ArrayList<Diet> currentDiet(String userID) {
		String SQL = "SELECT * FROM diet WHERE userID = ? AND dietDate BETWEEN DATE_ADD(NOW(),INTERVAL -1 WEEK ) AND NOW()";
                ArrayList<Diet> dietList = new ArrayList<Diet>();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
                        while (rs.next()) {
                            Diet diet = new Diet();
                            diet.setDietID(rs.getInt(1));
                            diet.setUserID(rs.getString(2));
                            diet.setDietmenu(rs.getString(3));
                            diet.setMenuCount(rs.getInt(4));
                            diet.setDietDate(rs.getString(5));
                            diet.setMenuCalorie(rs.getInt(6));
                           dietList.add(diet);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dietList;
	}
}
