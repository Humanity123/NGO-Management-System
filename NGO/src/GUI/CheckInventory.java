package GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class CheckInventory extends JPanel {
	private static JTable table;
	private static DefaultTableModel model;
	private JTextField fundsField;
	/**
	 * Create the panel.
	 */
private class MyDefaultTableModel extends DefaultTableModel {
	    
	    public MyDefaultTableModel() { // constructor
	        super();
	    }

	    @Override
	    public boolean isCellEditable(int row, int column) { // custom isCellEditable function
	        return false;
	    }
	}
	public CheckInventory() {
		setLayout(null);
		model= new MyDefaultTableModel();
		model.addColumn("CLASS");
		model.addColumn("BOOKS");
		model.addColumn("BAGS");
		model.addColumn("SHOES");
		model.addColumn("DRESS");
		try{
			java.sql.Statement st = Login.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from inventory;");
			for(int i=0;i<=11;++i){
				Vector row = new Vector();
				rs.next();
				row.add(i+1);
				row.add(rs.getInt("books"));
				row.add(rs.getInt("bags"));
				row.add(rs.getInt("shoes"));
				row.add(rs.getInt("dress"));
				model.addRow(row);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 111, 609, 344);
		add(scrollPane);
		table = new JTable(model);
		table.setRowSelectionAllowed(false);
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("Funds");
		lblNewLabel.setBounds(108, 51, 70, 15);
		add(lblNewLabel);
		
		fundsField = new JTextField();
		fundsField.setBounds(328, 49, 114, 19);
		add(fundsField);
		fundsField.setColumns(10);
		fundsField.setEditable(false);
		
	}
	
	public void reload(){
		try{
			java.sql.Statement st = Login.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from finance;");rs.next();
			fundsField.setText(""+rs.getInt("funds"));
			model.setRowCount(0);
			rs = st.executeQuery("select * from inventory;");
			for(int i=0;i<=11;++i){
				Vector row = new Vector();
				rs.next();
				row.add(i+1);
				row.add(rs.getInt("books"));
				row.add(rs.getInt("bags"));
				row.add(rs.getInt("shoes"));
				row.add(rs.getInt("dress"));
				model.addRow(row);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
