package GamePlane.Play;

public class BigPlane extends AirObject{
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
