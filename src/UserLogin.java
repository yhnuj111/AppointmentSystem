import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
@SuppressWarnings("serial")
public class UserLogin extends JFrame {

	private JPanel contentPane;
	private JTextField username_field;
	private JTextField password_field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin();
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
	public UserLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		username_field = new JTextField();
		username_field.setBounds(209, 73, 158, 32);
		contentPane.add(username_field);
		username_field.setColumns(10);
		
		password_field = new JTextField();
		password_field.setBounds(209, 166, 158, 32);
		contentPane.add(password_field);
		password_field.setColumns(10);
		
		JLabel lblUserName = new JLabel("User name:");
		lblUserName.setBounds(81, 81, 79, 16);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(81, 174, 79, 16);
		contentPane.add(lblPassword);
		
		JButton btnPatientLogin = new JButton("Visitor Login");
		btnPatientLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Account", "root", "root");
					String name = username_field.getText();
					String password = password_field.getText();
					String query = "select * from PatientId where user_name=? and password=?";
					PreparedStatement statement = myConn.prepareStatement(query);
					statement.setString(1, name);
                    statement.setString(2, password);
                    ResultSet set = statement.executeQuery();
                    if(set.next()){
                    	JOptionPane.showMessageDialog(null, "Login successfully");
                    	UserInterface ui = new UserInterface();	                
                    	ui.setVisible(true);
                    	dispose();
                    	
                    }else{
                    	JOptionPane.showMessageDialog(null, "Invalid password or user name..");
                    }
				}catch(Exception exc){
					exc.printStackTrace();
				}
			}
		});
		btnPatientLogin.setBounds(40, 225, 117, 29);
		contentPane.add(btnPatientLogin);
		
		JButton btnDoctorLogin = new JButton("Insititution Login");
		btnDoctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Account", "root", "root");
					String name = username_field.getText();
					String password = password_field.getText();
					String query = "select * from DoctorIdInfo where username=? and password=?";
					PreparedStatement statement = myConn.prepareStatement(query);
					statement.setString(1, name);
                    statement.setString(2, password);
                    ResultSet set = statement.executeQuery();
                    if(set.next()){
                    	JOptionPane.showMessageDialog(null, "Login successfully");
                    	DoctorInterface di = new DoctorInterface();	                
                    	di.setVisible(true);
                    	dispose();
                    	
                    }else{
                    	JOptionPane.showMessageDialog(null, "Invalid password or user name..");
                    }
				}catch(Exception exc){
					exc.printStackTrace();
				}

			}
		});
		btnDoctorLogin.setBounds(169, 225, 133, 29);
		contentPane.add(btnDoctorLogin);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterPage rp = new RegisterPage();
				rp.setVisible(true);
				dispose();
			}
		});
		btnRegister.setBounds(314, 225, 117, 29);
		contentPane.add(btnRegister);
		
	}
}
