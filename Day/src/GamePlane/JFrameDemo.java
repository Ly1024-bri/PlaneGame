package GamePlane;

import javax.swing.*;

public class JFrameDemo {
    public static void main(String[] args) {
        JFrame j = new JFrame();
        j.setTitle("飞机大战");

        j.setSize(400,650);

        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setAlwaysOnTop(true);
        j.setLocationRelativeTo(null);

        j.setUndecorated(true);
        j.setVisible(true);
    }
}
