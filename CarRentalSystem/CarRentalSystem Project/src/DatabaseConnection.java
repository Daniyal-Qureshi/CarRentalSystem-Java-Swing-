
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JOptionPane;

import com.mysql.cj.exceptions.RSAException;

import java.sql.*;


public class DatabaseConnection {

	private static Connection connection;
	private static Statement statement=null;
	private static ResultSet resultSet=null;
	private static String username="root";
	private static String password="root";
	
	 DatabaseConnection() {
	
		 try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection=DriverManager.getConnection
						("jdbc:mysql://localhost:3306/CarRentalSystem",username,password);		
				
				
			}
			catch(Exception e) {
				
				e.printStackTrace();
				System.out.println("Exception");
			}
			
		}
		
	//=====================================Insert user 
	 public  void insert(String username,String Fullname,String password)	{
		
		
		 String query="INSERT INTO CarRentalSystem.customer (username ,FullName ,Userpassword)"+"VALUES(?,?,?);";

		try {	
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setString(1,username);
			ps.setString(2,Fullname);
			ps.setString(3,password);
			
			
			ps.execute();
			
			connection.close();
			ps.close();
			
		}
		catch(SQLException e) {
		System.out.println("Record not add"+e);
			
		}
	 
	 }

	 
//==============================Search
public boolean Search(String username,String Password,int querynumber){
	
	String[] query= {
	"Select * from CarRentalSystem.customer where username='"+username+"';"
	,"Select * from CarRentalSystem.customer where username='"+username+"'and Userpassword='"+Password+"';"	
   ,"Select * from CarRentalSystem.car where name='"+username+"';"
   
	};
	

	if(querynumber==3) {
	int carID=Integer.parseInt(username);	
	query[0]="Select *from CarRentalSystem.booking where CarID='"+carID+"'";	
	querynumber=0;	
	}
		
		 
		 
	
	try {	
		 statement =connection.createStatement();
		 resultSet=statement.executeQuery(query[querynumber]);
		
		
		 
		if(resultSet.next())
			return true;
	
		else
			return false;
		
		
	}
	catch(SQLException e) {
	System.out.println("Search  Exception"+e);
	return false;
	}
	
	finally {
		try {
		connection.close();
		statement.close();
		resultSet.close();
		
		}
		catch (SQLException e) {
			System.out.println("CLose exception");
		}
		
	}
	
	}




	public String ViewCar(int querynumber) {
		String query[]= {
				"Select * from CarRentalSystem.car",
				"Select * from CarRentalSystem.car where status='Returned'"					
		};
		String result[]= {
				
				" ID\tName\tRental charges\t  Status\n\n",
				" Name\tRental charges\t  Status\n\n"
		};
		
		
		try {	
			 statement =connection.createStatement();
			 resultSet=statement.executeQuery(query[querynumber]);
			 
			 
			 
			 if(!resultSet.next())
				return result[querynumber]="\n \n\tSorry! No  Car Available at the moment";
				
			 resultSet.previous();
			while(querynumber==0&&resultSet.next()) {
				
				result[querynumber]+=" "+resultSet.getLong(1)+"\t"+resultSet.getString(2)+"\t"
						+resultSet.getLong(3)+"\t "+resultSet.getString(4)+"\n";
				
				
			}
			
			while(resultSet.next()) {
				result[querynumber]+=" "+resultSet.getString(2)+"\t"
						+resultSet.getLong(3)+"\t "+resultSet.getString(4)+"\n";	
			}
				
			
				
			
		return result[querynumber];
		
		}
		catch(SQLException e) {
		System.out.println("Search  Exception"+e);
		return null;
		}
		
		finally {
			try {
			connection.close();
			statement.close();
			resultSet.close();
			
			}
			catch (SQLException e) {
				System.out.println("CLose exception");
			}
			
		}
		
		
		
		
	}
	
	
	
	public void InsertCar(String name,int price,String status) {
		
		
		int ID=new Random().nextInt();
		while(ID<=0)		
			ID=new Random().nextInt();
		
		
		String query="Select CarID from CarRentalSystem.car";
		try {	
			 statement =connection.createStatement();
			 resultSet=statement.executeQuery(query);
			int count=0;
			while(resultSet.next()) 
				count+=1;
			
			resultSet.close();
			resultSet=statement.executeQuery(query);
			
			int[] array=new int[count];
		
			
			for(int j=0;resultSet.next();j++) 
				array[j]=resultSet.getInt(1);
			
			boolean flag=false;
			while(true) {
			for (int i = 0; i < array.length; i++) { 
				if(array[i]==ID)
						flag=true;				
			}
			
			if(flag) {
					ID=new Random().nextInt();
					flag=false;
				}
				else 
					break;
						
			}
			
			query="Insert into CarRentalSystem.car values(?,?,?,?); ";
		
			
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1,ID);
			ps.setString(2,name);
			ps.setInt(3,price);
			ps.setString(4, status);
			
			ps.execute();
			ps.close();
			
		
		}
		catch(SQLException e) {
		System.out.println("Search  Exception"+e);
	
		}
		
		finally {
				try {
				connection.close();
				statement.close();
				resultSet.close();
				
				}
				catch (SQLException e) {
					System.out.println("CLose exception");
				}
			
		}
		JOptionPane.showMessageDialog(null,"Car Information Inserted Successfully",null,JOptionPane.INFORMATION_MESSAGE);
		
		
	}
	
	
	
	
	public void Delete(String name,int ID) {
		try {
		statement =connection.createStatement();
		String[] query= {
			"Delete from CarRentalSystem.car where name='"+name+"';",
			"Delete from CarRentalSystem.booking where username='"+name+"' and CarID= '"+ID+"';",
			
			
		};
		int r=100;
		if(ID==0)
			r=statement.executeUpdate(query[0]);

		
		else
			r=statement.executeUpdate(query[1]);
			
		
		if(r!=1) 
			JOptionPane.showMessageDialog(null,"request failed",null,JOptionPane.ERROR_MESSAGE);
	
		
		
		if(r==1&&ID==0)
			JOptionPane.showMessageDialog(null,"Car Information removed Successfully",null,JOptionPane.INFORMATION_MESSAGE);
		
		else if(r==1) {
			JOptionPane.showMessageDialog(null,"Booking has been cancelled successfully",null,JOptionPane.INFORMATION_MESSAGE);		
			
		}
		
		
		 
		
		
		statement.close();
		connection.close();
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Can't remove car at the moment",null,JOptionPane.ERROR_MESSAGE);
				
			
			
		}
		
		
	
		
	}
	
	
