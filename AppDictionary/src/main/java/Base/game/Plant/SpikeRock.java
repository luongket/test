package Base.game.Plant;

import Base.game.Zombie.Basic_Zombie;
import javafx.scene.image.Image;

import java.util.List;

public class SpikeRock extends Plant{
    private float damage;

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damge) {
        this.damage = damge;
    }

    public SpikeRock(int width, int height, Point center, double price, double hp, String name, int Lane,float damage) {
        super(width, height, center, price, hp, name, Lane);
        this.damage = damage;
    }
    @Override
    public  void ActionPlant(List<Basic_Zombie> zombies){
        for(Basic_Zombie zombie : zombies){
            if(((zombie.getImage().getLayoutX()-this.getImg().getLayoutX()-150)<=-15)&&
                    (zombie.getImage().getLayoutX()-this.getImg().getLayoutX()-150)>-50&&this.Lane==zombie.getLane()){
                System.out.println((zombie.getImage().getLayoutX()-this.getImg().getLayoutX()));
                zombie.setHp(zombie.getHp()-this.getDamage());
            }
        }

    }
}
