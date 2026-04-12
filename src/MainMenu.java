import java.util.ArrayList;
import java.util.HashMap;
import static Misc.Utility.loadMenuText;

public class MainMenu implements Menu {
    ArrayList<String> mainMenu = loadMenuText("mainMenu.txt");
    static final HashMap<String, Runnable> menuOptions = new HashMap<>();

    MainMenu(MenuControl menuControl) {
        menuOptions.put("1", () -> menuControl.setMenu(new View(Main.students, menuControl)));
        menuOptions.put("2", () -> menuControl.setMenu(new View(Main.faculty, menuControl)));
        menuOptions.put("3", () -> menuControl.setMenu(new View(Main.staff, menuControl)));
        menuOptions.put("Q", Main::quit);
    }


    public boolean checkUserInput(String input) {
        return menuOptions.containsKey(input);
    }


    public void runUserInput(String input) {
        menuOptions.get(input).run();
    }

    public void print() {
        int optionNo = 1;
        boolean column1 = true;

        System.out.println("--------------------\nSCHOOL RECORDS\n");

        for (String line : this.mainMenu) {
            String row = optionNo + " - " + line;

            if (column1) {
                System.out.printf("%-50s", row);

            } else {
                System.out.printf("%-50s", row);
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


}
