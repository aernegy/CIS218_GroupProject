package SchoolRecords.Menus;

import SchoolRecords.Records.Records;
import SchoolRecords.Records.Record;
import static SchoolRecords.Misc.Utility.columnCheck;
import static SchoolRecords.Misc.Utility.sleep;
import java.util.ArrayList;
import java.util.HashMap;


public class Update implements Menu {
    static final HashMap<String, Runnable> menuOptions = new HashMap<>();
    ArrayList<String> columns;
    Record record;


    Update(Records records, ArrayList<String> columns, Record record, MenuControl menuControl) {
        this.columns = columns;
        this.record = record;

        for (int i = 0; i < columns.size();) {
            int index = i;
            menuOptions.put(Integer.toString(++i),
                    () -> menuControl.setMenu(new UpdateDialog(records, columns, record, index, menuControl)));
        }
        menuOptions.put("Q", () -> menuControl.setMenu(new Details(records, this.columns, this.record, menuControl)));
    }


    @Override
    public void print() {
        System.out.println("\n==================================================");
        System.out.println("SELECT FIELD TO UPDATE\n");

        for (int i = 0; i < this.columns.size(); i++) {
            System.out.printf("%S", "[" + (i + 1) + "] " + this.columns.get(i) + ": " + record.get(i) + "\n");
        }

        System.out.println("\n[Q] RETURN");
        System.out.println("==================================================");
    }


    @Override
    public boolean checkUserInput(String input) {
        return menuOptions.containsKey(input);
    }


    @Override
    public void runUserInput(String input) {
        menuOptions.get(input.toUpperCase()).run();
    }
}


class UpdateDialog implements Menu {
    static final HashMap<String, Runnable> menuOptions = new HashMap<>();
    ArrayList<String> columns;
    Record record;
    int index;


    UpdateDialog(Records records, ArrayList<String> columns, Record record, int index, MenuControl menuControl) {
        this.columns = columns;
        this.record = record;
        this.index = index;

        menuOptions.put("Q", () -> menuControl.setMenu(new Update(records, columns, record, menuControl)));
    }


    @Override
    public void print() {
        System.out.println("==================================================");
        System.out.printf("%S", "Initial value of " + columns.get(this.index) + ": " + record.get(this.index) + "\n");
        System.out.printf("%S", "Enter new value: ");
    }


    @Override
    public boolean checkUserInput(String input) {
        return columnCheck(input, columns, index);
    }


    @Override
    public void runUserInput(String input) {
        record.set(index, input);
        System.out.println("\nUPDATED SUCCESSFULLY!");

        sleep(2000);
        menuOptions.get("Q").run();
    }
}