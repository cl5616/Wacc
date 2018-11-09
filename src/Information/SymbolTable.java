package Information;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class SymbolTable<T>{

  private SymbolTable<T> encST;
  private Map<String, T> dict = new HashMap<>();

  public SymbolTable(SymbolTable<T> encST) {
    this.encST = encST;
  }

  public SymbolTable() {
    encST = null;
  }

  private Map<String, T> getDict() {
    return dict;
  }

  private SymbolTable<T> getEncST() {
    return encST;
  }

  public boolean addSymbol(String newName, T info) {
    dict.put(newName, info);
    return true;
  }

  public boolean contain(String name){
    return dict.containsKey(name);
  }

  public T lookForAll(String name){
    if (contain(name)){
      return dict.get(name);
    }

    if (encST == null){
      return null;
    }

    return encST.lookForAll(name);
  }

  public SymbolTable<T> restore() {
    return encST;
  }

  public SymbolTable<T> generateNew() {
    return new SymbolTable<T>(this);
  }

  @Override
  public String toString() {
    String ret = "";
    if (encST != null){
      ret += encST.toString();
      ret += " ";
    }
    Iterator it = dict.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry)it.next();
      ret += " ";
      ret += pair.getKey();
      ret += "->";
      ret += pair.getValue();
      ret += " ";
    }
    return ret;
  }

  public void remove(String objectToBeRemoved) {
    dict.remove(objectToBeRemoved);
  }
}
