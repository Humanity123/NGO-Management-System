//login iteration 2.0

package GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;

//import com.mysql.jdbc.Connection;

import BackEnd.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.sql.*;

public class Login extends JPanel {
	private JTextField IDField;
	private JPasswordField passwordField;


	private static String  driver="jdbc:mysql://localhost:3306/";
	private static String database="testdb";
	private static String username="root";
	private static String pass="qwerty";
	private static java.sql.Connection mycon=null;
	private ResultSet myrst=null;

	private  void establishConnection(){
		try {
			mycon=DriverManager.getConnection(driver+database,username,pass);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static java.sql.Connection getConnection(){
		return mycon;
	}

	private void authoriseUser(String user,String pass) throws InvalidLogin,SQLException{
		Statement mystmt=mycon.createStatement();
		myrst=mystmt.executeQuery("select * from helpers where id=\""+user+"\" and password=\""+pass+"\"");
		if(myrst.next()){
			System.out.println(myrst.getString("id"));
		}else{
			InvalidLogin e=new InvalidLogin() ;
			throw e;
		}
	}
	private void authoriseManager(String user,String pass) throws InvalidLogin,SQLException{
		Statement mystmt=mycon.createStatement();
		myrst=mystmt.executeQuery("select * from manager where id=\""+user+"\" and password=\""+pass+"\"");
		if(myrst.next()){
			System.out.println(myrst.getString("id"));
		}else{
			InvalidLogin e=new InvalidLogin() ;
			throw e;
		}
	}

	private void authoriseDonor(String user,String pass) throws InvalidLogin,SQLException{
		Statement mystmt=mycon.createStatement();
		myrst=mystmt.executeQuery("select * from donors where id=\""+user+"\" and password=\""+pass+"\"");
		if(myrst.next()){
			System.out.println(myrst.getString("id"));
		}else{
			InvalidLogin e=new InvalidLogin() ;
			throw e;
		}
	}

	public Login() {
		setLayout(null);
		establishConnection();
		IDField = new JTextField();
		IDField.setBounds(286, 96, 241, 40);
		add(IDField);
		IDField.setColumns(10);

		JLabel lblNewLabel = new JLabel("User ID : ");
		lblNewLabel.setBounds(42, 96, 105, 40);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password : ");
		lblNewLabel_1.setBounds(42, 191, 92, 31);if(true){
			Main.getCard().show(Main.getPane(),"Helper");
		}
		else{
			JOptionPane.showMessageDialog(getParent().getParent(), "Try again!");
		}
		add(lblNewLabel_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(286, 191, 251, 31);
		add(passwordField);

		JButton btnHelper = new JButton("Helper");
		btnHelper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = IDField.getText();
				String pass = passwordField.getText();
				try {
					authoriseUser(id, pass);
				} catch (InvalidLogin e) {
					JOptionPane.showMessageDialog(getParent().getParent(), "Invalid Username Or Password!");
					return;
				}
				catch( Exception e){
					JOptionPane.showMessageDialog(Main.getPane(), "Unknown Error Occured");
					return;
				}
				Main.getCard().show(Main.getPane(),"Helper");
				Helper helper;
				try {
					helper = new Helper(myrst.getString("name"), myrst.getString("id"), new Contact ( myrst.getString("address"),myrst.getString("phone")), new Password(myrst.getString("password")));
					HelperPanel.setHelper(helper);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(Main.getPane(), "Unknown Error Occured");
					return;
				}
			}
		});
		btnHelper.setBounds(30, 302, 117, 25);
		add(btnHelper);

		JButton btnManager = new JButton("Manager");
		btnManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String id = IDField.getText();
				String pass = passwordField.getText();
				try {
					authoriseManager(id, pass);
				} catch (InvalidLogin e) {
					JOptionPane.showMessageDialog(getParent().getParent(), "Invalid Username Or Password!");
					return;
				}
				catch( Exception e){
					JOptionPane.showMessageDialog(Main.getPane(), "Unknown Error Occured");
					return;
				}
				Main.getCard().show(Main.getPane(),"Manager");
				Manager manager;
				try {
					manager = new Manager(myrst.getString("name"), myrst.getString("id"), new Contact ( myrst.getString("address"),myrst.getString("phone")), new Password(myrst.getString("password")));
					ManagerPanel.setManager(manager);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(Main.getPane(), "Unknown Error Occured");
					return;
				}
			}
		});
		btnManager.setBounds(279, 302, 117, 25);
		add(btnManager);

		JButton btnDonor = new JButton("Donor");
		btnDonor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String id = IDField.getText();
				String pass = passwordField.getText();
				try {
					authoriseDonor(id, pass);
				} catch (InvalidLogin e) {
					JOptionPane.showMessageDialog(getParent().getParent(), "Invalid Username Or Password!");
					return;
				}
				catch( Exception e){
					JOptionPane.showMessageDialog(Main.getPane(), "Unknown Error Occured");
					return;
				}
				Main.getCard().show(Main.getPane(),"Donor");
				PledgedDonor donor;
				try {
					donor = new PledgedDonor(myrst.getString("name"), myrst.getString("id"), new Contact ( myrst.getString("address"),myrst.getString("phone")), new Password(myrst.getString("password")),myrst.getInt("amount"),(myrst.getInt("plan")==0)?DonationPlan.SEMI_ANNUALLY:DonationPlan.ANNUALLY,0,0);
					DonorPanel.setPledgedDonor(donor);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(Main.getPane(), "Unknown Error Occured");
					return;
				}
			}
		});
		btnDonor.setBounds(547, 321, 117, 25);
		add(btnDonor);

		JButton btnRegisterDonor = new JButton("Register Donor");
		btnRegisterDonor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterDonor.clear();
				Main.getCard().show(Main.getPane(), "RegisterDonor");
			}
		});
		btnRegisterDonor.setBounds(246, 414, 192, 40);
		add(btnRegisterDonor);
	}
}