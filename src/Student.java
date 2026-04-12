import java.util.ArrayList;
import java.util.HashMap;

public class Student implements Menu {
    static final HashMap<String, Runnable> menuOptions = new HashMap<>();
    ArrayList<String> record;

    Student(ArrayList<ArrayList<String>> records, int index, MenuControl menuControl) {
        menuOptions.put("U", Update::new);
        menuOptions.put("D", Delete::new);
        menuOptions.put("Q", () -> menuControl.setMenu(new View(records, menuControl)));

        this.record = records.get(index);
    }


    public void print() {
        System.out.println("--------------------");

        System.out.println("NAME: " + record.getFirst());
        System.out.println("ID: " + record.get(1));
        System.out.println("MAJOR: " + record.get(2));
        System.out.println("GPA: " + record.getLast());

        System.out.println("\nU - Update details\nD - Delete record\nQ - Return");
    }

    public boolean checkUserInput(String input) {
        return menuOptions.containsKey(input);
    }

    public void runUserInput(String input) {
        menuOptions.get(input).run();
    }
}