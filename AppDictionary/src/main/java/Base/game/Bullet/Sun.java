package Base.game.Bullet;

import Base.game.Plant.Point;
import Controller.gameController;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class Sun {
    private ImageView img = new ImageView(new Image("/asset/Game/Sun.gif"));
    private double posY;

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    private boolean falling = true;

    public ImageView getImg() {
        return img;
    }

    public Sun(Point center, Pane pane) {
        img.setLayoutX(center.getPointX());
        img.setLayoutY(center.getPointY());
        posY = center.getPointY();
        img.setFitHeight(50);
        img.setFitWidth(50);
        makeImage(pane);
    }

    public void makeImage(Pane pane) {
        this.img.setOnMouseClicked(e -> {
            this.getImg().setVisible(false);
            this.getImg().setLayoutY(this.getImg().getLayoutY() - 900);
            gameController.UpdateSunCount(10);
        });
        pane.getChildren().add(this.getImg());
    }

    public void moveSun() {

        if (img.getLayoutY() <= posY + 100 && img.getLayoutY() > posY) {
            img.setVisible(true);
        } else if (img.getLayoutY() > posY + 50) {
            return;
        }
        img.setLayoutY(img.getLayoutY() + 1);
    }


}
