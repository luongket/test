package Base.game.Plant;

import Base.game.Zombie.Basic_Zombie;
import javafx.scene.image.Image;

import java.util.List;

public class IceShroom extends Plant{
    public IceShroom(int width, int height, Point center, double price, double hp, String name, int Lane) {
        super(width, height, center, price, hp, name, Lane);
    }
    public void ActionPlant(List<Basic_Zombie> zombies){
        Thread yourThread = new Thread(() -> {
            // Code của luồng của bạn


            try {
                Thread.sleep(700);
                String path="/asset/Game/DoomShroom/DroomBoom.gif";
                if(!path.equals(this.getPath())){
                    this.getImg().setFitWidth(100);
                    this.getImg().setFitHeight(100);
                    this.getImg().setLayoutX(center.getPointY()-30);
                    this.getImg().setImage(new Image(path));
                    this.setPath(path);
                }
                for (Basic_Zombie zombie1 : zombies){
                    zombie1.ZombieNotMove();
                }
                Thread.sleep(600); // Tạm dừng luồng này trong 2 giây
                // Code tiếp theo sau khi tạm dừng
                this.hp=0;


            } catch (InterruptedException e) {
                // Xử lý ngoại lệ nếu cần
            }
        });
        yourThread.start();
    }
}
