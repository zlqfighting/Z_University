package New_Calculator;

import Calculator.SimpleCalculator;

import javax.swing.*;
import java.awt.*;

public class Test {
    public static void main(String[] args) {
        EventQueue.invokeLater(()->
        {
            JFrame frame = new SimpleCalculator();

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }

        }



