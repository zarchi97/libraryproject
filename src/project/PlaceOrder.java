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
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class PlaceOrder extends JFrame {

	private JPanel contentPane;
	private JTextField txtBName;
	private JTextField txtUsername;
	private JTextField txtQty;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlaceOrder frame = new PlaceOrder();
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
	public PlaceOrder() {
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 556);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 160, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Place Order");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(141, 11, 280, 52);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(63, 94, 135, 42);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("UserName:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(63, 159, 135, 42);
		contentPane.add(lblNewLabel_1_1);
		
		txtBName = new JTextField();
		txtBName.setBounds(206, 96, 199, 42);
		contentPane.add(txtBName);
		txtBName.setColumns(10);
		
		txtUsername = new JTextField();
		txtUsername.setColumns(10);
		txtUsername.setBounds(206, 161, 199, 42);
		contentPane.add(txtUsername);
		
		JButton btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String bname = txtBName.getText();
				String uname=txtUsername.getText();
				String quantity=txtQty.getText();
				int qty = Integer.valueOf(quantity);
				String pr=txtPrice.getText();
				double price=Double.valueOf(pr);
				if (!bname.equals("") && !txtQty.getText().equals("") && !uname.equals("") && !txtPrice.getText().equals("") ) {
					String url="jdbc:mysql://localhost:3306/mydb";
					try {
						Connection con=DriverManager.getConnection(url,"root","root");
						Statement st=con.createStatement();
						String query="select * from addbooks";
						ResultSet rs=st.executeQuery(query);
						boolean flag=false;
						while(rs.next()) {
							String bkname=rs.getString(2);
							if(bname.equals(bkname)) {
								flag=true;
								break;
							}
						}
						if(flag) {
						String query1="insert into addorder(bookname,username,qty,price) values(?,?,?,?)";
						PreparedStatement ps=con.prepareStatement(query1);
						ps.setString(1, bname);
						ps.setString(2, uname);
						ps.setInt(3, qty);
						ps.setDouble(4, price*qty);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(btnPlaceOrder,"Add Order Successful!" );
						txtBName.setText("");
						txtUsername.setText("");
						txtQty.setText("");
						txtPrice.setText("");
					}else {
						JOptionPane.showMessageDialog(btnPlaceOrder, "This book doesn't exist!");
					}
						
					}catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                     
                    
				}else {
					JOptionPane.showMessageDialog(btnPlaceOrder, "Please fill complete information", "Warning Message", 1);
				}
			
				
				
			}
		});
		btnPlaceOrder.setBackground(new Color(102, 205, 170));
		btnPlaceOrder.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnPlaceOrder.setBounds(85, 431, 135, 42);
		contentPane.add(btnPlaceOrder);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBName.setText("");
				txtUsername.setText("");
				txtQty.setText("");
				txtPrice.setText("");	
				
			}
		});
		btnCancel.setBackground(new Color(102, 205, 170));
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCancel.setBounds(257, 431, 105, 42);
		contentPane.add(btnCancel);
		
		txtQty = new JTextField();
		txtQty.setColumns(10);
		txtQty.setBounds(206, 223, 199, 42);
		contentPane.add(txtQty);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(206, 284, 199, 42);
		contentPane.add(txtPrice);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Price:");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(63, 284, 135, 42);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Qty:");
		lblNewLabel_1_1_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_1_2.setBounds(63, 223, 135, 42);
		contentPane.add(lblNewLabel_1_1_2);
		
		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnClose.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnClose.setBackground(new Color(102, 205, 170));
		btnClose.setBounds(395, 431, 105, 42);
		contentPane.add(btnClose);
	}
}
