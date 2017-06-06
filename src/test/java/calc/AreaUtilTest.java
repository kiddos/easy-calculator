package calc;

import calc.exception.ComputeException;
import junit.framework.TestCase;

public class AreaUtilTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testComputeRectangleArea() throws Exception {
        double result = AreaUtil.computeRectangleArea("100", "300");
        assertTrue(Math.abs(result - 30000) < 1e-9);

        result = AreaUtil.computeRectangleArea("30.0", "30.0");
        assertTrue(Math.abs(result - 900) < 1e-9);

        boolean thrown = false;
        try {
            AreaUtil.computeRectangleArea("-30.0", "-30.0");
        } catch (ComputeException e) {
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try {
            AreaUtil.computeRectangleArea("-30.0", "30");
        } catch (ComputeException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    public void testComputeTrapazoidArea() throws Exception {
        double result = AreaUtil.computeTrapazoidArea("200", "100", "10");
        assertTrue(Math.abs(result - 1500) < 1e-9);

        result = AreaUtil.computeTrapazoidArea("10.1", "20.2", "30.3");
        assertTrue(Math.abs(result - 459.04) < 1e-2);

        boolean thrown = false;
        try {
            AreaUtil.computeTrapazoidArea("-30.0", "-30.0", "-10.0");
        } catch (ComputeException e) {
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try {
            AreaUtil.computeTrapazoidArea("-30.0", "30", "0.0");
        } catch (ComputeException e) {
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try {
            AreaUtil.computeTrapazoidArea("30.0", "-30", "0.0");
        } catch (ComputeException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}