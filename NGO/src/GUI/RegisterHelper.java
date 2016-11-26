//register helper panel
 
package GUI;
 
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
 
import BackEnd.Contact;
import BackEnd.Helper;
import BackEnd.Password;
 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
 
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
public class RegisterHelper extends JPanel {
	private JTextField nameTextField;
	private JTextField addressTextField;
	private JTextField phoneTextField;
	private JTextField passwordTextField;
 
	/**
	 * Create the panel.
	 */
 
	public RegisterHelper() {
		setLayout(null);
 
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(109, 87, 123, 16);
		add(lblName);
 
		nameTextField = new JTextField();
		nameTextField.setBounds(371, 82, 130, 26);
		add(nameTextField);
		nameTextField.setColumns(10);
 
		addressTextField = new JTextField();
		addressTextField.setColumns(10);
		addressTextField.setBounds(371, 140, 130, 26);
		add(addressTextField);
 
		JLabel lblContact = new JLabel("Address");
		lblContact.setBounds(109, 145, 123, 16);
		add(lblContact);
 
		phoneTextField = new JTextField();
		phoneTextField.setColumns(10);
		phoneTextField.setBounds(371, 202, 130, 26);
		add(phoneTextField);
 
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(109, 207, 123, 16);
		add(lblPhoneNumber);
 
		passwordTextField = new JTextField();
		passwordTextField.setColumns(10);
		passwordTextField.setBounds(371, 282, 130, 26);
		add(passwordTextField);
 
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(109, 287, 123, 16);
		add(lblPassword);
 
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=nameTextField.getText();
				String address=addressTextField.getText();
				String phone=phoneTextField.getText();
				String password=passwordTextField.getText();
				if(name.length()==0 || address.length()==0 || phone.length()==0 || password.length()==0 ){
					JOptionPane.showMessageDialog(getParent().getParent(), "Invalid Or Empty Entry!");
					return;
				}
				ResultSet rs;
				String Id;
				try {
					java.sql.Statement st =Login.getConnection().createStatement(); 
					rs = st.executeQuery("select * from ids;");
					rs.next();
					int h=rs.getInt("helpers");
					Id = new Integer(h).toString();
					Id="H"+Id;
					st.executeUpdate("update ids set helpers="+(h+1));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(getParent().getParent(), "Unknown error!");
					return;
				}
				Helper helper=new Helper(name,Id,new Contact(address,phone),new Password(password));
				helper.registerHelper();
			}
		});
		btnSubmit.setBounds(262, 376, 117, 29);
		add(btnSubmit);
 
	}
 
}