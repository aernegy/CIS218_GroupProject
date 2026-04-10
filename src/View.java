import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.*;

public class View {
    /* Use this class to load the list of students, faculty, or staff */

    View(ArrayList records, BufferedReader userInput) {
        String errorMessage = Main.loadMenuText("menuError.txt");

        try {
            while (true) {
                listRecords(records);

                String input = userInput.readLine().toUpperCase();

                if (input.equals("Q")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    static void listRecords(ArrayList<ArrayList> records) {
        int recordNo = 1;
        Boolean column1 = true;

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

        System.out.println("\nA - Add new student\nQ - Return to main menu\n");
    }
}
