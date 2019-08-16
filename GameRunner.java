package Dnd;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import Dnd.Being.DamageTypes;

public class GameRunner extends WorldEditor {
	
	private final static int cityCount = m_country.get_cities().size(); 
	private final static int routeCount = m_country.get_routes().size();
	
	private static Encounter m_encounter = null; 
	
	public static void play () {
		
		m_city = null;
		m_route = null;
		m_enemy = null;
		
		System.out.println("Starting up the game...");
		boolean done = false;
		do {
			getHostLocation();
			getEncounter();
			
		} while (done == false);
	}
		
		//if battle, roll for order 
		//Shows each monster on its turn, asks for action 
		//listMonsterOptions();
		//Rolls for actions and gives results 
		
	private static void listHostLocations() {
		
		String output = "<<< Cities >>> \n";
		
		for (int i = 0; i < cityCount; i++) {
			output = output + (i+1) + ") " + m_country.get_cities().get(i).get_name() + "\n"; 
		}
		output = output + "<<< Routes >>> \n";
		
		for (int i = 0; i < routeCount; i++) {
			output = output + (i+1+cityCount) + ") " + m_country.get_routes().get(i).get_name() + "\n"; 
		}
		System.out.println(output);
		
	}
	
	private static void getHostLocation () {
		
		boolean done = false;
		do {
			
			listHostLocations();
			System.out.println("Type the number of the location you want to start in.");
			int locCount = routeCount + cityCount;
			try {
				int a = scanner.nextInt();
				if (a < 1 | a > locCount) {
					System.out.println(OutOfRangeException);
				}
				else {
					if (a <= cityCount) {
						m_city = m_country.get_cities().get(a-1);
					}
					else {
						m_route = m_country.get_routes().get(a - cityCount -1);
					}
					done = true;
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException + "\n"
						+ "Error resulting from: " + e);
				scanner.next();
			}
			
		}while (done == false);
		
	}
	
	public static void getEncounter () {
		
		ArrayList<Encounter> availableEncounters = null;
		if (m_city != null) {
			availableEncounters = m_city.get_all_encounters();
			m_city.list_all_info();
		}
		else if(m_route != null) {
			availableEncounters = m_route.get_all_encounters();
			m_route.list_all_info();
		}
		else {
			System.out.println("A problem occured in that no host location is active.");
		}
		
		boolean done = false;
		do {
			if (availableEncounters.isEmpty()) {
				System.out.println("There are no Encounters here. Try picking a different location.");
				done = true;
			}
			else {
				listEncounters(); 
				System.out.println("Type the number of the Encounter you wish to run.");
				
				try {
					int a = scanner.nextInt();
					if (a == 1) {
						System.out.println("Returning to Location Selection...");
						done = true;
						continue;
					}
					else if (a < 1 | a > availableEncounters.size()) {
						System.out.println(OutOfRangeException);
					}
					else {
						m_encounter = availableEncounters.get(a-2);
						getEncounterOptions();
					}
				}
				catch (Exception e) {
					System.out.println(MustBeIntException + "\n"
							+ "Error resulting from: " + e);
					scanner.next();
				}
			}
			
		} while ( done == false ); 
		
	}
	
	public static void listEncounters () {
		int x = 1;
		String output = x + ") Return to Location Selection \n";
		if (m_city != null) {
			for (int i = 0; i < m_city.get_all_encounters().size(); i++) {
				
				output = output + (i+2) + ") " +  m_city.get_all_encounters().get(i).get_name() + "\n";
				
			}
		}
		else if (m_route != null) {
			for (int i = 0; i< m_route.get_all_encounters().size(); i++) {
				
				output = output + (i+2) + ") " +  m_route.get_all_encounters().get(i).get_name() + "\n";
				
			}
		}
		else {
			System.out.println("A problem occured in that no Host Location is active.");
		}
		
		System.out.println(output);
		
	}
	
	private static void getEncounterOptions () {
		
		boolean done = false;
		do {
			m_encounter.list_all_info();
			listEncounterOptions();
			System.out.println("Type the number of the action you would liek to take.");
			
			try {
				int a = scanner.nextInt();
				if (a < 1 | a > 4) {
					System.out.println(OutOfRangeException);
				}
				else {
					done = parseEncounterOptions(a);
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException + "\n"
						+ "Error resulting from: " + e);
				scanner.next();
			}
			
			
		} while (done == false);
		
	}
	
