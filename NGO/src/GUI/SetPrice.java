package GUI;

import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SetPrice extends JPanel {
	private static JTable table;
	private static DefaultTableModel model;
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	private class MyDefaultTableModel extends DefaultTableModel {
	    
	    public MyDefaultTableModel() { // constructor
	        super();
	    }

	    @Override
	    public boolean isCellEditable(int row, int column) { // custom isCellEditable function
	        return column>0;
	    }

	    
	}
	
	public SetPrice()  {
		setLayout(null);
		model= new MyDefaultTableModel();
		model.addColumn("CLASS");
		model.addColumn("BOOKS");
		model.addColumn("BAGS");
		model.addColumn("SHOES");
		model.addColumn("DRESS");
		try{
			java.sql.Statement st = Login.getConnection().createStatement();
			ResultSet rs = st.executeQuery("select * from price;");
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
		scrollPane.setBounds(28, 64, 609, 344);
		add(scrollPane);
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{save();}
				catch(Exception e){e.printStackTrace();}
			}
		});
		btnSave.setBounds(232, 453, 117, 25);
		add(btnSave);
	}
		
	private void save()throws Exception{
		for(int i=0;i<12;++i){
			try{
				Integer.parseInt(table.getModel().getValueAt(i, 1).toString());
				Integer.parseInt(table.getModel().getValueAt(i, 2).toString());
				Integer.parseInt(table.getModel().getValueAt(i, 3).toString());
				Integer.parseInt(table.getModel().getValueAt(i, 4).toString());
			}
			catch(NumberFormatException e){
				JOptionPane.showMessageDialog(Main.getPane(), "Incorrect Values. No changes saved!");
				return;
			}
		}
		for(int i=0;i<12;++i){
			java.sql.Statement st = Login.getConnection().createStatement();
			st.executeUpdate("update price set books = "+table.getModel().getValueAt(i, 1)+" where class = "+(i+1)+" ;");
			st.executeUpdate("update price set bags = "+table.getModel().getValueAt(i, 2)+" where class = "+(i+1)+" ;");
			st.executeUpdate("update price set shoes = "+table.getModel().getValueAt(i, 3)+" where class = "+(i+1)+" ;");
			st.executeUpdate("update price set dress = "+table.getModel().getValueAt(i, 4)+" where class = "+(i+1)+" ;");
		}
		JOptionPane.showMessageDialog(Main.getPane(), "Updated!");
	}
}
