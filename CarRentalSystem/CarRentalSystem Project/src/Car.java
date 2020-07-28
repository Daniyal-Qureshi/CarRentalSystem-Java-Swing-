import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Cursor;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Car extends JFrame implements ActionListener  {
		JFrame AdminDashboard; 
		String username,Password;
		Car(JFrame jfm,String user,String pass){
			AdminDashboard=jfm;
			username=user;
			Password=pass;
			
			
		}
		
		
		Car(JFrame jfm) {
			 AdminDashboard=jfm;
		 
		 }
		public void actionPerformed(ActionEvent ae) {
		JButton Btn=(JButton)ae.getSource();
		String Btntext=Btn.getText();
		
		
		
		
		
		//View Cars for admin dashboard
		//Available stock for Customer dashboard
		if(Btntext.equals("Available Cars")||Btntext.equals("View Cars")) {
			JButton dashboardButton=new JButton("Dashboard");
			dashboardButton.setBounds(350,30,100,40);
			dashboardButton.setBackground(Color.DARK_GRAY);
			dashboardButton.setForeground(Color.white);
			dashboardButton.setFocusPainted(false);
			
			if(Btntext.equals("Available Cars"))
			dashboardButton.addActionListener(new CustomerDashboardHandler(this,username,Password));
			
			else 
				dashboardButton.addActionListener(new AdminDashboardHandler(this));
			
			
			
			add(dashboardButton);
		
			
			
			
			setBounds(10,10,570,650);
			setTitle(Btntext);
			JLabel label=new JLabel(Btntext);
			label.setBounds(30,-100,300,300);
			label.setFont(new Font("Arial",Font.BOLD,25));
			add(label);
			
			
			
			JTextArea textArea;
			String result="";
			if(Btntext.equals("View Cars"))
				result=new DatabaseConnection().ViewCar(0);
			else {
				result=new DatabaseConnection().ViewCar(1);
				if(!(result.charAt(0)=='\n'))
				ReserveCar(this);
			
			}
			
			if(result==null)
				JOptionPane.showMessageDialog(null,"Can't display Cars rightnow",null,JOptionPane.ERROR_MESSAGE);
			
			else {
			textArea=new JTextArea(result);
			textArea.setEditable(false);
			textArea.setBackground(Color.DARK_GRAY);
			textArea.setForeground(Color.white);
			Font font=new Font("Arial",Font.ITALIC,20);
			textArea.setFont(font);
			
			if(Btntext.equals("View Cars")||(result.charAt(0)=='\n'))
				textArea.setBounds(10,100,540,500);
			
			else 
				textArea.setBounds(10,100,450,500);
			
			
			add(textArea);
			
			}
			
			
		}
		
		
		//Admin Dashboard
		else if(Btntext.equals("Add Car")) 
			InsertCar("Add Car","Add Car Information");
		
		//Admin Dashboard
		else if(Btntext.equals("Remove Car")) {
			
			JButton dashboardButton=new JButton("Dashboard");
			dashboardButton.setBounds(30,300,150,30);
			dashboardButton.setBackground(Color.DARK_GRAY);
			dashboardButton.setForeground(Color.white);
			dashboardButton.setFocusPainted(false);
			dashboardButton.addActionListener(new AdminDashboardHandler(this));
			add(dashboardButton);
			
			
			
			setBounds(10,10,400,400);
			setTitle("Remove Car");
			JLabel label=new JLabel("Remove Car Information");
			label.setBounds(30,-70,300,300);
			label.setFont(new Font("Arial",Font.BOLD,20));
			add(label);
			
			JLabel deletelabel=new JLabel("Enter Car name");
			deletelabel.setBounds(30,100,200,100);
			deletelabel.setFont(new Font("Arial",Font.BOLD,15));
			
			add(deletelabel);
			
			JTextField deleteField=new JTextField();
			deleteField.setBounds(30,200,150,30);
			
			add(deleteField);
			
			JButton deletebtn=new JButton("Submit");
			deletebtn.setBackground(Color.DARK_GRAY);
			deletebtn.setForeground(Color.white);
			deletebtn.setFocusPainted(false);
			deletebtn.setBounds(30,250,150,30);
			deletebtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new DatabaseConnection().Delete(deleteField.getText(),0);
				}
			});
			
			
			add(deletebtn);
			
			
		}
		//Admin Dashboard
		
		else if(Btntext.equals("Update Car")) 
			InsertCar("Update Car","Update Car Information");	
			
		
		
		
		//Admin Dashboard
		else if(Btntext.equals("Change Booking")) 
			ChangeBooking("Change Booking Status","Change Status");
		
		//Admin Dashboard
		
		else if(Btntext.equals("Cancel Booking"))
			ChangeBooking("Cancel Customer Booking  ","Submit");
		
			
		//Customer Dashboard  Rented Cars
		else if(Btntext.equals("Rented Cars"))	
		{	
		
			JButton dashboardButton=new JButton("Dashboard");
			dashboardButton.setBounds(230,40,100,40);
			dashboardButton.setBackground(Color.DARK_GRAY);
			dashboardButton.setForeground(Color.white);
			dashboardButton.setFocusPainted(false);
			dashboardButton.addActionListener(new CustomerDashboardHandler(this,username,Password));
			add(dashboardButton);
			
			
			
			setBounds(10,10,350,500);
			JLabel label=new JLabel("Past Rented Cars");
			label.setBounds(30,-90,300,300);
			label.setFont(new Font("Arial",Font.BOLD,20));
			add(label);
			
			setTitle("Past rented cars");
		
			JTextArea area=new JTextArea();
			area.setBounds(10,100,300,350);
			Font font=new Font("Arial",Font.ITALIC,20);
			area.setFont(font);
			area.setBackground(Color.DARK_GRAY);
			
			area.setForeground(Color.white);
			area.setEditable(false);
			String result=new DatabaseConnection().RentedCars(username);
			
			if(result==null) 
				area.setText(" \n\n Don't have any rented car");
			
			else 
				area.setText(result);
			add(area);	
			
			
			
		}
	
		//Customer Dashboard  Profile
		else if (Btntext.equals("User Profile")) {
			JButton dashboardButton=new JButton("Dashboard");
			dashboardButton.setBounds(200,7,100,40);
			dashboardButton.setBackground(Color.DARK_GRAY);
			dashboardButton.setForeground(Color.white);
			dashboardButton.setFocusPainted(false);
			dashboardButton.addActionListener(new CustomerDashboardHandler(this,username,Password));
			add(dashboardButton);
			
			
			setTitle("User Profile");
			String result=new DatabaseConnection().ViewProfile(username);
			setBounds(10,10,350,400);
			JLabel label=new JLabel("User Profile");
			label.setBounds(20,-70,200,200);
			label.setFont(new Font("Arial",Font.BOLD,25));
			
			add(label);
			
			JTextArea area=new JTextArea(result);
			area.setBounds(10,50,300,300);
			Font font=new Font("Arial",Font.ITALIC,22);
			area.setFont(font);
			area.setBackground(Color.DARK_GRAY);
			
			area.setForeground(Color.white);
			area.setEditable(false);	
			add(area);
		}
		
		else if(Btntext.equals("Edit profile")) {
			
			JButton dashboardButton=new JButton("Dashboard");
			dashboardButton.setBounds(180,20,100,40);
			dashboardButton.setBackground(Color.DARK_GRAY);
			dashboardButton.setForeground(Color.white);
			dashboardButton.setFocusPainted(false);
			dashboardButton.addActionListener(new CustomerDashboardHandler(this,username,Password));
			add(dashboardButton);
			
			
			setTitle("Edit Profile");
			setBounds(10,10,300,500);
			JLabel label=new JLabel("Edit Profile");
			label.setBounds(20,-50,200,200);
			label.setFont(new Font("Arial",Font.BOLD,25));
			add(label);
			
			JLabel fullname=new JLabel("Enter Full Name");
			fullname.setBounds(10,10,200,200);
			add(fullname);
			
			JTextField namefield=new JTextField();
			namefield.setBounds(10,130,150,25);
			add(namefield);
			
			
			JLabel passwordlabel=new JLabel("Enter Password");
			passwordlabel.setBounds(10,100,200,200);
			add(passwordlabel);
			
			JPasswordField passwordfield=new JPasswordField ();
			passwordfield.setBounds(10,220,150,25);
			add(passwordfield);
			
			JLabel repasswordlabel=new JLabel("Re-enter Password ");
			repasswordlabel.setBounds(10,200,200,200);
			add(repasswordlabel);
			
			JPasswordField repasswordfield=new JPasswordField ();
			repasswordfield.setBounds(10,320,150,25);
			add(repasswordfield);
			
			
			JButton submit =new JButton("Update Info");
			submit.setBounds(10,380,150,30);
			submit.setBackground(Color.DARK_GRAY);
			submit.setForeground(Color.WHITE);
			add(submit);
			
			submit.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if(namefield.getText().length()==0&&passwordfield.getPassword().length==0
						&&repasswordfield.getPassword().length==0) {
						JOptionPane.showMessageDialog(null,"Fill Required field",null,JOptionPane.INFORMATION_MESSAGE);
						return;
						}
					
					
						
					String userpassword="",repassword="";
					char passwordArray[] =passwordfield.getPassword();
					char repasswordArray[]=repasswordfield.getPassword();
					
					for(int i=0;i<passwordArray.length;i++)
						userpassword+=passwordArray[i];
					
					
					for(int i=0;i<repasswordArray.length;i++)
						repassword+=repasswordArray[i];
					
					
					if(!userpassword.equals(repassword))
						JOptionPane.showMessageDialog(null,"Incorrect Password","Password mismatch",JOptionPane.ERROR_MESSAGE);
					
					else 
						new DatabaseConnection().UpdateProfile(username, namefield.getText(), userpassword);
					
					
					
				}
			});
			
			
			
			
		}
		
		
		
		
		setLayout(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		AdminDashboard.setVisible(false);

		
		
}
	
		
//==============================Insert and Update method Admin Dashboard 		
public void InsertCar(String title,String Label) {
	
	
	JButton dashboardButton=new JButton("Dashboard");
	dashboardButton.setBounds(10,500,180,30);
	dashboardButton.setBackground(Color.DARK_GRAY);
	dashboardButton.setForeground(Color.white);
	dashboardButton.setFocusPainted(false);
	dashboardButton.addActionListener(new AdminDashboardHandler(this));
	add(dashboardButton);
	
	Font font=new Font("Arial",Font.BOLD,15);
	setBounds(10,10,300,600);
	setTitle(title);
	JLabel label=new JLabel(Label);
	label.setBounds(50,-30,300,200);
	label.setFont(new Font("Arial",Font.BOLD,20));
	add(label);
	
	
	
	//Labels
	JLabel name=new JLabel("Enter Name");
	name.setBounds(10,100,100,100);
	JLabel Rental=new JLabel("Enter Rental Charges");
	Rental.setBounds(10,200,200,100);
	JLabel status=new JLabel("Enter Status");
	status.setBounds(10,300,100,100);
	name.setFont(font);
	status.setFont(font);
	Rental.setFont(font);
	
	add(status);
	add(name);
	add(Rental);
	
	//Fields
	
	JTextField namefield=new JTextField();
	namefield.setBounds(10,170,180,25);
	
	JTextField pricefield=new JTextField();
	pricefield.setBounds(10,270,180,25);
	
	JTextField statusfield=new JTextField();
	statusfield.setBounds(10,370,180,25);

	add(namefield);
	add(pricefield);
	add(statusfield);
	
	//Button  
	JButton submit=new JButton("Submit");
	submit.setBounds(10,430,180,30);
	submit.setBackground(Color.DARK_GRAY);
	submit.setForeground(Color.white);
	submit.setFocusPainted(false);
	
	submit.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(title.equals("Add Car")&&(namefield.getText().length()==0||
				pricefield.getText().length()==0||
				statusfield.getText().length()==0
				))
			JOptionPane.showMessageDialog(null,"Please fill required fields",null,JOptionPane.INFORMATION_MESSAGE);
			
			
			
			else if(title.equals("Update Car")&&namefield.getText().length()==0)
					JOptionPane.showMessageDialog(null,"Enter Car Name",null,JOptionPane.INFORMATION_MESSAGE);	
					
				
			else if(title.equals("Update Car")&&namefield.getText().length()==0&&
					pricefield.getText().length()==0&&
					statusfield.getText().length()==0)
				JOptionPane.showMessageDialog(null,"Please fill required fields",null,JOptionPane.INFORMATION_MESSAGE);	
				
			else if(title.equals("Update Car")&&pricefield.getText().length()==0&&
					statusfield.getText().length()==0)
			JOptionPane.showMessageDialog(null,"Nothing Updated",null,JOptionPane.INFORMATION_MESSAGE);	

			
					
			
			
			
		else {	
		try {
			int price=0;
			if(pricefield.getText().length()!=0)
				price=Integer.parseInt(pricefield.getText());				
		
		
		if(title.equals("Add Car"))
		new DatabaseConnection().InsertCar(namefield.getText(), price, statusfield.getText());			
		
		else {
			
			if(new DatabaseConnection().Search(namefield.getText(),null,2)) {
				new DatabaseConnection().UpdateCar(namefield.getText(), price, statusfield.getText());			
				JOptionPane.showMessageDialog(null,"Car Information updated successfully",null,JOptionPane.INFORMATION_MESSAGE);
				
			}
				
		
			else 
				JOptionPane.showMessageDialog(null,"Please Add Car first ","No Car exist",JOptionPane.INFORMATION_MESSAGE);
			
			
		}
		
		
		}
		catch (Exception ae) {
			JOptionPane.showMessageDialog(null,"Enter price in correct format",null,JOptionPane.ERROR_MESSAGE);
			
		}
			
		}
		
		}
	});
	
	add(submit);
	
	
	
}	



