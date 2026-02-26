package module3;
import java.util.Random;

public class AlgorithmPerformanceAnalyzer {

    // Generate random array
    public static int[] generateArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(1000);
        }
        return arr;
    }

    // Bubble Sort algorithm
    public static void bubbleSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Linear Search algorithm
    public static int linearSearch(int[] arr, int key) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == key) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] sizes = {100, 500, 1000};

        System.out.println("Algorithm Performance Analyzer");
        System.out.println("--------------------------------------------------");
        System.out.println("Input Size | Sorting Time (ns) | Searching Time (ns)");
        System.out.println("--------------------------------------------------");

        for (int size : sizes) {

            // Generate array
            int[] arr = generateArray(size);

            // Measure Bubble Sort time
            long startSort = System.nanoTime();
            bubbleSort(arr);
            long endSort = System.nanoTime();
            long sortingTime = endSort - startSort;

            // Value to search
            int key = arr[size / 2];

            // Measure Linear Search time
            long startSearch = System.nanoTime();
            linearSearch(arr, key);
            long endSearch = System.nanoTime();
            long searchingTime = endSearch - startSearch;

            // Display result
            System.out.println(size + "        | " + sortingTime + "        | " + searchingTime);
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Test completed successfully.");
    }
}


