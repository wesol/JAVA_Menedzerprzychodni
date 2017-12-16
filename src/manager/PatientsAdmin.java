package manager;

import java.io.FilterInputStream;
import java.util.HashSet;
import java.util.Scanner;

public class PatientsAdmin {
	
	Scanner rl = new Scanner(new FilterInputStream(System.in) {
		@Override
		public void close() {
		}
	});
	DBConnector base = new DBConnector();
	PatientsList patientsList = new PatientsList();

	public void patients() {

		outerLoop: while (true) {
			System.out.println(
					"'D' - Dodanie pacjenta\n'U' - Usuniêcie pacjenta\n'A' - Zaktualizowanie danych pacjenta\n'L'- Lista pacjentów\n'Q' - Wyjœcie do poprzedniej strony");
			String choice = rl.nextLine().toUpperCase();
			switch (choice) {
			case "D": {
				String name = "";
				String last = "";
				String cellphone = "";
				String pesel = "";
				String prefix = "";
				String choice_wew;
				String temp = "";
				do {
					System.out.println("Podaj imie pacjenta" + prefix + name);
					temp = rl.nextLine();
					if (temp.equals("") && name.equals(""))
						name = "Nie wprowadzono";
					else if (!temp.equals(""))
						name = temp.substring(0, 1).toUpperCase() + temp.substring(1, temp.length()).toLowerCase();

					System.out.println("Podaj nazwisko pacjenta" + prefix + last);
					temp = rl.nextLine();
					if (temp.equals("") && last.equals(""))
						last = "Nie wprowadzono";
					else if (!temp.equals(""))
						last = temp.substring(0, 1).toUpperCase() + temp.substring(1, temp.length()).toLowerCase();

					System.out.println("Podaj telefon pacjenta" + prefix + cellphone);
					temp = rl.nextLine();
					if (temp.equals("") && cellphone.equals(""))
						cellphone = "Nie wprowadzono";
					else if (!temp.equals(""))
						cellphone = temp;
					System.out.println("Podaj pesel pacjenta" + prefix + pesel);
					temp = rl.nextLine();
					if (temp.equals("") && cellphone.equals(""))
						pesel = "Nie wprowadzono";
					else if (!temp.equals(""))
						pesel = temp;

					System.out.println("Wprowadzone dane to:\nImiê:\t\t" + name + "\nNazwisko:\t" + last
							+ "\nTelefon:\t" + cellphone + "\nPesel:\t\t" + pesel
							+ "\nPotwierdzenie i zapisanych danych - 'T', poprawienie wprowadzonych danych - ENTER lub wprowadŸ 'Q' aby wyjœæ bez dodania lekarza");

					choice_wew = rl.nextLine().toUpperCase();

					if (choice_wew.equals("T")) {
						base.insert("insert into pacjenci (name, last , cellphone, pesel) values ('" + name + "', '"
								+ last + "', '" + cellphone + "', '" + pesel + "')");
						System.out.println("Dodano do bazy pacjentów");
					}

					prefix = " lub wciœnij enter aby pozostawiæ uprzednio wprowadzone: ";

				} while (!(choice_wew.equals("Q") || choice_wew.equals("T")));

				break;
			}
			case "U": {
				String id;
				String choice_wew;
				base.query("Select * from pacjenci", "%-5s%-15s%-15s%-15s%-15s", "Imiê", "Nazwisko", "Telefon",
						"Pesel");
				HashSet<String> zbior = base.zbior("Select * from pacjenci");

				while (true) {
					System.out.println(
							"\nPodaj ID pacjenta którego chcesz usun¹æ lub 'Q' aby wyjœc do poprzedniego menu");
					id = rl.nextLine().toUpperCase();
					if (zbior.contains(id)) {
						System.out.println("\nCzy napewno chcesz usun¹æ:");
						base.query("Select * from pacjenci where id_p = " + id, "%-5s%-15s%-15s%-15s%-15s", "Imiê",
								"Nazwisko", "Telefon", "Pesel");
						System.out.println("\nPotwierdŸ wpisuj¹c 'T' lub wciœnij dowolny przycisk aby anulowaæ\n");
						choice_wew = rl.nextLine().toUpperCase();

						if (choice_wew.equals("T")) {
							base.insert("Delete from pacjenci where id_p =" + id);
							System.out.println("Usuniêto z bazy\n");
						} else
							System.out.println("Nie usuniêto\n");
						break;

					} else if (id.equals("Q"))
						break;

					else
						System.out.println("Takiego ID nie ma w bazie!");
				}
				break;

			}
			case "A": {
				String id;
				String choice_wew;
				base.query("Select * from pacjenci", "%-5s%-15s%-15s%-15s%-15s", "Imiê", "Nazwisko", "Telefon",
						"Pesel");
				HashSet<String> zbior = base.zbior("Select * from pacjenci");

				while (true) {
					System.out.println(
							"\nPodaj ID pacjenta którego dane chcesz zeedytowaæ lub 'Q' aby wyjœæ do poprzedniej strony");
					id = rl.nextLine().toUpperCase();

					if (id.equals("Q"))
						break;

					else if (zbior.contains(id)) {
						System.out.println("\nAktualizowany rekord:");
						base.query("Select * from pacjenci where id_l = " + id, "%-5s%-15s%-15s%-15s%-15s", "Imiê",
								"Nazwisko", "Telefon", "Pesel");
						do {
							System.out.println(
									"\n'I'-Zmieñ imiê \n'N'-Zmieñ nazwisko \n'T'-Zmieñ telefon\n'P'-Zmieñ pesel\n'Q'-Wyjœcie do poprzedniej strony");
							choice_wew = rl.nextLine().toUpperCase();
							switch (choice_wew) {
							case "I":
								base.update(rl, "imiê", id, "pacjenci", "id_l", "name");
								break;
							case "N":
								base.update(rl, "nazwisko", id, "pacjenci", "id_l", "last");
								break;
							case "T":
								base.update(rl, "numer telefonu", id, "pacjenci", "id_l", "cellphone");
								break;
							case "P":
								base.update(rl, "pesel", id, "pacjenci", "id_l", "pesel");
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
				patientsList.list();
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
