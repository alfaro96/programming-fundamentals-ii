package prioritylist;

import exceptions.*;

/**
 * A priority-based list that orders elements by their priority value.
 * Lower values represent higher priority.
 * Supports adding elements, removing the last added, and removing a specific element.
 *
 * @author Juan Carlos Alfaro Jiménez
 * @version 1.0
 */
public class PriorityList {

    private int size;
    private Record[] array;
    private int pointer;

    /**
     * Constructs a PriorityList with a specified maximum size.
     *
     * @param size The maximum number of elements allowed
     */
    public PriorityList(int size) {
        this.array = new Record[size];
        this.pointer = 0;
        this.size = size;
    }

    /**
     * Adds a record to the list, ordered by priority.
     *
     * @param record The record to add
     * @throws ListFullException if the list is already full
     */
    public void add(Record record) throws ListFullException {
        if (pointer == size - 1) {
            throw new ListFullException();
        }

        if (pointer == 0) {
            array[0] = record;
        } else {
            int i = pointer - 1;
            while (i >= 0 && array[i].getPriority() > record.getPriority()) {
                array[i + 1] = array[i];
                i--;
            }
            array[i + 1] = record;
        }
        pointer++;
    }

    /**
     * Removes and returns the last record in the list.
     *
     * @return The removed record
     * @throws EmptyListException if the list is empty
     */
    public Record remove() throws EmptyListException {
        if (pointer == 0) {
            throw new EmptyListException();
        }
        pointer--;
        Record record = array[pointer];
        array[pointer] = null;
        return record;
    }

    /**
     * Removes a specific record from the list if it exists.
     *
     * @param record The record to remove
     * @throws ElementNotFoundException if the record is not found
     */
    public void remove(Record record) throws ElementNotFoundException {
        for (int i = 0; i < pointer; i++) {
            if (record.equals(array[i])) {
                for (int j = i; j < pointer - 1; j++) {
                    array[j] = array[j + 1];
                }
                pointer--;
                return;
            }
        }
        throw new ElementNotFoundException();
    }
}
