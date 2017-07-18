import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Cache that uses the least recently used algorithm to choose the evict victim.
 */
public class CacheLRU<K,V> extends CacheObject<K,V> {

    // Priority queues are perfect for this algorithm, because we can compare each member when inserting
    // new entries, and so we will always know which entry was the last to be used.
    // So, since our cache object already implemented a PriorityQueue, we are gonna use that one.

    /*
    * Constructor #1.
    * @Params:
    * cacheName: The name of our cache.
    * */
    public CacheLRU(String cacheName){
        super(cacheName);
    }

    /*
    * Constructor #2.
    * @Params:
    * cacheName: The name of our cache.
    * cacheMaxTime: Maximum amount of time our cache can stay without clearing it's entries.
    * */
    public CacheLRU(String cacheName, long cacheMaxTime){
        super(cacheName,cacheMaxTime);
    }

    /*
    * Constructor #3.
    * @Params:
    * cacheMaxTime: Maximum amount of time our cache can stay without clearing it's entries.
    * cacheName: The name of our cache.
    * */
    public CacheLRU(long entryMaxTime, String cacheName){
        super(entryMaxTime,cacheName);
    }

    /*
    * Constructor #4.
    * @Params:
    * cacheName: The name of our cache.
    * maxEntries: Maximum amount of entries that our cache can hold simultaneously.
    * */
    public CacheLRU(String cacheName, int maxEntries){
        super(cacheName,maxEntries);
    }

    /*
    * Constructor #5.
    * @Params:
    * cacheName: The name of our cache.
    * maxEntries: Maximum amount of entries that our cache can hold simultaneously.
    * entryMaxTime: Maximum amount of time an entry can stay in the cache without being read.
    * */
    public CacheLRU(String cacheName, int maxEntries, long entryMaxTime){
        super(cacheName,maxEntries,entryMaxTime);
    }

    /*
    * Constructor #6.
    * @Params:
    * cacheName: The name of our cache.
    * maxEntries: Maximum amount of entries that our cache can hold simultaneously.
    * entryMaxTime: Maximum amount of time an entry can stay in the cache without being read.
    * */
    public CacheLRU(String cacheName, long cacheMaxTime, int maxEntries){
        super(cacheName,cacheMaxTime,maxEntries);
    }

    /*
    * Constructor #7.
    * @Params:
    * cacheName: The name of our cache.
    * entryMaxTime: Maximum amount of time an entry can stay in the cache without being read.
    * cacheMaxTime: Maximum amount of time our cache can stay without clearing it's entries.
    * */
    public CacheLRU(String cacheName, long cacheMaxTime, long entryMaxTime){
        super(cacheName,cacheMaxTime,entryMaxTime);
    }


    /*
    * Constructor #8.
    * @Params:
    * cacheName: The name of our cache.
    * maxEntries: Maximum amount of entries that our cache can hold simultaneously.
    * entryMaxTime: Maximum amount of time an entry can stay in the cache without being read.
    * cacheMaxTime: Maximum amount of time our cache can stay without clearing it's entries.
    * */
    public CacheLRU(String cacheName, int maxEntries, long entryMaxTime, long cacheMaxTime){
        super(cacheName,maxEntries,entryMaxTime,cacheMaxTime);
    }

    /*
    * Method that finds the appropriate victim of the cache based on it's definition.
    * Returns the key of the selected victim.
    */
    @Override
    public K getVictim(){
        try {
            K key = (K) ((Entry) getPriorityQueue().poll()).getKey();
            return key;
        } catch (ClassCastException e) {
            System.out.println("Couldn't cast to Object to Entry type.");
        }
        return null;
    }
}
