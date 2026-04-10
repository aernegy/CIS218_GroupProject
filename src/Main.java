import java.io.*;
import java.util.HashMap;

class Main {
    public Main() {
        HashMap<String, Runnable> menu_options = new HashMap<>();
        menu_options.put("1", View::new);
        Boolean error = false;

        try (BufferedReader user_input = new BufferedReader(new InputStreamReader(System.in))) {
            String main_menu = loadMenuText("main_menu.txt");
            String error_message = loadMenuText("menu_error.txt");

            while (true) {
                System.out.println(main_menu);

                if (error) {
                    System.out.println(error_message);
                    error = false;
                }

                String input = user_input.readLine().toUpperCase();

                if (menu_options.containsKey(input)) {
                    menu_options.get(input).run();
                } else if (input.equals("Q")) {
                    System.out.println("Program shutdown \nHasta la vista!");
                    break;
                } else {
                    error = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static String loadMenuText(String fileName) {
        String result = "";
        String line;

        try {
            BufferedReader file_reader = new BufferedReader(new FileReader(fileName));

            while ((line = file_reader.readLine()) != null) {
                result.concat(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    static void main(String[] args) {
        new Main();
    }
}