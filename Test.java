import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.text.DecimalFormat;
import javax.swing.table.TableColumn;
import java.util.Calendar;

public class Test extends JDialog {

	Statement st;    
    ResultSet set;
    Connection conn;
    MyTable tableView;
    JButton b1,b2;
    String arr[];
    int TotalCol=0;
    String arr2[][]=null;
    String MyDR="";
    MainClass main;
    DecimalFormat form= new DecimalFormat("###,###.00");
    String Data[]=new String[8];
    int Gross;
    String Dates[]={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    String input1[],input2[];
    public Test(Statement st,String dr,MainClass main)
    {   
    	super(main, "Ledger", true);
    	this.st=st;
    	this.main=main;
    	MyDR=dr;
    	input1=new String[8];
    	input2=new String[5];
	    
      	
    	
        JPanel pp=new JPanel(){public Dimension getPreferredSize(){return new Dimension(564,350);}};
        pp.setLayout(new BorderLayout());	
    	load();
    	
        
        b1=new JButton("PRINT LEDGER");
        b2=new JButton("PRINT LEDGER/AGING");
      
                
       
                
        
        pp.add(new JScrollPane(tableView.getTb())); 
        JPanel jp=new JPanel(){public Dimension getPreferredSize(){return new Dimension(564,370);}};
        jp.setLayout(new BorderLayout());	
        jp.add(pp,BorderLayout.CENTER);
        JPanel pan=new JPanel();
        pan.add(b1);pan.add(b2); 
        jp.add(pan,BorderLayout.SOUTH); 
        	
        ReportPrint rep=new ReportPrint(b1,tableView.getTb(),input1,input2,tableView.getcount());
        String ins[]=getData();
        String ins2[]=getDateData();
        PrintAging Age=new PrintAging(b2,tableView.getTb(),input1,input2,tableView.getcount(),ins,ins2);
        getContentPane().add(jp,BorderLayout.CENTER);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);	
        setResizable(false);	
        pack(); 
   	   setLocation(300,100);	
   	   setVisible(true);
    
    	
  	
    }
    public String[] getData()
   	{
   			    
            	        
            	        Data[0]=".00";
            		    Data[1]=".00";
            		    Data[2]=".00";
            		    Data[3]=".00";
            		    Data[4]=".00";
            		    Data[5]=".00";
            	        Balance bal;
            	        int TotalBalz2=0;
            		    int TotalBalz3=0; 
            			try{
            		        set=st.executeQuery("Select * from [Parameters/constants]");set.next();
    				 		java.util.Date DueDate=set.getDate("Cut-off Date");
            		        
            		        
            		        
            		        set=st.executeQuery("Select * from [Account Entry] where [dr #]='"+MyDR+"'");set.next();
            		        
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
            		    
            		                 		   		
            		    	if(resDate<0)
            		    		{   
            		    		    
            		    		}
            		    	else
            		    		{   
            		    		
            		    		    
            		    		
            		    			String temp="";
            		    		     
            		    		    
            		    		       	
            		    			try{
            		    				
            		    			    temp=bal.getNumber(resDate-4)+"";
            		    			    TotalBalz3+=bal.getNumber(resDate-4);           		    			    
            		    				Data[4]=form.format(Integer.parseInt(temp));
            		    			
            		    		        }catch(Exception ee){}	
            		    		    try{    	
            		    				temp=bal.getNumber(resDate-3)+"";
            		    				TotalBalz3+=bal.getNumber(resDate-3); 
            		    			    if(Integer.parseInt(temp)==0)temp="";
            		    				Data[3]=form.format(Integer.parseInt(temp));
            		    			
            		    				}catch(Exception ee){}
            		    			try{	
            		    				temp=bal.getNumber(resDate-2)+"";
            		    			    TotalBalz3+=bal.getNumber(resDate-2); 
            		    				Data[2]=form.format(Integer.parseInt(temp));
            		    				
            		    				}catch(Exception ee){}
            		    			try{		
            		    				temp=bal.getNumber(resDate-1)+"";
            		    			 	TotalBalz3+=bal.getNumber(resDate-1); 	
            		    				Data[1]=form.format(Integer.parseInt(temp));
            		    			
            		    				}catch(Exception ee){}
            		    				
            		    			try{		
            		    				temp=bal.getNumber(resDate)+"";
            		    			 		
            		    				Data[0]=form.format(Integer.parseInt(temp));
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
            		    		      		    		    
            		    		    Data[5]=form.format(TotalBalz2);
            		    		   
            		            }	
	        	             
            		  		}catch(Exception eee){eee.printStackTrace();}
         	int ss=TotalBalz2+TotalBalz3;
         	Data[7]=form.format(ss);  		
            System.out.println(Data[0]+"--"+Data[1]+"--"+Data[2]+"--"+Data[3]+"--"+Data[4]+"--"+Data[5]);
            Data[6]=form.format(TotalCol);	  	
            return Data;		  		
      }
            		  
    public String[] getDateData()
    	{
    		 String Title[];
    		 java.util.Date DueDate=null;
    		 Title=new String[7];
    		 try{
    		 
    		        set=st.executeQuery("Select * from [Parameters/constants]");set.next();
    				DueDate =set.getDate("Cut-off Date");
    				
    				String StrYear=DueDate.getYear()+"";
    	    		StrYear=StrYear.substring(StrYear.length()-2,StrYear.length());
    				Title[6]=DueDate.getDate()+"-"+Dates[DueDate.getMonth()]+"-"+StrYear;
    				
    				Calendar cal = Calendar.getInstance();
    				cal.setTime(DueDate);
    				cal.add(Calendar.MONTH, +1);
    				DueDate = cal.getTime();
    				
    				
    		    }catch(Exception ee){}	
    		    	
    		    
    			java.util.Date tempdate=DueDate;
     			for(int ii=0;ii<6;ii++)
     			{
     		
    		
    				String StrYear=tempdate.getYear()+"";
    	    		StrYear=StrYear.substring(StrYear.length()-2,StrYear.length());
    	   			if(ii==5)StrYear="down";
    				Title[ii]=Dates[tempdate.getMonth()]+"-"+StrYear;    		
    				Calendar cal = Calendar.getInstance();
    				cal.setTime(tempdate);
    				cal.add(Calendar.MONTH, -1);
    				tempdate = cal.getTime();
     			}
				    
			 	    
			 return Title;	     
    	}        		
   	
    public void load()
  	{  	try
  		{
  		String arr[]={"DATE","DOCUMENT","AMOUNT","DISCOUNT","DEBIT","CREDIT","BALANCE"};
  		String arr3[]=new String[7];                       
        tableView=new MyTable();
        tableView.getTb().setRowSelectionAllowed(false);
        tableView.setRH(15);
        
        

        tableView.setFonts("Courier New",11);
        tableView.setData(arr2,arr);
        
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		tableView.getTb().getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		tableView.getTb().getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		tableView.getTb().getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		tableView.getTb().getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		tableView.getTb().getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
       	tableView.getTb().getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
       	tableView.getTb().setShowHorizontalLines(false);
       	tableView.getTb().setShowVerticalLines(false); 
       	
       	TableColumn column=tableView.getTb().getColumnModel().getColumn(0);
        column.setPreferredWidth(85);
        column=tableView.getTb().getColumnModel().getColumn(1);
        column.setPreferredWidth(70);
        column=tableView.getTb().getColumnModel().getColumn(2);
        column.setPreferredWidth(77);
        column=tableView.getTb().getColumnModel().getColumn(3);
        column.setPreferredWidth(79);
        column=tableView.getTb().getColumnModel().getColumn(4);
        column.setPreferredWidth(81);
        column=tableView.getTb().getColumnModel().getColumn(5);
        column.setPreferredWidth(77);
        column=tableView.getTb().getColumnModel().getColumn(6);
        column.setPreferredWidth(84);          
        	try{
    	
				set=st.executeQuery("Select * from [Account Entry] where [DR #]='"+MyDR+"'");set.next();
          	
            	
            			
            		    
            			input1[0]=set.getString("Customer");
            			input1[1]=set.getString("Address");
            			input1[2]=set.getString("Unit");
            			input1[3]=set.getString("Serial #");
            			input1[4]=set.getDate("DR Date")+"";
            			input1[5]=set.getString("Due Date");
            			input1[6]=set.getString("Collector");
            			input1[7]=set.getString("Dr #");
            			
            			input2[0]=set.getString("LCP");           		
            			input2[2]=set.getString("Downpayment");
            			input2[3]=set.getString("Installment");
            			input2[4]=set.getString("Term");
            			
            			
            	set=st.executeQuery("Select * from [Transaction Register] where [DR #]='"+MyDR+"' and [Transaction Type]='GROSS'");
            	while(set.next()){
            	
            	
            		
            		    Gross=Integer.parseInt(set.getString("Amount"));
            		    input2[1]=Gross+"";
            		    
            		    System.out.println(Gross+"fff");
    	
    	        String arr2[]={"","","","","","",form.format(Gross)};
    			tableView.insert(arr2);
            	}
    			
    			
    	
          	    set=st.executeQuery("Select * from [Transaction Register] where [DR #]='"+MyDR+"' order by date,[doc #]");
          	
          		int temp3=Gross;
          		arr=new String[7];
          		String Stemp="";
          		
           		while(set.next())
            		{   try{
            		
            				String transtype=set.getString("Transaction Type");
            			
            				
            			
            				if(transtype.equalsIgnoreCase("GROSS"))continue;
	            			int temp1=set.getInt("Discount");
	            			int temp2=set.getInt("Amount");
	            		    int totaltemp=temp1+temp2;
	            			
	            			if(transtype.equalsIgnoreCase("INSTALLMENT"))Stemp="Ori";
	            			if(transtype.equalsIgnoreCase("DOWNPAYMENT"))Stemp="Ord";	
            			
            				arr[0]=set.getDate("Date")+"";
            				arr[1]=Stemp+"-"+set.getString("Doc #");
            		
            				arr[3]=form.format(temp1);
            				arr[4]="";
            				arr[5]=form.format(temp2+temp1);
            				temp3=temp3-(temp1+temp2);
            				arr[6]=form.format(temp3);
            		    	arr[2]=form.format(temp2);
            		    	tableView.insert(arr);
            		    	try
            		    	{
            		    	   TotalCol+=totaltemp;
            		    	}catch(Exception ee){}
            		    	
            		      }catch(Exception ee){}
            		}	
    		}catch(Exception ee){ee.printStackTrace();}	
  	
  		
  	
  			
  		}catch(Exception ee){ee.printStackTrace();}
  	}
  
   	
    
  
    public static void main(String args[])
    {
        
    }
}