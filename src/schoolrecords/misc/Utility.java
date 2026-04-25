package schoolrecords.misc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utility {

  /* To reduce repetitive center printing in code */
  public static void centerPrint(String text, int length) {
    int padding = (length - text.length()) / 2;
    System.out.printf("%" + padding + "S%S%" + padding + "S%n", "", text, "");
  }

  /* Checks for legal user values when entering values into certain columns, whether adding a
   * new Record into a Records or updating a Record */
  public static boolean columnCheck(String input, ArrayList<String> columns, int index) {
    ArrayList<String> intCheck = new ArrayList<>(List.of("ID", "SALARY"));

    /* If the input is only whitespace. */
    if (input.matches("\\s*")) {
      return false;
    } else if (intCheck.contains(columns.get(index))) {
      /* Returns true if the input is one or more digits. Allows for negative sign in front. */
      return input.matches("-?\\d+");
    } else if (columns.get(index).equals("GPA")) {
      try {
        /* Returns true if input is a double and is between 0 and 4. */
        return (0 <= Double.parseDouble(input) && Double.parseDouble(input) <= 4.0);
      } catch (NumberFormatException e) {
        return false;
      }

    } else {
      return true;
    }
  }

  public static ArrayList<String> loadMenuText(String fileName) {
    ArrayList<String> result = new ArrayList<>();
    String line;

    try {
      BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

      while ((line = fileReader.readLine()) != null) {
        result.add(line);
      }

      fileReader.close();

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

    } catch (FileNotFoundException e) {
      /* If file was not found, just create a dummy 'file' and give it to the program to work with.
       * A new file is then only written when saveRecords is called. */
      System.out.println(e.getMessage());
      switch (fileName) {
        case "studentRecords.txt":
          result.add(new ArrayList<>(List.of("STUDENT")));
          result.add(new ArrayList<>(List.of("ID,FULLNAME,MAJOR,GPA")));
          break;
        case "facultyRecords.txt":
          result.add(new ArrayList<>(List.of("FACULTY")));
          result.add(new ArrayList<>(List.of("ID,FULLNAME,COURSES,SALARY")));
          break;
        case "staffRecords.txt":
          result.add(new ArrayList<>(List.of("STAFF")));
          result.add(new ArrayList<>(List.of("ID,FULLNAME,DEPARTMENT,SALARY")));
          break;
      }
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return result;
  }

  public static void saveRecords(ArrayList<ArrayList<String>> records, String fileName) {
    try {
      BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName));

      for (ArrayList<String> record : records) {
        /* Delimit field values in a row by commas, converting them into upper case */
        fileWriter.write(String.join(",", record).toUpperCase());
        fileWriter.newLine();
      }

      fileWriter.close();

    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  /* Pauses the program momentarily */
  public static void sleep(long milis) {
    try {
      Thread.sleep(milis);
    } catch (InterruptedException e) {
      System.out.println(e.getMessage());
    }
  }
}
