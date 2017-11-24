package manager;

import java.util.HashSet;
import java.util.Scanner;

public class PacjenciAdmin {

	public static void pacjenciAdmin(DBConnector baza, Scanner rl) {

		outerLoop: while (true) {
			System.out.println(
					"'D' - Dodanie pacjenta\n'U' - Usuni�cie pacjenta\n'A' - Zaktualizowanie danych pacjenta\n'L'- Lista pacjent�w\n'Q' - Wyj�cie do poprzedniego menu");
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

					System.out.println("Wprowadzone dane to:\nImi�:\t\t" + imie + "\nNazwisko:\t" + nazwisko
							+ "\nTelefon:\t" + tel + "\nPesel:\t\t" + pesel
							+ "\nJe�li potwierdzasz poprawno�� danych wprowad� 'T' lub wprowad� 'Q' aby wyj�� bez dodania pacjenta");

					choice_wew = rl.nextLine().toUpperCase();

					if (choice_wew.equals("T")) {
						baza.insert("insert into pacjenci (name, last , cellphone, pesel) values ('" + imie + "', '"
								+ nazwisko + "', '" + tel + "', '" + pesel + "')");
						System.out.println("Dodano do bazy pacjent�w");
					}

					przedrostek = " lub wci�nij enter aby pozostawi� uprzednio wprowadzone: ";

				} while (!(choice_wew.equals("Q") || choice_wew.equals("T")));

				break;
			}
			case "U": {
				String id;
				String choice_wew;
				baza.zapytanie("Select * from pacjenci", "%-5s%-15s%-15s%-15s%-15s", "Imi�", "Nazwisko", "Telefon",
						"Pesel");
				HashSet<String> zbior = baza.zbior("Select * from pacjenci");

				while (true) {
					System.out.println("\nPodaj ID pacjenta kt�rego chcesz usun�� lub 'Q' aby wyj�c do poprzedniego menu");
					id = rl.nextLine().toUpperCase();
					if (zbior.contains(id)) {
						System.out.println("\nCzy napewno chcesz usun��:");
						baza.zapytanie("Select * from pacjenci where id_p = " + id, "%-5s%-15s%-15s%-15s%-15s", "Imi�",
								"Nazwisko", "Telefon", "Pesel");
						System.out.println("\nPotwierd� wpisuj�c 'T' lub wci�nij dowolny przycisk aby anulowa�\n");
						choice_wew = rl.nextLine().toUpperCase();
						
						if (choice_wew.equals("T")) {
							baza.insert("Delete from pacjenci where id_p =" + id);
							System.out.println("Usuni�to z bazy\n");
						} else
							System.out.println("Nie usuni�to\n");
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
				baza.zapytanie("Select * from pacjenci", "%-5s%-15s%-15s%-15s%-15s", "Imi�", "Nazwisko", "Telefon",
						"Pesel");
				HashSet<String> zbior = baza.zbior("Select * from pacjenci");

				while (true) {
					System.out.println(
							"\nPodaj ID pacjenta kt�rego dane chcesz zeedytowa� lub 'Q' aby wyj�� do poprzedniego menu");
					id = rl.nextLine().toUpperCase();

					if (id.equals("Q"))
						break;

					else if (zbior.contains(id)) {
						System.out.println("\nAktualizowany rekord:");
						baza.zapytanie("Select * from pacjenci where id_l = " + id, "%-5s%-15s%-15s%-15s%-15s", "Imi�",
								"Nazwisko", "Telefon", "Pesel");
						do {
							System.out.println(
									"\n'I'-Zmie� imi� \n'N'-Zmie� nazwisko \n'T'-Zmie� telefon\n'P'-Zmie� pesel\n'Q'-Wyj�cie do poprzedniego menu");
							choice_wew = rl.nextLine().toUpperCase();
							switch (choice_wew) {
							case "I":
								baza.update(rl, "imi�", id, "pacjenci", "id_l", "name");
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
								System.out.println("B��dne wprowadzenie");
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
				System.out.println("Wybierz jedn� z dost�pnych opcji:");
			}
		}

	}

}
