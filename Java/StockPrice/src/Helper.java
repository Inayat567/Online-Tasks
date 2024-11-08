import java.util.ArrayList;

//This is a helper class which contain all the calculation functions
public class Helper { 

    // calculateAveragePrice function takes array of type Float as input and return the average float number (price);
    public static float calculateAveragePrice(Float[] prices) { 
        float sum = 0;
        for (float price : prices) {
            sum += price;
        }
        return sum / prices.length;
    }

    // findMaximumPrice takes array of type Float as input and return the maximun float number (price);
    public static float findMaximumPrice(Float[] prices) {
        float maxPrice = 0;
        for (float price : prices) {
            if (price > maxPrice) {
                maxPrice = price;
            }
        }
        return maxPrice;
    }

    // countOccurrences function takes array of type Float and a target float number as input and return the number of occurance of that target number;
    public static int countOccurrences(Float[] prices, float targetPrice) {
        int num = 0;
        for (float price : prices) {
            if (price == targetPrice) {
                num++;
            }
        }
        return num;
    }

    // computeCumulativeSum function takes array of type Float as input and return the cummulative sum of given array;
    public static ArrayList<Float> computeCumulativeSum(ArrayList<Float> prices) {
        ArrayList<Float> cumSum = new ArrayList<>();
        float sum = 0;
        for (float price : prices) {
            sum += price;
            cumSum.add(sum);
        }
        return cumSum;
    }
}
