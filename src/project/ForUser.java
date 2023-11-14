package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ForUser extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForUser frame = new ForUser();
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
	public ForUser() {
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 456);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 160, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome To Our Library");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(140, 11, 388, 46);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("View Books");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag=true;
				ViewBook view = null;
				try {
					view = new ViewBook();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				view.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(new Color(102, 205, 170));
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewButton.setBounds(123, 118, 149, 58);
		contentPane.add(btnNewButton);
		
		JButton btnBorrowBook = new JButton("Borrow Book");
		btnBorrowBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag=true;
				Borrowing borrow = new Borrowing();
			    borrow.setVisible(true);
				dispose();
			}
		});
		btnBorrowBook.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnBorrowBook.setBackground(new Color(102, 205, 170));
		btnBorrowBook.setBounds(366, 118, 150, 58);
		contentPane.add(btnBorrowBook);
		
		JButton btnCalculateFine = new JButton("Calculate Fine");
		btnCalculateFine.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCalculateFine.setBackground(new Color(102, 205, 170));
		btnCalculateFine.setBounds(123, 238, 150, 58);
		contentPane.add(btnCalculateFine);
		
		JButton btnReturnBook = new JButton("Return Book");
		btnReturnBook.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnReturnBook.setBackground(new Color(102, 205, 170));
		btnReturnBook.setBounds(366, 238, 150, 58);
		contentPane.add(btnReturnBook);
	}

}
