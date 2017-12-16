package manager;

import java.io.FilterInputStream;
import java.util.HashSet;
import java.util.Scanner;

public class DoctorsAdmin {
	
	Scanner rl = new Scanner(new FilterInputStream(System.in) {
		@Override
		public void close() {
		}
	});
	DBConnector base = new DBConnector();
	DoctorsList doctorsList = new DoctorsList();
	
	public void doctors() {
		outerLoop: while (true) {
			System.out.println(
					"'D' - Dodanie lekarza\n'U' - Usuniêcie lekarza\n'A' - Zaktualizowanie danych lekarza\n'L'- Lista lekarzy\n'Q' - Wyjœcie do poprzedniego menu");
			String choice = rl.nextLine().toUpperCase();
			switch (choice) {
			case "D": {
				String name = "";
				String last = "";
				String cellphone_number = "";
				String prefix = "";
				String choice_wew;
				String temp = "";
				do {
					System.out.println("Podaj imie lekarza" + prefix + name);
					temp = rl.nextLine();
					if (temp.equals("") && name.equals(""))
						name = "Nie wprowadzono";
					else if (!temp.equals(""))
						name = temp.substring(0, 1).toUpperCase() + temp.substring(1, temp.length()).toLowerCase();

					System.out.println("Podaj nazwisko lekarza" + prefix + last);
					temp = rl.nextLine();
					if (temp.equals("") && last.equals(""))
						last = "Nie wprowadzono";
					else if (!temp.equals(""))
						last = temp.substring(0, 1).toUpperCase() + temp.substring(1, temp.length()).toLowerCase();

					System.out.println("Podaj telefon lekarza" + prefix + cellphone_number);
					temp = rl.nextLine();
					if (temp.equals("") && cellphone_number.equals(""))
						cellphone_number = "Nie wprowadzono";
					else if (!temp.equals(""))
						cellphone_number = temp.substring(0, 1).toUpperCase()
								+ temp.substring(1, temp.length()).toLowerCase();

					System.out.println("Wprowadzone dane to:\nImiê:\t\t" + name + "\nNazwisko:\t" + last
							+ "\nTelefon:\t" + cellphone_number
							+ "\nPotwierdzenie i zapisanych danych - 'T', poprawienie wprowadzonych danych - ENTER lub wprowadŸ 'Q' aby wyjœæ bez dodania lekarza");

					choice_wew = rl.nextLine().toUpperCase();

					if (choice_wew.equals("T")) {
						base.insert("insert into lekarze (name, last , cellphone) values ('" + name + "', '" + last
								+ "', '" + cellphone_number + "')");
						System.out.println("Dodano do bazy lekarzy");
					}

					prefix = " lub wciœnij enter aby pozostawiæ uprzednio wprowadzone: ";

				} while (!(choice_wew.equals("Q") || choice_wew.equals("T")));

				break;
			}
			case "U": {
				String id;
				String choice_wew;
				base.query("Select * from lekarze", "%-5s%-15s%-15s%-15s", "Imiê", "Nazwisko", "Telefon");
				HashSet<String> zbior = base.zbior("Select * from lekarze");

				while (true) {
					System.out
							.println("\nPodaj ID lekarza którego chcesz usun¹æ lub 'Q' aby wyjœc do poprzedniego menu");
					id = rl.nextLine().toUpperCase();
					if (zbior.contains(id)) {
						System.out.println("\nCzy napewno chcesz usun¹æ:");
						base.query("Select * from lekarze where id_l = " + id, "%-5s%-15s%-15s%-15s", "Imiê",
								"Nazwisko", "Telefon");
						System.out.println("\nPotwierdŸ wpisuj¹c 'T' lub wciœnij dowolny przycisk aby anulowaæ\n");
						choice_wew = rl.nextLine().toUpperCase();

						if (choice_wew.equals("T")) {
							base.insert("Delete from lekarze where id_l =" + id);
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
				base.query("Select * from lekarze", "%-5s%-15s%-15s%-15s", "Imiê", "Nazwisko", "Telefon");
				HashSet<String> zbior = base.zbior("Select * from lekarze");

				while (true) {
					System.out.println(
							"\nPodaj ID lekarza którego dane chcesz zeedytowaæ lub 'Q' aby wyjœæ do poprzedniego menu");
					id = rl.nextLine().toUpperCase();

					if (id.equals("Q"))
						break;

					else if (zbior.contains(id)) {
						System.out.println("\nAktualizowany rekord:");
						base.query("Select * from lekarze where id_l = " + id, "%-5s%-15s%-15s%-15s", "Imiê",
								"Nazwisko", "Telefon");
						do {
							System.out.println(
									"\n'I'-Zmieñ imiê \n'N'-Zmieñ nazwisko \n'T'-Zmieñ telefon\n'Q'-Wyjœcie do poprzedniego menu");
							choice_wew = rl.nextLine().toUpperCase();
							switch (choice_wew) {
							case "I":
								base.update(rl, "imiê", id, "lekarze", "id_l", "name");
								break;
							case "N":
								base.update(rl, "nazwisko", id, "lekarze", "id_l", "last");
								break;
							case "T":
								base.update(rl, "numer telefonu", id, "lekarze", "id_l", "cellphone");
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
				doctorsList.list();
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
