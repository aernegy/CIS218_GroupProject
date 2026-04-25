package schoolrecords.records;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/* Represents a single individual in a Records. */
public class Record {
  private final String recordsName;
  private final ArrayList<String> columns;
  /* Similar to HashMap (refer to Add). In addition to being a HashMap, guarantees that keySet()
   * and valueSet() have deterministic order defined by order of insertion, enabling deterministic
   * iteration. */
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

  /* Get values in columns used for Searching */
  public ArrayList<String> getSearch() {
    return new ArrayList<>(List.of(recordsName, get(0), get(1), get(2)));
  }


  /* Redundant function in case values are changed by calling the key */
  public void set(String column, String value) {
    record.put(column, value);
  }

  public void set(int index, String value) {
    record.put(new ArrayList<>(record.keySet()).get(index), value);
  }
}
