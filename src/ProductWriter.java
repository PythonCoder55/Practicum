import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductWriter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> productsData = new ArrayList<>();

        do {
            String id = SafeInput.getRegExString(scanner, "Enter ID", "\\d{6}");
            String name = SafeInput.getNonZeroLenString(scanner, "Enter Name");
            String description = SafeInput.getNonZeroLenString(scanner, "Enter Description");
            double cost = SafeInput.getRangedDouble(scanner, "Enter Cost", 0.0, Double.MAX_VALUE);

            String productRecord = id + ", " + name + ", " + description + ", " + cost;
            productsData.add(productRecord);

        } while (SafeInput.getYNConfirm(scanner, "Do you want to enter another product? (Y/N)"));

        saveToFile(productsData, scanner);

        System.out.println("Data saved to file. Program terminated.");
    }

    private static void saveToFile(ArrayList<String> data, Scanner scanner) {
        String filename = SafeInput.getRegExString(scanner, "Enter the file name to save data", "[A-Za-z0-9]+\\.txt");

        try (PrintWriter writer = new PrintWriter(filename)) {
            for (String item : data) {
                writer.println(item);
            }
        } catch (IOException e) {
            System.out.println("Error saving list to file: " + e.getMessage());
        }
    }
}