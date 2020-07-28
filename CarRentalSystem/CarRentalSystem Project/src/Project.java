import java.awt.Event;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;



public class Project extends JFrame {
	Project(){
	setTitle("Car Rental System");
	setLayout(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setResizable(false);
	setVisible(true);
	
	//Label Car System
	JLabel label=new JLabel("Car Rental System");
	label.setBounds(250,10,300,100);
	Font font=new Font("Arial",Font.ITALIC,35);
	label.setFont(font);
	label.setForeground(Color.BLACK);
	add(label);
	//Cover ICon
	ImageIcon Coverimg=new ImageIcon("car8.jpg");
	JLabel Cover=new JLabel(Coverimg);
	Cover.setBounds(10,100,1000,560);
	add(Cover);
	//Buttons
	JButton signin =new JButton("Sign in");
	JButton signup =new JButton("Sign up");
	
	signin.setBounds(700,30,100,40);
	signin.setBackground(Color.DARK_GRAY);
	signin.setForeground(Color.WHITE);
	signin.setFocusPainted(false);
	signup.setFocusPainted(false);
	signup.setBounds(810,30,100,40);
	signup.setBackground(Color.DARK_GRAY);
	signup.setForeground(Color.WHITE);

	signin.addActionListener(new SignIn(this));
	signup.addActionListener(new SignUp(this));
	
	
	
	add(signup);
	add(signin);
	
	
	pack();
	setBounds(10,10,1025,700);

	
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Project();
		
	}

}

