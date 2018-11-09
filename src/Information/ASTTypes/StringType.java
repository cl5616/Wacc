package Information.ASTTypes;

public class StringType extends ArrayType{

  private String value;

  public StringType(String varName, String value) {
    super(varName, new CharType(), value.length());
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public StringType() {
    super("", new CharType(), -1);
  }
}
