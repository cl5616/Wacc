package Information;

import Information.ASTTypes.Type;
import java.util.List;

public class ParamList extends Information {

  private final List<Type> params;

  public ParamList(List<Type> params) {
    this.params = params;
  }

  public List<Type> getParams() {
    return params;
  }

  public int size(){
    return params.size();
  }

  public Type getParam(int i){
    return params.get(i);
  }
}
