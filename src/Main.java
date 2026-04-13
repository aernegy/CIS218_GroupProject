import SchoolRecords.MenuLogic;
import SchoolRecords.Records.Records;

import static SchoolRecords.Misc.Utility.loadMenuText;

void main() {
    Records students = new Records("studentRecords.txt");
    Records faculty = new Records("facultyRecords.txt");
    Records staff = new Records("staffRecords.txt");
    String errorMessage = loadMenuText("menuError.txt").getFirst();

    new MenuLogic(students, faculty, staff, errorMessage);

    // Upon program quit
    IO.println("Program shutdown \nHasta la vista!");
}
