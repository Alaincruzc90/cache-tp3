/**
 * Created by esteban on 15/07/2017.
 * Cache that chooses it's victim at Random. A random integer is select based on the size of the Cache.
 */

import java.util.ArrayList;
import java.util.Random;

public class CacheRandom<K,V> extends CacheObject<K,V> {

    //List that contains every element in the cache.
    private ArrayList<K> List;

    //Constructor by name of cache.
    public CacheRandom(String cacheName){
        super(cacheName);
        List = new ArrayList<K>();
    }

    //Constructor by name of cache and maximum of entries allowed.
    public CacheRandom(String cacheName, int maxEntries){
        super(cacheName,maxEntries);
        List = new ArrayList<K>();
    }

    //Constructor of cahe by cache name, maximum of entries and maximum time allowed in cache.
    public CacheRandom(String cacheName, int maxEntries, long entryMaxTime, long cacheMaxTime){
        super(cacheName, maxEntries, entryMaxTime, cacheMaxTime);
        List = new ArrayList<K>();
    }

    /*
    * Method that finds the appropriate victim of the cache based on it's definition.
    * Returns the key of the selected victim.
    */
    @Override
    public K getVictim(){
        Random RNG = new Random();
        int victimIndex = RNG.nextInt(List.size());
        return List.remove(victimIndex);
    }

    /*
    * Method that evicts element of the cache with the specified key.
    * Returns nothing.
    */
    @Override
    public void evict(K key){
        super.evict(key);
        List.remove(key);
    }

    /*
    * Method that inserts the element key in the cache.
    * Returns nothing.
    */
    @Override
    public void put(K key, V value){
        super.put(key,value);
        List.add(key);
    }

}
