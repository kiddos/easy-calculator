package calc;

import junit.framework.TestCase;

public class ParserTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testParseSingle() throws Exception {
        Lexer lexer = new Lexer();
        Lexer.Pair[] pairs = lexer.process("1 + 1");
        Parser parser = new Parser();
        double value = parser.parse(pairs);
        assertEquals(value, 2.0);
    }
}