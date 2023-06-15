import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.misc.Pair;
import semantic.utils.Constants;

public class CustomErrorStrategy extends DefaultErrorStrategy {

    @Override
    public void reset(Parser recognizer) {
        super.reset(recognizer);
    }

    @Override
    protected void beginErrorCondition(Parser recognizer) {
        super.beginErrorCondition(recognizer);
    }

    @Override
    public boolean inErrorRecoveryMode(Parser recognizer) {
        return super.inErrorRecoveryMode(recognizer);
    }

    @Override
    protected void endErrorCondition(Parser recognizer) {
        super.endErrorCondition(recognizer);
    }

    @Override
    public void reportMatch(Parser recognizer) {
        super.reportMatch(recognizer);
    }

    @Override
    public void recover(Parser recognizer, RecognitionException e) {
        super.recover(recognizer, e);
    }

    @Override
    public void sync(Parser recognizer) throws RecognitionException {
        super.sync(recognizer);
    }

    @Override
    public Token recoverInline(Parser recognizer) throws RecognitionException {
        return super.recoverInline(recognizer);
    }

    @Override
    protected boolean singleTokenInsertion(Parser recognizer) {
        return super.singleTokenInsertion(recognizer);
    }

    @Override
    protected Token singleTokenDeletion(Parser recognizer) {
        return super.singleTokenDeletion(recognizer);
    }

    @Override
    protected IntervalSet getExpectedTokens(Parser recognizer) {
        return super.getExpectedTokens(recognizer);
    }

    @Override
    protected String getSymbolText(Token symbol) {
        return super.getSymbolText(symbol);
    }

    @Override
    protected int getSymbolType(Token symbol) {
        return super.getSymbolType(symbol);
    }

    @Override
    protected String escapeWSAndQuote(String s) {
        return super.escapeWSAndQuote(s);
    }

    @Override
    protected IntervalSet getErrorRecoverySet(Parser recognizer) {
        return super.getErrorRecoverySet(recognizer);
    }

    @Override
    protected void consumeUntil(Parser recognizer, IntervalSet set) {
        super.consumeUntil(recognizer, set);
    }

    @Override
    public void reportError(Parser recognizer, RecognitionException e) {
        if (!this.inErrorRecoveryMode(recognizer)) {
            this.beginErrorCondition(recognizer);
            if (e instanceof NoViableAltException) {
                this.reportNoViableAlternative(recognizer, (NoViableAltException)e);
            } else if (e instanceof InputMismatchException) {
                this.reportInputMismatch(recognizer, (InputMismatchException)e);
            } else if (e instanceof FailedPredicateException) {
                this.reportFailedPredicate(recognizer, (FailedPredicateException)e);
            } else {
                System.err.println("Error de reconocimiento desconocido: " + e.getClass().getName());
                Constants.PROGRAM.setMalformed();
                recognizer.notifyErrorListeners(e.getOffendingToken(), e.getMessage(), e);
            }

        }
    }

    @Override
    protected void reportNoViableAlternative(Parser recognizer, NoViableAltException e) {
        TokenStream tokens = recognizer.getInputStream();
        String input;
        if (tokens != null) {
            if (e.getStartToken().getType() == -1) {
                input = "<fin de fichero>";
            } else {
                input = tokens.getText(e.getStartToken(), e.getOffendingToken());
            }
        } else {
            input = "<entrada desconocida>";
        }

        String msg = "No se ha encontrado una alternativa a " + this.escapeWSAndQuote(input);
        Constants.PROGRAM.setMalformed();
        recognizer.notifyErrorListeners(e.getOffendingToken(), msg, e);
    }

    @Override
    protected void reportInputMismatch(Parser recognizer, InputMismatchException e) {
        String msg = "Entrada inesperada " + this.getTokenErrorDisplay(e.getOffendingToken()) + " esperado " + e.getExpectedTokens().toString(recognizer.getVocabulary());
        Constants.PROGRAM.setMalformed();
        recognizer.notifyErrorListeners(e.getOffendingToken(), msg, e);
    }

    @Override
    protected void reportFailedPredicate(Parser recognizer, FailedPredicateException e) {
        String ruleName = recognizer.getRuleNames()[recognizer.getRuleContext().getRuleIndex()];
        String msg = "Regla " + ruleName + " " + e.getMessage();
        Constants.PROGRAM.setMalformed();
        recognizer.notifyErrorListeners(e.getOffendingToken(), msg, e);
    }

    @Override
    protected void reportUnwantedToken(Parser recognizer) {
        if (!this.inErrorRecoveryMode(recognizer)) {
            this.beginErrorCondition(recognizer);
            Token t = recognizer.getCurrentToken();
            String tokenName = this.getTokenErrorDisplay(t);
            IntervalSet expecting = this.getExpectedTokens(recognizer);
            String msg = "Entrada desconocida " + tokenName + " esperado " + expecting.toString(recognizer.getVocabulary());
            Constants.PROGRAM.setMalformed();
            recognizer.notifyErrorListeners(t, msg, null);
        }
    }

    @Override
    protected void reportMissingToken(Parser recognizer) {
        if (!this.inErrorRecoveryMode(recognizer)) {
            this.beginErrorCondition(recognizer);
            Token t = recognizer.getCurrentToken();
            IntervalSet expecting = this.getExpectedTokens(recognizer);
            String msg = "Falta " + expecting.toString(recognizer.getVocabulary()) + " antes de " + this.getTokenErrorDisplay(t);
            Constants.PROGRAM.setMalformed();
            recognizer.notifyErrorListeners(t, msg, null);
        }
    }

    @Override
    protected Token getMissingSymbol(Parser recognizer) {
        Token currentSymbol = recognizer.getCurrentToken();
        IntervalSet expecting = this.getExpectedTokens(recognizer);
        int expectedTokenType = 0;
        if (!expecting.isNil()) {
            expectedTokenType = expecting.getMinElement();
        }

        String tokenText;
        if (expectedTokenType == -1) {
            tokenText = "<esperado fin de fichero>";
        } else {
            tokenText = "<esperado " + recognizer.getVocabulary().getDisplayName(expectedTokenType) + ">";
        }

        Token current = currentSymbol;
        Token lookback = recognizer.getInputStream().LT(-1);
        if (currentSymbol.getType() == -1 && lookback != null) {
            current = lookback;
        }

        return recognizer.getTokenFactory().create(new Pair(current.getTokenSource(), current.getTokenSource().getInputStream()), expectedTokenType, tokenText, 0, -1, -1, current.getLine(), current.getCharPositionInLine());
    }

    @Override
    protected String getTokenErrorDisplay(Token t) {
        if (t == null) {
            return "<no se ha encontrado un token viable>";
        } else {
            String s = this.getSymbolText(t);
            if (s == null) {
                if (this.getSymbolType(t) == -1) {
                    s = "<fin de fichero>";
                } else {
                    s = "<" + this.getSymbolType(t) + ">";
                }
            }

            return this.escapeWSAndQuote(s);
        }
    }
}
