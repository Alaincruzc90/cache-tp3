/**
 * Created by alaincruzcasanova on 7/15/17.
 */
public class Entry<K, V> {

    private long time = 0;
    private K key = null;
    private V data = null;

    public Entry(K key, long time, V data){
        this.key = key;
        this.time = time;
        this.data = data;
    }

    public long getTime(){
        return time;
    }

    public K getKey(){
        return key;
    }

    public V getData(){
        return data;
    }
}
