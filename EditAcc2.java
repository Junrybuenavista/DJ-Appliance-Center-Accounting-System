 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.util.Vector;
import java.text.DecimalFormat;
 
public class EditAcc2 extends JDialog implements FocusListener, ActionListener
        {
        
            Statement st;
            ResultSet set;
            String arr[];
            JButton sel1,sel2,sel3;
            JTextField t1,t3,t4,t5,t6,t7,t8,t9,t10;
            JComboBox box1,box2,box3,box4,box5;
            String selected;
            String cate;
            JLabel lab1;
            JPanel p3;
            MainClass main;
            DateClass dclass;
            JButton b1,b2,b3;
            String MyDR="";
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
   		 		if(e.getSource()==b3)
   		 			{
   		 				int ress=JOptionPane.showConfirmDialog(main,"Are you sure you want to delete this Account ?");
						if(ress==JOptionPane.YES_OPTION )
							{try{
							
							    st.execute("DELETE FROM [Account Entry] WHERE [Dr #]='"+t1.getText()+"'");
							
								st.execute("DELETE FROM [Transaction Register] WHERE [Dr #]='"+t1.getText()+"'");
								if(main.box1.getSelectedIndex()==0){main.load(1);}
   		 	  					if(main.box1.getSelectedIndex()==1){main.load(2);}								
								JOptionPane.showMessageDialog(main,"Account Deleted !");
								setVisible(false); 
    				 			dispose();
								}catch(Exception ee){JOptionPane.showMessageDialog(main,"ERROR !");}
							
							}
            			
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
    					if(t6.getText().equals(".00")||t6.getText().equals(".0"))return;
    					temp=t6;Integer.parseInt(t6.getText());
    					t6.setText(form.format(Integer.parseInt(t6.getText())));
    				}
    			if(e.getSource()==t7){if(t7.getText().equals(".00")||t7.getText().equals(".0"))return;temp=t7;Integer.parseInt(t7.getText());t7.setText(form.format(Integer.parseInt(t7.getText())));}
        		if(e.getSource()==t8){if(t8.getText().equals(".00")||t8.getText().equals(".0"))return;temp=t8;Integer.parseInt(t8.getText());t8.setText(form.format(Integer.parseInt(t8.getText())));}
        		if(e.getSource()==t9){if(t9.getText().equals("0"))return;temp=t9;Integer.parseInt(t9.getText());t9.setText(t9.getText());}
        		if(e.getSource()==t10){if(t10.getText().equals("0"))return;temp=t10;Integer.parseInt(t10.getText());t10.setText(t10.getText());}
        		}
    		    catch(Exception ee){JOptionPane.showMessageDialog(main,"Please Enter Nuber Only.");if(temp==t9||temp==t10)temp.setText("0");else temp.setText(".00");return;}
    		}
        	public EditAcc2(Statement st,MainClass main,String dr)
        	{    
        		 super(main, "Edit Accounts", true);
        		 this.st=st;
        		 MyDR=dr;
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
        		 
        		 t1=new JTextField(MyDR,15);
        		
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
        		 b3=new JButton("DELETE");b3.addActionListener(this);
        	
        		 p2.add(new JLabel("DR # :"));p2.add(t1);t1.setEditable(false);np1.add(new JLabel(""));
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
        		 p3.add(b1);p3.add(b3);p3.add(b2);
        		 p1.add(np2,BorderLayout.CENTER);
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
            		set=st.executeQuery("Select * from [Account Entry] where [DR #]='"+MyDR+"'");set.next();
            		t3.setText(set.getString("Customer"));
            		t4.setText(set.getString("Address"));
            		t5.setText(set.getString("Serial #"));
            		t7.setText(form.format(Integer.parseInt(set.getString("Downpayment"))));
            		t8.setText(form.format(Integer.parseInt(set.getString("Installment"))));
            		t9.setText(set.getString("Term"));
            		t10.setText(set.getString("Due Date"));
            		t6.setText(form.format(Integer.parseInt(set.getString("LCP"))));
            	
            	  
    			}catch(Exception ee){}
   		   }
   		   public void save()
   		   	{
   		   		try
   		   		{
   		   			st.execute("UPDATE  [Account Entry] set [DR Date]='"+dclass.getSql()+"', Customer='"+t3.getText()+"', Address='"+t4.getText()+"', [Serial #]='"+t5.getText()+"', LCP='"+t6.getText()+"', downpayment='"+t7.getText()+"', Installment='"+t8.getText()+"', Term='"+t9.getText()+"', [Due Date]='"+t10.getText()+"', Collector='"+box5.getSelectedItem()+"' where [DR #]='"+MyDR+"' ");
   		   			main.load(0);
   		   		}
   		   		 
   		   		catch(Exception ee){ee.printStackTrace();JOptionPane.showMessageDialog(this,"Error Duplicate Value in DR #");return;}
   		   		
   		   		JOptionPane.showMessageDialog(this,"Data Save.");
   		   		setVisible(false); 
    			dispose();
   		   		
   		   	}
            
    	   		
    	
                
        }