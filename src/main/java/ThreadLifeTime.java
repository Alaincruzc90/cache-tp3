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
                e.printStackTrace();
                break;
            }

        }
    }

    public ThreadLifeTime(CacheObject cache){
        this.cache = cache;
    }
}
