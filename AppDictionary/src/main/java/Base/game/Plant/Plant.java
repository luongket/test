package Base.game.Plant;

import Base.game.GameElement;
import Base.game.Zombie.Basic_Zombie;
import Controller.gameController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.List;

public class Plant extends GameElement {
    protected Point center;
    protected double price;
    protected double hp;
    protected String name;
    ImageView img;
    public int Lane;
    public int row,col;

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }



    public Plant(int width, int height, Point center, double price, double hp,
                 String name,int Lane) {
        super(width, height);
        this.center = center;
        this.price = price;
        this.hp = hp;
        this.name = name;
        this.Lane = Lane;
        String tmp = "/asset/Game/" + name+"/"+name + ".gif";
        this.setPath(tmp);
    }

    public void makeImage(GridPane pane, int col, int row) {
        img = new ImageView();
        Image im = new Image(path, (double) width, (double) height, false, false);
        img.setImage(im);
        img.setOnMouseClicked(event ->{
            if(gameController.Shovel){
                this.Reset();
                this.setHp(0);
                gameController.Shovel=false;
                gameController.Sun+=this.getPrice();
            }
        });
        pane.add(img, col, row);
        Lane=row+1;
        this.row=row;
        this.col=col;
    }
    public void makeImage(Pane pane){
        img = new ImageView();
        Image im = new Image(path, (double) width, (double) height, false, false);
        img.setImage(im);
        this.getImg().setLayoutX(center.getPointX());
        this.getImg().setLayoutY(center.getPointY());
        pane.getChildren().add(img);
    }
    public void ActionPlant(List<Basic_Zombie> zombies){

    }
    public void ActionPlant(){

    }
    public void Reset(){
        this.getImg().setVisible(false);
    }
}
