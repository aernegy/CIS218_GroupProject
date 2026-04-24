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
  private static final HashMap<String, Runnable> menuOptions = new HashMap<>();

  public MainMenu(MenuControl menuControl) {
    menuOptions.put("1", () -> menuControl.setMenu(new View(getStudents(), menuControl, true)));
    menuOptions.put("2", () -> menuControl.setMenu(new View(getFaculty(), menuControl, true)));
    menuOptions.put("3", () -> menuControl.setMenu(new View(getStaff(), menuControl, true)));
    menuOptions.put("4", () -> menuControl.setMenu(new Search(getStudents(), getFaculty(), getStaff(), menuControl)));
    menuOptions.put("5", () -> menuControl.setMenu(new Add(getStudents(), getFaculty(), getStaff(), menuControl)));
    menuOptions.put("Q", MenuLogic::quit);
  }


  @Override
  public void print() {
    int optionNo = 1;
    boolean column1 = true;

    System.out.println("\n==================================================");
    centerPrint(">> SCHOOL RECORDS <<", 50);
    System.out.println("\n==================================================");

    for (String line : this.mainMenu) {
      String row = "[" + optionNo + "] " + line;

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
