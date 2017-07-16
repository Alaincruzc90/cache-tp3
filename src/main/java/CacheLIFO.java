import java.util.Stack;

/**
 * Cache that ues last in, first out algorithm to choose the evict victim.
 */
public class CacheLIFO<K,V> extends CacheObject<K,V> {

    // Stack are perfect for the LIFO algorithm, because it allows to pop the last element that was inserted.
    private Stack<K> stack;

    public CacheLIFO(String cacheName){
        super(cacheName);
        stack = new Stack<K>();
    }

    public CacheLIFO(String cacheName, int maxEntries){
        super(cacheName,maxEntries);
        stack = new Stack<K>();
    }

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

    @Override
    public void evict(K key){
        super.evict(key);
        stack.remove(key);
    }

    @Override
    public void put(K key, V value){
        super.put(key,value);
        stack.push(key);
    }

    @Override
    public void clear(){
        super.clear();
        stack.clear();
    }

}
