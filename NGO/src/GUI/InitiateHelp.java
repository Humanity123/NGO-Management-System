//initiate help panel not debugged iteration 1.0
 
package GUI;
 
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
 
public class InitiateHelp extends JPanel {
	private JTextField totalFundsTextField;
	private JTextField totalRegisteredStudentTextField;
	private JTextField studentsToBeHelpedTextField;
 
	/**
	 * Create the panel.
	 */
	public InitiateHelp() {
		setLayout(null);
 
		JLabel lblNewLabel = new JLabel("Total Funds");
		lblNewLabel.setBounds(121, 85, 181, 16);
		add(lblNewLabel);
 
		totalFundsTextField = new JTextField();
		totalFundsTextField.setEditable(false);
		totalFundsTextField.setBounds(314, 80, 130, 26);
		add(totalFundsTextField);
		totalFundsTextField.setColumns(10);
 
		JLabel lblTotalRegistered = new JLabel("Total Registered Students");
		lblTotalRegistered.setBounds(121, 132, 181, 16);
		add(lblTotalRegistered);
 
		totalRegisteredStudentTextField = new JTextField();
		totalRegisteredStudentTextField.setEditable(false);
		totalRegisteredStudentTextField.setColumns(10);
		totalRegisteredStudentTextField.setBounds(314, 127, 130, 26);
		add(totalRegisteredStudentTextField);
 
		JLabel lblStudentsToBe = new JLabel("Students to be Helped");
		lblStudentsToBe.setBounds(121, 207, 181, 16);
		add(lblStudentsToBe);
 
		studentsToBeHelpedTextField = new JTextField();
		studentsToBeHelpedTextField.setEditable(false);
		studentsToBeHelpedTextField.setColumns(10);
		studentsToBeHelpedTextField.setBounds(314, 202, 130, 26);
		add(studentsToBeHelpedTextField);
 
		JButton btnInitiateHelp = new JButton("Initiate Help");
		btnInitiateHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int have=0; 
					int Have[][]=new int[12][4];
					int cost[][]= new int[12][4];
					int expensed[]=new int[5];
					ArrayList<String> ids=new ArrayList<>();
 
 
 
					int moneyR,bagR,shoeR,dressR,classIn,bookR;
					java.sql.Statement mystmt=Login.getConnection().createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,  ResultSet.CONCUR_UPDATABLE);
					ResultSet myrst = mystmt.executeQuery("select * from students ORDER BY( (0.5*income + 0.1*class + sqrt(marks))*(0.2*gender+1));");
					ResultSet money=mystmt.executeQuery("select * from finance;");
					ResultSet inventory=mystmt.executeQuery("select * from inventory;");
					ResultSet expense=mystmt.executeQuery("select * from expenses;");
 
					int _c=0;
					while(expense.next()){
						expensed[_c]=expense.getInt(_c);
						++_c;
					}
 
					for(int i=0;i<12;++i){
						ResultSet rss = mystmt.executeQuery("select * from price where class = "+(i+1)+";");
						rss.next();
						cost[i][0]=rss.getInt("books");
						cost[i][1]=rss.getInt("bags");
						cost[i][2]=rss.getInt("shoes");
						cost[i][3]=rss.getInt("dress");
					}
 
