package Code;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
public class MainScreen{
  
  public void listBook(){
    
  }
  public void showUser(){
    
  }
  public static void main(String[] args) {
		try {
			Connection myConn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/librarysql", "root", "1972Merv");
			Statement myStat = (Statement) myConn.createStatement();
			ResultSet myRs = ((java.sql.Statement) myStat).executeQuery("select * from book");
			while(myRs.next()) {
				System.out.println(myRs.getString("ISBN"));
			}
		}
	    catch (Exception e) {
	    	e.printStackTrace();
	    }	
	}
}