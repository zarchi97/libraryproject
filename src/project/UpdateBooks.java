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
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class UpdateBooks extends JFrame {

	private JPanel contentPane;
	private JTextField txtQty;
	private JTextField txtPrice;
	private JTextField txtBQty;
    private DefaultTableModel model;
    private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateBooks frame = new UpdateBooks();
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
	public UpdateBooks() throws SQLException {
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1112, 676);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 160, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Update Book");
		lblNewLabel.setBackground(new Color(245, 245, 245));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(362, 22, 394, 62);
		contentPane.add(lblNewLabel);
		
		model=new DefaultTableModel();
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(2, 1, 2, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(473, 129, 597, 454);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 577, 432);
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
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBackground(new Color(102, 205, 170));
		btnNewButton.setBounds(957, 594, 89, 34);
		contentPane.add(btnNewButton);
		
		JComboBox combo2 = new JComboBox();
		combo2.setBounds(200, 159, 198, 48);
		contentPane.add(combo2);
		combo2.setBackground(new Color(220, 220, 220));
		combo2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		combo2.setModel(new DefaultComboBoxModel(new String[] {"Book1", "Book2", "Book3", "Book4"}));
		
		JLabel lblNewLabel_1 = new JLabel("Book Name:");
		lblNewLabel_1.setBounds(72, 157, 118, 53);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JLabel lblNewLabel_1_1 = new JLabel("Qty:");
		lblNewLabel_1_1.setBounds(92, 226, 51, 53);
		contentPane.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Borrow Qty:");
		lblNewLabel_1_1_1.setBounds(72, 303, 118, 53);
		contentPane.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JLabel lblNewLabel_1_2 = new JLabel("Price:");
		lblNewLabel_1_2.setBounds(92, 373, 66, 53);
		contentPane.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setBounds(97, 483, 108, 49);
		contentPane.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname =String.valueOf(combo2.getSelectedItem());
				String qty = txtQty.getText();
                String bqty = txtBQty.getText();
                String price=txtPrice.getText();
               
				if (!bname.equals("") && !qty.equals("")) {
					String url = "jdbc:mysql://localhost:3306/mydb";
					try {
						Connection con = DriverManager.getConnection(url, "root", "root");
						int Qty = Integer.valueOf(qty);
						int BQty = Integer.valueOf(bqty);
						Double pr=Double.valueOf(price);
						//int BQty = Integer.valueOf(bqty);
						Statement st = con.createStatement();
						String query = "select * from addbooks";
						ResultSet rs = st.executeQuery(query);
						boolean flag = false;
						while (rs.next()) {
							String bkname = rs.getString(2);
							if (bname.equals(bkname)) {
								flag = true;
								break;
							}
						}
						if (flag) {
							String query1 = "update addbooks set Qty='"+ Qty +"',BorrowQty='"+ BQty +"',Price='"+ pr +"'  where BookName= '"+ bname +"'";
							PreparedStatement ps = con.prepareStatement(query1);
							ps.executeUpdate();
							JOptionPane.showMessageDialog(btnUpdate, "Update successful!");
							con.close();
						} else {
							JOptionPane.showMessageDialog(btnUpdate, "This book doesn't exist");
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					JOptionPane.showMessageDialog(btnUpdate, "Please fill info");
				}
				
				}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnUpdate.setBackground(new Color(102, 205, 170));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(295, 483, 103, 49);
		contentPane.add(btnCancel);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCancel.setBackground(new Color(102, 205, 170));
		
		txtQty = new JTextField();
		txtQty.setBounds(200, 230, 198, 49);
		contentPane.add(txtQty);
		txtQty.setColumns(10);
		
		txtBQty = new JTextField();
		txtBQty.setBounds(200, 303, 198, 49);
		contentPane.add(txtBQty);
		txtBQty.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(200, 377, 198, 49);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
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
