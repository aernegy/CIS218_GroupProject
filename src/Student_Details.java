//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.io.*;
//import static Misc.Utility.loadMenuText;
//
//public class Student_Details implements Menu {
//    boolean quit = false;
//
//    Student_Details(ArrayList<String> record, BufferedReader userInput) {
//        String errorMessage = loadMenuText("menuError.txt").getFirst();
//
//        HashMap<String, Runnable> menuOptions = new HashMap<>();
//        menuOptions.put("U", Update::new);
//        menuOptions.put("D", Delete::new);
//        menuOptions.put("Q", this::quit);
//
//        boolean error = false;
//
//        try {
//            while (true) {
//                if (quit) {
//                    break;
//                }
//
//                print(new ArrayList<>(List.of(record)));
//
//                if (error) {
//                    System.out.println(errorMessage);
//                    error = false;
//                }
//
//                String input = userInput.readLine().toUpperCase();
//
//                if (menuOptions.containsKey(input)) {
//                    menuOptions.get(input).run();
//                } else {
//                    error = true;
//                }
//
//            }
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
//
//
//    public void print(ArrayList<ArrayList<String>> temp) {
//        ArrayList<String> record = temp.getFirst();
//
//        System.out.println("--------------------");
//
//        System.out.println("NAME: " + record.getFirst());
//        System.out.println("ID: " + record.get(1));
//        System.out.println("MAJOR: " + record.get(2));
//        System.out.println("GPA: " + record.getLast());
//
//        System.out.println("\nU - Update details\nD - Delete record\nQ - Return");
//    }
//
//
//    public void quit() {
//        quit = true;
//    }
//}

public class Student_Details {
    Student_Details() {
        System.out.println("Student_details");
    }
}
