/**
 * Created by esteban on 7/13/17.
 * Class in charge of connecting with the Data Access Object and the Cache.
 * It receives the input of the user. Checks if the content is available in the cache.
 * If it is not it retrieves it from the database and stores it in the cache.
 */
public class Service {

    //Boolean that identifies if cache is active or not.
    private boolean cacheOption;

    //Cache object that stores elements by ID.
    private CacheFIFO<Integer, String> cacheID;

    //Cache object that stores elements by Title.
    private CacheFIFO<String, String> cacheTitle;

    //Data Access Object that allows use of database.
    private DataAccessObject dataAccess;

    Service(boolean cacheOption){
        this.cacheOption = cacheOption;
        cacheID = new CacheFIFO<Integer, String>("ID Cache");
        cacheTitle = new CacheFIFO<String, String>("Title Cache");
        dataAccess = new DataAccessObject();
    }

    //Retrieves page based on ID.
    public String getPage(int id){

        //Based on cacheOption either checks if it is on cache or just retrieves it from the database.
        if(cacheOption){
            //If cache is active
            //Checks if page is on cache
            String result = cacheID.get(id);
            if(result != null) {
                //If it is retrieve from cache and return it.
                return result;
            }
            //If it is not. Retrieve it from database.
            result = dataAccess.getPage(id);

            //If the result is null(not found) then return an error String.
            if(result == null)
                return "Error: Page not found.";

            //Store it on cache and return the page.
            cacheID.put(id,result);
            return result;
        }
        else {
            String result;
            //If cache is deactivated
            //Retrieve page from database and return it.
            result = dataAccess.getPage(id);

            //If the result is null(not found) then return an error String.
            if(result == null)
                return "Error: Page not found.";

            return result;
        }
    }

    //Retrieves page based on title.
    public String getPage(String title){
        //Based on cacheOption either checks if it is on cache or just retrieves it from the database.
        if(cacheOption){
            //If cache is active
            //Checks if page is on cache
            String result = cacheTitle.get(title);
            if(result != null) {
                //If it is retrieve from cache and return it.
                return result;
            }
            //If it is not. Retrieve it from database.
            result = dataAccess.getPage(title);

            //If the result is null(not found) then return an error String.
            if(result == null)
                return "Error: Page not found.";

            //Store it on cache and return the page.
            cacheTitle.put(title,result);
            return result;
        }
        else {
            String result;
            //If cache is deactivated
            //Retrieve page from database and return it.
            result = dataAccess.getPage(title);

            //If the result is null(not found) then return an error String.
            if(result == null)
                return "Error: Page not found.";

            return result;
        }
    }

    //Deactivates or activates cache.
    public void toggleCache(boolean cacheOption){
        this.cacheOption = cacheOption;
    }

    public boolean getCacheOption(){
        return this.cacheOption;
    }

    public void finishService(){
        cacheTitle.stopThreads();
        cacheID.stopThreads();
    }

}