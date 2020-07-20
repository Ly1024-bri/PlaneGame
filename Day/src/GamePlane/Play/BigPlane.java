package GamePlane.Play;
//大飞机类
public class BigPlane extends AirObject{
    //定义飞机血量与速度

    private int planelife;
    private int speed;

    public int getPlanelife() {
        return planelife;
    }

    public void setPlanelife(int planelife) {
        this.planelife = planelife;
    }

    public BigPlane(){
        super(PlayGame.bigplane,(int)(Math.random()*(PlayGame.WIDTH-PlayGame.bigplane.getWidth())),-PlayGame.bigplane.getHeight());
        speed = 1;
    }

    @Override
    void move() {
        setY(getY()+speed);
    }
}
