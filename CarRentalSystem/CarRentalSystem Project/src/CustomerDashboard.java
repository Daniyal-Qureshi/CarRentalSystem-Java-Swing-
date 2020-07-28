import java.awt.Color;
import java.awt.Event;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Panel;

public class CustomerDashboard extends JFrame {
	
	CustomerDashboard(JFrame jfm,String username,String password) {
	setTitle("Customer portal");
	setBounds(10,10,600,650);
	setLayout(null);
	
	//Admin Panel
	JPanel panel=new JPanel();
	panel.setBounds(10,20,550,50);
	panel.setBackground(Color.DARK_GRAY);
	panel.setLayout(null);
	
	//Label 
	JLabel label=new JLabel("Customer Portal");
	label.setBounds(25,-23,300,100);
	label.setForeground(Color.WHITE);
	label.setFont(new Font("Arial",Font.ITALIC,30));
	panel.add(label);
	
	
	//Logout btn
	JButton logout=new JButton("Log out ");
	logout.addActionListener(new SignIn(this));
	logout.setBounds(300,9,100,35);
	logout.setBackground(Color.DARK_GRAY);
	logout.setForeground(Color.WHITE);
	logout.setFocusPainted(false);
	panel.add(logout);
	
	add(panel);
	
	JButton Editprofile=new JButton("Edit profile");
	Editprofile.setBounds(410,9,100,35);
	Editprofile.setBackground(Color.DARK_GRAY);
	Editprofile.setForeground(Color.WHITE);
	Editprofile.setFocusPainted(false);
	Editprofile.addActionListener(new Car(this,username,password));
	panel.add(Editprofile);
	add(panel);
	
	
	//Panel 2 welcome panel
	JPanel panel2=new JPanel();
	panel2.setBounds(10,100,280,400);
	panel2.setBackground(Color.DARK_GRAY);
	panel2.setLayout(null);
	
	//label welcome    
	JLabel label2=new JLabel("<html>Welcome to <br/> Customer Portal</html> ");
	label2.setBounds(20,110,300,100);
	label2.setFont(new Font("Arial",Font.ITALIC,30));
	label2.setForeground(Color.WHITE);
	panel2.add(label2);
	add(panel2);
	
	
	
	
	
	//Button
	Font font=new Font("Arial",Font.ITALIC,20);
	//Available Car
	JButton currentstock=new JButton("Available Cars");
	currentstock.setFont(font);
	currentstock.setBackground(Color.DARK_GRAY);
	currentstock.setForeground(Color.white);
	currentstock.setFocusPainted(false);
	currentstock.setBounds(300,100,200,100);
	
	currentstock.addActionListener(new Car(this,username,password));
	add(currentstock);
	
	
	//Rented Car
	JButton rentedcars=new JButton("Rented Cars");
	rentedcars.setFont(font);
	rentedcars.setBackground(Color.DARK_GRAY);
	rentedcars.setForeground(Color.white);
	rentedcars.setBounds(300,250,200,100);	
	rentedcars.setFocusPainted(false);
	rentedcars.addActionListener(new Car(this,username,password));
	add(rentedcars);
	

	//
	JButton userProfile=new JButton("User Profile");
	userProfile.setFont(font);
	userProfile.setBackground(Color.DARK_GRAY);
	userProfile.setForeground(Color.white);
	userProfile.setBounds(300,400,200,100);	
	userProfile.setFocusPainted(false);
	userProfile.addActionListener(new Car(this,username,password));
	add(userProfile);
	
	
	setVisible(true);
	setResizable(false);
	jfm.setVisible(false);
	
		
		
	}
	

	
	
}





