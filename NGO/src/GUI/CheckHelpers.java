package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import BackEnd.*;
import java.sql.*;

public class CheckHelpers extends JPanel {

	private static JTable table;
	private static DefaultTableModel model;

	private class MyDefaultTableModel extends DefaultTableModel {

		public MyDefaultTableModel() { // constructor
			super();
		}

		@Override
		public boolean isCellEditable(int row, int column) { // custom isCellEditable function
			return false;
		}
	}
	public CheckHelpers() {
		setLayout(null);
		model= new MyDefaultTableModel();
		model.addColumn("ID");
		model.addColumn("NAME");
		model.addColumn("ADDRESS");
		model.addColumn("PHONE");
		model.addColumn("PASSWORD");
		try{
			java.sql.Statement st = Login.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from donors order by name;");
			while(rs.next()){
				Vector row = new Vector();
				row.add(rs.getString("id"));
				row.add(rs.getString("name"));
				row.add(rs.getString("address"));
				row.add(rs.getString("phone"));
				row.add(rs.getString("password"));
				model.addRow(row);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(37, 111, 609, 344);
		add(scrollPane);
		table = new JTable(model);

		scrollPane.setViewportView(table);
	}

	public void reload(){
		try{
			java.sql.Statement st = Login.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from donors order by name;");
			int i=0;
			while(rs.next()){
				model.setValueAt(rs.getString("id"), i, 0);
				model.setValueAt(rs.getString("name"), i, 1);
				model.setValueAt(rs.getString("address"), i, 2);
				model.setValueAt(rs.getString("phone"), i, 3);
				model.setValueAt(rs.getInt("password"), i, 4);
				++i;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
