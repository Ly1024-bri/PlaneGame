package GamePlane.Play;

import java.awt.image.BufferedImage;
//主机类
public class Hero extends AirObject {
    //定义血量
    private int life;

    private int a = 0;
    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getSroce() {
        return sroce;
    }

    public void setSroce(int sroce) {
        this.sroce = sroce;
    }
    //定义分数
    private int sroce;
    public Hero(){
        super(PlayGame.hero0,130,400);
    }
    //切换主机图片
    private BufferedImage[] heroImg ={PlayGame.hero0,PlayGame.hero1};
    private int count;
    @Override
    void move() {
        count++;
        setImg(heroImg[count%2]);
    }
    //定义升级子弹的次数
    public void aa(){
        a = 20;
    }
    //定义子弹
    public Bullet[] shoot(){
        Bullet[] bullets;
        if (a ==0) {
            bullets = new Bullet[1];

            bullets[0] = new Bullet(this.getX() + (this.getWidth() / 2), this.getY());
        }else{
            bullets = new Bullet[2];
            bullets[0] = new Bullet(this.getX()+(this.getWidth()/4),this.getY());
            bullets[1] = new Bullet(this.getX()+(this.getWidth()*3/4),this.getY());
            a--;
        }
        return bullets;

    }
}
