package game;

import javax.swing.*;
import java.awt.*;

public class JFrameHelper {
    public JFrame jFrameCreator(String name, JPanel panel){
        JFrame jFrame = new JFrame(name);
        jFrame.setContentPane(panel);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        p.setLocation(p.getX()-(double)panel.getPreferredSize().getWidth()/2,
                p.getY()-(double)panel.getPreferredSize().getHeight()/2);
        jFrame.setLocation(p);
        return jFrame;
        //the frame needs to be packed and set to visible after using this method
    }
    public void jFrameReposition(JFrame frame, JPanel panel){
        Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        p.setLocation(p.getX()-(double)panel.getPreferredSize().getWidth()/2,
                p.getY()-(double)panel.getPreferredSize().getHeight()/2);
        frame.setLocation(p);
    }
}
