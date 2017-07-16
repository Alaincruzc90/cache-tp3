import java.util.Scanner;

/**
 * Created by esteban on 7/13/17.
 * Class in charge of receiving input from the user and passing the request to the Service class.
 * It consists of a console menu which gives the user three options. The first option is for searching a web page by it's name.
 * The second is searching by an identifier. The third option is exiting from the program.
 * <p>
 * Main Class of the program.
 */
public class Controller {

    public static void main(String[] args) {
        Service service = new Service(true);

        System.out.println("Welcome! Type 1 to search by a page name. Type 2 to search by page identifier. Type 3 to exit program. Cache is active, to toggle cache status(active or inactive) select option 4.");
        Scanner scanner = new Scanner(System.in);
        int option = -1;
        do {
            System.out.println("Select an option.");
            option = scanner.nextInt();

            //Search page by title
            if (option == 1) {
                System.out.println("Enter the title of the page:");
                scanner.nextLine();//Flush nextline from nextInt.
                //Receive wep page name
                String webPageTitle = scanner.nextLine();
                //Pass the request to Service
                System.out.println(service.getPage(webPageTitle));
            }

            //Search page by identifier
            else if (option == 2) {
                System.out.println("Enter the ID of the page:");
                //Receive web page identifier
                int webPageIdentifier = scanner.nextInt();
                //Pass request to Service
                System.out.println("" + service.getPage(webPageIdentifier));
            }

            //Exit
            else if (option == 3) {
                System.out.println("Goodbye!");
            }

            //Toggle cache option
            else if (option == 4) {
                boolean cacheStatus = !service.getCacheOption();
                service.toggleCache(cacheStatus);
                if(cacheStatus)
                    System.out.println("Cache is active.");
                else
                    System.out.println("Cache is inactive.");
            }

            //Invalid option
            else {
                System.out.println("Invalid option! Valid options are 1,2 or 3.");
            }

        } while (option != 3);
        service.finishService();
    }

}