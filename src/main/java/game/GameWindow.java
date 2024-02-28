package game;

import javax.swing.*;
import java.awt.*;

public class GameWindow { //under Construction
    private JFrame jframe;
    public GameWindow(){
        jframe= new JFrame("Test Game");
        jframe.setVisible(true);
        jframe.setSize(600, 400);

        //Centers the window
        Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        p.setLocation(p.getX()-(double)jframe.getWidth()/2, p.getY()-(double)jframe.getHeight()/2);
        jframe.setLocation(p);
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
    }
}

