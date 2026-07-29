package console.program;

import java.util.Scanner;

/**
 * The Restaurant class provides the functionality needed to manage orders and checkout.
 */
public class MainMenu {

    private String name;

	Search search = new Search();
    
    public MainMenu(String name) {
    	this.name = name;
    }
    
    public String getName() {
    	return this.name;
    }
    
	/**
     * The method to operate the restaurant.
     */
    public void run() {
		// Data data = new Data();
		// ArrayList<data_record> classLists = new ArrayList<>();

		// classLists = data.getData("course-1.csv");

		// for (data_record temp : classLists) {
		// 	temp.printData();
		// }

    	boolean exit = false;
		String input = "";
		while(!exit) {
			printMenu(name);

			System.out.println("Please select: ");
			input = readUserInput();
			
			switch (input) {
				case "1" -> System.out.println("Search function to be coded");
				case "2" -> System.out.println("Enroll function to be coded");
				case "3" -> System.out.println("Implement Withdraw from course");
				case "4" -> exit = true;
				default -> System.out.println("Please input a number between 1-4");
			}
		}
    }
    
	/**
     * The utility method to print menu options.
     */
	public static void printMenu(String name){
		String banner = new String(new char[50]).replace('\u0000', '-');

		System.out.println("Welcome to " + name + "!");
		System.out.println(banner);
		System.out.println("> Select from main menu");
		System.out.println(banner);
		System.out.println("  1) Search by keyword to enroll");
		System.out.println("  2) Show my enrolled courses");
		System.out.println("  3) Withdraw from a course");
		System.out.println("  4) Exit");
	}
	
	/**
     * The utility method to read user input.
     */
    public static String readUserInput() {
	    Scanner sc = new Scanner(System.in);
	    return sc.nextLine();
	}
}
