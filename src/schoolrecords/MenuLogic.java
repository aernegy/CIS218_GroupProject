package schoolrecords;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import schoolrecords.menus.MainMenu;
import schoolrecords.menus.Menu;
import schoolrecords.menus.MenuControl;
import schoolrecords.records.Records;

/* AI inspired: */
/* By making MenuLogic implement MenuControl, we can pass MenuLogic into menus. However, the
 * parameter of every menu that we pass MenuLogic into expects a MenuControl. This still works,
 * since MenuLogic is an instance of MenuControl. We do this in order that the menus that we pass
 * MenuLogic into can only access the features of MenuControl, i.e. setMenu, the only method in
 * MenuControl. */
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
      /* The 'heartbeat' of the program. Implements the following loop for user experience:
       * 1) The menu is printed, with an error message if necessary.
       * 2) The user submits an input
       * 3) The input is checked
       * 4) If valid, the input is run
       * 5) If not valid, nothing happens and an error message is printed next loop.
       * 6) Loop back to step 1.
       * Since every page in the program implements Menu, this enables the same loop to be used
       * throughout the program. */
      while (true) {
        /* If the user wishes to quit, interrupt the program loop and leave */
        if (quit) {
          students.save();
          faculty.save();
          staff.save();
          break;
        }

        /* Print the menu that is open */
        menu.print();

        /* If there is an error in the previous loop,
         * display error message before user inputs again */
        if (error) {
          System.out.printf("%S", "\n" + errorMessage + "\n");
          error = false;
        }

        String input = userInput.readLine();

        /* If the user's input is valid (rules differing between menus), run.
         * Otherwise, call an error next loop. */
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


  /* Enables menus to change the menu that is open */
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