package exceptions;

/**
 * Signals that a year is out of the range the collection accepts.
 *
 * <p>It is an unchecked exception: it extends {@link RuntimeException}, so the methods that
 * throw it do not have to declare it, although a caller can still catch it.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 */
public class YearOutOfRangeException extends RuntimeException {

    /**
     * Constructs a new {@link YearOutOfRangeException} whose message includes the invalid year.
     *
     * @param year the year out of range.
     */
    public YearOutOfRangeException(int year) {
        super("Year out of range: " + year + ".");
    }
}
