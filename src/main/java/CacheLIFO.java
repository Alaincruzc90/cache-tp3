import java.util.Stack;

/**
 * Created by alaincruzcasanova on 7/15/17.
 */
public class CacheLIFO<K,V> extends CacheObject<K,V> {

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

}
