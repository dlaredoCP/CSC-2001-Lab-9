import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    @Test
    void testMergeSort_evens(){
        ArrayList<Integer> list = ArrayList.emptyList();
        list.add(4);
        list.add(33);
        list.add(19);
        list.add(7);
        list = ArrayList.mergeSort(list);
        assertEquals(7, list.get(1));
    }
    @Test
    void testMergeSort_odds(){
        ArrayList<Integer> list = ArrayList.emptyList();
        list.add(4);
        list.add(33);
        list.add(19);
        list.add(7);
        list.add(40);
        list = ArrayList.mergeSort(list);
        assertEquals(7, list.get(1));
    }

    //TODO: merge test
    @Test
    void testMerge(){
        ArrayList<Integer> listOne = ArrayList.emptyList();
        ArrayList<Integer> listTwo = ArrayList.emptyList();

        listOne.add(1);
        listOne.add(3);
        listOne.add(5);
        listOne.add(7);

        listTwo.add(2);
        listTwo.add(4);
        listTwo.add(6);
        listTwo.add(8);

        ArrayList<Integer> finalList = listOne.merge(listTwo);
        assertEquals(1, finalList.get(0));
        assertEquals(2, finalList.get(1));
        assertEquals(3, finalList.get(2));
    }

    @Test
    void testBinarySearch(){
        ArrayList<Integer> list = ArrayList.emptyList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(6);
        assertEquals(0,list.binarySearch(1));
    }

    @Test
    void testRemove(){
        ArrayList<Integer> list = ArrayList.emptyList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(6);
        list.remove(1);
        assertEquals(3, list.get(1));
    }

    @Test
    void testHeapSort(){
        ArrayList<Integer> list = ArrayList.emptyList();
        list.add(35);
        list.add(8);
        list.add(2);
        list.add(19);
        list.add(16);
        list.heapSort();
        assertEquals(2, list.get(0));
        assertEquals(8, list.get(1));
        assertEquals(16, list.get(2));
    }

    /** merge sort should be faster than heap sort */
    /** --> timing ArrayList sizes of 10, 10^2, 10^3, 10^4, 10^5. . .10^10 */

    @Test
    void merge_measurement(){
        for (long x=1; x<=Math.pow(10,9); x*=10){
            ArrayList<Integer> list = ArrayList.emptyList();
            for (int i=0; i<x; i++){
                list.add(new Random().nextInt(100));
            }
            long startTime = System.nanoTime();
            list = ArrayList.mergeSort(list);
            long endTime = System.nanoTime();
            double t = (double) (endTime - startTime) / 1000000000;
            IO.println("It took " + t + " seconds for mergeSort to sort a list of " + x + " elements!");
        }
    }

    @Test
    void heap_measurement(){
        for (long x=1; x<=Math.pow(10,9); x*=10){
            ArrayList<Integer> list = ArrayList.emptyList();
            for (int i=0; i<x; i++){
                list.add(new Random().nextInt(100));
            }
            long startTime = System.nanoTime();
            list.heapSort();
            long endTime = System.nanoTime();
            double t = (double) (endTime - startTime) / 1000000000;
            IO.println("It took " + t + " seconds for heapSort to sort a list of " + x + " elements!");
        }
    }
}