package Base.game.Plant;

import Base.game.Zombie.Basic_Zombie;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.List;

public class DroomShroom extends Plant {
    public DroomShroom(int width, int height, Point center, double price, double hp, String name, int Lane) {
        super(width, height, center, price, hp, name, Lane);
    }
    public void ActionPlant(List<Basic_Zombie> zombies) {
        synchronized (zombies) {
            for (Basic_Zombie zombie : zombies) {
                Thread yourThread = new Thread(() -> {
                    // Code của luồng của bạn
                    String path = "/asset/Game/DoomShroom/DroomBoom.gif";
                    if (!path.equals(this.getPath())) {
                        this.getImg().setFitWidth(100);
                        this.getImg().setFitHeight(100);
                        this.getImg().setLayoutX(center.getPointY() - 30);
                        this.getImg().setImage(new Image(path));
                        this.setPath(path);
                    }

                    for (Basic_Zombie zombie1 : zombies) {
                        if (((zombie1.getImage().getLayoutX() - this.getCenter().getPointX()) <= 6) &&
                                (zombie1.getImage().getLayoutX() - this.getCenter().getPointX()) > -50) {

                            zombie1.setHp(0);

                        }
                    }
                    Media explode = new Media(getClass().getResource("/asset/Sound/jalapeno.wav").toString());
                    MediaPlayer mediaPlayer = new MediaPlayer(explode);
                    mediaPlayer.setAutoPlay(true);
                    mediaPlayer.play();
                    try {
                        Thread.sleep(600); // Tạm dừng luồng này trong 2 giây
                        // Code tiếp theo sau khi tạm dừng

                        this.setHp(0);

                    } catch (InterruptedException e) {
                        // Xử lý ngoại lệ nếu cần
                    }
                });
                if (((zombie.getImage().getLayoutX() - this.getCenter().getPointX()) <= -15) &&
                        (zombie.getImage().getLayoutX() - this.getCenter().getPointX()) > -50 && this.Lane == zombie.getLane()) {
                    yourThread.start();
                    return;
                }
            }
        }
    }
}
