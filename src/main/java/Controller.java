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

    //This integer represents the default value of the caches options. If this value is chosen by the user the default value of that element in the cache is used.
    private static int defaultValue = -1;

    //Method that return an user selected integer, it makes sure that the input is actually an integer.
    //It uses a primary message to tell the user what the input is about. Also receives a second message to tell the user what happened if the input was not an integer.
    // *In this case an empty string is considered the default value(defined in this class).
    private static int receiveIntegerFromUser(String PrimaryMessage, String errorMessage){
        Scanner scanner = new Scanner(System.in);
        System.out.println(PrimaryMessage);
        while(true) {
            String userInput = scanner.nextLine();
            try {
                return Integer.parseInt(userInput);
            }
            catch (Exception e) {
                if(e.getClass().getCanonicalName() == "java.lang.NumberFormatException") {
                    if (userInput.isEmpty()) {
                        return defaultValue;
                    }
                    System.out.println(errorMessage);
                }
                else
                    e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service(true);

        System.out.println("Welcome! Choose the cache values.");
        Scanner scanner = new Scanner(System.in);

        //Receive parameter for cache size.
        int cacheSize = receiveIntegerFromUser("Enter the size of the cache or press enter for the default value.", "Cache size has to be an integer.");

        //Receive parameter for entry max time.
        int cacheEntryTime = receiveIntegerFromUser("Enter the maximum time an entry can exist in the cache(in seconds) or press enter for the default value.", "Cache entry time has to be an integer.");

        //Receive parameter for cache max time.
        int cacheMaxTime = receiveIntegerFromUser("Enter the maximum time that passes until a cache is erased if it has not been accessed or press enter for the default value.", "Cache time has to be an integer.");

        //Service initialization based different combinations of parameters.
        if(cacheSize == defaultValue) {
            if(cacheEntryTime == defaultValue){
                if(cacheMaxTime == defaultValue){

                }
                else{

                }
            }
            else{
                if(cacheMaxTime == defaultValue){

                }
                else{

                }
            }
        }
        else {
            if(cacheEntryTime == defaultValue){
                if(cacheMaxTime == defaultValue){

                }
                else{

                }
            }
            else{
                if(cacheMaxTime == defaultValue){

                }
                else{

                }
            }
        }

        System.out.println("Type 1 to search by a page name. Type 2 to search by page identifier. Cache is active, to toggle cache status(active or inactive) select option 3. Press enter to exit program.");
        int option;
        do {

            option = receiveIntegerFromUser("Select an option.", "Error: Invalid Input! Option has to be an integer.");

            //Search page by title
            if (option == 1) {
                System.out.println("Enter the title of the page:");
                //Receive wep page name
                String webPageTitle = scanner.nextLine();
                //Pass the request to Service
                System.out.println(service.getPage(webPageTitle));
            }

            //Search page by identifier
            else if (option == 2) {
                //Receive web page identifier
                int webPageIdentifier = receiveIntegerFromUser("Enter the ID of the page:", "Page ID should be an integer.");

                //Pass request to Service
                System.out.println(service.getPage(webPageIdentifier));
            }

            //Toggle cache option
            else if (option == 3) {
                boolean cacheStatus = !service.getCacheOption();
                service.toggleCache(cacheStatus);
                if(cacheStatus)
                    System.out.println("Cache is active.");
                else
                    System.out.println("Cache is inactive.");
            }

            //Exit
            else if (option == defaultValue) {
                System.out.println("Goodbye!");
            }

            //Invalid option
            else {
                System.out.println("Invalid option! Valid options are 1,2 or 3.");
            }

        } while (option != defaultValue);
        service.finishService();
    }

}