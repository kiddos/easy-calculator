package calc;

import calc.exception.ParseException;
import junit.framework.TestCase;

public class ParserTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testParserEvaluateIntegerAdditionWithExtraSpace() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process(" 1 + 1 ");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertEquals(value, 2.0);
    }

    public void testParserEvaluateIntegerAdditionWithoutSpace() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("1+1");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertEquals(value, 2.0);
    }

    public void testParserEvaluationIntegerAddition() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("1 + 1");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertEquals(value, 2.0);
    }

    public void testParserEvaluationIntegerSubtraction() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("2 - 1");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertEquals(value, 1.0);
    }

    public void testParserEvaluationIntegerMultiplication() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("2 × 2");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertEquals(value, 4.0);
    }

    public void testParserEvaluationIntegerDivision() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("4 ÷ 2");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertEquals(value, 2.0);
    }

    public void testParserEvaluationIntegerExponential() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("4 ^ 2");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertEquals(value, 16.0);
    }

    public void testParserEvaluationFloatAddition() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("1.1 + 2.2");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertTrue(Math.abs(value - 3.3) < 1e-9);
    }

    public void testParserEvaluationFloatSubtraction() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("3.5 - 1.9");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertTrue(Math.abs(value - 1.6) < 1e-9);
    }

    public void testParserEvaluationFloatMultiplication() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("1.5 × 1.5");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertTrue(Math.abs(value - 2.25) < 1e-9);
    }

    public void testParserEvaluationFloatDivision() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("2.6 ÷ 1.3");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertTrue(Math.abs(value - 2.0) < 1e-9);
    }

    public void testParserEvaluationFloatExponential() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("4.0 ^ 0.5");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertTrue(Math.abs(value - 2.0) < 1e-9);
    }

    public void testParserEvaluateShortIntegerExpression() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("1 + 2 × 3");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertEquals(value, 7.0);
    }

    public void testParserEvaluateFloatExpressionWithSpace() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("1.0 + 10.0");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertEquals(value, 11.0);
    }

    public void testParserEvaluateFloatExpressionWithoutSpace() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("3.0+30.0");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertTrue(Math.abs(value - 33.0) < 1e-9);
    }

    public void testParserEvaluationFloatExpressionWithMultipleSpace() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process(" 2.2 + 1.1 ");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertTrue(Math.abs(value - 3.3) < 1e-9);
    }

    public void testParserEvaluateShortFloatExpression() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("1.1 + 2.2 × 3.3");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertTrue(Math.abs(value - 8.36) < 1e-9);
    }

    public void testParserEvaluateNegativeSignNumbers() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("1 + -2");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertEquals(value, -1.0);

        pairs = lexer.process("1 × -2");
        value = parser.parse(pairs);
        assertEquals(value, -2.0);

        pairs = lexer.process("1 ÷ -2");
        value = parser.parse(pairs);
        assertEquals(value, -0.5);
    }

    public void testParserEvaluateNegativeInteger() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("-1");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertEquals(value, -1.0);
    }

    public void testParseInvalidAddExpression() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("+ 2");
        Parser parser = new Parser();
        boolean thrown = false;
        try {
            parser.parse(pairs);
        } catch (ParseException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    public void testParseInvalidMultiplyExpression() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("× 2");
        Parser parser = new Parser();
        boolean thrown = false;
        try {
            parser.parse(pairs);
        } catch (ParseException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    public void testParseInvalidDivideExpression() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("÷ 2");
        Parser parser = new Parser();
        boolean thrown = false;
        try {
            parser.parse(pairs);
        } catch (ParseException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    public void testParseInvalidNumberExpression() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("0 1 2 3");
        Parser parser = new Parser();
        boolean thrown = false;
        try {
            parser.parse(pairs);
        } catch (ParseException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    public void testParserEvaluateLongFloatExpression() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("11.1 + 22.2 - 33.3 × 44.4 ÷ 55.5");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertTrue(Math.abs(value - 6.66) < 1e-9);
    }

    public void testParserEvaluateReverseLongFloatExpression() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("11.1 ÷ 22.2 × 33.3 + - 44.4 + 55.5");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertTrue(Math.abs(value - 27.75) < 1e-9);
    }
}