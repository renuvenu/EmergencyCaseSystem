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
class HospitalPage extends JFrame {
	 
    Container container=getContentPane();
    JLabel lblName,lbladd,lblmob,lblemail,lblcon;
    String EmailID,name,address,pincode,mobile;

    HospitalPage(String EmailID)
    {
    	this.EmailID=EmailID;
        System.out.println(EmailID);
    	setLayoutManager();
        setTitle("Details Page");
        setVisible(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        display();
        
    }
   public void setLayoutManager()
   {
	   container.setLayout(null);
   }
   public void display()
   {
    	   System.out.print("hii");
    	   		try {
            	 DatabaseConnection db=new DatabaseConnection();
                 String query="Select HospitalName,Address,Pincode,Mobilenumber from clinic where EmailID=?";
                 PreparedStatement pStmt=db.con.prepareStatement(query);
                 pStmt.setString(1,EmailID);
                 ResultSet rs=pStmt.executeQuery();

                 if(rs.next())
	             {
	              String recepient=EmailID;
	               name=rs.getString("HospitalName");
	               address=rs.getString("Address");
	               pincode=rs.getString("Pincode");
	               mobile=rs.getString("MobileNumber");
	             System.out.println(name);
	             System.out.println(address);
	             lblName=new JLabel(name);
	             container.add(lblName);
	             lblName.setFont(new Font("Serif",Font.BOLD,30));
	             lblName.setForeground(Color.RED);
	             lblName.setBounds(40*10,20*2,300,30);
	         	lblcon=new JLabel("CONTACT US:");
	             container.add(lblcon);
	             lblcon.setFont(new Font("Serif",Font.BOLD,20));
	             lblcon.setForeground(Color.BLUE);
	             lblcon.setBounds(26*10,160*2,200,30);
	             lbladd=new JLabel("Address: "+address);
	             container.add(lbladd);
	             lbladd.setFont(new Font("Serif",Font.BOLD,17));
	             lbladd.setForeground(Color.BLACK);
	             lbladd.setBounds(20*10,180*2,400,30);
	             lblemail=new JLabel("Email us: "+recepient);
	             container.add(lblemail);
	             lblemail.setFont(new Font("Serif",Font.BOLD,17));
	             lblemail.setForeground(Color.BLACK);
	             lblemail.setBounds(20*10,200*2,400,30);
	             lblmob=new JLabel("Call us: "+mobile);
	             container.add(lblmob);
	             lblmob.setFont(new Font("Serif",Font.BOLD,17));
	             lblmob.setForeground(Color.BLACK);
	             lblmob.setBounds(20*10,220*2,400,30);
	             ImageIcon image1=new ImageIcon("C:\\Users\\91877\\Downloads\\20211208_185533.jpg");
	             Image image2=image1.getImage();
	             Image image=image2.getScaledInstance(1300, 1000, java.awt.Image.SCALE_SMOOTH); // LAST is used for the quality of image (highest quality)
	             JLabel background=new JLabel();
	             background.setIcon(new ImageIcon(image));
	             background.setBounds(0, 0, 1300, 800);
	             getContentPane().add(background);
	             
	             
	               }else {
	            	   System.out.println("Not successfull");
	               }
               }
                
               catch (Exception exception) {
            	   
                   System.out.println(exception.toString());
               }     
           
   }
   
}
