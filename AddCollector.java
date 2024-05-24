 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
 
 
public class AddCollector implements ActionListener 
        {
        	JTextField txt;
            
        	Statement st;
        	ResultSet set;
        	MainClass aba;
            JButton b1,b2;
            JList list;
            
            public void load()
            {
            	try
            	{   
            	    
            	    
            	    set=st.executeQuery("Select count(*) as cc from Collector");set.next();
          	        int cman=Integer.parseInt(set.getString("cc"));
            	    String arr[]=new String[cman];
            	    
            		int ctr=0;
            		set=st.executeQuery("Select Collector from Collector");
            		while(set.next())
            		{
            			arr[ctr]=set.getString("Collector");
            			ctr++;
            		}
            		list.setListData(arr);
            	}catch(Exception ee){ee.printStackTrace();}
            }
            public void actionPerformed(ActionEvent e)
            {
            	if(e.getSource()==b1)
            	{
            		 try
            		 {  
            		 	
            		 	
            		 	st.execute("Insert Into Collector(Collector) values('"+txt.getText()+"')");
            		 	load();
            		 	txt.setText("");
            		 	
            		 	JOptionPane.showMessageDialog(aba,"New Collector Added !");
            		 }catch(Exception ee){}
            	}
            	if(e.getSource()==b2)
            	{
            	    try
					   {	
            	
            		
            		
					  
				
						st.execute("Delete from Collector where Collector='"+list.getSelectedValue()+"'");
						load();
						JOptionPane.showMessageDialog(aba,"        Collector Deleted !");
					   }catch(Exception ee){ee.printStackTrace();}
					
            	 }
            }
        	
        	public AddCollector(Statement st,MainClass aba)
        	{
        		 this.aba=aba;
        		 
        		 
        		 this.st=st;
        	
        	    
        	     JPanel p11=new JPanel();
        		 JPanel p22=new JPanel();
        		 JPanel p1=new JPanel();
        		 JPanel p2=new JPanel();
	           	 JPanel p3=new JPanel(){public Dimension getPreferredSize(){return new Dimension(400,200);}}; 
	             p3.setLayout(new BorderLayout());
	           	 txt=new JTextField(25);
	           	 p11.add(new JLabel(" Enter UserName  "));
	           	 p11.add(txt);
	           	 
	           	 
	           	 
	           	 b1=new JButton("Add");
	           	 b2=new JButton("Delete");
	             
	             list=new JList();
	             load();
	             b1.addActionListener(this);
	             b2.addActionListener(this);
	             
	             
	             
	             p2.add(b1);p2.add(b2);
	             p3.add(new JScrollPane(list),BorderLayout.CENTER);
	             
	             	        		       		       
        		 Object[] message = new Object[5];          
                 message[0]=p11;
                 message[1]=p22;
                 message[2]=p1;
                 message[3]=p2;
                 message[4]=p3;                            
             
                

                 String[] options = {"Close"};
               
		    		int result = JOptionPane.showOptionDialog(
		    		aba,
		    		message,
		    		"",
		    		JOptionPane.DEFAULT_OPTION,
		    		JOptionPane.INFORMATION_MESSAGE,
		    		null,
		    		options,
		    		options[0]

						);
						
				 
               
           }
                
        }