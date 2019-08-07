package Dnd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSearch {
	/*
	 * Used to parse text files to find certain keywords and return related information as Strings
	 */
	
	/*
	 * Searches Country text files to find lines on which the search String is found
	 * 
	 * @param 	fileName String			:the name of the Country/ the Country's text file
	 * @param 	searchStr String		:the String that is to be searched for in the text file 
	 * @throws	FileNotFoundException	:if a file matching the fileName cannot be found or opened
	 * @return	answer String			:contains all the lines in which the search String was found 
	 */
    public String parseFile(String fileName, String searchStr) throws FileNotFoundException{
    	String path = "C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + fileName + "\\" + fileName + ".txt";
        Scanner scan = new Scanner(new File(path));
        String answer = "";
        while(scan.hasNext()){
            String line = scan.nextLine().toString();
            if(line.contains(searchStr)){
            	if (answer == "") {
            		answer = line;
            	}
            	else {
            		answer = answer + line;
            	}
            }
        }
        scan.close();
        return answer;
    }
    
    /*
	 * Searches City text files to find lines on which the search String is found
	 * 
	 * @param 	fileName String			:the name of the City/ the City's text file
	 * @param 	searchStr String		:the String that is to be searched for in the text file 
	 * @throws	FileNotFoundException	:if a file matching the fileName cannot be found or opened
	 * @return	answer String			:contains all the lines in which the search String was found 
	 */
    public String parseFile(String fileName, String searchStr, Country country) throws FileNotFoundException{
    	//For Cities
    	String path = "C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + country.get_country_name() + "\\Cities\\" + fileName + "\\" + fileName + ".txt";
        Scanner scan = new Scanner(new File(path));
        String answer = "";
        while(scan.hasNext()){
            String line = scan.nextLine().toString();
            if(line.contains(searchStr) | answer.contains("Buildings")){
            	if (answer == "") {
            		answer = line;
            	}
            	else if (line.contains("---")) {
            		continue; 
            	}
            	else {
            		answer = answer + "\n" + line;
            	}
            }
        }
        scan.close();
        return answer;
    }
    
    /*
	 * Searches Route text files to find lines on which the search String is found
	 * 
	 * @param 	fileName String			:the name of the Route/ the Route's text file
	 * @param 	searchStr String		:the String that is to be searched for in the text file 
	 * @param 	token String			:pass in any String token to distinguish Route parseFile from City parseFile
	 * @throws	FileNotFoundException	:if a file matching the fileName cannot be found or opened
	 * @return	answer String			:contains all the lines in which the search String was found 
	 */
    public String parseFile(String fileName, String searchStr, Country country, String token) throws FileNotFoundException{
    	String path = "C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + country.get_country_name() + "\\Routes\\" + fileName + "\\" + fileName + ".txt";
    	
        Scanner scan = new Scanner(new File(path));
        String answer = "";
        while(scan.hasNext()){
            String line = scan.nextLine().toString();
            if(line.contains(searchStr)){
            	if (answer == "") {
            		answer = line;
            	}
            	else {
            		answer = answer + "\n" + line;
            	}
            }
        }
        scan.close();
        return answer;
    }
    
    /*
	 * Searches Route Encounter text files to find lines on which the search String is found
	 * 
	 * @param 	fileName String			:the name of the Route/ the Route's text file
	 * @param 	searchStr String		:the String that is to be searched for in the text file 
	 * @param 	route Route				:the Route on which this Encounter takes place 
	 * @throws	FileNotFoundException	:if a file matching the fileName cannot be found or opened
	 * @return	answer String			:contains all the lines in which the search String was found 
	 */
    public String parseFile(String fileName, String searchStr, Country country, Route route) throws FileNotFoundException{
    	String path = "C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + country.get_country_name() + "\\Routes\\" + route.get_name() + "\\Encounters\\" + fileName + "\\" + fileName + ".txt";
    	//System.out.println(path);
        Scanner scan = new Scanner(new File(path));
        String answer = "";
        while(scan.hasNext()){
            String line = scan.nextLine().toString();
            if(line.contains(searchStr)){
            	if (answer == "") {
            		answer = line;
            	}
            	else {
            		answer = answer + "\n" + line;
            	}
            }
        }
        scan.close();
        return answer;
    }
    
    /*
	 * Searches City Encounter text files to find lines on which the search String is found
	 * 
	 * @param 	fileName String			:the name of the Route/ the Route's text file
	 * @param 	searchStr String		:the String that is to be searched for in the text file 
	 * @param 	city City				:the City in which this Encounter takes place 
	 * @throws	FileNotFoundException	:if a file matching the fileName cannot be found or opened
	 * @return	answer String			:contains all the lines in which the search String was found 
	 */
    public String parseFile(String fileName, String searchStr, Country country, City city) throws FileNotFoundException{
    	String path = "C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + country.get_country_name() + "\\Cities\\" + city.get_name() + "\\Encounters\\" + fileName + "\\" + fileName + ".txt";
    	
        Scanner scan = new Scanner(new File(path));
        String answer = "";
        while(scan.hasNext()){
            String line = scan.nextLine().toString();
            if(line.contains(searchStr)){
            	if (answer == "") {
            		answer = line;
            	}
            	else {
            		answer = answer + "\n" + line;
            	}
            }
        }
        scan.close();
        return answer;
    }
    
    /*
 	 * Searches Monster text files to find lines on which the search String is found
 	 * 
 	 * @param 	fileName String			:the name of the Monster/ the Monster's text file
 	 * @param 	searchStr String		:the String that is to be searched for in the text file 
 	 * @throws	FileNotFoundException	:if a file matching the fileName cannot be found or opened
 	 * @return	answer String			:contains all the lines in which the search String was found 
 	 */
     public String parseFile(String fileName, String searchStr, Encounter encounter, Monster monster) throws FileNotFoundException{
    	String path = "";
    	//System.out.println("PATH:" + path);
    	if (monster.get_r_host() == null) {
    		//System.out.println("Route is null.");
    		path = "C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + monster.get_country().get_country_name() + "\\Cities\\" + monster.get_c_host().get_name() + "\\Encounters\\" + encounter.get_name() + "\\Enemies\\" + monster.get_name() + "\\" + monster.get_name() + ".txt";
    		//System.out.println("New Path: " + path);
    	}
    	else {
    		//System.out.println("City is null.");
    		path = "C:\\Users\\Fusion360\\Desktop\\DM\\Countries\\" + monster.get_country().get_country_name() + "\\Routes\\" + monster.get_r_host().get_name() + "\\Encounters\\" + encounter.get_name() + "\\Enemies\\" + monster.get_name() + "\\" + monster.get_name() + ".txt";
    	}
    	//System.out.println("PATH:" + path);
     	Scanner scan = new Scanner(new File(path));
        String answer = "";
        while(scan.hasNext()){
             String line = scan.nextLine().toString();
             if(line.contains(searchStr)){
             	if (answer == "") {
             		answer = line;
             	}
             	else {
             		answer = answer + "\n" + line;
             	}
             }
        }
        scan.close();
        return answer;
     }
    
}
