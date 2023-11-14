package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class DeleteBooks extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel model;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DeleteBooks frame = new DeleteBooks();
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
	public DeleteBooks() throws SQLException {
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1147, 521);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 160, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Delete Books");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(394, 11, 369, 47);
		contentPane.add(lblNewLabel);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(477, 116, 637, 284);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		model=new DefaultTableModel();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 617, 262);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(188, 143, 143));
		scrollPane.setViewportView(table);
		
        table.setModel(model);
        
        JButton btnNewButton = new JButton("<<<");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		boolean flag=true;
				AdminManagement admin=new AdminManagement();
				admin.setVisible(true);
				dispose();

        	}
        });
        btnNewButton.setBackground(new Color(102, 205, 170));
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        btnNewButton.setBounds(993, 411, 118, 40);
        contentPane.add(btnNewButton);
        
        JComboBox combo1 = new JComboBox();
        combo1.setBounds(223, 145, 202, 44);
        contentPane.add(combo1);
        combo1.setFont(new Font("Times New Roman", Font.BOLD, 18));
        combo1.setModel(new DefaultComboBoxModel(new String[] {"Book1", "Book2", "Book3"}));
        
        JLabel lblNewLabel_1 = new JLabel("Book Name:");
        lblNewLabel_1.setBounds(74, 145, 152, 44);
        contentPane.add(lblNewLabel_1);
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
        
        JButton btnDelete = new JButton("Delete ByBN");
        btnDelete.setBounds(74, 265, 137, 44);
        contentPane.add(btnDelete);
        btnDelete.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String dl=String.valueOf(combo1.getSelectedItem());
        		if(!dl.equals("")) {
        			String url="jdbc:mysql://localhost:3306/mydb";
        			try {
        				Connection con=DriverManager.getConnection(url,"root","root");
        				Statement st=con.createStatement();
        				String query="select * from addbooks";
        				ResultSet rs=st.executeQuery(query);
        				boolean flag=false;
        				while(rs.next()) {
        					String bname=rs.getString("BookName");
        				
        					if(dl.equals(bname)) {
        					flag=true;
        					break;
        					}
        				}
        				if(flag) {
        				String query1="delete from addbooks where BookName= '"+dl+"'";
        				st.executeUpdate(query1);
        				JOptionPane.showMessageDialog(btnDelete, "Delete Successful!");
        				
        				}
        				else {
        					JOptionPane.showMessageDialog(btnDelete,"BookName doesn't exist");
        				}
        			
        				} catch (SQLException e1) {
        				// TODO Auto-generated catch block
        				e1.printStackTrace();
        			}
        		}
        			else {
        				JOptionPane.showMessageDialog(btnDelete, "Please Enter BookName");
        			}
        		}
        });
        btnDelete.setBackground(new Color(102, 205, 170));
        btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 18));
        
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(273, 265, 118, 44);
        contentPane.add(btnCancel);
        btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 18));
        btnCancel.setBackground(new Color(102, 205, 170));
        
		Object[] col= {"BookName","Author","Qty","Price","BorrowQty"};
		model.setColumnIdentifiers(col);
		String url="jdbc:mysql://localhost:3306/mydb";
		Connection con=DriverManager.getConnection(url,"root","root");
		Statement st=con.createStatement();
		String query1="select * from addbooks";
		ResultSet rs=st.executeQuery(query1);
		while(rs.next()) {
			Object []row= {rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDouble(5),rs.getInt(6)};
					model.addRow(row);	
			}
	}
}
