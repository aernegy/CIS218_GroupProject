package schoolrecords.menus;

import static schoolrecords.MenuLogic.getFaculty;
import static schoolrecords.MenuLogic.getStaff;
import static schoolrecords.MenuLogic.getStudents;
import static schoolrecords.misc.Utility.centerPrint;
import static schoolrecords.misc.Utility.loadMenuText;

import java.util.ArrayList;
import java.util.HashMap;
import schoolrecords.MenuLogic;

public class MainMenu implements Menu {
  private final ArrayList<String> mainMenu = loadMenuText("mainMenu.txt");
  /* Refer to Add for an explanation */
  private static final HashMap<String, Runnable> menuOptions = new HashMap<>();

  public MainMenu(MenuControl menuControl) {
    menuOptions.put("1", () -> menuControl.setMenu(new View(getStudents(), menuControl, false)));
    menuOptions.put("2", () -> menuControl.setMenu(new View(getFaculty(), menuControl, false)));
    menuOptions.put("3", () -> menuControl.setMenu(new View(getStaff(), menuControl, false)));
    menuOptions.put("4", () -> menuControl.setMenu(new Search(getStudents(), getFaculty(), getStaff(), menuControl)));
    menuOptions.put("5", () -> menuControl.setMenu(new Add(getStudents(), getFaculty(), getStaff(), menuControl)));
    menuOptions.put("Q", MenuLogic::quit);
  }


  @Override
  public void print() {
    /* To help generalize the function. I.e. if more options are added in the future, the following
     * code should not require changes */
    int optionNo = 1;

    /* To assist printing options into two columns */
    boolean column1 = true;

    System.out.println("\n==================================================");
    centerPrint(">> SCHOOL RECORDS <<", 50);
    System.out.println("==================================================");

    for (String line : this.mainMenu) {
      String row = "[" + optionNo + "] " + line;
      /* Print each row into a column of a minimum of 30 characters,
       * left justified and upper case. */
      System.out.printf("%-30S", row);
      if (!column1) {
        System.out.println();
      }

      optionNo++;

      column1 = !column1;
    }

    if (!column1) {
      System.out.println();
    }

    System.out.println("\n[Q] LOG OUT\n");
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
