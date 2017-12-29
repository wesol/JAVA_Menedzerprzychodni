package manager;

import java.io.FilterInputStream;
import java.sql.SQLException;
import java.util.Scanner;

public class HospitalApp {

	public static void main(String[] args) throws SQLException {
		Scanner rl = new Scanner(new FilterInputStream(System.in) {
			@Override
			public void close() {
			}
		});

		Visits visits = new Visits();
		PatientsList patientsList = new PatientsList();
		DoctorsList doctorsList = new DoctorsList();
		PatientsAdmin patientsAdmin = new PatientsAdmin();
		DoctorsAdmin doctorsAdmin = new DoctorsAdmin();

		outerLoop: while (true) {
			System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n", "'U' - Umówienie wizyty",
					"'W' - Wyœwietlenie przysz³ych wizyt", "'O' - Wyœwietlenie wizyt odbytych",
					"'L' - Wyœwietlenie bazy lekarzy", "'P' - Wyœwietlenie bazy pacjentów",
					"'AL' - Narzêdzia administracyjne - lekarze", "'AP' - Narzêdzia administracyjne - pacjenci",
					"'Q' - Wyjœcie z programu");
			String choice = rl.nextLine().toUpperCase();

			switch (choice) {
			case "U": {
				visits.bookvisit();
				break;
			}
			case "W": {
				visits.futureVisits();
				break;
			}
			case "O": {
				visits.doneVisits();
				break;
			}
			case "L": {
				doctorsList.list();
				break;
			}
			case "P": {
				patientsList.list();
				break;
			}
			case "AL": {
				doctorsAdmin.doctors();
				break;
			}
			case "AP": {
				patientsAdmin.patients();
				break;
			}
			case "Q": {
				System.out.println("Do widzenia");
				break outerLoop;
			}
			default:
				System.out.println("Wybierz jedn¹ z dostêpnych opcji:");
			}
		}
		rl.close();
	}
}
