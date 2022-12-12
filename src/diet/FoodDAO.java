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
public class FoodDAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

	public FoodDAO() {
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
     
         public ArrayList<Food> searchFoodList(String search) {
                search = "%" + search + "%";
		String SQL = "SELECT * FROM food WHERE foodName like ?";
                ArrayList<Food> foodList = new ArrayList<Food>();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, search);
			rs = pstmt.executeQuery();
                        while (rs.next()) {
                            Food food = new Food();
                            food.setFoodName(rs.getString(1));
                            food.setFoodBigCategory(rs.getString(2));
                            food.setFoodSmallCategory(rs.getString(3));
                            food.setServingSize(rs.getInt(4));
                            food.setSizeUnit(rs.getString(5));
                            food.setKacal(rs.getDouble(6));
                            food.setMoisture(rs.getDouble(7));
                            food.setProtein(rs.getDouble(8));
                            food.setFat(rs.getDouble(9));
                            food.setCarbohydrate(rs.getDouble(10));
                           foodList.add(food);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return foodList;
	}
}
