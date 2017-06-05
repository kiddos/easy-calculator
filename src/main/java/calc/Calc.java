package calc;

import javax.swing.*;

public class Calc {
    public static void main(String[] args) {
        JFrame ui = new JFrame("Calculator");
        ui.setContentPane(new UI().contentPane);
        ui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ui.pack();
        ui.setVisible(true);
    }
}
