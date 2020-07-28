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

public class AdminDashboard extends JFrame {
	
	AdminDashboard(JFrame jfm) {
	setTitle("Customer Portal");
	setBounds(10,10,800,600);
	setLayout(null);
	
	//Admin Panel
	JPanel panel=new JPanel();
	panel.setBounds(10,20,750,50);
	panel.setBackground(Color.DARK_GRAY);
	panel.setLayout(null);
	
	//Label 
	JLabel label=new JLabel("Admin Portal");
	label.setBounds(100,-23,300,100);
	label.setForeground(Color.WHITE);
	label.setFont(new Font("Arial",Font.ITALIC,30));
	panel.add(label);
	
	
	//Logout btn
	JButton logout=new JButton("Log out ");
	logout.setFocusPainted(false);
	logout.addActionListener(new SignIn(this));
	logout.setBounds(600,9,100,35);
	logout.setBackground(Color.DARK_GRAY);
	logout.setForeground(Color.WHITE);
	
	
	
	panel.add(logout);
	add(panel);
	
	
	//Panel 2 welcome panel
	JPanel panel2=new JPanel();
	panel2.setBounds(10,100,280,400);
	panel2.setBackground(Color.DARK_GRAY);
	panel2.setLayout(null);
	
	//label welcome    
	JLabel label2=new JLabel("<html>Welcome to <br/> Admin Portal</html> ");
	label2.setBounds(35,110,300,100);
	label2.setFont(new Font("Arial",Font.ITALIC,30));
	label2.setForeground(Color.WHITE);
	panel2.add(label2);
	add(panel2);
	
	
	
	
	
	//Button
	Font font=new Font("Arial",Font.ITALIC,20);
	//view car
	JButton viewcars=new JButton("View Cars");
	viewcars.setFont(font);
	viewcars.setFocusPainted(false);
	viewcars.setBackground(Color.DARK_GRAY);
	viewcars.setForeground(Color.white);
	viewcars.setBounds(300,100,200,100);	
	viewcars.addActionListener(new Car(this));
	add(viewcars);
	
	//Add car
	JButton Addcar=new JButton("Add Car");
	Addcar.setFont(font);
	Addcar.setFocusPainted(false);
	Addcar.setBackground(Color.DARK_GRAY);
	Addcar.setForeground(Color.white);
	Addcar.setBounds(550,100,200,100);	
	Addcar.addActionListener(new Car(this));
	add(Addcar);
	
	//Delete car
	
	JButton Deletecar=new JButton("Remove Car");
	Deletecar.setFont(font);
	Deletecar.setFocusPainted(false);
	Deletecar.setBounds(300,250,200,100);	
	Deletecar.setBackground(Color.DARK_GRAY);
	Deletecar.setForeground(Color.white);

	Deletecar.addActionListener(new Car(this));
	add(Deletecar);
	
	//Update Car
	JButton Updatecar=new JButton("Update Car");
	Updatecar.setBackground(Color.DARK_GRAY);
	Updatecar.setFocusPainted(false);
	Updatecar.setForeground(Color.white);
	Updatecar.setFont(font);
	Updatecar.setBounds(550,250,200,100);	
	Updatecar.addActionListener(new Car(this));
	add(Updatecar);
	
	//View All Booking
	JButton editBooking=new JButton("Change Booking");
	editBooking.setBackground(Color.DARK_GRAY);
	editBooking.setFocusPainted(false);
	editBooking.setForeground(Color.white);	
	editBooking.setFont(font);
	editBooking.setBounds(300,400,200,100);	
	editBooking.addActionListener(new Car(this));
	add(editBooking);
	
	
	//View Booking
	JButton CancelBooking=new JButton("Cancel Booking");
	CancelBooking.setBackground(Color.DARK_GRAY);
	CancelBooking.setFocusPainted(false);
	CancelBooking.setForeground(Color.white);
	CancelBooking.setFont(font);
	CancelBooking.setBounds(550,400,200,100);	
	CancelBooking.addActionListener(new Car(this));
	add(CancelBooking);
	
	
	
	setVisible(true);
	setResizable(false);
	jfm.setVisible(false);
	
		
		
	}
	

	
	
}





