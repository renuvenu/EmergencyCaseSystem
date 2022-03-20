package awt1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class Login extends JFrame implements ActionListener {
    public static String LogInUserName="";
    Container container=getContentPane();
    JLabel lblhead=new JLabel("LOGIN FORM");
    JLabel lblUser=new JLabel("EMAIL ID");
    JLabel lblPass=new JLabel("PASSWORD");
    JTextField txtUser=new JTextField();
    JPasswordField txtPass=new JPasswordField();
    JButton btnLogin=new JButton("LOGIN");
    JCheckBox showPassword=new JCheckBox("Show P"
            + "assword");


    Login()
    {
       //Calling methods inside constructor.
    	lblhead.setFont(new Font("Serif",Font.BOLD,17));
        lblhead.setForeground(Color.RED);
    	lblUser.setFont(new Font("Serif",Font.BOLD,17));
        lblUser.setForeground(Color.BLACK);
        lblPass.setFont(new Font("Serif",Font.BOLD,17));
        lblPass.setForeground(Color.BLACK);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setTitle("Login Form");
        ImageIcon image1=new ImageIcon("C:\\Users\\91877\\Downloads\\person.jpg");
        Image image2=image1.getImage();
        Image image=image2.getScaledInstance(1300, 1000, java.awt.Image.SCALE_SMOOTH); // LAST is used for the quality of image (highest quality)
        JLabel background=new JLabel();
        background.setIcon(new ImageIcon(image));
        background.setBounds(0, 0, 1300, 800);
        getContentPane().add(background);
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        addActionEvent();

    }
   public void setLayoutManager()
   {
       container.setLayout(null);
   }
   public void setLocationAndSize()
   {
       //Setting location and Size of each components using setBounds() method.
	   lblhead.setBounds(54*10,120*2,150,15);
       lblUser.setBounds(50*10,150*2,100,30);
       lblPass.setBounds(50*10,180*2,100,30);
       txtUser.setBounds(150*4,150*2,150,30);
       txtPass.setBounds(150*4,180*2,150,30);
       showPassword.setBounds(150*4,210*2,150,30);
       btnLogin.setBounds(62*9,240*2,100,30);
   }
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
	   container.add(lblhead);
       container.add(lblUser);
       container.add(lblPass);
       container.add(txtUser);
       container.add(txtPass);
       container.add(showPassword);
       container.add(btnLogin);
   }
   
   public void addActionEvent()
   {
       btnLogin.addActionListener(this);
       showPassword.addActionListener(this);
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
               txtPass.setEchoChar((char) 0);
            } else {
                txtPass.setEchoChar('*');
            }
        }
        if(e.getSource()==btnLogin)
        {
                final String email=txtUser.getText();
                final String password=new String(txtPass.getPassword());
                try{
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con= DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "test", "sql");
                    System.out.println("Connection Success");
                    String query="select EmailId,Password from person where EmailID=? and Password=?";
                    PreparedStatement pStmt = con.prepareStatement(query);
                    pStmt.setString(1,email);
                    pStmt.setString(2,password);
                   
                    ResultSet rs=pStmt.executeQuery();
                    if(rs.next())
                    {
                        LogInUserName = rs.getString("EmailID");
                        System.out.print(LogInUserName);
                        JOptionPane.showMessageDialog(null,"Logged in Successfully!!"); 
                        new EmergencyButton(LogInUserName);
                    }
                    else
                    {
                         JOptionPane.showMessageDialog(null,"User not found");
                    }
                    con.close();
                    System.out.println("User: " + LogInUserName);
                }
                catch(Exception exception){
                    JOptionPane.showMessageDialog(null,exception.toString());
            }
        }
    }

    
    public static void main(String args[])
    {
           new Login();
    }
}