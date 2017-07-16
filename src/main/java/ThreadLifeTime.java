import java.util.concurrent.TimeUnit;

/**
 * Created by alaincruzcasanova on 7/15/17.
 */
public class ThreadLifeTime implements Runnable {

    CacheObject cache;

    public void run() {
        while (true){
            try {
                TimeUnit.SECONDS.sleep(cache.getCacheMaxTime());
                cache.clear();
            } catch (InterruptedException e) {
                //If the exception is because of the interruption of the thread we don't worry because it is the service stopping the threads.
                if(e.getClass().getCanonicalName() != "java.lang.InterruptedException")
                    e.printStackTrace();
                break;
            }

        }
    }

    public ThreadLifeTime(CacheObject cache){
        this.cache = cache;
    }
}
