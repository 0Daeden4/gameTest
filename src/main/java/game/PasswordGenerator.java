package game;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PasswordGenerator {
    public JPanel mainPassowrd;
    private JSlider slider1;
    private JTextPane passwordLabel;
    private JLabel yourPasswordLabel;
    private JLabel passwordLengthLabel;
    private JLabel regexSelectionLabel;
    private JLabel excludeLabel;
    private JRadioButton digitsButton;
    private JRadioButton capitalLettersButton;
    private JRadioButton punctuationButton;
    private JRadioButton lettersButton;
    private JRadioButton lowerCaseLettersButton;
    private JRadioButton asciiButton;
    private JTextField exclusionTextField;
    private JTextField passwordLengthTextBox;
    private JButton copyPasswordButton;
    private JLabel copiedToClipboardLabel;
    private String passwordLength = passwordLengthLabel.getText();
    private String regex ="0";
    private String password ="";
    private boolean nothingSelected = true;
    private String excluded="";
    public PasswordGenerator() {
        JFrameHelper jFrameHelper = new JFrameHelper();
        slider1.setMaximum(256);
        passwordLengthTextBox.setText(String.valueOf(slider1.getValue()));
        passwordLabel.setContentType("text/html");
        passwordLabel.setEditable(false);
        copiedToClipboardLabel.setVisible(false);
        digitsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regexEditor(digitsButton, "[0-9]");
            }
        });

        lettersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regexEditor(lettersButton, "[a-zA-Z]");
            }
        });
        capitalLettersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regexEditor(capitalLettersButton, "[A-Z]");
            }
        });
        lowerCaseLettersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regexEditor(lowerCaseLettersButton, "[a-z]");
            }
        });
        punctuationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regexEditor(punctuationButton, "[\\p{Punct}]");
            }
        });
        asciiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                regexEditor(asciiButton, "[\\p{ASCII}]");
            }
        });
        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                passwordFormatterAndGenerator(regex+excluded);
            }
        });
        exclusionTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(exclusionTextField.getText().isEmpty()) {
                    excluded ="";
                    return;
                }
                excluded = "&&[^"+exclusionTextField.getText()+"]";
                passwordFormatterAndGenerator(regex+excluded);
            }
        });
        passwordLengthTextBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!passwordLengthTextBox.getText().matches("[0-9]+$")){
                    passwordLengthTextBox.setText("Invalid Input!");
                    return;
                }
                slider1.setValue(Integer.parseInt(passwordLengthTextBox.getText()));
                mainPassowrd.repaint();
            }
        });
        copyPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String passwordClipboard = password.replaceAll("<br/>","");
                passwordClipboard = passwordClipboard.replaceAll("<h3>", "");
                passwordClipboard = passwordClipboard.replaceAll("</h3>", "");
                StringSelection stringSelection = new StringSelection(passwordClipboard);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
                copiedToClipboardLabel.setVisible(true);
                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(1000); //Sleep for 1 second
                        } catch (InterruptedException e) {}
                        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                copiedToClipboardLabel.setVisible(false);
                            }
                        });
                    }
                }).start();
            }
        });
    }

    private String passwordFormatterAndGenerator(String regex, int length){
        if(length==0) return "";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(regex);
        sb.insert(0,"[");
        sb.append("&&[^\\p{Cntrl}]&&[^\r\n\t]&&[^(^<br>$&&^<B>$&&^<I>$)]");
        sb.append("]");
        regex = sb.toString();
        String randomString = "";
        int n = 0;
        while(length > 0){
            n = random.nextInt(0,127);
            char c = (char)n;
            if(Character.toString(c).matches(regex)){
                randomString += c;
            }else{
                continue;
            }
            length--;
        }
        return randomString;
    }
    private void passwordFormatterAndGenerator(String regex){
        password="";
        passwordLengthTextBox.setText(String.valueOf(slider1.getValue()));
        int numOfDivisions = Math.max(slider1.getValue()/64,2);
        if(slider1.getValue()%2!=0){
            password+= passwordFormatterAndGenerator(regex,1);
        }
        for(int i =0; i< numOfDivisions; i++){
            password+= passwordFormatterAndGenerator(regex,
                    slider1.getValue()/ numOfDivisions)
                    +(i<numOfDivisions-1?"<br/>":"");
        }
        //password = password.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]+", "");
        passwordLabel.setText("<h3>"+password+"</h3>");
    }
    private void regexEditor (JRadioButton button, String regex){
        if(nothingSelected) this.regex = "";
        nothingSelected= false;
        if(button.isSelected()) this.regex+= regex;
        else {
            if (!this.regex.matches(regex)){
                this.regex =this.regex.replace(regex, "");
                if (this.regex.isEmpty()) {
                    this.regex ="0";
                    nothingSelected = true;
                }
            }
        }
    }
}
