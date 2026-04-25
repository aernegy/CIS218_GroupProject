import static schoolrecords.misc.Utility.loadMenuText;

import schoolrecords.MenuLogic;
import schoolrecords.records.Records;

public class Main {
  /* Starts the program. Divorced from MenuLogic to modularize code. */
  public static void main(String[] args) {
    final Records students = new Records("studentRecords.txt");
    final Records faculty = new Records("facultyRecords.txt");
    final Records staff = new Records("staffRecords.txt");
    final String errorMessage = loadMenuText("menuError.txt").get(0);

    new MenuLogic(students, faculty, staff, errorMessage);

    // Upon program quit
    System.out.printf("%S", "Program shutdown \nHasta la vista!");
  }
}