import java.io.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

class JTableDatabase extends JFrame{
    ResultSet rs;
    JTableDatabase(){
    final Vector columnNames = new Vector();
        final Vector data = new Vector();
      JPanel panel=new JPanel();
      try{
    Class.forName("com.mysql.jdbc.Driver");
     Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
        Statement st = con.createStatement();
        ResultSet rs= st.executeQuery("Select * from employee");
    ResultSetMetaData md = rs.getMetaData();
int columns = md.getColumnCount();
for (int i = 1; i <= columns; i++) {
columnNames.addElement( md.getColumnName(i) );
}
while (rs.next()) {
Vector row = new Vector(columns);
for (int i = 1; i <= columns; i++) {
row.addElement( rs.getObject(i) );
}
data.addElement( row );
}
}
catch(Exception e){}
JTable table = new JTable(data, columnNames);
JScrollPane scrollPane = new JScrollPane(table);
panel.add(scrollPane);
add(panel);
}
 public static void main(String arg[])
  {
    try
    {
    JTableDatabase frame=new JTableDatabase();
    frame.setSize(550,200);
    frame.setVisible(true);
    }
  catch(Exception e)
    {}
  }
}