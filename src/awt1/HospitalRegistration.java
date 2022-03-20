package awt1;
import java.awt.Color;
import java.awt.Font;
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

class HospitalRegistration extends JFrame implements ActionListener {
	 
    Container container=getContentPane();
    JLabel lblhead = new JLabel("HOSPITAL REGISTRATION");
    JLabel lblName = new JLabel("HOSPITAL NAME");
    JLabel lblAddress = new JLabel("ADDRESS");
    JLabel lblPincode = new JLabel("PINCODE");
    JLabel lblMobile=new JLabel("MOBILE NUMBER");
    JLabel lblEmail=new JLabel("EMAIL ID");
    JLabel lblPass=new JLabel("CREATE PASSWORD");
    JLabel lblConformPass=new JLabel("CONFORM PASSWORD");
    JTextField txtName = new JTextField();
    JTextField txtAddress= new JTextField();
    JTextField txtPincode = new JTextField();
    JTextField txtMobile = new JTextField();
    JTextField txtEmail=new JTextField();
    JPasswordField txtPass=new JPasswordField();
    JPasswordField txtConformPass=new JPasswordField();
    JButton btnRegister=new JButton("REGISTER");
    JButton btnLog=new JButton("Already a user?LOGIN");



    HospitalRegistration()
    {
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
        ImageIcon image1=new ImageIcon("C:\\Users\\91877\\Downloads\\istockphoto-938656872-612x612.jpg");
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
	   lblhead.setBounds(46*10,40*2,300,80);
       lblName.setBounds(34*10,80*2,200,30);
       lblAddress.setBounds(34*10,100*2,200,30);
       lblPincode.setBounds(34*10,120*2,200,30);
       lblMobile.setBounds(34*10,140*2,200,30);
       lblEmail.setBounds(34*10,160*2,200,30);
       lblPass.setBounds(34*10,180*2,200,30);
       lblConformPass.setBounds(34*10,200*2,200,30);
       txtName.setBounds(150*5,80*2,200,30);
       txtAddress.setBounds(150*5,100*2,200,30);
       txtPincode.setBounds(150*5,120*2,200,30);
       txtMobile.setBounds(150*5,140*2,200,30);
       txtEmail.setBounds(150*5,160*2,200,30);
       txtPass.setBounds(150*5,180*2,200,30);
       txtConformPass.setBounds(150*5,200*2,200,30);
       btnRegister.setBounds(55*11,230*2,100,30);
       btnLog.setBounds(52*11,250*2,200,30);

   }
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
	   
	   container.add(lblName);
	   container.add(lblhead);
	   container.add(lblAddress);
	   container.add(lblPincode);
	   container.add(lblMobile);
	   container.add(lblEmail);
	   container.add(lblPass);
	   container.add(lblConformPass);
	   container.add(txtName);
	   container.add(txtAddress);
	   container.add(txtPincode);
	   container.add(txtMobile);
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
                       //add columns in the clinic database
                       String query="insert into clinic values(?,?,?,?,?,?)";
                       PreparedStatement pStmt=con.prepareStatement(query);
                       pStmt.setString(1,name);
                       pStmt.setString(2,address);
                       pStmt.setString(3,pincode);
                       pStmt.setString(4,mobile);
                       pStmt.setString(5,email);
                       pStmt.setString(6,password);                       
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
	    	   new LoginHospital();
	       }
       }
    
 
    public static void main(String[] args) {
        new HospitalRegistration();
          
    }
}
