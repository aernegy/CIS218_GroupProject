import java.util.ArrayList;
import java.util.HashMap;

public class Details implements Menu {
    static final HashMap<String, Runnable> menuOptions = new HashMap<>();
    ArrayList<String> columns;
    ArrayList<String> record;

    Details(ArrayList<ArrayList<String>> records, int index, MenuControl menuControl) {
        menuOptions.put("U", Update::new);
        menuOptions.put("D", Delete::new);
        menuOptions.put("Q", () -> menuControl.setMenu(new View(records, menuControl)));

        this.columns = records.getFirst();
        this.record = records.get(index);
    }


    public void print() {
        System.out.println("--------------------");

        for (int i = 0; i < this.columns.size(); i++) {
            System.out.println(this.columns.get(i) + ": " + record.get(i));
        }

        System.out.println("\nU - Update details\nD - Delete record\nQ - Return");
    }

    public boolean checkUserInput(String input) {
        return menuOptions.containsKey(input);
    }

    public void runUserInput(String input) {
        menuOptions.get(input).run();
    }
}