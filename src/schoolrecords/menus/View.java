package schoolrecords.menus;

import java.util.HashMap;

import schoolrecords.records.Records;

public class View implements Menu {
  private static final HashMap<String, Runnable> menuOptions = new HashMap<>();
  private final Records records;
  private final boolean enableAdd;

  View(Records records, MenuControl menuControl, boolean enableAdd) {
    this.records = records;
    this.enableAdd = enableAdd;

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
      if (enableAdd) {
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

    if (enableAdd) {
      System.out.println("\n[A] ADD");
    }
    System.out.println("[S] SEARCH\n[Q] RETURN TO MAIN MENU\n");
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
