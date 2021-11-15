package algo;

public class SelectionSort {
    public int findSmallest(int[] array, int begIndex) {
        int smallest = begIndex;
        for (int i = begIndex + 1; i < array.length; i++) {
            if (array[smallest] < array[i]) {
                smallest = i;
            }
        }
        return smallest;
    }
}
