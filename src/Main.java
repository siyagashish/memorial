import java.sql.*;
import java.util.*;

public class Main {
	static Sql sql = new Sql("soldiers");

	static int node_no(String name) {

		int node_no = 0;
		try {
			Statement stmt = sql.con.createStatement();
			String q1 = "select * from data where name = '" + name + "'";
			ResultSet rs = stmt.executeQuery(q1);

			while (rs.next()) {
				System.out.print("Name at Circle: " + rs.getInt(2) + "\nArc: " + rs.getInt(3) + "\nColumn: "
						+ rs.getInt(4) + "\nRow: " + rs.getInt(5));
				if (rs.getInt(2) == 1) {
					node_no = rs.getInt(3);
				} else {
					node_no = 8 + rs.getInt(3) * 2;
					if (rs.getInt(4) <= 2) {
						node_no -= 1;
					}
				}
			}
			System.out.println();
		} catch (Exception e) {

		}
		return node_no;
	}

	
	public static void main(String[] args) {

		Graph g = new Graph();
		Scanner s = new Scanner(System.in);
		int temp =0;
		
		while (true) {
			System.out.println("Enter Name :");

			int temp1 = Main.node_no(s.next());
			g.shortest_path(temp, temp1);

			System.out.println("0 to terminate 1 to continue");
			if(s.nextInt()==0) {
				break;
			}
			temp = temp1;
			System.out.println();
		}
		s.close();
	}
}
