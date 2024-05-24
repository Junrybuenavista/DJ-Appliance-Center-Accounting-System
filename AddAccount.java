 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.util.Vector;
import java.text.DecimalFormat;
 
public class AddAccount extends JDialog implements FocusListener, ActionListener
        {
        
            Statement st;
            ResultSet set;
            String arr[];
            JTextField t1,t3,t4,t5,t6,t7,t8,t9,t10;
            JButton sel1,sel2,sel3;
            JComboBox box1,box2,box3,box4,box5;
            String selected;
            String cate;
            JLabel lab1;
            JPanel p3;
            MainClass main;
            DateClass dclass;
            JButton b1,b2;
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
            	if(e.getSource()==sel1)
   		 		{
   		 			String res=JOptionPane.showInputDialog("Please Enter Value");
   		 			box1.addItem(res);
   		 			box1.setSelectedItem(res);
   		 		}
   		 		if(e.getSource()==sel2)
   		 		{
   		 			String res=JOptionPane.showInputDialog("Please Enter Value");
   		 			box2.addItem(res);
   		 			box2.setSelectedItem(res);
   		 		}
   		 		if(e.getSource()==sel3)
   		 		{
   		 			String res=JOptionPane.showInputDialog("Please Enter Value");
   		 			box3.addItem(res);
   		 			box3.setSelectedItem(res);
   		 		}	
            }
        	public void focusGained(FocusEvent e) {
       		 	
   			 }
           
    		public void focusLost(FocusEvent e) {
    		    JTextField temp=null;
    		    try
    		    {
    		    	
    			if(e.getSource()==t6)
    				{   
    					if(t6.getText().endsWith(".00")||t6.getText().endsWith(".0"))return;
    					temp=t6;Integer.parseInt(t6.getText());
    					t6.setText(form.format(Integer.parseInt(t6.getText())));
    				}
    			if(e.getSource()==t7){if(t7.getText().endsWith(".00")||t7.getText().endsWith(".0"))return;temp=t7;Integer.parseInt(t7.getText());t7.setText(form.format(Integer.parseInt(t7.getText())));}
        		if(e.getSource()==t8){if(t8.getText().endsWith(".00")||t8.getText().endsWith(".0"))return;temp=t8;Integer.parseInt(t8.getText());t8.setText(form.format(Integer.parseInt(t8.getText())));}
        		if(e.getSource()==t9){if(t9.getText().equals("0"))return;temp=t9;Integer.parseInt(t9.getText());t9.setText(t9.getText());}
        		if(e.getSource()==t10){if(t10.getText().equals("0"))return;temp=t10;Integer.parseInt(t10.getText());t10.setText(t10.getText());}
        		}
    		    catch(Exception ee){JOptionPane.showMessageDialog(main,"Please Enter Nuber Only.");if(temp==t9||temp==t10)temp.setText("0");else temp.setText(".00");return;}
    		}
        	public AddAccount(Statement st,MainClass main)
        	{    
        		 super(main, "Add Accounts", true);
        		 this.st=st;
        		 this.main=main;
        		 this.selected=selected;
        		 JPanel p1=new JPanel(){public Dimension getPreferredSize(){return new Dimension(450,400);}};
        		 JPanel p3=new JPanel(){public Dimension getPreferredSize(){return new Dimension(450,40);}};
        		 
        		 JPanel np1=new JPanel(){public Dimension getPreferredSize(){return new Dimension(30,40);}};
        		 np1.setLayout(new GridLayout(14,1));
        		 JPanel np2=new JPanel(){public Dimension getPreferredSize(){return new Dimension(450,40);}};
        		 np2.setLayout(new BorderLayout());
        		 np2.add(np1,BorderLayout.CENTER);

        		 p1.setLayout(new BorderLayout());
        		 JPanel p2=new JPanel();
        		 p2.setLayout(new GridLayout(14,2));
        		 np2.add(p2,BorderLayout.WEST);
        		 dclass=new DateClass();
        		 
        		 t1=new JTextField(15);
        		
        		 t3=new JTextField(15);
        		 t4=new JTextField(15);
        		 t5=new JTextField(15);
        		 t6=new JTextField(".00",15);t6.addFocusListener(this);
        		 t7=new JTextField(".00",15);t7.addFocusListener(this);
        		 t8=new JTextField(".00",15);t8.addFocusListener(this);
        		 t9=new JTextField("0",15);t9.addFocusListener(this);
        		 t10=new JTextField("0",15);t10.addFocusListener(this);
                 sel1=new JButton("...");sel1.addActionListener(this);
                 sel2=new JButton("...");sel2.addActionListener(this);
                 sel2.setEnabled(false);
                 sel3=new JButton("...");sel3.addActionListener(this);
        	
        		 box1=new JComboBox();
        		 box2=new JComboBox();
        		 box3=new JComboBox();
        		 box4=new JComboBox();
        		 box5=new JComboBox();
        		 
        		 b1=new JButton(" SAVE ");b1.addActionListener(this);
        		 b2=new JButton("CANCEL");b2.addActionListener(this);
        	     
        		 p2.add(new JLabel("DR # :"));p2.add(t1);np1.add(new JLabel(""));
        		 p2.add(new JLabel("DR DATE :"));p2.add(dclass);np1.add(new JLabel(""));
        		 p2.add(new JLabel("CUSTOMER :"));p2.add(t3);np1.add(new JLabel(""));
        		 p2.add(new JLabel("ADDRESS :"));p2.add(t4);np1.add(new JLabel(""));
        		 
        		 p2.add(new JLabel("BRAND :"));p2.add(box1);np1.add(sel1);
        		 p2.add(new JLabel("UNIT :"));p2.add(box2);np1.add(sel2);
        		 p2.add(new JLabel("MODEL :"));p2.add(box3);np1.add(sel3);
        		 
        		 p2.add(new JLabel("SERIAL # :"));p2.add(t5);
        		 p2.add(new JLabel("LCP :"));p2.add(t6);
        		 p2.add(new JLabel("DOWNDPAYMENT :"));p2.add(t7);
        		 p2.add(new JLabel("INSTALLMENT :"));p2.add(t8);
        		 p2.add(new JLabel("TERM :"));p2.add(t9);
        		 p2.add(new JLabel("DUE DATE :"));p2.add(t10);
        		 p2.add(new JLabel("COLLECTOR :"));p2.add(box5);
        		 p3.add(b1);p3.add(b2);
        		 p1.add(np2,BorderLayout.CENTER);
        		 p1.add(p3,BorderLayout.SOUTH);
        		 
        		 
        		 
                                         
             
                

                 
                 load();
		    	 getContentPane().add(p1,BorderLayout.CENTER);
                 setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   				 pack(); 
   				 setLocation(300,100);	
   				 setVisible(true);
   				
   			      
   	  	
   		 
					
			
						
				 
               
           }
           public void load()
   		   {
    			try
    	   		{   
    	   			set=st.executeQuery("Select * from Brand");
            		while(set.next())
            		{
            			box1.addItem(set.getString("Field1"));            		
            		}
            	    set=st.executeQuery("Select * from Unit");
            		while(set.next())
            		{
            			box2.addItem(set.getString("Unit"));            		
            		}
            		set=st.executeQuery("Select * from Model");
            		while(set.next())
            		{
            			box3.addItem(set.getString("Model"));            		
            		}
            		set=st.executeQuery("Select * from Collector");
            		while(set.next())
            		{
            			box5.addItem(set.getString("Collector"));            		
            		}
    			}catch(Exception ee){}
   		   }
   		   public void save()
   		   	{
   		   		try
   		   		{
   		   			
   		   			st.execute("INSERT INTO [Account Entry]([DR #],[DR Date],Customer,address,brand,unit,model,[Serial #],LCP,Downpayment,Installment,Term,[Due Date],Collector) VALUES('"+t1.getText()+"','"+dclass.getSql()+"','"+t3.getText()+"','"+t4.getText()+"','"+box1.getSelectedItem()+"','"+box2.getSelectedItem()+"','"+box3.getSelectedItem()+"','"+t5.getText()+"','"+t6.getText()+"','"+t7.getText()+"','"+t8.getText()+"','"+t9.getText()+"','"+t10.getText()+"','"+box5.getSelectedItem()+"')");
   		   			
   		   			
   		   			//st.execute("INSERT INTO [Transaction Register]([Doc #],[DR #],[Transaction type],[Date],amount,discount,remarks) VALUES('"+t3.getText()+"','"+t1.getText()+"','"+box1.getSelectedItem()+"','"+dclass.getSql()+"','"+t4.getText()+"','"+t5.getText()+"','"+t6.getText()+" ')");
   		   			//set=st.executeQuery("Select * from [Account Entry]");
   		   		}
   		   		 
   		   		catch(Exception ee){ee.printStackTrace();JOptionPane.showMessageDialog(this,"Error Duplicate Value in DR #");return;}
   		   		
   		   		JOptionPane.showMessageDialog(this,"Data Save.");
   		   		setVisible(false); 
    			dispose();
   		   		
   		   	}
                
        }