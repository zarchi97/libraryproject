package project;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;

public class SearchTable extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchTable frame = new SearchTable();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public SearchTable() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		model=new DefaultTableModel();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 565, 363);
		contentPane.add(scrollPane);
		table = new JTable();
		table.setBackground(new Color(0, 139, 139));
		scrollPane.setViewportView(table);
		table.setModel(model);
		
		Object[] col= {"BookName","Author","Qty","Price","BorrowQty"};
		model.setColumnIdentifiers(col);
		String url="jdbc:mysql://localhost:3306/mydb";
		Connection con=DriverManager.getConnection(url,"root","root");
		Statement st=con.createStatement();
		String query1="select * from searchtable";
		ResultSet rs=st.executeQuery(query1);
		while(rs.next()) {
			Object []row= {rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDouble(5),rs.getInt(6)};
			model.addRow(row);
		}
	}

}
