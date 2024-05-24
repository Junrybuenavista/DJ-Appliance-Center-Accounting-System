import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.DecimalFormat;


public class TransPrint extends JDialog implements ActionListener{
   
    DateClass From,To;
    JButton b1,b2;
    MainClass main;
    Statement st;
     public void actionPerformed(ActionEvent e)
            {   if(e.getSource()==b1)
            		{
            		try{
            		
            		   new TestTable(st,From.getSql(),To.getSql(),main);
            		   }catch(Exception eeee){eeee.printStackTrace();}
            		   
            		}
            	if(e.getSource()==b2)
            		{
            			setVisible(false); 
    				 	dispose();
            		}
            }
    public TransPrint(Statement st,MainClass main)
    {
    	super(main, "Transaction Print", true);
    	
    	 From=new DateClass();
    	 To=new DateClass();
    	 this.main=main;
    	 this.st=st;
    	 b1=new JButton("OK");b1.addActionListener(this);
    	 b2=new JButton("CANCEL");b2.addActionListener(this);
    	 JPanel p1=new JPanel(){public Dimension getPreferredSize(){return new Dimension(60,100);}};
    	 p1.setLayout(new GridLayout(2,1));
    	 JPanel p2=new JPanel();
    	 
    	 p2.setLayout(new GridLayout(2,1));
    	 p1.add(new JLabel("Date From:"));
    	 p1.add(new JLabel("Date To:"));
    	 p2.add(From);p2.add(To);
    	 
    	 
    	 JPanel p3=new JPanel(){public Dimension getPreferredSize(){return new Dimension(80,60);}};
    	 p3.setLayout(new BorderLayout());
    	 p3.add(p1,BorderLayout.CENTER);p3.add(p2,BorderLayout.EAST);
    	 
    	
    	 JPanel p4=new JPanel(){public Dimension getPreferredSize(){return new Dimension(350,50);}};
    	 p4.add(b1);p4.add(b2);
    	 getContentPane().add(p3,BorderLayout.CENTER);
    	 getContentPane().add(p4,BorderLayout.SOUTH);
         setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
    	
         pack(); 
         setResizable(false);	
   	     setLocation(300,100);	
   	     setVisible(true);
    
    	 
    	 
    }
    
    
}