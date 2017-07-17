import java.util.*;

// Abstract implementation of the cache
public abstract class CacheObject<K,V> implements Cache<K,V> {

    // Variables that will be needed for our cache settings.

    // Max number of entries that the cache can hold
    private int maxEntries = 10;
    // Max time that a entry can stay in the cache without being read
    // Important, set to 0 to make it infinite
    private long entryMaxTime = 3600;
    // Given time for our cache to free all it's spaces
    // Important, set to 0 to make it infinite
    private long cacheMaxTime = 1000;
    // Name of our cache
    private String cacheName = "";
    // Priority queue that will measure when an entry's life has surpassed our max time
    private PriorityQueue<Entry> priorityQE;
    // Key value map with all our entries
    private Map<K,Entry<K,V>> entryMap;
    // Threads that will run the process of checking entries' and cache's life time
    private Thread entryThread;
    private Thread lifeThread;

    /*
    * Constructor #1
    * Receive the name of our cache as a parameter
    * */
    CacheObject(String cacheName){
        this.cacheName = cacheName;
        // For our priority queue we need to compare each entries' times in order to sort them.
        // Our comparator will help us sorting our entries.
        Comparator<Entry> comparator = new EntryComparator();
        priorityQE = new PriorityQueue<Entry>(30, (Comparator<? super Entry>) comparator);
        entryMap = new HashMap<K, Entry<K, V>>();
        // Initialization of the threads in charge of cleaning the cache.
        initEntryLife(this);
        initLifeTime(this);
    }

    /*
    * Constructor #2
    * Receive the name of our cache as a parameter and the maximum number of entries that
    * the cache can hold.
    * */
    CacheObject(String cacheName, int maxEntries){
        this.cacheName = cacheName;
        this.maxEntries = maxEntries;
        // For our priority queue we need to compare each entries' times in order to sort them.
        // Our comparator will help us sorting our entries.
        Comparator<Entry> comparator = new EntryComparator();
        priorityQE = new PriorityQueue<Entry>(30, (Comparator<? super Entry>) comparator);
        entryMap = new HashMap<K, Entry<K, V>>();
        // Initialization of the threads in charge of cleaning the cache.
        initEntryLife(this);
        initLifeTime(this);
    }

    /*
    * Constructor #3
    * Receive the name of our cache as a parameter, the maximum number of entries that
    * the cache can hold, the maximum life of an entry and the maximum time a cache
    * can go on without clearing all the entries.
    * */
    CacheObject(String cacheName, int maxEntries, long entryMaxTime, long cacheMaxTime){
        this.cacheName = cacheName;
        this.maxEntries = maxEntries;
        this.entryMaxTime = entryMaxTime;
        this.cacheMaxTime = cacheMaxTime;
        // For our priority queue we need to compare each entries' times in order to sort them.
        // Our comparator will help us sorting our entries.
        Comparator<Entry> comparator = new EntryComparator();
        priorityQE = new PriorityQueue<Entry>(30, (Comparator<? super Entry>) comparator);
        entryMap = new HashMap<K, Entry<K, V>>();
        // Initialization of the threads in charge of cleaning the cache.
        initEntryLife(this);
        initLifeTime(this);
    }

    public String getName() {
        return cacheName;
    }

    /*
    * Method that returns the data corresponding to a key.
    * We receive a key and returns it's corresponding value.
     */
    public synchronized V get(K key){
        // First, we need to check if the key is contained in our data structures.
        if (entryMap.containsKey(key)) {
            // If we found the key, we need to create a new entry.
            // This new entry will be used as a replacement to the old one.
            // This is needed, because we need to change the last time the entry was read,
            // and so we need to delete the old one and replace it with a new one to conserve it's
            // reference
            Entry entry = new Entry(key, getTime(), entryMap.get(key).getData());
            // With the entry created, we remove the key from the structures.
            evict(key);
            // Now we add the replacement to the structures.
            entryMap.put(key,entry);
            priorityQE.add(entry);
            return entryMap.get(key).getData();
        }
        // If the key is not found, we return null.
        return null;
    }

    /*
    * Method that will allow us introduce new entries into our data structures.
    * We will receive both the key and it's corresponding value, which will be added.
    * */
    public synchronized void put(K key, V value) {
        // We need to check that we have not surpassed our cache's limit.
        if(entryMap.size()>=maxEntries){
            // Should we have just as much as the allowed number of entries,
            // we need to find a victim and remove.
            K keyVictim = getVictim();
            evict(keyVictim);
        }
        // Add the new entry.
        Entry<K,V> entry = new Entry<K,V>(key,getTime(),value);
        entryMap.put(key,entry);
        priorityQE.add(entry);
    }

    /*
    * Method that will allow us remove an entry from our data structures.
    * We will receive a key and with that we will remove it's corresponding entry.
    * */
    public synchronized void evict(K key){
        Entry entry = entryMap.get(key);
        entryMap.remove(key);
        priorityQE.remove(entry);
    }

    /*
    * Method that will allow us remove all entries from our data structures.
    * */
    public synchronized void clear(){
        entryMap.clear();
        priorityQE.clear();
    }

    /*
    * Returns the current time since epoch.
    * */
    public long getTime(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        long secondsSinceEpoch = calendar.getTimeInMillis() / 1000L;
        return secondsSinceEpoch;
    }

    /*
    * Initialize the thread that will check the cache's expiring time.
    * */
    public void initLifeTime(CacheObject cache) {
        // Set cacheMaxTime to 0 if we don't want our cache clearing our entries.
        if (cacheMaxTime>0) {
            lifeThread = new Thread(new ThreadLifeTime(this));
            lifeThread.start();
        }
    }

    /*
    * Initialize the thread that will check the cache's entries life time.
    * */
    public void initEntryLife(CacheObject cache){
        // Set entryMaxTime to 0 if we don't want our cache clearing our entries.
        if (entryMaxTime>0) {
            entryThread = new Thread(new ThreadEntryLife(this));
            entryThread.start();
        }
    };

    public long getEntryMaxTime(){
        return entryMaxTime;
    }

    public long getCacheMaxTime(){
        return cacheMaxTime;
    }

    // Returns the head of our priority queue.
    public synchronized Entry getFirstQueue(){ return priorityQE.peek(); }

    // Method that interrupts our threads, so that they can finish their corresponding sleeps.
    public void stopThreads(){
        //System.out.println("Stoping threads.");
        entryThread.interrupt();
        lifeThread.interrupt();
    }

    public Map getMap(){ return  entryMap; }

    // Destructor of our class.
    // We want to make sure our threads are stop when we destro our class.
    @Override
    public void finalize(){
        stopThreads();
    }

    // Abstract method that will be implemented in our different caches.
    public abstract K getVictim();

}
