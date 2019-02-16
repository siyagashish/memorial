import java.sql.*;

public class Sql {

	public Connection con;

	public Sql(String db){
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+db, "root", "ashish610");
			
		} catch(Exception e) {
			System.out.println("connection ERROR");
		}
	}
}
