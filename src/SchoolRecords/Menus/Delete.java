package SchoolRecords.Menus;

public class Delete implements Menu {
    Delete() {
        System.out.println("SchoolRecords.Menus.Delete");
    }


    @Override
    public boolean checkUserInput(String input) {
        return true;
    }


    @Override
    public void runUserInput(String input) {

    }


    @Override
    public void print() {

    }
}
