package manager;

import java.util.Scanner;

public class Wizyty {

	private static void wizyty(String[] queryF, DBConnector baza, Scanner rl) {

		baza.zapytanie(queryF[0], queryF[1], queryF[2], queryF[3], queryF[4], queryF[5]);

		outerLoop: while (true) {
			System.out.println("\n(Sortowanie wg: 'D'- daty, 'L'-lekarza, 'P'-pacjenta ,'Q'-wyjdŸ do menu)");
			String choice = rl.nextLine().toUpperCase();
			switch (choice) {
			case "D": {
				baza.zapytanie(queryF[0] + " order by date, hour", queryF[1], queryF[2], queryF[3], queryF[4], queryF[5]);
				break;
			}
			case "L": {
				baza.zapytanie(queryF[0] + " order by lekarz", queryF[1], queryF[2], queryF[3], queryF[4], queryF[5]);
				break;
			}
			case "P": {
				baza.zapytanie(queryF[0] + " order by pacjent", queryF[1], queryF[2], queryF[3], queryF[4], queryF[5]);
				break;
			}

			case "Q":
				break outerLoop;
			default:
				System.out.println("Wybierz jedn¹ z dostêpnych opcji:");
			}
		}
	}

	public static void wizytyOdbyte(DBConnector baza, Scanner rl) {
		// zapytanie razem z formatowaniem bez sortowania
		String[] queryF = { "SELECT * FROM przeszle_wizyty", "%-5s%-15s%-10s%-25s%-25s", "Data", "Godzina", "Lekarz",
				"Pacjent" };
		wizyty(queryF, baza, rl);
	}

	public static void wizytyPrzyszle(DBConnector baza, Scanner rl) {

		String[] queryF = { "SELECT * FROM przyszle_wizyty", "%-5s%-15s%-10s%-25s%-25s", "Data", "Godzina", "Lekarz",
				"Pacjent" };

		wizyty(queryF, baza, rl);
	}

}