	private static void listEncounterOptions() {
	
		String output = "1) Return to Encounter Selection \n";
		output = output + "2) Skill Check on PLayers \n";
		output = output + "3) Skill Check on Enemy \n";
		output = output + "4) Begin Combat \n";
		System.out.println(output);
		
	}
	
	private static boolean parseEncounterOptions(int choice) {
		
		boolean done = false;
		switch (choice) {
		case 1:
			System.out.println("Returning to Encounter Selection...");
			done = true;
			break;
		case 2:
		case 3:
			done = runSkillCheck();
			break;
		case 4:
			runCombat();
			break;
		}
		return done;
		
	}
	
	private static boolean runSkillCheck() {
		boolean done = false;
		System.out.println("Entering Skill Checker...");
		
		return done;
	}
	
	private static boolean runCombat () {
		boolean done = false;
		System.out.println("Entering Combat...");
		HashMap<Monster, Integer> enemyOrder = rollEnemyInitiative();
		do {
			System.out.println("1) Return to Previous Menu");
			int i = 2;
			for (Monster m: m_encounter.get_enemies()) {
				System.out.println(i + ") " + m.get_name() + ": " + enemyOrder.get(m));
				i++;
			}
			if (m_encounter.get_enemies().isEmpty()) {
				System.out.println("Congratulations! All enemies have been vanquished. Please return to the Encounter Menu.");
			}
			System.out.println("Type the number of an enemy for combat options.");
			try {
				int a = scanner.nextInt();
				if (a == 1) {
					done = true;
					continue;
				}
				else if (a < 1 | a > m_encounter.get_enemies().size() + 1) {
					System.out.println(OutOfRangeException);
				}
				else {
					getEnemyOptions(a); 
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException + "\n"
						+ "Error resulting from: " + e);
				scanner.next();
			}
			
		} while (done == false);
		return done;
	}

	private static HashMap<Monster, Integer> rollEnemyInitiative () {
		
		HashMap<Monster, Integer> order = new HashMap<Monster, Integer>();
		for (Monster m: m_encounter.get_enemies()) {
			int initiative = m.roll() + m.get_abilityMod("DEX");
			order.put(m, initiative);
		}
		
		return order;
		
	}
	
	private static void getEnemyOptions(int index) {
		
		m_enemy = m_encounter.get_enemies().get(index - 2);
		
		boolean done = false;
		do {
			listEnemyOptions();
			System.out.println("Type the number of the action " + m_enemy.get_name() + " should take");
			
			try {
				int a = scanner.nextInt();
				if (a < 1 | a > 6) {
					System.out.println(OutOfRangeException);
				}
				else {
					done = parseEnemyOptionChoice(a); 
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException + "\n"
						+ "Error resulting from: " + e);
				scanner.next();
			}
			
		} while (done == false);
		
		
	}
	
	private static void listEnemyOptions () {
		
		String output = "";
		output = output + "1) Finish Turn \n";
		output = output + "2) Attack \n";
		output = output + "3) Dodge \n";
		output = output + "4) Heal \n";
		output = output + "5) Take Damage \n";
		output = output + "6) Get Enemy Stats";
		System.out.println(output);
		
	}
	
	private static boolean parseEnemyOptionChoice(int choice) {
		
		boolean done = false;
		switch (choice) {
		
		case 1:
			System.out.println("Returning to Enemy Selector");
			done = true;
			break;
		case 2:
			rollAttack();
			break;
		case 3:
			//dodge();
			break;
		case 4:
			getHeals();
			break;
		case 5:
			done = getAttacked(); 
			break;
		case 6:
			m_enemy.list_all_stats();
			break;
		}
		return done;
	}
	
	private static void rollAttack () {
		
		boolean done = false;
		do {
			
			System.out.println("Enter target's AC: ");
			try {
				int a = scanner.nextInt();
				if (a < 0) {
					System.out.println(OutOfRangeException);
				}
				else {
					boolean adv = getAdv();
					boolean disadv = getDisadv();
					if (adv & disadv) {
						System.out.println("You can't roll with Advantage and Disadvantage at the same time. Try again");
						continue;
					}
					else {
						m_enemy.attack(adv, disadv, a);
						done = true; 
					}
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException + "\n"
						+ "Error resulting from: " + e);
				scanner.next();
			}
			
			
		} while(done == false);
		
	}
	
