package SchoolRecords;

import java.io.*;
import SchoolRecords.Menus.MainMenu;
import SchoolRecords.Menus.Menu;
import SchoolRecords.Menus.MenuControl;
import SchoolRecords.Records.Records;


public class MenuLogic implements MenuControl {
    Menu menu;
    static boolean quit = false;
    public static Records students;
    public static Records faculty;
    public static Records staff;


    public MenuLogic(Records students, Records faculty, Records staff, String errorMessage) {
        MenuLogic.students = students;
        MenuLogic.faculty = faculty;
        MenuLogic.staff = staff;
        menu = new MainMenu(this);
        boolean error = false;

        try (BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                if (quit) {
                    students.save("studentRecords.txt");
                    faculty.save("facultyRecords.txt");
                    staff.save("staffRecords.txt");
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


    @Override
    public void setMenu(Menu newMenu) {
        this.menu = newMenu;
    }


    static public void quit() {
        quit = true;
    }
}