import java.util.ArrayList;
import java.util.HashMap;

public class BayesClassifier {
    private ArrayList<ArrayList<Integer>> X;
    private ArrayList<ArrayList<Integer>> X_Train;
    private ArrayList<ArrayList<Integer>> Y_Train;
    private ArrayList<ArrayList<Integer>> X_Test;
    private ArrayList<ArrayList<Integer>> Y_Test;
    private int features;

    public HashMap<Integer, ArrayList<Integer>> probabilityMap; // Maps integer converted features to a count of yes an no e.g.: [1,2] = 1 yes and 2 no



    public BayesClassifier(ArrayList<ArrayList<Integer>> X) {
        probabilityMap = new HashMap<>();
    }

    // Getters and Setters
    public void setX(ArrayList<ArrayList<Integer>> x) {
        X = x;
    }

    public ArrayList<ArrayList<Integer>> getX() {
        return X;
    }

    public void setX_Train(ArrayList<ArrayList<Integer>> x_Train) {
        X_Train = x_Train;
    }

    public ArrayList<ArrayList<Integer>> getX_Train() {
        return X_Train;
    }

    public void setY_Train(ArrayList<ArrayList<Integer>> y_Train) {
        Y_Train = y_Train;
    }

    public ArrayList<ArrayList<Integer>> getY_Train() {
        return Y_Train;
    }

    public void setX_Test(ArrayList<ArrayList<Integer>> x_Test) {
        X_Test = x_Test;
    }

    public ArrayList<ArrayList<Integer>> getX_Test() {
        return X_Test;
    }

    public void setY_Test(ArrayList<ArrayList<Integer>> y_Test) {
        Y_Test = y_Test;
    }

    public ArrayList<ArrayList<Integer>> getY_Test() {
        return Y_Test;
    }

    public int getFeatures() {
        return features;
    }

    public void setFeatures(int features) {
        this.features = features;
    }

    // Trains model on training Data
    public void fit() {

    }

    // Predicts whether arm is broken or not returning an array with its first value being 1 for true and 0 for false and its second value being the accuracy
    public ArrayList<Integer> predict() {

        return new ArrayList<>();
    }
}
