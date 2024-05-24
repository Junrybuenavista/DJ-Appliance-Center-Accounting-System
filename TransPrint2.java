import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import javax.swing.table.TableColumn;
import java.awt.print.PrinterException;

public class TransPrint2 extends JDialog {

	Statement st;    
    ResultSet set;
    
    Connection conn;
    MyTable tableView;
    JButton b1;
    String arr[];
    String arr2[][]=null;
    String From,To;
    MainClass main;
    TransPrint3 printme;
    DecimalFormat form= new DecimalFormat("###,###.00");
    
    int Gross;
    String input1[],input2[];
    
    public TransPrint2(Statement st,String From,String To,MainClass main)
    {   
    	super(main, "Trasaction Print", true);
    	this.st=st;
    	this.From=From;
    	this.To=To;
    	this.main=main;
    
    	input1=new String[8];
    	input2=new String[5];
	    
      	
    	
        JPanel pp=new JPanel(){public Dimension getPreferredSize(){return new Dimension(564,350);}};
   	    pp.setLayout(new BorderLayout());
    	load();
    	
        
        b1=new JButton("PRINT");
     
        printme=new TransPrint3(b1,tableView.getTb());
                
        
                
        
        pp.add(new JScrollPane(tableView.getTb())); 
        JPanel jp=new JPanel(){public Dimension getPreferredSize(){return new Dimension(564,370);}};
        jp.setLayout(new BorderLayout());	
        jp.add(pp,BorderLayout.CENTER); 
        jp.add(b1,BorderLayout.SOUTH); 
        	
        
        getContentPane().add(jp,BorderLayout.CENTER);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
    	
       pack(); 
   	   setLocation(300,100);	
   	   setVisible(true);
    
    	
  	
    }
   
     public void load()
  	{  	try
  		{
  		String arr[]={"DATE","DOC #","DR #","TRANSACTION","AMOUNT","DISCOUNT","REMARKS"};
  		String arr3[]=new String[7];                       
        tableView=new MyTable();
        tableView.getTb().setRowSelectionAllowed(false);
        tableView.setRH(16);
        //tableView.setme();
        

        tableView.setFonts("Courier New",11);
        tableView.setData(arr2,arr);
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
	
		tableView.getTb().getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		tableView.getTb().getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
       	tableView.getTb().getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
       	
       	
        
        TableColumn column=tableView.getTb().getColumnModel().getColumn(0);
        column.setPreferredWidth(90);
        column=tableView.getTb().getColumnModel().getColumn(1);
        column.setPreferredWidth(70);
        column=tableView.getTb().getColumnModel().getColumn(2);
        column.setPreferredWidth(55);
        column=tableView.getTb().getColumnModel().getColumn(3);
        column.setPreferredWidth(100);
        column=tableView.getTb().getColumnModel().getColumn(4);
        column.setPreferredWidth(75);
        column=tableView.getTb().getColumnModel().getColumn(5);
        column.setPreferredWidth(65);
        column=tableView.getTb().getColumnModel().getColumn(6);
        column.setPreferredWidth(100);
        //tableView.getTb().setShowHorizontalLines(false);
       	//tableView.getTb().setShowVerticalLines(false);           
        	try{
    	
						
            	
            			
                
            	arr=new String[7];		
            	set=st.executeQuery("Select * from [Transaction Register] where [Date] >= #"+From+"# and [Date] <= #"+To+"# ORDER BY [Date]");
            	while(set.next())
            	{
            	
            	
            		
            		    arr[0]=set.getDate("date")+"";
            		    arr[1]=set.getString("doc #");
            		    arr[2]=set.getString("dr #");
            		    arr[3]=set.getString("Transaction type");
            		    arr[4]=form.format(Integer.parseInt(set.getString("Amount")));
            		    arr[5]=form.format(Integer.parseInt(set.getString("discount")));
            		    arr[6]=set.getString("Remarks");
            		   
    		        	tableView.insert(arr);
            	}
    			
    			
    	
          	   
    		}catch(Exception ee){ee.printStackTrace();}	
  	
  		
  	
  			
  		}catch(Exception ee){ee.printStackTrace();}
  	}
  
   	
    
  
    public static void main(String args[])
    {
        
    }
}