import java.util.ArrayList;
import java.util.HashMap;

public class BayesClassifier {
    private ArrayList<ArrayList<Integer>> D;
    private ArrayList<Integer> X_Train;
    private ArrayList<Integer> Y_Train;
    private ArrayList<Integer> X_Test;
    private ArrayList<Integer> Y_Test;
    private int features;
    public HashMap<Integer, ArrayList<Integer>> probabilityMap; // Maps integer converted features to a count of yes an no e.g.: [1,2] = 1 yes and 2 no



    public BayesClassifier(ArrayList<ArrayList<Integer>> D, int features) {
        setD(D); // Setting Data
        setX_Train(HelperFunctions.trainSplitX(getD()));
        setY_Train(HelperFunctions.trainSplitY(getD()));
        setX_Test(HelperFunctions.testSplitX(getD()));
        setY_Test(HelperFunctions.testSplitY(getD()));
        setFeatures(features);
        probabilityMap = new HashMap<>();

    }

    // Getters and Setters
    public void setD(ArrayList<ArrayList<Integer>> d) {
        D = d;
    }

    public ArrayList<ArrayList<Integer>> getD() {
        return D;
    }

    public void setX_Train(ArrayList<Integer> x_Train) {
        X_Train = x_Train;
    }

    public ArrayList<Integer> getX_Train() {
        return X_Train;
    }

    public void setY_Train(ArrayList<Integer> y_Train) {
        Y_Train = y_Train;
    }

    public ArrayList<Integer> getY_Train() {
        return Y_Train;
    }

    public void setX_Test(ArrayList<Integer> x_Test) {
        X_Test = x_Test;
    }

    public ArrayList<Integer> getX_Test() {
        return X_Test;
    }

    public void setY_Test(ArrayList<Integer> y_Test) {
        Y_Test = y_Test;
    }

    public ArrayList<Integer> getY_Test() {
        return Y_Test;
    }

    public int getFeatures() {
        return features;
    }

    public void setFeatures(int features) {
        if (features > 0)
            this.features = features;
    }

    // Trains model on training Data
    public void fit() {

        for (int i = 0; i < getX_Train().size(); i++) {
            // Getting the binary digits from X data
            int key = getX_Train().get(i);

            if (probabilityMap.containsKey(key) ) {
                // Extracting current "yes" and "no" count from probability map
                ArrayList<Integer> oldLabel = new ArrayList<>();
                oldLabel = probabilityMap.get(key);

                // array to hold new "yes" and "no" count
                ArrayList<Integer> newLabel = new ArrayList<>();

                // Updating "yes" and "no" count in probability map
                if (Y_Train.get(i) == 1) {
                    newLabel.add(oldLabel.get(0) + 1); // Since Y value is 1 meaning "yes" we add 1 to the "yes" count and add it to new tuple
                    newLabel.add(oldLabel.get(1)); // Copy old "no" count back
                } else if (Y_Train.get(i) == 0){
                    newLabel.add(oldLabel.get(0)); // Copy old "yes" count back
                    newLabel.add(oldLabel.get(1) + 1); // Since Y value is 0 meaning "no" we add 1 to the "no" count and add it to new tuple
                }

                // Updating map value
                probabilityMap.put(key, newLabel);

            } else {
                ArrayList<Integer> newLabel = new ArrayList<>();
                if (Y_Train.get(i) == 1) {
                    newLabel.add(1); // Instantiate "yes" value with 1 since Y value is 1 meaning "yes"
                    newLabel.add(0); // Instantiate "no" value with 0
                } else if (Y_Train.get(i) == 0){
                    newLabel.add(0); // Copy old "yes" count back
                    newLabel.add(1); // Since Y value is 0 meaning "no" we instantiate it with the value 1
                }

                // Updating map value
                probabilityMap.put(key, newLabel);
            }
        }

    }

    // returns percentage accuracy of Model
    public int test() {

        int percentage = 0;
        int accuratePredictions = 0; // number of accurate predictions

        for(int i = 0; i < X_Test.size(); i++) {
            ArrayList<Integer> prediction = predict(X_Test.get(0));
            if (Y_Test.get(i) == prediction.get(0)) {
                accuratePredictions += 1;
            }
        }

        percentage = Math.round(((float) accuratePredictions / X_Test.size()) * 100);

        return percentage;
    }

    // Predicts whether arm is broken or not returning an array with its first value being 1 for true and 0 for false and its second value being the percentage chance
    public ArrayList<Integer> predict(int key) {
        // Getting the "yes" and "no" count from probability map
        int yesCnt = probabilityMap.get(key).get(0);
        int noCnt = probabilityMap.get(key).get(1);
        int total = yesCnt + noCnt;

        ArrayList<Integer> res = new ArrayList<>();
        if (yesCnt > noCnt) {
            res.add(1); // 1 represent "yes"
            int percent = Math.round(((float) yesCnt / total) * 100); // Calculating percent chance of "yes"
            res.add(percent); // Adding percentage chance
        } else if (noCnt > yesCnt) {
            res.add(0); // 0 represent "no"
            int percent = Math.round(((float) noCnt / total) * 100); // Calculating percent chance of "yes"
            res.add(percent); // Adding percentage chance
        } else {
            res.add(1); // 1 represent "yes", I chose yes when there is a tie since there is less "yes" values
            res.add(50); // if neither are bigger they are equal meaning it's a 50% chance.
        }

        return res;
    }
}
