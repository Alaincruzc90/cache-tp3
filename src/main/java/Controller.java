import java.util.Scanner;

/**
 * Class in charge of receiving input from the user and passing the request to the Service class.
 * It consists of a console menu which gives the user three options. The first option is for searching a web page by it's name.
 * The second is searching by an identifier. The third option is exiting from the program.
 * <p>
 * Main Class of the program.
 */
public class Controller {

    public static void main(String[] args) {
        Service service = new Service();

        System.out.println("Welcome! Type 1 to search by a page name. Type 2 to search by page identifier. Type 3 to exit program.");
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        do {
            System.out.println("Select an option.");
            option = scanner.nextInt();
            if (option == 1) {
                //Receive wep page name
                String webPageName = scanner.next();
                //Pass the request to Service
            } else if (option == 2) {
                //Receive web page identifier
                int webPageIdentifier = scanner.nextInt();
                //Pass request to Service
            } else if (option == 3) {
                System.out.println("Goodbye!");
            } else {
                System.out.println("Invalid option! Valid options are 1,2 or 3.");
            }

        } while (option != 3);
    }

}