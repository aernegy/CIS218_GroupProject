package schoolrecords.menus;

import static schoolrecords.misc.Utility.centerPrint;
import static schoolrecords.misc.Utility.columnCheck;
import static schoolrecords.misc.Utility.sleep;

import java.util.ArrayList;
import schoolrecords.records.Record;
import schoolrecords.records.Records;

public class UpdateDialog implements Menu {
  private final MenuControl menuControl;
  private final ArrayList<String> columns;
  private final Records records;
  private final schoolrecords.records.Record record;
  private final int index;

  UpdateDialog(Records records, ArrayList<String> columns, Record record, int index, MenuControl menuControl) {
    this.columns = columns;
    this.records = records;
    this.record = record;
    this.index = index;
    this.menuControl = menuControl;
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

    System.out.println("\n==================================================");
    centerPrint("UPDATED SUCCESSFULLY!", 50);
    System.out.println("\n==================================================");

    sleep(2000);
    menuControl.setMenu(new Update(records, columns, record, menuControl));
  }
}