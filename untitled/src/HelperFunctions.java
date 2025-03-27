import java.util.ArrayList;
import java.util.HashMap;


// Static Helper functions
public class HelperFunctions {
    // Maps feature value to binary i.e "Yes": 1, "No": 0 ...
    public static HashMap<String, Integer> stringToBinary = new HashMap<>();
    private static final double trainSplitSize = 0.75; // Setting the size of training data to 60%
    private static final double testSplitSize = 0.25; // Setting the size of testing data to 40%

    // Populating values of stringToBinary HashMap
    static {
        // Mappings to 1
        stringToBinary.put("HighImpact", 1);
        stringToBinary.put("Extended", 1);
        stringToBinary.put("Hard", 1);
        stringToBinary.put("No", 1);
        stringToBinary.put("yes", 1);

        // Mappings to 0
        stringToBinary.put("LowImpact", 0);
        stringToBinary.put("Bent", 0);
        stringToBinary.put("Soft", 0);
        stringToBinary.put("Yes", 0);
        stringToBinary.put("no", 0);
    }

    // Splits data into training data X
    public static ArrayList<Integer> trainSplitX(ArrayList<ArrayList<Integer>> D) {
        // Defining length of training split at 80%
        int trainLength = (int) (D.size() * trainSplitSize);

        // Defining 2d array of training data split [1010, 1001, ...] etc.
        ArrayList<Integer> trainSplitX = new ArrayList<>();

        // Populating Training array with 80% of data
        for(int i = 0; i < trainLength; i++) {
            trainSplitX.add(D.get(i).get(0));
        }

        return trainSplitX;
    }

    // Splits data into training data Y
    public static ArrayList<Integer> trainSplitY(ArrayList<ArrayList<Integer>> D) {
        // Length of train split
        int trainLength = (int) (D.size() * trainSplitSize);

        // Defining 2d array of training data split [1, 0, 1,...] etc.
        ArrayList<Integer> trainSplitY = new ArrayList<>();

        // Populating Training array with 80% of data
        for(int i = 0; i < trainLength; i++) {
            trainSplitY.add(D.get(i).get(1));
        }

        return trainSplitY;
    }

    // Splits data into testing data X
    public static ArrayList<Integer> testSplitX(ArrayList<ArrayList<Integer>> D) {
        // Defining length of testing split and training split
        int testLength = (int) (D.size() * testSplitSize);
        int trainLength = (int) (D.size() * trainSplitSize);

        // Defining 2d array of testing data split [1010, 1001, ...] etc.
        ArrayList<Integer> testSplitX = new ArrayList<>();

        // Populating Training array with 80% of data
        for(int i = trainLength; i < testLength + trainLength; i++) {
            testSplitX.add(D.get(i).get(0));
        }

        return testSplitX;
    }

    // Splits data into testing data Y
    public static ArrayList<Integer> testSplitY(ArrayList<ArrayList<Integer>> D) {
        // Defining length of testing split and training split
        int testLength = (int) (D.size() * testSplitSize);
        int trainLength = (int) (D.size() * trainSplitSize);

        // Defining 2d array of testing data split [1, 0, 0, 1 ...] etc.
        ArrayList<Integer> testSplitY = new ArrayList<>();

        // Populating Training array with 80% of data
        for(int i = trainLength; i < testLength + trainLength; i++) {
            testSplitY.add(D.get(i).get(1));
        }

        return testSplitY;
    }


    public static ArrayList<ArrayList<Integer>> stringToBinaryData(ArrayList<ArrayList<String>> stringData, int features) {
        ArrayList<ArrayList<Integer>> convertedArray = new ArrayList<>(); // New Binary Array List of Data points

        // Converts Features and labels to binary and adds it to convertedArray
        for (ArrayList<String> list: stringData) {
            int key = 0;
            int factor = 1;

            // Converting String features into integer key
            for (int i = 0; i < features; i++) {
                // The binary number is taken from the map based on feature value
                // The binary number is then multiplied by the factor so each binary feature will be a digit for the key.
                // i.e. first feature mapped to 1 key will be : (0) += 1 * 1, (1) += 1 * 10, (11) += 0 * 100,  (11) += 1 * 1000 = 1011
                key += HelperFunctions.stringToBinary.get(list.get(i)) * factor;

                // multiplying factor by 10 to make binary number take next digit position
                factor *= 10;
            }

            ArrayList<Integer> X_y = new ArrayList<>(); // 2d array with the first value being binary features and 2nd value being binary label.
            X_y.add(key); // adds the binary representation of the string features to array
            X_y.add(HelperFunctions.stringToBinary.get(list.get(features))); // gets the binary mapping of yes or no and adds it to the array

            convertedArray.add(X_y); // Adding transformed data point to new array
        }

        return convertedArray;
    }

    // Converts a csv line to an array of string
    public static ArrayList<String> getArrayCSVLine(String line) {

        ArrayList<Integer> commaIdx = new ArrayList<>(); // holds index of comma's
        ArrayList<String> featureValues = new ArrayList<>(); // holds string feature values
        commaIdx.add(-1);

        // Getting the index of each comma in the string
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ',') {
                commaIdx.add(i);
            }
        }
        commaIdx.add(line.length());

        // using the comma index to get each word of the line.
        for (int i = 0; i < commaIdx.size() - 1; i++) {

            String feature = line.substring(commaIdx.get(i) + 1, commaIdx.get(i + 1)); // getting subsequence of line based on comma before and after word.
            featureValues.add(feature); // adding word to array of words
        }

        return featureValues;
    }
}
