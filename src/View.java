import java.util.ArrayList;
import java.util.HashMap;

public class View implements Menu {
    static final HashMap<String, Runnable> menuOptions = new HashMap<>();

    View(ArrayList<ArrayList<String>> records, MenuControl menuControl) {
        for (int i = 0; i < records.size();) {
            int index = i;
            menuOptions.put(Integer.toString(++i), () -> menuControl.setMenu(new Student(records, index, menuControl)));
        }
        menuOptions.put("A", Add::new);
        menuOptions.put("Q", () -> menuControl.setMenu(new MainMenu(menuControl)));
    }


    public void print() {
        int recordNo = 1;
        boolean column1 = true;

        System.out.println("--------------------");

        for (ArrayList<String> record : Main.students) {
            String row = recordNo + " - " + record.getFirst();

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


    public boolean checkUserInput(String input) {
        return menuOptions.containsKey(input);
    }

    public void runUserInput(String input) {
        menuOptions.get(input).run();
    }
}
