package calc;

import junit.framework.TestCase;

public class LexerTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testLexerTokenZero() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("0");
        assertEquals(pairs.length, 1);
        assertEquals(pairs[0].getValue(), 0.0);
        assertEquals(pairs[0].getToken(), Lexer.Tokens.NUMBER);
    }

    public void testLexerTokenZeroFloatingPoint() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("0.0");
        assertEquals(pairs.length, 1);
        assertEquals(pairs[0].getValue(), 0.0);
        assertEquals(pairs[0].getToken(), Lexer.Tokens.NUMBER);
    }

    public void testLexerTokenNonZero() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("1");
        assertEquals(pairs.length, 1);
        assertEquals(pairs[0].getValue(), 1.0);
        assertEquals(pairs[0].getToken(), Lexer.Tokens.NUMBER);
    }

    public void testLexerTokenNonZeroFloatingPoint() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("1.0");
        assertEquals(pairs.length, 1);
        assertEquals(pairs[0].getValue(), 1.0);
        assertEquals(pairs[0].getToken(), Lexer.Tokens.NUMBER);

        pairs = lexer.process("1.");
        assertEquals(pairs.length, 1);
        assertEquals(pairs[0].getValue(), 1.0);
        assertEquals(pairs[0].getToken(), Lexer.Tokens.NUMBER);
    }

    public void testLexerTokenZeroWithSpace() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process(" 0 ");
        assertEquals(pairs.length, 1);
        assertEquals(pairs[0].getValue(), 0.0);
        assertEquals(pairs[0].getToken(), Lexer.Tokens.NUMBER);
    }

    public void testLexerTokenMultipleZeroWithSpace() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process(" 0000000 ");
        assertEquals(pairs.length, 1);
        assertEquals(pairs[0].getValue(), 0.0);
        assertEquals(pairs[0].getToken(), Lexer.Tokens.NUMBER);
    }

    public void testLexerTokenNonZeroNumberWithSpace() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process(" 1000000 ");
        assertEquals(pairs.length, 1);
        assertEquals(pairs[0].getValue(), 1000000.0);
        assertEquals(pairs[0].getToken(), Lexer.Tokens.NUMBER);
    }

    public void testLexerTokenAdd() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("+");
        assertEquals(pairs.length, 1);
        assertEquals(pairs[0].getValue(), 0.0);
        assertEquals(pairs[0].getToken(), Lexer.Tokens.ADD);
    }

    public void testLexerTokenSubtract() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("-");
        assertEquals(pairs.length, 1);
        assertEquals(pairs[0].getValue(), 0.0);
        assertEquals(pairs[0].getToken(), Lexer.Tokens.SUB);
    }

    public void testLexerTokenMultiply() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("×");
        assertEquals(pairs.length, 1);
        assertEquals(pairs[0].getValue(), 0.0);
        assertEquals(pairs[0].getToken(), Lexer.Tokens.MUL);
    }

    public void testLexerTokenDivide() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("÷");
        assertEquals(pairs.length, 1);
        assertEquals(pairs[0].getValue(), 0.0);
        assertEquals(pairs[0].getToken(), Lexer.Tokens.DIV);
    }

    public void testLexerTokenExponential() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("^");
        assertEquals(pairs.length, 1);
        assertEquals(pairs[0].getValue(), 0.0);
        assertEquals(pairs[0].getToken(), Lexer.Tokens.EXP);
    }

    public void testLexerTokenMultipleWithSpace() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("1 + 1");
        assertEquals(pairs.length, 3);
        assertEquals(pairs[0].getValue(), 1.0);
        assertEquals(pairs[0].getToken(), Lexer.Tokens.NUMBER);
        assertEquals(pairs[1].getValue(), 0.0);
        assertEquals(pairs[1].getToken(), Lexer.Tokens.ADD);
        assertEquals(pairs[2].getValue(), 1.0);
        assertEquals(pairs[2].getToken(), Lexer.Tokens.NUMBER);
    }

    public void testLexerTokenMultipleWithoutSpace() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("1+1");
        assertEquals(pairs.length, 3);
        assertEquals(pairs[0].getValue(), 1.0);
        assertEquals(pairs[0].getToken(), Lexer.Tokens.NUMBER);
        assertEquals(pairs[1].getValue(), 0.0);
        assertEquals(pairs[1].getToken(), Lexer.Tokens.ADD);
        assertEquals(pairs[2].getValue(), 1.0);
        assertEquals(pairs[2].getToken(), Lexer.Tokens.NUMBER);
    }

    public void testLexerTokenLongExpression() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("11 + 22 - 33 × 44 ÷ 55");
        assertEquals(pairs.length, 9);
        assertEquals(pairs[0].getValue(), 11.0);
        assertEquals(pairs[0].getToken(), Lexer.Tokens.NUMBER);

        assertEquals(pairs[1].getValue(), 0.0);
        assertEquals(pairs[1].getToken(), Lexer.Tokens.ADD);

        assertEquals(pairs[2].getValue(), 22.0);
        assertEquals(pairs[2].getToken(), Lexer.Tokens.NUMBER);

        assertEquals(pairs[3].getValue(), 0.0);
        assertEquals(pairs[3].getToken(), Lexer.Tokens.SUB);

        assertEquals(pairs[4].getValue(), 33.0);
        assertEquals(pairs[4].getToken(), Lexer.Tokens.NUMBER);

        assertEquals(pairs[5].getValue(), 0.0);
        assertEquals(pairs[5].getToken(), Lexer.Tokens.MUL);

        assertEquals(pairs[6].getValue(), 44.0);
        assertEquals(pairs[6].getToken(), Lexer.Tokens.NUMBER);

        assertEquals(pairs[7].getValue(), 0.0);
        assertEquals(pairs[7].getToken(), Lexer.Tokens.DIV);

        assertEquals(pairs[8].getValue(), 55.0);
        assertEquals(pairs[8].getToken(), Lexer.Tokens.NUMBER);
    }

    public void testLexerTokenSpecial() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("e");
        assertEquals(pairs.length, 1);
        assertEquals(pairs[0].getValue(), Math.E);
        assertEquals(pairs[0].getToken(), Lexer.Tokens.NUMBER);

        pairs = lexer.process("π");
        assertEquals(pairs.length, 1);
        assertEquals(pairs[0].getValue(), Math.PI);
        assertEquals(pairs[0].getToken(), Lexer.Tokens.NUMBER);
    }

    public void testLexerTokenSpecialWithSpace() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("10 e");
        assertEquals(pairs.length, 2);
        assertEquals(pairs[1].getValue(), Math.E);
        assertEquals(pairs[1].getToken(), Lexer.Tokens.NUMBER);

        pairs = lexer.process("9 π");
        assertEquals(pairs.length, 2);
        assertEquals(pairs[1].getValue(), Math.PI);
        assertEquals(pairs[1].getToken(), Lexer.Tokens.NUMBER);
    }
}