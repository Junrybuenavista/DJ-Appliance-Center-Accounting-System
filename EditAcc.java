 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.util.Vector;
 
 
public class EditAcc
        {
        
            Statement st;
            ResultSet set;
            String arr[];
            
            JTextField t1;
          
            JLabel lab1;
            JPanel p3;
            MainClass main;
          
            
           
         
          
        	
        	public EditAcc(Statement st,MainClass main)
        	{
        		 this.st=st;
        		 this.main=main;
        		 
        		 JPanel p1=new JPanel(){public Dimension getPreferredSize(){return new Dimension(200,60);}};
        		 p1.setLayout(new BorderLayout());
        		 JPanel p2=new JPanel();
        		 p2.setLayout(new GridLayout(2,1));
        		
        	
        		
        	    
        		 t1=new JTextField();
        	
        		 p2.add(new JLabel("ENTER DR # :"));p2.add(t1);
        		
        		
        		 
        		
        		 
        		 p1.add(p2,BorderLayout.CENTER);
        		
        		 
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
   				    		 	   
   				    		 	}
   				    		 catch(Exception eee){}	
   				    
   				    	}
   				  
   				
   			  
   			  	
   		 
					
			
						
				 
               
           }
         
                
        }