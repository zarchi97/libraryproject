package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;

public class BorrowTable extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel model;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BorrowTable frame = new BorrowTable();
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
	public BorrowTable() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BorrowTable");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel.setBounds(139, 11, 383, 39);
		contentPane.add(lblNewLabel);
		model=new DefaultTableModel();
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 88, 673, 250);
		contentPane.add(scrollPane);
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBackground(new Color(0, 139, 139));
		scrollPane.setViewportView(table);
		table.setModel(model);
		Object[] col= {"BookName","UserName","Borrow Qty"};
		model.setColumnIdentifiers(col);
		String url="jdbc:mysql://localhost:3306/mydb";
		Connection con=DriverManager.getConnection(url,"root","root");
		Statement st=con.createStatement();
		String query="select * from borrowtable";
		ResultSet rs=st.executeQuery(query);
		while(rs.next()) {
			Object []row= {rs.getString(2),rs.getString(3),rs.getInt(4)};
			model.addRow(row);
		}
	}
}
