package awt1;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Properties;
class EmergencyButton extends JFrame implements ActionListener {
	Container container=getContentPane();
	JLabel lblhead = new JLabel("Emergency Alert");
	Icon icon1 = new ImageIcon("C:\\Users\\91877\\Downloads\\alert3.png");
	JButton button1 = new JButton(icon1);
	static String EmailID = "";
	EmergencyButton(String EmailID)
    {
       //Calling methods inside constructor.
		lblhead.setFont(new Font("Serif",Font.BOLD,20));
        lblhead.setForeground(Color.RED);
		this.EmailID=EmailID;
		System.out.print(EmailID);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setTitle("Emergency");
        ImageIcon image1=new ImageIcon("C:\\Users\\91877\\Downloads\\plain.jpg");
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
	   lblhead.setBounds(55*10,40*2,300,80);
       button1.setBounds(50*10,90*2,230,200);

   }
   public void addComponentsToContainer()
   {
      //Adding each components to the Container
	   container.add(lblhead);
       container.add(button1);

   }
   public void addActionEvent()
   {       
	   button1.addActionListener(this);
   }
   public void actionPerformed(ActionEvent e)
   {
       if(e.getSource()==button1)
       {
    	   		try {
            	 DatabaseConnection db=new DatabaseConnection();
                 String query="Select Name,Address,Pincode,Mobilenumber,Bloodgroup from person where EmailID=?";
                 PreparedStatement pStmt=db.con.prepareStatement(query);
                 
                 pStmt.setString(1,EmailID);
                 ResultSet rs=pStmt.executeQuery();
                 if(rs.next())
	             {
	              String name=rs.getString("Name");
	              String address=rs.getString("Address");
	              String pincode=rs.getString("Pincode");
	              String mobile=rs.getString("MobileNumber");
	              String blood=rs.getString("Bloodgroup");
	              String q="select EmailID from clinic where Pincode=?";
	              PreparedStatement p=db.con.prepareStatement(q);
	              p.setString(1,pincode);
	              ResultSet r=p.executeQuery();
	              if(r.next())
		             {
		              String recepient=r.getString("EmailID");
		              sendMail(recepient,name,address,pincode,mobile,blood);
		             }
	              System.out.println("hello");
	             System.out.println(name);
	             System.out.println(address);
	             
                 JOptionPane.showMessageDialog(null,"Email sent Successfully");
                 System.out.println("sent successfull");
	               }else {
	            	   JOptionPane.showMessageDialog(null,"User Not Found");
	            	   System.out.println("Not successfull");
	               }
               }
                
               catch (Exception exception) {
            	   
                   System.out.println(exception.toString());
               }     
           
       }
   }
   public void sendMail(String recepient,String name,String address,String pincode,String mobile,String blood)
   {
           final String adminMail="19eucs115@skcet.ac.in";
           final String adminPassword="unblock02";
           
           Properties property = new Properties();
           property.put("mail.smtp.auth", "true");
           property.put("mail.smtp.starttls.enable", "true");
           property.put("mail.smtp.host", "smtp.googlemail.com");
           property.put("mail.smtp.port", "587");
           
           Session session = Session.getDefaultInstance(property, new Authenticator() {
                   @Override         
                   protected PasswordAuthentication getPasswordAuthentication(){
                       return new PasswordAuthentication(adminMail,adminPassword);
                   }
           });
          try 
          {
               Message message = new MimeMessage(session);
               message.setFrom(new InternetAddress(adminMail));
               message.setRecipient(Message.RecipientType.TO,new InternetAddress(recepient));
               message.setSubject("Emergency Alert");
               String content = "<h3>Name: "+ name +",</h3>"
                     + "<h3>Address: "+ address +",</h3>"
                     + "<h3>Pincode: "+ pincode +",</h3>"
                     + "<h3>Mobile Number: "+ mobile +",</h3>"
                     +"<h3>Blood Group: "+ blood +",</h3>";
               message.setContent(content,"text/html");
               
               Transport.send(message);
               System.out.println("mail Sent Successfully");
               
           }
          catch (Exception e) {
              e.printStackTrace();
          }
   }

}
