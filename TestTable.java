    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;
    import javax.swing.table.*;
    import java.util.*;
    import java.sql.*;
    import java.awt.print.*;
    import javax.swing.table.DefaultTableModel;
    import java.text.DecimalFormat;
    import javax.swing.table.TableColumn;
    
    public class TestTable extends JDialog implements ActionListener, Printable,Pageable
    {
    private JTable table;
    private JToolBar toolbar;
    private JLabel lbl1;
    private JButton btnprint,btnexport;
    private JPanel pnl1,chkpnl1,chkpnl2;
    private JTextArea txtarea;
    private JTableHeader header;
    private int printWidth;
    private int headerHeight;
    private int rowHeight;
    private PageFormat defaultPageFormat = new PageFormat();
    String From,To;
    MainClass main;
    Statement st;
    DefaultTableModel mod;
    ResultSet set;
    DecimalFormat form= new DecimalFormat("###,###.00");
    
    
    
    public TestTable(Statement st,String From,String To,MainClass main) throws Exception
    {
    super(main, "Trasaction Print", true);
    this.st=st;
    this.From=From;
    this.To=To;
    this.main=main;
    
    
    
    mod=new DefaultTableModel();
    btnprint = new JButton("Print");
    btnprint.addActionListener(this);
    btnexport = new JButton("Cancel");
    btnexport.addActionListener(this);
    
    toolbar = new JToolBar();
    String arr[]={"DATE","DOC #","DR #","TRANSACTION","AMOUNT","DISCOUNT","REMARKS"};
    String ss[][]=null;
    table = new JTable(mod);
    table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF);
    mod.setDataVector(ss,arr);
    table.setFont(new Font("Courier New",Font.PLAIN,11));
    table.setShowHorizontalLines(false);
    table.setShowVerticalLines(false); 
    TableColumn column=table.getColumnModel().getColumn(0);
        column.setPreferredWidth(84);
        column=table.getColumnModel().getColumn(1);
        column.setPreferredWidth(70);
        column=table.getColumnModel().getColumn(2);
        column.setPreferredWidth(55);
        column=table.getColumnModel().getColumn(3);
        column.setPreferredWidth(110);
        column=table.getColumnModel().getColumn(4);
        column.setPreferredWidth(75);
        column=table.getColumnModel().getColumn(5);
        column.setPreferredWidth(65);
        column=table.getColumnModel().getColumn(6);
        column.setPreferredWidth(100);
    load();
    //table.setBorder(BorderFactory.createLineBorder(Color.black));
    header = table.getTableHeader();
    headerHeight = header.getPreferredSize().height;
    rowHeight = table.getRowHeight();
    //table.setEnabled(false);
    //table.setShowHorizontalLines(false);
    //table.setShowVerticalLines(false);
    Font fnt = new Font("serif",Font.BOLD,16);
    lbl1 = new JLabel("DON BOSCO SCHOOL LIBRARY");
    lbl1.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
    lbl1.setHorizontalAlignment(JLabel.CENTER);
    lbl1.setFont(fnt);
    pnl1 = new JPanel();
    chkpnl1 = new JPanel();
    chkpnl2 = new JPanel();
    //pnl1.setBorder(BorderFactory.createMatteBorder(8,8,8,8,Color.gray));
    //pnl1.setLayout(new BorderLayout());
    //pnl2 = new JPanel();
    //pnl2.setBackground(Color.white);
    //pnl2.add(table);
    //pnl1.add(lbl1,BorderLayout.NORTH);
    //pnl1.add(chkpnl1,BorderLayout.WEST);
    //pnl1.add(chkpnl2,BorderLayout.EAST);
    //pnl1.add(table,BorderLayout.CENTER);
    //pnl1.setBackground(Color.white);
    JScrollPane scrollpane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);//txtarea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
    //scrollpane.setBackground(Color.red);
    toolbar.add(btnprint);
    toolbar.addSeparator();
    toolbar.add(btnexport);
    toolbar.addSeparator();
    
    getContentPane().add(toolbar,BorderLayout.NORTH);
    getContentPane().add(scrollpane);
    
    
    pack();
    setSize(570,300);
    setResizable(false); 
   	setLocation(300,100);	
   	setVisible(true);
    
    
    }
    private int getNumberOfRowsByPage() 
    {
    double tableAvailableSpaceByPage = defaultPageFormat.getImageableHeight()-headerHeight-80;
    return((int)Math.floor(tableAvailableSpaceByPage/rowHeight));
    }
    
    private int getTableVerticalShiftForIndex(int index) 
    {
    int shiftY = ((getNumberOfRowsByPage()*rowHeight*index));
    return(shiftY);
    }
    
    public int getNumberOfPages() 
    {
    int nbPages = (int)Math.ceil(table.getRowCount()/(double)getNumberOfRowsByPage());
    return(nbPages);
    }
    
    public PageFormat getPageFormat(int pageIndex) throws IndexOutOfBoundsException 
    {
    	if (pageIndex<1 && pageIndex>getNumberOfPages()) {
    	throw new IndexOutOfBoundsException("Page "+pageIndex+" doesn't exist!");
    	}
    	else {
    	return(defaultPageFormat);
   		 }
    }
    public Printable getPrintable(int pageIndex) throws IndexOutOfBoundsException
    {
    	if (pageIndex<1 && pageIndex>getNumberOfPages()) {
    	throw new IndexOutOfBoundsException("Page "+pageIndex+" doesn't exist!");
    	}
    	else {
    	return(this);
    	}
    }
    
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException 
    {
    printWidth = table.getWidth();
    System.out.println("THE PRINT WIDTH::"+printWidth);
    if (pageIndex<1 && pageIndex>getNumberOfPages()) {
    return Printable.NO_SUCH_PAGE;
    }
    			Graphics2D g = (Graphics2D)graphics;
    			
    			//g.translate(pageFormat.getImageableX(), pageFormat.getImageableY());   			
              
    
    
    			double scaleX = pageFormat.getImageableWidth()/printWidth;
    			
    			boolean needScale = scaleX < 1;
    			
    			
   			    //g.translate(pageFormat.getImageableX()-30,pageFormat.getImageableY()+60);
   			    
   			    
    
    
   				//if (needScale) {
    			//g.scale(scaleX,1);
   				//}
   				//g.setClip(0,0,printWidth,headerHeight);
    			//header.printAll(g);
    			//g.translate(0,headerHeight);
    			g.setClip(0,0,printWidth+32, 1000);
    			g.translate(32,pageFormat.getImageableY()+100);
    			
    			g.setColor(Color.BLUE);
                g.setFont(new Font("Courier New",Font.BOLD,25));               
                
    			g.drawString("Transaction Summary",0,-30);
    			
    			int start=-22;		
        		g.drawLine(0,start, 600,start);
        		g.drawLine(0,start+3,600,start+3);
        		g.setFont(new Font("Courier New",Font.PLAIN,11));
        		g.drawString("DATE",0,start+15);
        		g.drawString("DOC #",90,start+15);
        		g.drawString("DR #",160,start+15);
        		g.drawString("TRANSACTION",216,start+15);
        		g.drawString("AMOUNT",330,start+15);
        		g.drawString("DISCOUNT",395,start+15);
        		g.drawString("REMARKS",480,start+15);
        		g.drawLine(0,start+20,600,start+20);
    			
    			
    		    g.setFont(new Font("Courier New",Font.BOLD,12));
    			g.setColor(Color.BLUE);
    			//g.drawLine(0,(getNumberOfRowsByPage()*rowHeight),574,(getNumberOfRowsByPage()*rowHeight));
    			g.drawString("PAGE "+(pageIndex+1)+" of "+getNumberOfPages(),0, getNumberOfRowsByPage()*rowHeight+20);
    			System.out.println(pageFormat.getImageableY()+"---------------");
    			
    			
   				g.setClip(0,0,printWidth, getNumberOfRowsByPage()*rowHeight);
    			g.translate(0,-getTableVerticalShiftForIndex(pageIndex));
    			
    			//if(pageIndex>0)g.translate(0,-getTableVerticalShiftForIndex(pageIndex)*200);
    			//if(pageIndex>0)g.translate(0,pageFormat.getImageableY()-100);
    			
    		   	
    			
    			table.printAll(g);
    			
    		
    		
    		
    		
    			return Printable.PAGE_EXISTS;
    }
    public void actionPerformed(ActionEvent ae)
    {
    Object obj = ae.getSource();
    		if(obj==btnexport)
    		{
    			setVisible(false); 
    			dispose();
   			}
   			if(obj==btnprint)
    		{
    			PrinterJob printerJob = PrinterJob.getPrinterJob();
    			printerJob.setJobName("Printing table");
    			defaultPageFormat = printerJob.defaultPage();
    			defaultPageFormat = printerJob.pageDialog(defaultPageFormat);
    			printerJob.setPageable(this);
    			
    	    if (printerJob.printDialog())
   			{
    			try {
    				printerJob.print();
   					System.out.println("Coming here TO print");
    				}
    				catch (PrinterException e)
    				{
    				System.out.println(e);
    				}
    		}
    		}
    }
    public static void main(String arg[])
    {
    try
    {
    //TestTable rpt = new TestTable();
   // rpt.setVisible(true);
    //rpt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    catch(Exception e)
    {
    }
    }
    
    
    
    
    
     public void load()
  	{  	try
  		{
  		
  	
        

       
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
	
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
       	table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
       	
       	
        
        TableColumn column=table.getColumnModel().getColumn(0);
        column.setPreferredWidth(90);
        column=table.getColumnModel().getColumn(1);
        column.setPreferredWidth(70);
        column=table.getColumnModel().getColumn(2);
        column.setPreferredWidth(55);
        column=table.getColumnModel().getColumn(3);
        column.setPreferredWidth(100);
        column=table.getColumnModel().getColumn(4);
        column.setPreferredWidth(75);
        column=table.getColumnModel().getColumn(5);
        column.setPreferredWidth(65);
        column=table.getColumnModel().getColumn(6);
        column.setPreferredWidth(100);
        //table.setShowHorizontalLines(false);
       	//table.setShowVerticalLines(false);           
        	try{
    	
						
            	
            			
                
            	String arr[]=new String[7];		
            	set=st.executeQuery("Select * from [Transaction Register] where [Date] >= #"+From+"# and [Date] <= #"+To+"# order by date,[doc #]");
            	while(set.next())
            	{
            	
            	
            		try{
            		    
            		    String transtype=set.getString("Transaction Type");
            			
            				
            			
            			if(transtype.equalsIgnoreCase("GROSS"))continue;
            		    
            		    arr[0]=set.getDate("date")+"";
            		    arr[1]=set.getString("doc #");
            		    arr[2]=set.getString("dr #");
            		    arr[3]=transtype;
            		    arr[4]=form.format(Integer.parseInt(set.getString("Amount")));
            		    arr[5]=form.format(Integer.parseInt(set.getString("discount")));
            		    arr[6]=set.getString("Remarks");
            		   
    		        	mod.insertRow(mod.getRowCount(), arr);
            		  }catch(Exception eee){mod.insertRow(mod.getRowCount(), arr);}
            	}
    			
    			
    	
          	   
    		}catch(Exception ee){ee.printStackTrace();}	
  	
  		
  	
  			
  		}catch(Exception ee){ee.printStackTrace();}
  	}
    }