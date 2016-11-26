package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;
import BackEnd.*;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GiveHelp extends JPanel {

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
	public GiveHelp() {
		model= new MyDefaultTableModel();
		model.addColumn("ID");
		model.addColumn("BOOKS");
		model.addColumn("BAGS");
		model.addColumn("DRESS");
		model.addColumn("SHOES");
		model.addColumn("FEES");

		try{
			java.sql.Statement st = Login.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from helped;");
			while(rs.next()){
				Vector row = new Vector();
				row.add(rs.getString("id"));
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
	
	JButton btnHelp = new JButton("Help");
	btnHelp.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try{
				int r=table.getSelectedRow();
				if(r<0){JOptionPane.showMessageDialog(Main.getPane(), "Invalid Action!");return;}
				java.sql.Statement st = Login.getConnection().createStatement();
				st.executeUpdate("delete from helped where id = \""+(String)table.getValueAt(r, 0)+"\";");
				((DefaultTableModel)table.getModel()).removeRow(r);
			}catch(Exception e){
				JOptionPane.showMessageDialog(Main.getPane(), "Unknown Error");
				e.printStackTrace();
			}
		}
	});
	btnHelp.setBounds(658, 244, 117, 45);
	add(btnHelp);
}

public void reload(){
	try{
		java.sql.Statement st = Login.getConnection().createStatement();
		ResultSet rs = st.executeQuery("select * from helped order by id;");
		model.setRowCount(0);
		while(rs.next()){
			Vector row = new Vector();
			row.add(rs.getString("id"));
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
