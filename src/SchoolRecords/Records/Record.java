package SchoolRecords.Records;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Record {
    private LinkedHashMap<String, String> record = new LinkedHashMap<>();

    Record(ArrayList<String> recordValues, ArrayList<String> columns) {
        for (int i = 0; i < columns.size(); i++) {
            record.put(columns.get(i), recordValues.get(i));
        }
    }


    ArrayList<String> getAll() {
        return new ArrayList<>(record.values());
    }


    public String get(String column) {
        return record.get(column);
    }
    public String get(int index) {
        return record.get(new ArrayList<>(record.keySet()).get(index));
    }


    public void set(String column, String value) {
        record.put(column, value);
    }
    public void set(int index, String value) {
        record.put(new ArrayList<>(record.keySet()).get(index), value);
    }
}
