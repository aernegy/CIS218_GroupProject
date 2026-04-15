package SchoolRecords.Records;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


public class Record {
    private final ArrayList<String> columns;
    private LinkedHashMap<String, String> record = new LinkedHashMap<>();


    Record(ArrayList<String> recordValues, ArrayList<String> columns) {
        this.columns = columns;
        for (int i = 0; i < columns.size(); i++) {
            record.put(this.columns.get(i), recordValues.get(i));
        }
    }


    public String get(String column) {
        return record.get(column);
    }
    public String get(int index) {
        return record.get(new ArrayList<>(record.keySet()).get(index));
    }


    ArrayList<String> getAll() {
        return new ArrayList<>(record.values());
    }


    public ArrayList<String> getSearch() {
        return new ArrayList<>(List.of(get(0), get(2), get(3)));
    }


    public ArrayList<String> getColumns() {
        return columns;
    }


    public void set(String column, String value) {
        record.put(column, value);
    }
    public void set(int index, String value) {
        record.put(new ArrayList<>(record.keySet()).get(index), value);
    }
}
