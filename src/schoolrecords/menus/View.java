package schoolrecords.menus;

import static schoolrecords.MenuLogic.getFaculty;
import static schoolrecords.MenuLogic.getStaff;
import static schoolrecords.MenuLogic.getStudents;

import java.util.HashMap;
import schoolrecords.records.Records;

public class View implements Menu {
  /* Refer to Add for an explanation */
  private static final HashMap<String, Runnable> menuOptions = new HashMap<>();
  private final Records records;
  private boolean generalAdd;

  View(Records records, MenuControl menuControl, boolean generalAdd) {
    this.records = records;
    this.generalAdd = generalAdd;

    /* For every Record in records, put a menu option to open a Details for that Record */
    for (int i = 0; i < records.size(); ) {
      int index = i;
      menuOptions.put(
          Integer.toString(++i),
          () -> menuControl.setMenu(
              new Details(
                  records, records.getIndex(index).getColumns(), records.getIndex(index), menuControl
              )
          )
      );
    }
      /* If we know what Records the user wants to add into, add into that Records.
      *  If not, then open a menu to first ask the user what Records to add into. */
      if (generalAdd) {
        menuOptions.put("A", () -> menuControl.setMenu(new Add(getStudents(), getFaculty(), getStaff(), menuControl)));
      } else {
        menuOptions.put("A", () -> menuControl.setMenu(new AddDialog(records, menuControl)));
      }
    menuOptions.put("S", () -> menuControl.setMenu(new Search(records, menuControl)));
    menuOptions.put("Q", () -> menuControl.setMenu(new MainMenu(menuControl)));
  }


  @Override
  public void print() {
    boolean column1 = true;

    System.out.println("\n==================================================");
    if (records.getRecordsName() != null) {
      System.out.printf("%S", records.getRecordsName());
    } else {
      System.out.printf("%S", "SEARCH");
    }

    System.out.println("\n==================================================");

    if (records.size() == 0) {
      System.out.println("\nNo records found");
    }

    for (int i = 0; i < records.size(); i++) {
      String row = "[" + (i + 1) + "] " + records.getIndex(i).get("FULLNAME");

      System.out.printf("%-30S", row);
      if (!column1) {
        System.out.println();
      }

      column1 = !column1;
    }

    if (!column1) {
      System.out.println();
    }

    System.out.println("\n[A] ADD\n[S] SEARCH\n[Q] RETURN TO MAIN MENU\n");
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
