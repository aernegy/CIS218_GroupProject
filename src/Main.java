import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static Misc.Utility.loadMenuText;

class Main implements MenuControl {
    Menu menu;
    static boolean quit = false;

    static ArrayList<ArrayList<String>> students = loadRecords("studentRecords.txt");
    static ArrayList<ArrayList<String>> faculty = loadRecords("facultyRecords.txt");
    static ArrayList<ArrayList<String>> staff = loadRecords("staffRecords.txt");
    String errorMessage = loadMenuText("menuError.txt").getFirst();


    Main() {
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        menu = new MainMenu(this);

        boolean error = false;

        try {
            while (true) {
                if (quit) {
                    System.out.println("Program shutdown \nHasta la vista!");
                    break;
                }

                menu.print();

                if (error) {
                    System.out.println(errorMessage);
                    error = false;
                }

                String input = userInput.readLine().toUpperCase();

                if (menu.checkUserInput(input)) {
                    menu.runUserInput(input);
                } else {
                    error = true;
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
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


    public void setMenu(Menu newMenu) {
        this.menu = newMenu;
    }


    static public void quit() {
        quit = true;
    }


    static void main(String[] args) {
        new Main();
    }
}