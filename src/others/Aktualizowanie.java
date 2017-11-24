package others;

import java.util.Scanner;

import manager.DBConnector;

public class Aktualizowanie {
	
	public static void update(Scanner rl, DBConnector baza, String changedValueName, String id, String databaseTabelName, String databaseColumnNameId, String databaseUpdatedColumnName) {
		while (true) {
		System.out.println("WprowadŸ "+ changedValueName +":");
		String temp = rl.nextLine();
		String temp_confirmed = temp.substring(0, 1).toUpperCase() + temp.substring(1, temp.length()).toLowerCase();
		System.out.println("Wprowadzono  "+ changedValueName +": '" + temp_confirmed +"'. Jeœli potwiedzasz poprawnoœæ wprowadŸ 'Y', lub dowolny przycisk aby poprawiæ."
				+ "\nJeœli chcesz wyjœæ do poprzedniego menu bez zapisywania zmian wprowadŸ 'Q'");
		String choice_wew = rl.nextLine().toUpperCase();
		if (choice_wew.equals("Y")) {
			baza.insert("Update "+ databaseTabelName +" set " + databaseUpdatedColumnName + " = '"+ temp_confirmed +"' where "+ databaseColumnNameId +" = " + id);
			
			System.out.println("Zaktualizowano rekord");
			break;
		}else if (choice_wew.equals("Q")) 				
			break;
		}
	}
}
