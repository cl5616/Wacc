package Information;

public class WaccClassVmCodesInfo extends Information{

  private final VmCodesInfo operation;
  private final Information fieldOrMethod;


  public WaccClassVmCodesInfo(VmCodesInfo operation, Information fieldOrMethod) {
    this.operation = operation;
    this.fieldOrMethod = fieldOrMethod;
  }

  public Information getOperation() {
    return operation;
  }

  public Information getFieldOrMethod() {
    return fieldOrMethod;
  }
}
