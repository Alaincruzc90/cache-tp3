import java.util.concurrent.TimeUnit;

/**
 * Created by alaincruzcasanova on 7/15/17.
 */
public class ThreadEntryLife implements Runnable {

    private CacheObject cache;

    public ThreadEntryLife(CacheObject cache){
        this.cache = cache;
    }

    public void run() {
        while (true){
            try {
                TimeUnit.SECONDS.sleep(5);
                boolean check = true;
                while(check){
                    Entry entry;
                    if ((entry = cache.getFirstQueue())!=null) {
                        long timeCheck = cache.getTime()-entry.getTime();
                        if (timeCheck >= cache.getEntryMaxTime()) {
                            cache.evict(entry.getKey());
                        } else {
                            check = false;
                        }
                    } else {
                        check = false;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

        }
    }
}
