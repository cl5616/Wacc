package Information.OpInfo;
import Information.Information;

public class UnOpInfo extends Information
{
    public enum UnOp {
        NOT, NEG, LEN, ORD, CHR, PLUS
    };
    public final UnOp unOpType;
    public UnOpInfo(UnOp unOpType)
    {
        this.unOpType = unOpType;
    }
}
