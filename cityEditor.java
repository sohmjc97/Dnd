package Dnd;

import Dnd.Country.CityType;

public class cityEditor extends WorldEditor {
	/*
	 * Runs UI for editing Cities 
	 */

	/*
	 * Main function for cityEditor
	 * Gets user input on which option from listCityChoices they want to do
	 */
	public static void edit() {
		
		boolean done = false;
		System.out.println("Welcome to City Editor!");
		do {
			System.out.println("Which city would you like to edit?");
			listCityChoices();
			try {
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > (m_country.m_cities.size() + 2)) {
					System.out.println(OutOfRangeException);
				}
				else {
					done = parseCityChoice(a); 
				}
			}
			catch(Exception e) {
				System.out.println(MustBeIntException);
				scanner.next();
			}
		} while (done == false); 
		
	}
	
	/*
	 * Lists all options for cityEditor, including to add a new CIty, return to previous menu, or edit any existing City
	 */
	private static void listCityChoices () {
		
		String output = ""; 
		int cityCount = m_country.get_cities().size(); 
		//System.out.println("City Count: " + n);
		output = output + "1) " + "Return to Country Editor \n";
		output = output + "2) " + "Add new city \n";
		
		for (int i = 0; i< cityCount; i++) {
			output = output + (i+3) + ") " + m_country.get_cities().get(i).get_name() + "\n"; 
		}
		System.out.println(output);
		
	}
	
	/*
	 * Chooses to return to previous menu, add a City, or edit a City based on user input in edit()
	 */
	private static boolean parseCityChoice(int choice) {
		
		//System.out.println("Parsing choices");
		boolean done = false;
		
		if (choice == 1) {
			System.out.println("Returning to Country Menu");
			done = true;
		}
		else if (choice == 2) {
			scanner.nextLine(); 
			Worldbuilder.cityCreator(); 
			int i = m_country.get_cities().size(); 
			m_city = m_country.get_cities().get(i-1);
		}
		else {
			m_city = m_country.get_cities().get(choice - 3); 
			parseCityEdits();
		}
		return done; 
		
	}
	
	/*
	 * Lists all options for City attribute edits 
	 */
	private static void listCityEdits() {
		
		String output = "What changes would you like to make to " + m_city.get_name() + "\n";
		output = output + "1) Return to City Selection \n";
		output = output + "2) Description \n";
		output = output + "3) Age \n";
		output = output + "4) Population \n";
		output = output + "5) Ruler \n";
		output = output + "6) City Type \n";
		output = output + "7) Terrain Type \n";
		output = output + "8) NPC's \n";
		output = output + "9) Buildings \n";
		output = output + "10) Encounters \n";
		output = output + "11) View City Details \n";
		output = output + "12) Save City \n";
		output = output + "13) Delete City \n";
		System.out.println(output); 
	}
	
	/*
	 * Gets user input on which city attributes to edit
	 */
	private static void parseCityEdits () {
		
		boolean done = false; 
		do {
			try {
				listCityEdits(); 
				System.out.println("Type the number of the thing you want to edit.");
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > 13) {
					System.out.println(OutOfRangeException);
				}
				else {
					done = parseCityEditsChoice(a); 
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException);
				scanner.next();
			}
		} while (done == false);
		
	}
	
	/*
	 * Chooses which city attribute to edit based on user input from parseCityEdits() 
	 */
	private static boolean parseCityEditsChoice(int choice) {
		boolean done = false;
		switch(choice) {
			case 1:
				System.out.println("Returning to City Selection...");
				done = true;
				break;
			case 2:
				System.out.println("What should the new description be?");
				scanner.nextLine(); 
				m_city.set_description(scanner.nextLine());
				System.out.println("Description has been set to: \n" + m_city.get_description());
				break;
			case 3: 
				Worldbuilder.constructAge(); 
				break;
			case 4:
				Worldbuilder.constructPopulation();
				break;
			case 5:
				Worldbuilder.constructLocalRuler();
				break; 
			case 6:
				boolean capital = false;
				if (m_city.get_ctype() == CityType.CAPITAL) {
					capital = true; 
				}
				System.out.println("Note: If this city is a CAPITAL, you cannot change its city type.");
				Worldbuilder.constructCityType(capital);
				break;
			case 7:
				Worldbuilder.constructTerrain();
				break;
			case 8:
				Worldbuilder.constructNPCs();
				if (m_city.get_NPCs().size() == 0) {
					System.out.println("There aren't any NPC's to delete, so we'll skip that step.");
				}
				else {
					delete_NPCs(); 
				}
				break;
			case 9:
				Worldbuilder.customizeBuildings();
				break;
			case 10:
				encounterEditor.edit(m_city);
				break;
			case 11:
				m_city.list_all_info();
				break;
			case 12:
				boolean saved = Worldbuilder.saveCity();
				if (saved) {
					System.out.println("Saving " + m_city.get_name());
					for (Encounter e: m_city.get_all_encounters()) {
						for (Monster m: e.get_enemies()) {
							m.autoSave();
							System.out.println("Saving " + m.get_name());
						}
						e.autoSave();
						System.out.println("Saving " + e.get_name());
					}
					System.out.println(m_city.get_name() + " and all it contains have been saved.");
				}
				break; 
			case 13:
				done = delete_city(); 
				break;
		}
		return done; 
	}
	
	/*
	 * If there are no NPC's, this will do nothing but tell the user that. 
	 * If there are NPC's, it will verify that the user wants to delete NPC's before continuing. 
	 * The user will be shown a list of NPC's and be asked to enter the number of the one they want to delete. 
	 * This will continue until the user inputs that they are done deleting NPC's or the NPC's are all deleted. 
	 */
	private static void delete_NPCs () {
		
		boolean done = false;
		do {
			try {
				if (m_city.get_NPCs().size() == 0) {
					System.out.println("There are no more NPC's left.");
					done = true; 
					continue; 
				}
				else {
					System.out.println("Do you want to delete any NPC's ? (Yes = 1/ No = 0)");
					int a = scanner.nextInt();
					if (a == 1) {
						boolean over = false; 
						executeDeletion(); 
					}
					else if (a == 0) {
						System.out.println("No further NPCs will be deleted.");
						done = true;
					}
					else {
						System.out.println(OneOrZeroException);
					}
				}
			}
			catch (Exception e) {
				System.out.println(OneOrZeroException);
				scanner.next();
			}
		} while (done == false);
	}
		
	/*
	 * Gets user input as to which NPC to delete and deletes it 
	 */
	private static void executeDeletion() {
		
		boolean done = false; 
		do {
			System.out.println("What NPC do you want to delete?");
			
			int i = 1;
			for (int x = 0; x < m_city.get_NPCs().size(); x++) {
				System.out.println(i + ") " + m_city.get_NPCs().get(x));
				i++;
			}
			
			System.out.println("Enter the number of the NPC you want to delete.");
			
			try {
				int a = scanner.nextInt(); 
				String name = "";
				
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > m_city.get_NPCs().size()) {
					System.out.println(OutOfRangeException);
				}
				else {
					name = m_city.get_NPCs().get(a-1).toString();
				}
				
				boolean removed = m_city.delete_NPC(name);
				if (removed) {
					System.out.println(name + " has been successfully deleted.");
					done = true; 
				}
				else {
					System.out.println("There was a problem deleting the NPC. Try again.");
				}
				
			}
			catch (Exception e) {
				System.out.println(MustBeIntException);
				System.out.println("Error resulting from: " + e);
				scanner.next();
			}
		} while (done == false);
		
	}
	
	/*
	 * Verifies that the user wants to delete this City. If they don't, they will return to the previous menu.
	 * If they do want to delete it, the city will be removed from the Country, all routes that connected the
	 * City to other Cities will be deleted as well, and the CIty object itself will be deleted. 
	 */
	private static boolean delete_city () {
		
		boolean deleted = false;
		boolean over = false;
		do {
			//scanner.next();
			System.out.println("Are you sure you want to delete " + m_city.get_name() + "? (Yes = 1/ No = 0)");
			System.out.println("Remember, if you delete this city, you will also delete all routes that connect it to other cities.");
			try {
					int a = scanner.nextInt(); 
					if (a == 1) {
						m_country.delete_routes(m_city);
						m_country.delete_city(m_city);
						m_city = null;
						deleted = true;
						over = true;
					}
					else if (a == 0) {
						System.out.println("Wheew, that was close!");
						over = true;
					}
					else {
						System.out.println(OneOrZeroException);
					}
			}
			catch(Exception e) {
				System.out.println(OneOrZeroException);
				scanner.next(); 
			}
		} while (over == false);
		return deleted; 
	}
}
