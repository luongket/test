package Base.game.Plant;

import Base.game.Zombie.Basic_Zombie;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.List;

public class Chilli extends Plant{
    Media explode = new Media(getClass().getResource("/asset/Sound/jalapeno.wav").toString());
    MediaPlayer mediaPlayer = new MediaPlayer(explode);
    public Chilli(int width, int height, Point center, double price, double hp, String name, int Lane) {
        super(width, height, center, price, hp, name, Lane);


    }
    public  void ActionPlant(List<Basic_Zombie> zombies){
        synchronized (zombies){
        Thread yourThread = new Thread(() -> {
            // Code của luồng của bạn
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.play();
            try {

                Thread.sleep(700);
                String path="/asset/Game/chilli/chilliAttack.gif";
                if(!path.equals(this.getPath())){
                    this.getImg().setFitWidth(690);
                    this.getImg().setFitHeight(50);
                    this.getImg().setLayoutX(150);
                    this.getImg().setLayoutY(center.getPointY());
                    this.getImg().setImage(new Image(path));
                    this.setPath(path);
                }

                Thread.sleep(700);
                for (Basic_Zombie zombie : zombies){
                    if(this.Lane==zombie.getLane()){
                        zombie.setHp(zombie.getHp()-20);

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
