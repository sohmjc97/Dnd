package Dnd;

import java.util.Scanner;

public class Main {

	//This is the main D&D program File 
	//Here is where we actually customize our world 
	public static void main (String args[]) {
		Scanner scanner = new Scanner(System.in);
		boolean done = false;
		do {
			try {
				System.out.println("Welcomee to the DM Control Room!");
				System.out.println("Would you like to 1) Edit an Old World or 2) Create a new ome?");
				int a = scanner.nextInt();
				
				if (a == 1) {
					WorldEditor.edit(); 
					done = true;
				}
				else if (a == 2) {
					Worldbuilder.build(); 
					
					System.out.println("# of countries: " + Worldbuilder.get_country_count());
					System.out.println("Countries: " + Worldbuilder.get_country_list());
					System.out.println("Total # of cities: " + Worldbuilder.get_global_city_count());
					System.out.println("Total Cities: " + Worldbuilder.get_global_city_list());
					System.out.println("Total # of routes: " + Route.get_route_count());
					done = true;
				}
				else {
					System.out.println("Answer must be 1 oe 2. Try again.");
				}
			}
			catch (Exception e) {
				System.out.println("Answer must be 1 oe 2. Try again.");
				scanner.next();
			}
		} while (done == false);
		
		/*
		 * These run at end to give info about the state of the last active 
		 * City and Route for debugging purposes. 
		 */
		try {
			System.out.println(WorldEditor.m_city.get_all_info());
		}
		catch (NullPointerException e) {
			System.out.println("No City was active, so no info could be returned.");
		}
		try {
			System.out.println(WorldEditor.m_route.get_all_info());
		}
		catch (NullPointerException e) {
			System.out.println("No Route was active, so no info could be returned.");
		}
		scanner.close();
	}

}
