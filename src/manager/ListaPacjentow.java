package manager;

import java.util.Scanner;

public class ListaPacjentow {

	public static void lista(DBConnector baza, Scanner rl) {
		baza.zapytanie("Select * from pacjenci", "%-5s%-15s%-15s%-15s%-15s", "Imiê", "Nazwisko", "Telefon","PESEL");

		outerLoop: while (true) {
			System.out.println("\n(Sortowanie po: 'N'-Nazwisku, 'I'-Imieniu, 'P'-Peselu ,'Q'-wyjdŸ do menu)");
			String choice = rl.nextLine().toUpperCase();
			switch (choice) {
			case "N": {
				baza.zapytanie("Select * from pacjenci order by last", "%-5s%-15s%-15s%-15%-15ss", "Imiê", "Nazwisko",
						"Telefon","PESEL");
				break;
			}
			case "I": {
				baza.zapytanie("Select * from pacjenci order by name", "%-5s%-15s%-15s%-15s%-15s", "Imiê", "Nazwisko",
						"Telefon","PESEL");
				break;
			}
			case "P": {
				baza.zapytanie("Select * from pacjenci order by pesel", "%-5s%-15s%-15s%-15s%-15s", "Imiê", "Nazwisko",
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
