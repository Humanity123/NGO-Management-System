package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class MinorDonation extends JPanel {
	private JTextField booksTextField;
	private JTextField bagsTextField;
	private JTextField shoesTextField;
	private JTextField dressTextField;
	private JTextField moneyTextField;
	private JTextField bookClassTextField;
	private JTextField bagClassTextField;
	private JTextField shoeClassTextField;
	private JTextField dressClassTextField;

	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	private int convertToInt(JTextField field) throws Exception{
		if(field.getText().length()==0){
			Exception e=new Exception("Empty Field");
			throw e;
		}
		int value;
		try{
			value=Integer.parseInt(field.getText());
		}catch (Exception e){
			e=new Exception("Not A Valid Input");
			throw e;
		}
		return value; 
	}
	public MinorDonation() {
		setLayout(null);
		
		JLabel label = new JLabel("BOOKS");
		label.setBounds(155, 206, 84, 25);
		add(label);
		
		JLabel label_1 = new JLabel("BAGS");
		label_1.setBounds(155, 234, 70, 15);
		add(label_1);
		
		JLabel label_2 = new JLabel("SHOES");
		label_2.setBounds(155, 261, 70, 15);
		add(label_2);
		
		JLabel label_3 = new JLabel("DRESS");
		label_3.setBounds(155, 288, 70, 15);
		add(label_3);
		
		JLabel lblMoney = new JLabel("Money");
		lblMoney.setBounds(155, 315, 70, 15);
		add(lblMoney);
		
		booksTextField = new JTextField();
		booksTextField.setColumns(10);
		booksTextField.setBounds(270, 207, 114, 19);
		add(booksTextField);
		
		bagsTextField = new JTextField();
		bagsTextField.setColumns(10);
		bagsTextField.setBounds(270, 232, 114, 19);
		add(bagsTextField);
		
		shoesTextField = new JTextField();
		shoesTextField.setColumns(10);
		shoesTextField.setBounds(270, 257, 114, 19);
		add(shoesTextField);
		
		dressTextField = new JTextField();
		dressTextField.setText("");
		dressTextField.setColumns(10);
		dressTextField.setBounds(270, 286, 114, 19);
		add(dressTextField);
		
		moneyTextField = new JTextField();
		moneyTextField.setColumns(10);
		moneyTextField.setBounds(270, 313, 114, 19);
		add(moneyTextField);
		bookClassTextField = new JTextField();
		bookClassTextField.setBounds(442, 205, 130, 25);
		add(bookClassTextField);
		bookClassTextField.setColumns(10);
		
		bagClassTextField = new JTextField();
		bagClassTextField.setColumns(10);
		bagClassTextField.setBounds(442, 228, 130, 25);
		add(bagClassTextField);
		
		shoeClassTextField = new JTextField();
		shoeClassTextField.setColumns(10);
		shoeClassTextField.setBounds(442, 255, 130, 25);
		add(shoeClassTextField);
		
		dressClassTextField = new JTextField();
		dressClassTextField.setColumns(10);
		dressClassTextField.setBounds(442, 282, 130, 25);
		add(dressClassTextField);
		
		JButton btnAccept = new JButton("Accept");
		btnAccept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int books,bags,shoes,dress,money,bookClass,bagClass,shoeClass,dressClass;
				try{
					books=convertToInt(booksTextField);
					bags=convertToInt(bagsTextField);
					shoes=convertToInt(bagsTextField);
					dress=convertToInt(bagsTextField);
					money=convertToInt(moneyTextField);
					bookClass=convertToInt(bookClassTextField);
					bagClass=convertToInt(bagClassTextField);
					shoeClass=convertToInt(shoeClassTextField);
					dressClass=convertToInt(dressClassTextField);
					if(bookClass>12 || bagClass>12 || shoeClass>12 || dressClass>12||bookClass<=0 || bagClass<=0 || shoeClass<=0 || dressClass<=0){
						Exception e=new Exception("Invalid Class Input: No such Class Exists");
						throw e;
					}
				}catch(Exception e){
					JOptionPane.showMessageDialog(Main.getPane(), e.getMessage());
					return;
				}
				try {
					java.sql.Statement mystmt=Login.getConnection().createStatement();
					ResultSet myrst=mystmt.executeQuery("Select * from inventory");
					for(int i=1;i<=12;++i){        //assuming class are from 1 to 12
						myrst.next();
						if(bookClass==i){
							mystmt.executeUpdate("Update inventory set books=\""+(books+myrst.getInt("books"))+"\" where class=\""+i+"\";");
						}
						if(bagClass==i){
							mystmt.executeUpdate("Update inventory set bags=\""+(bags+myrst.getInt("bags"))+"\" where class=\""+i+"\";");
						}
						if(shoeClass==i){
							mystmt.executeUpdate("Update inventory set shoes=\""+(shoes+myrst.getInt("shoes"))+"\" where class=\""+i+"\";");
						}
						if(bookClass==i){
							mystmt.executeUpdate("Update inventory set dress=\""+(dress+myrst.getInt("dress"))+"\" where class=\""+i+"\";");
						}
					}
					myrst=mystmt.executeQuery("Select * from finance");
					mystmt.executeUpdate("Update finance set funds=\""+(money+myrst.getInt("funds"))+"\";");
							
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					return;
				}
				
				
			}
		});
		btnAccept.setBounds(220, 417, 215, 53);
		add(btnAccept);
		
		

	}

}
