package others;

import java.util.Scanner;

import manager.DBConnector;

public class Aktualizowanie {
	
	public static void update(Scanner rl, DBConnector baza, String changedValueName, String id, String databaseTabelName, String databaseColumnNameId, String databaseUpdatedColumnName) {
		while (true) {
		System.out.println("Wprowad� "+ changedValueName +":");
		String temp = rl.nextLine();
		String temp_confirmed = temp.substring(0, 1).toUpperCase() + temp.substring(1, temp.length()).toLowerCase();
		System.out.println("Wprowadzono  "+ changedValueName +": '" + temp_confirmed +"'. Je�li potwiedzasz poprawno�� wprowad� 'Y', lub dowolny przycisk aby poprawi�."
				+ "\nJe�li chcesz wyj�� do poprzedniego menu bez zapisywania zmian wprowad� 'Q'");
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
