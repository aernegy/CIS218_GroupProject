package SchoolRecords.Records;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import static SchoolRecords.Misc.Utility.loadRecords;
import static SchoolRecords.Misc.Utility.saveRecords;


public class Records {
    private final ArrayList<String> columns;
    private LinkedHashMap<String, Record> records = new LinkedHashMap<>();


    public Records(String fileName) {
        ArrayList<ArrayList<String>> load = loadRecords(fileName);
        columns = load.removeFirst();
        for (ArrayList<String> recordValues : load) {
            records.put(recordValues.getFirst(), new Record(recordValues, columns));
        }
    }


    public Record get(int id) {
        return records.get(Integer.toString(id));
    }
    public Record getIndex(int index) {
        return records.get(new ArrayList<>(records.keySet()).get(index));
    }

    public ArrayList<String> getColumns() {
        return columns;
    }


    void add(Record record) {
        records.put(record.get("ID"), record);
    }


    void delete(int id) {
        records.remove(Integer.toString(id));
    }


    public void save(String fileName) {
        ArrayList<ArrayList<String>> save = new ArrayList<>();
        for (Record record : records.values()) {
            save.add(record.getAll());
        }
        save.addFirst(columns);
        saveRecords(save, fileName);
    }

    public int size() {
        return records.size();
    }
}
