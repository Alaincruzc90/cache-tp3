import java.util.Stack;

/**
 * Cache that uses last in, first out algorithm to choose the evict victim.
 */
public class CacheLIFO<K,V> extends CacheObject<K,V> {

    // Stack are perfect for the LIFO algorithm, because it allows to pop the last element that was inserted.
    private Stack<K> stack;

    /*
    * Constructor #1.
    * @Params:
    * cacheName: The name of our cache.
    * */
    public CacheLIFO(String cacheName){
        super(cacheName);
        stack = new Stack<K>();
    }

    /*
    * Constructor #2.
    * @Params:
    * cacheName: The name of our cache.
    * cacheMaxTime: Maximum amount of time our cache can stay without clearing it's entries.
    * */
    public CacheLIFO(String cacheName, long cacheMaxTime){
        super(cacheName,cacheMaxTime);
        stack = new Stack<K>();
    }

    /*
    * Constructor #3.
    * @Params:
    * cacheMaxTime: Maximum amount of time our cache can stay without clearing it's entries.
    * cacheName: The name of our cache.
    * */
    public CacheLIFO(long entryMaxTime, String cacheName){
        super(entryMaxTime,cacheName);
        stack = new Stack<K>();
    }

    /*
    * Constructor #4.
    * @Params:
    * cacheName: The name of our cache.
    * maxEntries: Maximum amount of entries that our cache can hold simultaneously.
    * */
    public CacheLIFO(String cacheName, int maxEntries){
        super(cacheName,maxEntries);
        stack = new Stack<K>();
    }

    /*
    * Constructor #5.
    * @Params:
    * cacheName: The name of our cache.
    * maxEntries: Maximum amount of entries that our cache can hold simultaneously.
    * entryMaxTime: Maximum amount of time an entry can stay in the cache without being read.
    * */
    public CacheLIFO(String cacheName, int maxEntries, long entryMaxTime){
        super(cacheName,maxEntries,entryMaxTime);
        stack = new Stack<K>();
    }

    /*
    * Constructor #6.
    * @Params:
    * cacheName: The name of our cache.
    * maxEntries: Maximum amount of entries that our cache can hold simultaneously.
    * entryMaxTime: Maximum amount of time an entry can stay in the cache without being read.
    * */
    public CacheLIFO(String cacheName, long cacheMaxTime, int maxEntries){
        super(cacheName,cacheMaxTime,maxEntries);
        stack = new Stack<K>();
    }

    /*
    * Constructor #7.
    * @Params:
    * cacheName: The name of our cache.
    * entryMaxTime: Maximum amount of time an entry can stay in the cache without being read.
    * cacheMaxTime: Maximum amount of time our cache can stay without clearing it's entries.
    * */
    public CacheLIFO(String cacheName, long cacheMaxTime, long entryMaxTime){
        super(cacheName,cacheMaxTime,entryMaxTime);
        stack = new Stack<K>();
    }


    /*
    * Constructor #8.
    * @Params:
    * cacheName: The name of our cache.
    * maxEntries: Maximum amount of entries that our cache can hold simultaneously.
    * entryMaxTime: Maximum amount of time an entry can stay in the cache without being read.
    * cacheMaxTime: Maximum amount of time our cache can stay without clearing it's entries.
    * */
    public CacheLIFO(String cacheName, int maxEntries, long entryMaxTime, long cacheMaxTime){
        super(cacheName,maxEntries,entryMaxTime,cacheMaxTime);
        stack = new Stack<K>();
    }

    /*
    * Method that finds the appropriate victim of the cache based on it's definition.
    * Returns the key of the selected victim.
    */
    @Override
    public K getVictim(){
        K key = stack.pop();
        //System.out.println(key);
        return key;
    }

    /*
    * Method that evicts element of the cache with the specified key.
    * Returns nothing.
    */
    @Override
    public void evict(K key){
        super.evict(key);
        //System.out.println(key);
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
