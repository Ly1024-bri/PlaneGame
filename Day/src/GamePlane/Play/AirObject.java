package GamePlane.Play;

import java.awt.image.BufferedImage;
//飞行物父类
public abstract class AirObject {
    //定义x坐标与y坐标，物体图片，图片的长和高
    private BufferedImage img;
    private int width;
    private int height;
    private int x;
    private int y;


    public AirObject(BufferedImage img,int x,int y){
        this.img = img;
        this.width = img.getWidth();
        this.height = img.getHeight();
        this.x = x;
        this.y = y;

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }

//创建移动方法
    abstract void move();
}
