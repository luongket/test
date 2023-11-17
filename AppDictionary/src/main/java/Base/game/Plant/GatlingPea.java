package Base.game.Plant;

import Base.game.Bullet.Bullet;
import Base.game.Zombie.Basic_Zombie;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class GatlingPea extends Plant {
    public List<Bullet> bullets=new ArrayList<Bullet>();

    public GatlingPea(int width, int height, Point point, double price, double hp, String name, int Lane, float Damage, float Speed, Pane pane, String BulletName) {
        super(width, height, point, price, hp, name, Lane);
        point.setPointY(center.getPointY()-5-2.5);
        for (int i=0;i<5;i++) {
            if(i%2==0){
                point.setPointY(center.getPointY()-i*5+5+2.5);
                point.setPointX(center.getPointX()-i*10);
            }else {
                point.setPointY(center.getPointY()+i*5);
            }
            Bullet bullet=new Bullet(Damage, Speed,Lane, pane,point,BulletName,20,20);
            bullets.add(bullet);
        }
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
            for (int i = 0; i<bullets.size(); i++){
                for (Basic_Zombie zombie:zombies){
                    bullets.get(i).Collision(zombie);
                }
                bullets.get(i).getImage().setLayoutX(bullets.get(i).getImage().getLayoutX() +bullets.get(i).getSpeed());
                if(bullets.get(i).getImage().getLayoutX()>900){
                    bullets.get(i).getImage().setLayoutX(0);
                }
                if(bullets.get(i).getPosition().getPointX()>bullets.get(i).getImage().getLayoutX()){
                    bullets.get(i).falg=false;
                }else {
                    bullets.get(i).falg=true;

                }
                bullets.get(i).getImage().setVisible(bullets.get(i).falg);

            }
        }
        else {
            for (int i = 0; i<bullets.size();i++){
                bullets.get(i).CollisionZombie();

            }
        }


    }
    @Override
    public void Reset() {
        super.Reset();
        for (int i = 0; i<bullets.size();i++){
            bullets.get(i).getImage().setVisible(false);
        }
    }
}

