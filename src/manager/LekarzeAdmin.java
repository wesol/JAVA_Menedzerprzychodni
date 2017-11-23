package manager;

import java.util.HashSet;
import java.util.Scanner;

public class LekarzeAdmin {

	public static void lekarzeAdmin(DBConnector baza, Scanner rl) {

		outerLoop: while (true) {
			System.out.println(
					"'D'-dodanie lekarza\n'U'-usuni�cie lekarza\n'A'-zaktualizowanie danych lekarza\n'L'-Lista lekarzy\n'Q'-wyj�cie do poprzedniego menu");
			String choice = rl.nextLine().toUpperCase();
			switch (choice) {
			case "D": {
				String imie = "";
				String nazwisko = "";
				String tel = "";
				String przedrostek = "";
				String choice_wew;
				String temp = "";
				do {
					System.out.println("Podaj imie lekarza" + przedrostek + imie);
					temp = rl.nextLine();
					if (temp.equals("") && imie.equals(""))
						imie = "Nie wprowadzono";
					else if (!temp.equals(""))
						imie = temp.substring(0, 1).toUpperCase() + temp.substring(1, temp.length()).toLowerCase();

					System.out.println("Podaj nazwisko lekarza" + przedrostek + nazwisko);
					temp = rl.nextLine();
					if (temp.equals("") && nazwisko.equals(""))
						nazwisko = "Nie wprowadzono";
					else if (!temp.equals(""))
						nazwisko = temp.substring(0, 1).toUpperCase() + temp.substring(1, temp.length()).toLowerCase();

					System.out.println("Podaj telefon lekarza" + przedrostek + tel);
					temp = rl.nextLine();
					if (temp.equals("") && tel.equals(""))
						tel = "Nie wprowadzono";
					else if (!temp.equals(""))
						tel = temp.substring(0, 1).toUpperCase() + temp.substring(1, temp.length()).toLowerCase();

					System.out.println("Wprowadzone dane to:\nImi�:\t\t" + imie + "\nNazwisko:\t" + nazwisko
							+ "\nTelefon:\t" + tel
							+ "\nJe�li potwierdzasz poprawno�� danych wprowad� 'T' lub wprowad� 'Q' aby wyj�� bez dodania lekarza");

					choice_wew = rl.nextLine().toUpperCase();

					if (choice_wew.equals("T")) {
						baza.insert("insert into lekarze (name, last , cellphone) values ('" + imie + "', '" + nazwisko	+ "', '" + tel + "')");
						System.out.println("Dodano do bazy lekarzy");
					}

					przedrostek = " lub wci�nij enter aby pozostawi� uprzednio wprowadzone: ";

				} while (!(choice_wew.equals("Q") || choice_wew.equals("T")));

				break;
			}
			case "U": {
				String id_l;
				String choice_wew;
				baza.zapytanie("Select * from lekarze", "%-5s%-15s%-15s%-15s", "Imi�", "Nazwisko", "Telefon");
				HashSet<String> zbior = baza.zbior("Select * from lekarze");

				while (true) {
					System.out.println("\nPodaj ID lekarza kt�rego chcesz usun��:");
					id_l = rl.nextLine();
					if (zbior.contains(id_l))
						break;
					else
						System.out.println("Takiego ID nie ma w bazie!");
				}

				System.out.println("\nCzy napewno chcesz usun��:");
				baza.zapytanie("Select * from lekarze where id_l = " + id_l, "%-5s%-15s%-15s%-15s", "Imi�", "Nazwisko", "Telefon");
				System.out.println("\nPotwierd� wpisuj�c 'Y'\n");
				
				choice_wew =rl.nextLine().toUpperCase();
				if (choice_wew.equals("Y")) {
					baza.insert("Delete from lekarze where id_l =" + id_l);
					System.out.println("Usuni�to z bazy\n");
				}else
					System.out.println("Nie usuni�to\n");
				break;
			}
			case "A": {

				break;
			}
			case "L": {
				ListaLekarzy.lista(baza, rl);
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
