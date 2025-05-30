import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    JTextField display;
    String operator = "";
    double num1, num2, result;

    public Calculator() {
        // Frame setup
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display field
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        // Buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String input = e.getActionCommand();

        if (input.matches("[0-9]")) {
            display.setText(display.getText() + input);
        } else if (input.matches("[+\\-*/]")) {
            operator = input;
            num1 = Double.parseDouble(display.getText());
            display.setText("");
        } else if (input.equals("=")) {
            num2 = Double.parseDouble(display.getText());
            switch (operator) {
                case "+": result = num1 + num2; break;
                case "-": result = num1 - num2; break;
                case "*": result = num1 * num2; break;
                case "/":
                    if (num2 != 0) result = num1 / num2;
                    else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }
            display.setText(String.valueOf(result));
        } else if (input.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
            operator = "";
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
