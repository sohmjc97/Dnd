package Dnd;

import java.util.ArrayList;

public class monsterEditor extends encounterEditor{
	/*
	 * Runs a UI for editing Monsters within Encounters 
	 */
	
	private static Monster m_enemy = null; 
	
	/*
	 * Main function of the Enemy Editor. 
	 */
	public static void edit () {
			
		System.out.println("----- Enemy Editor -----");
		System.out.println("Which Enemy would you like to edit?");
		
		boolean done = false;
		do {
			listEnemyChoices(); 
			System.out.println("Type the number of the Enemy you want to edit.");
			try {
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > (m_encounter.get_enemies().size() + 2)) {
					System.out.println(OutOfRangeException);
				}
				else {
					done = parseEnemyChoices(a); 
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from:  " + e);
				scanner.next(); 
			}
		} while (done == false);
		
	}
	
	/*
	 * Lists options for which Enemy to edit. Gives an option to create a new Enemy as well. 
	 */
	private static void listEnemyChoices () {
		
		String output = ""; 
		int enemyCount = m_encounter.get_enemies().size(); 
		System.out.println("Enemy Count: " + enemyCount);
		
		output = output + "1) " + "Return to Encounter Editor \n";
		output = output + "2) " + "Add new Enemy \n";
		if (enemyCount >= 1) {
			output = output + "3) " + m_encounter.get_enemies().get(0).get_name() +"\n";
		}
		if (enemyCount >= 2) {
			output = output + "4) " + m_encounter.get_enemies().get(1).get_name()+ "\n";
		}
		if (enemyCount >= 3) {
			output = output + "5) " + m_encounter.get_enemies().get(2).get_name() + "\n";
		}
		if (enemyCount >= 4) {
			output = output + "6) " + m_encounter.get_enemies().get(3).get_name() + "\n";
		}
		if (enemyCount >= 5) {
			output = output + "7) " + m_encounter.get_enemies().get(4).get_name() + "\n";
		}
		if (enemyCount >= 6) {
			output = output + "8) " + m_encounter.get_enemies().get(5).get_name() + "\n";
		}
		/*
		 * Add additional lines if you plan on having an Encounter with more than 6 enemies 
		 */
		System.out.println(output);  
		
	}
	
	
	/*
	 * Decides which Enemy to edit based on the User input 
	 */
	private static boolean parseEnemyChoices (int choice) {
		
		boolean done = false;
		switch (choice) {
		
		case 1:
			System.out.println("Returning to Encounter Editor...");
			done = true;
			break;
		case 2:
			scanner.nextLine();
			System.out.println("What should the new enemy be called?");
			String name = scanner.nextLine();
			m_enemy = m_encounter.add_enemy(name);
			break;
		case 3:
			m_enemy = m_encounter.get_enemies().get(0);
			getEnemyEdits(); 
			break;
		case 4:
			m_enemy = m_encounter.get_enemies().get(1);
			getEnemyEdits(); 
			break;
		case 5:
			m_enemy = m_encounter.get_enemies().get(2);
			getEnemyEdits(); 
			break;
		case 6:
			m_enemy = m_encounter.get_enemies().get(3);
			getEnemyEdits(); 
			break;
		case 7:
			m_enemy = m_encounter.get_enemies().get(4);
			getEnemyEdits(); 
			break;
		case 8:
			m_enemy = m_encounter.get_enemies().get(5);
			getEnemyEdits(); 
			break;
		}
		return done; 
		
	}
	
	/*
	 * Gets user input on which of the Monster's attributes should be edited
	 */
	private static void getEnemyEdits() {
		
		boolean done = false;
		do {
			try {
				listEnemyEdits();
				System.out.println("Type the number of the attribute you want to edit.");
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > 9) {
					System.out.println(OutOfRangeException);
				}
				else {
					done = parseEnemyEdits(a);
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from:  " + e);
				scanner.next();
			}
		} while (done == false);
	
	}
	
	/*
	 * Lists options for editing Monster attributes 
	 */
	private static void listEnemyEdits () {
		System.out.println("What changes do you want to make to " + m_enemy.get_name() + "?");
		String output = "";
		output = output + "1) Name (Unavailable) \n";
		output = output + "2) Description \n";
		output = output + "3) Host \n";
		output = output + "4) Stats \n";
		output = output + "5) Items \n";
		output = output + "6) View Enemy Details \n";
		output = output + "7) Delete Enemy \n";
		output = output + "8) Save Enemy \n";
		output = output + "9) Return to Enemy Selection \n";
		System.out.println(output); 
	}
	
	
	/*
	 * Decides which attributes to edit base don the User's input
	 */
	private static boolean parseEnemyEdits (int choice) {
		
		boolean done = false;
		switch (choice) {
		
		case 1: 
			System.out.println("Name editing is not yet available.");
			break;
		case 2:
			scanner.nextLine(); 
			System.out.println("What would you like the new description to be?");
			String descrip = scanner.nextLine(); 
			m_enemy.set_description(descrip);
			System.out.println("The new description has been set to :");
			System.out.println(m_enemy.get_description());
		case 3:
			System.out.println("This enemy belongs to the " + m_encounter.get_name() + " encoutner.");
			break;
		case 4:
			parseEnemyStats();
			break;
		case 5:
			editItems();
			break;
		case 6:
			System.out.println(m_enemy.get_all_stats());
			break;
		case 7:
			done = deleteEnemy();
			break;
		case 8:
			saveEnemy();
			break;
		case 9:
			System.out.println("Returning to Enemy Selection...");
			done = true;
			break; 
		
		}
		return done; 
		
	}
	
