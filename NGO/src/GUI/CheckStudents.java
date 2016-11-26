package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import java.sql.*;

public class CheckStudents extends JPanel {

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
	public CheckStudents() {
		model= new MyDefaultTableModel();
		model.addColumn("ID");
		model.addColumn("NAME");
		model.addColumn("CLASS");
		model.addColumn("FEES");
		model.addColumn("BOOKS");
		model.addColumn("BAGS");
		model.addColumn("SHOES");
		model.addColumn("DRESS");
		try{
			java.sql.Statement st = Login.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from students order by name;");
			while(rs.next()){
				Vector row = new Vector();
				row.add(rs.getString("id"));
				row.add(rs.getString("name"));
				row.add(rs.getInt("class"));
				row.add(rs.getInt("fees"));
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

	scrollPane.setViewportView(table);
}

public void reload(){
	try{
		java.sql.Statement st = Login.getConnection().createStatement();
		ResultSet rs;
		rs=st.executeQuery("select * from students order by name;");
		model.setRowCount(0);
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getString("id"));
			row.add(rs.getString("name"));
			row.add(rs.getInt("class"));
			row.add(rs.getInt("fees"));
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
