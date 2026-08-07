package console.program;

import java.util.Scanner;

/**
 * The Restaurant class provides the functionality needed to manage orders and checkout.
 */
public class MainMenu {

    private String name;

	Search search = new Search();

	private static String banner = new String(new char[50]).replace('\u0000', '-');
    
    public MainMenu(String name) {
    	this.name = name;
    }
    
	/**
     * The method to operate the restaurant.
     */
    public void run() {
    	boolean exit = false;
		String input;

		System.out.println("\nWelcome to " + name + "!");

		while(!exit) {
			printMenu();

			System.out.print("Please select: ");
			input = readUserInput();

			switch (input) {
				case "1" -> printCourseListMenu();
				case "2" -> search.curStudentEnrollment.showEnrollment();
				case "3" -> search.curStudentEnrollment.Withdraw();
				case "4" -> exit = true;
				default -> System.out.println("Please input a number between 1-4");
			}
		}
    }
    
	/**
     * The utility method to print menu options.
     */
	public static void printMenu(){
		System.out.println(banner);
		System.out.println("> Select from main menu");
		System.out.println(banner);
		System.out.println("  1) Search by keyword to enroll");
		System.out.println("  2) Show my enrolled courses");
		System.out.println("  3) Withdraw from a course");
		System.out.println("  4) Exit");
	}

	public void printCourseListMenu() {
		System.out.print("Please provide a brand: ");

		String input = readUserInput();

		search.printList(input);
	}
	
	/**
     * The utility method to read user input.
     */
    public static String readUserInput() {
	    Scanner sc = new Scanner(System.in);
	    return sc.nextLine();
	}
}
