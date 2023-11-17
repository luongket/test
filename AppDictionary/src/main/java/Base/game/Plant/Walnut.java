package Base.game.Plant;

import Base.game.Zombie.Basic_Zombie;
import javafx.scene.image.Image;

import java.util.List;

public class Walnut extends Plant{
    private double MaxHp;
    public Walnut(int width, int height, Point center, double price, double hp, String name, int Lane) {
        super(width, height, center, price, hp, name, Lane);
        this.MaxHp=hp;
    }
    @Override
    public  void ActionPlant(List<Basic_Zombie> zombies){
        if (this.getHp()<MaxHp*2/3&&this.getHp()>=MaxHp/3){

            this.getImg().setImage(new Image("/asset/Game/Walnut/WalnutMidle.gif"));
            this.getImg().setFitHeight(40);this.getImg().setFitWidth(40);
        }else if(this.getHp()<MaxHp/3){
            this.getImg().setImage(new Image("/asset/Game/Walnut/WalnutLow.gif"));
            this.getImg().setFitHeight(40);this.getImg().setFitWidth(40);

        }

    }

}
