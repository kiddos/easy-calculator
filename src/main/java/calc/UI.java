package calc;

import calc.exception.CalculatorException;
import calc.exception.ComputeException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
    public JPanel contentPane;
    private JPanel buttenPanel;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JButton btn0;
    private JButton btnDot;
    private JButton btnAdd;
    private JButton btnSub;
    private JButton btnMul;
    private JButton btnDiv;
    private JButton btnEnter;
    private JButton btnClear;
    private JButton btnExponent;
    private JButton btnNatural;
    private JButton btnPi;
    private JPanel displayPanel;
    private JTextField entry;
    private JPanel calculatorPanel;
    private JPanel areaPanel;
    private JPanel areaOutputPanel;
    private JTextField tfArea;
    private JPanel widthPanel;
    private JPanel heightPanel;
    private JPanel perimeterPanel;
    private JTextField tfPerimeter;
    private JTextField tfWidth;
    private JTextField tfHeight;
    private JTextField tfTTop;
    private JTextField tfTHeight;
    private JTextField tfTBottom;
    private JPanel outputPanel;
    private JPanel areaInputPanel;
    private JPanel trapazoidInputPanel;
    private JPanel topPanel;
    private JPanel bottomPanel;
    private JPanel height2Panel;
    private JPanel side2Panel;
    private JPanel side1Panel;
    private JTextField tfTSide1;
    private JTextField tfTSide2;

    public UI() {
        // number buttons
        btn0.addActionListener(new NormalInsertHandler("0"));
        btn1.addActionListener(new NormalInsertHandler("1"));
        btn2.addActionListener(new NormalInsertHandler("2"));
        btn3.addActionListener(new NormalInsertHandler("3"));
        btn4.addActionListener(new NormalInsertHandler("4"));
        btn5.addActionListener(new NormalInsertHandler("5"));
        btn6.addActionListener(new NormalInsertHandler("6"));
        btn7.addActionListener(new NormalInsertHandler("7"));
        btn8.addActionListener(new NormalInsertHandler("8"));
        btn9.addActionListener(new NormalInsertHandler("9"));
        // dot
        btnDot.addActionListener(new FloatingPointHandler("."));
        // clear button
        btnClear.addActionListener(new ClearHandler());
        // special numbers
        btnPi.addActionListener(new SpecialNumberHandler("π"));
        btnNatural.addActionListener(new SpecialNumberHandler("e"));
        // operator
        btnAdd.addActionListener(new NormalOperatorHandler("+"));
        btnMul.addActionListener(new NormalOperatorHandler("×"));
        btnDiv.addActionListener(new NormalOperatorHandler("÷"));
        btnExponent.addActionListener(new NormalOperatorHandler("^"));
        btnSub.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = entry.getText();
                String[] slice = text.split(" ");
                if (slice.length == 0 || !isNumeric(slice[slice.length - 1])) {
                    entry.setText(entry.getText() + " -");
                } else {
                    entry.setText(entry.getText() + " - ");
                }
            }
        });
        btnEnter.addActionListener(new EnterHandler());

        // disable entry
        entry.setEditable(false);
        tfArea.setEditable(false);
        tfPerimeter.setEditable(false);
        // area
        RectangleAreaHandler rectangleAreaHandler = new RectangleAreaHandler();
        tfWidth.getDocument().addDocumentListener(rectangleAreaHandler);
        tfHeight.getDocument().addDocumentListener(rectangleAreaHandler);
        TrapazoidAreaHandler trapazoidAreaHandler = new TrapazoidAreaHandler();
        tfTTop.getDocument().addDocumentListener(trapazoidAreaHandler);
        tfTBottom.getDocument().addDocumentListener(trapazoidAreaHandler);
        tfTHeight.getDocument().addDocumentListener(trapazoidAreaHandler);

        // perimeter
        RectanglePerimeterHandler rectanglePerimeterHandler = new RectanglePerimeterHandler();
        tfWidth.getDocument().addDocumentListener(rectanglePerimeterHandler);
        tfHeight.getDocument().addDocumentListener(rectanglePerimeterHandler);
        TrapazoidPerimeterHandler trapazoidPerimeterHandler = new TrapazoidPerimeterHandler();
        tfTTop.getDocument().addDocumentListener(trapazoidPerimeterHandler);
        tfTBottom.getDocument().addDocumentListener(trapazoidPerimeterHandler);
        tfTSide1.getDocument().addDocumentListener(trapazoidPerimeterHandler);
        tfTSide2.getDocument().addDocumentListener(trapazoidPerimeterHandler);
    }

    private boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private class NormalInsertHandler implements ActionListener {
        private String text;

        public NormalInsertHandler(String text) {
            this.text = text;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            entry.setText(entry.getText() + this.text);
        }
    }

    private class ClearHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            entry.setText("");
        }
    }

    private class FloatingPointHandler implements ActionListener {
        private String dot;

        public FloatingPointHandler(String dot) {
            this.dot = dot;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String text = entry.getText();
            String[] slice = text.split(" ");
            if (slice.length == 0 || !slice[slice.length - 1].contains(".")) {
                entry.setText(entry.getText() + this.dot);
            }
        }
    }

    private class SpecialNumberHandler implements ActionListener {
        private String number;

        public SpecialNumberHandler(String number) {
            this.number = number;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            entry.setText(entry.getText() + " " + this.number + " ");
        }
    }

    private class NormalOperatorHandler implements ActionListener {
        private String operator;

        public NormalOperatorHandler(String operator) {
            this.operator = operator;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            entry.setText(entry.getText() + " " + this.operator + " ");
        }
    }

    private class EnterHandler implements ActionListener {
        private Lexer lexer = new Lexer();
        private Parser parser = new Parser();

        @Override
        public void actionPerformed(ActionEvent a) {
            String expression = entry.getText();
            Lexer.Pair[] pairs = lexer.process(expression);

            try {
                double value = parser.parse(pairs);
                entry.setText(String.valueOf(value));
            } catch (CalculatorException e) {
                JOptionPane.showConfirmDialog(contentPane, "Warning", e.getMessage(),
                        JOptionPane.WARNING_MESSAGE, JOptionPane.OK_OPTION);
            }
        }
    }

    private class RectangleAreaHandler implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            compute();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            compute();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            compute();
        }

        private void compute() {
            String width = tfWidth.getText();
            String height = tfHeight.getText();
            if (width.length() > 0 && height.length() > 0) {
                try {
                    double area = AreaUtil.computeRectangleArea(width, height);
                    tfArea.setText(String.valueOf(area));
                } catch (ComputeException c) {
                    JOptionPane.showConfirmDialog(contentPane, "Warning", c.getMessage(),
                            JOptionPane.WARNING_MESSAGE, JOptionPane.OK_OPTION);
                }
            }
        }
    }

    private class TrapazoidAreaHandler implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            compute();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            compute();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            compute();
        }

        private void compute() {
            String top = tfTTop.getText();
            String bot = tfTBottom.getText();
            String height = tfTHeight.getText();
            if (top.length() > 0 && bot.length() > 0 && height.length() > 0) {
                try {
                    double area = AreaUtil.computeTrapazoidArea(top, bot, height);
                    tfArea.setText(String.valueOf(area));
                } catch (ComputeException c) {
                    JOptionPane.showConfirmDialog(contentPane, "Warning", c.getMessage(),
                            JOptionPane.WARNING_MESSAGE, JOptionPane.OK_OPTION);
                }
            }
        }
    }

    private class RectanglePerimeterHandler implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            compute();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            compute();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            compute();
        }

        private void compute() {
            String width = tfWidth.getText();
            String height = tfHeight.getText();
            if (width.length() > 0 && height.length() > 0) {
                try {
                    double perimeter = PerimeterUtil.computeRectanglePerimeter(width, height);
                    tfPerimeter.setText(String.valueOf(perimeter));
                } catch (ComputeException c) {
                    JOptionPane.showConfirmDialog(contentPane, "Warning", c.getMessage(),
                            JOptionPane.WARNING_MESSAGE, JOptionPane.OK_OPTION);
                }
            }
        }
    }

    private class TrapazoidPerimeterHandler implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            compute();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            compute();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            compute();
        }

        private void compute() {
            String top = tfTTop.getText();
            String bot = tfTBottom.getText();
            String side1 = tfTSide1.getText();
            String side2 = tfTSide2.getText();
            if (top.length() > 0 && bot.length() > 0 && side1.length() > 0 && side2.length() > 0) {
                try {
                    double perimeter = PerimeterUtil.computeTrapazoidPerimeter(top, bot, side1, side2);
                    tfPerimeter.setText(String.valueOf(perimeter));
                } catch (ComputeException c) {
                    JOptionPane.showConfirmDialog(contentPane, "Warning", c.getMessage(),
                            JOptionPane.WARNING_MESSAGE, JOptionPane.OK_OPTION);
                }
            }
        }
    }
}
