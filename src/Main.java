import java.io.*;
import java.util.ArrayList;

import static Misc.Utility.loadMenuText;
import static Misc.Utility.loadRecords;
import static Misc.Utility.saveRecords;


class Main implements MenuControl {
    Menu menu;
    static boolean quit = false;

    static ArrayList<ArrayList<String>> students = loadRecords("studentRecords.txt");
    static ArrayList<ArrayList<String>> faculty = loadRecords("facultyRecords.txt");
    static ArrayList<ArrayList<String>> staff = loadRecords("staffRecords.txt");
    String errorMessage = loadMenuText("menuError.txt").getFirst();


    Main() {
        menu = new MainMenu(this);
        boolean error = false;

        try (BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                if (quit) {
                    saveRecords(students, "studentRecords.txt");
                    saveRecords(faculty, "facultyRecords.txt");
                    saveRecords(staff, "staffRecords.txt");
                    System.out.println("Program shutdown \nHasta la vista!");
                    break;
                }

                menu.print();

                if (error) {
                    System.out.println(errorMessage);
                    error = false;
                }

                String input = userInput.readLine();

                if (menu.checkUserInput(input.toUpperCase())) {
                    menu.runUserInput(input);
                } else {
                    error = true;
                }
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
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