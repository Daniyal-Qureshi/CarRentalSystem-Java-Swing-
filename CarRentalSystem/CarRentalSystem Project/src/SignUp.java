import java.awt.Color;
import java.awt.Event;
//import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.jar.Attributes.Name;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Font;

public class SignUp extends JFrame implements ActionListener {

		JFrame mainjfm;
		SignUp(JFrame mainjfm){
			this.mainjfm=mainjfm;
			
		}
	
		public void actionPerformed(ActionEvent ae) {
			setResizable(false);
			setTitle("Sign Up");
			setBounds(300,100,300,600);
			setVisible(true);
			setLayout(null);
			//Labels
			JLabel carlabel=new JLabel("Sign Up");
			carlabel.setBounds(100,50,200,40);		
			carlabel.setFont(new Font("Arial",Font.BOLD,30));
			add(carlabel);
			       
			Font font=new Font("Arial",Font.BOLD,13);
			JLabel fullnamelabel=new JLabel("Enter Full Name :");
			fullnamelabel.setBounds(10,150,200,30);
			
			JLabel usernamelabel=new JLabel("Enter UserName :");
			usernamelabel.setBounds(10,200,200,30);
			
			
			JLabel passwordlabel=new JLabel("Enter Password :");
			passwordlabel.setBounds(10,250,200,30);
			
			
			JLabel repasswordlabel=new JLabel("Re-Enter Password :");
			repasswordlabel.setBounds(10,300,200,30);
			
			
			usernamelabel.setFont(font);
			passwordlabel.setFont(font);
			repasswordlabel.setFont(font);
			fullnamelabel.setFont(font);
			
			add(usernamelabel);
			add(passwordlabel);
			add(repasswordlabel);
			add(fullnamelabel);
			
			
			
			
			//TextFields
			JTextField fullnamefield=new JTextField();
			fullnamefield.setBounds(140,150,130,25);
			
		
			

			
			JTextField usernamefield=new JTextField();
			usernamefield.setBounds(140,200,130,25);
		
			JPasswordField passwordfield=new JPasswordField();
			passwordfield.setBounds(140,250,130,25);
			passwordfield.setEchoChar('*');
			
			JPasswordField repasswordfield=new JPasswordField();
			repasswordfield.setBounds(140,300,130,25);
			repasswordfield.setEchoChar('*');
			
			add(fullnamefield);
			add(usernamefield);	
			add(passwordfield);
			add(repasswordfield);
			//Button
		
					
			JButton signupBtn=new JButton("Sign up");
			signupBtn.setBounds(140,350,130,25);
			signupBtn.setBackground(Color.DARK_GRAY);
			signupBtn.setForeground(Color.WHITE);
			signupBtn.setFocusPainted(false);
			signupBtn.addActionListener(
			new SignUpHandler(usernamefield,fullnamefield,passwordfield, repasswordfield));
			add(signupBtn);
			
			JLabel label=new JLabel("Already have an account ? ");
			label.setBounds(10,350,200,200);
			add(label);
			
			
			
			JButton sigin =new JButton("Sign In");
			sigin.setBounds(160,430,100,30);
			sigin.setBackground(Color.DARK_GRAY);
			sigin.setForeground(Color.white);
			sigin.setFocusPainted(false);
			sigin.addActionListener(new SignIn(this));
			add(sigin);
			
			
			
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainjfm.setVisible(false);
			
			
		
	} 

}
class SignUpHandler implements ActionListener{
	
	JPasswordField passworfield,rePasswordfield;
	JTextField fullnamefield,usernamefield;
	
	
	SignUpHandler(JTextField usernamefield,JTextField fullnamefield,JPasswordField passwordfield,JPasswordField rePasswordfield) {
		
		
		this.passworfield=passwordfield;
		this.rePasswordfield=rePasswordfield;
		this.fullnamefield=fullnamefield;
		this.usernamefield=usernamefield;
		
		
		
	}
	
	public void actionPerformed(ActionEvent ae) {	
		
		if(this.passworfield.getPassword().length==0||this.rePasswordfield.getPassword().length==0||
				this.fullnamefield.getText().length()==0||this.usernamefield.getText().length()==0){
			
			JOptionPane.showMessageDialog(null,"Please fill all required fields",null,JOptionPane.INFORMATION_MESSAGE);
			return;
			
		}
		
		
		
		
		String password="",repassword="";
		
		char[] array= this.passworfield.getPassword();
		
		for(int i=0;i<array.length;i++) 
		password+=array[i];
		
		array=this.rePasswordfield.getPassword();
		
		for(int i=0;i<array.length;i++) 
			repassword+=array[i];
		

		if(!repassword.equals(password))
			JOptionPane.showMessageDialog(null,"Enter correct password","Password mismatch",JOptionPane.ERROR_MESSAGE);
		
		
		DatabaseConnection con=new DatabaseConnection();
		
		
		
		if(!con.Search(this.usernamefield.getText(),null,0))
		{
		con=new DatabaseConnection();
		con.insert(this.usernamefield.getText(), this.fullnamefield.getText(), password);
		JOptionPane.showMessageDialog(null,"Account has been created","Congratulations",JOptionPane.INFORMATION_MESSAGE);
		
		}
		
		
		
		else {
			JOptionPane.showMessageDialog(null,"Try other username","Already Exist",JOptionPane.ERROR_MESSAGE);
				
		}
		
		
	}
	
	
	
}