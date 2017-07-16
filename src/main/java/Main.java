import java.util.concurrent.TimeUnit;

/**
 * Created by alaincruzcasanova on 7/14/17.
 */
public class Main {

    public static void main(String[] args) {
        DataAccessObject data = new DataAccessObject();
        data.getPage("Anarchism");
        CacheLIFO<Integer,String> cache = new CacheLIFO<Integer, String>("Oper",10,10,4);
        cache.put(1,"Prueba");
        try {
            TimeUnit.SECONDS.sleep(7);
            cache.stopThreads();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
