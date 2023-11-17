package Base.game.Zombie;

public class ZombieData {
    private float speed;
    private float damge;
    private float Hp;
    private String name;
    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDamge() {
        return damge;
    }

    public void setDamge(float damge) {
        this.damge = damge;
    }

    public ZombieData(float speed, float damge, float hp, String name) {
        this.speed = speed;
        this.damge = damge;
        this.Hp = hp;
        this.name = name;
    }

    public float getHp() {
        return Hp;
    }

    public void setHp(float hp) {
        Hp = hp;
    }
}
