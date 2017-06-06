package calc;

import calc.exception.ComputeException;

public class PerimeterUtil {
    public static double computeRectanglePerimeter(String width, String height) throws ComputeException {
        try {
            double w = Double.parseDouble(width);
            double h = Double.parseDouble(height);
            if (w < 0 || h < 0) {
                throw new ComputeException("Invalid Expression: result in negative output");
            }
            return w * 2 + h * 2;
        } catch (Exception e) {
            throw new ComputeException("Invalid Expression: incorrect input");
        }
    }

    public static double computeTrapazoidPerimeter(String top, String bottom, String side1, String side2) throws ComputeException {
        try {
            double t = Double.parseDouble(top);
            double b = Double.parseDouble(bottom);
            double s1 = Double.parseDouble(side1);
            double s2 = Double.parseDouble(side2);
            if (t < 0 || b < 0 || s1 < 0 || s2 < 0) {
                throw new ComputeException("Invalid Expression: result in negative output");
            }
            return t + b + s1 + s2;
        } catch (Exception e) {
            throw new ComputeException("Invalid Expression: incorrect input");
        }
    }
}
