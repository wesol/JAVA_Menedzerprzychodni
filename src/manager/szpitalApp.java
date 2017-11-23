package manager;

import java.io.FilterInputStream;
import java.util.Scanner;

public class szpitalApp {

	public static void main(String[] args) {
		// obiekt do pobierania danych
		Scanner rl = new Scanner(new FilterInputStream(System.in) {
			@Override
			public void close() {
			}
		});

		DBConnector baza = new DBConnector();

		outerLoop: while (true) {
			System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n", "'U' - Umówienie wizyty",
					"'W' - Wyœwietlenie przysz³ych wizyt", "'O' - Wyœwietlenie wizyt odbytych",
					"'L' - Wyœwietlenie bazy lekarzy", "'P' - Wyœwietlenie bazy pacjentów",
					"'AL' - Narzêdzia administracyjne - lekarze", "'AP' - Narzêdzia administracyjne - pacjenci",
					"'Q' - Wyjœcie z programu");
			String choice = rl.nextLine().toUpperCase();

			switch (choice) {
			case "U": {
				/////
				
				
				///////
				break;
			}
			case "W": {
				Wizyty.wizytyPrzyszle(baza, rl);
				break;
			}
			case "O": {
				Wizyty.wizytyOdbyte(baza, rl);
				break;
			}
			case "L": {
				ListaLekarzy.lista(baza, rl);
				break;
			}
			case "P": {
				ListaPacjentow.lista(baza, rl);
				break;
			}
			case "AL": {
				LekarzeAdmin.lekarzeAdmin(baza, rl);
				break;
			}
			case "AP": {

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

	}
}
