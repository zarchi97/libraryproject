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
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ReturnBook extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtBname;
	private JTextField txtdate;
	private JTextField txtelp;
	private JTextField txtFine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBook frame = new ReturnBook();
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
	public ReturnBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 555);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Return Book");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(105, 11, 305, 44);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name:");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(83, 146, 137, 44);
		contentPane.add(lblNewLabel_1);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(239, 93, 184, 44);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnreturn = new JButton("Return Book");
		btnreturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String uname	=txtUsername.getText();
			String bname	=txtBname.getText();
			String rdate	=txtdate.getText();
			String elpday	=txtelp.getText();
			String fine	=txtFine.getText();
			String url="jdbc:mysql://localhost:3306/mydb";
			try {
				Connection con=DriverManager.getConnection(url,"root","root");
				String query="insert into returnbooks(username,bookname,returnbook,elp,fine) values(?,?,?,?,?)";
				PreparedStatement ps=con.prepareStatement(query);
				ps.setString(1, uname );
				ps.setString(2,bname);
				ps.setString(3,rdate );
				ps.setString(4, elpday);
				ps.setString(5, fine);
				
				int k=ps.executeUpdate();
				if(k==1) {
					JOptionPane.showMessageDialog(btnreturn, "Return Successful");
				}
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
				
				
			}
		});
		btnreturn.setBackground(new Color(100, 149, 237));
		btnreturn.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnreturn.setBounds(105, 436, 137, 44);
		contentPane.add(btnreturn);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCancel.setBackground(new Color(100, 149, 237));
		btnCancel.setBounds(281, 436, 137, 44);
		contentPane.add(btnCancel);
		
		txtBname = new JTextField();
		txtBname.setColumns(10);
		txtBname.setBounds(239, 148, 184, 44);
		contentPane.add(txtBname);
		
		txtdate = new JTextField();
		txtdate.setColumns(10);
		txtdate.setBounds(239, 207, 184, 44);
		contentPane.add(txtdate);
		
		txtelp = new JTextField();
		txtelp.setColumns(10);
		txtelp.setBounds(239, 267, 184, 44);
		contentPane.add(txtelp);
		
		txtFine = new JTextField();
		txtFine.setColumns(10);
		txtFine.setBounds(239, 325, 184, 44);
		contentPane.add(txtFine);
		
		JLabel lblNewLabel_1_1 = new JLabel("Book Name:");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(83, 91, 137, 44);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Book Name:");
		lblNewLabel_1_2.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_2.setBounds(83, 207, 137, 44);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Book Name:");
		lblNewLabel_1_3.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_3.setBounds(83, 267, 137, 44);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Book Name:");
		lblNewLabel_1_4.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_4.setBounds(83, 325, 137, 44);
		contentPane.add(lblNewLabel_1_4);
	}
}
