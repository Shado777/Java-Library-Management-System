package librarymanagementsystem;

import java.awt.*;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class LibLogin extends JFrame{
    //attributes
    private JPanel welcomePanel,credentialsPanel,buttonPanel;
    private Box credentailBox1, credentailBox2, credentialVertical, forgotBox, buttonBox;
    private JLabel welcomeLbl, userLbl, passwordLbl;
    private JTextField userTxt;
    private JPasswordField passTxt;
    private JButton loginBtn,exitBtn,forgotBtn;
    
    //constructor
    public LibLogin() {
       //JFrame
        this.setTitle("Login");
        this.setSize(800, 380);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        //JPanels
        welcomePanel = new JPanel();
        credentialsPanel = new JPanel();
        buttonPanel = new JPanel();
        
        //JLabels
        welcomeLbl = new JLabel("Please Login to the Library System here");
        welcomeLbl.setFont(new Font("Arial",Font.BOLD,25)); 
        
        userLbl = new JLabel("Username: ");
        userLbl.setFont(new Font("Arial",Font.BOLD,25)); 
        
        passwordLbl = new JLabel("Password: ");
        passwordLbl.setFont(new Font("Arial",Font.BOLD,25)); 
        
        //JFields
        userTxt = new JTextField(5);
        userTxt.setFont(new Font("Arial",Font.BOLD,25));
        
        passTxt = new JPasswordField(5);
        passTxt.setFont(new Font("Arial",Font.BOLD,25)); 
        
        //JButtons
        loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Arial",Font.BOLD,25));
        loginBtn.setBackground(Color.green);
        loginBtn.addActionListener(new loginBtn()); 
        
        exitBtn = new JButton("Exit ");
        exitBtn.setFont(new Font("Arial",Font.BOLD,25)); 
        exitBtn.setBackground(Color.red);
        exitBtn.addActionListener(new exitBtn());
        
        forgotBtn = new JButton("I forgot my password");
        forgotBtn.setFont(new Font("Arial",Font.BOLD,25)); 
        forgotBtn.setBackground(Color.LIGHT_GRAY); 
        forgotBtn.addActionListener(new forgotBtn()); 
        
        //Boxes
        //Horisontal boxes
        credentailBox1 = Box.createHorizontalBox();
        credentailBox1.add(Box.createHorizontalStrut(50));
        credentailBox1.add(userLbl);
        credentailBox1.add(Box.createHorizontalStrut(20));
        credentailBox1.add(userTxt);
        credentailBox1.add(Box.createHorizontalStrut(80));
        
        credentailBox2 = Box.createHorizontalBox();
        credentailBox2.add(Box.createHorizontalStrut(50));
        credentailBox2.add(passwordLbl);
        credentailBox2.add(Box.createHorizontalStrut(20));
        credentailBox2.add(passTxt);
        credentailBox2.add(Box.createHorizontalStrut(80));
        
        forgotBox = Box.createHorizontalBox();
        forgotBox.add(Box.createHorizontalStrut(50));
        forgotBox.add(forgotBtn);
        forgotBox.add(Box.createHorizontalStrut(50));
        
        buttonBox = Box.createHorizontalBox();
        buttonBox.add(Box.createHorizontalStrut(10));
        buttonBox.add(loginBtn);
        buttonBox.add(Box.createHorizontalStrut(25));
        buttonBox.add(exitBtn);
        buttonBox.add(Box.createHorizontalStrut(10));
        
        //Vertical boxes
        credentialVertical = Box.createVerticalBox();
        credentialVertical.add(Box.createVerticalStrut(50));
        credentialVertical.add(credentailBox1);
        credentialVertical.add(Box.createVerticalStrut(20));
        credentialVertical.add(credentailBox2);
        credentialVertical.add(Box.createVerticalStrut(40));
        credentialVertical.add(forgotBox);
        credentialVertical.add(Box.createVerticalStrut(50));
        
        //Adding to Panels
        welcomePanel.add(welcomeLbl);
        credentialsPanel.add(credentialVertical);
        buttonPanel.add(buttonBox);
        
        //Adding to JFrame
        this.add(welcomePanel, BorderLayout.NORTH);
        this.add(credentialVertical, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        
        this.setVisible(true);
    }
    //methods and eventhandlers
    
    //Exit button
    class exitBtn implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            UIManager.put("OptionPane.messageFont", new Font("Arial",Font.PLAIN,20));
            UIManager.put("OptionPane.buttonFont", new Font("Arial",Font.PLAIN,20));
            UIManager.put("Button.background", Color.LIGHT_GRAY);
            int option = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit the program?","Exit!",JOptionPane.YES_NO_OPTION);
            
            if(option == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
             
        }
    
    }
    
    //Forgot Password
    class forgotBtn implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            UIManager.put("OptionPane.messageFont", new Font("Arial", Font.PLAIN,20));
            UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN,20));
            UIManager.put("Button.background", Color.LIGHT_GRAY);
            int option = JOptionPane.showConfirmDialog(null,"You are about to be redirected to the forgot your password page!","Alert!",JOptionPane.OK_CANCEL_OPTION);
            
            if(option == JOptionPane.OK_OPTION) {
                dispose();
                libForgotPassword forgot = new libForgotPassword();
                forgot.setVisible(true);
            }
        }
    
    }
    
    //login button
    class loginBtn implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            //getting results from login form
            char[] passResult = passTxt.getPassword();
            String fPass = new String(passResult);
            String fUser = userTxt.getText();
            
            //attributes for MySql
            String Hostname = "localhost";
            String Database = "library";
            String Username = "root";
            String Password = "Captain@1945!";
            String dbUrl = "jdbc:mysql://"+Hostname+":3308/"+Database;
            
            //Connection to database to get login details
            //Note if true will be able to log in if false warning message
            try {
                Connection dbCon = DriverManager.getConnection(dbUrl,Username,Password);
                Statement dbStat1 = dbCon.createStatement();
                String Query1 = "SELECT * FROM testemp WHERE empUsername='"+fUser+"'and empPassword='"+fPass+"'";
                ResultSet rs1 = dbStat1.executeQuery(Query1);
                if(rs1.next()) {
                    UIManager.put("OptionPane.messageFont", new Font("Arial",Font.BOLD,25));
                    JOptionPane.showMessageDialog(null, "Your login credentials are correct\n\t\tRedirceting to Main menu", "Alert!",JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    libMainMenu mainMenu = new libMainMenu();
                    mainMenu.setVisible(true); 
                }
                else{
                    UIManager.put("OptionPane.messageFont", new Font("Arial",Font.BOLD,25));
                    UIManager.put("OptionPane.buttonFont", new Font("Arial",Font.BOLD,25));
                    JOptionPane.showMessageDialog(null, "Your username or password was incorrect!\n\t\tPlease Try again!", "Alert!",JOptionPane.ERROR_MESSAGE);
                    userTxt.setText(null); 
                    passTxt.setText(null); 
                }
            } 
            catch (SQLException ex) {
                Logger.getLogger(LibLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
