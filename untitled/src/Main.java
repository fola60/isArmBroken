import java.util.ArrayList;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        FileProcessor fileProcessor = new FileProcessor("broken_arm_data.csv");
        ArrayList<ArrayList<String>> lines = fileProcessor.getFileString();
        lines.remove(0);
        ArrayList<ArrayList<Integer>> encodedData = HelperFunctions.stringToBinaryData(lines, 4);

        BayesClassifier bayesClassifier = new BayesClassifier(encodedData, 4);
        bayesClassifier.fit();

        System.out.println(bayesClassifier.predict(10));

        for (Map.Entry<Integer, ArrayList<Integer>> entry : bayesClassifier.probabilityMap.entrySet()) {
            Integer key = entry.getKey();
            ArrayList<Integer> value = entry.getValue();
            System.out.println(HelperFunctions.binaryToString(key) + ",Arm broken count: " + value.get(0) + " ,Arm not broken count: " + value.get(1));
            System.out.println("");
        }
        GUI gui = new GUI(encodedData);

    }
}