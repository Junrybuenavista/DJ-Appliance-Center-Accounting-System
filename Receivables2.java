    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;
    import javax.swing.table.*;
    import java.util.Date;
    import java.sql.*;
    import java.awt.print.*;
    import javax.swing.table.DefaultTableModel;
    import java.text.DecimalFormat;
    import javax.swing.table.TableColumn;
    import java.util.Calendar;
    
    public class Receivables2 extends JDialog implements ActionListener, Printable,Pageable
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
    java.util.Date DueDate;
    JFrame main;
    String Collector;
    Statement st,st2;
    String input3[];
    int tbalance=0,tnydue=0,tmonth=0,tmonth2=0,tcoll=0,tamount=0;
    DefaultTableModel mod;
    ResultSet set,set2;
  
    DecimalFormat form= new DecimalFormat("###,###");
    String Dates[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    String Dates2[]={"January","February","March","April","May","June","July","August","September","October","November","December"};
    String Title[];
    
    public Receivables2(Statement st,Statement st2,Date DueDate,JFrame main,String Collector) throws Exception
    {
    super(main, "Trasaction Print", true);
    this.st=st;
    
    System.out.println("N/A");
    this.DueDate=DueDate;
    this.main=main;
    this.st2=st2;
   
    this.Collector=Collector;
    input3=Param.load2();
    Title=new String[5];
    
    
    
    mod=new DefaultTableModel();
    btnprint = new JButton("Print");
    btnprint.addActionListener(this);
    btnexport = new JButton("Cancel");
    btnexport.addActionListener(this);
    
    toolbar = new JToolBar();
    
    String arr[]={"ACCOUNT NAME","ADDRESS","BALANCE","UNIT","1","2","3","4","5","TCOLL.","DATE","AMOUNT","DUE","MOUNTHLY"};
    
     
     java.util.Date tempdate=DueDate;
     for(int ii=0;ii<5;ii++)
     	{
     		
    		
    		String StrYear=tempdate.getYear()+"";
    	    StrYear=StrYear.substring(StrYear.length()-2,StrYear.length());
    	    if(ii==4)StrYear="down";
    		arr[ii+4]=Title[ii]=Dates[tempdate.getMonth()]+"-"+StrYear;    		
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(tempdate);
    		cal.add(Calendar.MONTH, -1);
    		tempdate = cal.getTime();
     	}
     
     
     
    	
   
    String ss[][]=null;
    table = new JTable(mod)
    {  
  			public boolean isCellEditable(int row, int column){  
    
    				if(row > getRowCount())
    				{
    					 return true;
    				}  
    				return false;  
  					}  
	};
    table.setAutoResizeMode( JTable.AUTO_RESIZE_OFF);
    mod.setDataVector(ss,arr);
    table.setFont(new Font("Courier New",Font.PLAIN,10));
    table.setShowHorizontalLines(false);
   
    
    
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
    setSize(980,500);
    setResizable(false); 
   	setLocation(50,100);	
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
    			g.setClip(0,0,printWidth+100, 1200);
    			g.translate(32,pageFormat.getImageableY()+100);
    			
    			g.setColor(Color.BLACK);
                             
                int start2=40;
                g.setFont(new Font("Courier New",Font.BOLD,18));
            	g.setColor(Color.BLUE);
            	g.drawString(input3[0],0,-160+start2);
            	g.setColor(Color.BLACK);
            	g.setFont(new Font("Courier New",Font.BOLD,10));
        		g.drawString(input3[1],0,-145+start2);
        		g.drawString(input3[4]+", "+input3[5]+" "+input3[6],0,-135+start2);
                
                String Lyear=DueDate.getYear()+"";
     			Lyear=Lyear.substring(Lyear.length()-2,Lyear.length());
                g.setFont(new Font("Courier New",Font.PLAIN,15)); 
    			g.drawString("Aging Of Receivables: "+Dates2[DueDate.getMonth()]+"-20"+Lyear,0,-50);
    			
    			
    			int start=-22;
    			int start3=18;
    					
        		g.drawLine(0,start-22, 953,start-22);
        		g.drawLine(0,start-19,953,start-19);
        		g.setFont(new Font("Courier New",Font.BOLD,9));
        		g.drawString("Account Name",0,start+15);
        		g.drawString("Address",170+start3,start+15);
        		g.drawString("Balance",355+start3,start+15);
        		g.drawString("NY Due",410+start3,start+15);
        		g.drawString(Title[0],457+start3,start+15);
        		
        		g.drawString("Overdue",563+start3,start-6);
        		g.drawLine(500+start3,start-2,665,start-2);
        		
        		g.drawString(Title[1],500+start3,start+15);
        		g.drawString(Title[2],545+start3,start+15);
        		g.drawString(Title[3],590+start3,start+15);
        		g.drawString(Title[4],625+start3,start+15);
        		
        		g.drawString("TColl.",681+start3,start+15);
        		g.drawString("Date",742+start3,start+15);
        		g.drawString("Amount",802+start3,start+15);
        		g.drawString("Due",860+start3,start+4);
        		g.drawString("Date",860+start3,start+15);
        		g.drawString("Monthly",900+start3,start+15);
        		g.drawLine(0,start+20,953,start+20);
    			
    			
    		    g.setFont(new Font("Courier New",Font.BOLD,12));
    			g.setColor(Color.BLUE);
    			//g.drawLine(0,(getNumberOfRowsByPage()*rowHeight),574,(getNumberOfRowsByPage()*rowHeight));
    			g.drawString("PAGE "+(pageIndex+1)+" of "+getNumberOfPages(),0, getNumberOfRowsByPage()*rowHeight+20);
    			System.out.println(pageFormat.getImageableY()+"---------------");
    			
    			
   				g.setClip(0,0,printWidth, getNumberOfRowsByPage()*rowHeight);
    			g.translate(0,-getTableVerticalShiftForIndex(pageIndex));
    			
    			g.drawString("------------YEAH-----------------------",0, getNumberOfRowsByPage()*rowHeight+20);
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
    			defaultPageFormat.setOrientation(PageFormat.LANDSCAPE);
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
  
    
    
    
    public void load()
  	{  	try
  		{
  		
  	
        

       
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		
		DefaultTableCellRenderer liftRenderer = new DefaultTableCellRenderer();
		liftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		
	
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(10).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(11).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(12).setCellRenderer(rightRenderer);
       	table.getColumnModel().getColumn(13).setCellRenderer(rightRenderer);
       	
       	
        
        TableColumn column=table.getColumnModel().getColumn(0);
        column.setPreferredWidth(165);
        column=table.getColumnModel().getColumn(1);
        column.setPreferredWidth(175);
        column=table.getColumnModel().getColumn(2);
        column.setPreferredWidth(65);
        column=table.getColumnModel().getColumn(3);
        column.setPreferredWidth(35);
        column=table.getColumnModel().getColumn(4);
        column.setPreferredWidth(55);
        column=table.getColumnModel().getColumn(5);
        column.setPreferredWidth(45);
        column=table.getColumnModel().getColumn(6);
        column.setPreferredWidth(55);
        column=table.getColumnModel().getColumn(7); 
        column.setPreferredWidth(45);
        column=table.getColumnModel().getColumn(8);
        column.setPreferredWidth(45);
        column=table.getColumnModel().getColumn(9);
        column.setPreferredWidth(65);
        column=table.getColumnModel().getColumn(10);
        column.setPreferredWidth(68);
        column=table.getColumnModel().getColumn(11);
        column.setPreferredWidth(60);
        column=table.getColumnModel().getColumn(12);
        column.setPreferredWidth(35);
        column=table.getColumnModel().getColumn(13);
        column.setPreferredWidth(45);
        //table.setShowHorizontalLines(false);
       	//table.setShowVerticalLines(false);           
        	try{
        		
        		int ctr=0;
        		
            	
            	
            	
            			
                
            	String arr[]=new String[14];
            	Balance bal;
            
            	   int Total=0;
            		set=st.executeQuery("Select * from [Account Entry] where Collector='"+Collector+"' order by Customer");
            		while(set.next())
            		{
            	     
            	      System.out.println("UP");
            	
            			try{
            		          
            		        String Units=set.getString("Unit");  
            		        String MyDR=set.getString("DR #");
            		        int Term=set.getInt("term");
            		        int Install=set.getInt("Installment"); 
            		        bal=new Balance(MyDR,Term,Install);
            		        bal.f1();
            		        java.util.Date BalDate=bal.getDate();
            		        
            		        int resDate=0;
            		        String test1=DueDate.getYear()+"";
            		        String test2=BalDate.getYear()+"";
            		        
            		        int resYear=(DueDate.getYear()-BalDate.getYear());
            		        int yearM=12*resYear;
            		        
            		        resDate=(DueDate.getMonth()+1+yearM)-(BalDate.getMonth()+1);
            		    
            		        
            		        int checkBal=checkBalance(MyDR);
            		        
            		        if(checkBal<=0){System.out.println("Continue");continue;}
            		        System.out.println("Down");
            		        
            		   		arr[0]=MyDR+" "+set.getString("Customer");
            		   		arr[1]=set.getString("Address");
            		   		arr[2]=form.format(checkBal)+"";
            		   		try{
            		    	tbalance+=checkBal;
            		   		}catch(Exception ee){System.out.println("&&&&&&ERROR&&&&&&");}
            		    	if(resDate<1)
            		    		{   try{
            		    		
            		    			String allto=bal.getAllTotal()+"";
            		    			try{
            		    			
            		    			if(Integer.parseInt(allto)==0)allto="";
            		    			arr[3]=form.format(allto);
            		    			 try{
            		    					tnydue+=bal.getAllTotal();
            		   						}catch(Exception ee){System.out.println("&&&&&&ERROR&&&&&&");}
            		    					
            		    			
            		    			}catch(Exception ee){}
            		    		   
            		    		    
            		    		    arr[4]="";
            		    			arr[5]="";
            		    			arr[6]="";
            		    			arr[7]="";
            		    			arr[8]="";
            		    			arr[9]="";
            		    		    }catch(Exception ee){}	
            		    		}
            		    	else
            		    		{   
            		    			arr[4]="";
            		    			arr[5]="";
            		    			arr[6]="";
            		    			arr[7]="";
            		    			arr[8]="";
            		    			arr[9]="";
            		    			int TotalBalz=0;int TotalBalz2=0;
            		    		
            		    			try
            		    			{   
            		    				for(int i3=0;i3<resDate;i3++)
            		    					{
            		    						TotalBalz+=bal.getNumber(i3);           		    						
            		    					}
            		    			}catch(Exception ee){ee.printStackTrace();}
            		    			String temp="";
            		    		    try{
            		    			      temp=bal.getNumber(resDate-4)+"";
            		    		       }catch(Exception ee){}
            		    		       	
            		    			try{
            		    				
            		    			   
            		    			    if(Integer.parseInt(temp)==0)temp="";
            		    				arr[7]=form.format(Integer.parseInt(temp));
            		    						try{
            		    						tmonth2+=Integer.parseInt(temp);
            		   							}catch(Exception ee){System.out.println("&&&&&&ERROR&&&&&&");}
            		    				
            		    		        }catch(Exception ee){}	
            		    		    try{    	
            		    				temp=bal.getNumber(resDate-3)+"";
            		    			    if(Integer.parseInt(temp)==0)temp="";
            		    				arr[6]=form.format(Integer.parseInt(temp));
            		    							try{
            		    							tmonth2+=Integer.parseInt(temp);
            		   								}catch(Exception ee){System.out.println("&&&&&&ERROR&&&&&&");}
            		    				
            		    				}catch(Exception ee){}
            		    			try{	
            		    				temp=bal.getNumber(resDate-2)+"";
            		    			    if(Integer.parseInt(temp)==0)temp="";
            		    				arr[5]=form.format(Integer.parseInt(temp));
            		    				
            		    				try{
            		    					tmonth2+=Integer.parseInt(temp);
            		   						}catch(Exception ee){System.out.println("&&&&&&ERROR&&&&&&");}
            		    				
            		    				}catch(Exception ee){}
            		    			try{		
            		    				temp=bal.getNumber(resDate-1)+"";
            		    			    if(Integer.parseInt(temp)==0)temp="";		
            		    				arr[4]=form.format(Integer.parseInt(temp));
            		    				
            		    					try{
            		    					tmonth+=Integer.parseInt(temp);
            		   						}catch(Exception ee){System.out.println("&&&&&&ERROR&&&&&&");}
            		    				
            		    				}catch(Exception ee){}
            		    			  
            		    			int tt=resDate-5;
                                
            		    		try
            		    		{
            		    			if(tt>Term)
            		    			{           		    		
            		    		       
            		    				TotalBalz2+=bal.getAllTotal();
            		    			}
            		    			else
            		    			{  
            		    				while(true)
            		    					{
            		    						TotalBalz2+=bal.getNumber(tt);
            		    						tt--;
            		    					}
            		    			}	
            		    		}catch(Exception ee){}
            		    		
            		    		   
            		    		    //arr[3]=form.format(bal.getAllTotal()-TotalBalz);
            		    		    
            		    		  
            		    		    if(Units.equalsIgnoreCase("AIR CON"))arr[3]="AC";
            		    		    else if(Units.equalsIgnoreCase("AUDIO AMPLIFIER"))arr[3]="AA";
            		    		    else if(Units.equalsIgnoreCase("BED"))arr[3]="BD";
            		    		    else if(Units.equalsIgnoreCase("BOTTLE COOLER"))arr[3]="BC";
            		    		    else if(Units.equalsIgnoreCase("CENTER TABLE"))arr[3]="CT";
            		    		    else if(Units.equalsIgnoreCase("CHEST TYPE FREEZER"))arr[3]="CTF";
            		    		    else if(Units.equalsIgnoreCase("CORNET SET"))arr[3]="CS";
            		    		    else if(Units.equalsIgnoreCase("DINING SET"))arr[3]="DS";
            		    		    else if(Units.equalsIgnoreCase("DINING TABLE"))arr[3]="DIT";
            		    		    else if(Units.equalsIgnoreCase("DRESSER TABLE"))arr[3]="DRT";
            		    		    else if(Units.equalsIgnoreCase("DVD PLAYER"))arr[3]="DP";
            		    		    else if(Units.equalsIgnoreCase("ELECTRIC FAN"))arr[3]="EF";
            		    		    else if(Units.equalsIgnoreCase("ENTERTAINMENT"))arr[3]="ENT";
            		    		    else if(Units.equalsIgnoreCase("FLEX FOAM"))arr[3]="FF";
            		    		    else if(Units.equalsIgnoreCase("FOAM"))arr[3]="FOAM";
            		    		    else if(Units.equalsIgnoreCase("GALA BED ESSENTIALS"))arr[3]="GBE";
            		    		    else if(Units.equalsIgnoreCase("GAS RANGE"))arr[3]="GR";
            		    		    else if(Units.equalsIgnoreCase("KARAOKE"))arr[3]="KR";
            		    		    else if(Units.equalsIgnoreCase("MIDI PLAYER"))arr[3]="MP";
            		    		    else if(Units.equalsIgnoreCase("PROJECTION TV"))arr[3]="PTV";
            		    		    else if(Units.equalsIgnoreCase("REFRIGERATOR"))arr[3]="REF";
            		    		    else if(Units.equalsIgnoreCase("RICE COOKER"))arr[3]="RC";
            		    		    else if(Units.equalsIgnoreCase("SALA SET"))arr[3]="SS";
            		    		    else if(Units.equalsIgnoreCase("SEWING MACHINE"))arr[3]="SM";
            		    		    else if(Units.equalsIgnoreCase("SOFA BED"))arr[3]="SB";
            		    		    else if(Units.equalsIgnoreCase("SPRING BED"))arr[3]="SPB";
            		    		    else if(Units.equalsIgnoreCase("STEEL BED"))arr[3]="STB";
            		    		    else if(Units.equalsIgnoreCase("STEEL DIVIDER"))arr[3]="SD";
            		    		    else if(Units.equalsIgnoreCase("STEREO"))arr[3]="SR";
            		    		    else if(Units.equalsIgnoreCase("STOVE"))arr[3]="ST";
            		    		    else if(Units.equalsIgnoreCase("TELEVISION"))arr[3]="TV";
            		    		    else if(Units.equalsIgnoreCase("TV RACK"))arr[3]="TVR";
            		    		    else if(Units.equalsIgnoreCase("UPRIGHT FREEZER"))arr[3]="UREF";
            		    		    else if(Units.equalsIgnoreCase("VIDEO PLAYER"))arr[3]="VP";
            		    		    else if(Units.equalsIgnoreCase("WARDOBE"))arr[3]="WARD";
            		    		    else if(Units.equalsIgnoreCase("WASHING MACHINE"))arr[3]="WM";
            		    		    else if(Units.equalsIgnoreCase("WATER DISPENSER"))arr[3]="WD";
            		    		    else arr[3]="N/A";
            		    		    
            		    		     try{
            		    					tnydue+=bal.getAllTotal()-TotalBalz;
            		   						}catch(Exception ee){System.out.println("&&&&&&ERROR&&&&&&");}
            		    		    
            		    		    arr[8]=form.format(TotalBalz2);
            		    		    	try{
            		    					tmonth2+=TotalBalz2;
            		   						}catch(Exception ee){System.out.println("&&&&&&ERROR&&&&&&");}
            		    		    arr[9]=form.format(TotalBalz);
            		    		    	try{
            		    					tcoll+=TotalBalz;
            		   						}catch(Exception ee){System.out.println("&&&&&&ERROR&&&&&&");}
            		            }	
            		    
            		    	String armg[]=getMyDate(MyDR);
            		    	arr[10]=armg[0];
            		    	arr[11]=form.format(Integer.parseInt(armg[1]));
            		    					try{
            		    					tamount+=Integer.parseInt(armg[1]);
            		   						}catch(Exception ee){System.out.println("&&&&&&ERROR&&&&&&");}        		    	
            		    	arr[12]=set.getString("Due Date");
            		    	arr[13]=form.format(Install);
            		    	
            		    	
            		   
    		        		mod.insertRow(mod.getRowCount(), arr);
    		        		Total++;
            		  }catch(Exception eee){}
            		  
            		
            		
            		if(Total!=0){
            		
            		//String arr4[]={"Total "+code[i]+" Account/s: "+Total,"","","","","","","","","","","","",""};
            	  	//mod.insertRow(mod.getRowCount(), arr4);
            		}
            	}
             arr[0]="";arr[1]="";arr[2]="";arr[3]="";arr[4]="";arr[5]="";arr[6]="";arr[7]="";arr[8]="";arr[9]="";arr[10]="";arr[11]="";arr[12]="";arr[13]="";	
             
             mod.insertRow(mod.getRowCount(), arr);	
    		 arr[0]="Collector Total";arr[1]="";
    	     arr[2]=form.format(tbalance);
    	     arr[3]="";
    	     arr[4]=form.format(tmonth);
    	     arr[5]="*******";
    	     arr[6]=form.format(tmonth2);
    	     //table.getColumnModel().getColumn(7).setCellRenderer(liftRenderer);
			
    	     arr[7]="*******";arr[8]="*******";
    	     arr[9]=form.format(tcoll);
    	     
    	     arr[10]="";
    	     arr[11]=form.format(tamount);
    	     arr[12]="";arr[13]="";
    	     mod.insertRow(mod.getRowCount(), arr);
    	     arr[0]="Total Account/s:"+Total;arr[1]="";arr[2]="";arr[3]="";arr[4]="";arr[5]="";arr[6]="";arr[7]="";arr[8]="";arr[9]="";arr[10]="";arr[11]="";arr[12]="";arr[13]="";	
             mod.insertRow(mod.getRowCount(), arr);
          	   
    		}catch(Exception ee){}	
  	
  		
  	
  			
  		}catch(Exception ee){}
  	}
  	
  	public String[] getMyDate(String testDR)
  		{ String ret[]=new String[2];
  			try{
  		
  				set2=st2.executeQuery("Select * from [Transaction Register] where [DR #]='"+testDR+"' order by date DESC");set2.next();
  				ret[0]=set2.getDate("Date")+"";
  				ret[1]=(set2.getInt("Amount")+set2.getInt("discount"))+"";
  			}catch(Exception ee){}
  			return ret;
  		}
  	public int checkBalance(String testDR)
  		{  int ret=0;
  			try
  			{
  				set2=st2.executeQuery("Select * from [Transaction Register] where [DR #]='"+testDR+"' and [Transaction Type]='GROSS'");set2.next();
  				ret=Integer.parseInt(set2.getString("Amount"));
  				
  				set2=st2.executeQuery("Select * from [Transaction Register] where [DR #]='"+testDR+"' order by date,[doc #]");
  				
           		while(set2.next())
           		{  
           			
           			String transtype=set2.getString("Transaction Type");
            		if(transtype.equalsIgnoreCase("GROSS"))continue;
           			int temp1=set2.getInt("Discount");
	            	int temp2=set2.getInt("Amount");
	            	ret=ret-(temp1+temp2);
           		}
  				
  			}catch(Exception ee){ee.printStackTrace();}
  			return ret;
  		}
  
  		
  	public static void main(String args[])
  		{
  		 Connection conn1,conn2;	
  		 Statement stt1=null;
  		 Statement stt2=null;
  		 ResultSet set1;	
  			 try{ 
                     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  				
    				 conn1 = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=Copy.mdb;DriverID=22;READONLY=true;) ","","");
    				 conn2 = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=Copy.mdb;DriverID=22;READONLY=true;) ","","");  
    				 //conn = DriverManager.getConnection("jdbc:odbc:mydb","","");    				    			
    				 stt1=conn1.createStatement();
    				 stt2=conn2.createStatement();
    				 
    				 set1=stt1.executeQuery("Select * from [Parameters/constants]");set1.next();
    				 java.util.Date d=set1.getDate("Cut-off Date");
    				 
    					
    				 new Receivables2(stt1,stt2,d,new JFrame(),"PAGAPONG, FELIX");				 
    				 
	      }catch(Exception e){e.printStackTrace();}   
  		  	
  		
  		}
    }