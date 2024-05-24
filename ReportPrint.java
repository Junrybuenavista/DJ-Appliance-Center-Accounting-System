

import javax.swing.*;
import javax.swing.table.*;
import java.awt.print.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.Dimension;
import java.text.DecimalFormat;
public class ReportPrint implements Printable{

 
  String fr="",to="",tp="";
  JTable tableView;
  String MyName="";
  String Customer,Address,Item,Serial,DRDate,Due,Collector,LCP,Downpayment,Installment,Term;
  int Gross;
  DecimalFormat form= new DecimalFormat("###,###.00");
  String input1[],input2[],input3[];
  int counter;
  public ReportPrint(JButton but,JTable tableView,String[] input1,String[] input2,int counter) 
  	{
  	try{
  	  this.input1=input1;
  	  this.input2=input2;
      this.tableView=tableView;
      this.counter=counter;
      
      input3=Param.load2();
      but.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent evt) {

          PrinterJob pj=PrinterJob.getPrinterJob();
          pj.setPrintable(ReportPrint.this);
          pj.printDialog();
          try{
            pj.print();
          }catch (Exception PrintException) {}
          }
        });

  	  }catch(Exception ee){ee.printStackTrace();}
     }

     public int print(Graphics g, PageFormat pageFormat,
        int pageIndex) throws PrinterException {
     	Graphics2D  g2 = (Graphics2D) g;
     	g2.setColor(Color.black);
     	int fontHeight=g2.getFontMetrics().getHeight();
     	int fontDesent=g2.getFontMetrics().getDescent();

     	//leave room for page number
     	double pageHeight =pageFormat.getImageableHeight()-fontHeight;
     	double pageWidth =pageFormat.getImageableWidth();
     	double tableWidth = (double)tableView.getColumnModel().getTotalColumnWidth();
     	double scale = 1;
     	if (tableWidth >= pageWidth) {
		scale =  pageWidth / tableWidth;
	    }

     	double headerHeightOnPage=tableView.getTableHeader().getHeight()*scale;
     	double tableWidthOnPage=tableWidth*scale;

     	double oneRowHeight=(tableView.getRowHeight()+tableView.getRowMargin())*scale;
     	int numRowsOnAPage=(int)((pageHeight-headerHeightOnPage)/oneRowHeight);
     	double pageHeightForTable=oneRowHeight*numRowsOnAPage;
     	int totalNumPages=(int)Math.ceil(((double)tableView.getRowCount())/numRowsOnAPage);
     	if(pageIndex>=totalNumPages) {
                     return NO_SUCH_PAGE;
     	}
            g2.setFont(new Font("Courier New",Font.PLAIN,18));
            g2.setColor(Color.BLUE);
            g2.drawString(input3[0],20,30);
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Courier New",Font.PLAIN,10));
        	g2.drawString(input3[1],20,45);
        	g2.drawString(input3[4]+", "+input3[5]+" "+input3[6],20,55);
        	int start=75;
        	g2.setFont(new Font("Courier New",Font.PLAIN,10));
        	g2.setColor(Color.BLUE);
        	String ss[]={"Name\t:","Address\t:","Item\\Unit\t:","Serial #:","DR Date\t:","Due Date\t:","Collector\t:"};
        	String ss2[]={"LCP\t:","GROSS\t:","DOWNPAYMENT\t:","INSTALLMENT:","TERM\t:"};
        	for(int ii=0;ii<7;ii++)
        		{
        		  g2.drawString(ss[ii],20,start);
        		  if(ii>1)
        		  	{
        		  	   g2.drawString(ss2[ii-2],430,start);	
        		  	}
        		  start+=15;
        		}
        	g2.setColor(Color.BLACK);	
        	start=75;	
        	try{
        	
        	
        	for(int ii=0;ii<7;ii++)
        		{
        			
        		  if(ii==0)g2.drawString(input1[7]+" "+input1[ii],90,start);	
        		  else g2.drawString(input1[ii],90,start);
        		  if(ii>1)
        		  	{
        		  	
            		
        		  	   if((ii-2)==4)
        		  	   	{
        		  	   		int actual_width = g2.getFontMetrics().stringWidth(input2[ii-2]+" MONTH/S");
            				int x = 670 - actual_width - 100;
        		  	   		g2.drawString(input2[ii-2]+" MONTH/S",x,start);
        		  	   	}
        		  	   else
        		  	   	{
        		  	        int actual_width = g2.getFontMetrics().stringWidth(form.format(Integer.parseInt(input2[ii-2])));
            		        int x = 670 - actual_width - 100;
        		  	   		g2.drawString(form.format(Integer.parseInt(input2[ii-2])),x,start);
        		  	    }	
        		  	}
        		  start+=15;
        		}
        	}		
        	catch(Exception ee){ee.printStackTrace();}
        	g2.setColor(Color.BLUE);		
        	g2.drawLine(20,start, 574,start);
        	g2.drawLine(20,start+3,574,start+3);
        	g2.setFont(new Font("Courier New",Font.PLAIN,11));
        	g2.drawString("DATE",20,start+15);
        	g2.drawString("DOCUMENT",125,start+15);
        	g2.drawString("AMOUNT",215,start+15);
        	g2.drawString("DISCOUNT",280,start+15);
        	g2.drawString("DEBIT",378,start+15);
        	g2.drawString("CREDIT",450,start+15);
        	g2.drawString("BALANCE",520,start+15);
        	g2.drawLine(20,start+20,574,start+20);	
        		
        g2.setColor(Color.BLUE);
     	g2.drawLine(20,(counter*15)+32+start, 574,(counter*15)+32+start);
     	g2.drawString("-oOo- Nothing Follows -oOo-",200,(counter*15)+45+start);	
     		
     		int start2=(counter*15)+85+start;
     		 
     		g2.setColor(Color.BLACK);       			
            g2.drawString("Prepared by:",20,start2);
            g2.setFont(new Font("Courier New",Font.BOLD,10));
            g2.drawString(input3[8],20,start2+40);
            g2.setFont(new Font("Courier New",Font.PLAIN,10));
            g2.drawString("Clerk/Encoder",20,start2+50);
            
            g2.drawString("Verified by:",230,start2);
            g2.setFont(new Font("Courier New",Font.BOLD,10));
            g2.drawString(input3[3],230,start2+40);
            g2.setFont(new Font("Courier New",Font.PLAIN,10));
            g2.drawString("Cashier",230,start2+50);
            
            g2.drawString("Noted by:",440,start2);
            g2.setFont(new Font("Courier New",Font.BOLD,10));
            g2.drawString(input3[2],440,start2+40);
            g2.setFont(new Font("Courier New",Font.PLAIN,10));
            g2.drawString("Branch Manager",440,start2+50);
        		
          	   			
        
     	g2.translate(pageFormat.getImageableX()+20,
                       pageFormat.getImageableY()+190);
//bottom center

     	g2.drawString("Page: "+(pageIndex+1),
     	    (int)pageWidth/2-35, (int)(pageHeight
     	    +fontHeight-fontDesent));

     	g2.translate(0f,headerHeightOnPage);
     	g2.translate(0f,-pageIndex*pageHeightForTable);

     	//If this piece of the table is smaller
     	//than the size available,
     	//clip to the appropriate bounds.
     	if (pageIndex + 1 == totalNumPages) {
           int lastRowPrinted =
                 numRowsOnAPage * pageIndex;
           int numRowsLeft =
                 tableView.getRowCount()
                 - lastRowPrinted;
           g2.setClip(0,
             (int)(pageHeightForTable * pageIndex),
             (int) Math.ceil(tableWidthOnPage),
             (int) Math.ceil(oneRowHeight *
                               numRowsLeft));
     	}
     	//else clip to the entire area available.
     	else{
             g2.setClip(0,
             (int)(pageHeightForTable*pageIndex),
             (int) Math.ceil(tableWidthOnPage),
             (int) Math.ceil(pageHeightForTable));
     	}

     	g2.scale(scale,scale);
     	tableView.paint(g2);
     	g2.scale(1/scale,1/scale);
     	g2.translate(0f,pageIndex*pageHeightForTable);
     	g2.translate(0f, -headerHeightOnPage);
     	g2.setClip(0, 0,
     	  (int) Math.ceil(tableWidthOnPage),
          (int)Math.ceil(headerHeightOnPage));
     	g2.scale(scale,scale);
     	
     	
     
     	
     	//paint header at top

     	return Printable.PAGE_EXISTS;
   }


}