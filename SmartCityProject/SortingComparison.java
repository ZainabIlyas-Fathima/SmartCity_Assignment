import java.util.*;

public class SortingComparison {

    // Bubble Sort
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for(int i = 0; i < n-1; i++) {
            for(int j = 0; j < n-i-1; j++) {
                if(arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    // Merge Sort
    public static void mergeSort(int[] arr, int left, int right) {
        if(left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid+1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for(int i=0;i<n1;i++)
            L[i] = arr[left + i];

        for(int j=0;j<n2;j++)
            R[j] = arr[mid + 1 + j];

        int i=0, j=0, k=left;

        while(i<n1 && j<n2) {
            if(L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while(i<n1) {
            arr[k] = L[i];
            i++; k++;
        }

        while(j<n2) {
            arr[k] = R[j];
            j++; k++;
        }
    }

    // Quick Sort
    public static void quickSort(int[] arr, int low, int high) {
        if(low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for(int j=low; j<high; j++) {
            if(arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Enter numbers manually");
        System.out.println("2. Generate random numbers");
        System.out.print("Choose option: ");
        int choice = scanner.nextInt();

        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();

        int[] original = new int[n];

        if(choice == 1) {
            for(int i=0;i<n;i++) {
                System.out.print("Enter number " + (i+1) + ": ");
                original[i] = scanner.nextInt();
            }
        } else {
            Random rand = new Random();
            for(int i=0;i<n;i++) {
                original[i] = rand.nextInt(1000);
            }
            System.out.println("Random data generated.");
        }

        int[] arr1 = original.clone();
        int[] arr2 = original.clone();
        int[] arr3 = original.clone();

        long start, end;

        start = System.nanoTime();
        bubbleSort(arr1);
        end = System.nanoTime();
        long bubbleTime = end - start;

        start = System.nanoTime();
        mergeSort(arr2, 0, arr2.length-1);
        end = System.nanoTime();
        long mergeTime = end - start;

        start = System.nanoTime();
        quickSort(arr3, 0, arr3.length-1);
        end = System.nanoTime();
        long quickTime = end - start;

        System.out.println("\nSorted Output (Quick Sort): " + Arrays.toString(arr3));

        System.out.println("\nPerformance Comparison (nanoseconds)");
        System.out.println("--------------------------------------");
        System.out.println("Bubble Sort : " + bubbleTime);
        System.out.println("Merge Sort  : " + mergeTime);
        System.out.println("Quick Sort  : " + quickTime);

        scanner .close();
    }
}