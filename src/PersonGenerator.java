import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> personsData = new ArrayList<>();

        do {
            String id = SafeInput.getRegExString(scanner, "Enter ID", "\\d{6}");
            String firstName = SafeInput.getRegExString(scanner, "Enter First Name", "[A-Za-z]+");
            String lastName = SafeInput.getRegExString(scanner, "Enter Last Name", "[A-Za-z]+");
            String title = SafeInput.getNonZeroLenString(scanner, "Enter Title");
            int yearOfBirth = SafeInput.getInt(scanner, "Enter Year of Birth");

            String personRecord = id + ", " + firstName + ", " + lastName + ", " + title + ", " + yearOfBirth;
            personsData.add(personRecord);

        } while (SafeInput.getYNConfirm(scanner, "Do you want to enter another person? (Y/N)"));

        saveToFile(personsData, scanner);

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
