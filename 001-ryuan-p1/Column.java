// TO DO: add your implementation and JavaDocs.
/**
 * This class has some basic methods which will be used in connectFour class
 * like add, insert, delete with functions like shift, double & shrink array
 * size while add or delete.
 * 
 * @author RongLian Yuan
 *
 * @param <T> It describe my type parameter.
 */
public class Column<T> {
    // default initial capacity / minimum capacity
    /**
     * Initialize the the default length of the array is 2.
     */
    private static final int DEFAULT_CAPACITY = 2;

    // underlying array for storage -- you MUST use this for credit!
    // Do NOT change the name or type
    /**
     * data is the variable name of an array.
     */
    private T[] data;

    /**
     * size is the integer which can show how many elements in the array.
     */
    private int size;

    // ADD MORE PRIVATE MEMBERS HERE IF NEEDED!
    /**
     * Default constructor to create a basic array with date type T and the number.
     * of elements is 0;
     */
    @SuppressWarnings("unchecked")
    public Column() {

        data = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;

    }

    /**
     * Overloading. To create an array with the specific size.
     * 
     * @param initialCapacity is the length of the array.
     */
    @SuppressWarnings("unchecked")
    public Column(int initialCapacity) {
        if (initialCapacity < 1) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }
        data = (T[]) new Object[initialCapacity];
        size = 0;

    }

    /**
     * Get the number of elements of the array.
     * 
     * @return the elements of the array.
     */
    public int size() {

        return size;
    }

    /**
     * Get the length of the array.
     * 
     * @return the length of the array.
     */
    public int capacity() {

        return data.length;
    }

    /**
     * To set a value at a specific place of an array with data type T.
     * 
     * @param index to set the value at this place.
     * @param value to set this value at the specific place.
     * @return the old value before set a new value.
     */
    public T set(int index, T value) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
        }
        T oldValue = data[index];
        data[index] = value;

        return oldValue;
    }

    /**
     * With the specific index, return the value of this index with the data type T.
     * 
     * @param index to return the value at this index.
     * @return the value with data type T at the place of index.
     */
    public T get(int index) {

        if (index < 0 || index > data.length) {

            throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
        }

        return data[index]; // default return, make sure to remove/change

    }

    /**
     * To add a value into the array with data type T. If there is no place for
     * adding, double the length of the old array and copy the data to a new array,
     * then add this value.
     * 
     * @param value add this value to the array
     */

    @SuppressWarnings("unchecked")
    public void add(T value) {
        if (size < data.length) {
            data[size] = value;
            size++;
        } else if (size >= data.length) {
            T[] newArray = (T[]) new Object[size * 2];
            for (int i = 0; i < data.length; i++) {
                newArray[i] = data[i];
            }

            data = newArray;
            data[size] = value;
            size++;
        }

    }

    /**
     * To add some values at the specific index, if there is something at this
     * place, shift the rest of other elements. Also, if there is no place to add,
     * double the size of the array and move all elements to the new array.
     * 
     * @param index add value at this place.
     * @param value add this value to the specific index.
     */

    @SuppressWarnings("unchecked")
    public void add(int index, T value) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
        }
        if (size == data.length) {
            T[] newArrayDoubleSize = (T[]) new Object[data.length * 2];
            for (int i = 0; i < data.length; i++) {
                newArrayDoubleSize[i] = data[i];
            }
            data = newArrayDoubleSize;
        }
        if (data[index] == null) {
            data[index] = value;
            size++;
        } else {
            T[] newArrayForSwap = (T[]) new Object[data.length];
            for (int i = index; i < size; i++) {
                newArrayForSwap[i + 1] = data[i];
            }
            newArrayForSwap[index] = value;
            for (int i = 0; i < index; i++) {
                newArrayForSwap[i] = data[i];
            }
            data = newArrayForSwap;
            size++;
        }
    }

    /**
     * To delete the elements at the specific index of the array. If the number of
     * the elements in this array less than 1/3 of the capacity, shrink the length
     * of this array.
     * 
     * @param index to delete the element at this place.
     * @return the old value before deleting.
     */

    @SuppressWarnings("unchecked")
    public T delete(int index) {

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + " out of bounds!");
        }
        T itemWillRemove = data[index];
        T[] newArrayAfterDelete = (T[]) new Object[data.length];
        for (int i = index + 1; i < size; i++) {
            newArrayAfterDelete[i - 1] = data[i];
        }

        for (int i = 0; i < index; i++) {
            newArrayAfterDelete[i] = data[i];
        }

        data = newArrayAfterDelete;
        size--;
        if (size <= (data.length / 3)) {
            T[] newHalveArray = (T[]) new Object[data.length / 2];
            for (int i = 0; i < newHalveArray.length; i++) {
                newHalveArray[i] = data[i];
            }
            data = newHalveArray;
        }
        return itemWillRemove;

    }

    // ******************************************************
    // ******* BELOW THIS LINE IS TESTING CODE *******
    // ******* Edit it as much as you'd like! *******
    // ******* Remember to add JavaDoc *******
    // ******************************************************
    /**
     * print out the array's length and the number of the element of it.
     * 
     * @return print out the array of the size and capacity.
     */
    public String toString() {

        StringBuilder s = new StringBuilder("Column with " + size() + " items and a capacity of " + capacity() + ":");
        for (int i = 0; i < size(); i++) {
            s.append("\n  [" + i + "]: " + get(i));
        }
        return s.toString();

    }

    /**
     * To create an array and try to add, insert and delete something in this array
     * to make sure to methods above works well.
     * 
     * @param args arguments
     * 
     */

    public static void main(String args[]) {

        Column<Integer> nums = new Column<>();
        if ((nums.size() == 0) && (nums.capacity() == 2)) {
            System.out.println("Yay 1");
        }

        // append some numbers
        for (int i = 0; i < 3; i++) {
            nums.add(i * 2);
        }

        if (nums.size() == 3 && nums.get(2) == 4 && nums.capacity() == 4) {
            System.out.println("Yay 2");
        }

        // create a column of strings
        Column<String> msg = new Column<>();

        // insert some strings
        msg.add(0, "world");
        msg.add(0, "hello");
        msg.add(1, "new");
        msg.add(3, "!");

        // checking
        if (msg.get(0).equals("hello") && msg.set(1, "beautiful").equals("new") && msg.size() == 4
                && msg.capacity() == 4) {
            System.out.println("Yay 3");
        }

        // delete
        if (msg.delete(1).equals("beautiful") && msg.get(1).equals("world") && msg.size() == 3) {
            System.out.println("Yay 4");
        }

        // shrinking
        nums.add(100);
        nums.add(0, -10);
        if (nums.delete(0) == -10 && nums.delete(1) == 2 && nums.delete(2) == 100 && nums.size() == 2
                && nums.capacity() == 4) {
            System.out.println("Yay 5");
        }

    }

}