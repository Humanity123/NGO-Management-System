package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import BackEnd.*;
import java.sql.*;

public class CheckExpenses extends JPanel {

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
	public CheckExpenses() {
		model= new MyDefaultTableModel();
		model.addColumn("NAME");
		model.addColumn("BOOKS");
		model.addColumn("BAGS");
		model.addColumn("DRESS");
		model.addColumn("SHOES");
		model.addColumn("FEES");

		try{
			java.sql.Statement st = Login.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from finance;");
			while(rs.next()){
				Vector row = new Vector();
				row.add(rs.getString("name"));
				row.add(rs.getInt("books"));
				row.add(rs.getInt("bags"));
				row.add(rs.getInt("dress"));
				row.add(rs.getInt("shoes"));
				row.add(rs.getInt("fees"));
				model.addRow(row);
			}
	}catch(Exception e){
		e.printStackTrace();
	}
	setLayout(null);
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setBounds(37, 111, 609, 344);
	add(scrollPane);
	table = new JTable(model);

	scrollPane.setViewportView(table);
}

public void reload(){
	try{
		java.sql.Statement st = Login.getConnection().createStatement();
		ResultSet rs = st.executeQuery("select * from expense;");
		model.setRowCount(0);
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getString("name"));
			row.add(rs.getInt("books"));
			row.add(rs.getInt("bags"));
			row.add(rs.getInt("dress"));
			row.add(rs.getInt("shoes"));
			row.add(rs.getInt("fees"));
			model.addRow(row);
		}
	}catch(Exception e){
		e.printStackTrace();
	}
}
}
