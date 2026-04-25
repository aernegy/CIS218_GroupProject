package schoolrecords.menus;

import static schoolrecords.misc.Utility.centerPrint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import schoolrecords.records.Records;

public class Add implements Menu {
  private final ArrayList<Records> records;

  /* AI Inspired: */
  /* A data structure, where values are accessed by passing keys, rather than indexes. Runnable
   * is an interface that allows functions to be stored in the HashMap and, by calling run(),
   * execute the functions when called. */
  private final HashMap<String, Runnable> menuOptions = new HashMap<>();

  Add(Records students, Records faculty, Records staff, MenuControl menuControl) {
    /* For the user to select what type of Record to add */
    this.records = new ArrayList<>(List.of(students, faculty, staff));

    for (int i = 0; i < records.size(); ) {
      int index = i;
      menuOptions.put(Integer.toString(++i), () ->
          menuControl.setMenu(new AddDialog(records.get(index), menuControl))
      );
    }
  }


  @Override
  public void print() {
    System.out.println("\n==================================================");
    centerPrint("SELECT TYPE OF RECORD", 50);
    System.out.println("==================================================");

    for (int i = 0; i < records.size(); i++) {
      System.out.printf("[" + (i + 1) + "] " + records.get(i).getRecordsName() + "\n");
    }

    System.out.println("\n==================================================");
  }


  @Override
  public boolean checkUserInput(String input) {
    return menuOptions.containsKey(input);
  }


  @Override
  public void runUserInput(String input) {
    menuOptions.get(input).run();
  }
}