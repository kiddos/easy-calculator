package calc;

import calc.exception.ComputeException;
import junit.framework.TestCase;

public class PerimeterUtilTest extends TestCase {
    public void setUp() throws Exception {
        super.setUp();
    }

    public void tearDown() throws Exception {
    }

    public void testComputeRectanglePerimeter() throws Exception {
        double result = PerimeterUtil.computeRectanglePerimeter("100", "200");
        assertTrue(Math.abs(result - 600) < 1e-2);

        result = PerimeterUtil.computeRectanglePerimeter("10.1", "20.2");
        assertTrue(Math.abs(result - 60.6) < 1e-2);

        boolean thrown = false;
        try {
            PerimeterUtil.computeRectanglePerimeter("-10.1", "20.2");
        } catch (ComputeException e) {
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try {
            PerimeterUtil.computeRectanglePerimeter("-10.1", "-20.2");
        } catch (ComputeException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    public void testComputeTrapazoidPerimeter() throws Exception {
        double result = PerimeterUtil.computeTrapazoidPerimeter("30", "10", "20", "40");
        assertTrue(Math.abs(result - 100) < 1e-2);

        result = PerimeterUtil.computeTrapazoidPerimeter("30.3", "10.1", "20.2", "40.4");
        assertTrue(Math.abs(result - 101) < 1e-2);

        boolean thrown = false;
        try {
            PerimeterUtil.computeTrapazoidPerimeter("-10.1", "20.2", "30.3", "40.4");
        } catch (ComputeException e) {
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try {
            PerimeterUtil.computeTrapazoidPerimeter("-10.1", "-20.2", "-30.3", "-40.4");
        } catch (ComputeException e) {
            thrown = true;
        }
        assertTrue(thrown);

        thrown = false;
        try {
            PerimeterUtil.computeTrapazoidPerimeter("10.1", "20.2", "-30.3", "40.4");
        } catch (ComputeException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

}