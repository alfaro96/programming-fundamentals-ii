package exceptions;

/**
 * Unchecked exception thrown when an antique's creation year falls outside the
 * accepted range.
 *
 * <p>Because this exception extends {@link RuntimeException}, it does not need to
 * be declared in a {@code throws} clause, although callers may still choose to
 * catch it. The accepted year range is defined by the constants
 * {@link #MIN_YEAR} and {@link #MAX_YEAR}.</p>
 *
 * @author Juan Carlos Alfaro Jiménez
 * @see RuntimeException
 */
public class YearOutOfRangeException extends RuntimeException {

    /** The minimum accepted creation year for an antique. */
    public static final int MIN_YEAR = 1;

    /** The maximum accepted creation year for an antique. */
    public static final int MAX_YEAR = 1900;

    /**
     * Constructs a new {@link YearOutOfRangeException} with a descriptive message
     * that includes the invalid year and the accepted range.
     *
     * @param year The out-of-range year that caused the exception.
     */
    public YearOutOfRangeException(int year) {
        super("Invalid year: " + year + ". Year must be between "
                + MIN_YEAR + " and " + MAX_YEAR + ".");
    }
}
