import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Result extends JFrame implements ActionListener {

    private JLabel put_name;
    private JLabel put_weight;
    private JLabel put_height;
    private JLabel put_result;
    private JLabel result_txt;
    private JLabel put_age;
    private JButton back;

    public Result(String name, double weight, double height, String result, String result_text, int age) {
        super("BMI RESULT");
        setSize(500, 300);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        put_name = new JLabel("Name: " + name);
        add(put_name);
        put_name.setBounds(10, 10, 280, 20);

        put_age = new JLabel("Age: " + age);
        add(put_age);
        put_age.setBounds(10, 40, 280, 20);

        put_weight = new JLabel("Weight: " + weight + " Kg");
        add(put_weight);
        put_weight.setBounds(10, 70, 280, 20);

        put_height = new JLabel("Height: " + height + " Meter");
        add(put_height);
        put_height.setBounds(10, 100, 280, 20);

        put_result = new JLabel("Your BMI is: " + result);
        add(put_result);
        put_result.setBounds(10, 130, 280, 20);

        result_txt = new JLabel(result_text);
        add(result_txt);
        result_txt.setBounds(10, 160, 480, 20);

        back = new JButton("BACK");
        add(back);
        back.setBounds(150, 220, 100, 20);
        back.addActionListener(this);

        setLocationRelativeTo(null); // center the frame
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        dispose(); // Close the ResultFrame
    }
}