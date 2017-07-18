/**
 * Created by esteban on 15/07/2017.
 * Cache that chooses it's victim at Random. A random integer is select based on the size of the Cache.
 */

import java.util.ArrayList;
import java.util.Random;

public class CacheRandom<K,V> extends CacheObject<K,V> {

    //List that contains every element in the cache.
    private ArrayList<K> List;

    /*
    * Constructor #1.
    * @Params:
    * cacheName: The name of our cache.
    * */
    public CacheRandom(String cacheName){
        super(cacheName);
        List = new ArrayList<K>();
    }

    /*
    * Constructor #2.
    * @Params:
    * cacheName: The name of our cache.
    * cacheMaxTime: Maximum amount of time our cache can stay without clearing it's entries.
    * */
    public CacheRandom(String cacheName, long cacheMaxTime){
        super(cacheName,cacheMaxTime);
        List = new ArrayList<K>();
    }

    /*
    * Constructor #3.
    * @Params:
    * cacheMaxTime: Maximum amount of time our cache can stay without clearing it's entries.
    * cacheName: The name of our cache.
    * */
    public CacheRandom(long entryMaxTime, String cacheName){
        super(entryMaxTime,cacheName);
        List = new ArrayList<K>();
    }

    /*
    * Constructor #4.
    * @Params:
    * cacheName: The name of our cache.
    * maxEntries: Maximum amount of entries that our cache can hold simultaneously.
    * */
    public CacheRandom(String cacheName, int maxEntries){
        super(cacheName,maxEntries);
        List = new ArrayList<K>();
    }

    /*
    * Constructor #5.
    * @Params:
    * cacheName: The name of our cache.
    * maxEntries: Maximum amount of entries that our cache can hold simultaneously.
    * entryMaxTime: Maximum amount of time an entry can stay in the cache without being read.
    * */
    public CacheRandom(String cacheName, int maxEntries, long entryMaxTime){
        super(cacheName,maxEntries,entryMaxTime);
        List = new ArrayList<K>();
    }

    /*
    * Constructor #6.
    * @Params:
    * cacheName: The name of our cache.
    * maxEntries: Maximum amount of entries that our cache can hold simultaneously.
    * entryMaxTime: Maximum amount of time an entry can stay in the cache without being read.
    * */
    public CacheRandom(String cacheName, long cacheMaxTime, int maxEntries){
        super(cacheName,cacheMaxTime,maxEntries);
        List = new ArrayList<K>();
    }

    /*
    * Constructor #7.
    * @Params:
    * cacheName: The name of our cache.
    * entryMaxTime: Maximum amount of time an entry can stay in the cache without being read.
    * cacheMaxTime: Maximum amount of time our cache can stay without clearing it's entries.
    * */
    public CacheRandom(String cacheName, long cacheMaxTime, long entryMaxTime){
        super(cacheName,cacheMaxTime,entryMaxTime);
        List = new ArrayList<K>();
    }


    /*
    * Constructor #8.
    * @Params:
    * cacheName: The name of our cache.
    * maxEntries: Maximum amount of entries that our cache can hold simultaneously.
    * entryMaxTime: Maximum amount of time an entry can stay in the cache without being read.
    * cacheMaxTime: Maximum amount of time our cache can stay without clearing it's entries.
    * */
    public CacheRandom(String cacheName, int maxEntries, long entryMaxTime, long cacheMaxTime){
        super(cacheName,maxEntries,entryMaxTime,cacheMaxTime);
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
        K key = List.remove(victimIndex);
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

    /*
    * Method that clear all our data structures from their entries.
    * */
    @Override
    public void clear(){
        super.clear();
        List.clear();
    }

}