//Customer Methods

	public void ReserveCar(JFrame jfm) {	
		
		setBounds(10,10,480,700);
		//JLabel 
		
		JLabel label=new JLabel("Enter Car Name");
		label.setFont(new Font("Arial",Font.BOLD,15));
		label.setBounds(20,630,200,30);
		jfm.add(label);
		
		JTextField field=new JTextField();
		field.setBounds(150,630,150,25);
		jfm.add(field);
	
		JButton reserve=new JButton("Reserve Car");
		reserve.setBounds(320,630,130,30);
		reserve.setForeground(Color.white);
		reserve.setBackground(Color.DARK_GRAY);
		reserve.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				if(field.getText().length()==0)
					JOptionPane.showMessageDialog(null,"Enter Car Name first" ,null,JOptionPane.INFORMATION_MESSAGE);
				else {
				
				if(new DatabaseConnection().Search(field.getText(), null, 2)) {
					new DatabaseConnection().ReserveCars(username, field.getText());
					JOptionPane.showMessageDialog(null,"Your desired Car has been reserved" ,null,JOptionPane.INFORMATION_MESSAGE);

				
				}
				else 
					JOptionPane.showMessageDialog(null,"Incorrect Car name" ,null,JOptionPane.ERROR_MESSAGE);
				
					
					
				}
				
				
			}
		});
		
		jfm.add(reserve);
		
	
		
	
	}

	
	//==================Change Booking  Admin Dashboard
	//==================Cancel Booking 

	public void ChangeBooking(String title,String buttontext) {
		
		JButton dashboardButton=new JButton("Dashboard");
		dashboardButton.setBounds(450,25,100,40);
		dashboardButton.setBackground(Color.DARK_GRAY);
		dashboardButton.setForeground(Color.white);
		dashboardButton.setFocusPainted(false);
		dashboardButton.addActionListener(new AdminDashboardHandler(this));
		add(dashboardButton);
		
		JTextField customerfield=customerfield=new JTextField();
		JComboBox type=new JComboBox(new String[]{"Rented","Returned"});
		
		
		setBounds(10,10,600,680);
		JLabel label=new JLabel(title);
		label.setBounds(30,-100,500,300);
		label.setFont(new Font("Arial",Font.BOLD,25));
		add(label);
			
		String rs=new DatabaseConnection().ViewBooking();	
		
		JTextArea Area=new JTextArea(rs);
		Area.setEditable(false);
		Area.setBounds(10,100,570,400);
		Area.setBackground(Color.DARK_GRAY);
		Area.setForeground(Color.white);
		Font font=new Font("Arial",Font.ITALIC,20);
		Area.setFont(font);
		add(Area);	
		
		if(rs.charAt(0)=='\n')
			return;
			
		font=new Font("Arial",Font.BOLD,15);
		JLabel carname=new JLabel("Enter Car Name");
		carname.setFont(font);
		carname.setBounds(10,450,200,200);
		add(carname);
		
		JTextField carnamefield=new JTextField();
		carnamefield.setBounds(140,540,150,30);
		add(carnamefield);
		
		
		if(title.equals("Change Booking Status")) {
		JLabel status=new JLabel("Select status");
		status.setBounds(310,450,100,200);
		status.setFont(font);
		add(status);
		
		type.setBounds(420,540,130,25);
		type.setBackground(Color.DARK_GRAY);
		type.setForeground(Color.WHITE);
		add(type);
		}
		
		else {
			JLabel status=new JLabel("Enter Client Name");
			status.setBounds(295,450,200,200);
			status.setFont(font);
			add(status);
			 
			customerfield.setBounds(440,540,130,25);
			add(customerfield);	
			
		}
	
		JButton btn=new JButton(buttontext);
		btn.setFont(font);
		btn.setBounds(10,600,200,40);
		btn.setBackground(Color.DARK_GRAY);
		btn.setForeground(Color.WHITE);
		add(btn);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				
				if(title.equals("Change Booking Status")) {
				if(carnamefield.getText().length()==0)
					JOptionPane.showMessageDialog(null,"Enter Car Name first",null,JOptionPane.INFORMATION_MESSAGE);
				
					
				else 
					new DatabaseConnection().changestatus(carnamefield.getText(),type.getSelectedItem().toString());
				}
		
				
				
				else {
					
					if(carnamefield.getText().length()==0||customerfield.getText().length()==0)
						JOptionPane.showMessageDialog(null,"Please fill required fields",null,JOptionPane.INFORMATION_MESSAGE);
					
					else 
						new DatabaseConnection().CancelBooking(customerfield.getText(),carnamefield.getText());
						
						
						
					
					
				
				}
				
			}
		});

		
		
		

		
	}
	
	
	
}






class AdminDashboardHandler implements ActionListener{
	 JFrame jfm;
	AdminDashboardHandler(JFrame j) {
		jfm=j;
		 
	}
	public void actionPerformed(ActionEvent ae) {
		
		new AdminDashboard(jfm);
	}
	
	
}

class CustomerDashboardHandler implements ActionListener{
	 JFrame jfm;
	 String username;
	 String password;
	 CustomerDashboardHandler(JFrame j,String user,String pass) {
		jfm=j;
		username=user;
		password=pass;
		
		 
	}
	 
	public void actionPerformed(ActionEvent ae) {
		
		new CustomerDashboard(jfm,username,password);
	}
	
	
}




