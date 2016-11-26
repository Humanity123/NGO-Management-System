package GUI;

import javax.swing.JPanel;

import BackEnd.PledgedDonor;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class DonorPanel extends JPanel {

	private static PledgedDonor donor;
	private JButton btnDonate;
	
	public DonorPanel() {
		setLayout(null);
		
		btnDonate = new JButton("Waste money here");
		btnDonate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					java.sql.Statement mystmt=Login.getConnection().createStatement();
					ResultSet rst=mystmt.executeQuery("select * from finance");
					rst.next();
					mystmt.executeUpdate("update finance set funds=\""+(rst.getInt("funds")+donor.getPledgedAmount())+"\";");
					JOptionPane.showMessageDialog(Main.getPane(), "Thank You For Your Donation");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
				}

			}
		});
		btnDonate.setBounds(204, 89, 216, 38);
		add(btnDonate);
		
	}
	public void reload(){
		if(donor.isNotified()==0)
			btnDonate.setVisible(false);
		else
			btnDonate.setVisible(true);
	}
	public static void setPledgedDonor(PledgedDonor donor){
		DonorPanel.donor=donor;
	}
}
