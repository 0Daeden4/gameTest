package main;

import game.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        //GameWindow gameWindow = new GameWindow();
        JFrameHelper jFrameHelper = new JFrameHelper();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

//        JFrame selectionMenu = jFrameHelper.jFrameCreator("Selection Menu",
//                new SelectMenu().selectMenu);
//        selectionMenu.pack();
//        selectionMenu.setVisible(true);
        JPanel selectionMenu = new SelectMenu().selectMenu;
        JFrame passwordGenerator = jFrameHelper.jFrameCreator("Selection Menu",
                selectionMenu);
        passwordGenerator.pack();
        passwordGenerator.setVisible(true);

    }
}
