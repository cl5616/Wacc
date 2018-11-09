import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.misc.Nullable;

public class ErrorListener extends BaseErrorListener {

  @Override
  public void syntaxError(@NotNull Recognizer<?, ?> recognizer, @Nullable Object offendingSymbol,
      int line, int charPositionInLine, @NotNull String msg, @Nullable RecognitionException e) {
    System.err.println(msg + " " + line);
    System.exit(100);
  }
}
