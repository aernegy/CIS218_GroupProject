package SchoolRecords.Menus;

import SchoolRecords.Records.Records;
import SchoolRecords.Records.Record;
import java.util.ArrayList;
import java.util.HashMap;


public class Details implements SchoolRecords.Menus.Menu {
    static final HashMap<String, Runnable> menuOptions = new HashMap<>();
    Records records;
    ArrayList<String> columns;
    Record record;


    Details(Records records, ArrayList<String> columns, Record record, MenuControl menuControl) {
        this.records = records;
        this.columns = columns;
        this.record = record;

        menuOptions.put("U", () -> menuControl.setMenu(new Update(records, columns, record, menuControl)));
        menuOptions.put("D", () -> menuControl.setMenu(new Delete(records, columns, record, menuControl)));
        menuOptions.put("Q", () -> menuControl.setMenu(new View(records, menuControl)));
    }


    @Override
    public void print() {
        System.out.println("\n==================================================");
        System.out.printf("%S", "TYPE: " + records.getRecordsName() + "\n");

        for (int i = 0; i < this.columns.size(); i++) {
            System.out.printf("%S",this.columns.get(i) + ": " + record.get(i) + "\n");
        }

        System.out.println("\n[U] UPDATE DETAILS \n[D] DELETE RECORD\n[Q] RETURN");
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