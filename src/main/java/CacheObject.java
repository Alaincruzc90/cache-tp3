import java.util.*;

/**
 * Created by alaincruzcasanova on 7/15/17.
 */
public abstract class CacheObject<K,V> implements Cache<K,V> {

    private int maxEntries = 10;
    private long entryMaxTime = 3600;
    private long cacheMaxTime = 0;
    private String cacheName = "";

    private PriorityQueue<Entry> priorityQE;

    private Map<K,Entry<K,V>> entryMap;

    private Thread entryThread;
    private Thread lifeThread;

    public CacheObject(String cacheName){
        this.cacheName = cacheName;
        Comparator<Entry> comparator = new EntryComparator();
        priorityQE = new PriorityQueue<Entry>(10, (Comparator<? super Entry>) comparator);
        entryMap = new HashMap<K, Entry<K, V>>();
        initEntryLife(this);
        initLifeTime(this);
    }

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
            priorityQE.remove(key);
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
            lifeThread = new Thread(new ThreadEntryLife(this));
            lifeThread.start();
        }
    }

    public void initEntryLife(CacheObject cache){
        if (entryMaxTime>0) {
            entryThread = new Thread(new ThreadLifeTime(this));
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
        System.out.println("Stoping threads.");
        entryThread.interrupt();
        lifeThread.interrupt();
    }

    @Override
    public void finalize(){
        stopThreads();
    }

    public abstract K getVictim();

}
