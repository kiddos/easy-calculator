package calc;

import calc.exception.ComputeException;

public class AreaUtil {
    public static double computeRectangleArea(String width, String height) throws ComputeException {
        try {
            double w  = Double.parseDouble(width);
            double h = Double.parseDouble(height);
            double area = w * h;
            if (w < 0 || h < 0) {
                throw new ComputeException("Invalid Input: result in negative output");
            }
            return area;
        } catch (Exception e) {
            throw new ComputeException("Invalid Input: incorrect input type");
        }
    }

    public static double computeTrapazoidArea(String top, String bottom, String height) throws ComputeException {
        try {
            double t = Double.parseDouble(top);
            double b = Double.parseDouble(bottom);
            double h = Double.parseDouble(height);
            double area = (t + b) * h / 2.0;
            if (t < 0 || b < 0 || h < 0) {
                throw new ComputeException("Invalid Input: result in negative output");
            }
            return area;
        } catch (Exception e) {
            throw new ComputeException("Invalid Input: incorrect input type");
        }
    }
}
