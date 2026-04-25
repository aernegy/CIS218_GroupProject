package schoolrecords.records;

import static schoolrecords.misc.Utility.loadRecords;
import static schoolrecords.misc.Utility.saveRecords;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/* Represents a collection of individuals of the same type. */
public class Records {
  private String recordsName;
  ArrayList<String> columns = new ArrayList<>();
  /* Refer to Record for explanation */
  private final LinkedHashMap<String, Record> records = new LinkedHashMap<>();
  private String fileName;

  public Records(String fileName) {
    ArrayList<ArrayList<String>> load = loadRecords(fileName);
    this.recordsName = load.remove(0).remove(0);
    this.columns = load.remove(0);
    this.fileName = fileName;

    for (ArrayList<String> recordValues : load) {
      records.put(recordValues.get(0), new Record(recordsName, recordValues, columns));
    }

  }

  public Records() {
  }


  public void add(Record record) {
    records.put(record.get("ID"), record);
  }


  public void delete(String id) {
    records.remove(id);
  }


  public Record get(int id) {
    return records.get(Integer.toString(id));
  }

  public ArrayList<Record> getAll() {
    return new ArrayList<>(records.values());
  }

  public ArrayList<String> getColumns() {
    return columns;
  }

  public Record getIndex(int index) {
    return records.get(new ArrayList<>(records.keySet()).get(index));
  }

  public String getRecordsName() {
    return this.recordsName;
  }


  /* Redundant function in case a feature is added where user can choose save location. */
  public void save(String fileName) {
    ArrayList<ArrayList<String>> save = new ArrayList<>();

    for (Record record : records.values()) {
      save.add(record.getAll());
    }

    /* Put columns at the top of the file */
    save.add(0, columns);

    /* Put recordsName at the top of the file, before columns */
    save.add(0, new ArrayList<>(List.of(recordsName)));

    saveRecords(save, fileName);

    this.fileName = fileName;
  }

  public void save() {
    ArrayList<ArrayList<String>> save = new ArrayList<>();

    for (Record record : records.values()) {
      save.add(record.getAll());
    }

    /* Put columns at the top of the file */
    save.add(0, columns);

    /* Put recordsName at the top of the file, before columns */
    save.add(0, new ArrayList<>(List.of(recordsName)));

    saveRecords(save, fileName);
  }


  public int size() {
    return records.size();
  }
}
