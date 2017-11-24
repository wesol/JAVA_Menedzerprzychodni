package manager;

import java.util.HashSet;
import java.util.Scanner;

public class PacjenciAdmin {

	public static void pacjenciAdmin(DBConnector baza, Scanner rl) {

		outerLoop: while (true) {
			System.out.println(
					"'D' - Dodanie pacjenta\n'U' - Usuniêcie pacjenta\n'A' - Zaktualizowanie danych pacjenta\n'L'- Lista pacjentów\n'Q' - Wyjœcie do poprzedniego menu");
			String choice = rl.nextLine().toUpperCase();
			switch (choice) {
			case "D": {
				String imie = "";
				String nazwisko = "";
				String tel = "";
				String pesel = "";
				String przedrostek = "";
				String choice_wew;
				String temp = "";
				do {
					System.out.println("Podaj imie pacjenta" + przedrostek + imie);
					temp = rl.nextLine();
					if (temp.equals("") && imie.equals(""))
						imie = "Nie wprowadzono";
					else if (!temp.equals(""))
						imie = temp.substring(0, 1).toUpperCase() + temp.substring(1, temp.length()).toLowerCase();

					System.out.println("Podaj nazwisko pacjenta" + przedrostek + nazwisko);
					temp = rl.nextLine();
					if (temp.equals("") && nazwisko.equals(""))
						nazwisko = "Nie wprowadzono";
					else if (!temp.equals(""))
						nazwisko = temp.substring(0, 1).toUpperCase() + temp.substring(1, temp.length()).toLowerCase();

					System.out.println("Podaj telefon pacjenta" + przedrostek + tel);
					temp = rl.nextLine();
					if (temp.equals("") && tel.equals(""))
						tel = "Nie wprowadzono";
					else if (!temp.equals(""))
						tel = temp;
					System.out.println("Podaj pesel pacjenta" + przedrostek + pesel);
					temp = rl.nextLine();
					if (temp.equals("") && tel.equals(""))
						pesel = "Nie wprowadzono";
					else if (!temp.equals(""))
						pesel = temp;

					System.out.println("Wprowadzone dane to:\nImiê:\t\t" + imie + "\nNazwisko:\t" + nazwisko
							+ "\nTelefon:\t" + tel + "\nPesel:\t\t" + pesel
							+ "\nJeœli potwierdzasz poprawnoœæ danych wprowadŸ 'T' lub wprowadŸ 'Q' aby wyjœæ bez dodania pacjenta");

					choice_wew = rl.nextLine().toUpperCase();

					if (choice_wew.equals("T")) {
						baza.insert("insert into pacjenci (name, last , cellphone, pesel) values ('" + imie + "', '"
								+ nazwisko + "', '" + tel + "', '" + pesel + "')");
						System.out.println("Dodano do bazy pacjentów");
					}

					przedrostek = " lub wciœnij enter aby pozostawiæ uprzednio wprowadzone: ";

				} while (!(choice_wew.equals("Q") || choice_wew.equals("T")));

				break;
			}
			case "U": {
				String id;
				String choice_wew;
				baza.zapytanie("Select * from pacjenci", "%-5s%-15s%-15s%-15s%-15s", "Imiê", "Nazwisko", "Telefon",
						"Pesel");
				HashSet<String> zbior = baza.zbior("Select * from pacjenci");

				while (true) {
					System.out.println("\nPodaj ID pacjenta którego chcesz usun¹æ lub 'Q' aby wyjœc do poprzedniego menu");
					id = rl.nextLine().toUpperCase();
					if (zbior.contains(id)) {
						System.out.println("\nCzy napewno chcesz usun¹æ:");
						baza.zapytanie("Select * from pacjenci where id_p = " + id, "%-5s%-15s%-15s%-15s%-15s", "Imiê",
								"Nazwisko", "Telefon", "Pesel");
						System.out.println("\nPotwierdŸ wpisuj¹c 'T' lub wciœnij dowolny przycisk aby anulowaæ\n");
						choice_wew = rl.nextLine().toUpperCase();
						
						if (choice_wew.equals("T")) {
							baza.insert("Delete from pacjenci where id_p =" + id);
							System.out.println("Usuniêto z bazy\n");
						} else
							System.out.println("Nie usuniêto\n");
						break;
					
					}else if (id.equals("Q")) 
						break;
					
					else
						System.out.println("Takiego ID nie ma w bazie!");
				}
				break;
				
			}
			case "A": {
				String id;
				String choice_wew;
				baza.zapytanie("Select * from pacjenci", "%-5s%-15s%-15s%-15s%-15s", "Imiê", "Nazwisko", "Telefon",
						"Pesel");
				HashSet<String> zbior = baza.zbior("Select * from pacjenci");

				while (true) {
					System.out.println(
							"\nPodaj ID pacjenta którego dane chcesz zeedytowaæ lub 'Q' aby wyjœæ do poprzedniego menu");
					id = rl.nextLine().toUpperCase();

					if (id.equals("Q"))
						break;

					else if (zbior.contains(id)) {
						System.out.println("\nAktualizowany rekord:");
						baza.zapytanie("Select * from pacjenci where id_l = " + id, "%-5s%-15s%-15s%-15s%-15s", "Imiê",
								"Nazwisko", "Telefon", "Pesel");
						do {
							System.out.println(
									"\n'I'-Zmieñ imiê \n'N'-Zmieñ nazwisko \n'T'-Zmieñ telefon\n'P'-Zmieñ pesel\n'Q'-Wyjœcie do poprzedniego menu");
							choice_wew = rl.nextLine().toUpperCase();
							switch (choice_wew) {
							case "I":
								baza.update(rl, "imiê", id, "pacjenci", "id_l", "name");
								break;
							case "N":
								baza.update(rl, "nazwisko", id, "pacjenci", "id_l", "last");
								break;
							case "T":
								baza.update(rl, "numer telefonu", id, "pacjenci", "id_l", "cellphone");
								break;
							case "P":
								baza.update(rl, "pesel", id, "pacjenci", "id_l", "pesel");
								break;
							default:
								System.out.println("B³êdne wprowadzenie");
							}

						} while (choice_wew.equals("Q"));

					} else
						System.out.println("Takiego ID nie ma w bazie!");
				}

			}
			case "L": {
				ListaLekarzy.lista(baza, rl);
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
