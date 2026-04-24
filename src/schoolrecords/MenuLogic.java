package schoolrecords;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import schoolrecords.menus.MainMenu;
import schoolrecords.menus.Menu;
import schoolrecords.menus.MenuControl;
import schoolrecords.records.Records;

public class MenuLogic implements MenuControl {
  private Menu menu;
  private static boolean quit = false;
  private static Records students;
  private static Records faculty;
  private static Records staff;

  public MenuLogic(Records students, Records faculty, Records staff, String errorMessage) {
    MenuLogic.students = students;
    MenuLogic.faculty = faculty;
    MenuLogic.staff = staff;
    menu = new MainMenu(this);
    boolean error = false;

    try (BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {
      while (true) {
        if (quit) {
          students.save();
          faculty.save();
          staff.save();
          break;
        }

        menu.print();

        if (error) {
          System.out.printf("%S", "\n" + errorMessage + "\n");
          error = false;
        }

        String input = userInput.readLine();

        if (menu.checkUserInput(input.toUpperCase())) {
          menu.runUserInput(input);
        } else {
          error = true;
        }
      }

    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }


  @Override
  public void setMenu(Menu newMenu) {
    this.menu = newMenu;
  }


  static public Records getStudents() {
    return students;
  }

  static public Records getFaculty() {
    return faculty;
  }

  static public Records getStaff() {
    return staff;
  }


  static public void quit() {
    quit = true;
  }
}