package Controller;

import Base.App.Question;
import Base.App.QuestionLoader;
import Base.game.Bullet.Bullet;
import Base.game.Bullet.IceBullet;
import Base.game.GameState;
import Base.game.Plant.*;
import Base.game.Card;
import Base.game.Zombie.Basic_Zombie;
import Base.game.Lawn_mower;
import Base.game.Zombie.DataZombie;
import Base.game.Zombie.ZombieData;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import javafx.event.EventHandler;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class gameController implements Initializable {
    private ParallelTransition combinedTransition;
    public static GameState state = GameState.playGame;
    private DataPlant seedBank = new DataPlant();
    @FXML
    private AnchorPane GameRoot;
    @FXML
    private AnchorPane StartPane;
    @FXML
    private GridPane lawn_grid;
    private static int level = 8;
    public static int Sun = 250;
    public static boolean Shovel=false;
    public static final int LANE1 = 10;
    public static final int LANE2 = 70;
    public static final int LANE3 = 130;
    public static final int LANE4 = 190;
    public static final int LANE5 = 250;
    public boolean level_complete = false;
    @FXML
    private HBox BoxPlant;
    @FXML
    private GridPane table;
    @FXML
    private AnchorPane PausePane;

    @FXML
    private ImageView imageView;
    @FXML
    private Label SunLabel;
    public static int val;
    public static boolean UpdateSun = false;
    public static List<Plant> listPlant = new ArrayList<Plant>();
    public static List<SpikeRock> spike = new ArrayList<SpikeRock>();
    public static List<Basic_Zombie> ZombieList = Collections.synchronizedList(new ArrayList<Basic_Zombie>());
    public static List<Lawn_mower> lawnMovers = new ArrayList<Lawn_mower>();
    public static List<Card> plantIterm = new ArrayList<Card>();
    public DataZombie zombie = new DataZombie();

    /**
     * ket.
     */
    @FXML
    private ToggleGroup Choice;

    @FXML
    private Label lblQuestion;

    @FXML
    private RadioButton rdoA;

    @FXML
    private RadioButton rdoB;

    @FXML
    private RadioButton rdoC;

    private List<Question> questions;
    private int currentQuestionIndex = 0;


    public void ShowData() {
        PausePane.setVisible(true);
        PausePane.setViewOrder(-2);

        for (int i = 0; i < seedBank.getHeroes().size(); i++) {
            seedBank.getHeroes().get(i).getImg().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Node node = (ImageView) event.getSource();
                    int row = table.getRowIndex(node);
                    int column = table.getColumnIndex(node);
                    int plant = column * 4 + row;
                    if (plantIterm.size() < 4) {
                        if (!BoxPlant.getChildren().contains(node)) {
                            seedBank.getHeroes().get(plant).getImgBox().setOnMouseClicked(mouseEvent -> {
                                val = BoxPlant.getChildren().indexOf((ImageView) mouseEvent.getSource());
                                System.out.print(val);
                            });
                            BoxPlant.getChildren().add(seedBank.getHeroes().get(plant).getImgBox());
                            plantIterm.add(seedBank.getHeroes().get(plant));
                        }
                    }
                }
            });
            table.add(seedBank.getHeroes().get(i).getImg(), i / 4, i % 4, 1, 1);
        }
    }

    public void CloseTable() {
        table.getChildren().removeAll();
        PausePane.setVisible(false);

    }
    public void ShovelClick(){
        Shovel=true;
        val=-1;
    }
    public static void UpdateSunCount(int value) {
        Sun += value;
        UpdateSun=true;
    }

    public void showSun() {
        if(!UpdateSun){
            return;
        }
        SunLabel.setText(Integer.toString(Sun));
        UpdateSun=false;
    }

    public void AddPlant(Card iterm, int row, int col, Point center) {
        if (Sun < iterm.getPrice()) {
            return;
        }
        Sun-=iterm.getPrice();
        UpdateSun=true;
        switch (iterm.getPlantform()) {
            case pea:
                Bullet bullet = new Bullet(iterm.getDamge(), iterm.getSpeed(), row + 1, GameRoot, center, iterm.getBulletname(), 20, 20);
                Pea pea = new Pea(40, 40, center, iterm.getHp(), iterm.getNamePlant(), row + 1, bullet);
                pea.makeImage(lawn_grid, col, row);
                listPlant.add(pea);
                break;
            case Walnut:
                Walnut walnut = new Walnut(40, 40, center, iterm.getPrice(), iterm.getHp(), iterm.getNamePlant(), row + 1);
                walnut.makeImage(lawn_grid, col, row);
                listPlant.add(walnut);
                break;
            case cactus:
                Bullet bullet1 = new Bullet(iterm.getDamge(), iterm.getSpeed(), row + 1, GameRoot, center, iterm.getBulletname(), 20, 20);
                Cactus cactus = new Cactus(50, 50, center, iterm.getHp(), iterm.getNamePlant(), row + 1, bullet1);
                cactus.makeImage(lawn_grid, col, row);
                listPlant.add(cactus);
                break;
            case ThreePeater:
                ThreePeater threePeater = new ThreePeater(50, 50, center,
                        iterm.getPrice(), iterm.getHp(), iterm.getNamePlant(),
                        row + 1, iterm.getDamge(), iterm.getSpeed(), GameRoot, iterm.getBulletname());
                threePeater.makeImage(lawn_grid, col, row);
                listPlant.add(threePeater);
                break;
            case GatlingPea:
                GatlingPea gatlingPea = new GatlingPea(50, 50, center,
                        iterm.getPrice(), iterm.getHp(), iterm.getNamePlant(),
                        row + 1, iterm.getDamge(), iterm.getSpeed(), GameRoot, iterm.getBulletname());
                gatlingPea.makeImage(lawn_grid, col, row);
                listPlant.add(gatlingPea);
                break;
            case IcePea:
                IceBullet iceBullet = new IceBullet(iterm.getDamge(), iterm.getSpeed(), row + 1, GameRoot, center, iterm.getBulletname(), 20, 20);
                IcePea icePea = new IcePea(40, 40, center, iterm.getHp(), iterm.getNamePlant(), row + 1, iceBullet);
                icePea.makeImage(lawn_grid, col, row);
                listPlant.add(icePea);
                break;
            case SunFlower:
                SunFlower sunFlower = new SunFlower(40, 40, center, iterm.getPrice(), iterm.getHp(), iterm.getNamePlant(), row + 1, GameRoot);
                sunFlower.makeImage(lawn_grid, col, row);
                listPlant.add(sunFlower);
                break;
            case SpikeRock:
                SpikeRock spikeRock = new SpikeRock(40, 40, center, iterm.getPrice(), iterm.getHp(), iterm.getNamePlant(), row + 1, iterm.getDamge());
                spikeRock.makeImage(lawn_grid, col, row);
                spike.add(spikeRock);
                break;
            case PotatoMine:
                PotatoMine potatoMine = new PotatoMine(40, 40, center, iterm.getPrice(), iterm.getHp(), iterm.getNamePlant(), row + 1);
                potatoMine.makeImage(lawn_grid, col, row);
                listPlant.add(potatoMine);
                break;
            case cherry:
                Cherry cherry = new Cherry(40, 40, center, iterm.getPrice(), iterm.getHp(), iterm.getNamePlant(), row + 1);
                cherry.makeImage(GameRoot);
                listPlant.add(cherry);
                break;
            case chilli:
                Chilli chilli = new Chilli(40, 40, center, iterm.getPrice(), iterm.getHp(), iterm.getNamePlant(), row + 1);
                chilli.makeImage(GameRoot);
                listPlant.add(chilli);
                break;
            case DroomShroom:
                DroomShroom tonchWood = new DroomShroom(40, 40, center, iterm.getPrice(), iterm.getHp(), iterm.getNamePlant(), row + 1);
                tonchWood.makeImage(lawn_grid, col, row);
                listPlant.add(tonchWood);
                break;
            case IceShroom:
                IceShroom iceShroom = new IceShroom(40, 40, center, iterm.getPrice(), iterm.getHp(), iterm.getNamePlant(), row + 1);
                iceShroom.makeImage(lawn_grid, col, row);
                listPlant.add(iceShroom);
                break;
            case Squash:
                Squash squash = new Squash(60, 60, center, iterm.getPrice(), iterm.getHp(), iterm.getNamePlant(), row + 1);
                squash.makeImage(lawn_grid, col, row);
                listPlant.add(squash);
                break;

        }

    }

    public void removePlant(Plant plant) {
        plant.Reset();
        listPlant.remove(plant);
    }

    public Card choosePlant(MouseEvent event) throws IOException {
        Node node = (Node) event.getSource();
        int Index = BoxPlant.getChildren().indexOf(node);
        return plantIterm.get(Index);
    }

    public void SpawnZombie() {
        if (ZombieList.size() != 0||PausePane.isVisible()) {
            return;
        }
        Random random = new Random();
        level += 1;

        int countZombie = random.nextInt(2 + 3 * level) + 3 * level;

        Point center = new Point(700, 700);
        Timeline SpawnTime = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            int indexZombieName=level<=11?random.nextInt(level/3+1):random.nextInt(4);
            ZombieData tmp=zombie.Zombie.get(indexZombieName);
            int Lane = random.nextInt(4);
            switch (Lane) {
                case 0:
                    center.setPointY(LANE1);
                    Basic_Zombie newZom1 = new Basic_Zombie(tmp.getSpeed(), tmp.getName()
                            , center, Lane + 1,
                            tmp.getHp(), tmp.getDamge(), 75, 75,
                            GameRoot);
                    ZombieList.add(newZom1);
                    break;
                case 1:
                    center.setPointY(LANE2);
                    Basic_Zombie newZom2 = new Basic_Zombie(tmp.getSpeed(), tmp.getName()
                            , center, Lane + 1,
                            tmp.getHp(), tmp.getDamge(), 75, 75,
                            GameRoot);
                    ZombieList.add(newZom2);
                    break;
                case 2:
                    center.setPointY(LANE3);
                    Basic_Zombie newZom3 = new Basic_Zombie(tmp.getSpeed(), tmp.getName()
                            , center, Lane + 1,
                            tmp.getHp(), tmp.getDamge(), 75, 75,
                            GameRoot);
                    ZombieList.add(newZom3);
                    break;
                case 3:
                    center.setPointY(LANE4);
                    Basic_Zombie newZom4= new Basic_Zombie(tmp.getSpeed(), tmp.getName()
                            , center, Lane + 1,
                            tmp.getHp(), tmp.getDamge(), 75, 75,
                            GameRoot);
                    ZombieList.add(newZom4);
                    break;
                case 4:
                    center.setPointY(LANE5);
                    Basic_Zombie newZom5 = new Basic_Zombie(tmp.getSpeed(), tmp.getName()
                            , center, Lane + 1,
                            tmp.getHp(), tmp.getDamge(), 75, 75,
                            GameRoot);
                    ZombieList.add(newZom5);
                    break;
            }

        }));
        SpawnTime.setCycleCount(countZombie);
        SpawnTime.play();
    }


    public void RemoveZombie(Basic_Zombie zombie) {
        zombie.getImage().setVisible(false);
        ZombieList.remove(zombie);
    }


    @FXML
    public void choose(MouseEvent event) throws IOException {
        if (PausePane.isVisible() || val < 0 || val >= plantIterm.size()) {
            return;
        }
        Node node = event.getPickResult().getIntersectedNode();
        int row = lawn_grid.getRowIndex(node);
        int column = lawn_grid.getColumnIndex(node);
        for (int i = 0; i < listPlant.size(); i++) {
            if (listPlant.get(i).row == row && listPlant.get(i).col == column) {

                return;
            }
        }
        Media click = new Media(getClass().getResource("/asset/Sound/click.wav").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(click);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.play();
        Point center = new Point(node.getLayoutX() + 170, node.getLayoutY() + 55);
        System.out.println(row + " " + column);
        AddPlant(plantIterm.get(val), row, column, center);

    }

    Timeline updatePlant = new Timeline(new KeyFrame(Duration.seconds(0.02), event -> {
        for (int i = 0; i < listPlant.size(); i++) {
            listPlant.get(i).ActionPlant(ZombieList);
            listPlant.get(i).ActionPlant();
            if (listPlant.get(i).getHp() <= 0) {
                removePlant(listPlant.get(i));
            }

        }
        showSun();
    }));
    Timeline updateZombie = new Timeline(new KeyFrame(Duration.seconds(0.1), event -> {
        for (int i = 0; i < ZombieList.size(); i++) {
            if (listPlant.size() == 0) {
                ZombieList.get(i).collisionPlant = false;
            } else {
                for (int j = 0; j < listPlant.size(); j++) {
                    ZombieList.get(i).collisionPlant = false;
                    if (ZombieList.get(i).eatPlant(listPlant.get(j))) {
                        ZombieList.get(i).collisionPlant = true;
                        break;
                    }
                }
            }

            ZombieList.get(i).Move();
            ZombieList.get(i).UpdateAnimation();

            if (ZombieList.get(i).getHp() <= 0) {

                RemoveZombie(ZombieList.get(i));
            }
        }
    }));
    Timeline UpdateLevel = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
        SpawnZombie();
        showSun();
    }));
    Timeline UpdateLawnMover = new Timeline(new KeyFrame(Duration.seconds(0.02), event -> {
        for (int i = 0; i < lawnMovers.size(); i++) {
            lawnMovers.get(i).checkAction(ZombieList);

        }

    }));
    Timeline UpdateSpike = new Timeline(new KeyFrame(Duration.seconds(0.04), event -> {
        for (int i = 0; i < spike.size(); i++) {
            spike.get(i).ActionPlant(ZombieList);
        }
    }));

    public void SpawnLawnMover() {
        Lawn_mower newLawnMower1 = new Lawn_mower(50, 50, 1, 100, LANE1 + 40);
        Lawn_mower newLawnMower2 = new Lawn_mower(50, 50, 2, 100, LANE2 + 40);
        Lawn_mower newLawnMower3 = new Lawn_mower(50, 50, 3, 100, LANE3 + 40);
        Lawn_mower newLawnMower4 = new Lawn_mower(50, 50, 4, 100, LANE4 + 40);
        Lawn_mower newLawnMower5 = new Lawn_mower(50, 50, 5, 100, LANE5 + 40);
        lawnMovers.add(newLawnMower1);
        lawnMovers.add(newLawnMower2);
        lawnMovers.add(newLawnMower3);
        lawnMovers.add(newLawnMower4);
        lawnMovers.add(newLawnMower5);
        for (int i = 0; i <lawnMovers.size();i++) {
            GameRoot.getChildren().add(lawnMovers.get(i).getImage());
        }
    }
    public void Replay(){
        Sun=150;
        level=0;
        if (combinedTransition != null) {
            combinedTransition.stop();
        }

        for (int i=0;i<ZombieList.size();i++) {
            ZombieList.get(i).getImage().setVisible(false);
        }
        ZombieList.clear();
        for (int i=0;i<listPlant.size();i++) {
            listPlant.get(i).Reset();
        }
        listPlant.clear();
        plantIterm.clear();
        System.out.println(plantIterm.size() );
        for (int i = 0; i <lawnMovers.size(); i++) {
            lawnMovers.get(i).setOnAction(false);
            lawnMovers.get(i).getImage().setLayoutX(100);
        }
        for (int i=0;i<spike.size();i++) {
            spike.get(i).getImg().setVisible(false);
        }
        spike.clear();
        BoxPlant.getChildren().clear();
        PausePane.setVisible(true);
        // Bắt đầu lại ParallelTransition
        combinedTransition.playFromStart();

    }
    public void GamePlay() {
        showSun();
        Media BackGround = new Media(getClass().getResource("/asset/Sound/bgmfight.mp3").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(BackGround);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView view = new MediaView(mediaPlayer);
        UpdateSpike.setCycleCount(Animation.INDEFINITE);
        UpdateLawnMover.setCycleCount(Animation.INDEFINITE);
        updatePlant.setCycleCount(Animation.INDEFINITE);
        updateZombie.setCycleCount(Animation.INDEFINITE);
        UpdateLevel.setCycleCount(Animation.INDEFINITE);
        combinedTransition = new ParallelTransition(
                UpdateLevel,
                updateZombie,
                updatePlant,
                UpdateLawnMover,
                UpdateSpike
        );
        ShowData();
        StartPane.setVisible(false);
        GameRoot.setVisible(true);
        BoxPlant.setViewOrder(-1);
        imageView.setImage(new Image("/asset/Game/Lawn.png"));
        imageView.setFitWidth(840);
        imageView.setFitHeight(400);
        SpawnLawnMover();

        combinedTransition.play();
        try {
            questions = QuestionLoader.loadQuestions("C:\\OOP_IN2\\testMySQL\\src\\main\\java\\Controller\\question.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void GameEnd() {

    }

    public void GameStart() {
//        imageView.setImage(new Image("/asset/Game/bg.jpg"));
//        imageView.setFitWidth(840);
//        imageView.setFitHeight(560);
//        StartPane.setVisible(true);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        level = 0;
        listPlant.clear();
        ZombieList.clear();
        spike.clear();
        plantIterm.clear();
        lawnMovers.clear();
        Thread game = new Thread(() -> {
            try {
                Thread.sleep(3600); // Tạm dừng luồng này trong 2 giây
                // Code tiếp theo sau khi tạm dừng
                imageView.setImage(new Image("/asset/Game/cg18.png"));
                imageView.setOnMouseClicked(event->{
                    GamePlay();
                    showQuestion();
                });

            } catch (InterruptedException e) {
                // Xử lý ngoại lệ nếu cần
            }
        });

        game.start();

    }


    /**
     * ket.
     */
    @FXML
    void checkYourAnswer(ActionEvent event) {
        Question currentQuestion = questions.get(currentQuestionIndex);
        RadioButton selectedRadioButton = (RadioButton) Choice.getSelectedToggle();
        String selectedAnswer = selectedRadioButton.getText();

        if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
            UpdateSunCount(50);
            showSun();
            nextAnswer(event);
        } else {
            System.out.println("Incorrect!");
            nextAnswer(event);
        }
    }

    @FXML
    void nextAnswer(ActionEvent event) {
        currentQuestionIndex++;

        if (currentQuestionIndex < questions.size()) {
            showQuestion();
        } else {
            System.out.println("End of questions.");
        }
    }
    private void showQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);

        lblQuestion.setText(currentQuestion.getQuestion());

        List<String> choices = currentQuestion.getChoices();

        if (choices != null && choices.size() >= 3) {
            rdoA.setText(choices.get(0));
            rdoB.setText(choices.get(1));
            rdoC.setText(choices.get(2));
        } else {
            System.err.println("Error: Choices list does not have enough elements.");
        }
    }
}
