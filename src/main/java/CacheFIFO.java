/**
 * Created by esteban on 15/07/2017.
 * Cache that chooses it's victim by order of arrival. First element to enter the cache is the first one to leave.
 */

import java.util.LinkedList;

public class CacheFIFO<K,V> extends CacheObject<K,V> {

    //List that contains every element in the cache.
    private LinkedList<K> LinkedList;

    //Constructor by name of cache.
    public CacheFIFO(String cacheName){
        super(cacheName);
        LinkedList = new LinkedList<K>();
    }

    //Constructor by name of cache and maximum of entries allowed.
    public CacheFIFO(String cacheName, int maxEntries){
        super(cacheName,maxEntries);
        LinkedList = new LinkedList<K>();
    }

    //Constructor of cache by cache name, maximum of entries and maximum time allowed in cache.
    public CacheFIFO(String cacheName, int maxEntries, long entryMaxTime, long cacheMaxTime){
        super(cacheName, maxEntries, entryMaxTime, cacheMaxTime);
        LinkedList = new LinkedList<K>();
    }

    /*
    * Method that finds the appropriate victim of the cache based on it's definition.
    * Returns the key of the selected victim.
    */
    @Override
    public K getVictim(){
        return LinkedList.removeLast();
    }

    /*
    * Method that evicts element of the cache with the specified key.
    * Returns nothing.
    */
    @Override
    public void evict(K key){
        super.evict(key);
        LinkedList.remove(key);
    }

    /*
    * Method that inserts the element key in the cache.
    * Returns nothing.
    */
    @Override
    public void put(K key, V value){
        super.put(key,value);
        LinkedList.addFirst(key);
    }

}