	/*
	 * List the options for modifying Monster's stats
	 */
	private static void listEnemyStats() {
		String output = ""; 
		output = output + "---Enemy Stats---\n";
		output = output + "1) Level: " + m_enemy.get_lvl() + "\n";
		output = output + "2) HP: " + m_enemy.get_hp() + "\n";
		output = output + "3) AC: " + m_enemy.get_ac() + "\n";
		output = output + "4) XP: " + m_enemy.get_xp() + "\n";
		output = output + "5) Attack Mod: " + m_enemy.m_attackMod + "\n";
		output = output + "6) Damage Die: " + m_enemy.get_dmgDie() + "\n";
		output = output + "7) Number of Damage Die: " + m_enemy.get_numdmgDie() + "\n";
		output = output + "8) Damage Mod: " + m_enemy.get_dmgMod() + "\n";
		output = output + "9) Ability Mods: \n";
		for (String i: m_enemy.m_abilityMods.keySet()) {
			output = output + i + ": " + m_enemy.m_abilityMods.get(i) + "\n";
		}
		output = output + "10) Return to Previous Menu \n";

		System.out.println(output);
	}
	
	/*
	 * Gets user input on which of the Monster's stats should be modified
	 */
	private static void parseEnemyStats () {
		
		boolean done = false;
		do {
			listEnemyStats();
			System.out.println("What stat would you like to change?");
			try {
				int a = scanner.nextInt();
				if (a < 0) {
					System.out.println(OutOfRangeException);
				}
				else if (a > 10) {
					System.out.println(OutOfRangeException);
				}
				else {
					done = parseEnemyStatsChoice(a); 
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from:  " + e);
				scanner.next(); 
			}
			
		} while (done == false);
		
	}
	
	/*
	 * Decides which edits to make to the Monster's stats based on user input. 
	 */
	private static boolean parseEnemyStatsChoice(int choice) {
		
		boolean done = false; 
		switch (choice) {
		
		case 1: 
			editLvl();
			break;
		case 2:
			editHP();
			break;
		case 3:
			editAC();
			break;
		case 4:
			editXP();
			break;
		case 5:
			editAM();
			break;
		case 6:
			editDmgDie();
			break;
		case 7:
			editNumDmgDie();
			break;
		case 8:
			editDmgMod();
			break;
		case 9:
			editAbilityMods(); 
			break;
		case 10:
			done = true;
			break; 
		
		}
		return done; 
		
	}
	
	/*
	 * Gets user input on what the Monster's Level should be.
	 */
	private static void editLvl() {
		
		boolean done = false; 
		do {
			System.out.println("What would you like to set " + m_enemy.get_name() + "'s level to?");
			try {
				int a = scanner.nextInt();
				if (a < 0) {
					System.out.println(OutOfRangeException);
				}
				else if (a > 40) {
					System.out.println(OutOfRangeException);
				}
				else {
					m_enemy.set_lvl(a);
					done = true; 
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from:  " + e);
				scanner.next(); 
			}
			
		} while (done == false);
		
	}
	
	/*
	 * Gets user input on what the Monster's Max Hit Points should be.
	 */
	private static void editHP() {
		
		boolean done = false; 
		do {
			System.out.println("What would you like to set " + m_enemy.get_name() + "'s HP to?");
			try {
				int a = scanner.nextInt();
				if (a < 0) {
					System.out.println(OutOfRangeException);
				}
				else if (a > 1000) {
					System.out.println(OutOfRangeException);
				}
				else {
					m_enemy.set_hp(a);
					done = true; 
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from:  " + e);
				scanner.next(); 
			}
			
		} while (done == false);
		
	}
	
	/*
	 * Gets user input on what the Monster's Armor Class should be.
	 */
	private static void editAC() {
		
		boolean done = false; 
		do {
			System.out.println("What would you like to set " + m_enemy.get_name() + "'s AC to?");
			try {
				int a = scanner.nextInt();
				if (a < 0) {
					System.out.println(OutOfRangeException);
				}
				else if (a > 100) {
					System.out.println(OutOfRangeException);
				}
				else {
					m_enemy.set_ac(a);
					done = true; 
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from:  " + e);
				scanner.next(); 
			}
			
		} while (done == false);
		
	}
	
	/*
	 * Gets user input on which of the Monster's Ability Scores the Attack Modifier should come from.
	 */
	private static void editAM() {
		
		boolean done = false; 
		do {
			scanner.nextLine();
			System.out.println("What Ability Score is " + m_enemy.get_name() + "'s Attack Mod from?");
			try {
				String a = scanner.nextLine(); 
				switch (a) {
					case "STR":
						m_enemy.set_attackMod(a);
						done = true;
						break;
					case "DEX":
						m_enemy.set_attackMod(a);
						done = true;
						break;
					case "WIS":
						m_enemy.set_attackMod(a);
						done = true;
						break;
					case "INT":
						m_enemy.set_attackMod(a);
						done = true;
						break;
					case "CON":
						m_enemy.set_attackMod(a);
						done = true;
						break;
					case "CHA":
						m_enemy.set_attackMod(a);
						done = true;
						break; 
				}
				if (!done) {
					System.out.println("Answer must be either STR, DEX, WIS, INT, CON, or CHA.");
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from:  " + e);
				scanner.next(); 
			}
			
		} while (done == false);
		
	}
	
	/*
	 * Gets user input on how much XP the Monster gives out upon its defeat 
	 */
	private static void editXP() {
		
		boolean done = false; 
		do {
			System.out.println("What would you like to set " + m_enemy.get_name() + "'s XP to?");
			try {
				int a = scanner.nextInt();
				if (a < 0) {
					System.out.println(OutOfRangeException);
				}
				else if (a > 1000000) {
					System.out.println(OutOfRangeException);
				}
				else {
					m_enemy.set_xp(a);
					done = true; 
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from:  " + e);
				scanner.next(); 
			}
			
		} while (done == false);
		
	}
	
	/*
	 * Gets user input on what the Monster's damage Die should be.
	 */
	private static void editDmgDie() {
		
		boolean done = false; 
		do {
			System.out.println("How many sides does " + m_enemy.get_name() + "'s Damage Die have?");
			try {
				int a = scanner.nextInt();
				if (a < 0) {
					System.out.println(OutOfRangeException);
				}
				else if (a > 500) {
					System.out.println(OutOfRangeException);
				}
				else {
					m_enemy.set_dmgDie(a);
					done = true; 
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from:  " + e);
				scanner.next(); 
			}
			
		} while (done == false);
		
	}
	
	/*
	 * Gets user input on how many damage Die the Monster should have .
	 */
	private static void editNumDmgDie() {
		
		boolean done = false; 
		do {
			System.out.println("How many damage die can " + m_enemy.get_name() + " roll?");
			try {
				int a = scanner.nextInt();
				if (a < 0) {
					System.out.println(OutOfRangeException);
				}
				else if (a > 500) {
					System.out.println(OutOfRangeException);
				}
				else {
					m_enemy.set_numdmgDie(a);
					done = true; 
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from:  " + e);
				scanner.next(); 
			}
			
		} while (done == false);
		
	}
	
	/*
	 * Gets user input on what the Monster's Damage Modifier should be.
	 */
	private static void editDmgMod() {
		
		boolean done = false; 
		do {
			System.out.println("What is " + m_enemy.get_name() + "'s damage Mod?");
			try {
				int a = scanner.nextInt();
				if (a < 0) {
					System.out.println(OutOfRangeException);
				}
				else if (a > 500) {
					System.out.println(OutOfRangeException);
				}
				else {
					m_enemy.set_dmgMod(a);
					done = true; 
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from:  " + e);
				scanner.next(); 
			}
			
		} while (done == false);
		
	}
	
	/*
	 * Gets user input on what the Monster's Ability Modifiers should be.
	 */
	private static void editAbilityMods() {
		
		boolean done = false; 
		do {
			System.out.println("What are " + m_enemy.get_name() + "'s Ability Mods?");
			try {

				System.out.println("STR: ");
				int str = scanner.nextInt();
				
				System.out.println("DEX: ");
				int dex = scanner.nextInt();
				
				System.out.println("WIS: ");
				int wis = scanner.nextInt();
				
				System.out.println("INT: ");
				int intl = scanner.nextInt();
				
				System.out.println("CON: ");
				int con = scanner.nextInt();
				
				System.out.println("CHA: ");
				int cha = scanner.nextInt();
				
				m_enemy.set_AbilityMods(str, dex, wis, intl, con, cha);
				done = true; 
				
			}
			catch(Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from:  " + e);
				scanner.next(); 
			}
			
		} while (done == false);
		
	}
	
	/*
	 * Gets User input on what items to add to the Monster's inventory and what items to delete
	 * from the Monster's inventory. 
	 */
	private static void editItems() {
		
		boolean done = false;
		do {
			
			System.out.println("Do you want to add any items? (Yes = 1/ No = 0)");
			try {
				int a = scanner.nextInt();
				if (a == 1) {
					scanner.nextLine();
					System.out.println("Name the Item: ");
					String name = scanner.nextLine();
					System.out.println("Describe the Item: ");
					String descrip = scanner.nextLine();
					m_enemy.add_items(name, descrip);
					System.out.println(name + " was successfully added to " + m_enemy.get_name() + "'s inventory.");
				}
				else if (a == 0) {
					System.out.println("No further items will be added.");
					done = true; 
				}
				else {
					System.out.println(OneOrZeroException);
				}
			}
			catch(Exception e) {
				System.out.println(OneOrZeroException);
				//System.out.println("Error resulting from:  " + e);
				scanner.next();
			}
			
		} while (done == false);
		
		deleteItems();
	}
	
	/*
	 * Internal function of editItems that verifies and deletes items that the User
	 * no longer wants in this Monster's inventory. 
	 */
	private static void deleteItems() {
		
		boolean done = false; 
		do {
			
			if (m_enemy.get_items().isEmpty()) {
				done = true;
				System.out.println(m_enemy.get_name() + "'s inventory is empty.");
				continue; 
			}
			
			ArrayList<String> items = new ArrayList<String>(); 
			System.out.println("Do you want to delete any items? (Yes = 1/ No = 0)");
			try {
				int a = scanner.nextInt();
				if (a == 1) {
					int i = 1;
					for (String item: m_enemy.m_ItemDrop.keySet()) {
						System.out.println(i + ") " + item);
						items.add(item);
						i++; 
					}
					
					System.out.println("Type the number of the item you wish to delete: ");
					
					try {
						
						int n = scanner.nextInt(); 
						if (n < 1) {
							System.out.println(OutOfRangeException);
						}
	
						else if (n > items.size()) {
							System.out.println(OutOfRangeException);
						}
						else {
							String thing = items.get(n-1);
							m_enemy.get_items().remove(thing);
							System.out.println(thing + " was successfully deleted.");
						}
					}
					catch (Exception e) {
							//
					}
				}
				else if (a == 0) {
					System.out.println("No further items will be deleted.");
					done = true; 
				}
				else {
					System.out.println(OneOrZeroException);
				}
			}
			catch (Exception e) {
				System.out.println(OutOfRangeException);
				//System.out.println("Error resulting from:  " + e);
				scanner.next();
			}
			
		} while (done == false);
		
	}
	
	/*
	 * Verifies that the User wants to save this Monster to a file.
	 * If so, a new text file will be created and saved with the Monster's information. 
	 */
	private static void saveEnemy() {
		
		boolean done = false; 
		do {
			
			System.out.println("Do you want to save " + m_enemy.get_name() + " to a file? (Yes = 1/ No = 0)");
			try {
				int a = scanner.nextInt();
				if (a == 1) {
					FileCreator enemyFile = new FileCreator(m_enemy);
					System.out.println(m_enemy.get_name() + " was successfully saved.");
					done = true; 
				}
				else if (a == 0) {
					System.out.println(m_enemy.get_name() + " will not be saved.");
					done = true; 
				}
				else {
					System.out.println(OneOrZeroException);
				}
			}
			catch (Exception e) {
				System.out.println(OneOrZeroException);
				System.out.println("Error resulting from:  " + e);
				scanner.next();
			}
		} while (done == false);
		
	}
	
	/*
	 * Verifies that the User wants to delete this Monster object. 
	 * If so, the Monster will be removed from its Encounter and deleted. 
	 * 
	 * @return		boolean		:true if Monster is deleted, false otherwise. 
	 */
	private static boolean deleteEnemy() {
		boolean removed = false;
		boolean done = false; 
		do {
			
			System.out.println("Are you certain you want to delete " + m_enemy.get_name() + "? (Yes = 1/ No = 0)");
			try {
				int a = scanner.nextInt();
				if (a == 1) {
					m_encounter.delete_enemy(m_enemy);
					String name = m_enemy.get_name();
					m_enemy = null; 
					System.out.println(name + " was succesfully deleted.");
					removed = true;
					done = true; 
				}
				else if (a == 0) {
					System.out.println("Phew, that was close. \n" + m_enemy.get_name() + " will not be deleted.");
					removed = false; 
					done = true; 
				}
				else {
					System.out.println(OneOrZeroException); 
				}
			}
			catch (Exception e) {
				System.out.println(OneOrZeroException);
				System.out.println("Error resulting from:  " + e);
				scanner.next();
			}
			
		} while (done == false);
		
		return removed; 
	}
	
}
