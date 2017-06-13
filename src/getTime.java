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

public class getTime {

	public static ArrayList<ArrayList<String>> getT(){
		Connection con = null;
		try{
			con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Account", "root", "root");	
		} catch(SQLException ex){
			Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
		}
		ArrayList<ArrayList<String>> timelist = new ArrayList<ArrayList<String>>();
		ArrayList<String> inner ;

		try {		
			String s = "select time from PatientApp where year = "+userCalendar.y+" and month = "+userCalendar.m+" and day = "+userCalendar.day;
			PreparedStatement st = (PreparedStatement) con.prepareStatement(s);
			ResultSet rs = st.executeQuery(s);
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()){
				inner = new ArrayList<String>();
				for(int i = 0; i < columnCount; i++){
					inner.add(rs.getString(i+1));
				}
				timelist.add(inner);
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return timelist;
	}
	public static ArrayList<ArrayList<String>> getAllTime(){
		Connection con = null;
		try{
			con = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/Account", "root", "root");	
		} catch(SQLException ex){
			Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
		}
		ArrayList<ArrayList<String>> appTime = new ArrayList<ArrayList<String>>();
		ArrayList<String> inner ;
		try {		
			String s = "select time from PatientApp where username = ?";
			PreparedStatement st = (PreparedStatement) con.prepareStatement(s);
			st.setString(1,UserLogin.username);
			ResultSet rs = st.executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int columnCount = rsmd.getColumnCount();
			while(rs.next()){
				inner = new ArrayList<String>();
				for(int i = 0; i < columnCount; i++){
					inner.add(rs.getString(i+1));
				}
				appTime.add(inner);
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return appTime;
	
	}
	

}
