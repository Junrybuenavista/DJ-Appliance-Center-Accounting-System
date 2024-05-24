import java.sql.*;
import java.util.Date;

public class Balance {

    String MyDr;
    int term;
    int balance[];
    Connection conn1;	
  	Statement stt1=null;
 
  	ResultSet set1,set2;
  	int Install;
  	int Gross;
  	int Month=12;
  	int Year=2012;
  	String dateAcc;
  	
  	int Month2=11;
  	int Year2=2012;
  	String dateAcc2;
    public Balance(String MyDr,int term,int Install) 
    {
    		this.MyDr=MyDr;
    		this.term=term;
    		balance=new int[term];
    		this.Install=Install;
    			
  			 try{ 
                     Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");  				
    				 conn1 = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=Copy.mdb;DriverID=22;READONLY=true;) ","","");
    				 
    				 //conn = DriverManager.getConnection("jdbc:odbc:mydb","","");    				    			
    				 stt1=conn1.createStatement();
    			
    				 	
    				 			 
    				 
	             }catch(Exception e){e.printStackTrace();} 
	       
	       for(int i=0;i<term;i++)
	       	{
	       	   balance[i]=Install;
	       	}
	       	             	 	   	
    }
   public int checkBalance(String testDR)
  		{  int ret=0;
  			try
  			{
  			
  				set1=stt1.executeQuery("Select * from [Transaction Register] where [DR #]='"+testDR+"' order by date,[doc #]");
           		while(set1.next())
           		{  
           			
           			String transtype=set1.getString("Transaction Type");
            		if(transtype.equalsIgnoreCase("GROSS"))continue;
            		if(transtype.equalsIgnoreCase("DOWNPAYMENT"))continue;
            		
           			int temp1=set1.getInt("Discount");
	            	int temp2=set1.getInt("Amount");
	            	ret+=(temp1+temp2);
           		}
  				
  			}catch(Exception ee){ee.printStackTrace();}
  			return ret;
  		}
    public void getBalance(int ii)
    {
    	 
	       	   
	       	        
	       	     	int subra=0;
	       	     	for(int i1=0;i1<term;i1++)
	       	     	{  
	       	     		if(Month>12){Month=1;Year++;}
	       	     	  	dateAcc=Year+"-"+Month+"-28";
	       	     	  	if(Month2>12){Month2=1;Year2++;}
	       	     	  	dateAcc2=Year2+"-"+Month2+"-28";
	       	     	  	
	       	     	  	System.out.print(dateAcc+"\t");System.out.print(dateAcc2+"\t");
	       	     	    try
	       	     		{  	
	       	     	  			
	       	     				set1=stt1.executeQuery("Select * from [Transaction Register] where [DR #]='"+MyDr+"' and [date]>=#"+dateAcc2+"# and [date]<=#"+dateAcc+"#");
	       	     			    set1.next();
	       	     				
	       	     									           					
	       	     			                int am=0;
	       	     			    			String transtype=set1.getString("Transaction Type");
	       	     			    		
	       	     			    			if(transtype.equalsIgnoreCase("GROSS")){set1.next();}
	       	     			    			else if(transtype.equalsIgnoreCase("DOWNPAYMENT")){set1.next();}
            								else{
            								
	       	     			    		
	       	     			    		    am=set1.getInt("Amount")+set1.getInt("Discount");
            								}
	       	     			    			System.out.print(am+"--");
	       	     			    			
	       	     			    			
	       	     			    		     	     			    		   
	       	     	      	  			
	       	     	      	  				
	       	     			    		     
	       	     			    		     
	       	     			    		     
	       	     			    		     	   			    		    	   	
	       	     			    		     int temp=balance[i1]-am-subra;
	       	     			    		    
	       	     			    		     if(temp<0){subra=Math.abs(temp);balance[i1]=0;}
	       	     			    		     else balance[i1]=balance[i1]-am-subra;
	       	     			    		     
	       	     			    		     	   
	       	     			    		    
	       	     	      }catch(Exception ee)
	       	     	      	{               
	       	     	      		             
	       	     	      	  				int temp=balance[i1]-subra;
	       	     	      	  				if(temp<0){subra-=Install;balance[i1]=0;}
	       	     	      	  				else {balance[i1]-=subra;subra=0; }
	       	     	      	}
	       	     			
	       	     	   		    	
	       	     			
	       	     	   Month++;
	                   Month2++;
	                   System.out.print(balance[i1]+" ");	      
	       	     	   System.out.println("Subra="+subra);
	       	     	   System.out.println();				
	       	        
	       	     }
	       	    
	        System.out.println("_____________________________________________________");
	       	System.out.println(Gross+"\t"+term);
	       	for(int i=0;i<term;i++)
	       	{
	       	    System.out.println((i+1)+"\t\t"+balance[i]+"\t\t\t");
	       	}	   
    }
    public java.util.Date getDate()
    {   
    	java.util.Date d=null;
    	try
    	{
    		set1=stt1.executeQuery("Select * from [Transaction Register] where [DR #]='"+MyDr+"' order by date,[doc #]");
    		set1.next();
    		d=set1.getDate("date");
    	}catch(Exception ee){}
    	
    	return d;
    }
    public int getAllTotal()
    {
    	int ret=0;
        
         for(int i=0;i<term;i++)
	       	{
	       	    ret+=balance[i];
	       	}
       
    	return ret;
    }
    public void f1()
    {
    		for(int i=0;i<term;i++)
	       	{
	       	   balance[i]=Install;
	       	}   		
    		int numM=checkBalance(MyDr)/Install;
    		for(int i=0;i<numM;i++)
	       	{
	       	   balance[i]=0;
	       	}
	       	try
	       	{
	       	
	       	balance[numM]=Install-(checkBalance(MyDr)-(Install*numM));
	       	}catch(Exception ee){}
	       		
	      	 
    		//for(int i=0;i<term;i++)
	       	//{
	      //	   System.out.println((i+1)+"\t\t"+balance[i]);
	       //	} 
    }
    public int getNumber(int ee)
    	{   
    		
    		
    		return balance[ee];
    	}	
    	public static void main(String args[])
    	{
    		Balance b=new Balance("01999",3,3457);
    		//b.getBalance(0);
    	    b.f1();
    	    
    	    
    	    System.out.println();
    	    System.out.println(b.getDate());
    	    System.out.println("All Total\t"+b.getAllTotal());
    	} 
	}