import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileProcessor {
    private File file;
    private String fileName;
    private int FileLength;

    public FileProcessor(String filename) {
        setFileName(filename);
        setFile(filename);
    }

    // return a array of each line of the file
    public ArrayList<ArrayList<String>> getFileString(){
        int length = getFileLength(); // getting number of lines of file
        Scanner scanner = null;

        ArrayList<ArrayList<String>> fileString = new ArrayList<>();

        try {
            scanner = new Scanner(getFile());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        for (int i = 0; i < length; i++) {
            String line = scanner.nextLine();
            ArrayList<String> features = HelperFunctions.getArrayCSVLine(line); // Turning comma seperated line into array of strings
            fileString.add(features); // adding list of features to fileString
        }

        scanner.close();

        return fileString;
    }

    public File getFile() {
        return file;
    }

    public void setFile(String filename) {
        this.file = new File(filename);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    // Gets amount of lines in file
    private int getFileLength(){
        int length = 0;

        Scanner scanner = null;
        try {
            scanner = new Scanner(getFile());
            while (scanner.hasNext()) {
                scanner.nextLine();
                length += 1;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        scanner.close();
        return length;
    }

    // Returns a file writer with the inputed file
    private static FileWriter getFileWriter(File file) {
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file, true);
            return fileWriter;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    // Writes a line to the local file
    public void writeLine(String line) {
        FileWriter fw = getFileWriter(getFile());
        try {
            fw.write(line);
            closeFileWriter(fw);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Closes the filewriter
    public void closeFileWriter(FileWriter fw) {
        try {
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
