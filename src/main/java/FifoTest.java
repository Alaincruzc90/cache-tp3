import java.util.concurrent.TimeUnit;

/**
 * Created by alaincruzcasanova on 7/15/17.
 */
public class FifoTest {

    public static void main(String args[]){
        CacheLRU<Integer,String> cache = new CacheLRU<Integer, String>("Oper",10,0,0);
        try {
            cache.put(10, "10");
            cache.put(13, "13");
            TimeUnit.SECONDS.sleep(3);
            cache.put(11, "11");
            cache.get(10);
            TimeUnit.SECONDS.sleep(3);
            cache.put(12, "11");
            TimeUnit.SECONDS.sleep(2);
            cache.put(14, "11");
            cache.put(15, "11");
//            System.out.println("I am waiting 10 seconds.");
            TimeUnit.SECONDS.sleep(1);
            cache.put(16, "11");
            cache.put(17, "11");
            cache.get(12);
            cache.put(18, "11");
            cache.put(19, "11");
            cache.put(20, "11");
            cache.put(21, "11");
            TimeUnit.SECONDS.sleep(2);
            cache.put(22, "11");
            cache.get(10);
        } catch (InterruptedException e) {
            //ignore
        }
    }
}
