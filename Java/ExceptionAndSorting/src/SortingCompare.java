import java.util.Arrays;

public class SortingCompare {

    // Change this constant to easily modify the size of the arrays
    private static final int ARRAY_SIZE = 100000;

    public static void main(String[] args) {
        // Generate two arrays with random integers
        int[] array1 = generateRandomArray();
        int[] array2 = Arrays.copyOf(array1, array1.length);

        long startTime1 = System.currentTimeMillis();
        // calling selectionSort function with first array
        selectionSort(array1);
        long runTime1 = System.currentTimeMillis() - startTime1;

        // Time how long it takes to sort the first array and print out the time
        System.out.println("Selection Sort Time: " + runTime1 + " milliseconds");

        // Sort the second (identical) array using Arrays.sort()
        long startTime2 = System.currentTimeMillis();
        Arrays.sort(array2);
        long runTime2 = System.currentTimeMillis() - startTime2;

        // Time how long it takes to sort the second array and print out the time
        System.out.println("Arrays.sort() Time: " + runTime2 + " milliseconds");

        // Report the times in comments
        System.out.println("\nArray Size: " + ARRAY_SIZE);
        System.out.println("Selection Sort Time: " + runTime1 + " milliseconds");
        System.out.println("Arrays.sort() Time: " + runTime2 + " milliseconds");
    }

    // Method to generate an array with random integers
    private static int[] generateRandomArray() {
        int[] array = new int[ARRAY_SIZE];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Integer.MAX_VALUE * Math.random());
        }
        return array;
    }

    // Selection Sort implementation
    private static void selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }
}
