package Information;

import Information.ASTTypes.Type;

public class Stat extends Information{

  public Type getType() {
    return type;
  }

  public enum StatInfo{
    SKIP, VAR_DECLARE, ASSIGN, READ, FREE, RETURN, EXIT,
    PRINT, PRINTLN, WHILE, BEGIN, SUPER, DELETE;
  }

  private final StatInfo stat;
  private Type type;


  public Stat(StatInfo stat) {
    this.stat = stat;
  }

  public Stat(StatInfo stat, Type type){
    this.stat = stat;
    this.type = type;
  }

  public StatInfo getStat() {
    return stat;
  }
}
