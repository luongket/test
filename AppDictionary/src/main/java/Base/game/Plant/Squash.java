package Base.game.Plant;

import Base.game.Zombie.Basic_Zombie;
import javafx.scene.image.Image;

import java.util.List;

public class Squash extends Plant{
    public Squash(int width, int height, Point center, double price, double hp, String name, int Lane) {
        super(width, height, center, price, hp, name, Lane);
    }
    @Override
    public void ActionPlant(List<Basic_Zombie> zombies){
        for (Basic_Zombie zombie : zombies){
            Thread yourThread = new Thread(() -> {
                // Code của luồng của bạn
                try {
                    Thread.sleep(700); // Tạm dừng luồng này trong 2 giây
                    // Code tiếp theo sau khi tạm dừng
                    this.setHp(0);

                } catch (InterruptedException e) {
                    // Xử lý ngoại lệ nếu cần
                }
            });
            if(((zombie.getImage().getLayoutX()-this.getCenter().getPointX())<=-15)&&
                    (zombie.getImage().getLayoutX()-this.getCenter().getPointX())>-50&&this.Lane==zombie.getLane()){
                String path="/asset/Game/Squash/SquashAttack.gif";
                if(!path.equals(this.getPath())){
                    this.getImg().setImage(new Image(path));
                    this.getImg().setFitWidth(60);
                    this.getImg().setFitHeight(60);
                    this.getImg().setLayoutX(center.getPointX());
                    this.getImg().setLayoutY(center.getPointY());
                    this.setPath(path);
                }
                yourThread.start();
                zombie.setHp(0);
            }
        }
    }
}
