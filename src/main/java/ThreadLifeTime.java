import java.util.concurrent.TimeUnit;

/**
 * Class that will run the thread in charge of checking the cache expiring time,
 * so that if it has been surpassed it's life time limits, we clear all our cache.
 */
public class ThreadLifeTime implements Runnable {

    // In order to check our cache, we need to be able to access it
    // That is why we need to have the reference inside our class.
    CacheObject cache;

    // Constructor - We receive the cache that we will in charge of cleaning.
    public ThreadLifeTime(CacheObject cache){
        this.cache = cache;
    }

    //Signature method of the Runnable interface
    public void run() {
        //Infinite loop that will be checking each entry's life time.
        while (true){
            try {
                // We set our thread to sleep for the duration of the cache's expiring time.
                // When we reach that time, we simply wipe out all our entries.
                TimeUnit.SECONDS.sleep(cache.getCacheMaxTime());
                cache.clear();
            } catch (InterruptedException e) {
                // If the exception is because of the interruption of the thread
                // we don't worry because it is the cache stopping the threads.
                if(e.getClass().getCanonicalName() != "java.lang.InterruptedException")
                    e.printStackTrace();
                break;
            }

        }
    }
}
