package GamePlane.Play;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;
//继承JPanel画板
public class PlayGame  extends JPanel{

    public static BufferedImage hero0;
    public static BufferedImage hero1;
    public static BufferedImage start;
    public static BufferedImage bee;
    public static BufferedImage airplane;
    public static BufferedImage bigplane;
    public static BufferedImage bullet;
    public static BufferedImage gameover;
    public static BufferedImage pause;
    public static BufferedImage bg;
    public static BufferedImage ae1;
    public static BufferedImage be0;
    public static BufferedImage be1;
    public static BufferedImage be2;
    public static BufferedImage be3;
    public static BufferedImage ae3;
    public static BufferedImage ae0;
    public static BufferedImage ae2;
    public static BufferedImage ee0;
    public static BufferedImage ee1;
    public static BufferedImage ee2;
    public static BufferedImage ee3;
    //定义游戏窗体大小
    public static final int WIDTH = 400;
    public static final int HEIGHT = 650;
    //定义游戏状态
    public static final int START = 0;
    public static final int RUNING = 1;
    public static final int PAUSE = 2;
    public static final int GAME_OVER = 3;
    //静态代码块提前加载图片文件且只用加载一次
    static {
        try {
            hero0 = ImageIO.read(PlayGame.class.getResourceAsStream("pic/hero0.png"));
            hero1 = ImageIO.read(PlayGame.class.getResourceAsStream("pic/hero1.png"));
            bg = ImageIO.read(PlayGame.class.getResourceAsStream("pic/background.png"));
            bee = ImageIO.read(PlayGame.class.getResourceAsStream("pic/bee.png"));
            airplane = ImageIO.read(PlayGame.class.getResourceAsStream("pic/airplane.png"));
            bigplane = ImageIO.read(PlayGame.class.getResourceAsStream("pic/bigplane.png"));
            bullet = ImageIO.read(PlayGame.class.getResourceAsStream("pic/bullet.png"));
            start = ImageIO.read(PlayGame.class.getResourceAsStream("pic/start.png"));
            pause = ImageIO.read(PlayGame.class.getResourceAsStream("pic/pause.png"));
            gameover = ImageIO.read(PlayGame.class.getResourceAsStream("pic/gameover.png"));
            ae1 = ImageIO.read(PlayGame.class.getResourceAsStream("pic/airplane_ember1.png"));
            ae0 = ImageIO.read(PlayGame.class.getResourceAsStream("pic/airplane_ember0.png"));
            ae2 = ImageIO.read(PlayGame.class.getResourceAsStream("pic/airplane_ember2.png"));
            ae3 = ImageIO.read(PlayGame.class.getResourceAsStream("pic/airplane_ember3.png"));
            be0 = ImageIO.read(PlayGame.class.getResourceAsStream("pic/bee_ember0.png"));
            be1 = ImageIO.read(PlayGame.class.getResourceAsStream("pic/bee_ember1.png"));
            be2 = ImageIO.read(PlayGame.class.getResourceAsStream("pic/bee_ember2.png"));
            be3 = ImageIO.read(PlayGame.class.getResourceAsStream("pic/bee_ember3.png"));
            ee0 = ImageIO.read(PlayGame.class.getResourceAsStream("pic/extraplane_ember0.png"));
            ee1 = ImageIO.read(PlayGame.class.getResourceAsStream("pic/extraplane_ember1.png"));
            ee2 = ImageIO.read(PlayGame.class.getResourceAsStream("pic/extraplane_ember2.png"));
            ee3 = ImageIO.read(PlayGame.class.getResourceAsStream("pic/extraplane_ember3.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //创建其他飞行物与子弹集合
    private ArrayList<AirObject> flys;
    private ArrayList<Bullet> bullets;
    private int num = START;
    //无参构造初始化集合
    private PlayGame(){
        flys = new ArrayList<>();
        bullets = new ArrayList<>();
    }
    //创建主机对象
    private Hero h = new Hero();
    //开始游戏
    public void action(){
        java.util.Timer timer = new java.util.Timer();
            timer.schedule( new TimerTask() {
                @Override
                public void run() {
                    if (num == RUNING) {
                        //创建其他飞行物
                        someFly();
                        //主机移动
                        h.move();
                        //隐藏鼠标指针
                        Image imageCursor =
                                Toolkit.getDefaultToolkit().getImage("");
                        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                                imageCursor,  new Point(h.getX(), h.getY()), "cursor"));
                        //其他飞行物移动
                        step();
                        //子弹移动
                        bulletsStep();
                        //判断飞行物越界
                        boundsAction();
                        //判断子弹越界
                        shootBounds();
                        //发射子弹
                        shootAction();
                        //飞行物与主机撞击
                        boomWithPlane();
                        //子弹打中飞行物
                        shootAir();
                    }
                    repaint();
            }
        },2000,10);
            //调用鼠标监听器
        MouseAdapter adapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (num == START){
                    num = RUNING;
                }else if(num == GAME_OVER){
                    num = START;
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(num == PAUSE){
                    num = RUNING;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (num == RUNING){
                    num = PAUSE;
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (num == RUNING) {
                    int mouse_x = e.getX();
                    int mouse_y = e.getY();
                    h.setX(mouse_x - h.getWidth() / 2);
                    h.setY(mouse_y - h.getHeight() / 2);
                }
                repaint();
            }
        };
        this.addMouseListener(adapter);
        this.addMouseMotionListener(adapter);
    }
    //飞行物与主机撞击
    private void boomWithPlane(){
        for (int i = 0; i< flys.size();i++){
            AirObject fly = flys.get(i);
            if (fly.getY()+fly.getHeight()>=h.getY()&&fly.getY()+fly.getHeight()<=h.getY()+h.getHeight() && fly.getX()>=h.getX() && fly.getX()<=h.getX()+h.getWidth() && fly.getX() +fly.getWidth()>=h.getX() && (fly.getX() + fly.getWidth())<=(h.getX()+h.getWidth())) {
                //flysBoom();
                    flys.remove(i);
                    i--;
                    if (fly instanceof Bee){
                        h.aa();
                    }else {
                        heroBlood();
                    }

            }
        }
    }
    //设置英雄机初始化血量与大敌机初始血量
    private static int life = 3;
    private static int bigPlaneLife = 3;
    //英雄机血量
    private void heroBlood(){
        h.setLife(life);
        life = life-1;
        if (h.getLife()==1){
            num = GAME_OVER;
        }
    }
    private void flysBoom(){

    }
    //子弹打中飞行物
    private void shootAir(){
        for (int i = 0;i<flys.size();i++){
            AirObject fly = flys.get(i);
            for (int j = 0;j<bullets.size();j++){
                Bullet bullet = bullets.get(j);
                    if ( bullet.getX()>=fly.getX()&&bullet.getX()<=(fly.getX()+fly.getWidth()) && bullet.getY() <= (fly.getY()+fly.getHeight())&&bullet.getY()>=fly.getY()) {
                        bullets.remove(j);
                        j--;
                        h.setSroce(score);
                        if (fly instanceof Bee){
                            flys.remove(i);
                            i--;
                            int ran = (int)(Math.random()*2+1);
                            if (ran == 1){
                                if (life<3){
                                    life+=1;
                                }
                            }else{
                                h.aa();
                            }
                        }else if(fly instanceof AirPlane){
                            flys.remove(i);
                            score = score+100;
                            i--;
                        }else if(fly instanceof BigPlane){
                            ((BigPlane) fly).setPlanelife(bigPlaneLife);
                            bigPlaneLife--;
                            ((BigPlane) fly).getPlanelife();
                            if (((BigPlane) fly).getPlanelife()==0){
                                flys.remove(i);
                                score = score +300;
                                bigPlaneLife=3;
                                int ran = (int)(Math.random()*2+1);
                                if (ran == 1){
                                    if (life<3){
                                        life+=1;
                                    }
                                }else{
                                   h.aa();
                                }
                                i--;
                            }
                        }
                        h.getSroce();
                    }
            }
        }
    }
    //发射子弹
    private int i1 = 0;
    private void shootAction() {

        i1++;
        if (i1%10==0) {
            Bullet[] b = h.shoot();
            for (int i = 0; i < b.length; i++) {
                bullets.add(b[i]);
            }
        }
    }
    //子弹移动
    private void bulletsStep(){
        for (int i = 0; i< bullets.size();i++){
            Bullet b = bullets.get(i);
            b.move();
        }
    }
    //判断飞行物越界
    private void boundsAction(){
        for (int i = 0; i< flys.size();i++){
            AirObject fly = flys.get(i);
            if (fly.getY()>=PlayGame.HEIGHT){
                flys.remove(i);
                i--;
            }
        }
    }
    //判断子弹越界
    private void shootBounds(){
        for (int i = 0; i< bullets.size();i++){
            Bullet b= bullets.get(i);
            if (b.getY()<=0){
                bullets.remove(i);
                i--;
            }
        }
    }
    //其他飞行物移动
    private void step(){
        for (int i = 0;i<flys.size();i++){
            AirObject fly = flys.get(i);
            fly.move();
        }
    }
    private void Fly(Graphics g){
        for (int i = 0;i<flys.size();i++){
            AirObject fly = flys.get(i);
            g.drawImage(fly.getImg(),fly.getX(), fly.getY(),this);
        }
    }
    private void paintBullets(Graphics g){
        for (int i = 0;i<bullets.size();i++){
            Bullet b = bullets.get(i);
            g.drawImage(b.getImg(),b.getX(), b.getY(),this);

        }
    }
    //创建其他飞行物
    private int flyIndex = 0;
    private void someFly(){
        flyIndex++;
        if (flyIndex % 40 == 0){
            AirObject fly;
            int ran = (int)(Math.random()*40);

            if (ran>=35){
                fly = new BigPlane();

            }else if(ran==1||ran==5){
                fly = new Bee();
            }else{
                fly = new AirPlane();
            }
            flys.add(fly);
        }

    }
    //初始化得分
    public static int score;
    @Override
    public void paint(Graphics g){
        super.paint(g);
        g.drawImage(bg,0,0,null);
        Font font = new Font("微软雅黑",Font.BOLD,20);
        g.setFont(font);
        g.drawString("score:"+score,30,30);
        g.drawString("life:"+life,30,60);
        g.drawImage(h.getImg(),h.getX(),h.getY(),this);
        Fly(g);
        //一局游戏结束后从新开始游戏
        if (num == START){
            score=0;
            life=3;
            bigPlaneLife=3;
            h.setX(130);
            h.setY(400);
            g.drawImage(start,0,0,null);
            g.drawImage(h.getImg(),h.getX(),h.getY(),this);

        }else if(num == PAUSE){
            g.drawImage(pause,-15,0,null);
        }else if(num == GAME_OVER){
            g.drawImage(gameover,0,0,null);

        }else if (num == RUNING){
            paintBullets(g);
        }
    }

    public static void main(String[] args) {
        JFrame a = new JFrame("飞机大战");
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.setSize(WIDTH,HEIGHT);
        a.setLocationRelativeTo(null);
       // a.setUndecorated(true);
        PlayGame game = new PlayGame();
        a.add(game);
        game.action();
        a.setVisible(true);
    }
}