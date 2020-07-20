package GamePlane.Play;
//小飞机类
public class AirPlane extends AirObject{
//定义速度
    private int speed;
    public AirPlane(){
        super(PlayGame.airplane,(int)(Math.random()*(PlayGame.WIDTH-PlayGame.airplane.getWidth())),-PlayGame.airplane.getHeight());
        speed = 2;
    }
    @Override
    void move() {
        setY(getY()+speed);
    }
}
