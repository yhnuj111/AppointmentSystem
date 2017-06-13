import java.awt.EventQueue;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.Query;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class changeSchedule extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					changeSchedule frame = new changeSchedule();
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
	public changeSchedule() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		DefaultListModel<String> listModel;
		listModel = new DefaultListModel<String>();

		getSchedule gs = new getSchedule();
		@SuppressWarnings("static-access")
		ArrayList<ArrayList<String>> allSchedule = gs.getS();

		for(int i = 0; i < allSchedule.size(); i++){
			listModel.addElement(allSchedule.get(i).toString().replace("[","").replace("]",""));
		}
		JList<String> list = new JList<String>(listModel);
		list.setBounds(127, 33, 303, 163);
		contentPane.add(list);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				int year = Integer.parseInt(allSchedule.get(index).get(0));
				int month = Integer.parseInt(allSchedule.get(index).get(1));
				int day = Integer.parseInt(allSchedule.get(index).get(2));
				String meet = allSchedule.get(index).get(3);
				String time = allSchedule.get(index).get(4);
				//connect to database
				Connection con = getConnection();


				try {
					String query = "delete from PatientApp where username = ? and year = ? and month = ? and day = ? and meetPerson = ? and time = ?";
					PreparedStatement st = (PreparedStatement) con.prepareStatement(query);
					st.setString(1, UserLogin.username);
					st.setInt(2, year);
					st.setInt(3, month);
					st.setInt(4, day);
					st.setString(5, meet);
					st.setString(6, time);
					int set = st.executeUpdate();

					//refresh
					if(set == 1){
						list.setModel(listModel);
						listModel.removeElementAt(index);
					}


				} catch (SQLException e1) {
					e1.printStackTrace();
				}



			}
		});
		btnCancel.setBounds(327, 226, 117, 29);
		contentPane.add(btnCancel);

		JLabel lblNewLabel = new JLabel("Your Schedule");
		lblNewLabel.setBounds(24, 33, 97, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userCalendar uc = new userCalendar();
				uc.setVisible(true);
				dispose();
			}
		});
		btnGoBack.setBounds(61, 226, 117, 29);
		contentPane.add(btnGoBack);
	}
}
