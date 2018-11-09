package ARM;

public class Branch extends ArmInstruction {

  private final String label;

  public Branch(Instruction instruction, String label) {
    super(instruction);
    this.label = label;
  }

}
