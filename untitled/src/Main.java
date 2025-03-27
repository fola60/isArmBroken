import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FileProcessor fileProcessor = new FileProcessor("broken_arm_data.csv");
        ArrayList<ArrayList<String>> lines = fileProcessor.getFileString();
        lines.remove(0);
        ArrayList<ArrayList<Integer>> numLines = HelperFunctions.stringToBinaryData(lines, 4);

        BayesClassifier bayesClassifier = new BayesClassifier(numLines, 4);
        bayesClassifier.fit();
        int key = 1;
        System.out.println(bayesClassifier.probabilityMap.get(key));
        System.out.println(bayesClassifier.predict(key));
        System.out.println("Accuracy: " + bayesClassifier.test() + "%.");
    }
}