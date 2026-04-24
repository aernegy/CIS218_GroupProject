package schoolrecords.menus;

public interface Menu {
  void print();

  boolean checkUserInput(String input);

  void runUserInput(String input);
}