/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package my_final_package;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author emman
 */
public class testSplashscreen extends javax.swing.JFrame {

    /**
     * Creates new form testSplashscreen
     */
    public testSplashscreen() {
        initComponents();
    }
    
    public void imagescale3() {
        Icon i = logo.getIcon();
        ImageIcon icon = (ImageIcon) i;
        Image image = icon.getImage().getScaledInstance(logo.getWidth(), logo.getHeight(), java.awt.Image.SCALE_SMOOTH);
       logo.setIcon(new ImageIcon(image));
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        logo = new javax.swing.JLabel();
        loadingbar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(472, 491));
        setMinimumSize(new java.awt.Dimension(472, 491));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(472, 491));
        getContentPane().setLayout(null);

        jPanel1.setLayout(null);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/my_final_package/lamaw.gif"))); // NOI18N
        jPanel1.add(logo);
        logo.setBounds(0, 0, 470, 470);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 470, 470);
        getContentPane().add(loadingbar);
        loadingbar.setBounds(0, 470, 470, 20);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

   
    public static void main(String args[]) {
       
       testSplashscreen ls = new testSplashscreen();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ls.setVisible(true);
            }
        });
        try{
            for(int i = 0; i<=100; i++)  {
                Thread.sleep(50);
                ls.loadingbar.setValue(i);
                
            }
                    
        }catch(Exception e){
       
        }
       LoginForm log = new LoginForm();
    
       log.setVisible(true);
       ls.dispose();
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar loadingbar;
    private javax.swing.JLabel logo;
    // End of variables declaration//GEN-END:variables
}
