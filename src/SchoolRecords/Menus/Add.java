package SchoolRecords.Menus;

import SchoolRecords.Records.Records;
import SchoolRecords.Records.Record;
import static SchoolRecords.Misc.Utility.columnCheck;
import static SchoolRecords.Misc.Utility.sleep;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Add implements Menu {
    ArrayList<Records> records;
    MenuControl menuControl;
    HashMap<String, Runnable> menuOptions = new HashMap<>();


    Add(Records records, MenuControl menuControl) {
        this.menuControl = menuControl;
    }
    Add(Records students, Records faculty, Records staff, MenuControl menuControl) {
        this.records = new ArrayList<>(List.of(students, faculty, staff));
        this.menuControl = menuControl;

        for (int i = 0; i < records.size();) {
            int index = i;
            menuOptions.put(Integer.toString(++i), () -> menuControl.setMenu(new AddDialog(records.get(index), menuControl)));
        }
    }


    @Override
    public boolean checkUserInput(String input) {
        return menuOptions.containsKey(input);
    }


    @Override
    public void runUserInput(String input) {
        menuOptions.get(input).run();
    }


    @Override
    public void print() {
        System.out.println("\n==================================================");
        System.out.printf("%36S","SELECT TYPE OF RECORD");
        System.out.println("\n==================================================");

        for (int i = 0; i < records.size(); i++) {
            System.out.printf("[" + (i + 1) + "] " + records.get(i).getRecordsName() + "\n");
        }

        System.out.println("\n==================================================");
    }
}


class AddDialog implements Menu {
    Records records;
    MenuControl menuControl;
    int index = 0;
    ArrayList<String> recordValues = new ArrayList<>();
    ArrayList<String> columns;


    AddDialog(Records records, MenuControl menuControl) {
        this.records = records;
        this.menuControl = menuControl;
        this.columns = records.getColumns();
    }


    @Override
    public boolean checkUserInput(String input) {
        return columnCheck(input, columns, index);
    }


    @Override
    public void runUserInput(String input) {
        recordValues.add(input.toUpperCase());
        index++;

        if (index >= columns.size()) {
            records.add(new Record(records.getRecordsName(), recordValues, columns));

            System.out.println("\n==================================================");
            System.out.printf("%34S","ADDED SUCCESSFULLY");
            System.out.println("\n==================================================");

            records.save();
            sleep(2000);

            menuControl.setMenu(new View(records, menuControl));
        }
    }


    @Override
    public void print() {
        System.out.println("\n==================================================");

        System.out.printf("%S", "TYPE: " + records.getRecordsName() + "\n");
        for (int i = 0; i < index; i++) {
            System.out.printf("%S",columns.get(i) + ": " + recordValues.get(i) + "\n");
        }

        System.out.printf("%S", "Enter value for " + columns.get(index) + ":\n");
    }
}