package SchoolRecords.Menus;

import SchoolRecords.Records.Records;
import java.util.HashMap;

public class View implements Menu {
    static final HashMap<String, Runnable> menuOptions = new HashMap<>();
    Records records;


    View(Records records, MenuControl menuControl) {
        this.records = records;

        for (int i = 0; i < records.size();) {
            int index = i;
            menuOptions.put(Integer.toString(++i),
                    () -> menuControl.setMenu(new SchoolRecords.Menus.Details(records, records.getIndex(index).getColumns(), records.getIndex(index), menuControl)));
        }
        menuOptions.put("A", SchoolRecords.Menus.Add::new);
        menuOptions.put("S", () -> menuControl.setMenu(new SchoolRecords.Menus.Search(records, menuControl)));
        menuOptions.put("Q", () -> menuControl.setMenu(new SchoolRecords.Menus.MainMenu(menuControl)));
    }


    @Override
    public void print() {
        boolean column1 = true;

        System.out.println("\n--------------------");

        if (records.size() == 0) {
            System.out.println("\nNo records found");
        }

        for (int i = 0; i < records.size(); i++) {
            String row = (i + 1) + " - " + records.getIndex(i).get("FULLNAME");

            System.out.printf("%-50s", row);
            if (!column1) {
                System.out.println();
            }

            column1 = !column1;
        }

        if (!column1) {
            System.out.println();
        }

        System.out.println("\nA - Add\nS - Search\nQ - Return to main menu\n");
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
