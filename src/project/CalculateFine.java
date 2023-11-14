package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class CalculateFine extends JFrame {

	private JPanel contentPane;
	private JTextField txtFine;
	private JButton btnCalculate;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculateFine frame = new CalculateFine();
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
	public CalculateFine() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblFine = new JLabel("Calculate Fine");
		lblFine.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblFine.setForeground(new Color(0, 0, 0));
		lblFine.setHorizontalAlignment(SwingConstants.CENTER);
		lblFine.setBounds(83, 11, 356, 36);
		contentPane.add(lblFine);
		
		JLabel lblbN = new JLabel("Book Name:");
		lblbN.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblbN.setForeground(new Color(0, 0, 0));
		lblbN.setBounds(96, 89, 168, 44);
		contentPane.add(lblbN);
		
		txtFine = new JTextField();
		txtFine.setBounds(259, 90, 217, 47);
		contentPane.add(txtFine);
		txtFine.setColumns(10);
		
		btnCalculate = new JButton("Calculate Fine");
		btnCalculate.setBackground(new Color(100, 149, 237));
		btnCalculate.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCalculate.setBounds(97, 181, 152, 44);
		contentPane.add(btnCalculate);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		btnCancel.setBackground(new Color(100, 149, 237));
		btnCancel.setBounds(324, 181, 152, 44);
		contentPane.add(btnCancel);
	}
}
