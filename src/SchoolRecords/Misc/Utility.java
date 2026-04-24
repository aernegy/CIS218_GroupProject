package SchoolRecords.Misc;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Utility {
    public static ArrayList<String> loadMenuText(String fileName) {
        ArrayList<String> result = new ArrayList<>();
        String line;

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

            while ((line = fileReader.readLine()) != null) {
                result.add(line);
            }
        }
        catch (IOException e) {
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
                System.out.println(e.getMessage());
                switch (fileName) {
                    case "studentRecords.txt":
                        result.add(new ArrayList<>(List.of("STUDENT")));
                        result.add(new ArrayList<>(List.of("ID,FULLNAME,MAJOR,GPA")));
                    case "facultyRecords.txt":
                        result.add(new ArrayList<>(List.of("FACULTY")));
                        result.add(new ArrayList<>(List.of("ID,FULLNAME,COURSES,SALARY")));
                    case "staffRecords.txt":
                        result.add(new ArrayList<>(List.of("STAFF")));
                        result.add(new ArrayList<>(List.of("ID,FULLNAME,DEPARTMENT,SALARY")));
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
                fileWriter.write(String.join(",", record));
                fileWriter.newLine();
            }

            fileWriter.close();

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public static boolean columnCheck(String input, ArrayList<String> columns, int index) {
        ArrayList<String> intCheck = new ArrayList<>(List.of("ID", "SALARY"));

        if (intCheck.contains(columns.get(index))) {
            return input.matches("-?\\d+");
        } else if (columns.get(index).equals("GPA")) {
            try {
                return (0 <= Double.parseDouble(input) && Double.parseDouble(input) <= 4.0);
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return true;
        }
    }


    public static void sleep(long milis) {
        try {
            Thread.sleep(milis);
        }
        catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
