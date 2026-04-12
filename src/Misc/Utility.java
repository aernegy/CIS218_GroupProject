package Misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
}
