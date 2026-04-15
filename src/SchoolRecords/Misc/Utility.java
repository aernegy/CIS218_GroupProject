package SchoolRecords.Misc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Utility {
    public static ArrayList<String> loadMenuText(String fileName) {
        ArrayList<String> result = new ArrayList<>();
        String line;

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

            while ((line = fileReader.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return result;
    }


    public static ArrayList<ArrayList<String>> loadRecords(String fileName) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        String line;

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

            while ((line = fileReader.readLine()) != null) {
                result.add(new ArrayList<>(Arrays.asList(line.split("\\s*,\\s*"))));
            }

            fileReader.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return result;
    }


    public static void saveRecords(ArrayList<ArrayList<String>> records, String fileName) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName));

            for (ArrayList<String> record : records) {
                fileWriter.write(String.join(",", record));
                fileWriter.newLine();
            }

            fileWriter.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
