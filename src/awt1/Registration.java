package awt1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.*;
import java.util.Random;
import java.sql.*;

class Registration extends JFrame implements ActionListener {
	
    Container container=getContentPane();
    JLabel lblhead = new JLabel("USER REGISTRATION");
    JLabel lblName = new JLabel("NAME");
    JLabel lblAddress = new JLabel("ADDRESS");
    JLabel lblPincode = new JLabel("PINCODE");
    JLabel lblMobile=new JLabel("MOBILE NUMBER");
    JLabel lblBlood=new JLabel("BLOOD GROUP");
    JLabel lblEmail=new JLabel("EMAIL ID");
    JLabel lblPass=new JLabel("CREATE PASSWORD");
    JLabel lblConformPass=new JLabel("CONFORM PASSWORD");
    JTextField txtName = new JTextField();
    JTextField txtAddress= new JTextField();
    JTextField txtPincode = new JTextField();
    JTextField txtMobile = new JTextField();
    JTextField txtBlood = new JTextField();
    JTextField txtEmail=new JTextField();
    JPasswordField txtPass=new JPasswordField();
    JPasswordField txtConformPass=new JPasswordField();
    JButton btnRegister=new JButton("REGISTER");
    JButton btnLog=new JButton("Already a user?LOGIN");



    Registration()
    {
       //Calling methods inside constructor.

    	lblhead.setFont(new Font("Serif",Font.BOLD,20));
        lblhead.setForeground(Color.RED);
    	lblName.setFont(new Font("Serif",Font.BOLD,17));
        lblName.setForeground(Color.BLACK);
        lblAddress.setFont(new Font("Serif",Font.BOLD,17));
        lblAddress.setForeground(Color.BLACK);
        lblPincode.setFont(new Font("Serif",Font.BOLD,17));
        lblPincode.setForeground(Color.BLACK);
        lblMobile.setFont(new Font("Serif",Font.BOLD,17));
        lblMobile.setForeground(Color.BLACK);
        lblBlood.setFont(new Font("Serif",Font.BOLD,17));
        lblBlood.setForeground(Color.BLACK);
        lblEmail.setFont(new Font("Serif",Font.BOLD,17));
        lblEmail.setForeground(Color.BLACK);
        lblPass.setFont(new Font("Serif",Font.BOLD,17));
        lblPass.setForeground(Color.BLACK);
        lblConformPass.setFont(new Font("Serif",Font.BOLD,17));
        lblConformPass.setForeground(Color.BLACK);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setTitle("Registration Form");
        ImageIcon image1=new ImageIcon("C:\\Users\\91877\\Downloads\\person.jpg");
        Image image2=image1.getImage();
        Image image=image2.getScaledInstance(1300, 1000, java.awt.Image.SCALE_SMOOTH); // LAST is used for the quality of image (highest quality)
        JLabel background=new JLabel();
        background.setIcon(new ImageIcon(image));
        background.setBounds(0, 0, 1300, 800);
        getContentPane().add(background);
//        setBounds(500,150,1000,800);
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
	   lblhead.setBounds(55*10,40*2,300,80);
       lblName.setBounds(50*10,100*2,200,30);
       lblAddress.setBounds(50*10,120*2,200,30);
       lblPincode.setBounds(50*10,140*2,200,30);
       lblMobile.setBounds(50*10,160*2,200,30);
       lblBlood.setBounds(50*10,180*2,200,30);
       lblEmail.setBounds(50*10,200*2,200,30);
       lblPass.setBounds(50*10,220*2,200,30);
       lblConformPass.setBounds(50*10,240*2,200,30);
       txtName.setBounds(150*5,100*2,200,30);
       txtAddress.setBounds(150*5,120*2,200,30);
       txtPincode.setBounds(150*5,140*2,200,30);
       txtMobile.setBounds(150*5,160*2,200,30);
       txtBlood.setBounds(150*5,180*2,200,30);
       txtEmail.setBounds(150*5,200*2,200,30);
       txtPass.setBounds(150*5,220*2,200,30);
       txtConformPass.setBounds(150*5,240*2,200,30);
       btnRegister.setBounds(60*11,270*2,100,30);
       btnLog.setBounds(56*11,290*2,200,30);

   }
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
	   container.add(lblhead);
	   container.add(lblName);
	   container.add(lblAddress);
	   container.add(lblPincode);
	   container.add(lblMobile);
	   container.add(lblBlood);
	   container.add(lblEmail);
	   container.add(lblPass);
	   container.add(lblConformPass);
	   container.add(txtName);
	   container.add(txtAddress);
	   container.add(txtPincode);
	   container.add(txtMobile);
	   container.add(txtBlood);
	   container.add(txtEmail);
	   container.add(txtPass);
	   container.add(txtConformPass);
	   container.add(btnRegister);
	   container.add(btnLog);

   }
   
   public void addActionEvent()
   {
       btnRegister.addActionListener(this);
       btnLog.addActionListener(this);
   }
   public void actionPerformed(ActionEvent e)
   {
       if(e.getSource()==btnRegister)
       {
           String name = txtName.getText();
           String address=txtAddress.getText();
           String pincode=txtPincode.getText();
           String mobile=txtMobile.getText();
           String blood=txtBlood.getText();
           String email=txtEmail.getText();
           String password=String.valueOf(txtPass.getPassword());
           String conformPassword=String.valueOf(txtConformPass.getPassword());
           
           if(Validate.validateEmail(email) && Validate.validatePassword(password) && Validate.checkConformPassword(password, conformPassword))
           {
                   
                   JOptionPane.showMessageDialog(null,"Registered Successfully.","",-1);
                   try
                   {
                       System.out.print("inserted");
                       Class.forName("oracle.jdbc.driver.OracleDriver");
                       Connection con= DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "test", "sql");
                       //add columns in the person table
                       String query="insert into Person values(?,?,?,?,?,?,?)";
                       PreparedStatement pStmt=con.prepareStatement(query);
                       pStmt.setString(1,name);
                       pStmt.setString(2,address);
                       pStmt.setString(3,pincode);
                       pStmt.setString(4,mobile);
                       pStmt.setString(5,blood);
                       pStmt.setString(6,email);
                       pStmt.setString(7,password);                       
                       pStmt.executeUpdate();
                       con.close();
                       
                   }
                   catch(Exception exception)
                   {
                       exception.printStackTrace();
                   }
                   
               }
     
                else
               {
                   JOptionPane.showMessageDialog(null,"");
               }
           }
	       if(e.getSource()==btnLog)
	       {
	    	   new Login();
	       }
       }
   
   
    
 
    public static void main(String[] args) {
        new Registration();
          
    }
}