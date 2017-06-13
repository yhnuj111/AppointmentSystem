import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.Query;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class addDocApp {
	public static String doctorUser;
	public static String getDocUser(String s){
		return doctorUser = s;
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
	public static void addDoc(){
		Connection con = getConnection();
		PreparedStatement st;
		try {
			
			ArrayList<ArrayList<String>> alltimes = getTime.getAllTime();
			

			String doctorUsername = "";
			ArrayList<ArrayList<String>> docName = getDocAppName.getDocname();
			ArrayList<ArrayList<String>> patientName = getName.getPatientName();

			for(int i = 0; i < docName.size();i++){
				String allName = docName.get(i).get(0).toString().replace("[","").replace("]","");
				String [] sep = allName.split(",");
				String ln  = sep[0];
				String fn = sep[1];
				ln = ln.replaceAll("\\s+","");
				fn = fn.replaceAll("\\s+","");
				String query2 = "select username from DoctorIdInfo where lastname = ? and firstname = ? ";
				st = (PreparedStatement) con.prepareStatement(query2);
				st.setString(1, ln);
				st.setString(2, fn);
				ResultSet set1 = st.executeQuery();
				if(set1.next()){
					doctorUsername = set1.getString(1);
					getDocUser(doctorUsername);
				}

				


			}
			String pfn = patientName.get(0).get(1).toString().replace("[","").replace("]","");
			String pln = patientName.get(0).get(0).toString().replace("[","").replace("]","");

			String times = alltimes.get(alltimes.size()-1).toString().replace("[","").replace("]","");
			String query4 = "insert into DoctorApp(username,time,firstname,lastname) values(?,?,?,?)";		
			st = (PreparedStatement) con.prepareStatement(query4);
			st.setString(1, doctorUsername);
			st.setString(2, times);
			st.setString(3, pfn);
			st.setString(4, pln);
			int set2 = st.executeUpdate();
			if(set2 == 1){
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}



	}

}
