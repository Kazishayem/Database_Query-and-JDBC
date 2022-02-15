// public class Alter {
    
// }

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class AlterTable {
	public static void main(String[] args) {
		System.out.println("Table Alter Example!");
		Connection con = null;
		String url = "jdbc:mysql://127.0.0.1:3306/";
		String dbName = "student_info";
		String driverName = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "1260";
		try {
			// Creating db connection
			Class.forName(driverName);
			con = DriverManager.getConnection(url + dbName, userName, password);
			try {
				Statement st = con.createStatement();
				String sql = "ALTER TABLE student_info add address varchar(30)";
				st.executeUpdate(sql);
				System.out.println("Table is altered successfully");
			} catch (SQLException s) {
				System.out.println(s);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
