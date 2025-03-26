import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FileProcessor fileProcessor = new FileProcessor("broken_arm_data.csv");
        ArrayList<ArrayList<String>> lines = fileProcessor.getFileString();
        System.out.println(lines);
    }
}