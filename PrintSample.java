    import javax.swing.*;   
    import javax.swing.table.*;   
    import java.awt.print.*;   
    import java.util.*;   
    import java.awt.*;   
    import java.awt.event.*;   
    import java.awt.geom.*;   
    import java.awt.Dimension;   
    import java.sql.*;   
       
    public class PrintSample extends JPanel implements Printable {   
       JTable tableView;   
       public PrintSample(JTable mytb)   
            {   
                this.setLayout(new BorderLayout());   
                JLabel title = new JLabel("title");   
                title.setBounds(295,5,300,20);   
                this.add(title);   
                try   
                {   
                   
                    tableView = mytb;  
                    tableView.setPreferredScrollableViewportSize(new Dimension(780, 400));    
                    JScrollPane scrollPane = new JScrollPane(tableView);   
                    scrollPane.setPreferredSize(new Dimension(550, 80));   
                    this.add(BorderLayout.CENTER, scrollPane);    
                }        
                catch(Exception err)        
                {}   
                JButton printButton = new JButton();   
                printButton.setText("Print it !");   
                this.add(BorderLayout.SOUTH, printButton);   
                printButton.addActionListener( new ActionListener()   
                {   
                    public void actionPerformed(ActionEvent evt)   
                    {   
                        PrinterJob pj = PrinterJob.getPrinterJob();      
                        pj.setPrintable(PrintSample.this);      
                        pj.printDialog();      
                        try   
                        {   
                            pj.print();      
                        }   
                        catch (Exception PrintException)   
                        {   
                        }      
                    }   
                }   
                );   
            }   
       
       public int print(Graphics g, PageFormat pageFormat, int pageIndex)throws PrinterException   
            {   
                Graphics2D g2 = (Graphics2D)g;   
                g2.setColor(Color.black);   
                int fontHeight = g2.getFontMetrics().getHeight();   
                int fontDescent = g2.getFontMetrics().getDescent();   
          
                // reserve spaces for page number   
                double pageHeight = pageFormat.getImageableHeight() - fontHeight;      
                double pageWidth = pageFormat.getImageableWidth();   
                double tableWidth = (double)tableView.getColumnModel().getTotalColumnWidth();   
                double scale = 1;   
                if (tableWidth >= pageWidth)   
                {      
                    scale = pageWidth / tableWidth;   
                }   
                double headerHeightOnPage = tableView.getTableHeader().getHeight()*scale;   
                double tableWidthOnPage = tableWidth * scale;   
                double oneRowHeight = (tableView.getRowHeight() + tableView.getRowMargin())*scale;   
                int numRowsOnAPage = (int)((pageHeight-headerHeightOnPage) / oneRowHeight);   
                double pageHeightForTable = oneRowHeight * numRowsOnAPage;   
                int totalNumPages = (int)Math.ceil(((double)tableView.getRowCount()) / numRowsOnAPage);   
                if (pageIndex >= totalNumPages)      
                    return NO_SUCH_PAGE;   
                  
                g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());   
                 
                g2.drawString("Page: " + (pageIndex +1),(int)pageWidth/2 - 35, (int)(pageHeight + fontHeight - fontDescent));   
                g2.translate(0f, headerHeightOnPage);   
                g2.translate(0f, -pageIndex*pageHeightForTable);   
                           if (pageIndex + 1 == totalNumPages)   
                {      
                    int lastRowPrinted = numRowsOnAPage * pageIndex;      
                    int numRowsLeft = tableView.getRowCount() - lastRowPrinted;      
                    g2.setClip(0, (int)(pageHeightForTable * pageIndex),(int)Math.ceil(tableWidthOnPage),(int)Math.ceil(oneRowHeight * numRowsLeft));   
                }   
                           else   
                {   
                    g2.setClip(0, (int)(pageHeightForTable * pageIndex),(int)Math.ceil(tableWidthOnPage),(int)Math.ceil(pageHeightForTable));   
                }   
                g2.scale(scale, scale);   
                tableView.paint(g2);   
                            g2.scale(1/scale, 1/scale);   
                g2.translate(0f, pageIndex*pageHeightForTable);   
                g2.translate(0f, -headerHeightOnPage);   
                g2.setClip(0, 0,(int)Math.ceil(tableWidthOnPage),(int)Math.ceil(headerHeightOnPage));   
                g2.scale(scale, scale);   
                tableView.getTableHeader().paint(g2);   
                return Printable.PAGE_EXISTS;   
            }   
    }  