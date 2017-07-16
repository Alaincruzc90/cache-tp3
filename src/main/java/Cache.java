/**
 * Created by alaincruzcasanova on 7/15/17.
 */
public interface Cache<K,V> {

    // Returns the name of the cache
    public String getName();
    // Returns the value from a key value map that refers to a key of type K
    public V get(K key);
    // Put into a key value map a new entry
    // Also, if the cache is full, we will need to choose a victim to evict
    public void put(K key, V value);
    // Free the space used by a entry
    public void evict(K key);
    // Free our data structures from all our entries
    public void clear();
    // Initialize the thread that will find entries that had not been use for more than the entry life
    public void initEntryLife(CacheObject cache);
    // Initialize the thread that will clear all entries if the cache's life time is due
    public void initLifeTime(CacheObject cache);

}
