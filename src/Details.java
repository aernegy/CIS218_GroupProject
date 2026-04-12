import java.util.ArrayList;
import java.util.HashMap;


public class Details implements Menu {
    static final HashMap<String, Runnable> menuOptions = new HashMap<>();
    ArrayList<ArrayList<String>> records;
    ArrayList<String> columns;
    ArrayList<String> record;


    Details(ArrayList<ArrayList<String>> records, ArrayList<String> columns, ArrayList<String> record, MenuControl menuControl) {
        this.records = records;
        this.columns = columns;
        this.record = record;

        menuOptions.put("U", () -> menuControl.setMenu(new Update(records, columns, record, menuControl)));
        menuOptions.put("D", Delete::new);
        menuOptions.put("Q", () -> menuControl.setMenu(new View(records, menuControl)));
    }


    @Override
    public void print() {
        System.out.println("\n--------------------");

        for (int i = 0; i < this.columns.size(); i++) {
            System.out.println(this.columns.get(i) + ": " + record.get(i));
        }

        System.out.println("\nU - Update details\nD - Delete record\nQ - Return");
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