package prioritylist;

/**
 * Represents a data item with a priority and a description.
 * Used in the {@link PriorityList} class for sorting based on priority.
 * Lower values indicate higher priority.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 */
public class Record {

    private int priority;
    private String description;

    /**
     * Constructs a record with the specified priority and description.
     *
     * @param priority The priority level (lower means higher priority)
     * @param description A textual description of the record
     */
    public Record(int priority, String description) {
        this.priority = priority;
        this.description = description;
    }

    /**
     * Returns the priority of the record.
     *
     * @return The priority value
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Returns the description of the record.
     *
     * @return The description string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Compares this record to another for equality based on priority and description.
     *
     * @param o The object to compare to
     * @return @{code true} if both records have the same priority and description, {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return priority == record.priority && description.equals(record.description);
    }
}
