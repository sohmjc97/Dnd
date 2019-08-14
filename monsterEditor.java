package Dnd;

import java.util.ArrayList;

import Dnd.Being.DamageTypes;
import Dnd.Being.StatusCondition;

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
		//System.out.println("Which Enemy would you like to edit?");
		
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
				System.out.println("Error resulting from:  " + e);
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
		
		for (int i = 0; i< enemyCount; i++) {
			output = output + (i+3) + ") " + m_encounter.get_enemies().get(i).get_name() + "\n"; 
		}
			
		System.out.println(output);  
		
	}
	
	
	/*
	 * Decides which Enemy to edit based on the User input 
	 */
	private static boolean parseEnemyChoices (int choice) {
		
		boolean done = false;
		if (choice == 1) {
			System.out.println("Returning to Encounter Editor...");
			done = true;
		}
		else if (choice == 2) {
			scanner.nextLine();
			System.out.println("What should the new enemy be called?");
			String name = scanner.nextLine();
			m_enemy = m_encounter.add_enemy(name);
			m_enemy.autoSave();
		}
		else {
			m_enemy = m_encounter.get_enemies().get(choice - 3);
			getEnemyEdits(); 
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
		output = output + "1) Return to Enemy Selection \n";
		output = output + "2) Description \n";
		output = output + "3) Host \n";
		output = output + "4) Stats \n";
		output = output + "5) Items \n";
		output = output + "6) View Enemy Details \n";
		output = output + "7) Save Enemy \n";
		output = output + "8) Create Copies \n";
		output = output + "9) Delete Enemy \n";
		System.out.println(output); 
	}
	
	
	/*
	 * Decides which attributes to edit base don the User's input
	 */
	private static boolean parseEnemyEdits (int choice) {
		
		boolean done = false;
		switch (choice) {
		
		case 1: 
			System.out.println("Returning to Enemy Selection...");
			done = true;
			break;
		case 2:
			scanner.nextLine(); 
			System.out.println("What would you like the new description to be?");
			String descrip = scanner.nextLine(); 
			m_enemy.set_description(descrip);
			System.out.println("The new description has been set to :");
			System.out.println(m_enemy.get_description());
			break;
		case 3:
			System.out.println("This enemy belongs to the " + m_encounter.get_name() + " encoutner.");
			done = switchHost(); 
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
			saveEnemy();
			break;
		case 8:
			copyMonster(); 
			break;
		case 9:
			done = deleteEnemy();
			break;
		}
		return done; 
		
	}
	
	/*
	 * Gets user input as to whether this Monster should be moved to a new Encounter.
	 * If so,  gets User input as to which Encounter it should ve moved to and
	 * executes the switch.
	 */
	private static boolean switchHost() {
		
		boolean switched = false;
		boolean done = false;
		do {
			System.out.println("Would you like to change the Encouner this enemy belongs to? (Yes = 1/ No = 0)");
			try {
				int a = scanner.nextInt();
				if (a == 1) {
					getNewLocation();
					switched = true; 
					done = true; 
				}
				else if (a == 0) {
					System.out.println("The enemy will remain in this Encounter.");
					done = true;
				}
				else {
					System.out.println(OneOrZeroException);
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from:  " + e);
				scanner.next(); 
			}
			
		} while (done == false);
		return switched;
	}
	
	/*
	 * Gets user input as to which Host Location in which to find a new Encounter
	 * for the Monster
	 */
	private static void getNewLocation () {
		
		boolean done = false;
		do {
			int n = 1;
			System.out.println("---City Encounters---");
			/*System.out.println("Encounter: " + m_encounter.get_name());
			System.out.println("Country: " + m_encounter.get_country().get_country_name());
			System.out.println("Cities: " + m_encounter.get_country().get_cities()); */
			for (City city: m_encounter.get_country().get_cities()) {
				String encounters = " :: ";
				for (Encounter e: city.get_all_encounters()) {
					encounters = encounters + e.get_name() + " :: ";
				}
				System.out.println(n + ") " + city.get_name() + encounters);
				n++;
			}
			int m = n;
			System.out.println("---Routes---");
			for (Route route: m_encounter.get_country().get_routes()) {
				String encounters = " :: ";
				for (Encounter e: route.get_all_encounters()) {
					encounters = encounters + e.get_name() + " :: ";
				}
				System.out.println(m + ") " + route.get_name() + encounters);
				m++;
			}
			if (m == n) {
				m++;
			}
			System.out.println("Type the number of the new location: ");
			
			try {
				int a = scanner.nextInt();
				if (a < 0) {
					System.out.println(OutOfRangeException);
				}
				else if (a > m - 1) {
					System.out.println(OutOfRangeException);
				}
				else {
					boolean changed = false;
					if (a < n - 1) {
						City choice = m_encounter.get_country().get_cities().get(a-1);
						changed = getNewEncounter(choice);
					}
					else {
						Route choice = m_encounter.get_country().get_routes().get(m-a-1);
						changed = getNewEncounter(choice); 
					}
					//m_encounter = null; 
					done = changed; 
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException);
				System.out.println("Error resulting from:  " + e);
				scanner.next(); 
			}
			
		} while (done == false);
		
	}
	
	/*
	 * Lists the City Encounters for the chosen Host Location and gets user input s to which
	 * one the Monster should be moved to. 
	 */
	private static boolean getNewEncounter (City choice) {
		
		boolean changed = false; 
		boolean done = false;
		do {
			int n = 1;
			System.out.println("<<<" + choice.get_name() + ">>>");
			for (Encounter e: choice.get_all_encounters()) {
				System.out.println(n + ") " + e.get_name());
				n++;
			}
			if (n == 1) {
				System.out.println("There are no encounters in this location.");
				done = true;
				continue; }
			else {
				System.out.println("Type the number of the new Encounter:");
				try {
					int a = scanner.nextInt();
					if (a < 1) {
						System.out.println(OutOfRangeException);
					}
					else if (a > choice.get_all_encounters().size()) {
						System.out.println(OutOfRangeException);
					}
					else {
						//System.out.println(choice.get_all_encounters()); 
						Encounter newEncounter = choice.get_all_encounters().get(a - 1);
						System.out.println("You have chosen " + newEncounter.get_name());
						for (Monster m: newEncounter.get_enemies()) {
							if (m.get_name() == m_enemy.get_name()) {
								System.out.println(newEncounter.get_name() + " already has this enemy. Nothing was changed.");
								continue;
							}
						}
						m_enemy.switchEncounter(newEncounter, m_enemy);
						//System.out.println("New encounter is still " + m_enemy.get_encounter().get_name());
						changed = true;
						done = true; 
					}
				}
				catch(Exception e) {
					System.out.println(MustBeIntException);
					System.out.println("Error resulting from:  " + e);
					scanner.next(); 
				}
			}
			
		} while (done == false);
		return changed; 
		
	}
	
	/*
	 * Lists the Route Encounters for the chosen Host Location and gets user input s to which
	 * one the Monster should be moved to. 
	 */
	private static boolean getNewEncounter (Route choice) {
		
		boolean changed = false; 
		boolean done = false;
		do {
			int n = 1;
			System.out.println("<<<" + choice.get_name() + ">>>");
			for (Encounter e: choice.get_all_encounters()) {
				System.out.println(n + ") " + e.get_name());
				n++;
			}
			if (n == 1) {
				System.out.println("There are no encounters in this location.");
				done = true;
				continue;
			}
			else {
				System.out.println("Type the number of the new Encounter:");
				try {
					int a = scanner.nextInt();
					if (a < 1) {
						System.out.println(OutOfRangeException);
					}
					else if (a > choice.get_day_encounters().size() + 1) {
						System.out.println(OutOfRangeException);
					}
					else {
						System.out.println(choice.get_all_encounters()); 
						Encounter newEncounter = choice.get_all_encounters().get(a - 1);
						System.out.println(newEncounter.get_name());
						m_enemy.switchEncounter(newEncounter, m_enemy);
						System.out.println("New encounter is still " + m_enemy.get_encounter().get_name());
						changed = true; 
						done = true; 
					}
				}
				catch(Exception e) {
					System.out.println("Error resulting from:  " + e);
					System.out.println(MustBeIntException);
					scanner.next(); 
				}
			}
		} while (done == false);
		return changed;
		
	}
	
	/*
	 * List the options for modifying Monster's stats
	 */
	private static void listEnemyStats() {
		String output = ""; 
		output = output + "---Enemy Stats---\n";
		output = output + "1) Return to Previous Menu \n";
		output = output + "2) Level: " + m_enemy.get_lvl() + "\n";
		output = output + "3) HP: " + m_enemy.get_hp() + "\n";
		output = output + "4) AC: " + m_enemy.get_ac() + "\n";
		output = output + "5) XP: " + m_enemy.get_xp() + "\n";
		output = output + "6) Attack Mod: " + m_enemy.m_attackMod + "\n";
		output = output + "7) Damage Die: " + m_enemy.get_dmgDie() + "\n";
		output = output + "8) Number of Damage Die: " + m_enemy.get_numdmgDie() + "\n";
		output = output + "9) Damage Mod: " + m_enemy.get_dmgMod() + "\n";
		output = output + "10) Status Condition: " + m_enemy.get_status_condition() + "\n";
		output = output + "11) Ability Mods: \n";
		for (String i: m_enemy.m_abilityMods.keySet()) {
			output = output + i + ": " + m_enemy.m_abilityMods.get(i) + "\n";
		}
		output = output + "12) Weaknesses" + m_enemy.get_weaknesses() + "\n";
		output = output + "13) Resistances" + m_enemy.get_resistances() + "\n";
		output = output + "14) Damage Immunities " + m_enemy.get_dmg_immunities() + "\n";
		output = output + "15) Condition Immunities " + m_enemy.get_condition_immunities() + "\n";
		
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
				else if (a > 15) {
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
			done = true;
			break;
		case 2: 
			editLvl();
			break;
		case 3:
			editHP();
			break;
		case 4:
			editAC();
			break;
		case 5:
			editXP();
			break;
		case 6:
			editAM();
			break;
		case 7:
			editDmgDie();
			break;
		case 8:
			editNumDmgDie();
			break;
		case 9:
			editDmgMod();
			break;
		case 10:
			addStatusCondition();
			removeStatusCondition();
			break;
		case 11:
			editAbilityMods(); 
			break;
		case 12:
			addWeaknesses();
			removeWeaknesses();
			break; 
		case 13:
			addResistances(); 
			removeResistances(); 
			break; 
		case 14:
			addDmgImmunities();
			removeDmgImmunities();
			break; 
		case 15:
			addConditionImmunities();
			removeConditionImmunities(); 
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
	 * Asks User to choose which, if any, weaknesses will be added to the Monster
	 */
	private static void addWeaknesses() {
		
		boolean done = false;
		do {
			System.out.println("Type the name of the damage type you wish to add as a weakness.");
			int n = 1;
			System.out.println(n + ") " + "Add no weaknesses");
			for (DamageTypes i: DamageTypes.values()) {
				n++;
				System.out.println(n + ") " + i);
			}
			try {
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > DamageTypes.values().length + 1) {
					System.out.println(OutOfRangeException);
				}
				else {
					int x = 1; 
					if (a == x) {
						System.out.println("No new weaknesses were added.");
						done = true;
					}
					else {
						for (DamageTypes i:DamageTypes.values()) {
							x++;
							if (a == x) {
								m_enemy.add_weakness(i);
								System.out.println(i + " added as a weakness.");
							}
						}
					}
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
	 * Asks User to choose which, if any, weaknesses will be removed from the Monster
	 */
	private static void removeWeaknesses() {
		
		boolean done = false;
		do {
			System.out.println("Type the name of the damage type you wish to remove as a weakness.");
			int n = 1;
			System.out.println(n + ") " + "Remove no weaknesses");
			for (DamageTypes i: DamageTypes.values()) {
				n++;
				System.out.println(n + ") " + i);
			}
			try {
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > DamageTypes.values().length + 1) {
					System.out.println(OutOfRangeException);
				}
				else {
					int x = 1; 
					if (a == x) {
						System.out.println("No new weaknesses were removed.");
						done = true;
					}
					else {
						for (DamageTypes i:DamageTypes.values()) {
							x++;
							if (a == x) {
								m_enemy.remove_weakness(i);
								System.out.println(i + " removed as a weakness.");
							}
						}
					}
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
	 * Asks User to choose which, if any, resistances will be added to the Monster
	 */
	private static void addResistances () {
		
		boolean done = false;
		do {
			System.out.println("Type the number of the damage type you wish to add as a resistance.");
			int n = 1;
			System.out.println(n + ") " + "Add no resistances");
			for (DamageTypes i: DamageTypes.values()) {
				n++;
				System.out.println(n + ") " + i);
			}
			try {
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > DamageTypes.values().length + 1) {
					System.out.println(OutOfRangeException);
				}
				else {
					int x = 1; 
					if (a == x) {
						System.out.println("No new resistances were added.");
						done = true;
					}
					else {
						for (DamageTypes i:DamageTypes.values()) {
							x++;
							if (a == x) {
								m_enemy.add_resistance(i);
								System.out.println(i + " added as a resistance.");
							}
						}
					}
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
	 * Asks User to choose which, if any, resistances will be removed from the Monster
	 */
	private static void removeResistances () {
		
		boolean done = false;
		do {
			System.out.println("Type the number of the damage type you wish to remove as a resistance.");
			int n = 1;
			System.out.println(n + ") " + "Remove no resistances");
			for (DamageTypes i: DamageTypes.values()) {
				n++;
				System.out.println(n + ") " + i);
			}
			try {
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > DamageTypes.values().length + 1) {
					System.out.println(OutOfRangeException);
				}
				else {
					int x = 1; 
					if (a == x) {
						System.out.println("No new resistances were removed.");
						done = true;
					}
					else {
						for (DamageTypes i:DamageTypes.values()) {
							x++;
							if (a == x) {
								m_enemy.remove_resistance(i);
								System.out.println(i + " removed as a resistance.");
							}
						}
					}
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
	 * Asks User to choose which, if any, DamageType Immunities will be added to the Monster
	 */
	private static void addDmgImmunities () {
		
		boolean done = false;
		do {
			System.out.println("Type the number of the damage type you wish to add as an immunity.");
			int n = 1;
			System.out.println(n + ") " + "Add no Immunities");
			for (DamageTypes i: DamageTypes.values()) {
				n++;
				System.out.println(n + ") " + i);
			}
			try {
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > DamageTypes.values().length + 1) {
					System.out.println(OutOfRangeException);
				}
				else {
					int x = 1; 
					if (a == x) {
						System.out.println("No new immunities were added.");
						done = true;
					}
					else {
						for (DamageTypes i:DamageTypes.values()) {
							x++;
							if (a == x) {
								m_enemy.add_dmg_immunity(i);
								System.out.println(i + " added as an immunity.");
							}
						}
					}
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
	 * Asks User to choose which, if any, DamageType Immunities will be removed from the Monster
	 */
	private static void removeDmgImmunities () {
		
		boolean done = false;
		do {
			System.out.println("Type the number of the damage type you wish to remove as an immunity.");
			int n = 1;
			System.out.println(n + ") " + "Remove no Immunities");
			for (DamageTypes i: DamageTypes.values()) {
				n++;
				System.out.println(n + ") " + i);
			}
			try {
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > DamageTypes.values().length + 1) {
					System.out.println(OutOfRangeException);
				}
				else {
					int x = 1; 
					if (a == x) {
						System.out.println("No new Immunities were removed.");
						done = true;
					}
					else {
						for (DamageTypes i:DamageTypes.values()) {
							x++;
							if (a == x) {
								m_enemy.remove_dmg_immunity(i);
								System.out.println(i + " removed as an Immunity.");
							}
						}
					}
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
	 * Asks User to choose which, if any, StatusCondition Immunities will be added to the Monster
	 */
	private static void addConditionImmunities () {
		
		boolean done = false;
		do {
			System.out.println("Type the number of the condition you wish to add as an immunity.");
			int n = 1;
			System.out.println(n + ") " + "Add no Immunities");
			for (StatusCondition i: StatusCondition.values()) {
				n++;
				System.out.println(n + ") " + i);
			}
			try {
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > DamageTypes.values().length) {
					System.out.println(OutOfRangeException);
				}
				else {
					int x = 1; 
					if (a == x) {
						System.out.println("No new immunities were added.");
						done = true;
					}
					else {
						for (StatusCondition i:StatusCondition.values()) {
							x++;
							if (a == x) {
								m_enemy.add_condition_immunity(i);
								System.out.println(i + " added as an immunity.");
							}
						}
					}
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
	 * Asks User to choose which, if any, StatusCondition Immunities will be removed from the Monster
	 */
	private static void removeConditionImmunities () {
		
		boolean done = false;
		do {
			System.out.println("Type the number of the condition you wish to remove as an immunity.");
			int n = 1;
			System.out.println(n + ") " + "Remove no Immunities");
			for (StatusCondition i: StatusCondition.values()) {
				n++;
				System.out.println(n + ") " + i);
			}
			
			try {
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > DamageTypes.values().length) {
					System.out.println(OutOfRangeException);
				}
				else {
					int x = 1; 
					if (a == x) {
						System.out.println("No new Immunities were removed.");
						done = true;
					}
					else {
						for (StatusCondition i:StatusCondition.values()) {
							x++;
							if (a == x) {
								m_enemy.remove_condition_immunity(i);
								System.out.println(i + " removed as an Immunity.");
							}
						}
					}

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
	 * Asks the user which if any StatusConditions should be placed on the enemy
	 */
	private static void addStatusCondition () {
		
		boolean done = false;
		do {
			System.out.println("Type the number of the status condition you wish to give this enemy.");
			int n = 1;
			System.out.println(n + ") " + "Add no new Status Conditions");
			for (StatusCondition i: StatusCondition.values()) {
				n++;
				System.out.println(n + ") " + i);
			}
			
			try {
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > DamageTypes.values().length) {
					System.out.println(OutOfRangeException);
				}
				else {
					int x = 1; 
					if (a == x) {
						System.out.println("No new Status Conditions were added.");
						done = true;
					}
					else {
						for (StatusCondition i:StatusCondition.values()) {
							x++;
							if (a == x) {
								m_enemy.add_status_condition(i);
								System.out.println(m_enemy.get_name() + " is now " + i);
							}
						}
					}
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
	 * Asks the user which if any StatusConditions should be removed from the enemy
	 */
	private static void removeStatusCondition() {
		
		boolean done = false;
		do {
			System.out.println("Type the number of the status condition you wish to remove from this enemy.");
			int n = 1;
			System.out.println(n + ") " + "Remove no Status Conditions");
			for (StatusCondition i: StatusCondition.values()) {
				n++;
				System.out.println(n + ") " + i);
			}
			try {
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > DamageTypes.values().length) {
					System.out.println(OutOfRangeException);
				}
				else {
					int x = 1; 
					if (a == x) {
						System.out.println("No new Status Conditions were added.");
						done = true;
					}
					else {
						for (StatusCondition i:StatusCondition.values()) {
							x++;
							if (a == x) {
								m_enemy.remove_status_condition(i);
								System.out.println(m_enemy.get_name() + " has recovered from being " + i);
							}
						}
					}
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
			
			if (m_enemy.get_items_w_descrip().isEmpty()) {
				done = true;
				System.out.println(m_enemy.get_name() + "'s inventory is empty.");
				continue; 
			}
			
			ArrayList<String> items = m_enemy.get_item_names();
			System.out.println("Do you want to delete any items? (Yes = 1/ No = 0)");
			try {
				int a = scanner.nextInt();
				if (a == 1) {
					int i = 1;
					for (String item: m_enemy.get_item_names()) {
						System.out.println(i + ") " + item);
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
							m_enemy.get_items_w_descrip().remove(thing);
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
	public static void saveEnemy() {
		
		boolean done = false; 
		do {
			
			System.out.println("Do you want to save " + m_enemy.get_name() + " to a file? (Yes = 1/ No = 0)");
			try {
				int a = scanner.nextInt();
				if (a == 1) {
					m_enemy.autoSave();
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
				//System.out.println("Error resulting from:  " + e);
				scanner.next();
			}
			
		} while (done == false);
		
		return removed; 
	}
	
	/*
	 * This is great for when you want to make a bunch of the same type of enemy. Get the original in the state you want
	 * to make the copies, then save it and use this option with however many of the copies you want, noting that the original wil remain as well.
	 * The copies will be distinct object that is a perfect copy of the original, made from its text file. You can then edit each one as you
	 * like to give them differences, and it will not affect any of the other copies or the original. You can also change the original after
	 * making the copies, and it will not affect them, since they are now distinct objects.
	 */
	private static void copyMonster () {
		
		boolean done = false; 
		do {
			System.out.println("How many copies do you want to make of this Monster? Enter 0 if you don't want to make a copy.");
			try {
				int a = scanner.nextInt();
				if (a == 0) {
					System.out.println("No copies will be made.");
					done = true;
				}
				else if (a < 0 | a > 20) {
					System.out.println(OutOfRangeException);
				}
				else {
					int count = 0;
					
					ArrayList<Monster> copies = new ArrayList<Monster>();
					while (count < a) {
						Monster copy = new Monster(m_enemy.get_name(), m_enemy.get_encounter());
					
						copies.add(copy);
						count++;
					}
					reconstructEnemies(copies); 
					int i = 2;
					for (Monster m: copies) {
						m.set_name(m_enemy.get_name() + i);
						m_encounter.append_enemy(m);
						i++;
					}
					done = true;
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException + "\n" +
						"Error Resulting from: " + e);
				scanner.next();
			}
		} while (done == false);
		
	} 
	
}
