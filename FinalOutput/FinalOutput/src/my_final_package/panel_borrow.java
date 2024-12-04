package my_final_package;

import javax.swing.JSpinner;
import db.connect.dbconnects;
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
import javax.swing.text.TableView;

public class panel_borrow extends javax.swing.JPanel {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public panel_borrow() {
        initComponents();
        con = dbconnects.connectDB();
        datechooserLoad();
        panel_main manpan = new panel_main();
        
        panel_return retpan = new panel_return();
        loadTables(retpan.getTblBorrow());
        
    }

    public void datechooserLoad() {
    // Create a SpinnerDateModel to handle date selection
    SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
    date_spinner.setModel(model);

    // Set a date editor with the desired format
    JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(date_spinner, "yyyy-MM-dd");
    date_spinner.setEditor(timeEditor);
    }
    private static long CountDays(String startDateStr, String endDateStr, DateTimeFormatter dateFormatter) {
        LocalDate startDate = LocalDate.parse(startDateStr, dateFormatter);
        LocalDate endDate = LocalDate.parse(endDateStr, dateFormatter);

        
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
    public boolean isBookAvailable(String bookTitle) {
    // Query to check the status of the book in tbl_bookdata
    String query = "SELECT status FROM tbl_bookdata WHERE bookname = ?";
    try (var pst = con.prepareStatement(query)) {
        pst.setString(1, bookTitle); // Set the book title in the query
        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                String status = rs.getString("status");
                // Return true if the status is "Available", otherwise return false
                return "Available".equalsIgnoreCase(status);
            }
        }
    } catch (Exception e) {
        // Handle any exception (optional: log the error)
        e.printStackTrace();
    }
    return false; // Return false if the book is not found or the status is not "Available"
}
   public void loadTables(JTable tbl_borrow) {
        Connection con = dbconnects.connectDB();
        
        // Loading data for tbl_borrower
        String query1 = "SELECT * FROM tbl_borrower";
        try (var stmt = con.createStatement()) {
            ResultSet rs1 = stmt.executeQuery(query1);
            
            // Populate your first table (tbl_borrow) with the data
            DefaultTableModel model1 = (DefaultTableModel) tbl_borrow.getModel();
            
             model1.setRowCount(0);
             
            while (rs1.next()) {
                // Assuming your table has 4 columns, adjust accordingly
                model1.addRow(new Object[]{rs1.getString("name"), rs1.getString("contact"), rs1.getString("date"), rs1.getString("book")});
            }
        } catch (SQLException e) {
            System.out.println("Error loading tbl_borrower: " + e.getMessage());
        }

        // Loading data for tbl_bookdata
        String query2 = "SELECT * FROM tbl_bookdata";
        try (var stmt = con.createStatement()) {
            ResultSet rs2 = stmt.executeQuery(query2);
            
            // Populate your second table (tbl_books) with the data
            DefaultTableModel model2 = (DefaultTableModel) tbl_book.getModel();
            
            model2.setRowCount(0);
            
            while (rs2.next()) {
                // Assuming your table has 4 columns, adjust accordingly
                model2.addRow(new Object[]{rs2.getString("bookname"), rs2.getString("status")});
            }
        } catch (SQLException e) {
            System.out.println("Error loading tbl_bookdata: " + e.getMessage());
        }
    }
    public void LoadBooks() {
    try   {
        //  it alphabetically sorted the books
       String sql = "SELECT * FROM tbl_bookdata ORDER BY bookname ASC";

        
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        
        tbl_book.setModel(buildTableModel(rs));
    } catch (Exception e)  {
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_search = new javax.swing.JTextField();
        btn_refresh = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_book = new javax.swing.JTable();
        btn_borrow = new javax.swing.JLabel();
        txt_contactnum = new javax.swing.JTextField();
        txt_borrowname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        date_spinner = new javax.swing.JSpinner();
        jPanel1 = new javax.swing.JPanel();
        lbl_borrowbook = new javax.swing.JLabel();

        setBackground(new java.awt.Color(238, 225, 207));
        setLayout(null);

        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });
        add(txt_search);
        txt_search.setBounds(220, 30, 450, 30);

        btn_refresh.setBackground(new java.awt.Color(138, 189, 220));
        btn_refresh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_refresh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_refresh.setText("Refresh");
        btn_refresh.setOpaque(true);
        btn_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_refreshMouseClicked(evt);
            }
        });
        add(btn_refresh);
        btn_refresh.setBounds(560, 470, 80, 20);

        tbl_book.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Book name", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        tbl_book.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_bookMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_book);
        if (tbl_book.getColumnModel().getColumnCount() > 0) {
            tbl_book.getColumnModel().getColumn(0).setResizable(false);
            tbl_book.getColumnModel().getColumn(1).setResizable(false);
        }

        add(jScrollPane1);
        jScrollPane1.setBounds(220, 80, 450, 380);

        btn_borrow.setBackground(new java.awt.Color(138, 189, 220));
        btn_borrow.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btn_borrow.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_borrow.setText("Borrow Book");
        btn_borrow.setOpaque(true);
        btn_borrow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_borrowMouseClicked(evt);
            }
        });
        add(btn_borrow);
        btn_borrow.setBounds(20, 80, 100, 30);
        add(txt_contactnum);
        txt_contactnum.setBounds(10, 220, 150, 30);
        add(txt_borrowname);
        txt_borrowname.setBounds(10, 160, 150, 30);

        jLabel3.setText("Name:");
        add(jLabel3);
        jLabel3.setBounds(10, 130, 130, 30);

        jLabel4.setText("Contact no.");
        add(jLabel4);
        jLabel4.setBounds(10, 190, 130, 30);

        jLabel5.setText("Date:");
        add(jLabel5);
        jLabel5.setBounds(10, 250, 130, 30);
        add(date_spinner);
        date_spinner.setBounds(10, 280, 150, 30);

        jPanel1.setBackground(new java.awt.Color(238, 225, 207));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "You are borrowing:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel1.setLayout(null);
        jPanel1.add(lbl_borrowbook);
        lbl_borrowbook.setBounds(20, 30, 140, 30);

        add(jPanel1);
        jPanel1.setBounds(10, 320, 190, 160);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_borrowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_borrowMouseClicked
        System.out.println("button works");
    try {
        // Get input values
        String getusername = txt_borrowname.getText();
        String getContactnum = txt_contactnum.getText();
        Date getDate = (Date) date_spinner.getValue();

        // Define date format
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.now();
        String currentDateStr = currentDate.format(dateFormatter);

        // Format the selected date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(getDate);
        System.out.println(formattedDate);
        String getBookName = lbl_borrowbook.getText();

        // Convert the formatted date string to a LocalDate for comparison
        LocalDate borrowDate = LocalDate.parse(formattedDate, dateFormatter);
        long DaysBetween = ChronoUnit.DAYS.between(currentDate, borrowDate);

        // Check if the days between is more than 30
        if (DaysBetween > 30) {
            JOptionPane.showMessageDialog(this, "You can only borrow the book for up to 30 days.");
            return;  // Exit the method if the days exceed 30
        }

        // Check if the book is available
        boolean isAvailable = isBookAvailable(getBookName);

        // Regex for validating 10-digit contact number
        String contactRegex = "^[0-9]{10}$";

        if (getusername.equals("") || getBookName.equals("")) {
            JOptionPane.showMessageDialog(this, "Name is not inputted or a Book is not selected.");
        } else if (!getContactnum.matches(contactRegex)) {
            JOptionPane.showMessageDialog(this, "Invalid Contact Number! Please enter a 10-digit number.");
        } else if (isAvailable) {
            if (DaysBetween < 0) {
                JOptionPane.showMessageDialog(this, "Invalid Date!");
            } else {
                System.out.println("test 1 passed");

                // Disable auto-commit for transaction management
                con.setAutoCommit(false);

                try (PreparedStatement pstInsert = con.prepareStatement("INSERT INTO tbl_borrower (name, contact, date, book) VALUES (?, ?, ?, ?)"); 
                     PreparedStatement pstUpdate = con.prepareStatement("UPDATE tbl_bookdata SET status = ? WHERE bookname = ?")) {

                    // Insert query for adding a new entry to the tbl_borrower table
                    pstInsert.setString(1, getusername);  // Borrower's name
                    pstInsert.setString(2, getContactnum);  // Borrower's contact number
                    pstInsert.setString(3, formattedDate);  // Borrow date
                    pstInsert.setString(4, getBookName);  // Book name
                    pstInsert.executeUpdate();  // Execute the insert query
                    System.out.println("test 2 passed");

                    // Update the status of the borrowed book in tbl_bookdata
                    pstUpdate.setString(1, "Unavailable");  // Set the status to "Unavailable"
                    pstUpdate.setString(2, getBookName);  // Book title
                    pstUpdate.executeUpdate();  // Execute the update query
                    System.out.println("test 3 passed");

                    // Commit the transaction
                    con.commit();

                    // Show success message
                    JOptionPane.showMessageDialog(this, "Book Successfully Borrowed: Total Days: " + DaysBetween);

                    // Reload the book data
                    LoadBooks();

                } catch (SQLException e) {
                    // Rollback if any error occurs during the transaction
                    con.rollback();

                    // Handle exceptions and show error message
                    JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                } finally {
                    // Re-enable auto-commit mode for future queries
                    con.setAutoCommit(true);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Book is not Currently Available");
        }
    } catch (Exception e) {
        // Handle exception for invalid inputs or other unforeseen errors
        e.printStackTrace();
    }
    }//GEN-LAST:event_btn_borrowMouseClicked

    private void tbl_bookMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_bookMouseClicked
     try{
        DefaultTableModel tableinfo = (DefaultTableModel)tbl_book.getModel();
        
        int selectedRowIndex = tbl_book.getSelectedRow();
        
        lbl_borrowbook.setText(tableinfo.getValueAt(selectedRowIndex,0).toString());
        }catch(Exception e){
            
         }
    
    
    }//GEN-LAST:event_tbl_bookMouseClicked

    private void btn_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_refreshMouseClicked
        LoadBooks();
    }//GEN-LAST:event_btn_refreshMouseClicked

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased
       DefaultTableModel table = (DefaultTableModel) tbl_book.getModel();
    String find = txt_search.getText().toUpperCase();
    TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(table);
    tbl_book.setRowSorter(tr);


    tr.setRowFilter(RowFilter.regexFilter("(?i)" + find, 0));  // 0 is the column index for book name
    }//GEN-LAST:event_txt_searchKeyReleased
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_borrow;
    private javax.swing.JLabel btn_refresh;
    private javax.swing.JSpinner date_spinner;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_borrowbook;
    private javax.swing.JTable tbl_book;
    private javax.swing.JTextField txt_borrowname;
    private javax.swing.JTextField txt_contactnum;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables
}
