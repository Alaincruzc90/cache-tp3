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
    public CacheObject(String cacheName){
        this.cacheName = cacheName;
        // For our priority queue we need to compare thier time in order to sort them
        Comparator<Entry> comparator = new EntryComparator();
        priorityQE = new PriorityQueue<Entry>(10, (Comparator<? super Entry>) comparator);
        entryMap = new HashMap<K, Entry<K, V>>();
        initEntryLife(this);
        initLifeTime(this);
    }

    /*
    * Constructor #1
    * Receive the name of our cache as a parameter
    * */
    public CacheObject(String cacheName, int maxEntries){
        this.cacheName = cacheName;
        this.maxEntries = maxEntries;
        Comparator<Entry> comparator = new EntryComparator();
        priorityQE = new PriorityQueue<Entry>(10, (Comparator<? super Entry>) comparator);
        entryMap = new HashMap<K, Entry<K, V>>();
        initEntryLife(this);
        initLifeTime(this);
    }

    public CacheObject(String cacheName, int maxEntries, long entryMaxTime, long cacheMaxTime){
        this.cacheName = cacheName;
        this.maxEntries = maxEntries;
        this.entryMaxTime = entryMaxTime;
        this.cacheMaxTime = cacheMaxTime;
        Comparator<Entry> comparator = new EntryComparator();
        priorityQE = new PriorityQueue<Entry>(10, (Comparator<? super Entry>) comparator);
        entryMap = new HashMap<K, Entry<K, V>>();
        initEntryLife(this);
        initLifeTime(this);
    }

    public String getName() {
        return cacheName;
    }

    public synchronized V get(K key){
        if (entryMap.containsKey(key)) {
            Entry oldEntry = entryMap.get(key);
            priorityQE.remove(oldEntry);
            Entry entry = new Entry(key, getTime(), entryMap.get(key).getData());
            priorityQE.add(entry);
            return entryMap.get(key).getData();
        }
        return null;
    }

    public synchronized void put(K key, V value) {
        if(entryMap.size()>=maxEntries){
            K keyVictim = getVictim();
            evict(keyVictim);
        }
        Entry<K,V> entry = new Entry<K,V>(key,getTime(),value);
        entryMap.put(key,entry);
        priorityQE.add(entry);
    }

    public synchronized void evict(K key){
        Entry entry = entryMap.get(key);
        entryMap.remove(key);
        priorityQE.remove(entry);
    }

    public synchronized void clear(){
        entryMap.clear();
        priorityQE.clear();
    }

    public long getTime(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        long secondsSinceEpoch = calendar.getTimeInMillis() / 1000L;
        return secondsSinceEpoch;
    }

    public void initLifeTime(CacheObject cache) {
        if (cacheMaxTime>0) {
            lifeThread = new Thread(new ThreadLifeTime(this));
            lifeThread.start();
        }
    }

    public void initEntryLife(CacheObject cache){
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

    public Entry getFirstQueue(){
        return priorityQE.peek();
    }

    public void stopThreads(){
        //System.out.println("Stoping threads.");
        entryThread.interrupt();
        lifeThread.interrupt();
    }

    @Override
    public void finalize(){
        stopThreads();
    }

    public abstract K getVictim();

}
