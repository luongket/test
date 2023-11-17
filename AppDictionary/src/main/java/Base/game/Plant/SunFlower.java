package Base.game.Plant;

import Base.game.Bullet.Sun;
import javafx.scene.layout.Pane;

public class SunFlower extends Plant{
    public Sun sunrise;
    public SunFlower(int width, int height, Point center,
                     double price, double hp, String name, int Lane,Pane pane) {
        super(width, height, center, price, hp, name, Lane);
        sunrise=new Sun(center,pane);
    }
    @Override
    public void ActionPlant(){
        sunrise.moveSun();
    }

}
