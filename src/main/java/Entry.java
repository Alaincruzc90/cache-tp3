/**
 * Class where we will save the data of our entries.
 */

public class Entry<K, V> {

    // Time when the entry was last accessed
    private long time = 0;
    // Entry's key.
    // We will use this key to search for this entry in the multiple data structures.
    private K key = null;
    // Data that correspond to the key.
    private V data = null;

    // Constructor
    public Entry(K key, long time, V data){
        this.key = key;
        this.time = time;
        this.data = data;
    }

    public long getTime(){
        return time;
    }

    public K getKey(){ return key; }

    public V getData(){ return data; }
}
