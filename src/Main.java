import SchoolRecords.MenuLogic;

import java.util.ArrayList;
import static SchoolRecords.Misc.Utility.loadMenuText;
import static SchoolRecords.Misc.Utility.loadRecords;

public class Main {
    static void main(String[] args) {
        ArrayList<ArrayList<String>> students = loadRecords("studentRecords.txt");
        ArrayList<ArrayList<String>> faculty = loadRecords("facultyRecords.txt");
        ArrayList<ArrayList<String>> staff = loadRecords("staffRecords.txt");
        String errorMessage = loadMenuText("menuError.txt").getFirst();

        new MenuLogic(students, faculty, staff, errorMessage);

//        System.out.println(System.getProperty("user.dir"));

        // Upon program quit
        System.out.println("Program shutdown \nHasta la vista!");
    }
}
