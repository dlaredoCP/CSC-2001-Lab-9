public class Heap <T extends Comparable<T>> {
    private int count;
    private T[] array;

    public Heap(T[] array, int count) {
        this.array = array;
        this.count = count;
    }

    public boolean isEmpty(){
        return count==0;
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> Heap<T> emptyHeap(){
        return new Heap<>((T[]) new Comparable[2], 0);
    }

    public T get(int index){
        if (index > length() || index <= 0) {
            throw new IllegalArgumentException();
        } else {
            return array[index];
        }
    }

    public int length(){
        return count;
    }


    @SuppressWarnings("unchecked")
    public void enqueue(T element){
       if (count==0){
           array[1] = element;
       } else {
           if (count==array.length-1){
               T[] newArr = (T[]) new Comparable[array.length*2];
               for (int x = 0; x < count+1; x++) {
                   newArr[x] = array[x];
               }
               array = newArr;
           }
           int index = count+1;
           array[index]=element;
           while (index > 1 && array[index].compareTo(array[index/2]) < 0){
               array[index] = array[index/2];
               array[index/2] = element;
               index /= 2;
           }
       }
       count++;
    }

    public T dequeue(){
        T deleted;
        if (count==0){
            throw new IllegalCallerException();
        } else {
            if (count==1){
                deleted = array[count];
                array[count] = null;
                count--;
            } else {
                deleted = array[1];
                array[1] = array[count];
                array[count] = null;
                count--;
                int index = 1;
                while (index*2 <= count) {
                    int smallerChild = index*2;
                    if (index*2+1 <= count && array[index*2+1].compareTo(array[smallerChild])<0) {
                        smallerChild = index*2+1;
                    }
                    if (array[index].compareTo(array[smallerChild]) <= 0) {
                        break;
                    }
                    T hold = array[index];
                    array[index] = array[smallerChild];
                    array[smallerChild] = hold;
                    index = smallerChild;
                }
            }
            return deleted;
        }
    }

}
