package schoolrecords.menus;

public interface Menu {
  /* To enable MenuLogic to run the same behavior between all menus */

  void print();

  boolean checkUserInput(String input);

  void runUserInput(String input);
}