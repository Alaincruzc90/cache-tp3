/**
 * Created by esteban on 15/07/2017.
 * Cache that chooses it's victim by order of arrival. First element to enter the cache is the first one to leave.
 */

import java.util.LinkedList;

public class CacheFIFO<K,V> extends CacheObject<K,V> {

    //List that contains every element in the cache.
    private LinkedList<K> linkedList;

    /*
    * Constructor #1.
    * @Params:
    * cacheName: The name of our cache.
    * */
    public CacheFIFO(String cacheName){
        super(cacheName);
        linkedList = new LinkedList<K>();
    }

    /*
    * Constructor #2.
    * @Params:
    * cacheName: The name of our cache.
    * cacheMaxTime: Maximum amount of time our cache can stay without clearing it's entries.
    * */
    public CacheFIFO(String cacheName, long cacheMaxTime){
        super(cacheName,cacheMaxTime);
        linkedList = new LinkedList<K>();
    }

    /*
    * Constructor #3.
    * @Params:
    * cacheMaxTime: Maximum amount of time our cache can stay without clearing it's entries.
    * cacheName: The name of our cache.
    * */
    public CacheFIFO(long entryMaxTime, String cacheName){
        super(entryMaxTime,cacheName);
        linkedList = new LinkedList<K>();
    }

    /*
    * Constructor #4.
    * @Params:
    * cacheName: The name of our cache.
    * maxEntries: Maximum amount of entries that our cache can hold simultaneously.
    * */
    public CacheFIFO(String cacheName, int maxEntries){
        super(cacheName,maxEntries);
        linkedList = new LinkedList<K>();
    }

    /*
    * Constructor #5.
    * @Params:
    * cacheName: The name of our cache.
    * maxEntries: Maximum amount of entries that our cache can hold simultaneously.
    * entryMaxTime: Maximum amount of time an entry can stay in the cache without being read.
    * */
    public CacheFIFO(String cacheName, int maxEntries, long entryMaxTime){
        super(cacheName,maxEntries,entryMaxTime);
        linkedList = new LinkedList<K>();
    }

    /*
    * Constructor #6.
    * @Params:
    * cacheName: The name of our cache.
    * maxEntries: Maximum amount of entries that our cache can hold simultaneously.
    * entryMaxTime: Maximum amount of time an entry can stay in the cache without being read.
    * */
    public CacheFIFO(String cacheName, long cacheMaxTime, int maxEntries){
        super(cacheName,cacheMaxTime,maxEntries);
        linkedList = new LinkedList<K>();
    }

    /*
    * Constructor #7.
    * @Params:
    * cacheName: The name of our cache.
    * entryMaxTime: Maximum amount of time an entry can stay in the cache without being read.
    * cacheMaxTime: Maximum amount of time our cache can stay without clearing it's entries.
    * */
    public CacheFIFO(String cacheName, long cacheMaxTime, long entryMaxTime){
        super(cacheName,cacheMaxTime,entryMaxTime);
        linkedList = new LinkedList<K>();
    }


    /*
    * Constructor #8.
    * @Params:
    * cacheName: The name of our cache.
    * maxEntries: Maximum amount of entries that our cache can hold simultaneously.
    * entryMaxTime: Maximum amount of time an entry can stay in the cache without being read.
    * cacheMaxTime: Maximum amount of time our cache can stay without clearing it's entries.
    * */
    public CacheFIFO(String cacheName, int maxEntries, long entryMaxTime, long cacheMaxTime){
        super(cacheName,maxEntries,entryMaxTime,cacheMaxTime);
        linkedList = new LinkedList<K>();
    }

    /*
    * Method that finds the appropriate victim of the cache based on it's definition.
    * Returns the key of the selected victim.
    */
    @Override
    public K getVictim(){
        if(!linkedList.isEmpty()) {
            K key = linkedList.removeLast();
            //System.out.println(key);
            return key;
        }
        else {
            return null;
        }
    }

    /*
    * Method that evicts element of the cache with the specified key.
    * Returns nothing.
    */
    @Override
    public void evict(K key){
        super.evict(key);
        //System.out.println(key);
        linkedList.remove(key);
    }

    /*
    * Method that inserts the element key in the cache.
    * Returns nothing.
    */
    @Override
    public void put(K key, V value){
        super.put(key,value);
        linkedList.addFirst(key);
    }

    /*
    * Method that clear all our data structures from their entries.
    * */
    @Override
    public void clear(){
        super.clear();
        linkedList.clear();
    }

}
