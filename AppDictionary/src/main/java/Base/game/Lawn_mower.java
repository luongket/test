package Base.game;

import Base.game.Zombie.Basic_Zombie;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.util.List;

public class Lawn_mower extends GameElement {
    private int Lane;
    private ImageView image;
    private boolean OnAction=false;
    private boolean remove=false;

    public boolean isRemove() {
        return remove;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
    Media explode = new Media(getClass().getResource("/asset/Sound/lawnmower.wav").toString());
    MediaPlayer mediaPlayer = new MediaPlayer(explode);

    public Lawn_mower(int width, int height, int lane, float x, float y) {
        super(width, height);
        Lane = lane;
        this.image=new ImageView(new Image("/asset/Game/lawn_mover.gif"));
        image.setFitWidth(width);image.setFitHeight(height);
        image.setLayoutX(x);image.setLayoutY(y);
        this.setPath("/asset/Game/lawn_mover.gif");

    }

    public boolean isOnAction() {
        return OnAction;
    }

    public void setOnAction(boolean onAction) {
        OnAction = onAction;
    }

    public int getLane() {
        return Lane;
    }

    public void setLane(int lane) {
        Lane = lane;
    }
    public void checkAction (List<Basic_Zombie> zombies){
        if(this.isOnAction()){
            move();
        }
        for(Basic_Zombie zombie : zombies){
            if(zombie.getLane()==this.getLane()){
                if((zombie.getImage().getLayoutX()-this.image.getLayoutX())<=0&&
                        (zombie.getImage().getLayoutX()-this.image.getLayoutX())>=-30){
                    if(!this.isOnAction()){
                        this.setOnAction(true);
                    }
                    zombie.setHp(0);
                }
            }

        }
        UpdateAnimation();
    }
    public void UpdateAnimation() {
        if(this.isOnAction()){
            String path="/asset/Game/lawnMoverAction.gif";
            if(!path.equals(this.getPath())){
                this.image.setImage(new Image("/asset/Game/lawnMoverAction.gif"));
                this.setPath(path);
            }
        }else {
            String path="/asset/Game/lawn_mover.gif";
            if(!path.equals(this.getPath())){
                this.image=new ImageView(new Image("/asset/Game/lawn_mover.gif"));
                this.setPath(path);
            }
        }
    }
    public void move(){

        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
        if(this.image.getLayoutX()<1000){
            this.image.setLayoutX(this.image.getLayoutX()+1);
        }else {
            remove=true;
        }
    }
}
