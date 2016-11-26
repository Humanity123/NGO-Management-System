package GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;

public class RegisterStudent extends JPanel {
	private JTextField nameField;
	private JTextField dobField;
	private JTextField classField;
	private JTextField schoolField;
	private JTextField booksField;
	private JTextField bagsField;
	private JTextField shoesField;
	private JTextField dressField;
	private JTextField feesField;
	private JTextField incomeField;
	private JTextField marksField;

	/**
	 * Create the panel.
	 */
	public RegisterStudent() {
		setLayout(null);

		nameField = new JTextField();
		nameField.setBounds(285, 40, 229, 32);
		add(nameField);
		nameField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(37, 40, 101, 25);
		add(lblNewLabel);

		dobField = new JTextField();
		dobField.setBounds(285, 90, 229, 32);
		add(dobField);
		dobField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Date of Birth");
		lblNewLabel_1.setBounds(33, 98, 105, 15);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Class");
		lblNewLabel_2.setBounds(37, 125, 84, 25);
		add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("School");
		lblNewLabel_3.setBounds(37, 162, 70, 15);
		add(lblNewLabel_3);

		classField = new JTextField();
		classField.setBounds(285, 134, 241, 25);
		add(classField);
		classField.setColumns(10);

		schoolField = new JTextField();
		schoolField.setBounds(295, 162, 241, 25);
		add(schoolField);
		schoolField.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Gender");
		lblNewLabel_4.setBounds(37, 284, 70, 15);
		add(lblNewLabel_4);

		final JComboBox genderComboBox = new JComboBox();
		genderComboBox.setBounds(297, 279, 181, 25);
		add(genderComboBox);

		genderComboBox.addItem("MALE");genderComboBox.addItem("FEMALE");
		JLabel lblNewLabel_5 = new JLabel("BOOKS");
		lblNewLabel_5.setBounds(37, 347, 84, 25);
		add(lblNewLabel_5);
		JLabel lblNewLabel_6 = new JLabel("BAGS");
		lblNewLabel_6.setBounds(37, 377, 70, 15);
		add(lblNewLabel_6);
		JLabel lblNewLabel_7 = new JLabel("SHOES");
		lblNewLabel_7.setBounds(37, 404, 70, 15);
		add(lblNewLabel_7);
		JLabel lblNewLabel_8 = new JLabel("DRESS");
		lblNewLabel_8.setBounds(37, 431, 70, 15);
		add(lblNewLabel_8);
		JLabel lblNewLabel_9 = new JLabel("FEES");
		lblNewLabel_9.setBounds(37, 458, 70, 15);
		add(lblNewLabel_9);
		booksField = new JTextField();
		booksField.setBounds(152, 350, 114, 19);
		add(booksField);
		booksField.setColumns(10);
		bagsField = new JTextField();
		bagsField.setBounds(152, 375, 114, 19);
		add(bagsField);
		bagsField.setColumns(10);
		shoesField = new JTextField();
		shoesField.setBounds(152, 400, 114, 19);
		add(shoesField);
		shoesField.setColumns(10);
		dressField = new JTextField();
		dressField.setText("");
		dressField.setBounds(152, 429, 114, 19);
		add(dressField);
		dressField.setColumns(10);
		feesField = new JTextField();
		feesField.setBounds(152, 456, 114, 19);
		add(feesField);
		feesField.setColumns(10);
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name,dob,school;
				int Class, marks, income, books, bags, shoes, dress, fees;
				boolean gender;
				try{
					name = nameField.getText();
					dob= dobField.getText();
					DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
					df.parse(dobField.getText());
					school = schoolField.getText();
					if(name.length()==0 || school.length()==0) throw new Exception();
					Class = Integer.parseInt(classField.getText());
					marks = Integer.parseInt(marksField.getText());
					income = Integer.parseInt(incomeField.getText());
					books = Integer.parseInt(booksField.getText());
					bags =  Integer.parseInt(bagsField.getText());
					shoes =  Integer.parseInt(shoesField.getText());
					dress =  Integer.parseInt(dressField.getText());
					fees =  Integer.parseInt(feesField.getText());
					gender = (genderComboBox.getSelectedIndex()==1);
					 
				}catch(Exception e){
					JOptionPane.showMessageDialog(Main.getPane(), "Invalid data!");
					return;
				}
				try {
					java.sql.Statement st =Login.getConnection().createStatement(); 
					ResultSet rs = st.executeQuery("select * from ids;");
					rs.next();
					int s=rs.getInt("students");
					String Id = new Integer(s).toString();
					Id="S"+Id;
					st.executeUpdate("update ids set students="+(s+1));

					int g=0;if(gender)g=1;
					String val = "\""+Id+"\","+"\""+name+"\","+"\""+dob+"\",\""+school+"\","+classField.getText()+","+marksField.getText()+","+incomeField.getText()+","+booksField.getText()+","+bagsField.getText()+","+
							shoesField.getText()+","+dressField.getText()+","+feesField.getText()+","+new Integer(g).toString();
					
					st.executeUpdate("insert into students (id,name,dob,school,class,marks,income,books,bags,shoes,dress,fees,gender)"
							+ "VALUES (" + val + ");");
					JOptionPane.showMessageDialog(Main.getPane(), "Registered!");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnRegister.setBounds(435, 399, 212, 59);
		add(btnRegister);
		
		JLabel lblNewLabel_10 = new JLabel("Family Income");
		lblNewLabel_10.setBounds(37, 234, 114, 15);
		add(lblNewLabel_10);
		
		incomeField = new JTextField();
		incomeField.setBounds(285, 232, 229, 17);
		add(incomeField);
		incomeField.setColumns(10);
		
		JLabel label = new JLabel("Marks");
		label.setBounds(37, 207, 70, 15);
		add(label);
		
		marksField = new JTextField();
		marksField.setColumns(10);
		marksField.setBounds(285, 199, 241, 25);
		add(marksField);

	}
}
