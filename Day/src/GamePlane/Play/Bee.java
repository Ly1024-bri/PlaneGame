package GamePlane.Play;
//小蜜蜂类
public class Bee extends AirObject{
    //定义横向速度与纵向速度
    private int x_speed;
    private int y_speed;
    Bee(){
        super(PlayGame.bee,(int)(Math.random()*(PlayGame.WIDTH-PlayGame.bee.getWidth())),-PlayGame.bee.getHeight());
        x_speed = 1;
        y_speed = 2;
    }

    @Override
    void move() {
        setY(getY()+y_speed);
            setX(getX() + x_speed);
        if (getX()==PlayGame.WIDTH-PlayGame.bee.getWidth()){
            x_speed=-1;
        }else if(getX() == 0){
            x_speed=1;
        }
    }
}
