package GamePlane.Play;

public class Bullet extends AirObject {
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
