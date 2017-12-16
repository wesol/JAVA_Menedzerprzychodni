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
					"'D' - Dodanie pacjenta\n'U' - Usuni�cie pacjenta\n'A' - Zaktualizowanie danych pacjenta\n'L'- Lista pacjent�w\n'Q' - Wyj�cie do poprzedniej strony");
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

					System.out.println("Wprowadzone dane to:\nImi�:\t\t" + name + "\nNazwisko:\t" + last
							+ "\nTelefon:\t" + cellphone + "\nPesel:\t\t" + pesel
							+ "\nPotwierdzenie i zapisanych danych - 'T', poprawienie wprowadzonych danych - ENTER lub wprowad� 'Q' aby wyj�� bez dodania lekarza");

					choice_wew = rl.nextLine().toUpperCase();

					if (choice_wew.equals("T")) {
						base.insert("insert into pacjenci (name, last , cellphone, pesel) values ('" + name + "', '"
								+ last + "', '" + cellphone + "', '" + pesel + "')");
						System.out.println("Dodano do bazy pacjent�w");
					}

					prefix = " lub wci�nij enter aby pozostawi� uprzednio wprowadzone: ";

				} while (!(choice_wew.equals("Q") || choice_wew.equals("T")));

				break;
			}
			case "U": {
				String id;
				String choice_wew;
				base.query("Select * from pacjenci", "%-5s%-15s%-15s%-15s%-15s", "Imi�", "Nazwisko", "Telefon",
						"Pesel");
				HashSet<String> zbior = base.zbior("Select * from pacjenci");

				while (true) {
					System.out.println(
							"\nPodaj ID pacjenta kt�rego chcesz usun�� lub 'Q' aby wyj�c do poprzedniego menu");
					id = rl.nextLine().toUpperCase();
					if (zbior.contains(id)) {
						System.out.println("\nCzy napewno chcesz usun��:");
						base.query("Select * from pacjenci where id_p = " + id, "%-5s%-15s%-15s%-15s%-15s", "Imi�",
								"Nazwisko", "Telefon", "Pesel");
						System.out.println("\nPotwierd� wpisuj�c 'T' lub wci�nij dowolny przycisk aby anulowa�\n");
						choice_wew = rl.nextLine().toUpperCase();

						if (choice_wew.equals("T")) {
							base.insert("Delete from pacjenci where id_p =" + id);
							System.out.println("Usuni�to z bazy\n");
						} else
							System.out.println("Nie usuni�to\n");
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
				base.query("Select * from pacjenci", "%-5s%-15s%-15s%-15s%-15s", "Imi�", "Nazwisko", "Telefon",
						"Pesel");
				HashSet<String> zbior = base.zbior("Select * from pacjenci");

				while (true) {
					System.out.println(
							"\nPodaj ID pacjenta kt�rego dane chcesz zeedytowa� lub 'Q' aby wyj�� do poprzedniej strony");
					id = rl.nextLine().toUpperCase();

					if (id.equals("Q"))
						break;

					else if (zbior.contains(id)) {
						System.out.println("\nAktualizowany rekord:");
						base.query("Select * from pacjenci where id_l = " + id, "%-5s%-15s%-15s%-15s%-15s", "Imi�",
								"Nazwisko", "Telefon", "Pesel");
						do {
							System.out.println(
									"\n'I'-Zmie� imi� \n'N'-Zmie� nazwisko \n'T'-Zmie� telefon\n'P'-Zmie� pesel\n'Q'-Wyj�cie do poprzedniej strony");
							choice_wew = rl.nextLine().toUpperCase();
							switch (choice_wew) {
							case "I":
								base.update(rl, "imi�", id, "pacjenci", "id_l", "name");
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
								System.out.println("B��dne wprowadzenie");
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
				System.out.println("Wybierz jedn� z dost�pnych opcji:");
			}
		}

	}

}
