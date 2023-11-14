package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class AdminManagement extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminManagement frame = new AdminManagement();
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
	public AdminManagement() {
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 460);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 160, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Add Books");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag=true;
				AddBook add = null;
				try {
					add = new AddBook();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				add.setVisible(true);
				dispose();
				}
		});
		btnNewButton.setBackground(new Color(102, 205, 170));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(99, 147, 162, 55);
		contentPane.add(btnNewButton);
		
		JButton btnDeleteBooks = new JButton("Delete Books");
		btnDeleteBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag=true;
				DeleteBooks de = null;
				try {
					de = new DeleteBooks();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				de.setVisible(true);
				dispose();
			}
		});
		btnDeleteBooks.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDeleteBooks.setBackground(new Color(102, 205, 170));
		btnDeleteBooks.setBounds(348, 147, 156, 55);
		contentPane.add(btnDeleteBooks);
		
		JButton btnViewOrder = new JButton("View Borrowing");
		btnViewOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag=true;
				BorrowTable borrow = null;
				try {
					borrow = new BorrowTable();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				borrow.setVisible(true);
				dispose();
			
			}
		});
		btnViewOrder.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnViewOrder.setBackground(new Color(102, 205, 170));
		btnViewOrder.setBounds(348, 285, 156, 55);
		contentPane.add(btnViewOrder);
		
		JButton btnUpdateBooks = new JButton("Update Books");
		btnUpdateBooks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag=true;
				UpdateBooks up = null;
				try {
					up = new UpdateBooks();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				up.setVisible(true);
				dispose();
			}
		});
		btnUpdateBooks.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnUpdateBooks.setBackground(new Color(102, 205, 170));
		btnUpdateBooks.setBounds(99, 285, 162, 55);
		contentPane.add(btnUpdateBooks);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 599, 77);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Welcome Our LMS");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(154, 0, 273, 77);
		panel.add(lblNewLabel_1);
	}
}
