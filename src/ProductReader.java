import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ProductReader {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            String selectedFilePath = fileChooser.getSelectedFile().getPath();
            displayProductFile(selectedFilePath);
        } else {
            System.out.println("File selection canceled. Program terminated.");
        }
    }

    private static void displayProductFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Display header
            System.out.println("ID#           Name           Description                    Cost");
            System.out.println("=================================================================");

            // Display records
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                String formattedLine = String.format("%-14s%-15s%-31s%-10s",
                        parts[0], parts[1], parts[2], parts[3]);
                System.out.println(formattedLine);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
