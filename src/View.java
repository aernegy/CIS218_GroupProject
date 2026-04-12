import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;

public class View implements Menu {
    /* Use this class to load the list of students, faculty, or staff */

    static Boolean quit = false;

    View(ArrayList<ArrayList<String>> records, BufferedReader userInput) {
        String errorMessage = Main.loadMenuText("menuError.txt").getFirst();

        HashMap<String, Runnable> menuOptions = new HashMap<>();
        for (int i = 0; i < records.size();) {
            int index = i;
            menuOptions.put(Integer.toString(++i), () -> new Student_Details(records.get(index), userInput));
        }
        menuOptions.put("A", Add::new);
        menuOptions.put("Q", this::quit);

        boolean error = false;

        try {
            while (true) {
                if (quit) {
                    quit = false;
                    break;
                }

                print(records);

                if (error) {
                    System.out.println(errorMessage);
                    error = false;
                }

                String input = userInput.readLine().toUpperCase();

                if (menuOptions.containsKey(input)) {
                    menuOptions.get(input).run();
                } else {
                    error = true;
                }

            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    public void print(ArrayList<ArrayList<String>> records) {
        int recordNo = 1;
        boolean column1 = true;

        System.out.println("--------------------");

        for (ArrayList<String> record : records) {
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


    public void quit() {
        quit = true;
    }
}
