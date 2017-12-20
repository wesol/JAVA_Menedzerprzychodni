package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Scanner;

import others.Doctor;
import others.Patient;

public class DBConnector {
	private ResultSet rs;
	private Connection con;
	private Statement stmt;

	private void connect() {
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

	public void query(String query, String format, String title2, String title3) throws SQLException {

		connect();

		try {
			rs = stmt.executeQuery(query);
			System.out.printf(format, "ID", title2, title3);
			System.out.println();
			while (rs.next()) {
				// kolejnoœæ kolumn obs³ugiwana w zapytaniu
				System.out.printf(format, rs.getString(1), rs.getString(2), rs.getString(3));
				System.out.println();
			}

		} catch (SQLException e) {
			System.out.println("\n!!!B³¹d na zapytaniu z czterema kolumnami (1):\n" + e);
		}
		con.close();
	}

	public Patient queryPatientData(String pesel) throws SQLException {
		Patient patient = new Patient();
		connect();

		try {
			rs = stmt.executeQuery("Select * from pacjenci where pesel='" + pesel + "'");
			if (rs.next()) {
				// kolejnoœæ kolumn obs³ugiwana w zapytaniu
				patient.setID(rs.getInt(1));
				patient.setName(rs.getString(2));
				patient.setLast(rs.getString(3));
				patient.setCellphone(rs.getString(4));
				patient.setPesel(rs.getString(5));
			}

		} catch (SQLException e) {
			System.out.println("\n!!!B³¹d na zapytaniu pacjent:\n" + e);
		}
		con.close();
		return patient;

	}

	public Doctor queryDoctorData(String doctorName, String doctorLast) throws SQLException {
		Doctor doctor = new Doctor();
		connect();

		try {
			rs = stmt.executeQuery(
					"Select * from lekarze where name='" + doctorName + "' and last='" + doctorLast + "'");
			if (rs.next()) {
				// kolejnoœæ kolumn obs³ugiwana w zapytaniu
				doctor.setID(rs.getInt(1));
				doctor.setName(rs.getString(2));
				doctor.setLast(rs.getString(3));
				doctor.setCellphone(rs.getString(4));
			}

		} catch (SQLException e) {
			System.out.println("\n!!!B³¹d na zapytaniu lekarz:\n" + e);
		}
		con.close();
		return doctor;

	}

	public void query(String query, String format, String title2, String title3, String title4) throws SQLException {

		connect();

		try {
			rs = stmt.executeQuery(query);
			System.out.printf(format, "ID", title2, title3, title4);
			System.out.println();
			while (rs.next()) {
				// kolejnoœæ kolumn obs³ugiwana w zapytaniu
				System.out.printf(format, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				System.out.println();
			}

		} catch (SQLException e) {
			System.out.println("\n!!!B³¹d na zapytaniu z piêcioma kolumnami (2):\n" + e);
		}
		con.close();

	}

	public void query(String query, String format, String title2, String title3, String title4, String title5)
			throws SQLException {

		connect();
		try {
			rs = stmt.executeQuery(query);
			System.out.printf(format, "ID", title2, title3, title4, title5);
			System.out.println();
			while (rs.next()) {
				// kolejnoœæ kolumn obs³ugiwana w zapytaniu
				System.out.printf(format, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5));
				System.out.println();
			}

		} catch (SQLException e) {
			System.out.println("\n!!!B³¹d na zapytaniu z 6-ioma kolumnami (3):" + e);

		}
		con.close();
	}

	// ===========================================================================================
	// wstawianie nowego rekordu/update
	public void insert(String query) throws SQLException {
		connect();
		try {
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			System.out.println("\nB³¹d na insert");

		}
		con.close();
	}
	
	public void delete(String query) throws SQLException {
		connect();
		try {
			stmt.executeUpdate(query);
			System.out.println("Usuniêto z bazy\n");
		} catch (SQLException e) {
			System.out.println("\nOsoba widnieje w relacji wizyt, nie mo¿na jej usun¹æ z bazy\n");

		}
		con.close();
	}

	// ===========================================================================================
	// zapisywanie id-kow do zbioru
	public HashSet<String> zbior(String query) throws SQLException {
		HashSet<String> zbior_id = new HashSet<>();
		this.connect();
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				zbior_id.add(rs.getString(1));
			}

		} catch (SQLException e) {
			System.out.println("\n!!!B³¹d przy tworzeniu zbioru id\n" + e);
		}
		con.close();
		return zbior_id;
	}

	public void update(Scanner rl, String changedValueName, String id, String databaseTabelName,
			String databaseColumnNameId, String databaseUpdatedColumnName) throws SQLException {
		while (true) {
			System.out.println("WprowadŸ " + changedValueName + ":");
			String temp = rl.nextLine();
			String temp_confirmed = temp.substring(0, 1).toUpperCase() + temp.substring(1, temp.length()).toLowerCase();
			System.out.println("Wprowadzono  " + changedValueName + ": '" + temp_confirmed
					+ "'.\n('Y'- Zatwierdzenie || dowolny przycisk aby poprawiæ || 'Q'- poprzedniego menu bez zapisywania zmian,dowolny przycisk aby poprawiæ.");
			String choice_wew = rl.nextLine().toUpperCase();
			if (choice_wew.equals("Y")) {
				connect();
				try {
					stmt.executeUpdate("Update " + databaseTabelName + " set " + databaseUpdatedColumnName + " = '"
							+ temp_confirmed + "' where " + databaseColumnNameId + " = " + id);
					con.close();
				} catch (SQLException e) {
					System.out.println("\n!!!B³¹d na insert:\n" + e);
				}
				System.out.println("Zaktualizowano rekord");
				con.close();
				break;

			} else if (choice_wew.equals("Q")) {
				con.close();
				break;
			}
		}
	}
}
