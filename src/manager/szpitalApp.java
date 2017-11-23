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
			System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n", "'U' - Um�wienie wizyty",
					"'W' - Wy�wietlenie przysz�ych wizyt", "'O' - Wy�wietlenie wizyt odbytych",
					"'L' - Wy�wietlenie bazy lekarzy", "'P' - Wy�wietlenie bazy pacjent�w",
					"'AL' - Narz�dzia administracyjne - lekarze", "'AP' - Narz�dzia administracyjne - pacjenci",
					"'Q' - Wyj�cie z programu");
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
				System.out.println("Wybierz jedn� z dost�pnych opcji:");
			}
		}

	}
}
