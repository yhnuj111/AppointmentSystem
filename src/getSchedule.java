import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.management.Query;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;

public class getSchedule {
	public static ArrayList<ArrayList<String>> getS(){
		Connection con = null;
		try{
			con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Account", "root", "root");	
		} catch(SQLException ex){
			Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
		}
		ArrayList<ArrayList<String>> scheduleList = new ArrayList<ArrayList<String>>();
		ArrayList<String> inner ;

		try {		
			String query1 = "select year, month, day, meetPerson, time from PatientApp where username = ?";
			PreparedStatement statement = (PreparedStatement) con.prepareStatement(query1);
			statement.setString(1, UserLogin.username);
			
			ResultSet rs = statement.executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()){
				inner = new ArrayList<String>();
				for(int i = 0; i < columnCount; i++){
					inner.add(rs.getString(i+1));
				}
				scheduleList.add(inner);
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return scheduleList;
	}
	


}
