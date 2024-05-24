

import javax.swing.*;
import javax.swing.table.*;
import java.awt.print.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.Dimension;
import java.text.DecimalFormat;
public class TransPrint3 implements Printable{

 
  
  JTable tableView;
  String MyName="";
 
  int Gross;
  DecimalFormat form= new DecimalFormat("###,###.00");
 
  int counter;
  public TransPrint3(JButton but,JTable tableView) 
  	{
  	try{
  	  
  	  
      this.tableView=tableView;
      this.counter=counter;
      

      but.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent evt) {

          PrinterJob pj=PrinterJob.getPrinterJob();
          pj.setPrintable(TransPrint3.this);
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
     	double pageHeight =pageFormat.getImageableHeight()-fontHeight-115;
     	double pageWidth =pageFormat.getImageableWidth();
     	double tableWidth = (double)tableView.getColumnModel().getTotalColumnWidth();
     	double scale = 1;
     	if (tableWidth >= pageWidth) {
		scale =  pageWidth / tableWidth;
	    }
	    
	    

     	double headerHeightOnPage= tableView.getTableHeader().getHeight()*scale;
     	double tableWidthOnPage=(tableWidth*scale);

     	double oneRowHeight=(tableView.getRowHeight()+tableView.getRowMargin())*scale;
     	System.out.println(oneRowHeight+"------ROWHEIGHT------");
     	int numRowsOnAPage=(int)((pageHeight-headerHeightOnPage)/oneRowHeight);
     	double pageHeightForTable=oneRowHeight*numRowsOnAPage;
     	
     	int totalNumPages=(int)Math.ceil(((double)tableView.getRowCount())/numRowsOnAPage);
     	System.out.println(totalNumPages+"------TOTALNUMPAGES------");
     	if(pageIndex>=totalNumPages) {
                      return NO_SUCH_PAGE;
     	}
     	    
     		g2.setColor(Color.BLUE);
     		g2.setFont(new Font("Courier New",Font.BOLD,25));
        	g2.drawString("Trasaction Summary",20,60);
     		int start=90;		
        	g2.drawLine(20,start, 574,start);
        	g2.drawLine(20,start+3,574,start+3);
        	g2.setFont(new Font("Courier New",Font.PLAIN,11));
        	g2.drawString("DATE",20,start+15);
        	g2.drawString("DOC #",110,start+15);
        	g2.drawString("DR #",190,start+15);
        	g2.drawString("TRANSACTION",210,start+15);
        	g2.drawString("AMOUNT",372,start+15);
        	g2.drawString("DISCOUNT",425,start+15);
        	g2.drawString("REMARKS",520,start+15);
        	g2.drawLine(20,start+20,574,start+20);	
        
        
//bottom center
        g2.translate(pageFormat.getImageableX()+20,pageFormat.getImageableY()+112); 
        g2.setFont(new Font("Courier New",Font.PLAIN,15));	   
     	g2.drawString("Page: "+(pageIndex+1),(int)pageWidth/2-35, (int)(pageHeight+fontHeight-fontDesent));

     	//g2.translate(0f,headerHeightOnPage);
     	g2.translate(0f,-pageIndex*pageHeightForTable);

     	//If this piece of the table is smaller
     	//than the size available,
     	//clip to the appropriate bounds.
     	if (pageIndex + 1 == totalNumPages) {
           int lastRowPrinted =
           numRowsOnAPage * pageIndex;
           int numRowsLeft =tableView.getRowCount()- lastRowPrinted;
           g2.setClip(0,(int)(pageHeightForTable * pageIndex),(int) Math.ceil(tableWidthOnPage),(int) Math.ceil(oneRowHeight * numRowsLeft));
     	}

     	//else clip to the entire area available.
     	else{
             g2.setClip(0,(int)(pageHeightForTable*pageIndex),(int) Math.ceil(tableWidthOnPage),(int) Math.ceil(pageHeightForTable));
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
     	
     	
     
     	System.out.println(0f+"------------------");
     	//paint header at top

     	return Printable.PAGE_EXISTS;
   }


}