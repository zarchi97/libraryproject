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
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JPasswordField pwd;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 585, 389);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Welcome to Library Management System");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(31, 11, 503, 46);
		contentPane.add(lblNewLabel);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblEmail.setBounds(118, 108, 129, 46);
		contentPane.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(257, 108, 218, 46);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPassword.setBounds(118, 178, 129, 46);
		contentPane.add(lblPassword);

		pwd = new JPasswordField();
		pwd.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		pwd.setEchoChar('*');
		pwd.setBounds(257, 176, 218, 48);
		contentPane.add(pwd);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				char password[] = pwd.getPassword();
				String pswd = String.valueOf(password);

				if (!email.equals("") && !pswd.equals("")) {

					String url = "jdbc:mysql://localhost:3306/mydb";
					try {
						Connection con = DriverManager.getConnection(url, "root", "root");
						Statement st = con.createStatement();
						String query = "select*from accountno";
						ResultSet rs = st.executeQuery(query);
						boolean flag = false;
						while (rs.next()) {
							String eml = rs.getString("email");
							String pwd = rs.getString("password");
							if (pwd.equals(pswd) && eml.equals(email)) {
								flag = true;
								break;
							}
						}
						if (flag) {
							if (pswd.equals("admin") && email.equals("admin@123")) {
                               JOptionPane.showMessageDialog(btnLogin, "Login Successful!");
								Welcome wel = new Welcome();
								wel.setVisible(true);
								dispose();

							} else {
								WelcomeforUser welco = new WelcomeforUser();
								welco.setVisible(true);
								dispose();
							}
						} else {
							JOptionPane.showMessageDialog(btnLogin, "Create New Acc", "Warning Message", 2);
							con.close();
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				else {
					JOptionPane.showMessageDialog(btnLogin, "Please fill complete information", "Warning Message", 1);
				}
			}
		});
		btnLogin.setBackground(new Color(30, 144, 255));
		btnLogin.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnLogin.setBounds(92, 271, 155, 52);
		contentPane.add(btnLogin);

		JButton btnNewUser = new JButton("New User");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = true;
				AccountCreate acc = new AccountCreate();
				acc.setVisible(true);
				dispose();
			}
		});
		btnNewUser.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnNewUser.setBackground(new Color(30, 144, 255));
		btnNewUser.setBounds(320, 271, 155, 52);
		contentPane.add(btnNewUser);

	}
}
