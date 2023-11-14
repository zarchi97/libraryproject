package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
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

public class AddBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtBName;
	private JTextField txtBAuthor;
	private JTextField txtQty;
	private JTextField txtPrice;
	private JTextField txtBorrow;
	private DefaultTableModel model;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBook frame = new AddBook();
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
	public AddBook() throws SQLException {
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1248, 689);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 160, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Books");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setBounds(152, 23, 371, 64);
		contentPane.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(245, 245, 245));
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel_1.setBounds(487, 149, 710, 492);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		model=new DefaultTableModel();
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 690, 470);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(188, 143, 143));
		scrollPane.setViewportView(table);
		
		table.setModel(model);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBackground(new Color(102, 205, 170));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(906, 92, 131, 46);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("<<<");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag=true;
				AdminManagement admin=new AdminManagement();
				admin.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_1.setBackground(new Color(102, 205, 170));
		btnNewButton_1.setBounds(1080, 94, 116, 46);
		contentPane.add(btnNewButton_1);
		
		txtBName = new JTextField();
		txtBName.setBounds(152, 149, 236, 53);
		contentPane.add(txtBName);
		txtBName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name:");
		lblNewLabel_1.setBounds(31, 147, 168, 53);
		contentPane.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		txtBAuthor = new JTextField();
		txtBAuthor.setBounds(152, 224, 236, 53);
		contentPane.add(txtBAuthor);
		txtBAuthor.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Book Author:");
		lblNewLabel_1_1.setBounds(31, 222, 168, 53);
		contentPane.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		txtQty = new JTextField();
		txtQty.setBounds(152, 298, 236, 53);
		contentPane.add(txtQty);
		txtQty.setColumns(10);
		
		JLabel lblNewLabel_1_2 = new JLabel("Qty:");
		lblNewLabel_1_2.setBounds(53, 296, 60, 53);
		contentPane.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JLabel lblNewLabel_1_3 = new JLabel("Price:");
		lblNewLabel_1_3.setBounds(53, 373, 74, 53);
		contentPane.add(lblNewLabel_1_3);
		lblNewLabel_1_3.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		JLabel lblNewLabel_1_4 = new JLabel("Borrow Qty:");
		lblNewLabel_1_4.setBounds(31, 445, 123, 53);
		contentPane.add(lblNewLabel_1_4);
		lblNewLabel_1_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		
		txtPrice = new JTextField();
		txtPrice.setBounds(152, 375, 236, 53);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		txtBorrow = new JTextField();
		txtBorrow.setBounds(152, 447, 236, 53);
		contentPane.add(txtBorrow);
		txtBorrow.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(116, 540, 97, 47);
		contentPane.add(btnAdd);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String bname = txtBName.getText();
				String bauthor = txtBAuthor.getText();
                if (!bname.equals("") && !bauthor.equals("") && !txtQty.getText().equals("") && !txtPrice.getText().equals("")) {
					int qty = Integer.valueOf(txtQty.getText());
					double price = Double.valueOf(txtPrice.getText());
					String bbook = txtBorrow.getText();
					int borrow=Integer.valueOf(bbook);
					String url = "jdbc:mysql://localhost:3306/mydb";
					try {
						Connection con = DriverManager.getConnection(url, "root", "root");
						Statement st=con.createStatement();
						String query1="select*from addbooks";
						ResultSet rs=st.executeQuery(query1);
						boolean flag=false;
						while(rs.next()) {
							String bkname=rs.getString("BookName");
							if(bname.equals(bkname)) {
								flag=true;
								break;
							}
						}if(flag) {
								JOptionPane.showMessageDialog(btnAdd, "There is a book with this name!");
						}else {
							String query2="insert into addbooks(BookName,Author,Qty,Price,BorrowQty) values(?,?,?,?,?)";
							PreparedStatement ps = con.prepareStatement(query2);
							ps.setString(1, bname);
							ps.setString(2, bauthor);
							ps.setInt(3, qty);
							ps.setDouble(4, price);
							ps.setInt(5, borrow);
							ps.executeUpdate();
							JOptionPane.showMessageDialog(btnAdd, "Add Book Successful!");
						
						}
						
						} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(btnAdd, "Please fill complete information", "Warning Message", 1);
					}
				}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnAdd.setBackground(new Color(102, 205, 170));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(278, 540, 97, 47);
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
