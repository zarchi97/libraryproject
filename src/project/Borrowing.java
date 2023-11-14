package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Borrowing extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtBname;
	private JTextField txtDate;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Borrowing frame = new Borrowing();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Borrowing() {
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 535);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 160, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Borrow Book");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(166, 11, 317, 58);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("User Name:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(73, 121, 142, 48);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Book Name:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(73, 186, 142, 48);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Lend Date:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(73, 253, 142, 48);
		contentPane.add(lblNewLabel_1_2);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(258, 121, 219, 48);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		txtBname = new JTextField();
		txtBname.setColumns(10);
		txtBname.setBounds(258, 188, 219, 48);
		contentPane.add(txtBname);
		
		txtDate = new JTextField();
		txtDate.setColumns(10);
		txtDate.setBounds(258, 255, 219, 48);
		contentPane.add(txtDate);
		
		JButton btnBorrow = new JButton("Borrow");
		btnBorrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=txtUsername.getText();
				String bname=txtBname.getText();
				String date=txtDate.getText();
				  if (!name.equals("") && !bname.equals("") && !txtDate.getText().equals("")) {
						
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
									JOptionPane.showMessageDialog(btnBorrow, "Borrow Successful!");
									String query2="insert into borrowtable(username,bookname,date) values(?,?,?)";
									PreparedStatement ps = con.prepareStatement(query2);
									ps.setString(1, name);
									ps.setString(2, bname);
									ps.setString(3, date);
									
									ps.executeUpdate();
									
							}else {
								
								JOptionPane.showMessageDialog(btnBorrow, "This Book doesn't there!");
							
							}
							
							} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else {
						JOptionPane.showMessageDialog(btnBorrow, "Please fill complete information", "Warning Message", 1);
						}
				
				
			}
		});
		btnBorrow.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnBorrow.setBounds(100, 365, 115, 48);
		contentPane.add(btnBorrow);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCancel.setBounds(285, 365, 115, 48);
		contentPane.add(btnCancel);
	}

}