//=======================================Update	
	public void UpdateCar(String name,int price,String status) {
		
		String query;
		if(price==0)
			query="Update CarRentalSystem.car set status='"+status+"' where name='"+name+"';";
		
		else if(status.length()==0)
			query="Update CarRentalSystem.car set rentprice='"+price+"' where name='"+name+"';";
		
		else 
			query="Update CarRentalSystem.car set rentprice='"+price+"',status='"+status+"' where name='"+name+"';";
			
			
		
		
		
		
		try {	
			 statement =connection.createStatement();
			 int affected=statement.executeUpdate(query);
			if(affected!=1)
				throw new Exception();
			
			
			connection.close();
			statement.close();
			 
			 
		}
		catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Can't update information at the moment",null,JOptionPane.ERROR_MESSAGE);
			
		}

		
	}
	

	public void ReserveCars(String username,String carname) {

		
		new DatabaseConnection().UpdateCar(carname, 0, "Reserved");
		
		new DatabaseConnection();
		
		String query="Select CarID from CarRentalSystem.car where name='"+carname+"'";
		int CarID=0;
		try {
		statement =connection.createStatement();
		 resultSet=statement.executeQuery(query);
		resultSet.next();
		 CarID=resultSet.getInt(1);
		
		 statement.close();
		resultSet.close();
		connection.close();
		
		}
		
		catch (Exception e) {
		
			System.out.println("Reserve Car Error");
			e.getStackTrace();
		}
		
		
		
		
		query ="Insert into CarRentalSystem.booking values(?,?)";
		 

			try {
			new DatabaseConnection();
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1,CarID);
			ps.setString(2,username);
			
			ps.execute();
			ps.close();
			connection.close();
		
			}
			
			catch (Exception e) {
				
				System.out.println("Reserve Car Booking table error");
				e.getStackTrace();
			}
		
			
	}
	
	
	


	public String ViewBooking(){
		
	String query="SELECT FullName,name,rentprice,status FROM CarRentalSystem.car "
			+"INNER JOIN CarRentalSystem.booking USING(CarID) INNER JOIN "
			 +"CarRentalSystem.customer USING(username)";
	
	
	String result=" Customer\tCar Name\tRentalprice\tStatus\n\n ";
	try {
	statement =connection.createStatement();
	resultSet=statement.executeQuery(query);
	
	if(!resultSet.next())
		return result="\n\n\n  No booking is available at the moment";
	
	resultSet.previous();	
	while (resultSet.next()) {
		result+=" "+resultSet.getString(1)+"\t"+resultSet.getString(2)+"\t"+
					resultSet.getLong(3)+"\t"+resultSet.getString(4)+"\n";	
		
	}

	
	return result;
	
	}
	catch (Exception e) {
		System.out.println("View Booking exception");
		e.printStackTrace();
		return null;
	}
		
		
	}
	
	void changestatus(String carname,String status) {
	String query="Select CarID,name,status from CarRentalSystem.car where name='"+carname+"'";
	try {
		int CarID=0;
		String carName="";
		String carstatus="";
		statement =connection.createStatement();
		resultSet=statement.executeQuery(query);
		
		if(!resultSet.next()) {
			 JOptionPane.showMessageDialog(null,"Enter valid Car Name",null,JOptionPane.ERROR_MESSAGE);
			 return;
		}
		
		
		else {
		 CarID=resultSet.getInt(1);
		 carName=resultSet.getString(2);
		 carstatus=resultSet.getString(3);
		}
		
		statement.close();
		resultSet.close();
		connection.close();
		
		
		String carID=""+CarID;
		if(new DatabaseConnection().Search(carID,null,3)) {
			if(carstatus.equals("Reserved")&&status.equals("Rented")
				||carstatus.equals("Rented")&&status.equals("Returned")) { 
			
				new DatabaseConnection().UpdateCar(carName,0, status);
				JOptionPane.showMessageDialog(null,"Status changed successfully",null,JOptionPane.INFORMATION_MESSAGE);
			
			}
			else
				JOptionPane.showMessageDialog(null,"Select valid status",null,JOptionPane.INFORMATION_MESSAGE);
				
			
		
		}		
				
		else 
			JOptionPane.showMessageDialog(null,"Incorrect Car ",null,JOptionPane.ERROR_MESSAGE);
		
		
		
		
		
		
		
		
		
		
	}	
		
	catch (Exception e) {
		System.out.println("Change Status Method Exception");
	}
		
		
		
	}
	
	
	
	public void CancelBooking(String customername,String carname) 
	{
	
		try {
		connection.close();
		}
		catch (Exception e) {
			
		}
		
		String CarID=new DatabaseConnection().getColumn(carname, 0);
		String  username=new DatabaseConnection().getColumn(customername, 1);
		if(CarID==null)
			JOptionPane.showMessageDialog(null,"Incorrect Car Name",null,JOptionPane.ERROR_MESSAGE);
		else if(username==null)
			JOptionPane.showMessageDialog(null,"Incorrect Customer Name",null,JOptionPane.ERROR_MESSAGE);
		
		
		
		else {
			
			new DatabaseConnection().Delete(username, Integer.parseInt(CarID));
			new DatabaseConnection().UpdateCar(carname, 0,"Returned");
			
		}
		
	
		
		
	}
	
	
	
	public String getColumn(String column,int queryNumber) {
		String query[]= {
		"Select CarID from CarRentalSystem.car where name ='"+column+"'",
		"Select username from CarRentalSystem.customer where FullName ='"+column+"'",
		};
		
		
		
		try {
		
			statement=connection.createStatement();
			resultSet=statement.executeQuery(query[queryNumber]);
			resultSet.next();
			
			if(queryNumber==0)
			return ""+resultSet.getInt(1);
			
			else 
				return resultSet.getString(1);
			
		
			
		}
		
		catch (Exception e) {
			System.out.println("Exception in getting column");
			return null;
			
		}
		finally {
			
			try {
				
				connection.close();
				statement.close();
				resultSet.close();
				
				
				
			}
			catch (Exception e) {
				
			}
		}
		
	}
	
	public String RentedCars(String username) {
		String query="Select CarID from CarRentalSystem.booking where username ='"+username+"'";
		
		try {
			int[] CarID= {};
			int count=0;
			statement=connection.createStatement();
			resultSet=statement.executeQuery(query);
			
			if(!resultSet.next())
				return null;
			
			resultSet.previous();
			
			while(resultSet.next()) 
			count++;
			
			
			
			CarID=new int[count];
			resultSet=statement.executeQuery(query);
			
			int counter=0;
			while(resultSet.next()) {
			CarID[counter]=resultSet.getInt(1);
			counter++;
			
			}
			
			connection.close();
			statement.close();
			resultSet.close();
			String result=" Car Name\tRental price \n\n";
			
			for (int i = 0; i < CarID.length; i++) {	
			
			new  DatabaseConnection();
			query="Select name,rentprice from CarRentalSystem.car where CarID='"+CarID[i]+"'";
			statement=connection.createStatement();
			resultSet=statement.executeQuery(query);
			while(resultSet.next())
				result+=" "+resultSet.getString(1)+"\t"+resultSet.getInt(2)+"\n";
			
			statement.close();
			connection.close();
			resultSet.close();
			
		
		}
		return result;
		}
		
		catch (Exception e) {
			System.out.println("Exception in getting column");
			return null;
			
		}

		
		finally {
			
			try {
				connection.close();
				statement.close();
				resultSet.close();
				
				
				
				
			} catch (Exception e2) {
				
			}
			
		}
	}
	
	public String ViewProfile(String username) {
		
		String result="\n\n\n\n";
		try {
			
			String query="Select *from CarRentalSystem.customer where username='"+username+"'";
			statement=connection.createStatement();
			resultSet=statement.executeQuery(query);
			
			resultSet.next();
			result+=" Username : "+resultSet.getString(1)+"\n";
			result+=" Full Name: "+resultSet.getString(2)+"\n";
			result+=" Password : "+resultSet.getString(3)+"\n";
			
			connection.close();
			resultSet.close();
			statement.close();
			
			return result;
			
			
			
		} catch (Exception e) {
			
			System.out.println("View Profile Exception resolve it");
			return null;
		}
		
		
	}
	
	
	
	public void UpdateProfile(String username,String FullName,String password) {
		
		String query;
		if(password.length()==0) 
			query="Update CarRentalSystem.customer set FullName='"+FullName+"' where username='"+username+"'";
		
		
		else if(FullName.length()==0)
			query="Update CarRentalSystem.customer set Userpassword='"+password+"' where username='"+username+"'";
	
		else 
			query="Update CarRentalSystem.customer set FullName='"+FullName+"',Userpassword='"+password+"' where username='"+username+"'";
		
		try {
		statement=connection.createStatement();
		int f=statement.executeUpdate(query);
		
		statement.close();
		connection.close();
		
		if(f==1)
			JOptionPane.showMessageDialog(null,"Your profile information has been updated","Info updated",JOptionPane.INFORMATION_MESSAGE);
		
		
		else {
			JOptionPane.showMessageDialog(null,"Can't update info at the moment","Try again later",JOptionPane.INFORMATION_MESSAGE);
				
		}
		
		}
		catch (Exception e) {
			System.out.println("Update profile exception");
			
		}
		
		
		
	}
}
