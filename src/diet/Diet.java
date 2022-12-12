/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diet;

/**
 *
 * @author LeeJanyun
 */
public class Diet implements Comparable<Diet> {
    private int dietID; 
    private String userID;
    private String dietmenu; 
    private int menuCount;
    private String dietDate; 
    private int menuCalorie;

    public int getDietID() {
        return dietID;
    }

    public void setDietID(int dietID) {
        this.dietID = dietID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDietmenu() {
        return dietmenu;
    }

    public void setDietmenu(String dietmenu) {
        this.dietmenu = dietmenu;
    }

    public int getMenuCount() {
        return menuCount;
    }

    public void setMenuCount(int menuCount) {
        this.menuCount = menuCount;
    }

    public String getDietDate() {
        return dietDate;
    }

    public void setDietDate(String dietDate) {
        this.dietDate = dietDate;
    }

    public int getMenuCalorie() {
        return menuCalorie;
    }

    public void setMenuCalorie(int menuCalorie) {
        this.menuCalorie = menuCalorie;
    }
    
    @Override
    public int compareTo(Diet diet) {
        if (diet.dietDate.compareTo(dietDate) < 0) {
            return 1;
        } else if (diet.dietDate.compareTo(dietDate) > 0) {
            return -1;
        }
        return 0;
    }
}
