package manager;

import java.util.Scanner;

public class ListaLekarzy {

	public static void lista(DBConnector baza, Scanner rl) {
		baza.zapytanie("Select * from lekarze", "%-5s%-15s%-15s%-15s", "Imiê", "Nazwisko", "Telefon");

		outerLoop: while (true) {
			System.out.println("\n(Sortowanie po: 'N'-Nazwisku, 'I'-Imieniu, 'Q'-wyjdŸ do poprzedniego menu)");
			String choice = rl.nextLine().toUpperCase();
			switch (choice) {
			case "N": {
				baza.zapytanie("Select * from lekarze order by last", "%-5s%-15s%-15s%-15s", "Imiê", "Nazwisko",
						"Telefon");
				break;
			}
			case "I": {
				baza.zapytanie("Select * from lekarze order by name", "%-5s%-15s%-15s%-15s", "Imiê", "Nazwisko",
						"Telefon");
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
