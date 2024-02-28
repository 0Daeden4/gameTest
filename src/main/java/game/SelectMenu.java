package game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectMenu {
    public JPanel selectMenu;
    private JButton select0;
    private JButton select1;

    public SelectMenu() {
        JFrameHelper jHelper = new JFrameHelper();
        select0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame numberGuesser =jHelper.jFrameCreator("Number Guesser",
                        new NumberFinder().numberFinderPanel);
                numberGuesser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                numberGuesser.pack();
                numberGuesser.setVisible(true);
            }
        });
        select1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame passwordGenerator = jHelper.jFrameCreator("Password Generator",
                        new PasswordGenerator().mainPassowrd);
                passwordGenerator.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                passwordGenerator.pack();
                passwordGenerator.setVisible(true);
            }
        });
    }
}
