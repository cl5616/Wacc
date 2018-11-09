package Information.OpInfo;

import Information.Information;

import java.util.Map;
import java.util.TreeMap;

public class BinOpInfo extends Information
{
    public enum BinOp {
        ADD, SUB, MUL, DIV, MOD, EQ, GT, LT, GTE, LTE, NEQ, AND, OR
    }
    private final Map<BinOp, String> binOpStringMap;
    public final BinOp binOpType;
    public BinOpInfo(BinOp binOpType)
    {
        binOpStringMap = new TreeMap<>();
        binOpStringMap.put(BinOp.ADD, "+");
        binOpStringMap.put(BinOp.SUB, "-");
        binOpStringMap.put(BinOp.MUL, "*");
        binOpStringMap.put(BinOp.DIV, "/");
        binOpStringMap.put(BinOp.MOD, "%");
        binOpStringMap.put(BinOp.EQ, "==");
        binOpStringMap.put(BinOp.GT, ">");
        binOpStringMap.put(BinOp.LT, "<");
        binOpStringMap.put(BinOp.GTE, ">=");
        binOpStringMap.put(BinOp.LTE, "<=");
        binOpStringMap.put(BinOp.NEQ, ">=");
        binOpStringMap.put(BinOp.AND, "&&");
        binOpStringMap.put(BinOp.OR, "||");
        this.binOpType = binOpType;
    }
    public String binOptoString(BinOp binOp)
    {
        return binOpStringMap.get(binOp);
    }
}
