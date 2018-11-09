package ARM;

public class Arithmetic extends ArmInstruction {

  private final int r1;
  private final int r2;
  private final int r3;

  public Arithmetic(Instruction instruction, int r1, int r2, int r3) {
    super(instruction);
    this.r1 = r1;
    this.r2 = r2;
    this.r3 = r3;
  }
}