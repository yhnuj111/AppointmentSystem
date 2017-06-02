import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class RegisterPage extends JFrame {

	private JPanel contentPane;
	private JTextField firstname_field;
	private JTextField lastname_field;
	private JTextField age;
	private JTextField contactnum;
	private JTextField username_field;
	private JTextField password_field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterPage frame = new RegisterPage();
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
	public RegisterPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		firstname_field = new JTextField();
		firstname_field.setBounds(150, 6, 130, 37);
		contentPane.add(firstname_field);
		firstname_field.setColumns(10);

		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(29, 16, 75, 16);
		contentPane.add(lblFirstName);

		lastname_field = new JTextField();
		lastname_field.setBounds(150, 55, 130, 37);
		contentPane.add(lastname_field);
		lastname_field.setColumns(10);

		age = new JTextField();
		age.setBounds(150, 104, 130, 37);
		contentPane.add(age);
		age.setColumns(10);

		contactnum = new JTextField();
		contactnum.setBounds(150, 153, 130, 37);
		contentPane.add(contactnum);
		contactnum.setColumns(10);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(29, 65, 75, 16);
		contentPane.add(lblLastName);

		JLabel lblAge = new JLabel("Age");
		lblAge.setBounds(29, 114, 61, 16);
		contentPane.add(lblAge);

		JLabel lblContactNumber = new JLabel("Contact number");
		lblContactNumber.setBounds(29, 163, 140, 16);
		contentPane.add(lblContactNumber);

		JLabel lblRegisterAs = new JLabel("Register as");
		lblRegisterAs.setBounds(319, 66, 90, 16);
		contentPane.add(lblRegisterAs);

		JRadioButton rdbtnPateint = new JRadioButton("Visitor");
		rdbtnPateint.setBounds(303, 94, 141, 23);
		contentPane.add(rdbtnPateint);

		JRadioButton rdbtnDoctor = new JRadioButton("Institution");
		rdbtnDoctor.setBounds(303, 129, 141, 23);
		contentPane.add(rdbtnDoctor);

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnPateint);
		bg.add(rdbtnDoctor);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnDoctor.isSelected()){
					try{
						Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Account", "root", "root");
						String firstname1 = firstname_field.getText();
						String lastname1 = lastname_field.getText();
						int age1 = Integer.parseInt(age.getText());
						int contact = Integer.parseInt(contactnum.getText());
						String username1 = username_field.getText();
						String password1 = password_field.getText();
					
						String query = "insert into DoctorIdInfo (lastname,firstname,username,password,age,contactNumber) values(?,?,?,?,?,?)";
						PreparedStatement statement = myConn.prepareStatement(query);
						statement.setString(1, lastname1);
						statement.setString(2, firstname1);
						statement.setString(3, username1);
						statement.setString(4, password1);
						statement.setInt(5, age1);
						statement.setInt(6, contact);
						
						int set = statement.executeUpdate();
	                    if(set == 1){
	                    	JOptionPane.showMessageDialog(null, "Account created!");
	                    	UserLogin ui = new UserLogin();	                
	                    	ui.setVisible(true);
	                    	dispose();
	                    	
	                    }else{
	                    	JOptionPane.showMessageDialog(null, "Please enter valid information!");
	                    }
					}catch(Exception exc){
						exc.printStackTrace();
					}
						
						
				}else if(rdbtnPateint.isSelected()){
					try{
						Connection myConn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Account", "root", "root");
						String firstname1 = firstname_field.getText();
						String lastname1 = lastname_field.getText();
						int age1 = Integer.parseInt(age.getText());
						int contact = Integer.parseInt(contactnum.getText());
						String username1 = username_field.getText();
						String password1 = password_field.getText();
						
						String query = "insert into PatientId (lastname,firstname,user_name,password,age,contactNumber) values(?,?,?,?,?,?)";
						PreparedStatement statement = myConn.prepareStatement(query);
						statement.setString(1, lastname1);
						statement.setString(2, firstname1);
						statement.setString(3, username1);
						statement.setString(4, password1);
						statement.setInt(5, age1);
						statement.setInt(6, contact);
						
						int set = statement.executeUpdate();
	                    if(set == 1){
	                    	JOptionPane.showMessageDialog(null, "Account created!");
	                    	UserLogin ui = new UserLogin();	                
	                    	ui.setVisible(true);
	                    	dispose();
	                    	
	                    }else{
	                    	JOptionPane.showMessageDialog(null, "Please enter valid information!");
	                    }
					}catch(Exception exc){
						exc.printStackTrace();
					}
				}

			}
		});
		btnSubmit.setBounds(319, 208, 117, 29);
		contentPane.add(btnSubmit);
		
		username_field = new JTextField();
		username_field.setBounds(150, 191, 130, 37);
		contentPane.add(username_field);
		username_field.setColumns(10);
		
		password_field = new JTextField();
		password_field.setBounds(150, 235, 130, 37);
		contentPane.add(password_field);
		password_field.setColumns(10);
		
		JLabel lblUserName = new JLabel("User name");
		lblUserName.setBounds(29, 201, 75, 16);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(29, 245, 61, 16);
		contentPane.add(lblPassword);
	}
}
