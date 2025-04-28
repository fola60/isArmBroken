import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener {

    JPanel panel1, panel2;
    JComboBox comboBox1, comboBox2, comboBox3, comboBox4;
    JLabel label1, label2, label3, label4;
    JButton button1;
    BayesClassifier bayesClassifier;

    public GUI(ArrayList<ArrayList<Integer>> data) {
        super();
        setSize(1000, 600);
        setLayout(new GridLayout(10, 1));


        bayesClassifier = new BayesClassifier(data, 4);
        bayesClassifier.fit();

        panel1 = new JPanel();
        panel2 = new JPanel();

        String[] items1 = {"HighImpact", "LowImpact"};
        String[] items2 = {"Bent", "Extended"};
        String[] items3 = {"Soft", "Hard"};
        String[] items4 = {"Yes", "No"};

        comboBox1 = new JComboBox(items1);
        comboBox2 = new JComboBox(items2);
        comboBox3 = new JComboBox(items3);
        comboBox4 = new JComboBox(items4);

        label1 = new JLabel("Impact: ");
        label2 = new JLabel("Arm extension: ");
        label3 = new JLabel("Impact surface type: ");
        label4 = new JLabel("Protective Gear: ");

        button1 = new JButton("Predict");
        button1.addActionListener(this);

        panel1.setLayout(new FlowLayout());

        panel1.add(label1);
        panel1.add(comboBox1);
        panel1.add(label2);
        panel1.add(comboBox2);
        panel1.add(label3);
        panel1.add(comboBox3);
        panel1.add(label4);
        panel1.add(comboBox4);

        panel2.add(button1);

        add(panel1);
        add(panel2);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            int key = 0;

            if (comboBox1.getSelectedItem() == "HighImpact") {
                key += 1;
            }

            if (comboBox2.getSelectedItem() == "Extended") {
                key += 10;
            }

            if (comboBox3.getSelectedItem() == "Hard") {
                key += 100;
            }

            if (comboBox4.getSelectedItem() == "No") {
                key += 1000;
            }

            ArrayList<Integer> prediction = bayesClassifier.predict(key);


            if (prediction.get(0) == 1) {
                JOptionPane.showMessageDialog(this, "Your arm has a " + prediction.get(1) + "% chance of being broken.");
            } else {
                JOptionPane.showMessageDialog(this, "Your arm has a " + prediction.get(1) + "% chance of not being broken.");
            }




        }

    }
}
