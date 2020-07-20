package GamePlane.Play;

import java.awt.image.BufferedImage;

public class Hero extends AirObject {
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

    private int sroce;
    public Hero(){
        super(PlayGame.hero0,130,400);
    }
    private BufferedImage[] heroImg ={PlayGame.hero0,PlayGame.hero1};
    private int count;
    @Override
    void move() {
        count++;
        setImg(heroImg[count%2]);
    }
    public void aa(){
        a = 20;
    }

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
