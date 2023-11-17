package Base.game.Zombie;

import java.util.ArrayList;
import java.util.List;

public class DataZombie {
    public static List<ZombieData> Zombie=new ArrayList<ZombieData>();
    public ZombieData zombie=new ZombieData(2,0.5f,20,"Zombie");
    public ZombieData zombie1=new ZombieData(2,0.5f,25,"ConeHeadZombie");
    public ZombieData zombie2=new ZombieData(2,0.75f,25,"FlagZombie");
    public ZombieData zombie3=new ZombieData(2,0.75f,30,"BucketheadZombie");

    public DataZombie() {
        Zombie.add(zombie);
        Zombie.add(zombie1);
        Zombie.add(zombie2);
        Zombie.add(zombie3);
    }
}
