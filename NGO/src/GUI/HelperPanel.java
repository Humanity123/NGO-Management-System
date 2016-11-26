package GUI;
 
import javax.swing.JPanel;
 
import BackEnd.Helper;
 
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
public class HelperPanel extends JPanel {
 
	private static Helper helper;
	private static RegisterStudent registerStudent;
	private static UpdateStudent updateStudent;
	private static MinorDonation minorDonation;
	private static SetPrice setPrice;
	private static GiveHelp giveHelp;
 
	private static JPanel contentPane;
 
	private static CardLayout card;
	private JMenuItem mntmMinorDonation;
 
 
 
	public HelperPanel() {
		setLayout(null);
 
		contentPane = new JPanel();
		registerStudent = new RegisterStudent();
		updateStudent = new UpdateStudent();
		minorDonation = new MinorDonation();
		setPrice =new SetPrice();
		giveHelp = new GiveHelp();
 
		contentPane.setBounds(0, 30, 816, 515);
		add(contentPane);
		card = new CardLayout(0, 0);
		contentPane.setLayout(card);
 
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 816, 32);
		add(menuBar);
 
		JMenuItem mntmRegisterStudent = new JMenuItem("Register Student");
		mntmRegisterStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.show(contentPane, "RegisterStudent");
 
			}
		});
 
		menuBar.add(mntmRegisterStudent);
 
		JMenuItem mntmUpdateStudent = new JMenuItem("Update Student");
		mntmUpdateStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.show(contentPane, "UpdateStudent");
			}
		});
		menuBar.add(mntmUpdateStudent);
 
		mntmMinorDonation = new JMenuItem("Give Alms");
		mntmMinorDonation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(contentPane, "MinorDonation");
			}
		});
		
		JMenuItem mntmSetPrice = new JMenuItem("Set Price");
		mntmSetPrice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.show(contentPane,"SetPrice");
			}
		});
		menuBar.add(mntmSetPrice);
		menuBar.add(mntmMinorDonation);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Give Help");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				giveHelp.reload();
				card.show(contentPane, "GiveHelp");
			}
		});
		menuBar.add(mntmNewMenuItem);
 
		contentPane.add(registerStudent,"RegisterStudent");
		contentPane.add(updateStudent,"UpdateStudent");
		contentPane.add(minorDonation,"MinorDonation");
		contentPane.add(setPrice, "SetPrice");
		contentPane.add(giveHelp, "GiveHelp");
 
 
 
	}
	public static void setHelper(Helper helper){
		HelperPanel.helper = helper;
	}
}