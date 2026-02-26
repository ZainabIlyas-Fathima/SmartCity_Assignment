import java.util.Scanner;

/**
 * Main application class for the Smart City Route Planner.
 * As Member 1(zainab), I designed this menu to be the central hub for our project.
 * It connects the user's input to the Graph data structures we've built.
 */
public class SmartCityApp {
    public static void main(String[] args) {
        // Initializing our graph object which holds all the city logic
        CityGraph myCity = new CityGraph();
        Scanner input = new Scanner(System.in);
        int choice = 0;

        // The loop keeps the program running until the user explicitly chooses to exit
        while (choice != 7) {
            System.out.println("\n===============================");
            System.out.println("   SMART CITY ROUTE PLANNER    ");
            System.out.println("===============================");
            System.out.println("1. Add a Location");
            System.out.println("2. Remove a Location");
            System.out.println("3. Add a Road");
            System.out.println("4. Remove a Road");
            System.out.println("5. Show Map Connections");
            System.out.println("6. Plan Route (Stack & Queue)");
            System.out.println("7. Exit Program");
            System.out.print("Select an option (1-7): ");

            /* * INPUT VALIDATION: 
             * I used a try-catch block here to prevent the program from crashing 
             * if a user enters a letter instead of a number. 
             * I'm using nextLine() and parsing it to avoid the "newline" bug in Scanner.
             */
            try {
                choice = Integer.parseInt(input.nextLine());
            } 
            catch (Exception e) {
                System.out.println("Error: Please type a number from 1 to 7.");
                continue; // Restarts the loop to give the user another chance
            }

            // Handling the menu options based on user selection
            switch (choice) {
                case 1:
                    System.out.print("City name to add: ");
                    // Directly passing user input to our graph's add method
                    myCity.addLocation(input.nextLine());
                    break;
                case 2:
                    System.out.print("City name to remove: ");
                    myCity.removeLocation(input.nextLine());
                    break;
                case 3:
                    // Adding a road requires two points, so we capture both here
                    System.out.print("Enter Start City: ");
                    String s = input.nextLine();
                    System.out.print("Enter End City: ");
                    String d = input.nextLine();
                    myCity.addRoad(s, d);
                    break;
                case 4:
                    // This allows users to delete specific connections without deleting the city
                    System.out.print("Enter Start City: ");
                    String rs = input.nextLine();
                    System.out.print("Enter End City: ");
                    String rd = input.nextLine();
                    myCity.removeRoad(rs, rd);
                    break;
                case 5:
                    // Displays the current state of our Adjacency List
                    myCity.displayGraph();
                    break;
                case 6:
                    /*
                     * TRAVERSAL LOGIC:
                     * Here we run both searches to demonstrate the difference 
                     * between using a Queue (BFS) and a Stack (DFS).
                     */
                    System.out.print("Starting City for search: ");
                    String startNode = input.nextLine();
                    myCity.searchQueue(startNode);
                    myCity.searchStack(startNode);
                    break;
                case 7:
                    System.out.println("System Shutting Down. Goodbye!");
                    break;
                default:
                    // Validation for numbers that aren't 1-7
                    System.out.println("Please choose a valid menu number.");
            }
        }
        input.close(); // Closing the scanner to keep the code clean and memory-safe
    }
}