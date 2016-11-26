package BackEnd;
 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import GUI.Login;
 
public class Helper {
	private String name, id;
	private Contact contact;
	private Password password;
 
	private static String  driver="jdbc:mysql://localhost:3306/";
	private static String database="testdb";
	private static String username="root";
	private static String pass="qwerty";
	
	private ResultSet myrst=null;
 
	
 
	public void registerHelper(){
		try {
			Statement mystmt=Login.getConnection().createStatement();
			mystmt.executeUpdate("Insert into helpers (id,name,password,address,phone) values(\""+id+"\",\""+name+"\",\""+password.getPassword()+"\",\""+contact.getAddress()+"\",\""+contact.getPhone()+"\");");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
 
			e.printStackTrace();
		}
 
	}
	public Helper(String name, String id, Contact contact, Password password){
		this.name = name;
		this.id = id;
		this.contact = contact;
		this.password = password;
	}
 
	public boolean setPassword(String oldPassword, String newPassword){
		if(!oldPassword.equals(password))
		return false;
		password = new Password(newPassword);
		return true;
	}
 
//	public boolean registerStudent(Student student){
//		
//	}
 
}