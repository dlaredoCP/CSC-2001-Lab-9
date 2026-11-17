import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {
    //TODO: add test

    //TODO: mergeSort test
    @Test
    void testMergeSort(){
        ArrayList<Integer> list = ArrayList.emptyList();
        list.add(4);
        list.add(33);
        list.add(19);
        list.add(7);
        list = ArrayList.mergeSort(list);
        assertEquals(7, list.get(1));
    }
    //TODO: insert test

    //TODO: merge test
    @Test
    void testMerge(){

    }
}