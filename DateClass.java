import javax.swing.*;
import java.awt.*;

public class DateClass extends JPanel{
    JComboBox bm,bd,by;
    public DateClass()
    {   setLayout(new BorderLayout());
    	String sm[]={"       Jan       ","       Feb       ","       Mar       ","       Apr       ","       May       ","       Jun       ","       Jul       ","       Aug       ","       Sep       ","       Oct       ","       Nov       ","       Dec       "};
    	String sd[]={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
    	String sy[]={"     2006   ","     2007   ","     2008   ","     2009   ","     2010   ","     2011   ","     2012   ","     2013   ","     2014   ","     2015   ","     2016   ","     2017   ","     2018   ","     2019   ","     2020   "};
    	
    	bm=new JComboBox(sm);
    	by=new JComboBox(sy);
    	bd=new JComboBox(sd);
    	add(bm,BorderLayout.WEST);add(bd,BorderLayout.CENTER);add(by,BorderLayout.EAST);
    }
    
   public JComboBox getBy(){return by;}
   public JComboBox getBd(){return bd;}
   public JComboBox getBm(){return bm;}
   public String getSql()
   	{
   	   String sstemp=by.getSelectedItem()+"";
   	   sstemp.toString().trim();	
   	   return sstemp+"-"+(bm.getSelectedIndex()+1)+"-"+(bd.getSelectedIndex()+1);
   		 
   	} 
}