package GamePlane.Play;

public class AirPlane extends AirObject{

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
