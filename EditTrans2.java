 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.util.Vector;
import java.text.DecimalFormat;
 
 
public class EditTrans2 extends JDialog implements FocusListener, ActionListener
        {
        
            Statement st;
            ResultSet set;
            String arr[];
            JTextField t3,t4,t5,t6;
            JComboBox box1;
            JButton b1,b2,b3;
            JLabel lab1;
            JPanel p3;
            MainClass main;
            String MyDoc="";
            DateClass dclass;
            DecimalFormat form= new DecimalFormat("###,###.00");
            
            
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
            	if(e.getSource()==b3)
            		{
            			
            			
            			int ress=JOptionPane.showConfirmDialog(main,"Are you sure you want to delete this transaction ?");
						if(ress==JOptionPane.YES_OPTION )
							{try{
							
								st.execute("DELETE FROM [Transaction Register] WHERE [Doc #]='"+MyDoc+"'");
								JOptionPane.showMessageDialog(main,"Transaction Deleted !");
								setVisible(false); 
    				 			dispose();
								}catch(Exception ee){}
							
							}
            			
            		}
            }
            public void focusGained(FocusEvent e) {
   			 }
           
    		public void focusLost(FocusEvent e) {
    		    JTextField temp=null;
    		    try
    		    {
    		    	
    			if(e.getSource()==t4)
    				{   
    					if(t4.getText().endsWith(".00")||t4.getText().endsWith(".0"))return;
    					temp=t4;Integer.parseInt(t4.getText());
    					t4.setText(form.format(Integer.parseInt(t4.getText())));
    				}
    			   if(e.getSource()==t5){if(t5.getText().endsWith(".00")||t5.getText().endsWith(".0"))return;temp=t5;Integer.parseInt(t5.getText());t5.setText(form.format(Integer.parseInt(t5.getText())));}
        	   	}
    		    catch(Exception ee){JOptionPane.showMessageDialog(main,"Please Enter Nuber Only.");temp.setText(".00");return;}
    		}
        	
        	public EditTrans2(Statement st,MainClass main,String doc)
        	{
        		 super(main, "Edit Transaction", true);
        		 this.st=st;
        		 this.main=main;
        		 
        		 MyDoc=doc;
        		 JPanel p1=new JPanel(){public Dimension getPreferredSize(){return new Dimension(450,220);}};
        		 JPanel p3=new JPanel(){public Dimension getPreferredSize(){return new Dimension(400,40);}};
        		 p1.setLayout(new BorderLayout());
        		 JPanel p2=new JPanel();
        		 p2.setLayout(new GridLayout(7,2));
        		
        		 dclass=new DateClass();
        		
        		 
        		 t3=new JTextField(MyDoc,15);
        		 t4=new JTextField(".00",15);t4.addFocusListener(this);
        		 t5=new JTextField(".00",15);t5.addFocusListener(this);
        		 t6=new JTextField(15);
        		 b1=new JButton(" SAVE ");b1.addActionListener(this);
        		 b2=new JButton("CANCEL");b2.addActionListener(this);
        		 b3=new JButton("DELETE");b3.addActionListener(this);
        		 p3.add(b1);p3.add(b3);p3.add(b2);
        	     String arr[]={"DOWNPAYMENT","INSTALLMENT","REPOSSESS","GROSS"};
        		 box1=new JComboBox(arr);
        	
        		
        		 p2.add(new JLabel("DATE :"));p2.add(dclass);
        		 p2.add(new JLabel("DOC # :"));p2.add(t3);
        		 p2.add(new JLabel("TYPE :"));p2.add(box1);
        		 
        		
        		 
        		 p2.add(new JLabel("AMOUNT :"));p2.add(t4);
        		 p2.add(new JLabel("DISCOUNT :"));p2.add(t5);
        	  	 p2.add(new JLabel("REMARKS :"));p2.add(t6);
        		 
        		 p1.add(p2,BorderLayout.CENTER);
        		 p1.add(p3,BorderLayout.SOUTH);
        		
        		 
        		
                                         
             
                

              
   				  
   				
   			     load();
   			  	 getContentPane().add(p1,BorderLayout.CENTER);
                 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   				 pack(); 
   				 setLocation(300,100);	
   				 setVisible(true);
   		 
					
			
						
				 
               
           }
           public void save()
   		   {
   		   
   		   	
    			try
   		   		{      
   		   		 	st.execute("UPDATE  [Transaction Register] set [Date]='"+dclass.getSql()+"', amount='"+t4.getText()+"', discount='"+t5.getText()+"', remarks='"+t6.getText()+" ', [Transaction Type]='"+box1.getSelectedItem()+"', [Doc #]='"+t3.getText()+"' where [Doc #]='"+MyDoc+"'");	    
 				}
   		   		 
   		   		catch(Exception ee){ee.printStackTrace();JOptionPane.showMessageDialog(this,"Error Duplicate Value in Doc #");return;}
   		   
   		   	
   		   		JOptionPane.showMessageDialog(this,"Data Save.");
   		   		setVisible(false); 
    			dispose();
   		   }
   		   public void load()
   		   	{
				try
   		   		{      
   		   			    set=st.executeQuery("Select * from [Transaction Register] where [Doc #]='"+MyDoc+"'");set.next();
   		   			    t4.setText(form.format(Integer.parseInt(set.getString("amount"))));
   		   			    t5.setText(form.format(Integer.parseInt(set.getString("discount"))));
   		   			    t6.setText(set.getString("remarks"));
   		   			    box1.setSelectedItem(set.getString("Transaction Type"));
   		   			    
   		   			    
   		  		}
   		   		 
   		   		catch(Exception ee){ee.printStackTrace();return;}
   		   	}
                
        }