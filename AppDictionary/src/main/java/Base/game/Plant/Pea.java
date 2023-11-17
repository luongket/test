package Base.game.Plant;

import Base.game.Bullet.Bullet;
import Base.game.Zombie.Basic_Zombie;

import java.util.List;

public class Pea extends Plant {
    public Bullet bullet;

    public Pea(int width, int height, Point center,
               double hp, String name, int lane, Bullet bulletNew) {
        super(width, height, center, 50, hp, name,lane);
        bullet=bulletNew;


    }


    public boolean CanAction(List<Basic_Zombie> zombies){
        for(Basic_Zombie zombie : zombies){
            if(zombie.getLane()==this.Lane&&zombie.getImage().getLayoutX()>this.getImg().getLayoutX()+150){
                return true;
            }
        }
        return false;
    }
    @Override
    public void ActionPlant(List<Basic_Zombie> zombies){
        if(CanAction(zombies)){
            bullet.getImage().setLayoutX(bullet.getImage().getLayoutX() +bullet.getSpeed());
            if(bullet.getImage().getLayoutX()>900){
                bullet.getImage().setLayoutX(0);
            }
            if(bullet.getPosition().getPointX()>bullet.getImage().getLayoutX()){
                bullet.falg=false;
            }else {
                bullet.falg=true;

            }
            bullet.getImage().setVisible(bullet.falg);
            for (int i=0; i<zombies.size(); i++) {
                bullet.Collision(zombies.get(i));
                System.out.println("ok");
            }
        }
        else {
            bullet.falg=false;
            bullet.getImage().setVisible(bullet.falg);

        }


    }
    @Override
    public void Reset() {
        super.Reset();
        this.bullet.getImage().setVisible(false);
    }
}
