import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by alaincruzcasanova on 7/16/17.
 */
public class CacheLRU<K,V> extends CacheObject<K,V> {

    private PriorityQueue<Entry<K,V>> priorityLRU;

    public CacheLRU(String cacheName){
        super(cacheName);
        Comparator<Entry> comparator = new EntryComparator();
        priorityLRU = new PriorityQueue<Entry<K, V>>(30, (Comparator<? super Entry>) comparator);
    }

    public CacheLRU(String cacheName, int maxEntries){
        super(cacheName,maxEntries);
        Comparator<Entry> comparator = new EntryComparator();
        priorityLRU = new PriorityQueue<Entry<K, V>>(30, (Comparator<? super Entry>) comparator);
    }

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

    @Override
    public void put(K key, V value){
        Entry entry = new Entry(key,getTime(),value);
        priorityLRU.add(entry);
        super.put(key,value);
    }

    @Override
    public void clear(){
        super.clear();
        priorityLRU.clear();
    }
}
