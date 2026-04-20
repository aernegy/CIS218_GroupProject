import SchoolRecords.MenuLogic;
import SchoolRecords.Records.Records;

import static SchoolRecords.Misc.Utility.loadMenuText;

public static void main(String[] args) {
    Records students = new Records("studentRecords.txt");
    Records faculty = new Records("facultyRecords.txt");
    Records staff = new Records("staffRecords.txt");
    String errorMessage = loadMenuText("menuError.txt").get(0);

    new MenuLogic(students, faculty, staff, errorMessage);

    // Upon program quit
    System.out.println("Program shutdown \nHasta la vista!");
}
