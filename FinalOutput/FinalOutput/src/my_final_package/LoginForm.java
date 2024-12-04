package my_final_package;

import java.awt.Image;
import javax.swing.*;

public class LoginForm extends JFrame {
    
    private JButton btn_exit;
    private JButton btn_Login;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JLabel lbl;
    private JPasswordField text_pass;
    private JTextField text_user;
    
    

    public LoginForm() {
        initComponents();
        resize();
    }
    public void resize() {
      
       Icon i = lbl.getIcon();
       ImageIcon icon = (ImageIcon) i;
       Image image = icon.getImage().getScaledInstance(lbl.getWidth(),lbl.getHeight(),Image.SCALE_SMOOTH);
       lbl.setIcon(new ImageIcon(image));
               
    }

    private void initComponents() {

        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        text_user = new JTextField();
        text_pass = new JPasswordField();
        btn_exit = new JButton();
        btn_Login = new JButton();
        lbl = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 400));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setForeground(new java.awt.Color(0, 102, 102));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Impact", 0, 18));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Username");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 56, 120, 30);

        jLabel2.setFont(new java.awt.Font("Impact", 0, 18));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Password");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 140, 120, 20);
        
        jPanel1.add(text_user);
        text_user.setBounds(20, 100, 120, 22);
        
        jPanel1.add(text_pass);
        text_pass.setBounds(20, 180, 120, 22);

        btn_exit.setText("Exit");
        btn_exit.addActionListener(e -> System.exit(0));
        jPanel1.add(btn_exit);
        btn_exit.setBounds(220, 250, 120, 30);

        btn_Login.setText("Login");
        btn_Login.addActionListener(evt -> btn_LoginActionPerformed(evt));
        jPanel1.add(btn_Login);
        btn_Login.setBounds(20, 250, 120, 30);

        lbl.setIcon(new ImageIcon(getClass().getResource("/my_final_package/userimage.jpg")));
        jPanel1.add(lbl);
        lbl.setBounds(220, 100, 110, 110);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 400, 400);

        pack();
        setLocationRelativeTo(null);
    }

    private void btn_LoginActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String getusername = text_user.getText();
            String getpassword = new String(text_pass.getPassword());

            if ("12345".equals(getpassword) && "admin".equals(getusername)) {
                 
                JOptionPane.showMessageDialog(this, "Login Successful!");
                 Main obj = new Main();
                 obj.setVisible(true);
                 this.dispose();
                 
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new LoginForm().setVisible(true));
    }
}
