import java.util.ArrayList;

// Static Helper functions
public class HelperFunctions {


    // Splits X data into training and testing data
    public static ArrayList<ArrayList<ArrayList<Integer>>> trainTestSplitX(int[][] X) {
        // Defining length of training split at 80%
        int trainLength = (int) (X.length * 0.8);

        // Defining length of testing split at 20%
        int testLength = (int) (X.length * 0.8);

        // Defining 2d array of training data split [[1,2,3,4], [6,7,8,9], ...] etc.
        ArrayList<ArrayList<Integer>> trainSplit = new ArrayList<>();



        // Training data is at trainTest[0] and testing is at trainTest[1]
        return trainTest;
    }

    // Splits Y data into training and testing data

}
