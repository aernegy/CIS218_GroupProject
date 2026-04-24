package schoolrecords.menus;

import static schoolrecords.misc.Utility.centerPrint;
import static schoolrecords.misc.Utility.columnCheck;
import static schoolrecords.misc.Utility.sleep;

import java.util.ArrayList;
import schoolrecords.records.Record;
import schoolrecords.records.Records;

public class AddDialog implements Menu {
  private final Records records;
  private final MenuControl menuControl;
  private int index = 0;
  private final ArrayList<String> recordValues = new ArrayList<>();
  private final ArrayList<String> columns;

  AddDialog(Records records, MenuControl menuControl) {
    this.records = records;
    this.menuControl = menuControl;
    this.columns = records.getColumns();
  }


  @Override
  public void print() {
    System.out.println("\n==================================================");

    /* Explain */
    System.out.printf("%S", "TYPE: " + records.getRecordsName() + "\n");
    for (int i = 0; i < index; i++) {
      /* Explain */
      System.out.printf("%S", columns.get(i) + ": " + recordValues.get(i) + "\n");
    }

    System.out.printf("%S", "Enter value for " + columns.get(index) + ":\n");
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
      centerPrint("ADDED SUCCESSFULLY", 50);
      System.out.println("\n==================================================");

      records.save();
      sleep(2000);

      menuControl.setMenu(new View(records, menuControl, true));
    }
  }
}