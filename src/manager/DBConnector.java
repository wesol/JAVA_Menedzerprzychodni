package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;

public class DBConnector {
	private ResultSet rs;
	private static Connection con;
	private static Statement stmt;

	private static void connect() {
		try {
			// uwierzytelnianie sterownika
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/szpital?useSSL=false", "root",
					"123MWreaktor");
			stmt = con.createStatement();
		} catch (Exception e) {
			System.out.println("\n!!!B³¹d po³aczenia z baza danych:\n" + e);
		}
	}

	public void zapytanie(String query, String format, String naglowek2, String naglowek3) {

		connect();
		try {
			rs = stmt.executeQuery(query);
			System.out.printf(format, "ID", naglowek2, naglowek3);
			System.out.println();
			while (rs.next()) {
				// kolejnoœæ kolumn obs³ugiwana w zapytaniu
				System.out.printf(format, rs.getString(1), rs.getString(2), rs.getString(3));
				System.out.println();
			}

			con.close();
		} catch (SQLException e) {
			System.out.println("\n!!!B³¹d na zapytaniu1:\n" + e);
		}

	}

	public void zapytanie(String query, String format, String naglowek2, String naglowek3, String naglowek4) {

		connect();
		try {
			rs = stmt.executeQuery(query);
			System.out.printf(format, "ID", naglowek2, naglowek3, naglowek4);
			System.out.println();
			while (rs.next()) {
				// kolejnoœæ kolumn obs³ugiwana w zapytaniu
				System.out.printf(format, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				System.out.println();
			}

			con.close();
		} catch (SQLException e) {
			System.out.println("\n!!!B³¹d na zapytaniu2:\n" + e);
			;
		}

	}

	public void zapytanie(String query, String format, String naglowek2, String naglowek3, String naglowek4,
			String naglowek5) {

		connect();
		try {
			rs = stmt.executeQuery(query);
			System.out.printf(format, "ID", naglowek2, naglowek3, naglowek4, naglowek5);
			System.out.println();
			while (rs.next()) {
				// kolejnoœæ kolumn obs³ugiwana w zapytaniu
				System.out.printf(format, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				System.out.println();
			}

			con.close();
		} catch (SQLException e) {
			System.out.println("\n!!!B³¹d na zapytaniu3:\n" + e);
			
		}
	}
//===========================================================================================
//	update
	public void insert(String query) {
		connect();
		try {
			stmt.executeUpdate(query);
			con.close();
		} catch (SQLException e) {
			System.out.println("\n!!!B³¹d na insert:\n" + e);
			
		}
	}

	//===========================================================================================
		//	zapisywanie id-kow do zbioru
	public HashSet<String> zbior(String query) { 
		HashSet<String> zbior_id = new HashSet<>();
		connect();
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				zbior_id.add(rs.getString(1));
			}

			con.close();
		} catch (SQLException e) {
			System.out.println("\n!!!B³¹d przy tworzenie zbioru id\n" + e);
		}
		return zbior_id;
	}
	
}
