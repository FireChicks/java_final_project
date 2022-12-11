/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package user;

/**
 *
 * @author LeeJanyun
 */
public class User {
    private String userID;
    private String userPassword;
    private String userGender;
    private int userCalorie;
    
    public String getUserID() {
	return userID;
    }
    public void setUserID(String userID) {
	this.userID = userID;
    }
    public String getUserPassword() {
	return userPassword;
    }
    public void setUserPassword(String userPassword) {
	this.userPassword = userPassword;
    }
    public void setUserGender(String userGender){
        this.userGender = userGender;
    }
    public String getUserGender(){
        return userGender;
    }
    public void setUserCalorie(int userCalorie){
        this.userCalorie = userCalorie;
    }
    public int getUserCalorie(){
        return userCalorie;
    }
}
