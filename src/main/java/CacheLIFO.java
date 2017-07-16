import java.util.Stack;

/**
 * Cache that uses last in, first out algorithm to choose the evict victim.
 */
public class CacheLIFO<K,V> extends CacheObject<K,V> {

    // Stack are perfect for the LIFO algorithm, because it allows to pop the last element that was inserted.
    private Stack<K> stack;

    //Constructor by name of cache.
    public CacheLIFO(String cacheName){
        super(cacheName);
        stack = new Stack<K>();
    }

    //Constructor by name of cache and maximum of entries allowed.
    public CacheLIFO(String cacheName, int maxEntries){
        super(cacheName,maxEntries);
        stack = new Stack<K>();
    }

    //Constructor of cahe by cache name, maximum of entries and maximum time allowed in cache.
    public CacheLIFO(String cacheName, int maxEntries, long entryMaxTime, long cacheMaxTime){
        super(cacheName, maxEntries, entryMaxTime, cacheMaxTime);
        stack = new Stack<K>();
    }

    /*
    * Method that finds the appropriate victim of the cache based on it's definition.
    * Returns the key of the selected victim.
    */
    @Override
    public K getVictim(){
        return stack.pop();
    }

    /*
    * Method that evicts element of the cache with the specified key.
    * Returns nothing.
    */
    @Override
    public void evict(K key){
        super.evict(key);
        stack.remove(key);
    }

    /*
    * Method that inserts the element key in the cache.
    * Returns nothing.
    */
    @Override
    public void put(K key, V value){
        super.put(key,value);
        stack.push(key);
    }

    /*
    * Method that clear all our data structures from their entries.
    * */
    @Override
    public void clear(){
        super.clear();
        stack.clear();
    }

}
