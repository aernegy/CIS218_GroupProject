package schoolrecords.menus;

import java.util.ArrayList;
import java.util.HashMap;
import schoolrecords.records.Record;
import schoolrecords.records.Records;

public class Details implements schoolrecords.menus.Menu {
  /* Refer to Add for an explanation. */
  private static final HashMap<String, Runnable> menuOptions = new HashMap<>();
  private final Records records;
  private final ArrayList<String> columns;
  private final Record record;

  Details(Records records, ArrayList<String> columns, Record record, MenuControl menuControl) {
    this.records = records;
    this.columns = columns;
    this.record = record;

    menuOptions.put("U", () ->
        menuControl.setMenu(new Update(records, columns, record, menuControl))
    );
    menuOptions.put("D", () -> menuControl.setMenu(new Delete(records, columns, record, menuControl)));
    menuOptions.put("Q", () -> menuControl.setMenu(new View(records, menuControl, false)));
  }


  @Override
  public void print() {
    System.out.println("\n==================================================");
    /* Print the string in the second parameter, but in upper case. */
    System.out.printf("%S", "TYPE: " + records.getRecordsName() + "\n");

    /* Print each field in a Record */
    for (int i = 0; i < this.columns.size(); i++) {
      /* Print the string in the second parameter, but in upper case. */
      System.out.printf("%S", this.columns.get(i) + ": " + record.get(i) + "\n");
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