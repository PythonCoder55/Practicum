import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PersonReader {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooser.setFileFilter(filter);

        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            displayFile(String.valueOf(selectedFile));
        } else {
            System.out.println("File selection canceled.");
        }
    }

    private static void displayFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Display header
            System.out.println("ID#           Firstname     Lastname       Title    YOB");
            System.out.println("========================================================");

            // Display records
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                String formattedLine = String.format("%-14s%-14s%-15s%-9s%-4s",
                        parts[0], parts[1], parts[2], parts[3], parts[4]);
                System.out.println(formattedLine);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
