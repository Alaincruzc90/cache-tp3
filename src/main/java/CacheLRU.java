import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Cache that uses the least recently used algorithm to choose the evict victim.
 */
public class CacheLRU<K,V> extends CacheObject<K,V> {

    // Priority queues are perfect for this algorithm, because we can compare each member when inserting
    // new entries, and so we will always know which entry was the last to be used.
    // So, since our cache object already implemented a PriorityQueue, we are gonna use that one.

    //Constructor by name of cache.
    public CacheLRU(String cacheName){
        super(cacheName);
    }

    //Constructor by name of cache and maximum of entries allowed.
    public CacheLRU(String cacheName, int maxEntries){
        super(cacheName,maxEntries);
    }

    //Constructor of cahe by cache name, maximum of entries and maximum time allowed in cache.
    public CacheLRU(String cacheName, int maxEntries, long entryMaxTime, long cacheMaxTime){
        super(cacheName, maxEntries, entryMaxTime, cacheMaxTime);
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
