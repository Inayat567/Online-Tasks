import java.util.ArrayList;
import java.util.Scanner;

public class StockPrice {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        // initialize a targetPrice variable to save the user entered target price value
        float targetPrice;

        // Array of stock price;
        Float[] stock_Prices = { 540.5f, 920.2f, 811.8f, 103.4f, 1002.9f, 700.3f, 713.1f, 1600.0f, 139.9f, 680.3f };

        // Initializing the arrayList same as array of stock price
        ArrayList<Float> stock_Prices_Array_List = new ArrayList<>();
        for (float price : stock_Prices) {

            // add methods add the value to the arrayList
            stock_Prices_Array_List.add(price);
        }

        // calling calculateAveragePrice function to get the average of the stock prices
        // with array and arrayList
        float averagePriceArray = Helper.calculateAveragePrice(stock_Prices);
        float averagePriceArrayList = Helper.calculateAveragePrice(stock_Prices_Array_List.toArray(new Float[0]));

        System.out.println("Average Stock Price (Array) = " + averagePriceArray);
        System.out.println("Average Stock Price (ArrayList) = " + averagePriceArrayList);

        /*
         * calling findMaximumPrice function to get the maximum price from the stock
         * prices with array and arrayList
         */
        float maxPriceArray = Helper.findMaximumPrice(stock_Prices);
        float maxPriceArrayList = Helper.findMaximumPrice(stock_Prices_Array_List.toArray(new Float[0]));

        System.out.println("Maximum Stock Price (Array) = " + maxPriceArray);
        System.out.println("Maximum Stock Price (ArrayList) = " + maxPriceArrayList);

        System.out.print("Enter Target Price: ");

        // handling error using try catch because may be user enter any wrong value i.e.
        // String
        try {
            // getting target price value from user
            targetPrice = scan.nextFloat();
        } catch (Exception e) {
            scan.close();
            throw new Error("Invalid Input!");
        }
        scan.close();

        /*
         * calling countOccurrences function to get number of occurance of target price
         * value entered by user with array and arrayList
         */
        int occurrenceCountArray = Helper.countOccurrences(stock_Prices, targetPrice);
        int occurrenceCountArrayList = Helper.countOccurrences(stock_Prices_Array_List.toArray(new Float[0]),
                targetPrice);

        System.out.println("Occurrence Count of " + targetPrice + " (Array) = " + occurrenceCountArray);
        System.out.println("Occurrence Count of " + targetPrice + " (ArrayList) = " + occurrenceCountArrayList);

        /*
         * Calling helper's computeCumulativeSum function to get the cummulative sum
         * arrayList
         */
        ArrayList<Float> cumulativeSumArrayList = Helper.computeCumulativeSum(stock_Prices_Array_List);
        System.out.println("Cumulative Sum of Stock Prices (ArrayList) = " + cumulativeSumArrayList);
    }
}
