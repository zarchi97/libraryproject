package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.cj.xdevapi.PreparableStatement;
import com.mysql.cj.xdevapi.Schema.CreateCollectionOptions;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class AccountCreate extends JFrame {

	private JPanel contentPane;
	private JTextField txtCName;
	private JTextField txtCPhone;
	private JTextField txtCEmail;
	private JPasswordField pwd;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountCreate frame = new AccountCreate();
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
	public AccountCreate() {
		setTitle("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 606);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Create New Account");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel.setBounds(97, 11, 555, 75);
		contentPane.add(lblNewLabel);

		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(new Color(0, 0, 0));
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblName.setBounds(153, 125, 113, 59);
		contentPane.add(lblName);

		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setForeground(new Color(0, 0, 0));
		lblPhoneNumber.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPhoneNumber.setBounds(126, 195, 136, 59);
		contentPane.add(lblPhoneNumber);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(new Color(0, 0, 0));
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblEmail.setBounds(153, 265, 99, 59);
		contentPane.add(lblEmail);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(0, 0, 0));
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblPassword.setBounds(142, 335, 99, 59);
		contentPane.add(lblPassword);

		txtCName = new JTextField();
		txtCName.setBounds(272, 126, 286, 59);
		contentPane.add(txtCName);
		txtCName.setColumns(10);

		txtCPhone = new JTextField();
		txtCPhone.setColumns(10);
		txtCPhone.setBounds(272, 196, 286, 59);
		contentPane.add(txtCPhone);

		txtCEmail = new JTextField();
		txtCEmail.setColumns(10);
		txtCEmail.setBounds(272, 266, 286, 59);
		contentPane.add(txtCEmail);

		pwd = new JPasswordField();
		pwd.setBounds(272, 341, 286, 53);
		contentPane.add(pwd);

		JRadioButton rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setBackground(new Color(0, 139, 139));
		buttonGroup.add(rdbtnAdmin);
		rdbtnAdmin.setFont(new Font("Times New Roman", Font.BOLD, 18));
		rdbtnAdmin.setBounds(178, 413, 159, 53);
		contentPane.add(rdbtnAdmin);

		JRadioButton rdbtnUser = new JRadioButton("User");
		rdbtnUser.setBackground(new Color(0, 139, 139));
		buttonGroup.add(rdbtnUser);
		rdbtnUser.setFont(new Font("Times New Roman", Font.BOLD, 18));
		rdbtnUser.setBounds(377, 413, 159, 53);
		contentPane.add(rdbtnUser);

		JButton btnCA = new JButton("Create Account");
		btnCA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String cname = txtCName.getText();
				String cemail = txtCEmail.getText();
				char password[] = pwd.getPassword();
				String pswd = String.valueOf(password);
				String role;
				if (rdbtnAdmin.isSelected()) {
					role = "Admin";
				} else {
					role = "User";
				}
				if (!cemail.equals("") && !cname.equals("") && !txtCPhone.getText().equals("") && !pswd.equals("")
						&& !role.equals("")) {
					int cphone = Integer.valueOf(txtCPhone.getText());
					String url = "jdbc:mysql://localhost:3306/mydb";
					try {
						Connection con = DriverManager.getConnection(url, "root", "root");
						Statement st = con.createStatement();
						String query = "select*from accountno";
						ResultSet rs = st.executeQuery(query);
						boolean flag = false;
						while (rs.next()) {
							String name = rs.getString("name");
							int ph = rs.getInt("phone");
							String email = rs.getString("email");
							String pwd = rs.getString("password");
							String rl = rs.getString("role");
							if (!(cname.equals(name) && cemail.equals(email))) {
								flag = true;
								break;
							}

						}
						if (flag) {
							String query1 = "insert into accountno(name,phone,email,password,role) values(?,?,?,?,?)";
							PreparedStatement ps = con.prepareStatement(query1);
							ps.setString(1, cname);
							ps.setInt(2, cphone);
							ps.setString(3, cemail);
							ps.setString(4, pswd);
							ps.setString(5, role);
							ps.executeUpdate();

							Login login = new Login();
							login.setVisible(true);
							dispose();
						} else {
							JOptionPane.showMessageDialog(btnCA, "User exist! \n Try another one");
							con.close();

						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(btnCA, "Please fill complete information", "Warning Message", 1);
				}
			}

		});
		btnCA.setBackground(new Color(30, 144, 255));
		btnCA.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCA.setBounds(157, 489, 159, 46);
		contentPane.add(btnCA);

		JButton btnCCancel = new JButton("Cancel");
		btnCCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtCName.setText("");
				txtCPhone.setText("");
				txtCEmail.setText("");
				pwd.setText("");

			}
		});
		btnCCancel.setBackground(new Color(30, 144, 255));
		btnCCancel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCCancel.setBounds(409, 489, 159, 46);
		contentPane.add(btnCCancel);

	}
}
