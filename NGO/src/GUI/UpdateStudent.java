package GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateStudent extends JPanel {
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
	private JTextField idField;
	private JTextField marksField;

	/**
	 * Create the panel.
	 */
	public UpdateStudent() {
		setLayout(null);

		nameField = new JTextField();
		nameField.setBounds(353, 110, 229, 25);
		add(nameField);
		nameField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(101, 110, 101, 25);
		add(lblNewLabel);

		dobField = new JTextField();
		dobField.setBounds(363, 145, 229, 24);
		add(dobField);
		dobField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Date of Birth");
		lblNewLabel_1.setBounds(101, 149, 105, 15);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Class");
		lblNewLabel_2.setBounds(105, 176, 84, 25);
		add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("School");
		lblNewLabel_3.setBounds(101, 213, 70, 15);
		add(lblNewLabel_3);

		classField = new JTextField();
		classField.setBounds(351, 181, 241, 25);
		add(classField);
		classField.setColumns(10);

		schoolField = new JTextField();
		schoolField.setBounds(353, 208, 241, 25);
		add(schoolField);
		schoolField.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Gender");
		lblNewLabel_4.setBounds(105, 335, 70, 15);
		add(lblNewLabel_4);

		JComboBox genderComboBox = new JComboBox();
		genderComboBox.setBounds(365, 330, 181, 25);
		add(genderComboBox);

		genderComboBox.addItem("MALE");genderComboBox.addItem("FEMALE");
		JLabel lblNewLabel_5 = new JLabel("BOOKS");
		lblNewLabel_5.setBounds(105, 400, 84, 25);
		add(lblNewLabel_5);
		JLabel lblNewLabel_6 = new JLabel("BAGS");
		lblNewLabel_6.setBounds(105, 428, 70, 15);
		add(lblNewLabel_6);
		JLabel lblNewLabel_7 = new JLabel("SHOES");
		lblNewLabel_7.setBounds(105, 455, 70, 15);
		add(lblNewLabel_7);
		JLabel lblNewLabel_8 = new JLabel("DRESS");
		lblNewLabel_8.setBounds(105, 482, 70, 15);
		add(lblNewLabel_8);
		JLabel lblNewLabel_9 = new JLabel("FEES");
		lblNewLabel_9.setBounds(105, 509, 70, 15);
		add(lblNewLabel_9);
		booksField = new JTextField();
		booksField.setBounds(220, 401, 114, 19);
		add(booksField);
		booksField.setColumns(10);
		bagsField = new JTextField();
		bagsField.setBounds(220, 426, 114, 19);
		add(bagsField);
		bagsField.setColumns(10);
		shoesField = new JTextField();
		shoesField.setBounds(220, 451, 114, 19);
		add(shoesField);
		shoesField.setColumns(10);
		dressField = new JTextField();
		dressField.setText("");
		dressField.setBounds(220, 480, 114, 19);
		add(dressField);
		dressField.setColumns(10);
		feesField = new JTextField();
		feesField.setBounds(220, 507, 114, 19);
		add(feesField);
		feesField.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Family Income");
		lblNewLabel_10.setBounds(105, 285, 114, 15);
		add(lblNewLabel_10);
		
		incomeField = new JTextField();
		incomeField.setBounds(353, 283, 229, 17);
		add(incomeField);
		incomeField.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("ID");
		lblNewLabel_11.setBounds(105, 27, 114, 32);
		add(lblNewLabel_11);
		
		idField = new JTextField();
		idField.setBounds(353, 27, 192, 25);
		add(idField);
		idField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(220, 51, 105, 46);
		add(btnSearch);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.setBounds(485, 398, 208, 72);
		add(btnNewButton);
		
		marksField = new JTextField();
		marksField.setBounds(353, 245, 241, 25);
		add(marksField);
		marksField.setColumns(10);
		
		JLabel label = new JLabel("Marks");
		label.setBounds(101, 240, 70, 15);
		add(label);




	}
}