	private static boolean getAdv() {
		
		boolean adv = false;
		boolean done = false;
		do {
			System.out.println("Roll with Advantage? (Yes = 1/ No = 0) ");
			try {
				int a = scanner.nextInt();
				if (a == 1) {
					adv  = true;
					done = true;
				}
				else if (a == 0) {
					done = true;
				}
				else {
					System.out.println(OutOfRangeException);
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException + "\n"
						+ "Error resulting from: " + e);
				scanner.next();
			}
		} while (done == false);
		return adv;
		
	}
	
	private static boolean getDisadv() {
		
		boolean disadv = false;
		boolean done = false;
		do {
			System.out.println("Roll with Disadvantage? (Yes = 1/ No = 0) ");
			try {
				int a = scanner.nextInt();
				if (a == 1) {
					disadv  = true;
					done = true;
				}
				else if (a == 0) {
					done = true;
				}
				else {
					System.out.println(OutOfRangeException);
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException + "\n"
						+ "Error resulting from: " + e);
				scanner.next();
			}
		} while (done == false);
		return disadv;
		
	}
	
	private static void getHeals() {
		
		boolean done = false;
		do {
			
			try {
				System.out.println("How much health should " + m_enemy.get_name() + " recover?");
				int a = scanner.nextInt();
				if (a < 0) {
					System.out.println(OutOfRangeException);
				}
				else {
					m_enemy.regainHP(a);
					System.out.println(m_enemy.get_name() + " has recovered " + a + " hit points.");
					done = true; 
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException + "\n"
						+ "Error resulting from: " + e);
				scanner.next();
			}
			
		} while (done == false);
		
	}
	
	private static boolean getAttacked () {
		
		boolean gotkilled = false;
		boolean done = false;
		do {
			System.out.println("HWhat did the attacker roll to hit?");
			try {
				int a = scanner.nextInt();
				if (a < 0) {
					System.out.println(OutOfRangeException);
				}
				else if (a >= m_enemy.m_ac){
					System.out.println(m_enemy.get_name() + " takes the hit!");
					System.out.println("How much damage was dealt");
					
					try {
						int dmg = scanner.nextInt();
						if (dmg < 0) {
							System.out.println(OutOfRangeException);
						}
						else {
							DamageTypes type = getDamageType();
							m_enemy.takeDamage(dmg, type);
							if (m_enemy.isDead()) {
								m_enemy = null;
								gotkilled = true;
							}
						}
					}
					catch(Exception e) {
						System.out.println(MustBeIntException + "\n"
								+ "Error resulting from: " + e);
						scanner.next();
					}
					
				}
				else {
					System.out.println("The attack misses!");
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException + "\n"
						+ "Error resulting from: " + e);
				scanner.next();
			}
			
		} while (done = false);
		return gotkilled;
		
	}
	
	private static DamageTypes getDamageType () {
		
		boolean done = false;
		DamageTypes type = null;
		do {
			int i = 1;
			for (DamageTypes dmg: DamageTypes.values()) {
				System.out.println(i + ") " + dmg);
				i++;
			}
			System.out.println("Enter the type of damage that was dealt.");
			
			try {
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else {
					int x= 1;
					
					for (DamageTypes dmg: DamageTypes.values()) {
						if (a == x) {
							type = dmg;
							done = true;
						}
						x++;
					}
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException + "\n"
						+ "Error resulting from: " + e);
				scanner.next();
			}
			
		} while (done == false);
		return type; 
		
	}
	
	/*private static HashMap<String, Integer> getPlayerInitiative () {
		HashMap<String, Integer> playerOrder = new HashMap<String, Integer>();
		boolean done = false;
		do {
			System.out.println("How mnay players are participating?");
			try {
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else {
					String name = "";
					for (int i = 0; i < a; i++) {
						name = "Player" + (i+1);
						boolean over = false;
						int value = 0;
						do {
							try {
								System.out.println("Enter " + name + "'s initiative rool: ");
								int roll = scanner.nextInt();
								value = roll;
								over = true;
							}
							catch(Exception e) {
								System.out.println(MustBeIntException + "\n"
										+ "Error resulting from: " + e);
								scanner.next();
							}
						} while (over == false);
						playerOrder.put(name, value);
					
					}
					done = true;
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException + "\n"
						+ "Error resulting from: " + e);
				scanner.next();
			}
		} while (done == false);
		return playerOrder;
		
	}
	
	/*private static void mergeOrders (HashMap<Monster, Integer> enemyOrder, HashMap<String, Integer> playerOrder) {
		
		ArrayList<String> MasterOrder = new ArrayList<String>;
		
		
		
	}*/
	
	
}
