import java.util.Comparator;

/**
 * Created by alaincruzcasanova on 7/15/17.
 */
public class EntryComparator implements Comparator<Entry> {

    public int compare(Entry o1, Entry o2) {
        try {
            if (o1.getTime() > o2.getTime()) {
                return -1;
            } else {
                return 1;
            }
        } catch(NullPointerException np) {
            System.out.println(np.toString());
        }
        return 0;
    }
}
