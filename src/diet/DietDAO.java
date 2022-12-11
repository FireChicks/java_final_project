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
