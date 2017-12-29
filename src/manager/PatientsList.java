package manager;

import java.io.FilterInputStream;
import java.sql.SQLException;
import java.util.Scanner;

public class PatientsList {

	Scanner rl = new Scanner(new FilterInputStream(System.in) {
		@Override
		public void close() {
		}
	});
	DBConnector base = new DBConnector();

	public void list() throws SQLException {
		base.query("Select * from pacjenci", "%-5s%-15s%-15s%-15s%-15s", "Imiê", "Nazwisko", "Telefon", "PESEL");

		outerLoop: while (true) {
			System.out.println("\n(Sortowanie po: 'N'-Nazwisku, 'I'-Imieniu, 'P'-Peselu ,'Q'-wyjdŸ do menu)");
			String choice = rl.nextLine().toUpperCase();
			switch (choice) {
			case "N": {
				base.query("Select * from pacjenci order by last", "%-5s%-15s%-15s%-15%-15ss", "Imiê", "Nazwisko",
						"Telefon", "PESEL");
				break;
			}
			case "I": {
				base.query("Select * from pacjenci order by name", "%-5s%-15s%-15s%-15s%-15s", "Imiê", "Nazwisko",
						"Telefon", "PESEL");
				break;
			}
			case "P": {
				base.query("Select * from pacjenci order by pesel", "%-5s%-15s%-15s%-15s%-15s", "Imiê", "Nazwisko",
						"Telefon", "PESEL");
				break;
			}

			case "Q":
				break outerLoop;
			default:
				System.out.println("Wybierz jedn¹ z dostêpnych opcji:");
			}
		}

	}

}
