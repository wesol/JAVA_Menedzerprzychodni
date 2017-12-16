package manager;

import java.io.FilterInputStream;
import java.util.Scanner;

public class DoctorsList {
	
	Scanner rl = new Scanner(new FilterInputStream(System.in) {
		@Override
		public void close() {
		}
	});
	DBConnector base = new DBConnector();

	public void list() {
		base.query("Select * from lekarze", "%-5s%-15s%-15s%-15s", "Imi�", "Nazwisko", "Telefon");

		outerLoop: while (true) {
			System.out.println("\n(Sortowanie po: 'N'-Nazwisku, 'I'-Imieniu, 'Q'-wyjd� do poprzedniego menu)");
			String choice = rl.nextLine().toUpperCase();
			switch (choice) {
			case "N": {
				base.query("Select * from lekarze order by last", "%-5s%-15s%-15s%-15s", "Imi�", "Nazwisko", "Telefon");
				break;
			}
			case "I": {
				base.query("Select * from lekarze order by name", "%-5s%-15s%-15s%-15s", "Imi�", "Nazwisko", "Telefon");
				break;
			}

			case "Q":
				break outerLoop;
			default:
				System.out.println("Wybierz jedn� z dost�pnych opcji:");
			}
		}

	}

}
