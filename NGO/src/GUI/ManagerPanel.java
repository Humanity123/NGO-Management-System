
 
package GUI;
 
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.sql.*;
 
import BackEnd.Helper;
import BackEnd.Manager;
 
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenu;
 
public class ManagerPanel extends JPanel {
 
	
	private static CardLayout card;
	private static Manager manager;
	private static RegisterHelper registerHelper;
	private static CheckInventory checkInventory;
	private static CheckStudents checkStudents;
	private static CheckDonors checkDonors;
	private static CheckHelpers checkHelpers;
	private static InitiateHelp initiateHelp;
	
	private static JPanel contentPane;
	public ManagerPanel() {
		
		registerHelper=new RegisterHelper();
		checkInventory = new CheckInventory();
		checkStudents = new CheckStudents();
		checkDonors = new CheckDonors();
		checkHelpers = new CheckHelpers();
		initiateHelp = new InitiateHelp();
		
		setLayout(null);
		card = new CardLayout(0,0);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 750, 22);
		add(menuBar);
 
		JMenuItem mntmRegisterHelper = new JMenuItem("Register Helper");
		mntmRegisterHelper.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				card.show(contentPane, "RegisterHelper");
			}
		});
		menuBar.add(mntmRegisterHelper);
		
		JMenuItem mntmInitiateHelp = new JMenuItem("Initiate Help");
		mntmInitiateHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initiateHelp.initiate();
				card.show(contentPane, "InitiateHelp");
			}
		});
		
		JMenuItem mntmNotifyDonors = new JMenuItem("Notify Donors");
		mntmNotifyDonors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Statement st = Login.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
		                    ResultSet.CONCUR_UPDATABLE);
					int cost[][]= new int[12][4];
					for(int i=0;i<12;++i){
						ResultSet rss = st.executeQuery("select * from price where class = "+(i+1)+";");
						rss.next();
						cost[i][0]=rss.getInt("books");
						cost[i][1]=rss.getInt("bags");
						cost[i][2]=rss.getInt("shoes");
						cost[i][3]=rss.getInt("dress");
					}
					int need=0;int Need[][]= new int[12][4];
					ResultSet rs = st.executeQuery("select * from students;");
					while(rs.next()){
						int c = rs.getInt("class")-1;
						need+=rs.getInt("fees");
						Need[c][0]+=rs.getInt("books");
						Need[c][1]+=rs.getInt("bags");
						Need[c][2]+=rs.getInt("shoes");
						Need[c][3]+=rs.getInt("dress");
					}
					System.out.println("YOLO : "+need);
					
					int have=0; int Have[][]=new int[12][4];
					ResultSet t1=st.executeQuery("select * from finance;");t1.next();
					have=t1.getInt("funds");
					
					t1=st.executeQuery("select * from inventory;");
					int _c=0;
					while(t1.next()){
						Have[_c][0]+=t1.getInt("books");
						Have[_c][1]+=t1.getInt("bags");
						Have[_c][2]+=t1.getInt("shoes");
						Have[_c][3]+=t1.getInt("dress");
						_c++;
					}
					for(int j=0;j<12;++j){
						for(int i=0;i<4;++i){
							Need[j][i]-=Have[j][i];
							if(Need[j][i]>0)
								need+=Need[j][i]*cost[j][i];
						}
					}
					rs = st.executeQuery("select * from donors");
					while(rs.next()){
						if(need<0)break;
						if(rs.getInt("donated")==(rs.getInt("plan")+1))continue;
						 need-=rs.getInt("amount");
						 rs.updateInt("notified",1);
						 rs.updateRow();
					}
					String msg = "Notified Donors !(if any) ";
					if(need>0) msg=msg+"Still need "+need+" money";
					JOptionPane.showMessageDialog(Main.getPane(), msg);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(Main.getPane(), "Unknown Error !");
				}				
			}
		});
		menuBar.add(mntmNotifyDonors);
		menuBar.add(mntmInitiateHelp);
		
		JMenu mnNewMenu = new JMenu("Check Records");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmCheckInventory = new JMenuItem("Check Inventory");
		mnNewMenu.add(mntmCheckInventory);
		
		JMenuItem mntmNewMenuItemm = new JMenuItem("Check Helpers");
		mnNewMenu.add(mntmNewMenuItemm);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Check Donors");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmCheckStudents = new JMenuItem("Check Students");
		mnNewMenu.add(mntmCheckStudents);
		mntmCheckStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkStudents.reload();
				card.show(contentPane, "CheckStudents");
			}
		});
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkDonors.reload();
				card.show(contentPane, "CheckDonors");
			}
		});
		mntmNewMenuItemm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("here");
				checkHelpers.reload();
				card.show(contentPane, "CheckHelpers");
			}
		});
		mntmCheckInventory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				checkInventory.reload();
				card.show(contentPane, "CheckInventory");
			}
		});
 
		contentPane = new JPanel();
		contentPane.setBounds(0, 22, 750, 530);
		add(contentPane);
		contentPane.setLayout(card);
 
		contentPane.add(registerHelper,"RegisterHelper");
		contentPane.add(checkInventory,"CheckInventory");
		contentPane.add(checkStudents,"CheckStudents");
		contentPane.add(checkDonors,"CheckDonors");
		contentPane.add(checkHelpers,"CheckHelpers");
		contentPane.add(initiateHelp, "InitiateHelp");
 
	}
	public static void setManager(Manager manager){
		ManagerPanel.manager = manager;
	}
}