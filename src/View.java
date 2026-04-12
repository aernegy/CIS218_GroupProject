import java.util.ArrayList;
import java.util.HashMap;

public class View implements Menu {
    static final HashMap<String, Runnable> menuOptions = new HashMap<>();
    ArrayList<ArrayList<String>> records;
    ArrayList<String> columns;


    View(ArrayList<ArrayList<String>> records, MenuControl menuControl) {
        this.records = records;
        this.columns = records.getFirst();

        for (int i = 1; i < records.size(); i++) {
            int index = i;
            menuOptions.put(Integer.toString(i),
                    () -> menuControl.setMenu(new Details(records, records.getFirst(), records.get(index), menuControl)));
        }
        menuOptions.put("A", Add::new);
        menuOptions.put("Q", () -> menuControl.setMenu(new MainMenu(menuControl)));
    }


    @Override
    public void print() {
        int recordNo = 1;
        boolean column1 = true;

        System.out.println("\n--------------------");

        for (int i = 1; i < records.size(); i++) {
            String row = recordNo + " - " + records.get(i).get(2);

            if (column1) {
                System.out.printf("%-50s", row);
            } else {
                System.out.printf("%-50s", row);
                System.out.println();
            }

            recordNo += 1;

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