					inventory = mystmt.executeQuery("select * from inventory;");
					_c=0;
					while(inventory.next()){
						Have[_c][0]+=inventory.getInt("books");
						Have[_c][1]+=inventory.getInt("bags");
						Have[_c][2]+=inventory.getInt("shoes");
						Have[_c][3]+=inventory.getInt("dress");
						_c++;
					}
					have=money.getInt("funds");
					while(myrst.next()){
						moneyR=myrst.getInt("fees");
						bagR=myrst.getInt("bags");
						bookR=myrst.getInt("books");
						shoeR=myrst.getInt("shoes");
						dressR=myrst.getInt("dress");
						classIn=myrst.getInt("class");
 
						if(bagR>Have[classIn][1] || shoeR>Have[classIn][2] || dressR > Have[classIn][3] || bookR> Have[classIn][0] || moneyR> have){
							int moneyN=0,bagN=0,bookN=0,dressN=0,shoeN=0,feeN=moneyR;
							if(bagR>Have[classIn][1]) moneyN+=bagN=(bagR-Have[classIn][1])*cost[classIn][1];
							if(shoeR>Have[classIn][2]) moneyN+=shoeN=(shoeR-Have[classIn][2])*cost[classIn][2];
							if(bookR> Have[classIn][0]) moneyN+=bookN=(bookR- Have[classIn][0])*cost[classIn][0];
							if(dressR > Have[classIn][3]) moneyN+=dressN=(dressR - Have[classIn][3])*cost[classIn][3];
							moneyN+=moneyR;
								if(moneyN>have){
									mystmt.executeUpdate("insert into helped VALUES(\""+myrst.getString("id")+"\","+bookR+","+bagR+","+shoeR+","+dressR+","+moneyR+"");
									ids.add(myrst.getString("id"));
//									expensed[1]+=bagR*cost[classIn][1];
//									expensed[2]+=shoeR*cost[classIn][2];
//									expensed[3]+=dressR*cost[classIn][3];
//									expensed[0]+=bookR*cost[classIn][0];
//									expensed[4]+=moneyN;
									have-=feeN;
									mystmt.executeUpdate("insert into expenses VALUES(\""+myrst.getString("name")+"\","+bookN+","+bagN+","+shoeN+","+dressN+","+feeN+"");
									Have[classIn][1]-=bagR;
									Have[classIn][2]-=shoeR;
									Have[classIn][3]-=dressR;
									Have[classIn][0]-=bookR;
									if(Have[classIn][1]<0) Have[classIn][1]=0;
									if(Have[classIn][2]<0) Have[classIn][2]=0;
									if(Have[classIn][0]<0) Have[classIn][0]=0;
									if(Have[classIn][3]<0) Have[classIn][3]=0;
								}
								continue;
						}
 
						mystmt.executeUpdate("insert into helped VALUES(\""+myrst.getString("id")+"\","+bookR+","+bagR+","+shoeR+","+dressR+","+moneyR+"");
						ids.add(myrst.getString("id"));
						Have[classIn][1]-=bagR;
						//expensed[1]+=bagR*cost[classIn][1];
						Have[classIn][2]-=shoeR;
						//expensed[2]+=shoeR*cost[classIn][2];
						Have[classIn][3]-=dressR;
						//expensed[3]+=dressR*cost[classIn][3];
						Have[classIn][0]-=bookR;
						//expensed[0]+=bookR*cost[classIn][0];
						have-=moneyR;
						mystmt.executeUpdate("insert into expenses VALUES(\""+myrst.getString("name")+"\","+0+","+0+","+0+","+0+","+moneyR+"");
						//expensed[4]+=moneyR;
					}
 
					mystmt.executeUpdate("update finance set funds=\""+(have)+"\";");
 
					for(int i=0;i<ids.size();++i){
						mystmt.executeUpdate("delete from students where id=\""+ids.get(i)+"\";");
					}
 
					inventory=mystmt.executeQuery("select * from inventory;");
					_c=0;
					while(inventory.next()){
						inventory.updateInt(1,Have[_c][0]);
						inventory.updateInt(2,Have[_c][1]);
						inventory.updateInt(3,Have[_c][2]);
						inventory.updateInt(4,Have[_c][3]);
						inventory.updateRow();
					}
 
 
//					expense=mystmt.executeQuery("select * from expenses;");
//					_c=0;
//					while(expense.next()){
//						expense.updateInt("amount", expensed[_c]);
//						expense.updateRow();
//						++_c;
//					}
					initiate();
 
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
 
			}
		});
		btnInitiateHelp.setBounds(222, 358, 117, 29);
		add(btnInitiateHelp);
 
	}
	public void initiate(){
		try {
			java.sql.Statement mystmt=Login.getConnection().createStatement();
			ResultSet money=mystmt.executeQuery("select * from finance;");
			ResultSet studs=mystmt.executeQuery("select * from students;");
			ResultSet hlpd=mystmt.executeQuery("select * from students;");
			int numStud=0;
			int numHelped=0;
			while(studs.next()) ++numStud;
			while(hlpd.next()) ++numHelped;
			totalFundsTextField.setText(""+money.getInt("funds"));
			totalRegisteredStudentTextField.setText(""+studs);
			studentsToBeHelpedTextField.setText(""+numHelped);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
 
