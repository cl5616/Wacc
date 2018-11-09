package ARM;

public abstract class ArmInstruction {

  private final Instruction instruction;

  protected enum Instruction {
    BL, B, ADD, MUL, SUB;
  }

  public ArmInstruction(Instruction instruction){
    this.instruction = instruction;
  }
}
