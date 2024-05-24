 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.util.Vector;
 
 
public class Param extends JDialog implements ActionListener
        {
        
            Statement st;
            ResultSet set;
            String arr[];
            JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11;
            JButton b1,b2;
            
           
            JPanel p3;
            MainClass main;
          
            
           
              public void actionPerformed(ActionEvent e)
            {   if(e.getSource()==b1)
            		{
            		    save();
            		}
            	if(e.getSource()==b2)
            		{
            			setVisible(false); 
    				 	dispose();
            		}
            }
            
        	
        	public Param(Statement st,MainClass main)
        	{    
        		 super(main, "Parameter", true);
        		 this.st=st;
        		 this.main=main;
        		
        		 JPanel p1=new JPanel(){public Dimension getPreferredSize(){return new Dimension(450,400);}};
        		 JPanel p3=new JPanel(){public Dimension getPreferredSize(){return new Dimension(450,40);}};
        		 p1.setLayout(new BorderLayout());
        		 JPanel p2=new JPanel();
        		 p2.setLayout(new GridLayout(14,2));
        		
        		 
        		 t1=new JTextField(15);
        		 t2=new JTextField(15);
        		 t3=new JTextField(15);
        		 t4=new JTextField(15);
        		 t5=new JTextField(15);
        		 t6=new JTextField(15);
        		 t7=new JTextField(15);
        		 t8=new JTextField(15);
        		 t9=new JTextField(15);
        		 t10=new JTextField(15);
        		 t11=new JTextField();
        	
        		 b1=new JButton(" SAVE ");b1.addActionListener(this);
        		 b2=new JButton("CANCEL");b2.addActionListener(this);
        		 p3.add(b1);p3.add(b2);
        		 p2.add(new JLabel("TRADE NAME :"));p2.add(t1);
        		 p2.add(new JLabel("BRANCH :"));p2.add(t2);
        		 p2.add(new JLabel("BRANCH MANAGER :"));p2.add(t3);
        		 p2.add(new JLabel("CASHIER :"));p2.add(t4);
            	 p2.add(new JLabel("ADDRESS :"));p2.add(t5);
        		 p2.add(new JLabel("CITY/MUNICIPALITY :"));p2.add(t6);
        		 p2.add(new JLabel("PROVINCE :"));p2.add(t7);
        		 p2.add(new JLabel("CUT-OFF DATE :"));p2.add(t8);
        		 p2.add(new JLabel("CLERK :"));p2.add(t9);
        		 p2.add(new JLabel("BEGIN DATE :"));p2.add(t10);
        		 p2.add(new JLabel("END DATE :"));p2.add(t11);
        		 
        		 p1.add(p2,BorderLayout.CENTER);
        		 p1.add(p3,BorderLayout.SOUTH);
        		 
        		
                                         
             
                

               
   				  load();
   				
   			  
   			  	
   		 
					
			     getContentPane().add(p1,BorderLayout.CENTER);
                 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   				 pack(); 
   				 setResizable(false);
   				 setLocation(300,100);	
   				 setVisible(true);
						
				 
               
           }
           public void load()
   		   {
    			try
    	   		{
    	   			set=st.executeQuery("Select * from [Parameters/constants]");
            		while(set.next())
            		{   
            			t1.setText(set.getString("Trade Name"));
            			t2.setText(set.getString("Branch"));
            			t3.setText(set.getString("Branch Manager"));
            			t4.setText(set.getString("Cashier"));
            			t5.setText(set.getString("Address"));
            			t6.setText(set.getString("City/Municipality"));
            			t7.setText(set.getString("Province"));
            			t8.setText(set.getString("Cut-off Date"));
            			t9.setText(set.getString("Clerk"));
            			t10.setText(set.getDate("Begin Date")+"");
            			t11.setText(set.getDate("End Date")+"");            		
            		}
            	   
    			}catch(Exception ee){ee.printStackTrace();}
   		   }
   		   public static String[]  load2()
   		   {
   		   	
   		   	    
   		   	    String input1[];
   		   	    input1=new String[11];
   		   	    Statement Sst=null;
   		   	    Connection Sconn;
   		   	    ResultSet Sset=null;
   		   	     try{ 
                     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  				
    				 Sconn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=Copy.mdb;DriverID=22;READONLY=true;) ","",""); 
    				 //conn = DriverManager.getConnection("jdbc:odbc:mydb","","");    				    			
    				 Sst=Sconn.createStatement();
    				 				 
    				 
	                 }catch(Exception e){e.printStackTrace();}  
   		   	    
    			try
    	   		{
    	   			Sset=Sst.executeQuery("Select * from [Parameters/constants]");
            		while(Sset.next())
            		{   
            			input1[0]=Sset.getString("Trade Name");
            			input1[1]=Sset.getString("Branch");
            			input1[2]=Sset.getString("Branch Manager");
            			input1[3]=Sset.getString("Cashier");
            			input1[4]=Sset.getString("Address");
            			input1[5]=Sset.getString("City/Municipality");
            			input1[6]=Sset.getString("Province");
            			input1[7]=Sset.getDate("Cut-off Date")+"";
            			input1[8]=Sset.getString("Clerk");
            			input1[9]=Sset.getDate("Begin Date")+"";
            			input1[10]=Sset.getDate("End Date")+"";            		
            		}
            	   
    			}catch(Exception ee){ee.printStackTrace();}
    			return input1;
   		   }
   		   public void save()
   		   	{
   		   		try
   		   		{
   		   			st.execute("UPDATE  [Parameters/constants] set [trade name]='"+t1.getText()+"', Branch='"+t2.getText()+"', [Branch Manager]='"+t3.getText()+"', Cashier='"+t4.getText()+"', address='"+t5.getText()+"', [City/Municipality]='"+t6.getText()+"', Province='"+t7.getText()+"', [Cut-off date]='"+t8.getText()+"', Clerk='"+t9.getText()+"', [Begin date]='"+t10.getText()+"', [end date]='"+t11.getText()+"' where no='0'");
   		   			main.load(0);
   		   		}
   		   		 
   		   		catch(Exception ee){ee.printStackTrace();JOptionPane.showMessageDialog(this,"Error ");return;}
   		   		
   		   		JOptionPane.showMessageDialog(this,"Data Save.");
   		   		setVisible(false); 
    			dispose();
   		   		
   		   	} 
                
        }