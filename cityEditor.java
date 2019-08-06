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
			System.out.println(listCityChoices());
			try {
				int a = scanner.nextInt();
				if (a <= 0) {
					System.out.println(OutOfRangeException);
				}
				else if (a > (m_country.m_cities.size() + 3)) {
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
	private static String listCityChoices () {
		
		String output = ""; 
		int n = m_country.get_cities().size(); 
		//System.out.println("City Count: " + n);
		int i = 0; 
		output = output + "1) " + "Return to Country Editor \n";
		output = output + "2) " + "Add new city \n";
		if (n >= 1) {
			output = output + "3) " + m_country.get_cities().get(0).get_name() +"\n";
		}
		if (n >= 2) {
			output = output + "4) " + m_country.get_cities().get(1).get_name()+ "\n";
		}
		if (n >= 3) {
			output = output + "5) " + m_country.get_cities().get(2).get_name() + "\n";
		}
		if (n >= 4) {
			output = output + "6) " + m_country.get_cities().get(3).get_name()+ "\n";
		}
		if (n >= 5) {
			output = output + "7) " + m_country.get_cities().get(4).get_name()+ "\n";
		}
		if (n >= 6) {
			output = output + "8) " + m_country.get_cities().get(6).get_name()+ "\n";
		}
		//can add more later when needed
		return output; 
		
	}
	
	/*
	 * Chooses to return to previous menu, add a City, or edit a City based on user input in edit()
	 */
	private static boolean parseCityChoice(int a) {
		
		//System.out.println("Parsing choices");
		boolean done = false;
		switch (a) {
			case 1:
				done = true;
				break;
			case 2:
				scanner.nextLine(); 
				Worldbuilder.cityCreator(); 
				// need to get city back from there and added into now 
				int i = m_country.get_cities().size(); 
				m_city = m_country.get_cities().get(i-1);
				break;
			case 3:
				m_city = m_country.get_cities().get(0); 
				parseCityEdits(); 
				break;
			case 4:
				m_city = m_country.get_cities().get(1);
				parseCityEdits(); 
				break;
			case 5:
				m_city = m_country.get_cities().get(2);
				parseCityEdits(); 
				break;
			case 6:
				m_city = m_country.get_cities().get(3);
				parseCityEdits(); 
				break;
			case 7:
				m_city = m_country.get_cities().get(4);
				parseCityEdits(); 
				break;
			case 8:
				m_city = m_country.get_cities().get(5);
				parseCityEdits(); 
				break;
		}
		return done; 
	}
	
	/*
	 * Lists all options for City attribute edits 
	 */
	private static void listCityEdits() {
		
		String output = "What changes would you like to make to " + m_city.get_name() + "\n";
		output = output + "1) Name (Currently not allowed) \n";
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
		output = output + "14) Return to City Choice Menu \n";
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
				if (a <= 0) {
					System.out.println(OutOfRangeException);
				}
				else if (a > 14) {
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
				System.out.println("Name changes aren't yet allowed.");
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
				//edit_NPCs(); Unnecessary, save for later
				delete_NPCs(); 
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
				Worldbuilder.saveCity();
				break; 
			case 13:
				delete_city(); 
				done = true;
				break;
			case 14:
				done = true; 
				break;
		}
		return done; 
	}
	
	/* This is not super necessary, implement when bored 
	 * private static void edit_NPCs () {
		boolean done = false; 
		do {
			try {
				System.out.println("Do you want to add on to ")
			}
			catch (Exception e) {
				System.out.println(MustBeIntException);
				scanner.next();
			}
		} while (done == false);
	} */
	
	/*
	 * If there are no NPC's, this will do nothing but tell the user that. 
	 * If there are NPC's, it will verify that the user wants to delete NPC's before continuing. 
	 * The user will be shown a list of NPC's and be asked to enter the number of the one they want to delete. 
	 * This will continue until the user inputs that they are done deleting NPC's or the NPC's are all deleted. 
	 */
	private static void delete_NPCs () {
		
		boolean done = false;
		if (m_city.get_NPCs().size() == 0) {
			System.out.println("There aren't any NPC's to delete, so we'll skip that step.");
		}
		else {
			do {
				try {
					if (m_city.get_NPCs().size() == 0) {
						System.out.println("There are no more NPC's left.");
						done = true; 
						continue; 
					}
					
					System.out.println("Do you want to delete any NPC's ? (Yes = 1/ No = 0)");
					int a = scanner.nextInt();
					if (a == 1) {
						boolean over = false; 
						do {
							if (m_city.get_NPCs().size() == 0) {
								
								System.out.println("There are no more NPC's.");
								over = true; 
								continue; 
							}
							else {
								
								System.out.println("What NPC do you want to delete?");
								
								int i = 1;
								for (int x = 0; x < m_city.get_NPCs().size(); x++) {
									System.out.println(i + ") " + m_city.get_NPCs().get(x));
									i++;
								}
								
								System.out.println("Enter the number of the NPC you want to delete.");
								int b = scanner.nextInt(); 
								
								String name = "";
								
								if (b < 1) {
									System.out.println(OutOfRangeException);
								}
								else if (b > m_city.get_NPCs().size()) {
									System.out.println(OutOfRangeException);
								}
								else {
									name = m_city.get_NPCs().get(b-1).toString();
								}
								
								boolean removed = m_city.delete_NPC(name);
								if (removed) {
									System.out.println(name + " has been successfully deleted.");
									over = true; 
								}
								else {
									System.out.println("There was a problem. Try again.");
								}
							}
						} while (over == false);
					}
					else if (a == 0) {
						System.out.println("No further NPCs will be deleted.");
						done = true;
					}
					else {
						System.out.println(OneOrZeroException);
					}
				}
				catch (Exception e) {
					System.out.println(OneOrZeroException);
					scanner.next();
				}
			} while (done == false);
		}
	}
	
	/*
	 * Verifies that the user wants to delete this City. If they don't, they will return to the previous menu.
	 * If they do want to delete it, the city will be removed from the Country, all routes that connected the
	 * City to other Cities will be deleted as well, and the CIty object itself will be deleted. 
	 */
	private static void delete_city () {
		
		boolean over = false;
		do {
			//scanner.next();
			System.out.println("Are you sure you want to delete " + m_city.get_name() + "? (Yes = 1/ No = 0)");
			try {
					int a = scanner.nextInt(); 
					if (a == 1) {
						m_country.delete_routes(m_city);
						m_country.delete_city(m_city);
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
		m_city = null;
	}
}
