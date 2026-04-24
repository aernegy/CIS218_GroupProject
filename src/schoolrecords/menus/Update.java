package schoolrecords.menus;

import java.util.ArrayList;
import java.util.HashMap;

import schoolrecords.records.Record;
import schoolrecords.records.Records;

public class Update implements Menu {
  private static final HashMap<String, Runnable> menuOptions = new HashMap<>();
  private final ArrayList<String> columns;
  private final Record record;

  Update(Records records, ArrayList<String> columns, Record record, MenuControl menuControl) {
    this.columns = columns;
    this.record = record;

    for (int i = 0; i < columns.size(); ) {
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