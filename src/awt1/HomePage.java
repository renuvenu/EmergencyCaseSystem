package awt1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame implements ActionListener  {
	Container container=getContentPane();
	JLabel lblName1 = new JLabel("HOSPITAL");
	Icon icon1 = new ImageIcon("C:\\Users\\91877\\Downloads\\hospital.png");
	JButton button1 = new JButton(icon1);
	JLabel lblName2 = new JLabel("USER");
	Icon icon2 = new ImageIcon("C:\\Users\\91877\\Downloads\\user.png");
	JButton button2 = new JButton(icon2);
	JLabel lblhead = new JLabel("EMERGENCY CASE SYSTEM");
    
    HomePage()
    {
       //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setTitle("Home Page");
        ImageIcon image1=new ImageIcon("C:\\Users\\91877\\Downloads\\plain.jpg");
        Image image2=image1.getImage();
        Image image=image2.getScaledInstance(1300, 1000, java.awt.Image.SCALE_SMOOTH); // LAST is used for the quality of image (highest quality)
        JLabel background=new JLabel();
        background.setIcon(new ImageIcon(image));
        background.setBounds(0, 0, 1300, 800);
        getContentPane().add(background);
        setVisible(true);
        lblName1.setFont(new Font("Serif",Font.BOLD,20));
        lblName1.setForeground(Color.BLACK);
        lblName2.setFont(new Font("Serif",Font.BOLD,20));
        lblName2.setForeground(Color.BLACK);
        lblhead.setFont(new Font("Serif",Font.BOLD,28));
        lblhead.setForeground(Color.BLUE);
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
	   lblhead.setBounds(50*10,40*2,500,30);
	   lblName1.setBounds(30*10,90*2,200,30);
	   button1.setBounds(20*10,115*2,300,200);
	   lblName2.setBounds(91*10,90*2,200,30);
	   button2.setBounds(80*10,115*2,300,200);
   }
   public void addComponentsToContainer()
   {
	   container.add(lblhead);
	   container.add(button1);
	   container.add(lblName1);
	   container.add(button2);
	   container.add(lblName2);
   }
    public void addActionEvent()
    {
        button1.addActionListener(this);
        button2.addActionListener(this);
        
    }
    public void actionPerformed(ActionEvent e)
    {
    	if(e.getSource()==button1)
        {
    		new HospitalRegistration();
        }
    	if(e.getSource()==button2)
        {
    		new Registration();
        }
    }
    public static void main(String[] args) {
        new HomePage();
          
    }
}
