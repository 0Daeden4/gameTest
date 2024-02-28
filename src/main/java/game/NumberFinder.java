package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class NumberFinder {
    public JPanel numberFinderPanel;
    private JPasswordField enterYourNumberHerePasswordField;
    private JButton higher;
    private JButton lower;
    private JLabel numberEnter;
    private JLabel higherLower;
    private JPanel numberChange;
    private JButton numberFound;
    private JPanel numberFoundPanel;
    private JLabel numberFoundField;
    private JButton playAgainButton;
    private JButton exitGameButton;
    private int lowerBound;
    private int higherBound;
    private int currentGuess;
    private String higherLowerString = "Is your number higher or lower than ";


    public NumberFinder() {
        numberChange.setVisible(false);
        numberFoundPanel.setVisible(false);
        enterYourNumberHerePasswordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userString = Arrays.toString(enterYourNumberHerePasswordField.getPassword());
                userString= userString.replaceAll("\\p{Punct}", "");
                userString = userString.replaceAll(" ","");
                if(!userString.matches("[0-9]+$")){
                    numberEnter.setText("Invalid Input!");
                    return;
                }
                int userInput = Integer.parseInt(userString);
                lowerBound = Math.max(userInput/10,1)*80;
                if (userInput<0) lowerBound = -lowerBound;
                higherBound = lowerBound;
                currentGuess =0;
                updateGuess(currentGuess);
                enterYourNumberHerePasswordField.setVisible(false);
                numberEnter.setVisible(false);
                numberChange.setVisible(true);
            }
        });
        higher.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lowerBound = currentGuess;
                currentGuess += (higherBound-currentGuess)/2;
                if(Math.abs(Math.abs(higherBound)-Math.abs(lowerBound)) ==2) numberFoundEvent();
                updateGuess(currentGuess);
            }
        });
        lower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                higherBound = currentGuess;
                if(currentGuess>0)currentGuess -= (currentGuess-lowerBound)/2;
                else currentGuess = (currentGuess-(lowerBound<0?-lowerBound:lowerBound))/2;
                if(Math.abs(Math.abs(higherBound)-Math.abs(lowerBound)) ==2) numberFoundEvent();
                updateGuess(currentGuess);
            }
        });
        numberFound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberFoundEvent();
            }
        });
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                enterYourNumberHerePasswordField.setText("");
                enterYourNumberHerePasswordField.setVisible(true);
                numberEnter.setVisible(true);
                numberFoundPanel.setVisible(false);
            }
        });
        exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComponent comp = (JComponent) e.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
            }
        });
    }
    private void updateGuess(int guess){
        String higherLowerNumber = higherLowerString + guess+ "?";
        higherLower.setText(higherLowerNumber);
    }
    private void numberFoundEvent(){
        numberChange.setVisible(false);
        numberFoundField.setText("Yay! I found that your number was "+currentGuess+ "!");
        numberFoundPanel.setVisible(true);
    }
}
