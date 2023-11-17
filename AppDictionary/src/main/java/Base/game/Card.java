package Base.game;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
    private String namePlant;
    private float hp;
    private float damge;
    private float speed;
    private ImageView img;
    private ImageView imgBox;
    private float price;
    public String path;

    public String getBulletname() {
        return bulletname;
    }

    public void setBulletname(String bulletname) {
        this.bulletname = bulletname;
    }

    private String bulletname;

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    private PlantSeed plantform;

    public Card() {
    }

    public Card(String namePlant, float hp,
                float damge, float speed, String path,
                float price, String bulletname, PlantSeed plantform) {
        this.namePlant = namePlant;
        this.hp = hp;
        this.price=price;
        this.damge = damge;
        this.speed = speed;
        this.img = new ImageView(new Image("/asset/Game/"+namePlant+"/"+namePlant+".png"));
        img.setFitWidth(60);img.setFitHeight(60);
        this.imgBox = new ImageView(new Image("/asset/Game/"+namePlant+"/"+namePlant+".png"));
        imgBox.setFitWidth(45);imgBox.setFitHeight(45);
        this.bulletname=namePlant+"/"+bulletname;
        this.path=path;
        this.plantform=plantform;

    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public String getNamePlant() {
        return namePlant;
    }

    public void setNamePlant(String namePlant) {
        this.namePlant = namePlant;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getDamge() {
        return damge;
    }

    public void setDamge(float damge) {
        this.damge = damge;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public PlantSeed getPlantform() {
        return plantform;
    }

    public ImageView getImgBox() {
        return imgBox;
    }

    public void setImgBox(ImageView imgBox) {
        this.imgBox = imgBox;
    }

    public void UpdateImageBox(int sun){
        if(sun<this.getPrice()){
            this.getImgBox().setImage(new Image("asset/Game/"+path+"clock.png"));
        }
        else {
            this.getImgBox().setImage(new Image("asset/Game/"+path+".png"));
        }
    }
}
