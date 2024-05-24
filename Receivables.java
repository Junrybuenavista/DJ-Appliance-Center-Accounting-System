 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.util.Vector;
 
 
public class Receivables
        {
        
            Statement st;
            ResultSet set;
            String arr[];
            JComboBox box1;         
            JLabel lab1;
            JPanel p3;
            MainClass main;
            Statement stt1=null;
  		 	Statement stt2=null;
            java.util.Date d;
           
         
          
        	
        	public Receivables(MainClass main)
        	{
        		 this.st=st;
        		 this.main=main;
        		 
        		 JPanel p1=new JPanel(){public Dimension getPreferredSize(){return new Dimension(200,60);}};
        		 p1.setLayout(new BorderLayout());
        		 JPanel p2=new JPanel();
        		 p2.setLayout(new GridLayout(2,1));
        		
        	
        		  Connection conn1,conn2;	
  		 		  
  		 		  ResultSet set1;	
  			 	  try{ 
                     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  				
    				 conn1 = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=Copy.mdb;DriverID=22;READONLY=true;) ","","");
    				 conn2 = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=Copy.mdb;DriverID=22;READONLY=true;) ","","");  
    				 //conn = DriverManager.getConnection("jdbc:odbc:mydb","","");    				    			
    				 stt1=conn1.createStatement();
    				 stt2=conn2.createStatement();
    				 
    				 set1=stt1.executeQuery("Select * from [Parameters/constants]");set1.next();
    			     d=set1.getDate("Cut-off Date");
    				 
    					
    				 			 
    				 
	                 }catch(Exception e){e.printStackTrace();}
        	    
        		 box1=new JComboBox();
        	
        		 p2.add(new JLabel("SELECT COLLECTOR :"));p2.add(box1);
        		
        		
        		 
        		
        		 
        		 p1.add(p2,BorderLayout.CENTER);
        		
        		 
        		 Object[] message = new Object[1];          
                 message[0]=p1;
                                         
             
                

                 String[] options = {"PRINT","CANCEL"};
                 load();
		    		int result = JOptionPane.showOptionDialog(
		    		main,
		    		message,
		    		"",
		    		JOptionPane.DEFAULT_OPTION,
		    		JOptionPane.INFORMATION_MESSAGE,
		    		null,
		    		options,
		    		options[0]

						);
					if(result==0)
						{  
							
							
   				    		 try
   				    		 	{
   				    		 	 
   				    		 	  new Receivables2(stt1,stt2,d,main,box1.getSelectedItem()+"");		
   				    		 	}
   				    		 catch(Exception eee){}	
   				    
   				    	}
   				  
   				
   			  
   			  	
   		 
					
			
						
				 
               
           }
           public void load()
   		   {
    			try
    	   		{
    	   			set=stt1.executeQuery("Select * from Collector");
            		while(set.next())
            		{
            			box1.addItem(set.getString("Collector"));            		
            		}
    			}catch(Exception ee){}
   		   }
                
        }