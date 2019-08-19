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
				else if (a > (m_route.get_all_encounters().size() + 2)) {
					System.out.println(OutOfRangeException);
				}
				else {
					done = parseEncounterChoices(a); 
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
	 * Main Encounter Editor Function for Encounters belonging to Cities
	 * Gives Choices for which Encounters to edit and gets user answer
	 */
	public static void edit (City city) {
		
		c_host = city; 
		r_host = null; 
		
		System.out.println("----- Encounter Editor -----");
		//System.out.println("Which Encounter would you like to edit?");
		
		boolean done = false;
		do {
			listEncounterChoices(c_host); 
			System.out.println("Type the number of the Encounter you want to edit.");
			try {
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > (m_city.get_all_encounters().size() + 2)) {
					System.out.println(OutOfRangeException);
				}
				else {
					done = parseEncounterChoices(a); 
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
	 * Lists options for Route Encounters
	 * Prints a list of options for user to choose from
	 * They can return to previous menu, add a new Encounter or
	 * edit any of the old ones 
	 */
	private static void listEncounterChoices(Route route) {
		String output = "\n"; 
		int encounterCount = m_route.get_all_encounters().size(); 
		int dayEncounterCount = m_route.get_day_encounters().size();
		int nightEncounterCount = m_route.get_night_encounters().size();
		System.out.println("Encounter Count: " + encounterCount);
		
		output = output + "1) " + "Return to Route Editor \n";
		output = output + "2) " + "Add new encounter \n";
		output = output + "\n <<< Day Encounters >>> \n";
		for (int i = 0; i < dayEncounterCount; i++) {
			output = output + (i+3) + ") " + m_route.get_day_encounters().get(i).get_name() + "\n"; 
		}
		output = output + " \n <<< Night Encounters >>> \n";
		int x = 0;
		for (int j = dayEncounterCount; j < encounterCount; j++) {
			output = output + (j+3) + ") " + m_route.get_night_encounters().get(x).get_name() + "\n"; 
			x++;
		}
		System.out.println(output);  
		
	}
	
	/*
	 * Lists options for City Encounters
	 * Prints a list of options for user to choose from
	 * They can return to previous menu, add a new Encounter or
	 * edit any of the old ones 
	 */
	private static void listEncounterChoices(City city) {
		String output = "\n"; 
		int encounterCount = m_city.get_all_encounters().size(); 
		int dayEncounterCount = m_city.get_day_encounters().size();
		int nightEncounterCount = m_city.get_night_encounters().size();
		System.out.println("Encounter Count: " + encounterCount);
		
		output = output + "1) " + "Return to City Editor \n";
		output = output + "2) " + "Add new encounter \n";
		output = output + "\n <<< Day Encounters >>> \n";
		for (int i = 0; i < dayEncounterCount; i++) {
			output = output + (i+3) + ") " + m_city.get_day_encounters().get(i).get_name() + "\n"; 
		}
		output = output + " \n <<< Night Encounters >>> \n";
		int x = 0;
		for (int j = dayEncounterCount; j < encounterCount; j++) {
			output = output + (j+3) + ") " + m_city.get_night_encounters().get(x).get_name() + "\n"; 
			x++;
		}
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
		
		if (choice == 1) {
			done = true;
		}
		else if (choice == 2) {
			System.out.println("Entering Encounter Constructor...");
			//scanner.nextLine(); 
			
			if (c_host == null) {
				Worldbuilder.addEncounters(m_route);
				int i = m_route.get_all_encounters().size(); 
				m_encounter = m_route.get_all_encounters().get(i-1);
				m_encounter.autoSave();
			}
			else if (r_host == null) {
				Worldbuilder.addEncounters(m_city);
				int i = m_city.get_all_encounters().size();
				m_encounter = m_city.get_all_encounters().get(i-1); 
				m_encounter.autoSave();
			}
			else {
				System.out.println("Program encountered a problem.");
			}
		}
		else {
			if (c_host == null) {
				if (choice > m_route.get_day_encounters().size() +2) {
					m_encounter = m_route.get_night_encounters().get(choice - m_route.get_day_encounters().size() -3); 
				}
				else {
					m_encounter = m_route.get_day_encounters().get(choice - 3); 
				} 
			}
			else if (r_host == null) {
				if (choice > m_route.get_day_encounters().size() +2) {
					m_encounter = m_city.get_night_encounters().get(choice - m_route.get_day_encounters().size() -3); 
				}
				else {
					m_encounter = m_city.get_day_encounters().get(choice - 3); 
				} 
			}
			else {
				System.out.println("Program encountered a problem.");
			}
			getEncounterEdits(); 
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
				else if (a > 7) {
					System.out.println(OutOfRangeException);
				}
				else {
					done = parseEncounterEdits(a);
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
	 * Lists all options for editing individuals Encounters 
	 */
	private static void listEncounterEdits() {
		System.out.println("What changes do you want to make to " + m_encounter.get_name() + "?");
		String output = "";
		output = output + "1) Return to Encounter Selection \n";
		output = output + "2) Description \n";
		output = output + "3) Host Location \n";
		output = output + "4) Enemies \n";
		output = output + "5) View Encounter Details \n";
		output = output + "6) Save Encounter \n";
		output = output + "7) Delete Encounter \n";
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
				System.out.println("Returning to Encounter Selection...");
				done = true;
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
				done = switchHost(); 
				break;
			case 4:
				monsterEditor.edit(); 
				break; 
			case 5:
				m_encounter.list_all_info();
				break;
			case 6:
				boolean saved = saveEncounter(); 
				if (saved) {
					for (Monster m: m_encounter.get_enemies()) {
						m.autoSave();
					}
				}
				break; 
			case 7:
				done = delete_encounter(); 
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
						System.out.println(GenericException);
						//System.out.println("Error resulting from: " + e);
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
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from:  " + e);
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
						m_encounter.get_city().deleteEncounter(m_encounter);
						m_encounter = null;
						done = true;
						deleted = true;
						System.out.println("The encounter was successfully deleted!");
					}
					else if (get_c_host() == null) {
						m_encounter.get_route().deleteEncounter(m_encounter);
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
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from:  " + e);
				scanner.next(); 
			}
		} while (done == false); 
		return deleted; 
	}
	
	/*
	 * Gets user input on whether or not to save this Encounter to a file and follows through accordingly 
	 */
	public static boolean saveEncounter () {
		
		boolean saved = true; 
		boolean done = false;
		do {
			System.out.println("Do you want to save " + m_encounter.get_name() + " to a file? (Yes = 1/ No = 0)");
			try {
				int a = scanner.nextInt();
				if (a == 1) {
					m_encounter.autoSave(); 
					System.out.println(m_encounter.get_name() + " has been saved to a file.");
					done = true;
					saved = true;
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
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from:  " + e);
				scanner.next(); 
			}
		} while (done == false);
		return saved; 
	}
	
	/*
	 * Gets user input as to whether the Encounter should be moved to a new Host Location,
	 * which Host Location if so, and executes the move
	 */
	private static boolean switchHost () {
		
		boolean switched = false;
		boolean done = false;
		do {
			System.out.println("Would you like to change where this Encounter takes place? (Yes = 1/ No = 0)");
			try {
				int a = scanner.nextInt();
				if (a == 1) {
					getNewLocation();
					switched = true; 
					done = true; 
				}
				else if (a == 0) {
					System.out.println("Location will not be changed.");
					done = true;
				}
				else {
					System.out.println(OneOrZeroException);
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException);
				System.out.println("Error resulting from:  " + e);
				scanner.next(); 
			}
			
		} while (done == false);
		return switched;
	}
	
	/*
	 * Gets user input as to which Route or City the Encounter should be moved to
	 */
	private static void getNewLocation () {
		
		boolean done = false;
		do {
			int n = 1;
			/*System.out.println("---Cities---");
			System.out.println("Encounter: " + m_encounter.get_name());
			System.out.println("Country: " + m_encounter.get_country().get_country_name());
			System.out.println("Cities: " + m_encounter.get_country().get_cities()); */
			for (City city: m_encounter.get_country().get_cities()) {
				String encounters = "" ;
				for (Encounter e: city.get_all_encounters()) {
					encounters = encounters + " :: " + e.get_name();
				}
				System.out.println(n + ") " + city.get_name() + encounters);
				n++;
			}
			int m = n;
			System.out.println("---Routes---");
			for (Route route: m_encounter.get_country().get_routes()) {
				String encounters = "" ;
				for (Encounter e: route.get_all_encounters()) {
					encounters = encounters + " :: " + e.get_name();
				}
				System.out.println(m + ") " + route.get_name() + encounters);
				m++;
			}
			System.out.println("Type the number of the new location: ");
			
			try {
				int a = scanner.nextInt();
				if (a < 0) {
					System.out.println(OutOfRangeException);
				}
				else if (a > m-1) {
					System.out.println(OutOfRangeException);
				}
				else {
					if (a < n) {
						City choice = m_encounter.get_country().get_cities().get(a-1);
						System.out.println("You have chosen " + choice.get_name());
						for (Encounter e: choice.get_all_encounters()) {
							if (e.get_name() == m_encounter.get_name()) {
								System.out.println(choice.get_name() + " already has this encounter. Host was not changed"); 
								continue;
							}
						}
						m_encounter.change_host(choice);
					}
					else {
						Route choice = m_encounter.get_country().get_routes().get(a-n);
						System.out.println("You have chosen " + choice.get_name());
						for (Encounter e: choice.get_all_encounters()) {
							if (e.get_name() == m_encounter.get_name()) {
								System.out.println(choice.get_name() + " already has this encounter. Host was not changed."); 
								continue;
							}
						}
						m_encounter.change_host(choice);
					}
					m_encounter = null; 
					done = true; 
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException);
				System.out.println("Error resulting from:  " + e);
				scanner.next(); 
			}
			
		} while (done == false);
		
	}
	
}
