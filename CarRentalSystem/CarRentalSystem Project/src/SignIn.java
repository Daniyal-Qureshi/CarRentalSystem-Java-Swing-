import java.awt.Color;
import java.awt.Event;
//import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;




public class SignIn extends JFrame implements ActionListener  {
	JComboBox type;
	JFrame mainjfm;
	SignIn(JFrame mainjfm){
		this.mainjfm=mainjfm;		
	}

	
	public void actionPerformed(ActionEvent ae) {
		setResizable(false);
		setTitle("Sign in");
		setBounds(300,100,300,600);
		setVisible(true);
		setLayout(null);
		//Labels
		Font font=new Font("Arial",Font.BOLD,13);
		JLabel carlabel=new JLabel("Sign In");
		carlabel.setBounds(100,50,100,40);		
		carlabel.setFont(new Font("Arial",Font.BOLD,30));
		add(carlabel);
		
	
		JLabel namelabel=new JLabel("Enter Username :");
		namelabel.setBounds(10,150,200,30);
		namelabel.setFont(font);

		JLabel passwordlabel=new JLabel("Enter Password :");
		passwordlabel.setBounds(10,210,200,30);
		passwordlabel.setFont(font);
		
		JLabel typelabel=new JLabel("Account type :");
		typelabel.setBounds(10,270,200,30);
		typelabel.setFont(font);
		
		add(namelabel);
		add(passwordlabel);
		add(typelabel);
		
		
		
		
		//TextFields
		
		JTextField namefield=new JTextField();
		namefield.setBounds(120,150,130,25);
		JPasswordField passwordfield=new JPasswordField();
		passwordfield.setBounds(120,210,130,25);
		passwordfield.setEchoChar('*');
		add(namefield);
		add(passwordfield);	
		
		//Combo for account type
		
		type=new JComboBox(new String[]{"Admin","Customer"});
		type.setBounds(120,270,130,25);
		type.setBackground(Color.DARK_GRAY);
		type.setForeground(Color.WHITE);

		add(type);
		
		
		
		
		//Button
		
		JButton signinBtn=new JButton("Sign In");
		signinBtn.setBounds(120,320,130,25);
		signinBtn.addActionListener(new SignInHandler(this,namefield,passwordfield,type));
		signinBtn.setBackground(Color.DARK_GRAY);
		signinBtn.setForeground(Color.WHITE);
		signinBtn.setFocusPainted(false);
		
		add(signinBtn);			
		
		
		JLabel label=new JLabel("Don't have an account ? ");
		label.setBounds(10,350,200,200);
		add(label);
		
		
		
		JButton signup =new JButton("Sign up");
		signup.setBounds(160,430,100,30);
		signup.setBackground(Color.DARK_GRAY);
		signup.setForeground(Color.white);
		signup.setFocusPainted(false);
		signup.addActionListener(new SignUp(this));
		add(signup);
		
		
		mainjfm.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		
	}
	


}

class SignInHandler implements ActionListener{
	JComboBox type;
	JTextField username;
	JPasswordField password;
	JFrame jfm;
	SignInHandler(JFrame jfm,JTextField username,JPasswordField password,JComboBox type) {
		this.type=type;
		this.username=username;
		this.password=password;
		this.jfm=jfm;
	
	}
	
	
	public void actionPerformed(ActionEvent ae) {
		char[] array=this.password.getPassword();
		String Password="";
		for(int i=0;i<array.length;i++)
			Password+=array[i];
		
		
		if(this.type.getSelectedItem().toString().equals("Admin"))	{	
			if(this.username.getText().equals("Admin")&&Password.equals("1234"))
				new AdminDashboard(jfm);
		
			
			else 
				JOptionPane.showMessageDialog(null,"Incorrect username or password",null,JOptionPane.ERROR_MESSAGE);
			
			
		}
		else {
		DatabaseConnection db=new DatabaseConnection();
		if(db.Search(username.getText(),Password,1))
			new CustomerDashboard(jfm,username.getText(),Password);
			
		
		else 
			JOptionPane.showMessageDialog(null,"Incorrect username or password",null,JOptionPane.ERROR_MESSAGE);
			
		
		
		}
		
		
	
	}
	
	
}
