package Base.game.Zombie;

import Base.game.Plant.Plant;
import Base.game.Plant.Point;
import Base.game.GameState;
import Controller.gameController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


enum state {
    poisoned,
    freeze,
    Normal,
    burn;
}

public class Basic_Zombie {
    private float movementSpeed;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String Name;
    private Point Position;
    private state stateZombie;
    private float MaxHp;
    private float hp;
    private float damage;
    private float Width;
    private float Height;
    private float tmp;
    private ImageView image;
    private int Lane;
    public Boolean collisionPlant=false;
    public int heat=5;


    public ImageView getImage() {
        return image;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public float getWidth() {
        return Width;
    }

    public void setWidth(float width) {
        Width = width;
    }

    public float getHeight() {
        return Height;
    }

    public void setHeight(float height) {
        Height = height;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getMovementSpeed() {
        return movementSpeed;
    }

    public void setMovementSpeed(float movementSpeed) {
        this.movementSpeed = movementSpeed;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Point getPosition() {
        return Position;
    }

    public void setPosition(Point position) {
        Position = position;
    }

    public state getStateZombie() {
        return stateZombie;
    }

    public void setStateZombie(state stateZombie) {
        this.stateZombie = stateZombie;
    }

    public Basic_Zombie(float movementSpeed, String name, Point position, int Lane,
                        float maxHp, float damage,
                        float width, float height, Pane pane) {
        this.movementSpeed = movementSpeed;
        tmp=movementSpeed;
        Name = name;
        Position = position;
        this.stateZombie = state.Normal;
        this.MaxHp = maxHp;
        this.hp = maxHp;
        this.damage = damage;
        this.Lane=Lane;
        Width = width;
        Height = height;
        this.path="/asset/Game/Zombie/"+name+".gif";
        image=new ImageView(new Image(path));
        this.image.setLayoutX(position.getPointX());
        this.image.setLayoutY(position.getPointY());
        this.image.setFitHeight(height);this.image.setFitWidth(width);
        pane.getChildren().add(this.image);
    }



    public int getLane() {
        return Lane;
    }

    public void setLane(int lane) {
        Lane = lane;
    }


    public void Move(){
        if(this.heat<0){
            this.setStateZombie(state.freeze);
        }
        if(this.getStateZombie()==state.freeze){
            this.setMovementSpeed(0.5f);
        }
        if(hp>0&&this.getImage().getLayoutX()>80&&!collisionPlant){
            this.getImage().setLayoutX(this.getImage().getLayoutX()-this.getMovementSpeed());

        }else if(this.getImage().getLayoutX()<=80){
            gameController.state= GameState.lostGame;
        }
    }
    public Boolean eatPlant(Plant plant){
        if(plant.Lane==this.getLane()){
            if((this.getImage().getLayoutX()-plant.getCenter().getPointX())<-15
                    &&(this.getImage().getLayoutX()-plant.getCenter().getPointX())>-50){
                System.out.println((this.getImage().getLayoutX()-plant.getCenter().getPointX()));
                plant.setHp(plant.getHp()-this.getDamage());
                return true;

            }
            return false;

        }
        return false;

    }
    public void UpdateAnimation(){
        String path="/asset/Game/Zombie/"+this.getName()+"Attack"+".gif";
        String move="/asset/Game/Zombie/"+this.getName()+".gif";
        if(this.collisionPlant&&!path.equals(this.path)){
            this.getImage().setImage(new Image(path));
            this.path=path;
            System.out.println(this.path);
        }else if(!this.collisionPlant&&!move.equals(this.path)){
            this.path=move;
            this.getImage().setImage(new Image(move));
        }
    }
    public void ZombieNotMove(){
        Thread zombieThread=new Thread(() -> {
            this.setMovementSpeed(0);
            try {
                Thread.sleep(10000); // Tạm dừng luồng này trong 2 giây
                // Code tiếp theo sau khi tạm dừng
                this.setMovementSpeed(this.tmp);

            } catch (InterruptedException e) {
                // Xử lý ngoại lệ nếu cần
            }
        });
        zombieThread.start();
    }
}
