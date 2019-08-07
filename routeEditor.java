package Dnd;

public class routeEditor extends WorldEditor {
	/*
	 * Runs UI for editing the Country's Routes
	 */
	
	/* 
	 * Main function of routeEditor
	 * Gets user input on choices given in listRouteChoices()
	 */
	public static void edit() {
		
		System.out.println("-----Route Editor-----\n");
		System.out.println("Which Route would you like to edit?");
		
		boolean done = false;
		do {
			listRouteChoices(); 
			System.out.println("Type the number of the route you want to edit.");
			try {
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > (m_country.get_routes().size() + 2)) {
					System.out.println(OutOfRangeException);
				}
				else {
					done = parseRouteChoices(a); 
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
	 * Lists all Routes in the Country along with an option to return to the main WorldEditor menu
	 * as well as a choice to add a new Route. 
	 */
	private static void listRouteChoices () {
		
		String output = ""; 
		int routeCount = m_country.get_routes().size(); 
		System.out.println("Route Count: " + routeCount);
		
		output = output + "1) " + "Return to Country Editor \n";
		output = output + "2) " + "Add new route \n";
		if (routeCount >= 1) {
			output = output + "3) " + m_country.get_routes().get(0).get_name() +"\n";
		}
		if (routeCount >= 2) {
			output = output + "4) " + m_country.get_routes().get(1).get_name()+ "\n";
		}
		if (routeCount >= 3) {
			output = output + "5) " + m_country.get_routes().get(2).get_name() + "\n";
		}
		if (routeCount >= 4) {
			output = output + "6) " + m_country.get_routes().get(3).get_name()+ "\n";
		}
		if (routeCount >= 5) {
			output = output + "7) " + m_country.get_routes().get(4).get_name()+ "\n";
		}
		if (routeCount >= 6) {
			output = output + "8) " + m_country.get_routes().get(6).get_name()+ "\n";
		}
		/*
		 * Add additional lines if you plan on having a country with more than 6 cities
		 */
		System.out.println(output);  
	}
	
	/*
	 * Chooses to return to the previous menu, add a Route, or which Route to edit based on user input from edit()
	 * 
	 * @param	choice Integer 	:the integer value that the user input as the answer to the list of route choices
	 */
	private static boolean parseRouteChoices (int choice) {
		
		boolean done = false;
		//System.out.println("Parsing choices...");
		switch (choice) {
			case 1:
				System.out.println("Exiting Route Editor...");
				done = true;
				break;
			case 2:
				System.out.println("Entering Route Constructor...");
				//scanner.nextLine(); 
				Worldbuilder.contructRoutes(); 
				int i = m_country.get_routes().size(); 
				if (i > 0) {
					m_route = m_country.get_routes().get(i-1);
					m_route.get_origin().get_routes().add(m_route);
					m_route.get_destination().get_routes().add(m_route);
				}
				else {
					System.out.println("You need at least 2 cities to build a route. Try building some more cities first.");
				}
				break;
			case 3:
				m_route = m_country.get_routes().get(0); 
				parseRouteEdits(); 
				break;
			case 4:
				m_route = m_country.get_routes().get(1);
				parseRouteEdits(); 
				break;
			case 5:
				m_route = m_country.get_routes().get(2);
				parseRouteEdits(); 
				break;
			case 6:
				m_route = m_country.get_routes().get(3);
				parseRouteEdits(); 
				break;
			case 7:
				m_route = m_country.get_routes().get(4);
				parseRouteEdits(); 
				break;
			case 8:
				m_route = m_country.get_routes().get(5);
				parseRouteEdits(); 
				break;
		}
		return done; 
	}
	
	/*
	 * Gets user input on which Route attribute from listRouteEdits() they want to edit
	 */
	private static void parseRouteEdits() {
		
		boolean done = false;
		do {
			try {
				listRouteEdits();
				System.out.println("Type the number of the attribute you want to edit.");
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > 9) {
					System.out.println(OutOfRangeException);
				}
				else {
					done = editAttributes(a);
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
	 * Lists all options for editing individuals routes 
	 */
	private static void listRouteEdits() {
		
		System.out.println("What changes do you want to make to " + m_route.get_name() + "?");
		String output = "";
		output = output + "1) Name (Unavailable) \n";
		output = output + "2) Description \n";
		output = output + "3) Length \n";
		output = output + "4) Terrain Type \n";
		output = output + "5) Encounters \n";
		output = output + "6) View Route Details \n";
		output = output + "7) Delete Route \n";
		output = output + "8) Save Route \n";
		output = output + "9) Return to Route Choices \n";
		System.out.println(output); 
	}
	
	/*
	 * Edits a Route's attributes according to the user's input on which they want to edit
	 * 
	 * @param	choice Integer	:the integer value that the user input as the answer to the list of route edits
	 * 
	 * @return	done boolean	:true if user chose to delete this route or opted to return to the previous menu
	 * 							:false otherwise, allowing user to continue to edit this Route
	 */
	public static boolean editAttributes (int choice) {
		boolean done = false;
		switch (choice) {
			case 1:
				System.out.println("Name editing is not yet avialable.");
				break;
			case 2:
				scanner.nextLine();
				System.out.println("What would you like the new decsription to be?");
				String descrip = scanner.nextLine();
				m_route.set_description(descrip);
				System.out.println("Okay, the new description is: ");
				System.out.println(m_route.get_description());
				break; 
			case 3:
				Worldbuilder.constructLength(); 
				break;
			case 4:
				Worldbuilder.constructTerrain(m_route);
				break;
			case 5:
				encounterEditor.edit(m_route); 
				break; 
			case 6:
				System.out.println(m_route.get_all_info());
				break;
			case 7:
				done = deleteRoute(); 
				break; 
			case 8:
				Worldbuilder.saveRoute();
				break;
			case 9:
				done = true; 
				break; 
		}
		return done; 
	}
	
	/*
	 * Takes user input on which encounters to delete from this Route and then removes those encounters
	 */
	private static void deleteEncounters () {
		
		boolean done = false; 
		do {
			try {
				int n = 0; 
				System.out.println("Which encounter do you want to delete?");
				for (int i = 0; i < m_route.get_day_encounters().size(); i++) {
					System.out.println((i+1) +") " + m_route.get_day_encounters().get(i).get_encounter_name());
					n = i+1; 
				}
				n++; 
				System.out.println(n + ")  Return to Route Editor");
				System.out.println("Type the number of the encounter you want to delete.");
				int a = scanner.nextInt();
				if (a < 1) {
					System.out.println(OutOfRangeException);
				}
				else if (a > (m_route.get_day_encounters().size()+2)) {
					System.out.println(OutOfRangeException);
				}
				else if (a == n) {
					done = true; 
				}
				else {
					System.out.println("Removing Encounter #" + a + "...");
					m_route.get_day_encounters().remove(a-1);
				}
			}
			catch (Exception e) {
				System.out.println(MustBeIntException);
				//System.out.println("Error resulting from:  " + e);
				scanner.next(); 
			}
		}while (done == false);
	}
	
	/*
	 * Verifies that the user wants this Route to be deleted, then removes it from the Cities and Country
	 * before it deletes the Route object. 
	 */
	private static boolean deleteRoute () {
		
		boolean done = false;
		do {
			try {
				System.out.println("Are you sure you want to delete " + m_route.get_name() + "? (Yes = 1/ No = 0)");
				int a = scanner.nextInt();
				if (a == 1) {
					m_country.get_routes().remove(m_route);
					for (City i: m_country.get_cities()) {
						i.get_routes().remove(m_route);
					}
					m_route = null; 
					done = true;
				}
				else if (a ==0) {
					System.out.println("Phew that was close!");
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
		return done; 
	}
}
