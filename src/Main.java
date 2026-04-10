import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
    private final BufferedReader userInput;
    static Boolean quit = false;

    Main() {
        String mainMenu = loadMenuText("mainMenu.txt");
        String errorMessage = loadMenuText("menuError.txt");
        ArrayList<ArrayList<String>> students = loadRecords("studentRecords.txt");
        ArrayList<ArrayList<String>> faculty = loadRecords("facultyRecords.txt");
        ArrayList<ArrayList<String>> staff = loadRecords("staffRecords.txt");

        userInput = new BufferedReader(new InputStreamReader(System.in));

        HashMap<String, Runnable> menuOptions = new HashMap<>();
        menuOptions.put("1", () -> new View(students, userInput));
        menuOptions.put("2", () -> new View(faculty, userInput));
        menuOptions.put("3", () -> new View(staff, userInput));
        menuOptions.put("Q", () -> quit());

        Boolean error = false;

        try {
            menuLoop:
            while (true) {
                if (quit) {
                    System.out.println("Program shutdown \nHasta la vista!");
                    break;
                }
                System.out.println(mainMenu);

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
            e.printStackTrace();
        }
    }


    static String loadMenuText(String fileName) {
        String result = "";
        String line;

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

            while ((line = fileReader.readLine()) != null) {
                result += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


    static ArrayList loadRecords(String fileName) {
        ArrayList<ArrayList> result = new ArrayList<>();
        String line;

        try {
            BufferedReader fileReader = new BufferedReader(new FileReader(fileName));

            while ((line = fileReader.readLine()) != null) {
                result.add(new ArrayList<>(Arrays.asList(line.split("\\s*,\\s*"))));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    static void quit() {
        quit = true;
    }


    static void main(String[] args) {
        new Main();
    }
}