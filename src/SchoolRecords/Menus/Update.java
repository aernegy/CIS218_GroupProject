package SchoolRecords.Menus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Update implements Menu {
    static final HashMap<String, Runnable> menuOptions = new HashMap<>();
    ArrayList<String> columns;
    ArrayList<String> record;


    Update(ArrayList<ArrayList<String>> records, ArrayList<String> columns, ArrayList<String> record, MenuControl menuControl) {
        this.columns = columns;
        this.record = record;

        for (int i = 0; i < columns.size();) {
            int index = i;
            menuOptions.put(Integer.toString(++i),
                    () -> menuControl.setMenu(new UpdateDialog(records, columns, record, index, menuControl)));
        }
        menuOptions.put("Q", () -> menuControl.setMenu(new Details(records, this.columns, this.record, menuControl)));
    }


    @Override
    public void print() {
        System.out.println("\n--------------------");
        System.out.println("Select field to update\n");

        for (int i = 0; i < this.columns.size(); i++) {
            System.out.println((i + 1) + " - " + this.columns.get(i) + ": " + record.get(i));
        }

        System.out.println("\nQ - Return");
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


class UpdateDialog implements Menu {
    static final HashMap<String, Runnable> menuOptions = new HashMap<>();
    ArrayList<String> columns;
    ArrayList<String> record;
    int index;


    UpdateDialog(
            ArrayList<ArrayList<String>> records,
            ArrayList<String> columns,
            ArrayList<String> record,
            int index,
            MenuControl menuControl
    ) {
        this.columns = columns;
        this.record = record;
        this.index = index;

        menuOptions.put("Q", () -> menuControl.setMenu(new Update(records, columns, record, menuControl)));
    }


    @Override
    public void print() {
        System.out.println("\n--------------------");
        System.out.println("Initial value of " + columns.get(this.index) + ": " + record.get(this.index));
        System.out.print("Enter new value: ");
    }


    @Override
    public boolean checkUserInput(String input) {
        ArrayList<String> intCheck = new ArrayList<>(List.of("ID", "SALARY"));

        if (intCheck.contains(columns.get(index))) {
            return input.matches("-?\\d+");
        } else if (columns.get(index).equals("GPA")) {
            return (0 <= Double.parseDouble(input) && Double.parseDouble(input) <= 4.0);
        } else {
            return true;
        }
    }


    @Override
    public void runUserInput(String input) {
        record.set(index, input);
        System.out.println("\nUPDATED SUCCESSFULLY!");
        menuOptions.get("Q").run();
    }
}