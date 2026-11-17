public class LinkedList <T>{
    private T first;
    private LinkedList<T> rest;

    public LinkedList(T first, LinkedList<T> rest){
        this.first = first;
        this.rest = rest;
    }

    public int size() {
        if (first == null) {
            return 0;
        } else if (rest == null) {
            return 1;
        } else {
            return 1 + rest.size();
        }
    }

    public void add(T another){
        if (first == null){
            first = another;
            rest = null;
        } else
        if (rest == null) {
            rest = new LinkedList<>(another, null);
        } else {
            rest.add(another);
        }
    }

    public LinkedList<T> mergeSort(LinkedList<T> data) {
        //#1: Divide the list into two halves

        //#2: Merge sort the left half

        //#3: Merge sort the right half

        //#4: Merge the sorted halves together

        return null;
    }

    public static <T extends Comparable<T>> LinkedList<T> mergeHelper(LinkedList<T> data){
        return null;
    }

    public void insert(T newVal) {

    }

    public void merge(LinkedList<T> dataOne, LinkedList<T> dataTwo) {

    }
}