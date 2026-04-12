import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main implements Menu {
    private final BufferedReader userInput;
    static boolean quit = false;

    Main() {
        ArrayList<ArrayList<String>> mainMenu = new ArrayList<>(List.of(loadMenuText("mainMenu.txt")));
        String errorMessage = loadMenuText("menuError.txt").getFirst();
        ArrayList<ArrayList<String>> students = loadRecords("studentRecords.txt");
        ArrayList<ArrayList<String>> faculty = loadRecords("facultyRecords.txt");
        ArrayList<ArrayList<String>> staff = loadRecords("staffRecords.txt");

        userInput = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, Runnable> menuOptions = new HashMap<>();
        menuOptions.put("1", () -> new View(students, userInput));
        menuOptions.put("2", () -> new View(faculty, userInput));
        menuOptions.put("3", () -> new View(staff, userInput));
        menuOptions.put("Q", this::quit);

        boolean error = false;

        try {
            while (true) {
                if (quit) {
                    System.out.println("Program shutdown \nHasta la vista!");
                    break;
                }

                print(mainMenu);

                if (error) {
                    System.out.println(errorMessage);
                    error = false;
                }

                String input = userInput.readLine().toUpperCase();

                if (menuOptions.containsKey(input)) {
                    menuOptions.get(input).run();
                } else {
                    error = true;
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    static ArrayList<String> loadMenuText(String fileName) {
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


    static ArrayList<ArrayList<String>> loadRecords(String fileName) {
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        String line;

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

            while ((line = fileReader.readLine()) != null) {
                result.add(new ArrayList<>(Arrays.asList(line.split("\\s*,\\s*"))));
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return result;
    }


    public void print(ArrayList<ArrayList<String>> mainMenu) {
        int optionNo = 1;
        boolean column1 = true;

        System.out.println("--------------------\nSCHOOL RECORDS\n");

        for (String line : mainMenu.getFirst()) {
            String row = optionNo + " - " + line;

            if (column1) {
                System.out.printf("%-50s", row);

            } else {
                System.out.printf("%-50s", row);
                System.out.println();
            }

            optionNo++;

            column1 = !column1;
        }

        if (!column1) {
            System.out.println();
        }

        System.out.println("\nA - Add\nQ - Return to main menu\n");
    }


    public void quit() {
        quit = true;
    }


    static void main(String[] args) {
        new Main();
    }
}