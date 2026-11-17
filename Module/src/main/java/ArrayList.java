import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList <T extends Comparable<T>>{
    private int liveCount;
    private T[] array;

    @SuppressWarnings("unchecked")
    public ArrayList(T[] array, int liveCount) {
        this.array = array;
        this.liveCount = liveCount;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> ArrayList<T> emptyList(){
        return new ArrayList<>((T[]) new Comparable[2], 0);
    }

    /** Returns true if the collection is empty, false otherwise */
    public boolean isEmpty(){
        return liveCount==0;
    }

    /** Returns the number of elements in the list represented by the object */
    public int length() {
        return liveCount;
    }

    /** Accept a valid index and returns the value at that index in the ArrayList */
    public T get(int index){
        if (index>length()-1 || index<0){
            throw new IllegalArgumentException();
        } else {
            return array[index];
        }
    }

    @SuppressWarnings("unchecked")
    /** Accepts an element and adds it to the end of a list */
    public void add (T newVal){
        if (length() == array.length-1){
            T[] newArr = (T[]) new Comparable[array.length*2];
            for (int x = 0; x < array.length-1; x++) {
                newArr[x] = array[x];
            }
            newArr[length()] = newVal;
            array = newArr;
        } else {
            array[length()] = newVal;
        }
        liveCount++;
    }

    /** Accepts an ArrayList of generic type T and returns a merge sorted version of the ArrayList */
    public static <T extends Comparable<T>> ArrayList<T> mergeSort(ArrayList<T> data) {
        if (data.length()<=1){
            return data;
        }
        return mergeHelper(data);
    }

    /** Recursive helper method for mergeSort */
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> ArrayList<T> mergeHelper(ArrayList<T> data){
        if (data.length()>1) {
            int half = data.length() / 2;
            T[] firsthalf = (T[]) new Comparable[half];
            System.arraycopy(data.array, 0, firsthalf, 0, half);
            T[] secondhalf = (T[]) new Comparable[data.length() - half];
            if (data.length() - half >= 0)
                System.arraycopy(data.array, half, secondhalf, 0, data.length() - half);
            return merge(mergeHelper(new ArrayList<>(firsthalf, firsthalf.length)), mergeHelper(new ArrayList<>(secondhalf, secondhalf.length)));
        }
        return data;
    }

    /** Accepts a value of type T and inserts it into a sorted ArrayList */
    @SuppressWarnings("unchecked")
    public void insert(T newVal) {
        int index = Arrays.binarySearch(array, newVal);
        if (index < 0) {
            index = -(index + 1);
        }
        T[] newArray = (T[]) new Comparable[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, index);
        newArray[index] = newVal;
        System.arraycopy(array, index, newArray, index + 1, array.length - index);
        array = newArray;
        liveCount++;
    }

    /** Accepts two sorted ArrayLists and merges them into one ArrayList that contains all the elements in sorted order */
    public static <T extends Comparable<T>> ArrayList<T> merge(ArrayList<T> dataOne, ArrayList<T> dataTwo){
        ArrayList<T> main = new ArrayList<>(dataTwo.array, dataOne.length()+dataTwo.length());
        for (T item : main.array){
            main.insert(item);
        }
        return main;
    }

}
