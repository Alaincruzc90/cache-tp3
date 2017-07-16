import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Cache that uses the least recently used algorithm to choose the evict victim.
 */
public class CacheLRU<K,V> extends CacheObject<K,V> {

    // Priority queues are perfect for this algorithm, because we can compare each member when inserting
    // new entries, and so we will always know which entry was the last to be used.
    private PriorityQueue<Entry<K,V>> priorityLRU;

    //Constructor by name of cache.
    public CacheLRU(String cacheName){
        super(cacheName);
        Comparator<Entry> comparator = new EntryComparator();
        priorityLRU = new PriorityQueue<Entry<K, V>>(30, (Comparator<? super Entry>) comparator);
    }

    //Constructor by name of cache and maximum of entries allowed.
    public CacheLRU(String cacheName, int maxEntries){
        super(cacheName,maxEntries);
        Comparator<Entry> comparator = new EntryComparator();
        priorityLRU = new PriorityQueue<Entry<K, V>>(30, (Comparator<? super Entry>) comparator);
    }

    //Constructor of cahe by cache name, maximum of entries and maximum time allowed in cache.
    public CacheLRU(String cacheName, int maxEntries, long entryMaxTime, long cacheMaxTime){
        super(cacheName, maxEntries, entryMaxTime, cacheMaxTime);
        Comparator<Entry> comparator = new EntryComparator();
        priorityLRU = new PriorityQueue<Entry<K, V>>(30, (Comparator<? super Entry>) comparator);
    }

    /*
    * Method that finds the appropriate victim of the cache based on it's definition.
    * Returns the key of the selected victim.
    */
    @Override
    public K getVictim(){
        return priorityLRU.poll().getKey();
    }

    /*
    * Method that evicts element of the cache with the specified key.
    * Returns nothing.
    */
    @Override
    public void evict(K key){
        try {
            Entry entry = (Entry) this.getMap().get(key);
            super.evict(key);
            priorityLRU.remove(entry);
        } catch (ClassCastException e) {
            System.out.println("Coudn't cast to Entry.");
            System.out.println(e);
        }
    }

    /*
    * Method that inserts the element key in the cache.
    * Returns nothing.
    */
    @Override
    public void put(K key, V value){
        Entry entry = new Entry(key,getTime(),value);
        priorityLRU.add(entry);
        super.put(key,value);
    }

    /*
    * Method that clear all our data structures from their entries.
    * */
    @Override
    public void clear(){
        super.clear();
        priorityLRU.clear();
    }
}
