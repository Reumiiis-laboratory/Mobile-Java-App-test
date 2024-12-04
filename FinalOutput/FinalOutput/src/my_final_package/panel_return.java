
package my_final_package;

import javax.swing.JSpinner;
import db.connect.dbconnects;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class panel_return extends javax.swing.JPanel {
    Connection con ;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public panel_return() {
        initComponents();
        con = dbconnects.connectDB();
        
        
        txt_AmountTaken.hide();
    }
    
    public JTable getTblBorrow() {
        return tbl_borrow;
    }
    
    private void returnBook() {
    try {
        String getName = lbl_borrowername.getText(); // Get borrower name
        String getReturningBook = lbl_booktobereturned.getText(); // Get book to return
        
        // Step 1: Remove the corresponding record from the tbl_borrower table
        String deleteQuery = "DELETE FROM tbl_borrower WHERE name = ? AND book = ?";
        try (PreparedStatement pstDelete = con.prepareStatement(deleteQuery)) {
            pstDelete.setString(1, getName); // Set the borrower name
            pstDelete.setString(2, getReturningBook); // Set the book name
            int rowsDeleted = pstDelete.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Record removed successfully from tbl_borrower.");
            } else {
                System.out.println("No record found for this borrower and book.");
            }
        }

        // Step 2: Update the status of the book in tbl_bookdata to "Available"
        String updateStatusQuery = "UPDATE tbl_bookdata SET status = ? WHERE bookname = ?";
        try (PreparedStatement pstUpdate = con.prepareStatement(updateStatusQuery)) {
            pstUpdate.setString(1, "Available"); // Set status to "Available"
            pstUpdate.setString(2, getReturningBook); // Set book name
            int rowsUpdated = pstUpdate.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Book status updated to 'Available' in tbl_bookdata.");
            } else {
                System.out.println("No book found with this name.");
            }
        }

        // Step 3: Clear the labels and reload the table
        lbl_borrowername.setText("");
        lbl_booktobereturned.setText("");
        loadTable(); // Refresh the table after returning the book
        
        // Show success message
        JOptionPane.showMessageDialog(this, "Successfully returned the book.");
        
    } catch (SQLException e) {
        // Handle any SQL exceptions
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}
    
         public void loadTable() {
    try {
        // Modify the query to order by the date column in ascending order
        String sql = "SELECT * FROM tbl_borrower ORDER BY date ASC"; 
        
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        tbl_borrow.setModel(buildTableModel(rs)); // Update table model with sorted data
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    } finally {
        try {
            if (rs != null) rs.close();
            if (pst != null) pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
    ResultSetMetaData metaData = rs.getMetaData();
    int columnCount = metaData.getColumnCount();
    
    Vector<String> columnNames = new Vector<>();
    for (int i = 1; i <= columnCount; i++) {
        columnNames.add(metaData.getColumnName(i));
    }
    
    Vector<Vector<Object>> data = new Vector<>();
    while (rs.next()) {
        Vector<Object> row = new Vector<>();
        for (int i = 1; i <= columnCount; i++) {
            row.add(rs.getObject(i));
        }
        data.add(row);
    }
    
    return new DefaultTableModel(data, columnNames);
}

private static long CountDays(String startDateStr, String endDateStr, DateTimeFormatter dateFormatter) {
        LocalDate startDate = LocalDate.parse(startDateStr, dateFormatter);
        LocalDate endDate = LocalDate.parse(endDateStr, dateFormatter);

        
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_borrow = new javax.swing.JTable();
        btn_returnbook = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbl_booktobereturned = new javax.swing.JLabel();
        lbl_Notifier = new javax.swing.JLabel();
        lbl_borrowername = new javax.swing.JLabel();
        txt_AmountTaken = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lbl_TotalChange = new javax.swing.JLabel();
        lbl_TotalPenalty = new javax.swing.JLabel();
        btn_refresh = new javax.swing.JLabel();
        txt_borrowsearch = new javax.swing.JTextField();

        setBackground(new java.awt.Color(238, 225, 207));
        setLayout(null);

        tbl_borrow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Contact", "Number", "Book name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_borrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_borrowMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_borrow);
        if (tbl_borrow.getColumnModel().getColumnCount() > 0) {
            tbl_borrow.getColumnModel().getColumn(0).setResizable(false);
            tbl_borrow.getColumnModel().getColumn(1).setResizable(false);
            tbl_borrow.getColumnModel().getColumn(2).setResizable(false);
            tbl_borrow.getColumnModel().getColumn(3).setResizable(false);
        }

        add(jScrollPane1);
        jScrollPane1.setBounds(200, 50, 452, 220);

        btn_returnbook.setBackground(new java.awt.Color(38, 53, 96));
        btn_returnbook.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_returnbook.setForeground(new java.awt.Color(255, 255, 255));
        btn_returnbook.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_returnbook.setText("Return Book");
        btn_returnbook.setOpaque(true);
        btn_returnbook.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_returnbookMouseClicked(evt);
            }
        });
        add(btn_returnbook);
        btn_returnbook.setBounds(10, 430, 160, 40);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Book name:");
        add(jLabel1);
        jLabel1.setBounds(10, 170, 180, 40);

        lbl_booktobereturned.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add(lbl_booktobereturned);
        lbl_booktobereturned.setBounds(20, 220, 180, 40);

        lbl_Notifier.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add(lbl_Notifier);
        lbl_Notifier.setBounds(200, 280, 310, 40);

        lbl_borrowername.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add(lbl_borrowername);
        lbl_borrowername.setBounds(20, 120, 180, 40);
        add(txt_AmountTaken);
        txt_AmountTaken.setBounds(200, 420, 160, 30);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Name of the borrower:");
        add(jLabel3);
        jLabel3.setBounds(10, 70, 180, 40);

        lbl_TotalChange.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add(lbl_TotalChange);
        lbl_TotalChange.setBounds(200, 370, 170, 40);

        lbl_TotalPenalty.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add(lbl_TotalPenalty);
        lbl_TotalPenalty.setBounds(200, 320, 170, 40);

        btn_refresh.setBackground(new java.awt.Color(38, 53, 96));
        btn_refresh.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_refresh.setForeground(new java.awt.Color(255, 255, 255));
        btn_refresh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_refresh.setText("Refresh");
        btn_refresh.setOpaque(true);
        btn_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_refreshMouseClicked(evt);
            }
        });
        add(btn_refresh);
        btn_refresh.setBounds(10, 380, 160, 40);

        txt_borrowsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_borrowsearchKeyReleased(evt);
            }
        });
        add(txt_borrowsearch);
        txt_borrowsearch.setBounds(200, 10, 450, 30);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_returnbookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_returnbookMouseClicked
       try{
         DefaultTableModel tableinfo = (DefaultTableModel)tbl_borrow.getModel();
        
         int selectedRowIndex = tbl_borrow.getSelectedRow();
        
         String getName = lbl_borrowername.getText(); // Get borrower name
        String getReturningBook = lbl_booktobereturned.getText(); // Get book to return

         String returningDate = tableinfo.getValueAt(selectedRowIndex, 2).toString();
         
         DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
         LocalDate currentDate = LocalDate.now();
         String currentDateStr = currentDate.format(dateFormatter);
        
        long DaysCount = CountDays(currentDateStr,returningDate, dateFormatter) * -1;
        
        
        if(DaysCount <= 0) {
            
                returnBook();
                
                
                loadTable();
        
        } else {
            
            double totalCharge = DaysCount * 10;
            
            String Input = txt_AmountTaken.getText();
        if(Input.matches("//d+")) {
            System.out.println("working");
            JOptionPane.showMessageDialog(this, "It must only contain numbers");
        } else {
            double inPut = Double.parseDouble(Input);
            if(totalCharge > inPut) {
                JOptionPane.showMessageDialog(this, "You have Inputted lower than the Charge rate!");                
            }else {
                double Change = inPut - totalCharge;
                JOptionPane.showMessageDialog(this, "Your change is: " + Change);
                
                returnBook();
                
                lbl_Notifier.setText("");
                lbl_TotalChange.setText("");
                lbl_TotalChange.setText("");
                lbl_TotalChange.setText("");
                txt_AmountTaken.hide();
                txt_AmountTaken.setText("");
                
                loadTable();
                
                String currentDirectory = Paths.get("").toAbsolutePath().toString();
                String Filepath = currentDirectory + "/output.txt";
                
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(Filepath));
                    
                    String SavingFile = "Date: " + currentDateStr  + "|| Name:"+ getName +"|| Book Name: "
                                        + getReturningBook +"|| TotalCharge: "
                                        + totalCharge +"|| Amount: "+ inPut +"|| Change: "+ Change;
                    writer.write(SavingFile);
                    writer.newLine();
                    
                    writer.close();
                    
                    JOptionPane.showMessageDialog(this, "Transaction Saved at: " + Filepath);
                    
                } catch(IOException e) {
                    System.err.println("Error writing file: " + e.getMessage());
                }
                
            }
        }
        }
        
         
         
         
         
        } catch(Exception e){
            
        }
    }//GEN-LAST:event_btn_returnbookMouseClicked

    private void tbl_borrowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_borrowMouseClicked
       DefaultTableModel tableinfo = (DefaultTableModel)tbl_borrow.getModel();
        
        int selectedRowIndex = tbl_borrow.getSelectedRow();
        
        lbl_borrowername.setText(tableinfo.getValueAt(selectedRowIndex,0).toString());
        lbl_booktobereturned.setText(tableinfo.getValueAt(selectedRowIndex,3).toString());
        
        String ReturnDate = tableinfo.getValueAt(selectedRowIndex, 2).toString();
        
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        LocalDate currentDate = LocalDate.now();
        String currentDateStr = currentDate.format(dateFormatter);
        
        long DaysCount = CountDays(currentDateStr,ReturnDate, dateFormatter);
        System.out.println(DaysCount);
        if(0 > DaysCount) {
            
            lbl_Notifier.setText("This Person has exceeded the due date!");
            long totalDays = DaysCount * -1;
            double totalCharge = totalDays * 10;
            lbl_TotalPenalty.setText("Total: " + totalCharge + " Php");
            lbl_TotalChange.setText("Insert Amount");
            txt_AmountTaken.show();
            txt_AmountTaken.setText("");
        }else{
            lbl_Notifier.setText("");
            lbl_TotalChange.setText("");
            lbl_TotalPenalty.setText("");
            lbl_TotalChange.setText("");
            txt_AmountTaken.hide();
            txt_AmountTaken.setText("");
        }
        
        
    }//GEN-LAST:event_tbl_borrowMouseClicked

    private void btn_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_refreshMouseClicked
       loadTable();
       
    }//GEN-LAST:event_btn_refreshMouseClicked

    private void txt_borrowsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_borrowsearchKeyReleased
                                             
       DefaultTableModel table = (DefaultTableModel) tbl_borrow.getModel();
    String find = txt_borrowsearch.getText().toUpperCase();
    TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(table);
    tbl_borrow.setRowSorter(tr);


    tr.setRowFilter(RowFilter.regexFilter("(?i)" + find, 0));  // 0 is the column index for book name
    
    }//GEN-LAST:event_txt_borrowsearchKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_refresh;
    private javax.swing.JLabel btn_returnbook;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_Notifier;
    private javax.swing.JLabel lbl_TotalChange;
    private javax.swing.JLabel lbl_TotalPenalty;
    private javax.swing.JLabel lbl_booktobereturned;
    private javax.swing.JLabel lbl_borrowername;
    private javax.swing.JTable tbl_borrow;
    private javax.swing.JTextField txt_AmountTaken;
    private javax.swing.JTextField txt_borrowsearch;
    // End of variables declaration//GEN-END:variables
}
