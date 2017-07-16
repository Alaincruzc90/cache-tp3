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
                            System.out.println("ENTRY LIFE TIME: Entry -> "+ entry.getKey() +", was deleted for not beign used.");
                            System.out.println("Entry time in seconds: "+timeCheck);
                            System.out.println("Max entry time in seconds: "+cache.getEntryMaxTime());
                            cache.evict(entry.getKey());
                        } else {
                            check = false;
                        }
                    } else {
                        check = false;
                    }
                }
            } catch (InterruptedException e) {
                //If the exception is because of the interruption of the thread we don't worry because it is the service stopping the threads.
                if(e.getClass().getCanonicalName() != "java.lang.InterruptedException")
                    e.printStackTrace();
                break;
            }

        }
    }
}
