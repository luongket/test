package Base.game.Plant;

import Base.game.Bullet.Bullet;
import Base.game.Zombie.Basic_Zombie;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ThreePeater extends Plant{
    public List<Bullet> bullets=new ArrayList<Bullet>();

    public ThreePeater(int width, int height, Point point, double price, double hp, String name, int Lane, float Damage, float Speed, Pane pane,String BulletName) {
        super(width, height, point, price, hp, name, Lane);
        point.setPointY(center.getPointY()-100);
        for (int i=0;i<3;i++) {
            point.setPointY(point.getPointY()+50);
            if(i%2==1){
                point.setPointX(point.getPointX()+20);
            }else {
                point.setPointX(point.getPointX()-20);
            }
            Bullet bullet=new Bullet(Damage, Speed,Lane+i-1, pane,point,BulletName,20,20);
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
                bullets.get(i).falg=false;
                bullets.get(i).getImage().setVisible(bullets.get(i).falg);

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
