package schoolrecords.records;

import static schoolrecords.misc.Utility.loadRecords;
import static schoolrecords.misc.Utility.saveRecords;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class Records {
  private String recordsName;
  ArrayList<String> columns = new ArrayList<>();
  private final LinkedHashMap<String, Record> records = new LinkedHashMap<>();
  private String fileName;

  public Records(String fileName) {
    ArrayList<ArrayList<String>> load = loadRecords(fileName);
    this.recordsName = load.removeFirst().removeFirst();
    this.columns = load.removeFirst();
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


  public void save(String fileName) {
    ArrayList<ArrayList<String>> save = new ArrayList<>();

    for (Record record : records.values()) {
      save.add(record.getAll());
    }

    save.addFirst(columns);

    saveRecords(save, fileName);

    this.fileName = fileName;
  }

  public void save() {
    ArrayList<ArrayList<String>> save = new ArrayList<>();

    for (Record record : records.values()) {
      save.add(record.getAll());
    }

    save.addFirst(columns);

    save.addFirst(new ArrayList<>(List.of(recordsName)));

    saveRecords(save, fileName);
  }


  public int size() {
    return records.size();
  }
}
