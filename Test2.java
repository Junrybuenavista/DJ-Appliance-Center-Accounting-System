import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.DecimalFormat;

public class Test2  {

    Statement st;
    Connection conn;
    ResultSet set;
 
    public Test2()
    {
    
    	 try{ 
                     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  				
    				 conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=Copy.mdb;DriverID=22;READONLY=true;) ","",""); 
    				 //conn = DriverManager.getConnection("jdbc:odbc:mydb","","");    				    			
    				 st=conn.createStatement();
    				 
    				 st.execute("INSERT INTO [Transaction Register]([Doc #],[DR #]) VALUES('88898','887776')");
    				 
    		    set=st.executeQuery("Select * from [Transaction Register]");   	       	
           		
    	
    				 System.out.println("FUCK");				 
    				 
	      }catch(Exception e){e.printStackTrace();}    	
    	
    }
    public static void main(String args[])
    {
    	new Test2();
    }
}