package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import BackEnd.DonationPlan;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class RegisterDonor extends JPanel {
	private static JTextField nameField;
	private static JTextField addressField;
	private static JTextField phoneField;
	private static JTextField passwordField;
	private static JTextField amountField;
	private static String  driver="jdbc:mysql://localhost:3306/";
	private static String database="testdb";
	private static String username="root";
	private static String pass="qwerty";
	private static java.sql.Connection mycon=null;

	/**
	 * Create the panel.
	 */
	public RegisterDonor() {
		setLayout(null);
		
		JLabel label = new JLabel("Name");
		label.setBounds(175, 83, 123, 16);
		add(label);
		
		nameField = new JTextField();
		nameField.setColumns(10);
		nameField.setBounds(437, 78, 130, 26);
		add(nameField);
		
		addressField = new JTextField();
		addressField.setColumns(10);
		addressField.setBounds(437, 136, 130, 26);
		add(addressField);
		
		JLabel label_1 = new JLabel("Address");
		label_1.setBounds(175, 141, 123, 16);
		add(label_1);
		
		phoneField = new JTextField();
		phoneField.setColumns(10);
		phoneField.setBounds(437, 198, 130, 26);
		add(phoneField);
		
		JLabel label_2 = new JLabel("Phone Number");
		label_2.setBounds(175, 203, 123, 16);
		add(label_2);
		
		passwordField = new JTextField();
		passwordField.setColumns(10);
		passwordField.setBounds(437, 320, 130, 26);
		add(passwordField);
		
		JLabel label_3 = new JLabel("Password");
		label_3.setBounds(175, 324, 166, 16);
		add(label_3);
		
		final JComboBox planComboBox = new JComboBox();
		planComboBox.setBounds(420, 368, 181, 25);
		add(planComboBox);
		planComboBox.addItem("ANNUALLY");planComboBox.addItem("SEMI-ANNUALLY");
		
		
		JButton button = new JButton("Submit");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name=nameField.getText();
				String address=addressField.getText();
				String phone=phoneField.getText();
				String password=passwordField.getText();
				int amount,plan;
				try{
					amount = Integer.parseInt(amountField.getText());
				}catch(Exception e){
					JOptionPane.showMessageDialog(Main.getPane(), "Invalid Amount!");
					return;
				}
				plan=0;
				if(planComboBox.getSelectedIndex()==1)plan=1;
				if(name.length()==0 || address.length()==0 || phone.length()==0 || password.length()==0 ){
					JOptionPane.showMessageDialog(Main.getPane(), "Invalid Or Empty Entry!");
					return;
				}
				ResultSet rs;
				String Id;
				try {
					mycon=DriverManager.getConnection(driver+database,username,pass);
					java.sql.Statement st =mycon.createStatement(); 
					rs = st.executeQuery("select * from ids;");
					rs.next();
					int d=rs.getInt("donors");
					Id = new Integer(d).toString();
					Id="D"+Id;
					st.executeUpdate("update ids set donors="+(d+1));
					st.executeUpdate("Insert into donors (id,name,password,address,phone,amount,plan,notified,donated) values(\""
					+Id+"\",\""+name+"\",\""+password+"\",\""+address+"\",\""+phone+"\","+amount+","+plan+",0,0);");
					JOptionPane.showMessageDialog(Main.getPane(), "Successfully Registered");
					Main.getCard().show(Main.getPane(), "Login");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(Main.getPane(), "Unknown error!");
					return;
				}
				
			}
		});
		button.setBounds(328, 440, 117, 29);
		add(button);
		
		JLabel lblPledgedAmount = new JLabel("Pledged Amount");
		lblPledgedAmount.setBounds(175, 264, 137, 15);
		add(lblPledgedAmount);
		
		amountField = new JTextField();
		amountField.setBounds(437, 259, 130, 26);
		add(amountField);
		amountField.setColumns(10);
		
		
		
		JLabel lblPlan = new JLabel("Plan");
		lblPlan.setBounds(160, 373, 70, 15);
		add(lblPlan);

	}
	
	public static void clear(){
		nameField.setText("");
		addressField.setText("");
		phoneField.setText("");
		passwordField.setText("");
		amountField.setText("");
	}
}
