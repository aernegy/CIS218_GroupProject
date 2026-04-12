package SchoolRecords;

import java.io.*;
import java.util.ArrayList;
import SchoolRecords.Menus.MainMenu;
import SchoolRecords.Menus.Menu;
import SchoolRecords.Menus.MenuControl;

import static SchoolRecords.Misc.Utility.saveRecords;


public class MenuLogic implements MenuControl {
    Menu menu;
    static boolean quit = false;
    public static ArrayList<ArrayList<String>> students;
    public static ArrayList<ArrayList<String>> faculty;
    public static ArrayList<ArrayList<String>> staff;


    public MenuLogic(
            ArrayList<ArrayList<String>> students,
            ArrayList<ArrayList<String>> faculty,
            ArrayList<ArrayList<String>> staff,
            String errorMessage
    ) {
        MenuLogic.students = students;
        MenuLogic.faculty = faculty;
        MenuLogic.staff = staff;
        menu = new MainMenu(this);
        boolean error = false;

        try (BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                if (quit) {
                    saveRecords(students, "studentRecords.txt");
                    saveRecords(faculty, "facultyRecords.txt");
                    saveRecords(staff, "staffRecords.txt");
                    break;
                }
                System.out.println(System.getProperty("user.dir"));
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
}