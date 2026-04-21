package SchoolRecords.Menus;

import SchoolRecords.Records.Records;
import SchoolRecords.Records.Record;
import static SchoolRecords.Misc.Utility.sleep;
import java.util.ArrayList;


public class Delete implements Menu {
    Records records;
    ArrayList<String> columns;
    Record record;
    MenuControl menuControl;

    Delete(Records records, ArrayList<String> columns, Record record, MenuControl menuControl) {
        this.records = records;
        this.columns = columns;
        this.record = record;
        this.menuControl = menuControl;
    }


    @Override
    public boolean checkUserInput(String input) {
        return true;
    }


    @Override
    public void runUserInput(String input) {
        if (input.equalsIgnoreCase("Y")) {
            records.delete(record.get("ID"));
            System.out.println("\n==================================================");
            System.out.printf("%34S","DELETION SUCCESSFUL");
            System.out.println("\n==================================================");

            sleep(2000);

            records.save();
            menuControl.setMenu(new View(records, menuControl));

        } else {
            System.out.println("\n==================================================");
            System.out.printf("%34S","DELETION CANCELLED");
            System.out.println("\n==================================================");

            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            menuControl.setMenu(new Details(records, columns, record, menuControl));
        }


    }


    @Override
    public void print() {
        System.out.println("\n==================================================");
        System.out.printf("%42S",">> CONFIRM DELETE? ENTER 'Y' <<\n");
        System.out.println("==================================================");
    }
}
