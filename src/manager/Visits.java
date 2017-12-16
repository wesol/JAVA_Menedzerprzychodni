package manager;

import java.io.FilterInputStream;
import java.util.Scanner;

import others.Doctor;
import others.Patient;

public class Visits {

	Scanner rl = new Scanner(new FilterInputStream(System.in) {
		@Override
		public void close() {
		}
	});
	DBConnector base = new DBConnector();
	Patient patient;
	Doctor doctor;

	private void visits(String[] queryF) {

		base.query(queryF[0], queryF[1], queryF[2], queryF[3], queryF[4], queryF[5]);

		outerLoop: while (true) {
			System.out.println("\n(Sortowanie wg: 'D'- daty, 'L'-lekarza, 'P'-pacjenta ,'Q'-wyjd� do menu)");
			String choice = rl.nextLine().toUpperCase();
			switch (choice) {
			case "D": {
				base.query(queryF[0] + " order by date, hour", queryF[1], queryF[2], queryF[3], queryF[4], queryF[5]);
				break;
			}
			case "L": {
				base.query(queryF[0] + " order by lekarz", queryF[1], queryF[2], queryF[3], queryF[4], queryF[5]);
				break;
			}
			case "P": {
				base.query(queryF[0] + " order by pacjent", queryF[1], queryF[2], queryF[3], queryF[4], queryF[5]);
				break;
			}

			case "Q":
				break outerLoop;
			default:
				System.out.println("Wybierz jedn� z dost�pnych opcji:");
			}
		}
	}

	public void doneVisits() {
		// zapytanie razem z formatowaniem bez sortowania
		String[] queryF = { "SELECT * FROM przeszle_wizyty", "%-5s%-15s%-10s%-25s%-25s", "Data", "Godzina", "Lekarz",
				"Pacjent" };
		visits(queryF);
	}

	public void futureVisits() {

		String[] queryF = { "SELECT * FROM przyszle_wizyty", "%-5s%-15s%-10s%-25s%-25s", "Data", "Godzina", "Lekarz",
				"Pacjent" };

		visits(queryF);
	}

	public void bookvisit() {
		String doctorName;
		String doctorLast;
		String patientPesel;
		String date;
		String hour;

		String choice_wew;

		outerLoop: do {

			while (true) {
				System.out.println("Podaj imie lekarza");
				doctorName = rl.nextLine();
				System.out.println("Podaj nazwisko lekarza");
				doctorLast = rl.nextLine();
				try {
					doctor = base.queryDoctorData(doctorName, doctorLast);
					doctor.getName().isEmpty();
					break;
				} catch (Exception e) {
					System.out.println(
							"Takiego lekarza nie ma w bazie, wprowadz ponownie lub wprowadz 'Q' aby przerwa� dodawanie wizyty");
					choice_wew = rl.nextLine().toUpperCase();
					if (choice_wew.equals("Q"))
						break outerLoop;
				}
			}

			while (true) {
				System.out.println("Podaj pesel pacjenta");
				patientPesel = rl.nextLine();
				try {
					patient = base.queryPatientData(patientPesel);
					patient.getName().isEmpty();
					break;
				} catch (Exception e) {
					System.out.println(
							"Takiego pacjenta nie ma w bazie, wprowadz ponownie lub wprowadz 'Q' aby przerwa� dodawanie wizyty");
					choice_wew = rl.nextLine().toUpperCase();
					if (choice_wew.equals("Q"))
						break outerLoop;
				}
			}

			while (true) {
				System.out.println("Podaj dat� w formacie: RRRR-MM-DD");
				date = rl.nextLine();
				if (date.length() < 9 || date.length() > 10)
					System.out.println("B�edna data");
				else
					break;
			}
			while (true) {
				System.out.println("Podaj godzin� w formacie: HH:MM (np 13:30)");
				hour = rl.nextLine();
				if (hour.length() < 3 || hour.length() > 5)
					System.out.println("B�edna godzina");
				else
					break;
			}

			System.out.println("Potwierd� wizyt�:\nLekarz:\t\t" + doctor.getName() + " " + doctor.getLast()
					+ "\nPacjent:\t" + patient.getName() + " " + patient.getName() + "\nData:\t" + date + " " + hour
					+ "\nPotwierdzenie i zapisanych danych - 'T', poprawienie wprowadzonych danych - ENTER lub wprowad� 'Q' aby wyj�� bez dodania lekarza");

			choice_wew = rl.nextLine().toUpperCase();

			if (choice_wew.equals("T")) {
				base.insert("insert into wizyty (date, time , id_l, id_p) values ('" + date + "', '" + hour + "', '"
						+ doctor.getID() + "', '" + patient.getID() + "')");
				System.out.println("Dodano wizyt�!\n");
			}

		} while (!(choice_wew.equals("Q") || choice_wew.equals("T")));

	}

}
