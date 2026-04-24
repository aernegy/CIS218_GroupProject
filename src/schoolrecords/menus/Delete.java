package schoolrecords.menus;

import static schoolrecords.misc.Utility.sleep;
import static schoolrecords.misc.Utility.centerPrint;

import java.util.ArrayList;
import schoolrecords.records.Record;
import schoolrecords.records.Records;

public class Delete implements Menu {
  private final Records records;
  private final ArrayList<String> columns;
  private final Record record;
  private final MenuControl menuControl;

  Delete(Records records, ArrayList<String> columns, Record record, MenuControl menuControl) {
    this.records = records;
    this.columns = columns;
    this.record = record;
    this.menuControl = menuControl;
  }


  @Override
  public void print() {
    System.out.println("\n==================================================");
    centerPrint(">> CONFIRM DELETE? ENTER 'Y' <<\n", 50);
    System.out.println("==================================================");
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
      centerPrint("DELETION SUCCESSFUL", 50);
      System.out.println("\n==================================================");

      sleep(2000);

      records.save();
      menuControl.setMenu(new View(records, menuControl, true));

    } else {
      System.out.println("\n==================================================");
      centerPrint("DELETION CANCELLED", 50);
      System.out.println("\n==================================================");

      sleep(2000);

      menuControl.setMenu(new Details(records, columns, record, menuControl));
    }
  }
}
