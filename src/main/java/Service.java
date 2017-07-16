/**
 * Class in charge of connecting with the Data Access Object and the Cache.
 * It receives the input of the user. Checks if the content is available in the cache.
 * If it is not it retrieves it from the database and stores it in the cache.
 */
public class Service {

    //Boolean that identifies if cache is active or not.
    private boolean cacheOption;

    //Cache object that stores elements by ID.
    //private Cache<K,V> cacheID;

    //Cache object that stores elements by Title.
    //private Cache<K,V> cacheTitle;

    //Data Access Object that allows use of database.
    //private DataAccessObject dataAccess;

    Service(){

    }

    //Retrieves page based on ID.
    public String getPage(int id){
        //Based on cacheOption either checks if it is on cache or just retrieves it from the database.

        //If cache is active
        //Checks if page is on cache
        //If it is retrieve from cache and return it.

        //If it is not. Retrieve it from database.
        //Store it on cache and return the page.

        //If cache is deactivated
        //Retrieve page from database and return it.
        return "Page";
    }

    //Retrieves page based on title.
    public String getPage(String title){
        //Based on cacheOption either checks if it is on cache or just retrieves it from the database.

        //If cache is active
        //Checks if page is on cache
        //If it is retrieve from cache and return it.

        //If it is not. Retrieve it from database.
        //Store it on cache and return the page.

        //If cache is deactivated
        //Retrieve page from database and return it.
        return "Page";
    }

    //Deactivates or activates cache.
    public void toggleCache(boolean cacheOption){
        this.cacheOption = cacheOption;
    }

}