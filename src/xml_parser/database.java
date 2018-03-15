package xml_parser;

import java.sql.*;
import xml_parser.Address;

public class database {
   // JDBC driver name and database URL
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/Address";

	   static final String USER = "root";
	   static final String PASS = "";
   public void store(String lines, String city, String state, String postalCode, String countryCode)
   {
   Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);
      stmt = conn.createStatement();
      String sql;
      //sql = "INSERT into postal"+ "Values(""+) ";
      stmt.executeUpdate("INSERT INTO POSTAL (city,state,postalcode,countrycode,street) VALUES ('"+city+"','"+state+"','"+postalCode+"','"+countryCode+"','"+lines+"')");
     
      stmt.close();
      conn.close();
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
 
   }
   
   public String search(String lines, String city, String state, String postalCode, String countryCode)
   {
	   Connection conn = null;
	   Statement stmt = null;
	   String k="";
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");
	      conn = DriverManager.getConnection(DB_URL,USER,PASS);
	      stmt = conn.createStatement();
	      String sql;
	      //sql = "INSERT into postal"+ "Values(""+) ";
	      
	      String q="Select * from postal where city='"+city+"' and state='"+state+"'and postalcode='"+postalCode+"'and countrycode='"+countryCode+"'and street='"+lines+"'";
	      ResultSet rs=stmt.executeQuery(q);
	      //stmt.executeUpdate("INSERT INTO POSTAL (city,state,postalcode,countrycode,street) VALUES ('"+city+"','"+state+"','"+postalCode+"','"+countryCode+"','"+lines+"')");
	      if(rs.next())
			{
				do{ 
					k=rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5);
					//System.out.println(rs.getString(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5));
				}while(rs.next());
			}
			else
			{
				return null;
			}
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	 return k;
   }
   
   
   
}