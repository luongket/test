package Base.game.Bullet;

import Base.game.Bullet.Bullet;
import Base.game.Plant.Point;
import Base.game.Zombie.Basic_Zombie;
import javafx.scene.layout.Pane;

public class IceBullet extends Bullet {

    public IceBullet(float damage, float speed, int lane, Pane pane, Point position, String name, int width, int height) {
        super(damage, speed, lane, pane, position, name,width,height);
    }
    @Override
    public boolean Collision(Basic_Zombie zombie){
        if(falg&&((zombie.getImage().getLayoutX()-this.getImage().getLayoutX())<=-15)&&
                (zombie.getImage().getLayoutX()-this.getImage().getLayoutX())>-50&&this.getLane()==zombie.getLane()){
            CollisionZombie();
            zombie.heat--;
            zombie.setHp(zombie.getHp()-this.getDamage());
            return true;
        }
        return false;
    }
}
