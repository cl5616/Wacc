import Information.SymbolTable;

import java.io.*;

import org.antlr.v4.codegen.CodeGeneratorExtension;
import org.antlr.v4.runtime.*;
import antlr.*;
import org.antlr.v4.runtime.tree.ParseTree;

public class Compiler {

  private static int DOT_WACC_LEN = 5;
  /*private class Arguments
  {
    private boolean isVM;
    private String fileName;
    private String outputFile;
    public Arguments(String args[])
    {
      isVM = false;

      for (int i = 0; i < args.length; )
      {
        if (args[i].equals("-o"))
        {

        }
        else if (args[i].equals("-vm"))
        {
          isVM = true;
        }
        else
        {

        }
        i++;
      }
    }
  }//*/

  public static void main(String[] args) throws Exception {

    String[] parseFilename;
    String waccFileName;
    boolean isVM;
    if (args.length == 1) {
      waccFileName = args[0];
      isVM = false;
    }
    else if (args.length == 2 && args[0].equals("-vm"))
    {
      waccFileName = args[1];
      isVM = true;
    }
    else
    {
      System.err.println("invalid argument format");
      System.exit(-1);
      return;
    }
    parseFilename = waccFileName.split("\\.(?=[^\\.]+$)");

    if (!parseFilename[1].equals("wacc")) {
      System.err.println("Not wacc format file!");
      System.exit(-1);
    }


    File file = new File(waccFileName);

    InputStream targetStream = new FileInputStream(file);

    ANTLRInputStream input = new ANTLRInputStream(targetStream);

    BasicLexer lexer = new BasicLexer(input);

    CommonTokenStream tokens = new CommonTokenStream(lexer);

    BasicParser parser = new BasicParser(tokens);

    parser.removeErrorListeners();

    parser.addErrorListener(new ErrorListener());

    ParseTree tree = parser.main();

    SemanticVisitor semanticVisitor = new SemanticVisitor(new SymbolTable());

    semanticVisitor.visit(tree);

    CodeGenWaccVisitor codeGenWaccVisitor = new CodeGenWaccVisitor(semanticVisitor);

    codeGenWaccVisitor.visit(tree);//generate VMCodes

    String arm = null;
    byte[] bytecode = null;
    if (isVM)
    {
      bytecode = codeGenWaccVisitor.getVmProgram().toVMByteCodes();
    }
    else
    {
      arm = codeGenWaccVisitor.getVmProgram().toArmProgram();
    }
    String absoluteFileName = parseFilename[0];

    String fileName;

    try {

      fileName = absoluteFileName.substring(absoluteFileName.lastIndexOf('/') + 1);

    } catch (StringIndexOutOfBoundsException e) { // if the filePath is the same as filename

      fileName = absoluteFileName;
    }

    String asmFileName = fileName + (isVM ? ".waccc" : ".s");

    String dirName = "./waccVM/";

    File folder = new File(dirName);

    if(!folder.exists()) {
      folder.mkdirs();
    }

    String savingPath = dirName + asmFileName;


    if (isVM)
    {
      DataOutputStream writer = new DataOutputStream(new FileOutputStream(savingPath));
      writer.write(bytecode);
      writer.close();
    }
    else
    {
      PrintWriter writer = new PrintWriter(savingPath, "UTF-8");
      writer.println(arm);
      writer.close();
    }


    System.exit(0);
  }
}