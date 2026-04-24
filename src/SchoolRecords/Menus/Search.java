package SchoolRecords.Menus;

import SchoolRecords.Records.Records;
import SchoolRecords.Records.Record;
import java.util.ArrayList;


public class Search implements Menu {
    MenuControl menuControl;
    ArrayList<Record> searchRecords = new ArrayList<>();
    Records results = new Records();


    Search(Records records, MenuControl menuControl) {
        this.searchRecords = records.getAll();
        this.menuControl = menuControl;
    }
    Search(Records students, Records faculty, Records staff, MenuControl menuControl) {
        this.searchRecords.addAll(students.getAll());
        this.searchRecords.addAll(faculty.getAll());
        this.searchRecords.addAll(staff.getAll());
        this.menuControl = menuControl;
    }


    @Override
    public void print() {
        System.out.println("\n==================================================");
        System.out.println("RECORDS SEARCH");
        System.out.println("Search for name, ID, department, courses, etc.: ");
        System.out.println("==================================================");
    }


    @Override
    public boolean checkUserInput(String input) {
        return true;
    }


    @Override
    public void runUserInput(String input) {
        for (Record record : searchRecords) {
            for (String field : record.getSearch()) {
                if (field.toUpperCase().contains(input.toUpperCase())) results.add(record);
            }
        }

        menuControl.setMenu(new View(results, menuControl));
    }
}
