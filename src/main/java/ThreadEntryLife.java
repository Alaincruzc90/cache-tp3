import java.util.concurrent.TimeUnit;

/**
 * Class that will run the thread in charge of checking that no entry
 * will surpassed it's life time limits.
 */
public class ThreadEntryLife implements Runnable {

    // In order to check our cache, we need to be able to access it
    // That is why we need to have the reference inside our class.
    private CacheObject cache;

    // Constructor - We receive the cache that we will in charge of cleaning.
    public ThreadEntryLife(CacheObject cache){
        this.cache = cache;
    }

    //Signature method of the Runnable interface
    public void run() {
        //Infinite loop that will be checking each entry's life time.
        while (true){
            try {
                // We set the thread to sleep a significant time,
                // so that our thread is not running it's code
                // so often.
                TimeUnit.SECONDS.sleep(5);
                // Flag that will tell us to stop looking for new entries.
                boolean check = true;
                while(check){
                    // We search for the head of our priority queue, where
                    // we should find the least used entry.
                    // In case of null, it means the queue is empty
                    // and that we should keep going.
                    Entry entry;
                    if ((entry = cache.getFirstQueue())!=null) {
                        // Now we need to check that the head of the priority queue
                        // has not live longer than the maximun time.
                        long timeCheck = cache.getTime()-entry.getTime();
                        if (timeCheck >= cache.getEntryMaxTime()) {
                            System.out.println("ENTRY LIFE TIME: Entry -> "+ entry.getKey() +", was deleted for not beign used.");
                            System.out.println("Entry time in seconds: "+timeCheck);
                            System.out.println("Max entry time in seconds: "+cache.getEntryMaxTime());
                            // Delete the entry, for it has surpassed it's time limit.
                            cache.evict(entry.getKey());
                            // Now, we need to check the next entry
                        } else {
                            // Set the flag to false and exit the loop.
                            check = false;
                        }
                    } else {
                        check = false;
                    }
                }
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
