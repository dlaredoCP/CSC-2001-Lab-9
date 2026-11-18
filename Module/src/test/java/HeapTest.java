import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {

    @Test
    void testEnqueue(){
        Heap<Integer> heap = Heap.emptyHeap();
        heap.enqueue(3);
        heap.enqueue(5);
        heap.enqueue(2);
        assertEquals(3, heap.length());
    }

    @Test
    void testDequeue() {
        Heap<Integer> heap = Heap.emptyHeap();
        heap.enqueue(3);
        heap.enqueue(5);
        heap.enqueue(2);
        heap.dequeue();
        assertEquals(2, heap.length());
    }
}