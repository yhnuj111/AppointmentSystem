import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.management.Query;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class makeAppointment extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					makeAppointment frame = new makeAppointment();
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static Connection getConnection(){
		Connection con = null;
		try{
			con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Account", "root", "root");	
		} catch(SQLException ex){
			Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
		}
		return con;
	}



	/**
	 * Create the frame.
	 */
	public makeAppointment() {
		setTitle("Appointment System");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 532, 327);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblChooseAMeeting = new JLabel("Choose a meeting time");
		lblChooseAMeeting.setBounds(35, 35, 152, 16);
		contentPane.add(lblChooseAMeeting);

		String[] messageStrings = {"9:00am - 9:30 am","10:00am - 11:30am","1:00pm - 1:30pm", "2:00pm - 2:30 pm", "3:00pm - 3:30pm", "4:00pm - 4:30pm"};
		JComboBox<?> comboBox = new JComboBox<Object>(messageStrings);

		//String time = String.valueOf(comboBox.getSelectedItem());
		//	int index = Integer.valueOf(comboBox.getSelectedIndex());
		comboBox.setBounds(199, 31, 165, 27);
		getTime timel = new getTime();
		@SuppressWarnings("static-access")
		ArrayList<ArrayList<String>> timelist = timel.getT();

		for(int i = 0; i <= messageStrings.length-1; i++){
			for(int j = 0; j < timelist.size(); j++){
				if(messageStrings[i].equals(timelist.get(j).toString().replace("[","").replace("]",""))){
					comboBox.removeItem(messageStrings[i]);
					contentPane.add(comboBox);
				}else{
					contentPane.add(comboBox);
				}
			}
		}
		contentPane.add(comboBox);





		JLabel lblChooseThePerson = new JLabel("Choose the person to meet");
		lblChooseThePerson.setBounds(35, 74, 177, 16);
		contentPane.add(lblChooseThePerson);

		getName names = new getName();
		@SuppressWarnings("static-access")
		ArrayList<ArrayList<String>> allname = names.getUserName();
		JComboBox<ArrayList<String>> comboBox_1 = new JComboBox<ArrayList<String>>();
		comboBox_1.setBounds(224, 70, 140, 27);
		for(int i = 0; i < allname.size();i++){
			comboBox_1.addItem(allname.get(i));
		}
		contentPane.add(comboBox_1);



		JLabel lblMeetingReason = new JLabel("Meeting reason");
		lblMeetingReason.setBounds(35, 128, 105, 16);
		contentPane.add(lblMeetingReason);



		textField = new JTextField();
		textField.setBounds(152, 128, 187, 86);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				Connection con = getConnection();
				String username = UserLogin.username;
				int day = userCalendar.day;
				int year = userCalendar.y;
				int month = userCalendar.m;

				String meet = String.valueOf(comboBox_1.getSelectedItem());
				String time = String.valueOf(comboBox.getSelectedItem());


				try {

					String query1 = "select lastname, firstname from PatientId where user_name = ?";
					PreparedStatement statement = (PreparedStatement) con.prepareStatement(query1);
					statement.setString(1, username);
					ResultSet set = statement.executeQuery();

					if(set.next()){	
						String lastname = set.getString(2);
						String firstname = set.getString(1);
						String reason = textField.getText();

						String query2 = "insert into PatientApp(username,lastname,firstname,year,month,day,meetPerson,time,reason) values(?,?,?,?,?,?,?,?,?)";
						PreparedStatement st = (PreparedStatement) con.prepareStatement(query2);
						st.setString(1, username);
						st.setString(2, lastname);
						st.setString(3, firstname);
						st.setInt(4, year);
						st.setInt(5, month);
						st.setInt(6, day);
						st.setString(7, meet);
						st.setString(8, time);
						st.setString(9, reason);
						int rs = st.executeUpdate();
						if(rs == 1){
							JOptionPane.showMessageDialog(null, "Appointment created successfully");
							userCalendar uc = new userCalendar();
							uc.setVisible(true);
							dispose();
						}
					}

				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				addDocApp ad = new addDocApp();
				ad.addDoc();
				

			}
		});
		btnSubmit.setBounds(319, 249, 117, 29);
		contentPane.add(btnSubmit);

		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userCalendar uc = new userCalendar();
				uc.setVisible(true);
				dispose();
			}
		});
		btnGoBack.setBounds(60, 249, 117, 29);
		contentPane.add(btnGoBack);
	}

}
