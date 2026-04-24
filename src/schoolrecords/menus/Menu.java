package schoolrecords.menus;

public interface Menu {
    boolean checkUserInput(String input);

    void runUserInput(String input);

    void print();
}