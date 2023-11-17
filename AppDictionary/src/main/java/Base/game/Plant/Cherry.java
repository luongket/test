package Base.game.Plant;

import Base.game.Zombie.Basic_Zombie;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.List;

public class Cherry extends Plant{
    Media explode = new Media(getClass().getResource("/asset/Sound/jalapeno.wav").toString());
    MediaPlayer mediaPlayer = new MediaPlayer(explode);
    public Cherry(int width, int height, Point center, double price, double hp, String name, int Lane) {
        super(width, height, center, price, hp, name, Lane);

    }
    public  void ActionPlant(List<Basic_Zombie> zombies){
        synchronized (zombies){
        Thread yourThread = new Thread(() -> {
            // Code của luồng của bạn
            try {
                mediaPlayer.setAutoPlay(true);
                mediaPlayer.play();
                Thread.sleep(700);
                String path="/asset/Game/Cherry/boom.gif";
                if(!path.equals(this.getPath())){
                    this.getImg().setImage(new Image(path));
                    this.getImg().setFitWidth(100);
                    this.getImg().setFitHeight(100);
                    this.getImg().setLayoutX(center.getPointX());
                    this.getImg().setLayoutY(center.getPointY());
                    this.setPath(path);
                }
                Thread.sleep(700);
                // Code tiếp theo sau khi tạm dừng
                for (Basic_Zombie zombie : zombies){
                    if(((zombie.getImage().getLayoutX()-this.getCenter().getPointX())<=-15)&&
                            (zombie.getImage().getLayoutX()-this.getCenter().getPointX())>-50&&
                            (this.Lane==zombie.getLane()||this.Lane==zombie.getLane()+1||this.Lane==zombie.getLane()-1)){
                            zombie.setHp(0);

                    }
                }
                this.setHp(0);
            } catch (InterruptedException e) {
                // Xử lý ngoại lệ nếu cần
            }

        });
        yourThread.start();

        }
    }
}
