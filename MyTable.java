import javax.swing.*;import javax.swing.table.DefaultTableModel;
import java.awt.*;import java.util.*;
import java.awt.event.*;





 class MyTable extends JPanel 
        
    	{

    	 JTable tb;                    
    	 DefaultTableModel mod;
          
    	
      public void setFonts(String ss,int nn)
      	{
      		tb.setFont(new Font(ss,Font.BOLD,nn));
      	}
    		public MyTable()
    			{
                
    		  mod=new DefaultTableModel();
	    	  tb=new JTable(mod)
	    	  {  
  				public boolean isCellEditable(int row, int column){  
    
    				if(row < getRowCount() && column == 5)
    				{
    					 return true;
    				}  
    				return false;  
  					}  
		        };
    			
               

             



                
    			
              	tb.setRowHeight(20);
                tb.setAutoResizeMode( JTable.AUTO_RESIZE_OFF);
                add(JTable.createScrollPaneForTable(tb));


    			}
    			public void setRH(int nn){tb.setRowHeight(nn);}
    			public DefaultTableModel getMod(){return mod;}
    				public void insert(String []a)
    					{
    						mod.insertRow(mod.getRowCount(), a);
    					}
    				public void delete(int a)
    					{
    						mod.removeRow(a);
    					}
    				public int getcount(){return mod.getRowCount();}
    		    	public String getValue(int r,int c){return (String)mod.getValueAt(r,c);}
    		    	public void setValue(int r,int c,String s){mod.setValueAt(s,r,c);}
    		        public void setData(String[][] ss,String[] s){mod.setDataVector(ss,s);}
    		public Dimension getPreferredSize(){return new Dimension(1000,200);}
    		public void setResize()
    			{
    			     tb.setAutoResizeMode( JTable.AUTO_RESIZE_NEXT_COLUMN);
    			}
           public void setme()
           	{
           		tb.setPreferredScrollableViewportSize(new Dimension(500,getcount()*15));
           	}
      
           	public int getHR(){return tb.getRowHeight();}
           	public JTable getTb(){return tb;}
           	
           	

    	  
	

    
   }