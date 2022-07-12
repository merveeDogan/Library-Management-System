package Code;

import java.sql.Connection;
import java.sql.DriverManager;

public class LibraryDB { 
	 static Connection myConn;
	
   
	@SuppressWarnings("exports")
	public static Connection getConnection() throws Exception{
        if(myConn == null){
            Class.forName("com.mysql.cj.jdbc.Driver"); //throws exception if class doesn't match
           myConn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/librarysql", "root", "1972Merv");
        }
        return myConn;
    }
}