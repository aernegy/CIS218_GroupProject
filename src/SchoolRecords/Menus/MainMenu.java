package SchoolRecords.Menus;

import SchoolRecords.MenuLogic;
import java.util.ArrayList;
import java.util.HashMap;
import static SchoolRecords.Misc.Utility.loadMenuText;

public class MainMenu implements Menu {
    ArrayList<String> mainMenu = loadMenuText("mainMenu.txt");
    static final HashMap<String, Runnable> menuOptions = new HashMap<>();

    public MainMenu(MenuControl menuControl) {
        menuOptions.put("1", () -> menuControl.setMenu(new View(MenuLogic.students, menuControl)));
        menuOptions.put("2", () -> menuControl.setMenu(new View(MenuLogic.faculty, menuControl)));
        menuOptions.put("3", () -> menuControl.setMenu(new View(MenuLogic.staff, menuControl)));
        menuOptions.put("Q", MenuLogic::quit);
    }


    @Override
    public void print() {
        int optionNo = 1;
        boolean column1 = true;

        System.out.println("\n--------------------\nSCHOOL RECORDS\n");

        for (String line : this.mainMenu) {
            String row = optionNo + " - " + line;

            System.out.printf("%-50s", row);
            if (!column1) {
                System.out.println();
            }

            optionNo++;

            column1 = !column1;
        }

        if (!column1) {
            System.out.println();
        }

        System.out.println("\nA - Add\nQ - Return to main menu\n");
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
