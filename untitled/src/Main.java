import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FileProcessor fileProcessor = new FileProcessor("broken_arm_data.csv");
        ArrayList<ArrayList<String>> lines = fileProcessor.getFileString();
        lines.remove(0);
        ArrayList<ArrayList<Integer>> encodedData = HelperFunctions.stringToBinaryData(lines, 4);

        System.out.println(HelperFunctions.testSplitX(encodedData));

        BayesClassifier bayesClassifier = new BayesClassifier(encodedData, 4);
        bayesClassifier.fit();
        System.out.println("Accuracy: " + bayesClassifier.test() + "%.");
    }
}