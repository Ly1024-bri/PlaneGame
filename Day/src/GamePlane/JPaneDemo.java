package GamePlane;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JPaneDemo {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        MyPanel p = new MyPanel();
        f.add(p);
        f.setSize(400, 650);
        f.setTitle("飞机大战");
        f.setLocationRelativeTo(null);
        f.setAlwaysOnTop(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

    }
}
    class MyPanel extends JPanel{
        @Override
        public void paint(Graphics g){
            super.paint(g);

            Font font = new Font("微软雅黑",Font.BOLD,20);
            g.setFont(font);

            g.drawString("HelloWorld",20,50);
            paintAirPlane(g);
        }

    private void paintAirPlane(Graphics g){
        BufferedImage img = null;

        g.drawImage(img,100,200,null);

    }

}
