 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.util.Vector;
 
 
public class EditTrans
        {
        
            Statement st;
            ResultSet set;
            String arr[];
           
            JComboBox t1;
          
            JLabel lab1;
            JPanel p3;
            MainClass main;
            String MyDR="";
            
           
         
          
        	
        	public EditTrans(Statement st,MainClass main,String dr)
        	{
        		 this.st=st;
        		 this.main=main;
        		 MyDR=dr;
        		 JPanel p1=new JPanel(){public Dimension getPreferredSize(){return new Dimension(150,45);}};
        		 p1.setLayout(new BorderLayout());
        		 JPanel p2=new JPanel();
        		 p2.setLayout(new GridLayout(2,1));
        		
        	
        		
        	    
        		 t1=new JComboBox();
        	
        		 p2.add(new JLabel("ENTER DOC # :"));p2.add(t1);
        		 
        		
        		 
        		
        		 
        		 p1.add(p2,BorderLayout.CENTER);
        		 load();
        		 
        		 Object[] message = new Object[1];          
                 message[0]=p1;
                                         
             
                

                 String[] options = {"ENTER","CANCEL"};
                 
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
   				    		 	   new EditTrans2(st,main,t1.getSelectedItem()+"");
   				    		 	}
   				    		 catch(Exception eee){}	
   				    
   				    	}
   				  
   				
   			  
   			  	
   		 
					
			
						
				 
               
           }
           public void load()
   		   {
    			try
    	   		{   
    	   			set=st.executeQuery("Select * from [Transaction Register] where [DR #]='"+MyDR+"'");
            		while(set.next())
            		{
            			t1.addItem(set.getString("Doc #"));            		
            		}
            	  
    			}catch(Exception ee){}
   		   }
         
                
        }