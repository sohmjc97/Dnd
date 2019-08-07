package Dnd;

public class encounterEditor extends WorldEditor {
	
	protected static Encounter m_encounter = null; 
	protected static Route r_host = null;
	protected static City c_host = null; 
	
	/*
	 * Main Encounter Editor Function for Encounters belonging to Routes
	 * Gives Choices for which Encounters to edit and gets user answer
	 */
	public static void edit (Route route) {
		
		r_host = route; 
		c_host = null; 
		
		System.out.println("----- Encounter Editor -----");
		System.out.println("Which Encounter would you like to edit?");
		
		boolean done = false;
		do {
			listEncounterChoices(r_host); 
			System.out.println("Type the number of the Encounter you want to edit.");
			try {
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > (m_route.get_day_encounters().size() + 2)) {
					System.out.println(OutOfRangeException);
				}
				else {
					done = parseEncounterChoices(a); 
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException);
			}
		} while (done == false);
	}
	
	/*
	 * Main Encounter Editor Function for Encounters belonging to Cities
	 * Gives Choices for which Encounters to edit and gets user answer
	 */
	public static void edit (City city) {
		
		c_host = city; 
		r_host = null; 
		
		System.out.println("----- Encounter Editor -----");
		System.out.println("Which Encounter would you like to edit?");
		
		boolean done = false;
		do {
			listEncounterChoices(c_host); 
			System.out.println("Type the number of the Encounter you want to edit.");
			try {
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > (m_city.get_encounters().size() + 2)) {
					System.out.println(OutOfRangeException);
				}
				else {
					done = parseEncounterChoices(a); 
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException);
			}
		} while (done == false);
	}
	
	/*
	 * Lists options for Route Encounters
	 * Prints a list of options for user to choose from
	 * They can return to previous menu, add a new Encounter or
	 * edit any of the old ones 
	 */
	private static void listEncounterChoices(Route route) {
		String output = ""; 
		int encounterCount = m_route.get_day_encounters().size(); 
		System.out.println("Encounter Count: " + encounterCount);
		
		output = output + "1) " + "Return to Route Editor \n";
		output = output + "2) " + "Add new encounter \n";
		if (encounterCount >= 1) {
			output = output + "3) " + m_route.get_day_encounters().get(0).get_name() +"\n";
		}
		if (encounterCount >= 2) {
			output = output + "4) " + m_route.get_day_encounters().get(1).get_name()+ "\n";
		}
		if (encounterCount >= 3) {
			output = output + "5) " + m_route.get_day_encounters().get(2).get_name() + "\n";
		}
		if (encounterCount >= 4) {
			output = output + "6) " + m_route.get_day_encounters().get(3).get_name() + "\n";
		}
		if (encounterCount >= 5) {
			output = output + "7) " + m_route.get_day_encounters().get(4).get_name() + "\n";
		}
		if (encounterCount >= 6) {
			output = output + "8) " + m_route.get_day_encounters().get(5).get_name() + "\n";
		}
		/*
		 * Add additional lines if you plan on having a Route with more than 6 encounters 
		 */
		System.out.println(output);  
	}
	
	/*
	 * Lists options for City Encounters
	 * Prints a list of options for user to choose from
	 * They can return to previous menu, add a new Encounter or
	 * edit any of the old ones 
	 */
	private static void listEncounterChoices(City city) {
		String output = ""; 
		int encounterCount = m_city.get_encounters().size(); 
		System.out.println("Encounter Count: " + encounterCount);
		
		output = output + "1) " + "Return to City Editor \n";
		output = output + "2) " + "Add new encounter \n";
		if (encounterCount >= 1) {
			output = output + "3) " + m_city.get_encounters().get(0).get_name() +"\n";
		}
		if (encounterCount >= 2) {
			output = output + "4) " + m_city.get_encounters().get(1).get_name() + "\n";
		}
		if (encounterCount >= 3) {
			output = output + "5) " + m_city.get_encounters().get(2).get_name() + "\n";
		}
		if (encounterCount >= 4) {
			output = output + "6) " + m_city.get_encounters().get(3).get_name() + "\n";
		}
		if (encounterCount >= 5) {
			output = output + "7) " + m_city.get_encounters().get(4).get_name() + "\n";
		}
		if (encounterCount >= 6) {
			output = output + "8) " + m_city.get_encounters().get(5).get_name() + "\n";
		}
		/*
		 * Add additional lines if you plan on having a Route with more than 6 encounters 
		 */
		System.out.println(output);  
	}
	
	/*
	 * Takes user's input and directs them through their chosen edits
	 * 
	 * @param	choice Integer	:the user's choice from the previous options menu
	 * @return	boolean			:returns true if user wishes to return to previous menu
	 */
	private static boolean parseEncounterChoices(int choice) {
		boolean done = false;
		//System.out.println("Parsing choices...");
		switch (choice) {
			case 1:
				System.out.println("Exiting Encounter Editor...");
				done = true;
				break;
			case 2:
				System.out.println("Entering Encounter Constructor...");
				//scanner.nextLine(); 
				
				if (c_host == null) {
					Worldbuilder.addEncounters(m_route);
					int i = m_route.get_day_encounters().size(); 
					m_encounter = m_route.get_day_encounters().get(i-1);
				}
				else if (r_host == null) {
					Worldbuilder.addEncounters(m_city);
					int i = m_city.get_encounters().size();
					m_encounter = m_city.get_encounters().get(i-1); 
				}
				else {
					System.out.println("Program encountered a problem.");
				}
				break;
			case 3:
				if (c_host == null) {
					m_encounter = m_route.get_day_encounters().get(0); 
				}
				else if (r_host == null) {
					m_encounter = m_city.get_encounters().get(0);
				}
				else {
					System.out.println("Program encountered a problem.");
				}
				getEncounterEdits(); 
				break;
			case 4:
				if (c_host == null) {
					m_encounter = m_route.get_day_encounters().get(1); 
				}
				else if (r_host == null) {
					m_encounter = m_city.get_encounters().get(1);
				}
				else {
					System.out.println("Program encountered a problem.");
				}				
				getEncounterEdits(); 
				break;
			case 5:
				if (c_host == null) {
					m_encounter = m_route.get_day_encounters().get(2); 
				}
				else if (r_host == null) {
					m_encounter = m_city.get_encounters().get(2);
				}
				else {
					System.out.println("Program encountered a problem.");
				}				
				getEncounterEdits(); 
				break;
			case 6:
				if (c_host == null) {
					m_encounter = m_route.get_day_encounters().get(3); 
				}
				else if (r_host == null) {
					m_encounter = m_city.get_encounters().get(3);
				}
				else {
					System.out.println("Program encountered a problem.");
				}				
				getEncounterEdits(); 
				break;
			case 7:
				if (c_host == null) {
					m_encounter = m_route.get_day_encounters().get(4); 
				}
				else if (r_host == null) {
					m_encounter = m_city.get_encounters().get(4);
				}
				else {
					System.out.println("Program encountered a problem.");
				}				
				getEncounterEdits(); 
				break;
			case 8:
				if (c_host == null) {
					m_encounter = m_route.get_day_encounters().get(5); 
				}
				else if (r_host == null) {
					m_encounter = m_city.get_encounters().get(5);
				}
				else {
					System.out.println("Program encountered a problem.");
				}				
				getEncounterEdits(); 
				break;
		}
		return done; 
	}
	
	/*
	 * Gets user input on which Encounter attribute they want to edit
	 */
	private static void getEncounterEdits() {
		
		boolean done = false;
		do {
			try {
				listEncounterEdits();
				System.out.println("Type the number of the attribute you want to edit.");
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > 8) {
					System.out.println(OutOfRangeException);
				}
				else {
					done = parseEncounterEdits(a);
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException + "\n" + e);
				scanner.next();
			}
		} while (done == false);
		
	}
	
	/*
	 * Lists all options for editing individuals Encounters 
	 */
	private static void listEncounterEdits() {
		System.out.println("What changes do you want to make to " + m_encounter.get_name() + "?");
		String output = "";
		output = output + "1) Name (Unavailable) \n";
		output = output + "2) Description \n";
		output = output + "3) Host \n";
		output = output + "4) Enemies \n";
		output = output + "5) View Encounter Details \n";
		output = output + "6) Delete Encounter \n";
		output = output + "7) Save Encounter \n";
		output = output + "8) Return to Encounter Choices \n";
		System.out.println(output); 
	}
	
	/*
	 * Directs user through whichever choice they made from the previous menu. 
	 * 
	 * @param	choice Integer	:the user's choice from the previous menu
	 * @return	boolean			:returns true when user is done with this menu
	 */
	private static boolean parseEncounterEdits(int choice) {
		boolean done = false;
		switch (choice) {
			case 1:
				System.out.println("Name editing is not yet available.");
				break;
			case 2:
				edit_description(); 
				break;
			case 3:
				if (get_r_host() == null) {
					System.out.println("This encounter takes place in " + c_host.get_name() + ".");
				}
				else if (get_c_host() == null){
					System.out.println("This encounter takes place on " + r_host.get_name() + ".");
				}
				else {
					System.out.println("This encounter takes place nowhere.");
				}
				break;
			case 4:
				monsterEditor.edit(); 
				//editEnemies(); 
				//System.out.println(m_encounter.get_enemies()); 
				break; 
			case 5:
				System.out.println(m_encounter.get_all_info());
				break;
			case 6:
				done = delete_encounter(); 
				break; 
			case 7:
				saveEncounter(); 
				break;
			case 8:
				done = true; 
				break; 
		}
		return done; 
	}
	
	/*
	 * Verifies that the user wants to edit the Encounter's description.
	 * If so it takes the user's input and sets the new description. 
	 * If not, they will return to previous menu with no changes.
	 */
	private static void edit_description () {
		
		boolean done = false;
		do {
			
			try {
				System.out.println("Are you sure you want to change the description? (Yes = 1/ No = 0)");
				int a = scanner.nextInt();
				if (a == 1) {
					try {
						scanner.nextLine(); 
						System.out.println("What should the new description be?");
						String descrip = scanner.nextLine(); 
						m_encounter.set_description(descrip);
						System.out.println("The new description has been set to: \n" + m_encounter.get_description());
						done = true;
					}
					catch (Exception e) {
						System.out.println(GenericException + "\n" + e);
						scanner.next();
					}
				}
				else if (a == 0) {
					System.out.println("Okay. Description will not be changed.");
					done = true; 
				}
				else {
					System.out.println(OneOrZeroException);
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException + "\n" + e);
				scanner.nextLine();
			}
			
		} while (done == false);
		
	}
	
	/*
	 * Returns the Route on which the Encounter takes place 
	 * 
	 * @return 	r_host Route	:the Route host of the Encounter 
	 */
	private static Route get_r_host() {
		
		if (r_host != null) {
			return r_host;
		}
		else {
			return null; 
		}
		
	}
	
	/*
	 * Returns the City in which the Encounter takes place 
	 * 
	 * @return 	c_host City	:the City host of the Encounter 
	 */
	private static City get_c_host() {
		
		if (c_host != null) {
			return c_host;
		}
		else {
			return null; 
		}
		
	}
	
	/*
	 * Gets users' input on whether they want to add and/or delete enemies from the given Encounter 
	 */
	private static void editEnemies() {
		
		boolean done = false; 
		do {
			int i = 1;
			for (Monster enemy: m_encounter.get_enemies()) {
				System.out.println( i + ") " + enemy.get_name()); 
				i++; 
			}

			System.out.println("Do you want to add enemies to this Encounter? (Yes = 1/ No = 0)");
			try {
				int a = scanner.nextInt();
				if (a == 1) {
					
					System.out.println("Enter the Monster's Name: ");
					scanner.nextLine();
					String name = scanner.nextLine();
					m_encounter.add_enemy(name);
					
				}
				else if (a == 0) {
					System.out.println("No new enemies will be added.");
					deleteEnemies(); 
					done = true; 
				}
				else {
					System.out.println(OneOrZeroException);
				}
			}
			catch (Exception e) {
				System.out.println(OneOrZeroException + "\n" + e);
				scanner.next(); 
			}
			
		} while (done == false);
		
	}
	
	/*
	 * Internal function of editEnemies, which gets users' input on which if any enemies should be deleted
	 * from the current Encounter and deletes or abstains accordingly 
	 */
	private static void deleteEnemies() {
		
		boolean done = false;
		do {
			
			if (m_encounter.get_enemies().size() == 0) {
				System.out.println("There are no enemies left to delete.");
				done = true; 
				continue; 
			}
			
			int i = 1;
			for (Monster enemy: m_encounter.get_enemies()) {
				System.out.println( i + ") " + enemy.get_name()); 
				i++; 
			}
			System.out.println("Do you want to delete an enemy? (Yes = 1/ No = 0)");
			try {
				int a = scanner.nextInt();
				if (a == 1) {
					
					i = 1;
					for (Monster enemy: m_encounter.get_enemies()) {
						System.out.println( i + ") " + enemy.get_name()); 
						i++; 
					}
					System.out.println("Enter the number of the enemy you want to delete.");
					
					int b = scanner.nextInt();
					
					Monster m = m_encounter.get_enemies().remove(b-1);
					
					System.out.println(m.get_name() + " has been deleted."); 
					
					m = null; 
					 
				}
				else if (a == 0 ) {
					System.out.println("No further enemies will be deleted.");
					done = true; 
				}
				else {
					System.out.println(OutOfRangeException);
				}
			}
			catch(Exception e) {
				System.out.println(OneOrZeroException + "\n" + e);
				scanner.next();
			}
			
		} while(done == false);
		
	}
	
	/*
	 * Verifies that the user wants to delete this Encounter and follows through accordingly 
	 */
	private static boolean delete_encounter () {
		boolean deleted = false; 
		boolean done = false;
		do {
			System.out.println("Are you sure you want to delete " + m_encounter.get_name() + "? (Yes = 1/ No = 0)");
			try {
				
				int a = scanner.nextInt();
				if (a == 1) {
					
					if (get_r_host() == null) {
						m_encounter.get_city().m_encounters.remove(m_encounter);
						m_encounter = null;
						done = true;
						deleted = true;
						System.out.println("The encounter was successfully deleted!");
					}
					else if (get_c_host() == null) {
						m_encounter.get_route().get_day_encounters().remove(m_encounter);
						m_encounter = null; 
						done = true; 
						deleted = true;
						System.out.println("The encounter was successfully deleted!");
					}
					else {
						System.out.println("Something went wrong, and the encounter was not deleted.");
					}
					
				}
				else if (a == 0) {
					System.out.println("Phew, that was close!");
					done = true; 
				}
				else {
					System.out.println(OneOrZeroException);
				}
			}
			catch (Exception e) {
				System.out.println(OneOrZeroException + "\n" + e);
				scanner.next();
			}
		} while (done == false); 
		return deleted; 
	}
	
	/*
	 * Gets user input on whether or not to save this Encounter to a file and follows through accordingly 
	 */
	private static void saveEncounter () {
		
		boolean done = false;
		do {
			System.out.println("Do you want to save " + m_encounter.get_name() + " to a file? (Yes = 1/ No = 0)");
			try {
				int a = scanner.nextInt();
				if (a == 1) {
					FileCreator encounter_file = new FileCreator(m_encounter);
					System.out.println(m_encounter.get_name() + " has been saved to a file.");
					done = true;
				}
				else if (a == 0) {
					System.out.println(m_encounter.get_name() + " will not be saved.");
					done = true; 
				}
				else {
					System.out.println(OneOrZeroException);
				}
			}
			catch (Exception e) {
				System.out.println(OneOrZeroException + "\n" + e);
				scanner.next();
			}
		} while (done == false);
	}
	
}
