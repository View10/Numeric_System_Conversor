import NumericSystems.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI implements ActionListener, KeyListener {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel inputPanel;
    private JPanel outputPanel;

    //Input Area Setup
    private JTextField input;
    private JLabel inputBase;
    private JLabel error;
    private JComboBox<String> inputOptions;
    private JCheckBox isNegativeForBinary;

    private JPanel midPanel;
    private JLabel to;

    //Output Area Setup
    private JTextField output;
    private JLabel outputBase;
    private JComboBox<String> outputOptions;

    public GUI(){
        frame = new JFrame("Numeric System Conversor");
        mainPanel = new JPanel();
        inputPanel = new JPanel();
        outputPanel = new JPanel();

        //frame setup
        frame.setSize(440, 230);
        frame.setLayout(null);
        frame.setFocusable(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.addKeyListener(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //input setup
        input = new JTextField("0");
        input.setBounds(20, 20, 200, 40);
        input.setFont(new Font("Circular Std Medium", Font.PLAIN, 18));
        input.setFocusable(false);

        //error setup
        error = new JLabel("");
        error.setBounds(20, 55, 420, 30);
        error.setFont(new Font("Circular Std Medium", Font.PLAIN, 14));
        error.setForeground(Color.RED);

        //inputBase setup
        inputBase = new JLabel("₂");
        inputBase.setBounds(225, 30, 40, 40);
        inputBase.setFont(new Font("Circular Std Medium", Font.PLAIN, 20));

        //inputOptions setup
        String[] options = {"Decimal", "Binary", "Octal", "Hexadecimal"};
        inputOptions = new JComboBox<>(options);
        inputOptions.setSelectedIndex(0);
        inputOptions.setBounds(250, 20, 150, 40);
        inputOptions.addActionListener(this);

        //isNegativeForBinary setup
        isNegativeForBinary = new JCheckBox("Is it negative?");
        isNegativeForBinary.setBounds(260, 80, 200, 30);
        isNegativeForBinary.addActionListener(this);
        isNegativeForBinary.setFont(new Font("Circular Std Medium", Font.PLAIN, 15));
        isNegativeForBinary.setEnabled(false);
        isNegativeForBinary.setVisible(false);

        //to setup
        to = new JLabel("To");
        to.setBounds(180, 80, 40, 40);
        to.setFont(new Font("Circular Std Medium", Font.PLAIN, 18));

        //output setup
        output = new JTextField("0");
        output.setBounds(20, 130, 200, 40);
        output.setFont(new Font("Circular Std Medium", Font.PLAIN, 18));
        output.setEditable(false);


        //outputBase setup
        outputBase = new JLabel("₁₀");
        outputBase.setBounds(225, 140, 40, 40);
        outputBase.setFont(new Font("Circular Std Medium", Font.PLAIN, 20));

        //outputOptions setup
        outputOptions = new JComboBox<>(options);
        outputOptions.setSelectedIndex(1);
        outputOptions.setBounds(250, 130, 150, 40);
        outputOptions.addActionListener(this);

        //Adding components to frame
        frame.add(input);
        frame.add(error);
        frame.add(inputBase);
        frame.add(inputOptions);
        frame.add(isNegativeForBinary);
        frame.add(to);
        frame.add(output);
        frame.add(outputBase);
        frame.add(outputOptions);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        error.setText("");
        output.setText("");

        JComboBox<String> source = null;

        if (e.getSource() == inputOptions || e.getSource() == outputOptions){
            source = (JComboBox<String>) e.getSource();
        }

        if (source == inputOptions){
            boolean check;
            switch (source.getSelectedIndex()){
                case 0:
                    inputBase.setText("₁₀");
                    check = input.getText().contains("a");

                    if (check){
                        error.setText("Not a decimal!");
                    }

                    break;
                case 1:
                    inputBase.setText("₂");
                    check = Binary.isBinary(input.getText());

                    if (!check){
                        error.setText("Not a binary!");
                    }

                    break;
                case 2:
                    inputBase.setText("₈");
                    check = Octal.isOctal(input.getText());

                    if (check){
                        error.setText("Not an octal!");
                    }

                    break;
                case 3:
                    inputBase.setText("₁₆");
                    check = Hexadecimal.isHexadecimal(input.getText());

                    if (check){
                        error.setText("Not an hexadecimal!");
                    }

                    break;
            }
        }else if (source == outputOptions){
            switch (source.getSelectedIndex()){
                case 0:
                    outputBase.setText("₁₀");
                    break;
                case 1:
                    outputBase.setText("₂");
                    break;
                case 2:
                    outputBase.setText("₈");
                    break;
                case 3:
                    outputBase.setText("₁₆");
                    break;
            }
        }

        if (inputOptions.getSelectedIndex() != 1){
            isNegativeForBinary.setEnabled(false);
            isNegativeForBinary.setVisible(false);
        }else {
            isNegativeForBinary.setVisible(true);
            isNegativeForBinary.setEnabled(true);
        }

        if(inputOptions.getSelectedIndex() == outputOptions.getSelectedIndex()){
            error.setText("Cannot convert to the same numeric system!");
            output.setText("ERROR");
        }
        frame.requestFocusInWindow();
    }

    public void convert(String from, String to){
        switch (from){
            case "Decimal":
                switch (to) {
                    case "Binary" -> output.setText(NumericSystemConversor.decimalToBinary(input.getText()).getValue());
                    case "Octal" -> {
                        System.out.println(output.getText());
                        output.setText(NumericSystemConversor.decimalToOctal(input.getText()).getValue());
                    }
                    case "Hexadecimal" -> output.setText(NumericSystemConversor.decimalToHexadecimal(input.getText()).getValue());
                }
                break;
            case "Binary":
                Binary bin = new Binary(input.getText(), isNegativeForBinary.isSelected());
                switch (to) {
                    case "Decimal" -> output.setText(NumericSystemConversor.binaryToDecimal(bin)+"");
                    case "Octal" -> output.setText(NumericSystemConversor.decimalToOctal(NumericSystemConversor.binaryToDecimal(bin)+"").getValue());
                    case "Hexadecimal" -> output.setText(NumericSystemConversor.decimalToHexadecimal(NumericSystemConversor.binaryToDecimal(bin)+"").getValue());
                }
                break;
            case "Octal":
                Octal oct = new Octal(input.getText());
                switch (to){
                    case "Decimal" -> output.setText(NumericSystemConversor.octalToDecimal(oct)+"");
                    case "Binary" -> output.setText(NumericSystemConversor.decimalToBinary(NumericSystemConversor.octalToDecimal(oct)+"").getValue());
                    case "Hexadecimal" -> output.setText(NumericSystemConversor.decimalToHexadecimal(NumericSystemConversor.octalToDecimal(oct)+"").getValue());
                }
                break;
            case "Hexadecimal":
                Hexadecimal hex = new Hexadecimal(input.getText());
                switch (to){
                    case "Decimal" -> output.setText(NumericSystemConversor.hexadecimalToDecimal(hex)+"");
                    case "Binary" -> output.setText(NumericSystemConversor.decimalToBinary(NumericSystemConversor.hexadecimalToDecimal(hex)+"").getValue());
                    case "Octal" -> output.setText(NumericSystemConversor.decimalToOctal(NumericSystemConversor.hexadecimalToDecimal(hex)+"").getValue());
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_0){
            input.setText(input.getText()+"0");
        }

        if (e.getKeyCode() == KeyEvent.VK_1){
            input.setText(input.getText()+"1");
        }

        if (e.getKeyCode() == KeyEvent.VK_2){
            input.setText(input.getText()+"2");
        }

        if (e.getKeyCode() == KeyEvent.VK_3){
            input.setText(input.getText()+"3");
        }

        if (e.getKeyCode() == KeyEvent.VK_4){
            input.setText(input.getText()+"4");
        }

        if (e.getKeyCode() == KeyEvent.VK_5){
            input.setText(input.getText()+"5");
        }

        if (e.getKeyCode() == KeyEvent.VK_6){
            input.setText(input.getText()+"6");
        }

        if (e.getKeyCode() == KeyEvent.VK_7){
            input.setText(input.getText()+"7");
        }

        if (e.getKeyCode() == KeyEvent.VK_8){
            input.setText(input.getText()+"8");
        }

        if (e.getKeyCode() == KeyEvent.VK_9){
            input.setText(input.getText()+"9");
        }

        if (e.getKeyCode() == KeyEvent.VK_A){
            input.setText(input.getText()+"A");
        }

        if (e.getKeyCode() == KeyEvent.VK_B){
            input.setText(input.getText()+"B");
        }

        if (e.getKeyCode() == KeyEvent.VK_C){
            input.setText(input.getText()+"C");
        }

        if (e.getKeyCode() == KeyEvent.VK_D){
            input.setText(input.getText()+"D");
        }

        if (e.getKeyCode() == KeyEvent.VK_E){
            input.setText(input.getText()+"E");
        }

        if (e.getKeyCode() == KeyEvent.VK_F){
            input.setText(input.getText()+"F");
        }

        if (e.getKeyCode() == KeyEvent.VK_MINUS || e.getKeyCode() == KeyEvent.VK_SUBTRACT){
            if (input.getText().isEmpty()){
                input.setText(input.getText()+"-");
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            StringBuilder t = new StringBuilder(input.getText());

            if (t.isEmpty()){
                t.setLength(1);
                t.setCharAt(0, '0');
            }else {
                t.deleteCharAt(t.length()-1);
            }

            input.setText(String.valueOf(t));
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER){
            error.setText("");
            if (input.getText().isEmpty() || input.getText().equals("-")){
                error.setText("Enter a valid " + inputOptions.getItemAt(inputOptions.getSelectedIndex()) + " number!");
            }else {
                convert((String) inputOptions.getSelectedItem(), (String) outputOptions.getSelectedItem());
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
