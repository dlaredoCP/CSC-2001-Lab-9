import java.util.NoSuchElementException;

public class ArrayList <T extends Comparable<T>>{
    private int liveCount;
    private T[] array;

    public ArrayList(T[] array, int liveCount) {
        this.array = array;
        this.liveCount = liveCount;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> ArrayList<T> emptyList(){
        return new ArrayList<>((T[]) new Comparable[2], 0);
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
    public void makeEmpty(){
        array = (T[]) new Comparable[2];
        liveCount = 0;
    }

    @SuppressWarnings("unchecked")
    public void remove(int index){
        if (index > liveCount-1){
            throw new NoSuchElementException();
        } else {
            T[] replace = (T[]) new Comparable[liveCount-1];
            System.arraycopy(array, 0, replace, 0, index-1);
            System.arraycopy(array, index+1, replace, index, length()-index-1);
            array = replace;
            liveCount--;
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
            return mergeHelper(new ArrayList<>(firsthalf, firsthalf.length)).merge(mergeHelper(new ArrayList<>(secondhalf, secondhalf.length)));
        }
        return data;
    }

    /** Accepts two sorted ArrayLists and merges them into one ArrayList that contains all the elements in sorted order */
    public ArrayList<T> merge(ArrayList<T> dataTwo){
        ArrayList<T> together = emptyList();
        int a = 0;
        int b = 0;
        while (a<length() || b<dataTwo.length()){
            if (b==dataTwo.length()){
                for (int x=a; x<length(); x++){
                    together.add(array[x]);
                }
                break;
            }
            if (a==length()){
                for (int x=b; x<dataTwo.length(); x++){
                    together.add(dataTwo.array[x]);
                }
                break;
            } else {
                if (array[a].compareTo(dataTwo.array[b]) < 0) {
                    together.add(array[a]);
                    a++;
                } else if (array[a].compareTo(dataTwo.array[b]) > 0) {
                    together.add(dataTwo.array[b]);
                    b++;
                } else {
                    together.add(array[a]);
                    together.add(dataTwo.array[b]);
                    a++;
                    b++;
                }
            }
        }
        return together;
    }

    /** Accepts a target element and returns its index in the ArrayList, returns -1 if it's not in the ArrayList */
    public int binarySearch(T target){
        if (array == null || target == null) {
            return -1;
        }
        int low = 0;
        int high = liveCount - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = array[mid].compareTo(target);
            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public void heapSort(){
        Heap<T> heap = Heap.emptyHeap();
        for (int x=0; x<liveCount; x++){
            heap.enqueue(array[x]);
        }
        makeEmpty();
        while (!heap.isEmpty()) {
            add(heap.dequeue());
        }
    }

}
