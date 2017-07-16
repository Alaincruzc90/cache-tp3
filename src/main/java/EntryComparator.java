import java.util.Comparator;

/**
 * Comparator that will be use in our prioty queues.
 * We will be comparing the time the entries where created at,
 * and with this information we will decide the
 */
public class EntryComparator implements Comparator<Entry> {

    // Signature method of the Comparator interface
    public int compare(Entry o1, Entry o2) {
        try {
            // Compare the time of two entries and sort them
            if (o1.getTime() > o2.getTime()) {
                return 1;
            } else {
                return -1;
            }
        } catch(NullPointerException np) {
            System.out.println(np.toString());
        }
        return 0;
    }
}
