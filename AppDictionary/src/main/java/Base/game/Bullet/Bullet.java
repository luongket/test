package Base.game.Bullet;

import Base.game.GameElement;
import Base.game.Plant.Point;
import Base.game.Zombie.Basic_Zombie;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;

public class Bullet extends GameElement {
    private float damage;
    private float speed;
    private ImageView image;
    private int Lane;
    private Pane pane;
    private Point position;
    public boolean falg=false;
    public String name=null;

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Bullet(float damage, float speed, int lane, Pane pane, Point position,String name,int width,int height) {
        super(width, height);
        this.damage = damage;
        this.speed = speed;
        this.image = new ImageView(new Image("/asset/Game/"+name+".png"));
        Lane = lane;
        this.pane = pane;
        this.name=name;
        this.position = position;
        this.image.setLayoutX(position.getPointX()-5);
        this.image.setLayoutY(position.getPointY());
        this.image.setFitHeight(width);this.image.setFitWidth(height);
        this.image.setVisible(false);
        this.pane.getChildren().add(this.image);

    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public int getLane() {
        return Lane;
    }

    public void setLane(int lane) {
        Lane = lane;
    }

    public Pane getPane() {
        return pane;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public void CollisionZombie(){
        this.getImage().setVisible(false);
        this.getImage().setLayoutX(this.getImage().getLayoutX()-900);
    }
    public void reset(){
        this.falg=false;
        this.getImage().setVisible(falg);
        this.getImage().setLayoutX(this.getPosition().getPointX()-4);
    }
    public boolean Collision(Basic_Zombie zombie){
        if(falg&&((zombie.getImage().getLayoutX()-this.getImage().getLayoutX())<=-15)&&
                (zombie.getImage().getLayoutX()-this.getImage().getLayoutX())>-50&&this.Lane==zombie.getLane()){
            CollisionZombie();
            zombie.setHp(zombie.getHp()-this.getDamage());
            return true;
        }
        return false;
    }

}
