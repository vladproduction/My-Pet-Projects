import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorApp extends JFrame {
    public int res = 0;

    public CalculatorApp(){
        setTitle("calculator_swing App");
        JPanel jPanel = new JPanel();

        JButton plus = new JButton("+");
        JButton minus = new JButton("-");
        JButton multiply = new JButton("*");
        JButton divide = new JButton("/");

        JLabel result = new JLabel("result");
        TextField a = new TextField("");
        TextField b = new TextField("");

        ActionListener plusAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value_a = Integer.parseInt(a.getText());
                int value_b = Integer.parseInt(b.getText());
                res = value_a + value_b;
                result.setText(String.valueOf(res));
            }
        };

        ActionListener minusAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value_a = Integer.parseInt(a.getText());
                int value_b = Integer.parseInt(b.getText());
                res = value_a - value_b;
                result.setText(String.valueOf(res));
            }
        };

        ActionListener multiplyAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value_a = Integer.parseInt(a.getText());
                int value_b = Integer.parseInt(b.getText());
                res = value_a * value_b;
                result.setText(String.valueOf(res));
            }
        };

        ActionListener divideAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value_a = Integer.parseInt(a.getText());
                int value_b = Integer.parseInt(b.getText());
                res = value_a / value_b;
                result.setText(String.valueOf(res));
            }
        };

        plus.addActionListener(plusAction);
        minus.addActionListener(minusAction);
        multiply.addActionListener(multiplyAction);
        divide.addActionListener(divideAction);

        jPanel.setSize(400,200);
        add(jPanel);

        jPanel.add(a);
        jPanel.add(b);
        jPanel.add(plus);
        jPanel.add(minus);
        jPanel.add(multiply);
        jPanel.add(divide);
        jPanel.add(result);

        setPreferredSize(new Dimension(400,200));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }
}
