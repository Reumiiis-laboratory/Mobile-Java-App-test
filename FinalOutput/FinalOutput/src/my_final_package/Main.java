package my_final_package;


import db.connect.dbconnects;
import java.sql.Connection;
public class Main extends javax.swing.JFrame {
    Connection con = null;
    private panel_main mainPanel;
    private panel_borrow borrowPanel;
    private panel_return returnPanel;
    panel_main panman = new panel_main();
    
    public Main() {
        initComponents();
        initializePanels();
        panman.updateBookCounts();
        con = dbconnects.connectDB();
        
        
        
        

    }
    
    private void initializePanels() {
        // Initialize the panels and set bounds
        mainPanel = new panel_main();
        borrowPanel = new panel_borrow();
        returnPanel = new panel_return();

        mainPanel.setBounds(160, 0, 680, 500);
        borrowPanel.setBounds(160, 0, 680, 500);
        returnPanel.setBounds(160, 0, 680, 500);

        // Add all panels to the JFrame
        add(mainPanel);
        add(borrowPanel);
        add(returnPanel);

        // Initially show only the main panel
        mainPanel.setVisible(true);
        borrowPanel.setVisible(false);
        returnPanel.setVisible(false);
    }
    
    private void showPanel(javax.swing.JPanel panelToShow) {
        // Hide all panels
        mainPanel.setVisible(false);
        borrowPanel.setVisible(false);
        returnPanel.setVisible(false);

        // Show the specified panel
        panelToShow.setVisible(true);

        // Refresh the JFrame
        revalidate();
        repaint();
    }
     
     
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_hub = new javax.swing.JPanel();
        btn_logout = new javax.swing.JLabel();
        btn_borrowTab = new javax.swing.JLabel();
        btn_mainTab = new javax.swing.JLabel();
        btn_returnTab = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(835, 500));
        setMinimumSize(new java.awt.Dimension(835, 500));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(835, 500));
        setResizable(false);
        getContentPane().setLayout(null);

        panel_hub.setBackground(new java.awt.Color(77, 113, 166));

        btn_logout.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_logout.setForeground(new java.awt.Color(255, 255, 255));
        btn_logout.setText("Logout");
        btn_logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_logoutMouseClicked(evt);
            }
        });

        btn_borrowTab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_borrowTab.setForeground(new java.awt.Color(255, 255, 255));
        btn_borrowTab.setText("Borrow Book");
        btn_borrowTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_borrowTabMouseClicked(evt);
            }
        });

        btn_mainTab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_mainTab.setForeground(new java.awt.Color(255, 255, 255));
        btn_mainTab.setText("Main Menu");
        btn_mainTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_mainTabMouseClicked(evt);
            }
        });

        btn_returnTab.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_returnTab.setForeground(new java.awt.Color(255, 255, 255));
        btn_returnTab.setText("Return Book");
        btn_returnTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_returnTabMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel_hubLayout = new javax.swing.GroupLayout(panel_hub);
        panel_hub.setLayout(panel_hubLayout);
        panel_hubLayout.setHorizontalGroup(
            panel_hubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hubLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panel_hubLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_mainTab, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panel_hubLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_returnTab, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(panel_hubLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_borrowTab, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel_hubLayout.setVerticalGroup(
            panel_hubLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_hubLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(btn_borrowTab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_returnTab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_mainTab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(274, 274, 274)
                .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(panel_hub);
        panel_hub.setBounds(0, 0, 160, 500);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_borrowTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_borrowTabMouseClicked
         showPanel(borrowPanel);
        System.out.println("test worked");
        borrowPanel.LoadBooks();
        
    }//GEN-LAST:event_btn_borrowTabMouseClicked

    private void btn_mainTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_mainTabMouseClicked
       showPanel(mainPanel);
       panman.updateBookCounts();
       
    }//GEN-LAST:event_btn_mainTabMouseClicked

    private void btn_returnTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_returnTabMouseClicked
       showPanel(returnPanel);
       
    }//GEN-LAST:event_btn_returnTabMouseClicked

    private void btn_logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_logoutMouseClicked
       System.exit(0);
    }//GEN-LAST:event_btn_logoutMouseClicked

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_borrowTab;
    private javax.swing.JLabel btn_logout;
    private javax.swing.JLabel btn_mainTab;
    private javax.swing.JLabel btn_returnTab;
    private javax.swing.JPanel panel_hub;
    // End of variables declaration//GEN-END:variables
}
