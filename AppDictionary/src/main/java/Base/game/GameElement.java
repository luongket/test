package Base.game;

import javafx.scene.layout.Pane;

public abstract class GameElement {
    protected String path;
    protected int width;
    protected int height;

    public GameElement(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public  void makeImage(Pane pane, int col, int row){

    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
