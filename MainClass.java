import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.TableColumn;


public class MainClass extends JFrame implements ActionListener{

	Statement st;    
    ResultSet set;
    Connection conn;
    String arr[];
    String arr2[][]=null;
    JButton b1,b2,b3,b4,b6,b8,b9;
    MyTable tab;
    JComboBox box1;
    JTextField t1;
    String collector="PAGAPONG, FELIX";
    int userType;
    JPopupMenu menu;
    JMenuItem i1,i2,i3,i4;
    login log;
    
    public String getCollector()
    {
    	return collector;
    }
    public MainClass()
    {
		addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
        System.exit(0);}});
    	 try{ 
                     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  				
    				 conn = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=Copy.mdb;DriverID=22;READONLY=true;) ","",""); 
    				 //conn = DriverManager.getConnection("jdbc:odbc:mydb","","");    				    			
    				 st=conn.createStatement();
    				 				 
    				 
	      }catch(Exception e){e.printStackTrace();}    	
    	
    	
    	//this.setIconImage(Toolkit.getDefaultToolkit().getImage("untitled.icon"));
    	Container con=getContentPane();
        con.setLayout(new BorderLayout());
        String arr[]={"DR #","DR DATE","CUSTOMER","ADDRESS","Brand","Unit","Model","SERIAL #"};                       
        tab=new MyTable();
        tab.setData(arr2,arr);
        tab.setResize();
        tab.setFonts("Courier New",11);
        tab.getTb().addMouseListener(new MouseAdapter()
        	{
        		  public void mousePressed(MouseEvent e){
        			if (e.isPopupTrigger())
           			 doPop(e);
    				}

    				public void mouseReleased(MouseEvent e){
        			if (e.isPopupTrigger())
           			 doPop(e);
   				 }

    			 private void doPop(MouseEvent e){
        		    System.out.println("eee");
        			menu.show(e.getComponent(), e.getX(), e.getY());
   				 }
        	});
        
        menu=new JPopupMenu();
        i1=new JMenuItem("Add Transaction");i1.addActionListener(this);menu.add(i1);
        i2=new JMenuItem("Edit/Delete Transaction");i2.addActionListener(this);menu.add(i2);
        i3=new JMenuItem("Ledger/Aging");i3.addActionListener(this);menu.add(i3);
        i4=new JMenuItem("Edit/Delete Account");i4.addActionListener(this);menu.add(i4);
        
        String b1s[]={"Search by Name","Search by DR #"};
        box1=new JComboBox(b1s);
        t1=new JTextField(15);
        
        TableColumn column=tab.getTb().getColumnModel().getColumn(0);
        column.setPreferredWidth(5);
        column=tab.getTb().getColumnModel().getColumn(1);
        column.setPreferredWidth(45);
        column=tab.getTb().getColumnModel().getColumn(3);
        column.setPreferredWidth(200);
        
        ImageIcon icon = new ImageIcon("Logo.jpg");
	    JLabel lab1 = new JLabel( icon);
        
        
    	JPanel p1=new JPanel();
    	p1.setLayout(new BorderLayout());
    	JPanel p11=new JPanel(){public Dimension getPreferredSize(){return new Dimension(120,185);}};
    	
    	JPanel pb=new JPanel(){public Dimension getPreferredSize(){return new Dimension(180,218);}};
    	pb.setLayout(new BorderLayout());
    	JPanel ps=new JPanel(){public Dimension getPreferredSize(){return new Dimension(180,40);}};
    	pb.add(ps,BorderLayout.NORTH);pb.add(new JScrollPane(tab.getTb()),BorderLayout.CENTER);
        
        
    	p11.setLayout(new GridLayout(6,1));
		JPanel p2=new JPanel();
		p2.add(lab1);
    	
    	b1=new JButton("ADD ACCOUNTS");b1.addActionListener(this);p11.add(b1);	
    	b3=new JButton("PRINT TRANSACTION");b3.addActionListener(this);p11.add(b3);
    	b4=new JButton("RECEIVABLES");b4.addActionListener(this);p11.add(b4);
    	b8=new JButton("PARAMETER CONSTANT");b8.addActionListener(this);p11.add(b8);
    	b2=new JButton("ADD/DELETE COLLECTOR");b2.addActionListener(this);p11.add(b2);
    	b6=new JButton("ADD/DELETE USER");b6.addActionListener(this);p11.add(b6);
    	b9=new JButton("    OK     ");b9.addActionListener(this);
    	
    	p1.add(p11,BorderLayout.SOUTH);p1.add(p2,BorderLayout.CENTER);    	
    	ps.add(t1);ps.add(box1);ps.add(b9);
    	con.add(p1,BorderLayout.WEST);con.add(pb,BorderLayout.CENTER);
    	
    	
    	show();
    	setSize(1024,700);
    	setLocation(0,0);
    	setResizable(true);
    	log=new login(this,st);
    	if(!log.getType().equalsIgnoreCase("1")){b6.setEnabled(false);b2.setEnabled(false);}
    	load(0);
    	
    	
    		
    }
    public void load(int ser)
    {    
    	    try
    	    {
    	    	while(true){tab.delete(0);}
    	    }catch(Exception ee ){}
    	    
    		try{
    	         
    	        if(ser==0){
          		set=st.executeQuery("Select * from [Account Entry]");
    	        }
    	        if(ser==1){
          		set=st.executeQuery("Select * from [Account Entry] where Customer like '"+t1.getText()+"%'");
    	        }
    	        if(ser==2){
          		set=st.executeQuery("Select * from [Account Entry] where [DR #] like '"+t1.getText()+"%'");
    	        }
    	       
          		arr=new String[8];	
           		while(set.next())
            		{
            		  
            		  
            			arr[0]=set.getString("DR #");
            			arr[1]=set.getDate("DR Date")+"";
            			arr[2]=set.getString("Customer");
            			arr[3]=set.getString("Address");
            			arr[4]=set.getString("Brand");
            			arr[5]=set.getString("Unit");
            			arr[6]=set.getString("Model");
            			arr[7]=set.getString("Serial #");
            		    tab.insert(arr);
            		    
            		    
            		   
            		    
            		}	
    		}catch(Exception ee){ee.printStackTrace();}	
    
    }
    public void actionPerformed(ActionEvent e)
    	{
    		if(e.getSource()==b1)
   		 	{
   		 	  new AddAccount(st,this);
   		 	}
   		 	if(e.getSource()==b2)
   		 	{
   		 	  new AddCollector(st,this);
   		 	}
   		 
   		 	if(e.getSource()==b3)
   		 	{
   		 	   new TransPrint(st,this);
   		 	}
   		 	if(e.getSource()==b4)
   		 	{
   		 	   new Receivables(this);
   		 	}
   		 
   		 	if(e.getSource()==b6)
   		 	{
   		 	  new AddUser(st,this);
   		 	}
   		 
   		 	if(e.getSource()==b8)
   		 	{
   		 	  new Param(st,this);
   		 	}
   		 	if(e.getSource()==b9)
   		 	{
   		 	  if(box1.getSelectedIndex()==0){load(1);}
   		 	  if(box1.getSelectedIndex()==1){load(2);}
   		 	}
   		 	if(e.getSource()==i1) 
   		 		{
   		 		   new Transaction(st,this,tab.getValue(tab.getTb().getSelectedRow(),0));	
   		 		}
   		 	if(e.getSource()==i2)
   		 		{
   		 			new EditTrans(st,this,tab.getValue(tab.getTb().getSelectedRow(),0));
   		 		}
   		 	if(e.getSource()==i3)
   		 		{
   		 			new Test(st,tab.getValue(tab.getTb().getSelectedRow(),0),this);
   		 		}
   		 	if(e.getSource()==i4)
   		 		{
   		 			new EditAcc2(st,this,tab.getValue(tab.getTb().getSelectedRow(),0));
   		 		}
   		 		
    	}
  	    
    public static void main(String args[])
    {
    	new MainClass();
    }
}