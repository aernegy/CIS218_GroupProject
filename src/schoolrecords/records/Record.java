package schoolrecords.records;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Record {
  private final String recordsName;
  private final ArrayList<String> columns;
  private final LinkedHashMap<String, String> record = new LinkedHashMap<>();

  public Record(String recordsName, ArrayList<String> recordValues, ArrayList<String> columns) {
    this.recordsName = recordsName;
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

  public ArrayList<String> getColumns() {
    return columns;
  }

  public ArrayList<String> getSearch() {
    return new ArrayList<>(List.of(recordsName, get(0), get(1), get(2)));
  }


  public void set(String column, String value) {
    record.put(column, value);
  }

  public void set(int index, String value) {
    record.put(new ArrayList<>(record.keySet()).get(index), value);
  }
}
