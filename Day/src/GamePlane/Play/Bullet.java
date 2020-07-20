package GamePlane.Play;
//子弹类
public class Bullet extends AirObject {
    //定义速度
    private int speed;

    public Bullet(int x, int y){

        super(PlayGame.bullet,x,y);
        speed = 3;
    }
    @Override
    public void move() {

        setY(getY()-speed);

    }
}
